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

    public User getUser(Long user_id) {
        return userRepository.findById(user_id)
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

    public boolean deleteUser(Long user_id) {
        try {
            boolean exists = userRepository.existsById(user_id);
            if (exists) {
                userRepository.deleteById(user_id);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateUser(Long user_id, String email, String name, String password, String about, String photo_url) {
        boolean exists = userRepository.existsById(user_id);
        if (exists) {
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
            return true;
        } else
            return false;

    }
}
