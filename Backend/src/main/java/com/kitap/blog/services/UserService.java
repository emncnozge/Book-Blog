package com.kitap.blog.services;

import com.kitap.blog.entities.User;
import com.kitap.blog.repositories.UserRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String appToken = "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long login(String email, String password, String token) throws NoSuchAlgorithmException {
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.getUserByEmail(email);

            if (userRepository.existsById(user.getUser_id()) && token.equals(appToken)) {
                MessageDigest digest = MessageDigest.getInstance("SHA3-256");
                byte[] hashedPassword = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                System.out.println(Base64.getEncoder().encodeToString(hashedPassword));

                if (Base64.getEncoder().encodeToString(hashedPassword).equals(user.getPassword()))
                    return user.getUser_id();
            }
        }
        return 0L;
    }

    public List<User> getUsers(String token) {
        if (token.equals(appToken)) {
            return userRepository.findAll();
        } else
            return null;
    }

    public User getUser(Long user_id, String token) {
        if (token.equals(appToken)) {
            return userRepository.findById(user_id)
                    .orElseThrow(() -> new IllegalStateException("Error! Selected user doesn't exist."));
        } else
            return null;
    }

    public Long addUser(User user, String token) throws NoSuchAlgorithmException {
        if (!userRepository.existsByEmail(user.getEmail()) && token.equals(appToken)) {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashedPassword = digest.digest(
                    user.getPassword().getBytes(StandardCharsets.UTF_8));
            user.setPassword(Base64.getEncoder().encodeToString(hashedPassword));
            user.setAbout("");
            user.setPhoto_url("");
            userRepository.saveAndFlush(user);
            return user.getUser_id();
        } else
            return 0L;
    }

    public boolean deleteUser(Long user_id, String token) {
        if (token.equals(appToken)) {
            userRepository.deleteById(user_id);
            return true;

        } else
            return false;
    }

    @Transactional
    public boolean updateUser(Long user_id, String email, String name, String password, String about, String photo_url,
            boolean isAdmin, String token) throws NoSuchAlgorithmException {
        if (token.equals(appToken)) {
            if (email.equals("null"))
                email = null;
            if (name.equals("null"))
                name = null;
            if (password.equals("null"))
                password = null;
            if (about.equals("null"))
                about = null;
            if (photo_url.equals("null"))
                photo_url = null;
            User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalStateException("Error"));
            if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)
                    && !userRepository.existsByEmail(email)) {
                user.setEmail(email);
            }
            if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
                user.setName(name);
            }
            if (password != null && password.length() > 0) {
                MessageDigest digest = MessageDigest.getInstance("SHA3-256");
                byte[] hashedPassword = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                user.setPassword(Base64.getEncoder().encodeToString(hashedPassword));
            }
            if (about != null && about.length() > 0 && !Objects.equals(user.getAbout(), about)) {
                user.setAbout(about);
            }
            if (photo_url != null) {
                user.setPhoto_url(photo_url);
            }
            if (!Objects.equals(user.getIs_admin(), isAdmin)) {
                user.setIs_admin(isAdmin);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public void addUserPhoto(Long user_id, MultipartFile multipartFile, HttpServletResponse httpServletResponse)
            throws IOException {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalStateException("Error"));
        String fileName = StringUtils
                .cleanPath(multipartFile.getOriginalFilename() == null ? "" : multipartFile.getOriginalFilename());
        String uploadDir = "images/user-photos/" + user_id;
        user.setPhoto_url(uploadDir + "/" + fileName);

        byte[] bytes = multipartFile.getBytes();

        File dir = new File(uploadDir);
        if (!dir.exists())
            dir.mkdirs();

        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        httpServletResponse.sendRedirect("http://localhost:3000/profile");
    }

    public InputStreamResource getUserPhoto(Long user_id, HttpServletResponse response) throws IOException {

        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalStateException("Error"));
        Resource resource1 = new PathResource(user.getPhoto_url());
        response.setContentType("image/png");
        try {
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        } catch (Exception e) {
            resource1 = new PathResource("images/user-photos/default.png");
            response.setContentType("image/png");
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        }

    }

}
