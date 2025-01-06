package com.example.demo.service;

import com.example.demo.repository.jpa.connection.UserData;

import java.util.List;

public interface UserServiceInterface {

    public UserData saveUser(UserData user);//
    public UserData getUserById(int id); //
    public List<UserData> getAllUsers(); //
    public UserData update(UserData entity, int id);
    public void deleteUser(int id);

}
