package com.example.demo.service;

import com.example.demo.repository.MySQLUserDao;
import com.example.demo.repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    MySQLUserDao userDao;


    @Override
    public void saveUser(UserData entity) {
        userDao.save(entity);
    }

    @Override
    public UserData findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserData> findAll() {
        return userDao.findAll();
    }

    @Override
    public void update(UserData entity, int id) {
        userDao.update(entity, id);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
