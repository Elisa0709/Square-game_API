package com.example.demo.service;

import com.example.demo.repository.UserData;

import java.util.List;

public interface UserServiceInterface {

    public void saveUser(UserData user);
    public UserData findById(int id);
    public List<UserData> findAll();
    public void update(UserData entity, int id);
    public void delete(int id);
}
