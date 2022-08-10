package com.kitap.blog.repositories;

import com.kitap.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteUserRepository extends JpaRepository<User, String> {
}