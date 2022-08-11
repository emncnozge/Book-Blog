package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteUserRepository extends JpaRepository<FavoriteUser, Long> {
    @Query("FROM favorite_user fu WHERE fu.user_id = :user_id")
    List<FavoriteUser> getFavoriteUsers(Long user_id);

    @Query("FROM favorite_user fu WHERE fu.user_id = :user_id AND fu.favorite_user_id= :favorite_user_id")
    List<FavoriteUser> getFavoriteUser(Long user_id, Long favorite_user_id);
}
