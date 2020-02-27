package com.example.hello.repository;

import com.example.hello.model.VersionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionProductRepository extends JpaRepository<VersionProduct, Long> {
    VersionProduct findByName(String name);
}
