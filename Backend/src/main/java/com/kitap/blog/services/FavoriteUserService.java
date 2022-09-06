package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.repositories.FavoriteUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteUserService {
    private final FavoriteUserRepository favoriteUserRepository;

    public FavoriteUserService(FavoriteUserRepository favoriteUserRepository) {
        this.favoriteUserRepository = favoriteUserRepository;
    }

    public List<Object> getFavoriteUsers(Long user_id) {
        return favoriteUserRepository.getFavoriteUsersByUserid(user_id);
    }

    public boolean getFavoriteUser(Long user_id, Long favorite_user_id) {
        return (favoriteUserRepository.existsFavoriteUserByUseridAndFavoriteuserid(user_id, favorite_user_id));
    }

    public boolean addFavoriteUser(FavoriteUser favoriteUser) {
        try {

            if (!favoriteUserRepository.existsFavoriteUserByUseridAndFavoriteuserid(favoriteUser.getUserid(),
                    favoriteUser.getFavoriteuserid())) {
                favoriteUserRepository.saveAndFlush(favoriteUser);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    @Transactional
    public boolean deleteFavoriteUser(Long user_id, Long favorite_user_id) {
        try {
            favoriteUserRepository.deleteFavoriteUserByUseridAndFavoriteuserid(user_id, favorite_user_id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
