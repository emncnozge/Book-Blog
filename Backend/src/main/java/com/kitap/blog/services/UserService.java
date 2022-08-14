package com.kitap.blog.services;

import com.kitap.blog.entities.User;
import com.kitap.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public boolean login(String email, String password, String token) throws NoSuchAlgorithmException {
        User user = userRepository.getUserByEmail(email);
        if (userRepository.existsById(user.getUser_id()) && token.equals(appToken)) {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashedPassword = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword).equals(user.getPassword());
        }
        return false;
    }

    public List<User> getUsers(String token) {
        if (token.equals(appToken)) {
            return userRepository.findAll();
        } else return null;
    }

    public User getUser(Long user_id, String token) {
        if (token.equals(appToken)) {
            return userRepository.findById(user_id)
                    .orElseThrow(() -> new IllegalStateException("Error! Selected user doesn't exist."));
        } else return null;
    }

    public boolean addUser(User user, String token) throws NoSuchAlgorithmException {
        if (!userRepository.existsByEmail(user.getEmail()) && token.equals(appToken)) {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashedPassword = digest.digest(
                    user.getPassword().getBytes(StandardCharsets.UTF_8));
            user.setPassword(Base64.getEncoder().encodeToString(hashedPassword));
            userRepository.saveAndFlush(user);
            return true;
        } else return false;
    }

    public boolean deleteUser(Long user_id, String token) {
        if (token.equals(appToken)) {
            boolean exists = userRepository.existsById(user_id);
            if (exists) {
                userRepository.deleteById(user_id);
                return true;
            } else
                return false;

        } else return false;
    }

    @Transactional
    public boolean updateUser(Long user_id, String email, String name, String password, String about, String photo_url, boolean isAdmin, String token) {
        if (token.equals(appToken)) {
            boolean exists = userRepository.existsById(user_id);
            if (exists) {
                if (email.equals("null")) email = null;
                if (name.equals("null")) name = null;
                if (password.equals("null")) password = null;
                if (about.equals("null")) about = null;
                if (photo_url.equals("null")) photo_url = null;
                User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalStateException("Error"));
                if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
                    user.setEmail(email);
                }
                if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
                    user.setName(name);
                }
                if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
                    user.setPassword(password);
                }
                if (about != null && about.length() > 0 && !Objects.equals(user.getAbout(), about)) {
                    user.setAbout(about);
                }
                if (photo_url != null && photo_url.length() > 0 && !Objects.equals(user.getPhoto_url(), photo_url)) {
                    user.setPhoto_url(photo_url);
                }
                if (!Objects.equals(user.getIs_admin(), isAdmin)) {
                    user.setIs_admin(isAdmin);
                }
                return true;
            } else
                return false;
        }
        return false;
    }
}
