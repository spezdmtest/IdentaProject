package com.identa.identaproject.service;


import com.identa.identaproject.entities.User;
import com.identa.identaproject.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.UUID;


class UserServiceImplTest {
    private UserRepository repository;
    private UserService userService;
    private SimpMessagingTemplate template;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test");
    }

    @BeforeEach
    void SetUpBeforeEach() {
        System.out.println("Before each test");
        repository = Mockito.mock(UserRepository.class);
        template = Mockito.mock(SimpMessagingTemplate.class);
        userService = new UserServiceImpl(repository,template);
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test");
    }

    @Test
    void checkFindByName() {
        //have
        String name = "my@email.com";
        User expectedUser = User.builder().id(1L).email(name).build();

        Mockito.when(repository.findFirstByEmail(Mockito.anyString())).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void checkFindByNameExact() {
        //have
        String name = "my@email.com";
        User expectedUser = User.builder().id(1L).email(name).build();

        Mockito.when(repository.findFirstByEmail(Mockito.eq(name))).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);
        User rndUser = userService.findByName(UUID.randomUUID().toString());

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertNull(rndUser);
    }
}


