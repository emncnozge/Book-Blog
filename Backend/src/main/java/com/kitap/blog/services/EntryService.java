package com.kitap.blog.services;

import com.kitap.blog.entities.Entry;
import com.kitap.blog.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> getEntries(Long book_id) {
        return entryRepository.getEntriesByBookid(book_id);
    }

    public Optional<Entry> getEntry(Long entry_id) {
        return entryRepository.findById(entry_id);
    }

    public boolean addEntry(Entry entry) {
        try {
            entryRepository.saveAndFlush(entry);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Transactional
    public boolean deleteEntry(Long entry_id) {
        try {
            boolean exists = entryRepository.existsById(entry_id);
            if (exists) {
                entryRepository.deleteById(entry_id);
                return true;
            } else
                return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Transactional
    public boolean updateEntry(Long entry_id, String header, String entryContext) {
        boolean exists = entryRepository.existsById(entry_id);
        if (exists) {
            if (header.equals("null")) header = null;
            if (entryContext.equals("null")) entryContext = null;
            Entry entry = entryRepository.findById(entry_id).orElseThrow(() -> new IllegalStateException("Error"));
            if (header != null && header.length() > 0 && !Objects.equals(entry.getHeader(), header)) {
                entry.setHeader(header);
            }
            if (entryContext != null && entryContext.length() > 0 && !Objects.equals(entry.getEntry(), entryContext)) {
                entry.setEntry(entryContext);
            }
            return true;
        } else
            return false;
    }
}


