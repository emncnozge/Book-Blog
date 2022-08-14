package com.kitap.blog.controllers;

import com.kitap.blog.entities.User;
import com.kitap.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        return userService.login(String.valueOf(body.get("email")),
                String.valueOf(body.get("password")),
                String.valueOf(body.get("token")));
    }

    @PostMapping("/getUsers")
    public List<User> getUsers(@RequestBody Map<String, Object> body) {
        return userService.getUsers(String.valueOf(body.get("token")));
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody Map<String, Object> body) {
        return userService.getUser(Long.parseLong(String.valueOf(body.get("user_id"))),
                String.valueOf(body.get("token")));
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        User user = new User();
        user.setEmail(String.valueOf(body.get("email")));
        user.setPassword(String.valueOf(body.get("password")));
        user.setName(String.valueOf(body.get("name")));
        user.setAbout(String.valueOf(body.get("about")));
        user.setPhoto_url(String.valueOf(body.get("photo_url")));
        user.setIs_admin(Boolean.parseBoolean(String.valueOf(body.get("is_admin"))));

        return userService.addUser(user, String.valueOf(body.get("token")));
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUser(@RequestBody Map<String, Object> body) {
        return userService.deleteUser(Long.valueOf(String.valueOf(body.get("user_id"))), String.valueOf(body.get("token")));
    }

    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody Map<String, Object> body) {
        return userService.updateUser(
                Long.valueOf(String.valueOf(body.get("user_id"))),
                String.valueOf(body.get("email")),
                String.valueOf(body.get("name")),
                String.valueOf(body.get("password")),
                String.valueOf(body.get("about")),
                String.valueOf(body.get("photo_url")),
                Boolean.valueOf(String.valueOf(body.get("isAdmin"))),
                String.valueOf(body.get("token"))
        );
    }
}
