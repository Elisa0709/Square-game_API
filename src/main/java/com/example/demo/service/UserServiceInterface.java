package com.example.demo.service;

import com.example.demo.repository.jpa.connection.UserEntity;

import java.util.List;

public interface UserServiceInterface {

    public UserEntity saveUser(UserEntity user);//
    public UserEntity getUserById(int id); //
    public List<UserEntity> getAllUsers(); //
    public UserEntity update(UserEntity entity, int id);
    public void deleteUser(int id);

}
