package com.kitap.blog.entities;

import javax.persistence.*;


@Entity(name = "favorite_user")
public class FavoriteUser {
    @Id
    @SequenceGenerator(name = "favorite_user_sequence", sequenceName = "favorite_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String user_email;

    @Column(name = "favorite_user_email", nullable = false)
    private String favorite_user_email;

    public FavoriteUser() {
    }

    public FavoriteUser(Long id, String user_email, String favorite_user_email) {
        this.id = id;
        this.user_email = user_email;
        this.favorite_user_email = favorite_user_email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getFavorite_user_email() {
        return favorite_user_email;
    }

    public void setFavorite_user_email(String favorite_user_email) {
        this.favorite_user_email = favorite_user_email;
    }

    @Override
    public String toString() {
        return "FavoriteUser{" +
                "id='" + id + '\'' +
                ", user_email='" + user_email + '\'' +
                ", favorite_user_email='" + favorite_user_email + '\'' +
                '}';
    }
}
