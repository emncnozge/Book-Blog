package com.kitap.blog.services;

import com.kitap.blog.entities.FavoriteBook;
import com.kitap.blog.repositories.FavoriteBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class FavoriteBookServiceTest {
    private FavoriteBookService favoriteBookService;
    private FavoriteBookRepository favoriteBookRepository;

    @BeforeEach
    void setUp() {
        favoriteBookRepository = Mockito.mock(FavoriteBookRepository.class);
        favoriteBookService = new FavoriteBookService(favoriteBookRepository);
    }

    @Test
    public void addFavoriteBookTest() {
        FavoriteBook favoriteBook = new FavoriteBook(1L, 1L, 1L);
        Mockito.when(favoriteBookRepository.save(favoriteBook)).thenReturn(favoriteBook);
        Assertions.assertTrue(favoriteBookService.addFavoriteBook(favoriteBook));
    }

    @Test
    public void getFavoriteBooksTest() {
        List<Object> favoriteBook = new ArrayList<>();
        favoriteBook.add(1L);
        Mockito.when(favoriteBookRepository.getFavoriteBooksByUserid(1L)).thenReturn(favoriteBook);
        Assertions.assertEquals(favoriteBookRepository.getFavoriteBooksByUserid(1L),
                favoriteBookService.getFavoriteBooks(1L));
    }

    @Test
    public void getFavoriteBookTest() {
        Mockito.when(favoriteBookRepository.existsFavoriteBookByUseridAndFavoritebookid(1L, 1L)).thenReturn(true);
        Assertions.assertTrue(favoriteBookService.getFavoriteBook(1L, 1L));
    }

    @Test
    public void deleteFavoriteBookTest() {
        FavoriteBook favoriteBook = new FavoriteBook(1L, 1L, 1L);
        favoriteBookService.deleteFavoriteBook(favoriteBook.getUserid(), favoriteBook.getFavoritebookid());
        Mockito.verify(favoriteBookRepository).deleteFavoriteBookByUseridAndFavoritebookid(
                favoriteBook.getUserid(), favoriteBook.getFavoritebookid());
    }

}