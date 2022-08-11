package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.repositories.FavoriteUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteUserService {
    private final FavoriteUserRepository favoriteUserRepository;

    public FavoriteUserService(FavoriteUserRepository favoriteUserRepository) {
        this.favoriteUserRepository = favoriteUserRepository;
    }

    public List<FavoriteUser> getFavoriteUsers(String user_email) {
        List<FavoriteUser> favorites = favoriteUserRepository.findAll();
        List<FavoriteUser> myList = new ArrayList<>();
        for (int i = 0; i < favorites.size(); i++) {
            if (favorites.get(i).getUser_email().equals(user_email))
                myList.add(favorites.get(i));
        }
        return myList;
    }

}
