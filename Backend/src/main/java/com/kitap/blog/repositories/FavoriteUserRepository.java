package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteUserRepository extends JpaRepository<FavoriteUser, Long> {
    @Query("select u from user u, favorite_user fu where fu.favoriteuserid=u.user_id and fu.userid=:user_id")
    List<Object> getFavoriteUsersByUserid(Long user_id);

    Boolean existsFavoriteUserByUseridAndFavoriteuserid(Long user_id, Long favorite_user_id);

    void deleteFavoriteUserByUseridAndFavoriteuserid(Long user_id, Long favorite_user_id);
}
