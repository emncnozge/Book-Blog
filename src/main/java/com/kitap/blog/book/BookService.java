package com.kitap.blog.book;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public boolean addNewBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteBook(Long id) {
        try {
            boolean exists = bookRepository.existsById(id);
            if (exists) {
                bookRepository.deleteById(id);
                return true;
            } else return false;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateBook(Long id, Long authorId, String name) {
        boolean exists = bookRepository.existsById(id);
        if(exists) {
            Book book = bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("Error"));
            if(name != null && name.length()>0 && !Objects.equals(book.getName(),name)){
                book.setName(name);
            }
            if(authorId != null && authorId>0 && !Objects.equals(book.getAuthorId(),authorId)){
                book.setAuthorId(authorId);
            }
            return true;
        }
        else return false;


    }
}
