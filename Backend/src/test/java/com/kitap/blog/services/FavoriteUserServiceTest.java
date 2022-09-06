package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteUser;
import com.kitap.blog.repositories.FavoriteUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class FavoriteUserServiceTest {
    private FavoriteUserService favoriteUserService;
    private FavoriteUserRepository favoriteUserRepository;

    @BeforeEach
    void setUp() {
        favoriteUserRepository = Mockito.mock(FavoriteUserRepository.class);
        favoriteUserService = new FavoriteUserService(favoriteUserRepository);
    }

    @Test
    public void addFavoriteUserTest() {
        FavoriteUser favoriteUser = new FavoriteUser(1L, 1L, 1L);
        Mockito.when(favoriteUserRepository.save(favoriteUser)).thenReturn(favoriteUser);
        Assertions.assertTrue(favoriteUserService.addFavoriteUser(favoriteUser));
    }

    @Test
    public void getFavoriteUsersTest() {
        List<Object> favoriteUser = new ArrayList<>();
        favoriteUser.add(1L);
        Mockito.when(favoriteUserRepository.getFavoriteUsersByUserid(1L)).thenReturn(favoriteUser);
        Assertions.assertEquals(favoriteUserRepository.getFavoriteUsersByUserid(1L),
                favoriteUserService.getFavoriteUsers(1L));
    }

    @Test
    public void getFavoriteUserTest() {
        Mockito.when(favoriteUserRepository.existsFavoriteUserByUseridAndFavoriteuserid(1L, 1L)).thenReturn(true);
        Assertions.assertTrue(favoriteUserService.getFavoriteUser(1L, 1L));
    }

    @Test
    public void deleteFavoriteUserTest() {
        FavoriteUser favoriteUser = new FavoriteUser(1L, 1L, 1L);
        favoriteUserService.deleteFavoriteUser(favoriteUser.getUserid(), favoriteUser.getFavoriteuserid());
        Mockito.verify(favoriteUserRepository).deleteFavoriteUserByUseridAndFavoriteuserid(
                favoriteUser.getUserid(), favoriteUser.getFavoriteuserid());
    }

}