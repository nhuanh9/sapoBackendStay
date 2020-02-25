package com.example.hello.service;

import com.example.hello.model.CategoryHouse;
import com.example.hello.model.House;

import java.util.Optional;

public interface HouseService {
    Iterable<House> findAll();

    Optional<House> findById(Long id);

    void save(House house);

    void delete(Long id);

}
