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
    private Long userid;

    @Column(name = "favorite_author_id", nullable = false)
    private Long favoriteauthorid;

    public FavoriteAuthor() {
    }

    public FavoriteAuthor(Long id, Long userid, Long favoriteauthorid) {
        this.id = id;
        this.userid = userid;
        this.favoriteauthorid = favoriteauthorid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFavoriteauthorid() {
        return this.favoriteauthorid;
    }

    public void setFavoriteauthorid(Long favoriteauthorid) {
        this.favoriteauthorid = favoriteauthorid;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userid='" + getUserid() + "'" +
                ", favoriteauthorid='" + getFavoriteauthorid() + "'" +
                "}";
    }

}
