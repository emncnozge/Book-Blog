package com.kitap.blog.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "author")
public class Author {
    @Id
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    @Column(name = "author_id", updatable = false)
    private Long author_id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about", nullable = false, columnDefinition = "TEXT")
    private String about;

    @Column(name = "photo_url")
    private String photo_url;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


    public Author() {
    }

    public Author(Long author_id, String name, String about, String photo_url, Date createdOn, Date updatedOn) {
        this.author_id = author_id;
        this.name = name;
        this.about = about;
        this.photo_url = photo_url;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
