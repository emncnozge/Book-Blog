package com.kitap.blog.book;

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

    @PostMapping
    public boolean addNewBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping(path = "{id}")
    public boolean updateBook(
            @PathVariable("id") Long id,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String name) {
        return bookService.updateBook(id, authorId, name);
    }
}
