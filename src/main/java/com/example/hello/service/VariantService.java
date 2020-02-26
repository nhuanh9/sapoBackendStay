package com.example.hello.service;

import com.example.hello.model.Variant;

import java.util.Optional;

public interface VariantService {
    Iterable<Variant> findAll();

    Optional<Variant> findById(Long id);

    void save(Variant variant);

    void delete(Long id);

    Variant findByName(String name);

}
