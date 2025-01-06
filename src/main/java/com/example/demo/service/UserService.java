package com.example.demo.service;

import com.example.demo.repository.jpa.connection.UserEntity;
import com.example.demo.repository.jpa.connection.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity update(UserEntity entity, int id) {
        if(getUserById(id) != null){
            return userRepository.save(entity);
        }
        return null;
    }
    @Override
    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElse(null); // Doit renvoyer un optionnal donc : orElse
    }
    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }



}
