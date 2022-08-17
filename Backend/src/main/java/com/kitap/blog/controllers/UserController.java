package com.kitap.blog.controllers;

import com.kitap.blog.entities.User;
import com.kitap.blog.services.UserService;
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
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path = "api/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public Long login(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        return userService.login(String.valueOf(body.get("email")),
                String.valueOf(body.get("password")),
                String.valueOf(body.get("token")));
    }

    @PostMapping("/getUsers")
    public List<User> getUsers(@RequestBody Map<String, Object> body) {
        return userService.getUsers(String.valueOf(body.get("token")));
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody Map<String, Object> body) {
        return userService.getUser(Long.parseLong(String.valueOf(body.get("user_id"))),
                String.valueOf(body.get("token")));
    }

    @PostMapping("/addUser")
    public Long addUser(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        User user = new User();
        user.setEmail(String.valueOf(body.get("email")));
        user.setPassword(String.valueOf(body.get("password")));
        user.setName(String.valueOf(body.get("name")));
        user.setAbout(String.valueOf(body.get("about")));
        user.setPhoto_url(String.valueOf(body.get("photo_url")));
        user.setIs_admin(Boolean.parseBoolean(String.valueOf(body.get("is_admin"))));

        return userService.addUser(user, String.valueOf(body.get("token")));
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUser(@RequestBody Map<String, Object> body) {
        return userService.deleteUser(Long.valueOf(String.valueOf(body.get("user_id"))), String.valueOf(body.get("token")));
    }

    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody Map<String, Object> body) throws NoSuchAlgorithmException {
        return userService.updateUser(
                Long.valueOf(String.valueOf(body.get("user_id"))),
                String.valueOf(body.get("email")),
                String.valueOf(body.get("name")),
                String.valueOf(body.get("password")),
                String.valueOf(body.get("about")),
                String.valueOf(body.get("photo_url")),
                Boolean.valueOf(String.valueOf(body.get("isAdmin"))),
                String.valueOf(body.get("token"))
        );
    }


    @PostMapping("/photo")
    public void addUserPhoto(@RequestParam("user_id") Long user_id, @RequestParam("image") MultipartFile multipartFile,HttpServletResponse httpServletResponse) throws IOException {
        userService.addUserPhoto(user_id, multipartFile, httpServletResponse);
    }

    @GetMapping(path = "/photo/{user_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public InputStreamResource getBookPhoto(@PathVariable("user_id") Long user_id) throws IOException {
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
        return userService.getUserPhoto(user_id, response);
    }
}
