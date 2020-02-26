package com.example.hello.service;

import com.example.hello.model.Category;

import java.util.Optional;
public interface CategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void save(Category category);

    void delete(Long id);

    Category findByName(String name);
}
