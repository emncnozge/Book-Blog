package com.kitap.blog.services;

import com.kitap.blog.entities.Author;
import com.kitap.blog.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class FavoriteAuthorService {
    private final AuthorRepository authorRepository;

    public FavoriteAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Error! Selected author doesn't exist."));
    }

    public boolean addAuthor(Author author) {
        try {
            authorRepository.saveAndFlush(author);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteAuthor(Long author_id) {
        try {
            boolean exists = authorRepository.existsById(author_id);
            if (exists) {
                authorRepository.deleteById(author_id);
                return true;
            } else return false;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateAuthor(Long author_id, String name, String about, String photo_url) {
        boolean exists = authorRepository.existsById(author_id);
        if (exists) {
            Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalStateException("Error"));
            if (name != null && name.length() > 0 && !Objects.equals(author.getName(), name)) {
                author.setName(name);
            }
            if (about != null && about.length() > 0 && !Objects.equals(author.getAbout(), about)) {
                author.setAbout(about);
            }
            if (photo_url != null && photo_url.length() > 0 && !Objects.equals(author.getPhoto_url(), photo_url)) {
                author.setPhoto_url(photo_url);
            }
            return true;
        } else return false;


    }
}
