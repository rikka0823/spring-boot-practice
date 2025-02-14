package com.rikka.springBootPractice.model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Calculate {
    public int add(int x, int y) {
        return x + y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

    // psvm
    public static void main(String[] args) {
        System.out.println(new Calculate().add(1, 3));
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDate.now());
        String str1 = "測試";
        String str2 = "測試";

        System.out.println(str1 == str2);

    }
}
