package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.services.FavoriteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/favoriteuser")

public class FavoriteUserController {

    private final FavoriteUserService favoriteUserService;

    @Autowired
    public FavoriteUserController(FavoriteUserService favoriteUserService) {
        this.favoriteUserService = favoriteUserService;
    }

    @GetMapping
    public List<Object> getFavoriteUsers(@RequestParam(required = false) Long user_id) {
        return favoriteUserService.getFavoriteUsers(user_id);
    }

    @GetMapping(path = "isfavoriteuser")
    public boolean getFavoriteUser(@RequestParam(required = false) Long user_id,
            @RequestParam(required = false) Long favorite_user_id) {
        return favoriteUserService.getFavoriteUser(user_id, favorite_user_id);
    }

    @PostMapping
    public boolean addFavoriteUser(@RequestBody FavoriteUser favoriteUser) {
        return favoriteUserService.addFavoriteUser(favoriteUser);
    }

    @PostMapping(path = "delete")
    public boolean deleteFavoriteUser(@RequestBody Map<String, Object> data) {

        return favoriteUserService.deleteFavoriteUser(Long.valueOf(data.get("userid").toString()),
                Long.valueOf(data.get("favoriteuserid").toString()));
    }

}
