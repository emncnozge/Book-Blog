package com.kitap.blog.entities;

import javax.persistence.*;

@Entity(name = "favorite_book")
public class FavoriteBook {
    @Id
    @SequenceGenerator(name = "favorite_book_sequence", sequenceName = "favorite_book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_book_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userid;

    @Column(name = "favorite_book_id")
    private Long favoritebookid;

    public FavoriteBook() {
    }

    public FavoriteBook(Long id, Long userid, Long favoritebookid) {
        this.id = id;
        this.userid = userid;
        this.favoritebookid = favoritebookid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }


    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFavoritebookid() {
        return favoritebookid;
    }

    public void setFavoritebookid(Long favoritebookid) {
        this.favoritebookid = favoritebookid;
    }

    @Override
    public String toString() {
        return "FavoriteBook{" +
                "id=" + id +
                ", userid=" + userid +
                ", favoritebookid=" + favoritebookid +
                '}';
    }

}
