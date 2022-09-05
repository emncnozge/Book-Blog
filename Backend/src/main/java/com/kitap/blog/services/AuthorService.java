package com.kitap.blog.services;

import com.kitap.blog.entities.Author;
import com.kitap.blog.repositories.AuthorRepository;
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
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long user_id) {
        return authorRepository.findById(user_id)
                .orElseThrow(() ->
                        new IllegalStateException("Error! Selected author doesn't exist."));
    }

    public Long addAuthor(Author author) {
        try {
            authorRepository.saveAndFlush(author);

            return author.getAuthor_id();
        } catch (Exception e) {
            System.out.println(e);
            return 0L;
        }
    }

    public boolean deleteAuthor(Long author_id) {
        try {
            authorRepository.deleteById(author_id);
            return true;


        } catch (Exception e) {
            System.out.println("Hata!");
            return false;
        }
    }

    @Transactional
    public boolean updateAuthor(Long author_id, String name, String about, String photo_url) {
        boolean status = false;
        if (name.equals("null")) name = null;
        if (about.equals("null")) about = null;
        if (photo_url.equals("null")) photo_url = null;
        Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalStateException("Error"));
        if (name != null && name.length() > 0 && !Objects.equals(author.getName(), name)) {
            author.setName(name);
            status = true;
        }
        if (about != null && about.length() > 0 && !Objects.equals(author.getAbout(), about)) {
            author.setAbout(about);
            status = true;
        }
        if (photo_url != null && photo_url.length() > 0 && !Objects.equals(author.getPhoto_url(), photo_url)) {
            author.setPhoto_url(photo_url);
            status = true;
        }
        return status;
    }


    @Transactional
    public void addAuthorPhoto(Long author_id, MultipartFile multipartFile, HttpServletResponse httpServletResponse) throws IOException {
        Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalStateException("Error"));
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename() == null ? "" : multipartFile.getOriginalFilename());
        String uploadDir = "images/author-photos/" + author_id;
        author.setPhoto_url(uploadDir + "/" + fileName);

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

        httpServletResponse.sendRedirect("http://localhost:3000/admin/editauthor/" + author_id);
    }

    public InputStreamResource getAuthorPhoto(Long author_id, HttpServletResponse response) throws IOException {

        Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalStateException("Error"));
        Resource resource1 = new PathResource(author.getPhoto_url());
        response.setContentType("image/png");
        try {
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        } catch (Exception e) {
            resource1 = new PathResource("images/author-photos/default.png");
            response.setContentType("image/png");
            return new InputStreamResource(new FileInputStream(resource1.getFile()));
        }

    }
}
