package com.kitap.blog.controllers;

import com.kitap.blog.entities.Entry;
import com.kitap.blog.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/entry")

public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("getByBookId")
    public List<Entry> getEntriesByBookid(@RequestParam Long book_id) {
        return entryService.getEntriesByBookid(book_id);
    }

    @GetMapping("getTop5ByBookId")
    public List<Entry> findTop5ByBookidOrderByCreatedOnDesc(@RequestParam Long book_id) {
        return entryService.findTop5ByBookidOrderByCreatedOnDesc(book_id);
    }

    @GetMapping("getByUserId")
    public List<Entry> getEntriesByUserid(@RequestParam Long user_id) {
        return entryService.getEntriesByUserid(user_id);
    }

    @GetMapping(path = "getEntry")
    public Optional<Entry> getEntry(@RequestParam Long entry_id) {
        return entryService.getEntry(entry_id);
    }

    @PostMapping
    public boolean addEntry(@RequestBody Entry entry) {
        return entryService.addEntry(entry);
    }

    @PostMapping(path = "delete")
    public boolean deleteEntry(@RequestBody Map<String, Object> data) {

        return entryService.deleteEntry(Long.valueOf(data.get("entry_id").toString()));
    }


    @PutMapping
    public boolean updateEntry(@RequestBody Map<String, Object> body) {
        return entryService.updateEntry(
                Long.valueOf(body.get("entry_id").toString()),
                String.valueOf(body.get("header")),
                String.valueOf(body.get("entry"))
        );

    }

}
