package com.example.demo.repository.jdbc.connection;

import java.util.List;

public interface GenericDAO<T> {
    void save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity, int id);
    void delete(int id);
}
