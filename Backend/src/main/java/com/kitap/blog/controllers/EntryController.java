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

    @GetMapping
    public List<Entry> getEntries(@RequestParam Long book_id) {
        return entryService.getEntries(book_id);
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
