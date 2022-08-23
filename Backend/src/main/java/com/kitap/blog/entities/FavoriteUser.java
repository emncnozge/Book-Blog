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
    private Long userid;

    @Column(name = "favorite_user_id", nullable = false)
    private Long favoriteuserid;

    public FavoriteUser() {
    }

    public FavoriteUser(Long id, Long userid, Long favoriteuserid) {
        this.id = id;
        this.userid = userid;
        this.favoriteuserid = favoriteuserid;
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

    public Long getFavoriteuserid() {
        return this.favoriteuserid;
    }

    public void setFavoriteuserid(Long favoriteuserid) {
        this.favoriteuserid = favoriteuserid;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userid='" + getUserid() + "'" +
                ", favoriteuserid='" + getFavoriteuserid() + "'" +
                "}";
    }

}
