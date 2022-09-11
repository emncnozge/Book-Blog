package com.kitap.blog.services;

import com.kitap.blog.entities.Book;
import com.kitap.blog.repositories.BookRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getLast20Books() {
        return bookRepository.findTop20ByOrderByCreatedOnDesc();
    }
    public List<Object> getBooksByAuthorId(Long author_id) {
        return bookRepository.findBooksByAuthorId(author_id);
    }

    public Book getBook(Long user_id) {
        return bookRepository.findById(user_id)
                .orElseThrow(() -> new IllegalStateException("Error! Selected book doesn't exist."));
    }

    public Long addBook(Book book) {
        try {
            bookRepository.saveAndFlush(book);
            return book.getBook_id();
        } catch (Exception e) {
            System.out.println(e);
            return 0L;
        }
    }

    public boolean deleteBook(Long book_id) {
        try {
            bookRepository.deleteById(book_id);
            return true;

        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateBook(Long book_id, Long author_id, String name, String genre, String about, String photo_url) {
        boolean status = false;
        if (name.equals("null"))
            name = null;
        if (String.valueOf(author_id).equals("null"))
            author_id = null;
        if (about.equals("null"))
            about = null;
        if (genre.equals("null"))
            genre = null;
        if (photo_url.equals("null"))
            photo_url = null;
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalStateException("Error"));
        if (name != null && name.length() > 0 && !Objects.equals(book.getName(), name)) {
            book.setName(name);
            status = true;
        }
        if (genre != null && genre.length() > 0 && !Objects.equals(book.getGenre(), genre)) {
            book.setGenre(genre);
            status = true;

        }
        if (author_id != null && author_id > 0 && !Objects.equals(book.getAuthor_id(), author_id)) {
            book.setAuthor_id(author_id);
            status = true;

        }
        if (about != null && about.length() > 0 && !Objects.equals(book.getAbout(), about)) {
            book.setAbout(about);
            status = true;

        }
        if (photo_url != null && photo_url.length() > 0 && !Objects.equals(book.getPhoto_url(), photo_url)) {
            book.setPhoto_url(photo_url);
            status = true;
        }
        return status;
    }

    @Transactional
    public void addBookPhoto(Long book_id, MultipartFile multipartFile, HttpServletResponse httpServletResponse)
            throws IOException {
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalStateException("Error"));
        String fileName = StringUtils
                .cleanPath(multipartFile.getOriginalFilename() == null ? "" : multipartFile.getOriginalFilename());
        String uploadDir = "images/book-photos/" + book_id;
        book.setPhoto_url(uploadDir + "/" + fileName);

        byte[] bytes = multipartFile.getBytes();

        File dir = new File(uploadDir);
        if (!dir.exists())
            dir.mkdirs();

        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        httpServletResponse.sendRedirect("http://localhost:3000/admin/editbook/" + book_id);
    }

    public InputStreamResource getBookPhoto(Long book_id, HttpServletResponse response) throws IOException {
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalStateException("Error"));
        Resource resource1 = new PathResource(book.getPhoto_url());
        response.setContentType("image/jpeg");
        try {
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        } catch (Exception e) {
            resource1 = new PathResource("images/book-photos/default.png");
            response.setContentType("image/png");
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        }

    }

    public List<String> getGenres() {
        return bookRepository.getGenres();
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenre(genre);
    }
}
