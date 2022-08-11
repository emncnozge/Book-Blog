package com.kitap.blog.entities;

import javax.persistence.*;

@Entity(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @Column(name = "book_id", updatable = false)
    private Long book_id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author_id", nullable = false)
    private Long author_id;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "photo_url", nullable = true)
    private String photo_url;

    public Book(Long book_id, String name, Long author_id, String about, String photo_url) {
        this.book_id = book_id;
        this.name = name;
        this.author_id = author_id;
        this.about = about;
        this.photo_url = photo_url;
    }

    public Book() {
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
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
        return "Book{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                ", author_id=" + author_id +
                ", about='" + about + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }
}