package com.example.demo.service;

import com.example.demo.repository.UserData;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserData> getAllUsers() {
        return (List<UserData>) userRepository.findAll();
    }

    @Override
    public UserData update(UserData entity, int id) {
        if(getUserById(id) != null){
            return userRepository.save(entity);
        }
        return null;
    }
    @Override
    public UserData getUserById(int id) {
        return userRepository.findById(id).orElse(null); // Doit renvoyer un optionnal donc : orElse
    }
    @Override
    public UserData saveUser(UserData user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }



}
