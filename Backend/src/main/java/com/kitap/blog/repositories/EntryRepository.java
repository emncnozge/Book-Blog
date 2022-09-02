package com.kitap.blog.repositories;

import com.kitap.blog.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> getEntriesByBookid(Long bookid);

    List<Entry> findTop5ByBookidOrderByCreatedOnDesc(Long bookid);

    List<Entry> getEntriesByUserid(Long userid);
}
