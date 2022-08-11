package com.kitap.blog.entities;

import javax.persistence.*;


@Entity(name = "favorite_author")
public class FavoriteAuthor {
    @Id
    @SequenceGenerator(name = "favorite_author_sequence", sequenceName = "favorite_author_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_author_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "favorite_author_id", nullable = false)
    private Long favorite_author_id;

    public FavoriteAuthor() {
    }

    public FavoriteAuthor(Long id, Long user_id, Long favorite_author_id) {
        this.id = id;
        this.user_id = user_id;
        this.favorite_author_id = favorite_author_id;
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

    public Long getFavorite_author_id() {
        return favorite_author_id;
    }

    public void setFavorite_author_id(Long favorite_author_id) {
        this.favorite_author_id = favorite_author_id;
    }

    @Override
    public String toString() {
        return "FavoriteUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", favorite_author_id=" + favorite_author_id +
                '}';
    }
}
