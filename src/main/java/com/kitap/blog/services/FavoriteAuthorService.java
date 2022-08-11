package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteAuthor;
import com.kitap.blog.repositories.FavoriteAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteAuthorService {
    private final FavoriteAuthorRepository favoriteAuthorRepository;

    public FavoriteAuthorService(FavoriteAuthorRepository favoriteAuthorRepository) {
        this.favoriteAuthorRepository = favoriteAuthorRepository;
    }

    public List<FavoriteAuthor> getFavoriteAuthors(Long user_id) {
        return favoriteAuthorRepository.getFavoriteAuthors(user_id);
    }

    public boolean getFavoriteAuthor(Long user_id, Long favorite_author_id) {
        return (favoriteAuthorRepository.getFavoriteAuthor(user_id, favorite_author_id).size() > 0);
    }

    public boolean addFavoriteAuthor(FavoriteAuthor favoriteAuthor) {
        try {
            favoriteAuthorRepository.saveAndFlush(favoriteAuthor);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteFavoriteAuthor(Long id) {
        try {
            boolean exists = favoriteAuthorRepository.existsById(id);
            if (exists) {
                favoriteAuthorRepository.deleteById(id);
                return true;
            } else return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
