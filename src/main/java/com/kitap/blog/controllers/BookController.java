package com.kitap.blog.controllers;

import com.kitap.blog.services.BookService;
import com.kitap.blog.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/book")

public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "{book_id}")
    public Book getBook(@PathVariable("book_id") Long book_id) {
        return bookService.getBook(book_id);
    }

    @PostMapping
    public boolean addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping(path = "{book_id}")
    public boolean deleteBook(@PathVariable("book_id") Long book_id) {
        return bookService.deleteBook(book_id);
    }

    @PutMapping(path = "{book_id}")
    public boolean updateBook(
            @PathVariable("book_id") Long book_id,
            @RequestParam(required = false) Long author_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String about,
            @RequestParam(required = false) String photo_url) {
        return bookService.updateBook(book_id, author_id, name, about, photo_url);
    }
}
