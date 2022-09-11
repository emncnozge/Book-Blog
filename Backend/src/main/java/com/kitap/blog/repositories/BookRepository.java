package com.kitap.blog.repositories;

import com.kitap.blog.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findTop20ByOrderByCreatedOnDesc();

    List<Book> findBooksByGenre(String genre);

    @Query("select distinct b.genre from book b")
    List<String> getGenres();
    @Query("select b from book b where b.author_id=:author_id")
    List<Object> findBooksByAuthorId(Long author_id);

}
