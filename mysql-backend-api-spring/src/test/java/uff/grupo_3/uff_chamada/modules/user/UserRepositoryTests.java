package uff.grupo_3.uff_chamada.modules.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTests {
    // Crie um teste para o método getUserById() da classe UserRepository.

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }
    
    @Test
    public void testGetUsers() {
        User user1 = new User();
        user1.setId(4);
        user1.setUsername("test user 1");
        user1.setPassword("abc123");
        user1.setRole(UserRole.STUDENT);
        user1.setName("User Resu 1");
        user1.setEmail("resu1@email.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(5);
        user2.setUsername("test user 2");
        user2.setPassword("abc123");
        user2.setRole(UserRole.STUDENT);
        user2.setName("User Resu 2");
        user2.setEmail("resu2@email.com");
        userRepository.save(user2);

        List<User> usersList = userRepository.findAll();

        assertEquals(2, usersList.size());

        assertEquals(user1.getId(), usersList.get(0).getId());
        assertEquals(user1.getUsername(), usersList.get(0).getUsername());
        assertEquals(user1.getPassword(), usersList.get(0).getPassword());
        assertEquals(user1.getRole(), usersList.get(0).getRole());
        assertEquals(user1.getName(), usersList.get(0).getName());
        assertEquals(user1.getEmail(), usersList.get(0).getEmail());

        assertEquals(user2.getId(), usersList.get(1).getId());
        assertEquals(user2.getUsername(), usersList.get(1).getUsername());
        assertEquals(user2.getPassword(), usersList.get(1).getPassword());
        assertEquals(user2.getRole(), usersList.get(1).getRole());
        assertEquals(user2.getName(), usersList.get(1).getName());
        assertEquals(user2.getEmail(), usersList.get(1).getEmail());
    }
}
