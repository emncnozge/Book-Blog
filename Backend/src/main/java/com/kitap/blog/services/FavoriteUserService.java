package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.repositories.FavoriteUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteUserService {
    private final FavoriteUserRepository favoriteUserRepository;

    public FavoriteUserService(FavoriteUserRepository favoriteUserRepository) {
        this.favoriteUserRepository = favoriteUserRepository;
    }

    public List<FavoriteUser> getFavoriteUsers(Long user_id) {
        return favoriteUserRepository.getFavoriteUsers(user_id);
    }

    public boolean getFavoriteUser(Long user_id, Long favorite_user_id) {
        return (favoriteUserRepository.getFavoriteUser(user_id, favorite_user_id).size() > 0);
    }

    public boolean addFavoriteUser(FavoriteUser favoriteUser) {
        try {
            favoriteUserRepository.saveAndFlush(favoriteUser);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteFavoriteUser(Long id) {
        try {
            boolean exists = favoriteUserRepository.existsById(id);
            if (exists) {
                favoriteUserRepository.deleteById(id);
                return true;
            } else return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
