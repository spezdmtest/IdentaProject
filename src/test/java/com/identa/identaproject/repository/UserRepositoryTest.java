package com.identa.identaproject.repository;


import com.identa.identaproject.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void checkFindUserByEmail() {

        User user = new User();
        user.setEmail("test@test.com");

        entityManager.persist(user);

        User actualUser = userRepository.findFirstByEmail("test@test.com");

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(1, actualUser.getId());
        Assertions.assertEquals("test@test.com", actualUser.getEmail());
    }
}
