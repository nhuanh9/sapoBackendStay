package com.example.hello.repository;

import com.example.hello.model.CategoryHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryHouseRepository extends JpaRepository<CategoryHouse, Long> {
    CategoryHouse findByName(String name);
}
