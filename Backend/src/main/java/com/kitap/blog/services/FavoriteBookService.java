package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteBook;
import com.kitap.blog.repositories.FavoriteBookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteBookService {
    private final FavoriteBookRepository favoriteBookRepository;

    public FavoriteBookService(FavoriteBookRepository favoriteBookRepository) {
        this.favoriteBookRepository = favoriteBookRepository;
    }

    public List<Object> getFavoriteBooks(Long user_id) {
        return favoriteBookRepository.getFavoriteBooksByUserid(user_id);
    }

    public boolean getFavoriteBook(Long user_id, Long favorite_book_id) {
        return (favoriteBookRepository.existsFavoriteBookByUseridAndFavoritebookid(user_id, favorite_book_id));
    }

    public boolean addFavoriteBook(FavoriteBook favoriteBook) {
        try {

            if (!favoriteBookRepository.existsFavoriteBookByUseridAndFavoritebookid(favoriteBook.getUserid(), favoriteBook.getFavoritebookid())) {
                favoriteBookRepository.saveAndFlush(favoriteBook);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    @Transactional
    public boolean deleteFavoriteBook(Long user_id, Long favorite_book_id) {
        try {
            boolean exists = favoriteBookRepository.existsFavoriteBookByUseridAndFavoritebookid(user_id, favorite_book_id);
            if (exists) {
                favoriteBookRepository.deleteFavoriteBookByUseridAndFavoritebookid(user_id, favorite_book_id);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
