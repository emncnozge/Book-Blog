package com.kitap.blog.services;

import com.kitap.blog.entities.User;
import com.kitap.blog.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private String token = "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv";

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void addUserTest() throws NoSuchAlgorithmException {
        User user = new User(1L, "TestEmail", "TestPassword", "TestName", "TestAbout", "/test_url", false, new Date(),
                new Date());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user.getUser_id(), userService.addUser(user, token));
    }

    @Test
    public void loginTest() throws NoSuchAlgorithmException {
        User user = new User(1L, "TestEmail", "VrzGLrfa6ajS33LGSwZna36bRU30F+f6b9hRnvYjHLg=",
                "TestName", "TestAbout", "/test_url", false, new Date(),
                new Date());
        Mockito.when(userRepository.existsByEmail("TestEmail")).thenReturn(true);
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Mockito.when(userRepository.getUserByEmail("TestEmail")).thenReturn(user);
        Assertions.assertEquals(1L, userService.login("TestEmail", "TestPassword", token));
    }

    @Test
    public void getUserTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "TestEmail", "TestPassword",
                "TestName", "TestAbout", "/test_url", false, new Date(), new Date())));
        Assertions.assertEquals(userRepository.findById(1L), Optional.of(userService.getUser(1L, token)));
    }

    @Test
    public void getUsersTest() {
        Mockito.when(userRepository.findAll()).thenReturn(Stream
                .of(new User(1L, "TestEmail", "TestPassword", "TestName", "TestAbout", "/test_url", false, new Date(),
                        new Date()),
                        new User(2L, "TestEmail", "TestPassword", "TestName", "TestAbout", "/test_url", false,
                                new Date(), new Date()))
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, userService.getUsers(token).size());
    }

    @Test
    public void deleteUserTest() {
        User user = new User(1L, "TestEmail", "TestPassword", "TestName", "TestAbout", "/test_url", false, new Date(),
                new Date());
        userService.deleteUser(user.getUser_id(), token);
        Mockito.verify(userRepository).deleteById(user.getUser_id());
    }

    @Test
    public void updateUserTest() throws NoSuchAlgorithmException {
        User user = new User(1L, "TestEmail", "TestPassword", "TestName", "TestAbout", "/test_url", false, new Date(),
                new Date());
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUser_id());
        Assertions.assertTrue(userService.updateUser(1L, "TestEmail", "TestPassword", "TestName", "TestAbout",
                "/test_url", false, token));
    }

    @Test
    public void addUserPhotoTest() throws IOException {
        User user = new User(0L, "TestEmail", "TestPassword", "TestName", "TestAbout", "", false, new Date(),
                new Date());
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUser_id());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("images", "image1", "image/png",
                UserServiceTest.class.getResourceAsStream("/images/image1.png"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        userService.addUserPhoto(user.getUser_id(), mockMultipartFile, mockHttpServletResponse);
        Assertions.assertEquals("images/user-photos/0/image1", user.getPhoto_url());
    }

    @Test
    public void getUserPhotoTest() throws IOException {
        User user = new User(0L, "TestEmail", "TestPassword", "TestName", "TestAbout", "", false, new Date(),
                new Date());
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUser_id());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Assertions.assertEquals(userService.getUserPhoto(user.getUser_id(), mockHttpServletResponse).contentLength(),
                38765);
    }
}