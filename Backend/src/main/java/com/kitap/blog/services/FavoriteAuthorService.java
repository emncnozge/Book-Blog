package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteAuthor;
import com.kitap.blog.repositories.FavoriteAuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteAuthorService {
    private final FavoriteAuthorRepository favoriteAuthorRepository;

    public FavoriteAuthorService(FavoriteAuthorRepository favoriteAuthorRepository) {
        this.favoriteAuthorRepository = favoriteAuthorRepository;
    }

    public List<Object> getFavoriteAuthors(Long user_id) {
        return favoriteAuthorRepository.getFavoriteAuthorsByUserid(user_id);
    }

    public boolean getFavoriteAuthor(Long user_id, Long favorite_author_id) {
        return (favoriteAuthorRepository.existsFavoriteAuthorByUseridAndFavoriteauthorid(user_id, favorite_author_id));
    }

    public boolean addFavoriteAuthor(FavoriteAuthor favoriteAuthor) {
        try {

            if (!favoriteAuthorRepository.existsFavoriteAuthorByUseridAndFavoriteauthorid(favoriteAuthor.getUserid(),
                    favoriteAuthor.getFavoriteauthorid())) {
                favoriteAuthorRepository.saveAndFlush(favoriteAuthor);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    @Transactional
    public boolean deleteFavoriteAuthor(Long user_id, Long favorite_author_id) {
        try {
            boolean exists = favoriteAuthorRepository.existsFavoriteAuthorByUseridAndFavoriteauthorid(user_id,
                    favorite_author_id);
            if (exists) {
                favoriteAuthorRepository.deleteFavoriteAuthorByUseridAndFavoriteauthorid(user_id, favorite_author_id);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
