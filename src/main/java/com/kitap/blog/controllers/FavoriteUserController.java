package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.services.FavoriteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
