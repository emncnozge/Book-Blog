package com.kitap.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


class FavoriteUserKey implements Serializable {
    private String user_email;
    private String favorite_user_email;
}

@Entity(name = "favorite_user")
@IdClass(FavoriteUserKey.class)
public class FavoriteUser {
    @Id
    @Column(name = "user_email", nullable = false)
    private String user_email;

    @Id
    @Column(name = "favorite_user_email", nullable = false)
    private String favorite_user_email;

    public FavoriteUser() {
    }

    public FavoriteUser(String user_email, String favorite_user_email) {
        this.user_email = user_email;
        this.favorite_user_email = favorite_user_email;
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
                "user_email='" + user_email + '\'' +
                ", favorite_user_email='" + favorite_user_email + '\'' +
                '}';
    }
}
