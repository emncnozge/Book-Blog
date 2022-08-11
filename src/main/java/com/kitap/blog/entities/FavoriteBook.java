package com.kitap.blog.entities;

import javax.persistence.*;

@Entity(name = "favorite_book")
public class FavoriteBook {
    @Id
    @SequenceGenerator(name = "favorite_book_sequence", sequenceName = "favorite_book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_book_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "favorite_book_id", nullable = false)
    private Long favorite_book_id;

    public FavoriteBook() {
    }

    public FavoriteBook(Long id, Long user_id, Long favorite_book_id) {
        this.id = id;
        this.user_id = user_id;
        this.favorite_book_id = favorite_book_id;
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

    public Long getFavorite_book_id() {
        return favorite_book_id;
    }

    public void setFavorite_book_id(Long favorite_book_id) {
        this.favorite_book_id = favorite_book_id;
    }

    @Override
    public String toString() {
        return "FavoriteUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", favorite_book_id=" + favorite_book_id +
                '}';
    }
}
