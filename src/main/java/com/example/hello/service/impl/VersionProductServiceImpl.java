package com.example.hello.service.impl;

import com.example.hello.model.Version;
import com.example.hello.model.VersionProduct;
import com.example.hello.repository.VersionProductRepository;
import com.example.hello.service.VersionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VersionProductServiceImpl implements VersionProductService {

    @Autowired
    private VersionProductRepository versionProductRepository;
    @Override
    public Iterable<VersionProduct> findAll() {
        return versionProductRepository.findAll();
    }

    @Override
    public Optional<VersionProduct> findById(Long id) {
        return versionProductRepository.findById(id);
    }

    @Override
    public void save(VersionProduct versionProduct) {
        versionProductRepository.save(versionProduct);
    }

    @Override
    public void delete(Long id) {
        versionProductRepository.deleteById(id);
    }

    @Override
    public VersionProduct findByName(String name) {
        return versionProductRepository.findByName(name);
    }


}
