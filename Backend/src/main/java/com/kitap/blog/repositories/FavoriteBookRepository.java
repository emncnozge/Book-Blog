package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Long> {

    @Query("select b from book b, favorite_book fb where fb.favoritebookid=b.book_id and fb.userid=:user_id")
    List<Object> getFavoriteBooksByUserid(Long user_id);

    Boolean existsFavoriteBookByUseridAndFavoritebookid(Long user_id, Long favorite_book_id);

    void deleteFavoriteBookByUseridAndFavoritebookid(Long user_id, Long favorite_book_id);
}
