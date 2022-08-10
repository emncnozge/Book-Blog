package com.kitap.blog.services;

import com.kitap.blog.entities.Book;
import com.kitap.blog.repositories.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class FavoriteBookService {
    private final BookRepository bookRepository;

    public FavoriteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                new IllegalStateException("Error! Selected book doesn't exist."));
    }

    public boolean addBook(Book book) {
        try {
            bookRepository.saveAndFlush(book);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteBook(Long book_id) {
        try {
            boolean exists = bookRepository.existsById(book_id);
            if (exists) {
                bookRepository.deleteById(book_id);
                return true;
            } else return false;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateBook(Long book_id, Long author_id, String name, String about, String photo_url) {
        boolean exists = bookRepository.existsById(book_id);
        if (exists) {
            Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalStateException("Error"));
            if (name != null && name.length() > 0 && !Objects.equals(book.getName(), name)) {
                book.setName(name);
            }
            if (author_id != null && author_id > 0 && !Objects.equals(book.getAuthor_id(), author_id)) {
                book.setAuthor_id(author_id);
            }
            if (about != null && about.length() > 0 && !Objects.equals(book.getAbout(), about)) {
                book.setAbout(about);
            }
            System.out.println(photo_url);

            if (photo_url != null && photo_url.length() > 0 && !Objects.equals(book.getPhoto_url(), photo_url)) {
                book.setPhoto_url(photo_url);
                System.out.println("girdi");
            }
            return true;
        } else return false;


    }
}
