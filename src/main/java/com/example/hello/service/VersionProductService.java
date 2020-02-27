package com.example.hello.service;

import com.example.hello.model.Version;
import com.example.hello.model.VersionProduct;

import java.util.Optional;

public interface VersionProductService {
    Iterable<VersionProduct> findAll();

    Optional<VersionProduct> findById(Long id);

    void save(VersionProduct versionProduct);

    void delete(Long id);

    VersionProduct findByName(String name);
}
