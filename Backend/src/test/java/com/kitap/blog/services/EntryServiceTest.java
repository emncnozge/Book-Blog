package com.kitap.blog.services;

import com.kitap.blog.entities.Entry;
import com.kitap.blog.repositories.EntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class EntryServiceTest {
    private EntryService entryService;
    private EntryRepository entryRepository;

    @BeforeEach
    void setUp() {
        entryRepository = Mockito.mock(EntryRepository.class);
        entryService = new EntryService(entryRepository);
    }

    @Test
    public void addEntryTest() {
        Entry entry = new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date());
        Mockito.when(entryRepository.save(entry)).thenReturn(entry);
        Assertions.assertTrue(entryService.addEntry(entry));
    }

    @Test
    public void getEntryTest() {
        Mockito.when(entryRepository.findById(1L)).thenReturn(
                Optional.of(new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date())));
        Assertions.assertEquals(entryRepository.findById(1L), entryService.getEntry(1L));
    }
    @Test
    public void findTop5ByBookidOrderByCreatedOnDesc() {
        List<Entry> entry = new ArrayList<>();
        entry.add(new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date()));
        Mockito.when(entryRepository.findTop5ByBookidOrderByCreatedOnDesc(1L)).thenReturn(entry);
        Assertions.assertEquals(1, entryService.findTop5ByBookidOrderByCreatedOnDesc(1L).size());
    }

    @Test
    public void getEntriesByBookidTest() {
        List<Entry> entry = new ArrayList<>();
        entry.add(new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date()));
        Mockito.when(entryRepository.getEntriesByBookid(1L))
                .thenReturn(entry);
        Assertions.assertEquals(1, entryService.getEntriesByBookid(1L).size());
    }

    @Test
    public void getEntriesByUseridTest() {
        List<Entry> entry = new ArrayList<>();
        entry.add(new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date()));
        Mockito.when(entryRepository.getEntriesByUserid(1L))
                .thenReturn(entry);
        Assertions.assertEquals(1, entryService.getEntriesByUserid(1L).size());
    }

    @Test
    public void deleteEntryTest() {
        Entry entry = new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date());
        entryService.deleteEntry(entry.getEntry_id());
        Mockito.verify(entryRepository).deleteById(entry.getEntry_id());
    }

    @Test
    public void updateEntryTest() {
        Entry entry = new Entry(1L, 1L, 1L, "TestHeader", "TestEntry", new Date(), new Date());
        Mockito.doReturn(Optional.of(entry)).when(entryRepository).findById(entry.getEntry_id());
        Assertions.assertTrue(
                entryService.updateEntry(entry.getEntry_id(), "TestHeader2", "TestEntry2"));
    }

}