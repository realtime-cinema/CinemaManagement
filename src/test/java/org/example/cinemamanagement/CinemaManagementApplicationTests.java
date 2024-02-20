package org.example.cinemamanagement;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest

class CinemaManagementApplicationTests {

    Caculator caculator = new Caculator();

    @Test
    void itShouldAddNumbers() {
        System.out.println("Hello");
        int number = Caculator.add(1, 2);
        assertThat(number).isEqualTo(2);
    }

    class Caculator {
        static int add(int a, int b) {
            return a + b;
        }
    }
}
