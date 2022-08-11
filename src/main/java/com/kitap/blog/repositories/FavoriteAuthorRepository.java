package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteAuthorRepository extends JpaRepository<FavoriteAuthor, Long> {
    @Query("FROM favorite_author fa WHERE fa.user_id = :user_id")
    List<FavoriteAuthor> getFavoriteAuthors(Long user_id);

    @Query("FROM favorite_author fa WHERE fa.user_id = :user_id AND fa.favorite_author_id= :favorite_author_id")
    List<FavoriteAuthor> getFavoriteAuthor(Long user_id, Long favorite_author_id);
}
