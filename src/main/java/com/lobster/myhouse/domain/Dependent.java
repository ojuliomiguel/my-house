package com.lobster.myhouse.domain;

public class Dependent {
    private String name;
    private int age;

    public Dependent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}