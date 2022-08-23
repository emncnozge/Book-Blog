package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteAuthorRepository extends JpaRepository<FavoriteAuthor, Long> {
    @Query("select a from author a, favorite_author fa where fa.favoriteauthorid=a.author_id and fa.userid=:user_id")
    List<Object> getFavoriteAuthorsByUserid(Long user_id);

    Boolean existsFavoriteAuthorByUseridAndFavoriteauthorid(Long user_id, Long favorite_author_id);

    void deleteFavoriteAuthorByUseridAndFavoriteauthorid(Long user_id, Long favorite_author_id);
}
