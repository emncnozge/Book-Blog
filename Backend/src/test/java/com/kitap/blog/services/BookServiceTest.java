package com.kitap.blog.services;

import com.kitap.blog.entities.Book;
import com.kitap.blog.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void addBookTest() {
        Book book = new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date());
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertEquals(book.getBook_id(), bookService.addBook(book));
    }

    @Test
    public void getBookTest() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(
                Optional.of(new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date())));
        Assertions.assertEquals(bookRepository.findById(1L), Optional.of(bookService.getBook(1L)));
    }

    @Test
    public void getBooksTest() {
        Mockito.when(bookRepository.findAll()).thenReturn(Stream
                .of(new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date()),
                        new Book(2L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date()))
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, bookService.getBooks().size());
    }

    @Test
    public void getLast20BooksTest() {
        bookService.getLast20Books();
        Mockito.verify(bookRepository).findTop20ByOrderByCreatedOnDesc();
    }

    @Test
    public void deleteBookTest() {
        Book book = new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date());
        bookService.deleteBook(book.getBook_id());
        Mockito.verify(bookRepository).deleteById(book.getBook_id());
    }

    @Test
    public void updateBookTest() {
        Book book = new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date());
        Mockito.doReturn(Optional.of(book)).when(bookRepository).findById(book.getBook_id());
        Assertions.assertTrue(bookService.updateBook(book.getBook_id(), book.getAuthor_id(), "TestName2", "TestGenre2",
                "TestAbout2", "/test_url2"));
    }

    @Test
    public void addBookPhotoTest() throws IOException {
        Book book = new Book(0L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date());
        Mockito.doReturn(Optional.of(book)).when(bookRepository).findById(book.getBook_id());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("images", "image1", "image/png",
                BookServiceTest.class.getResourceAsStream("/images/image1.png"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        bookService.addBookPhoto(book.getBook_id(), mockMultipartFile, mockHttpServletResponse);
        Assertions.assertEquals("images/book-photos/0/image1", book.getPhoto_url());
    }

    @Test
    public void getBookPhotoTest() throws IOException {
        Book book = new Book(0L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date());
        Mockito.doReturn(Optional.of(book)).when(bookRepository).findById(book.getBook_id());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Assertions.assertEquals(bookService.getBookPhoto(book.getBook_id(), mockHttpServletResponse).contentLength(),
                23140);
    }

    @Test
    public void getGenresTest() {
        Mockito.when(bookRepository.getGenres()).thenReturn(Stream
                .of("Test1",
                        "Test2")
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, bookService.getGenres().size());
    }

    @Test
    public void getBooksByGenre() {
        Mockito.when(bookRepository.findBooksByGenre("TestGenre")).thenReturn(Stream
                .of(new Book(1L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date()),
                        new Book(2L, "TestName", "TestGenre", 1L, "TestAbout", "", new Date(), new Date()))
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, bookService.getBooksByGenre("TestGenre").size());
    }
}