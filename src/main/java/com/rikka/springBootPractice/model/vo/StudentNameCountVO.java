package com.rikka.springBootPractice.model.vo;

public class StudentNameCountVO {

    private String name;

    private Integer count;

    public StudentNameCountVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StudentNameCountVO{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
