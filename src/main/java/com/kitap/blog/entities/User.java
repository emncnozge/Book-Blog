package com.kitap.blog.entities;

import javax.persistence.*;

@Entity(name = "user")
public class User {
    @Id
    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password", updatable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "photo_url")
    private String photo_url;

    @Column(name = "is_admin", nullable = false)
    private boolean is_admin;
    
    public User(String email, String password, String name, String about, String photo_url, boolean is_admin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.about = about;
        this.photo_url = photo_url;
        this.is_admin = is_admin;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", is_admin='" + is_admin + '\'' +
                '}';
    }
}