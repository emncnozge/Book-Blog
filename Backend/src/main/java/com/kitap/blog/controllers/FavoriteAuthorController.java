package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteAuthor;
import com.kitap.blog.services.FavoriteAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/favoriteauthor")

public class FavoriteAuthorController {

    private final FavoriteAuthorService favoriteAuthorService;

    @Autowired
    public FavoriteAuthorController(FavoriteAuthorService favoriteAuthorService) {
        this.favoriteAuthorService = favoriteAuthorService;
    }

    @GetMapping
    public List<FavoriteAuthor> getFavoriteAuthors(@RequestParam(required = false) Long user_id) {
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

    @DeleteMapping(path = "{id}")
    public boolean deleteFavoriteAuthor(@PathVariable("id") Long id) {
        return favoriteAuthorService.deleteFavoriteAuthor(id);
    }

}
