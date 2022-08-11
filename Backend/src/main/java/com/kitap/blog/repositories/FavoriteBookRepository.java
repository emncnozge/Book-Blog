package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Long> {
    @Query("FROM favorite_book fa WHERE fa.user_id = :user_id")
    List<FavoriteBook> getFavoriteBooks(Long user_id);

    @Query("FROM favorite_book fa WHERE fa.user_id = :user_id AND fa.favorite_book_id= :favorite_book_id")
    List<FavoriteBook> getFavoriteBook(Long user_id, Long favorite_book_id);
}
