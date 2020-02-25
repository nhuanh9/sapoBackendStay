package com.example.hello.service;

import com.example.hello.model.CategoryHouse;

import java.util.Optional;

public interface CategoryHouseService {
    Iterable<CategoryHouse> findAll();

    Optional<CategoryHouse> findById(Long id);

    void save(CategoryHouse categoryHouse);

    void delete(Long id);

    CategoryHouse findByName(String name);
}
