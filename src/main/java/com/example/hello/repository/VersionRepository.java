package com.example.hello.repository;

import com.example.hello.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    //Iterable<Version> findAllByProductIdAndVariantIdAnAndSelectionId(Long productId, Long variantId, Long selectionId);
}
