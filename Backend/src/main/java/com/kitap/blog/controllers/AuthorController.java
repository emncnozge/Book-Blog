package com.kitap.blog.controllers;

import com.kitap.blog.entities.Author;
import com.kitap.blog.services.AuthorService;
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

    @PostMapping(path = "getAuthor")
    public Author getAuthor(@RequestBody Map<String, Object> body) {
        return authorService.getAuthor(Long.parseLong(String.valueOf(body.get("author_id"))));
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

    @PostMapping("/photo")
    public void addAuthorPhoto(@RequestParam("author_id") Long author_id, @RequestParam("image") MultipartFile multipartFile, HttpServletResponse httpServletResponse) throws IOException {
        authorService.addAuthorPhoto(author_id, multipartFile, httpServletResponse);
    }

    @GetMapping(path = "/photo/{author_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public InputStreamResource getBookPhoto(@PathVariable("author_id") Long author_id) throws IOException {
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
        return authorService.getAuthorPhoto(author_id, response);
    }
}
