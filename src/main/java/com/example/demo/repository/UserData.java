package com.example.demo.repository;

public class UserData {
    private int id;
    private String name;

    public UserData(){

    }
    public UserData(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }


}
