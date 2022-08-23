package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteAuthor;
import com.kitap.blog.services.FavoriteAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/favoriteauthor")

public class FavoriteAuthorController {

    private final FavoriteAuthorService favoriteAuthorService;

    @Autowired
    public FavoriteAuthorController(FavoriteAuthorService favoriteAuthorService) {
        this.favoriteAuthorService = favoriteAuthorService;
    }

    @GetMapping
    public List<Object> getFavoriteAuthors(@RequestParam(required = false) Long user_id) {
        return favoriteAuthorService.getFavoriteAuthors(user_id);
    }

    @GetMapping(path = "isfavoriteauthor")
    public boolean getFavoriteAuthor(@RequestParam(required = false) Long user_id,
                                     @RequestParam(required = false) Long favorite_author_id) {
        return favoriteAuthorService.getFavoriteAuthor(user_id, favorite_author_id);
    }

    @PostMapping
    public boolean addFavoriteAuthor(@RequestBody FavoriteAuthor favoriteAuthor) {
        return favoriteAuthorService.addFavoriteAuthor(favoriteAuthor);
    }

    @PostMapping(path = "delete")
    public boolean deleteFavoriteAuthor(@RequestBody Map<String, Object> data) {

        return favoriteAuthorService.deleteFavoriteAuthor(Long.valueOf(data.get("userid").toString()),
                Long.valueOf(data.get("favoriteauthorid").toString()));
    }

}
