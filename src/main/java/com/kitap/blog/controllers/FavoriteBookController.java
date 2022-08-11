package com.kitap.blog.controllers;

import com.kitap.blog.entities.FavoriteBook;
import com.kitap.blog.services.FavoriteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/favoritebook")

public class FavoriteBookController {

    private final FavoriteBookService favoriteBookService;

    @Autowired
    public FavoriteBookController(FavoriteBookService favoriteBookService) {
        this.favoriteBookService = favoriteBookService;
    }

    @GetMapping
    public List<FavoriteBook> getFavoriteBooks(@RequestParam(required = false) Long user_id) {
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

    @DeleteMapping(path = "{id}")
    public boolean deleteFavoriteBook(@PathVariable("id") Long id) {
        return favoriteBookService.deleteFavoriteBook(id);
    }

}
