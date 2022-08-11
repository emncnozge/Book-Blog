package com.kitap.blog.controllers;

import com.kitap.blog.entities.User;
import com.kitap.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{user_id}")
    public User getUser(@PathVariable("user_id") Long user_id) {
        return userService.getUser(user_id);
    }

    @PostMapping
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(path = "{user_id}")
    public boolean deleteUser(@PathVariable("user_id") Long user_id) {
        return userService.deleteUser(user_id);
    }

    @PutMapping(path = "{user_id}")
    public boolean updateUser(
            @PathVariable("user_id") Long user_id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String about,
            @RequestParam(required = false) String photo_url) {
        return userService.updateUser(user_id, email, name, password, about, photo_url);
    }
}
