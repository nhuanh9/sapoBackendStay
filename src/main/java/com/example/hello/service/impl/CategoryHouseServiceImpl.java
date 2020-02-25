package com.example.hello.service.impl;

import com.example.hello.model.CategoryHouse;
import com.example.hello.repository.CategoryHouseRepository;
import com.example.hello.service.CategoryHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryHouseServiceImpl implements CategoryHouseService {
    @Autowired
    private CategoryHouseRepository categoryHomeRepository;

    @Override
    public Iterable<CategoryHouse> findAll() {
        return categoryHomeRepository.findAll();
    }

    @Override
    public Optional<CategoryHouse> findById(Long id) {
        return categoryHomeRepository.findById(id);
    }

    @Override
    public void save(CategoryHouse categoryHome) {
        categoryHomeRepository.save(categoryHome);
    }

    @Override
    public void delete(Long id) {
        Optional<CategoryHouse> categoryHouse = categoryHomeRepository.findById(id);
        if (categoryHouse.isPresent()) {
            categoryHomeRepository.delete(categoryHouse.get());
        }
    }

    @Override
    public CategoryHouse findByName(String name) {
        return categoryHomeRepository.findByName(name);
    }


}
