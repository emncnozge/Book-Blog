package com.kitap.blog.repositories;

import com.kitap.blog.entities.FavoriteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteUserRepository extends JpaRepository<FavoriteUser, Long> {
    @Query("FROM favorite_user fu WHERE fu.user_email = :user_email")
    List<FavoriteUser> getFavoriteUsers(String user_email);

    @Query("FROM favorite_user fu WHERE fu.user_email = :user_email AND fu.favorite_user_email= :favorite_user_email")
    List<FavoriteUser> getFavoriteUser(String user_email, String favorite_user_email);
}
