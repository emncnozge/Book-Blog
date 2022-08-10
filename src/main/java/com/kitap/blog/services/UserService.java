package com.kitap.blog.services;

import com.kitap.blog.entities.User;
import com.kitap.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new IllegalStateException("Error! Selected user doesn't exist."));
    }

    public boolean addUser(User user) {
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteUser(String email) {
        try {
            boolean exists = userRepository.existsById(email);
            if (exists) {
                userRepository.deleteById(email);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateUser(String email, String name, String password, String about, String photo_url) {
        boolean exists = userRepository.existsById(email);
        if (exists) {
            User user = userRepository.findById(email).orElseThrow(() -> new IllegalStateException("Error"));
            if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
                user.setName(name);
            }
            if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
                user.setPassword(password);
            }
            if (about != null && about.length() > 0 && !Objects.equals(user.getAbout(), about)) {
                user.setAbout(about);
            }
            System.out.println(photo_url);

            if (photo_url != null && photo_url.length() > 0 && !Objects.equals(user.getPhoto_url(), photo_url)) {
                user.setPhoto_url(photo_url);
                System.out.println("girdi");
            }
            return true;
        } else
            return false;

    }
}
