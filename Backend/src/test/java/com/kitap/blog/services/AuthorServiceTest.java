package com.kitap.blog.services;

import com.kitap.blog.entities.Author;
import com.kitap.blog.repositories.AuthorRepository;
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


class AuthorServiceTest {
    private AuthorService authorService;
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository = Mockito.mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    public void addAuthorTest() {
        Author author = new Author(1L, "TestName", "TestAbout", "/test_url", new Date(), new Date());
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        Assertions.assertEquals(author.getAuthor_id(), authorService.addAuthor(author));
    }

    @Test
    public void getAuthorsTest() {
        Mockito.when(authorRepository.findAll()).thenReturn(Stream
                .of(new Author(1L, "TestName", "TestAbout", "/test_url", new Date(), new Date()),
                        new Author(1L, "TestName", "TestAbout", "/test_url", new Date(), new Date())).collect(Collectors.toList()));
        Assertions.assertEquals(2, authorService.getAuthors().size());
    }

    @Test
    public void deleteAuthorTest() {
        Author author = new Author(1L, "TestName", "TestAbout", "/test_url", new Date(), new Date());
        authorService.deleteAuthor(author.getAuthor_id());
        Mockito.verify(authorRepository).deleteById(author.getAuthor_id());
    }

    @Test
    public void updateAuthorTest() {
        Author author = new Author(1L, "TestName", "TestAbout", "/test_url", new Date(), new Date());
        Mockito.doReturn(Optional.of(author)).when(authorRepository).findById(author.getAuthor_id());
        Assertions.assertTrue(authorService.updateAuthor(author.getAuthor_id(), "TestName2", "TestAbout2", "/test_url2"));
    }

    @Test
    public void addAuthorPhotoTest() throws IOException {
        Author author = new Author(1L, "TestName", "TestAbout", "", new Date(), new Date());
        Mockito.doReturn(Optional.of(author)).when(authorRepository).findById(author.getAuthor_id());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("images", "image1", "image/png", AuthorServiceTest.class.getResourceAsStream("/images/image1.png"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        authorService.addAuthorPhoto(author.getAuthor_id(), mockMultipartFile, mockHttpServletResponse);
        Assertions.assertEquals("images/author-photos/1/image1", author.getPhoto_url());
    }

    @Test
    public void getAuthorPhotoTest() throws IOException {
        Author author = new Author(1L, "TestName", "TestAbout", "", new Date(), new Date());
        Mockito.doReturn(Optional.of(author)).when(authorRepository).findById(author.getAuthor_id());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Assertions.assertEquals(authorService.getAuthorPhoto(author.getAuthor_id(), mockHttpServletResponse).contentLength(), 38765);
    }
}