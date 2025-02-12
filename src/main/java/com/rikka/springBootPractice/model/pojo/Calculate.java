package com.rikka.springBootPractice.model.pojo;

public class Calculate {
    public int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println(new Calculate().add(1, 3));
    }

}
