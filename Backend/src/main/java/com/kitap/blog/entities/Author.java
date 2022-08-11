package com.kitap.blog.entities;

import javax.persistence.*;

@Entity(name = "author")
public class Author {
    @Id
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    @Column(name = "author_id", updatable = false)
    private Long author_id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "photo_url")
    private String photo_url;

    public Author(Long author_id, String name, String about, String photo_url) {
        this.author_id = author_id;
        this.name = name;
        this.about = about;
        this.photo_url = photo_url;
    }

    public Author() {
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

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }

}
