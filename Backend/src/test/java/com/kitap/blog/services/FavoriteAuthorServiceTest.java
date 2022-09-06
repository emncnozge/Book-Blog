package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteAuthor;
import com.kitap.blog.repositories.FavoriteAuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class FavoriteAuthorServiceTest {
    private FavoriteAuthorService favoriteAuthorService;
    private FavoriteAuthorRepository favoriteAuthorRepository;

    @BeforeEach
    void setUp() {
        favoriteAuthorRepository = Mockito.mock(FavoriteAuthorRepository.class);
        favoriteAuthorService = new FavoriteAuthorService(favoriteAuthorRepository);
    }

    @Test
    public void addFavoriteAuthorTest() {
        FavoriteAuthor favoriteAuthor = new FavoriteAuthor(1L, 1L, 1L);
        Mockito.when(favoriteAuthorRepository.save(favoriteAuthor)).thenReturn(favoriteAuthor);
        Assertions.assertTrue(favoriteAuthorService.addFavoriteAuthor(favoriteAuthor));
    }

    @Test
    public void getFavoriteAuthorsTest() {
        List<Object> favoriteAuthor = new ArrayList<>();
        favoriteAuthor.add(1L);
        Mockito.when(favoriteAuthorRepository.getFavoriteAuthorsByUserid(1L)).thenReturn(favoriteAuthor);
        Assertions.assertEquals(favoriteAuthorRepository.getFavoriteAuthorsByUserid(1L),
                favoriteAuthorService.getFavoriteAuthors(1L));
    }

    @Test
    public void getFavoriteAuthorTest() {
        Mockito.when(favoriteAuthorRepository.existsFavoriteAuthorByUseridAndFavoriteauthorid(1L, 1L)).thenReturn(true);
        Assertions.assertTrue(favoriteAuthorService.getFavoriteAuthor(1L, 1L));
    }

    @Test
    public void deleteFavoriteAuthorTest() {
        FavoriteAuthor favoriteAuthor = new FavoriteAuthor(1L, 1L, 1L);
        favoriteAuthorService.deleteFavoriteAuthor(favoriteAuthor.getUserid(), favoriteAuthor.getFavoriteauthorid());
        Mockito.verify(favoriteAuthorRepository).deleteFavoriteAuthorByUseridAndFavoriteauthorid(
                favoriteAuthor.getUserid(), favoriteAuthor.getFavoriteauthorid());
    }

}