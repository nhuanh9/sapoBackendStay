package com.example.hello.repository;

import com.example.hello.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    Variant findByName(String name);
}
