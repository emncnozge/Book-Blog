package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteBook;
import com.kitap.blog.services.FavoriteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/favoritebook")

public class FavoriteBookController {

    private final FavoriteBookService favoriteBookService;

    @Autowired
    public FavoriteBookController(FavoriteBookService favoriteBookService) {
        this.favoriteBookService = favoriteBookService;
    }

    @GetMapping
    public List<Object> getFavoriteBooks(@RequestParam(required = false) Long user_id) {
        return favoriteBookService.getFavoriteBooks(user_id);
    }

    @GetMapping(path = "isfavoritebook")
    public boolean getFavoriteBook(@RequestParam(required = false) Long user_id,
            @RequestParam(required = false) Long favorite_book_id) {
        return favoriteBookService.getFavoriteBook(user_id, favorite_book_id);
    }

    @PostMapping
    public boolean addFavoriteBook(@RequestBody FavoriteBook favoriteBook) {
        return favoriteBookService.addFavoriteBook(favoriteBook);
    }

    @PostMapping(path = "delete")
    public boolean deleteFavoriteBook(@RequestBody Map<String, Object> data) {

        return favoriteBookService.deleteFavoriteBook(Long.valueOf(data.get("userid").toString()),
                Long.valueOf(data.get("favoritebookid").toString()));
    }

}
