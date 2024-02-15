package org.example.cinemamanagement;

import org.example.cinemamanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CinemaManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getUser() {
        
        UserRepository userRepository = null;
        System.out.println(userRepository.findUserByEmail("user"));
    }

}
