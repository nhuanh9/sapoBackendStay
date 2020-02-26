package com.example.hello.repository;

import com.example.hello.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionRepository extends JpaRepository<Selection, Long> {
}
