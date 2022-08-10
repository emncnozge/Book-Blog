package com.kitap.blog.controllers;

import com.kitap.blog.entities.User;
import com.kitap.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")

public class FavoriteUserController {

    private final UserService userService;

    @Autowired
    public FavoriteUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{email}")
    public User getUser(@PathVariable("email") String email) {
        return userService.getUser(email);
    }

    @PostMapping
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(path = "{email}")
    public boolean deleteUser(@PathVariable("email") String email) {
        return userService.deleteUser(email);
    }

    @PutMapping(path = "{user_id}")
    public boolean updateUser(
            @PathVariable("user_id") String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String about,
            @RequestParam(required = false) String photo_url) {
        return userService.updateUser(email, name, password, about, photo_url);
    }
}
