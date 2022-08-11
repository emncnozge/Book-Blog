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

    public List<FavoriteUser> getFavoriteUsers(String user_email) {
        return favoriteUserRepository.getFavoriteUsers(user_email);
    }

}
