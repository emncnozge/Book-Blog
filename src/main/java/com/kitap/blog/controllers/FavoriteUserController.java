package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.services.FavoriteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/favoriteuser")

public class FavoriteUserController {

    private final FavoriteUserService favoriteUserService;

    @Autowired
    public FavoriteUserController(FavoriteUserService favoriteUserService) {
        this.favoriteUserService = favoriteUserService;
    }

    @GetMapping
    public List<FavoriteUser> getFavoriteUsers(@RequestParam(required = false) String user_email) {
        return favoriteUserService.getFavoriteUsers(user_email);
    }

    @GetMapping(path = "isfavoriteuser")
    public boolean getFavoriteUser(@RequestParam(required = false) String user_email, @RequestParam(required = false) String favorite_user_email) {
        return favoriteUserService.getFavoriteUser(user_email, favorite_user_email);
    }

    @PostMapping
    public boolean addFavoriteUser(@RequestBody FavoriteUser favoriteUser) {
        return favoriteUserService.addFavoriteUser(favoriteUser);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteFavoriteUser(@PathVariable("id") Long id) {
        return favoriteUserService.deleteFavoriteUser(id);
    }

}
