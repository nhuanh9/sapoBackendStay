package com.example.hello.service.impl;

import com.example.hello.model.Product;
import com.example.hello.repository.ProductRepository;
import com.example.hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductIpml implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}
