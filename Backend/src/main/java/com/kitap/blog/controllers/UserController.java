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
        return userService.login(body.get("email").toString(),
                body.get("password").toString(),
                body.get("token").toString());
    }

    @PostMapping("/getUsers")
    public List<User> getUsers(@RequestBody Map<String, Object> body) {
        return userService.getUsers(body.get("token").toString());
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody Map<String, Object> body) {
        return userService.getUser(Long.parseLong(body.get("user_id").toString()),
                body.get("token").toString());
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        User user = new User();
        user.setEmail(body.get("email").toString());
        user.setPassword(body.get("password").toString());
        user.setName(body.get("name").toString());
        user.setAbout(body.get("about").toString());
        user.setPhoto_url(body.get("photo_url").toString());
        user.setIs_admin(Boolean.parseBoolean(body.get("is_admin").toString()));

        return userService.addUser(user, body.get("token").toString());
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUser(@RequestBody Map<String, Object> body) {
        return userService.deleteUser(Long.parseLong(body.get("user_id").toString()), body.get("token").toString());
    }

    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody Map<String, Object> body) {
        return userService.updateUser(
                Long.parseLong(body.get("user_id").toString()),
                body.get("email").toString(),
                body.get("name").toString(),
                body.get("password").toString(),
                body.get("about").toString(),
                body.get("photo_url").toString(),
                Boolean.parseBoolean(body.get("isAdmin").toString()),
                body.get("token").toString()
        );
    }
}
