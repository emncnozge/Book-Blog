package com.kitap.blog.controllers;

import com.kitap.blog.services.AuthorService;
import com.kitap.blog.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/author")

public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping(path = "{author_id}")
    public Author getAuthor(@PathVariable("author_id") Long author_id) {
        return authorService.getAuthor(author_id);
    }

    @PostMapping
    public boolean addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @DeleteMapping(path = "{author_id}")
    public boolean deleteAuthor(@PathVariable("author_id") Long author_id) {
        return authorService.deleteAuthor(author_id);
    }

    @PutMapping(path = "{author_id}")
    public boolean updateAuthor(
            @PathVariable("author_id") Long author_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String about,
            @RequestParam(required = false) String photo_url) {
        return authorService.updateAuthor(author_id, name, about, photo_url);
    }
}
