package com.example.hello.service;
import com.example.hello.model.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void delete(Long id);

    Product findByName(String name);
}
