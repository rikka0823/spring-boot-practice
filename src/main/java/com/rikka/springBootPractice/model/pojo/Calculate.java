package com.rikka.springBootPractice.model.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class Calculate {

    public static int count = 0;

//    public int i;

    static void increment() {
        ++count;
    }

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

        System.out.println(count);

//        Calculate calculate = Calculate.builder()
//                .i(1)
//                .build();
//
//        calculate.setI(2);
    }
}
