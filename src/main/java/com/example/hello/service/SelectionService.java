package com.example.hello.service;

import com.example.hello.model.Selection;
import com.example.hello.model.Variant;

import java.util.Optional;

public interface SelectionService {
    Iterable<Selection> findAll();

    Optional<Selection> findById(Long id);

    void save(Selection selection);

    void delete(Long id);

    Long countAllByVariantId(Long id);

    Selection findByName(String name);

    void deleteAllById(Long id);

    Iterable<Selection> findAllByVariantId(Long id);


}
