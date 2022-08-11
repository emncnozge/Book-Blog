package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteBook;
import com.kitap.blog.repositories.FavoriteBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteBookService {
    private final FavoriteBookRepository favoriteBookRepository;

    public FavoriteBookService(FavoriteBookRepository favoriteBookRepository) {
        this.favoriteBookRepository = favoriteBookRepository;
    }

    public List<FavoriteBook> getFavoriteBooks(Long user_id) {
        return favoriteBookRepository.getFavoriteBooks(user_id);
    }

    public boolean getFavoriteBook(Long user_id, Long favorite_book_id) {
        return (favoriteBookRepository.getFavoriteBook(user_id, favorite_book_id).size() > 0);
    }

    public boolean addFavoriteBook(FavoriteBook favoriteBook) {
        try {
            favoriteBookRepository.saveAndFlush(favoriteBook);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteFavoriteBook(Long id) {
        try {
            boolean exists = favoriteBookRepository.existsById(id);
            if (exists) {
                favoriteBookRepository.deleteById(id);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
