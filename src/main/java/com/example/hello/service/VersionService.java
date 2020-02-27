package com.example.hello.service;

import com.example.hello.model.Variant;
import com.example.hello.model.Version;

import javax.persistence.Id;
import java.util.Optional;

public interface VersionService {
    Iterable<Version> findAll();

    Optional<Version> findById(Long id);

    void save(Version version);

    void delete(Long id);

    //Iterable<Version> findAllByProductIdAndVariantIdAnAndSelectionId(Long productId, Long variantId, Long selectionId);
}
