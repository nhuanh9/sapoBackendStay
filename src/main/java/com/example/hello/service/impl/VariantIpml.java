package com.example.hello.service.impl;

import com.example.hello.model.Variant;
import com.example.hello.repository.VariantRepository;
import com.example.hello.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class VariantIpml implements VariantService {
    @Autowired
    private VariantRepository variantRepository;
    @Override
    public Iterable<Variant> findAll() {
        return variantRepository.findAll();
    }

    @Override
    public Optional<Variant> findById(Long id) {
        return variantRepository.findById(id);
    }

    @Override
    public void save(Variant variant) {
        variantRepository.save(variant);
    }

    @Override
    public void delete(Long id) {
        variantRepository.deleteById(id);
    }

    @Override
    public Variant findByName(String name) {
        return variantRepository.findByName(name);
    }
}
