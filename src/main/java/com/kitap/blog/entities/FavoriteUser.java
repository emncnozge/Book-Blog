package com.kitap.blog.entities;

import javax.persistence.*;


@Entity(name = "favorite_user")
public class FavoriteUser {
    @Id
    @SequenceGenerator(name = "favorite_user_sequence", sequenceName = "favorite_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "favorite_user_id", nullable = false)
    private Long favorite_user_id;

    public FavoriteUser() {
    }

    public FavoriteUser(Long id, Long user_id, Long favorite_user_id) {
        this.id = id;
        this.user_id = user_id;
        this.favorite_user_id = favorite_user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getFavorite_user_id() {
        return favorite_user_id;
    }

    public void setFavorite_user_id(Long favorite_user_id) {
        this.favorite_user_id = favorite_user_id;
    }

    @Override
    public String toString() {
        return "FavoriteUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", favorite_user_id=" + favorite_user_id +
                '}';
    }
}
