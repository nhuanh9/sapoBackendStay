package com.example.hello.repository;

import com.example.hello.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.ws.Service;

public interface SelectionRepository extends JpaRepository<Selection, Long> {
    Long countAllByVariantId(Long id);

    Selection findByName(String name);

    void deleteAllByVariantId(Long id);

    Iterable<Selection> findAllByVariantId(Long id);

    Selection findByVariantIdAndName(Long id, String name);
}
