package com.rikka.springBootPractice.model.pojo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class CalculateTest {

    @Disabled
    @Test
    public void add() {
        Calculate calculate = new Calculate();
        int result = calculate.add(2, 3);

        System.out.println("add method");

        assertEquals(5, result);
        assertTrue(result > 1);
//        assertNull(result, "result should be null");
    }

    @DisplayName("divide method")
    @Test
    public void divide() {
        Calculate calculate = new Calculate();

        System.out.println("divide method");

        assertThrows(ArithmeticException.class,
                () -> calculate.divide(2, 0));
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("@AfterAll");
    }
}