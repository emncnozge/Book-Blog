package com.kitap.blog.controllers;

import com.kitap.blog.entities.Book;
import com.kitap.blog.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    @GetMapping(path = "last20")
    public List<Book> getLast20Books() {
        return bookService.getLast20Books();
    }

    @PostMapping(path = "getBook")
    public Book getBook(@RequestBody Map<String, Object> body) {
        return bookService.getBook(Long.parseLong(String.valueOf(body.get("book_id"))));
    }

    @GetMapping(path = "getGenres")
    public Object getGenres() {
        return bookService.getGenres();
    }

    @PostMapping(path = "getBooksByGenre")
    public Object getBooksByGenre(@RequestBody Map<String, Object> body) {
        if (body.get("genre") == null) {
            return null;
        }
        return bookService.getBooksByGenre(body.get("genre").toString());
    }

    @PostMapping
    public Long addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }


    @PostMapping("/photo")
    public void addUserPhoto(@RequestParam("book_id") Long book_id, @RequestParam("image") MultipartFile multipartFile, HttpServletResponse httpServletResponse) throws IOException {
        bookService.addBookPhoto(book_id, multipartFile, httpServletResponse);
    }

    @GetMapping(path = "/photo/{book_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public InputStreamResource getBookPhoto(@PathVariable("book_id") Long book_id) throws IOException {
        HttpServletResponse response = new HttpServletResponse() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String s) {
                return false;
            }

            @Override
            public String encodeURL(String s) {
                return null;
            }

            @Override
            public String encodeRedirectURL(String s) {
                return null;
            }

            @Override
            public String encodeUrl(String s) {
                return null;
            }

            @Override
            public String encodeRedirectUrl(String s) {
                return null;
            }

            @Override
            public void sendError(int i, String s) {

            }

            @Override
            public void sendError(int i) {

            }

            @Override
            public void sendRedirect(String s) {

            }

            @Override
            public void setDateHeader(String s, long l) {

            }

            @Override
            public void addDateHeader(String s, long l) {

            }

            @Override
            public void setHeader(String s, String s1) {

            }

            @Override
            public void addHeader(String s, String s1) {

            }

            @Override
            public void setIntHeader(String s, int i) {

            }

            @Override
            public void addIntHeader(String s, int i) {

            }

            @Override
            public void setStatus(int i, String s) {

            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public void setStatus(int i) {

            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) {

            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public void setContentType(String s) {

            }

            @Override
            public ServletOutputStream getOutputStream() {
                return null;
            }

            @Override
            public PrintWriter getWriter() {
                return null;
            }

            @Override
            public void setContentLength(int i) {

            }

            @Override
            public void setContentLengthLong(long l) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void setBufferSize(int i) {

            }

            @Override
            public void flushBuffer() {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public void setLocale(Locale locale) {

            }
        };
        return bookService.getBookPhoto(book_id, response);
    }

    @DeleteMapping(path = "{book_id}")
    public boolean deleteBook(@PathVariable("book_id") Long book_id) {
        return bookService.deleteBook(book_id);
    }

    @PutMapping(path = "/updateBook")
    public boolean updateBook(@RequestBody Map<String, Object> body) {
        Long author_id = null;
        if (!(String.valueOf(body.get("author_id")).equals("null")))
            author_id = Long.valueOf(String.valueOf(body.get("author_id")));
        return bookService.updateBook(Long.valueOf(String.valueOf(body.get("book_id"))),
                author_id,
                String.valueOf(body.get("name")),
                String.valueOf(body.get("genre")),
                String.valueOf(body.get("about")),
                String.valueOf(body.get("photo_url")));
    }
}
