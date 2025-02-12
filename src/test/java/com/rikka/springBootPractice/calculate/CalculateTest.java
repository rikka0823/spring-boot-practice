package com.rikka.springBootPractice.calculate;

import com.rikka.springBootPractice.model.pojo.Calculate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculateTest {

    @Test
    public void test() {
        Calculate calculate = new Calculate();
        assertEquals(5, calculate.add(2, 3));
    }
}