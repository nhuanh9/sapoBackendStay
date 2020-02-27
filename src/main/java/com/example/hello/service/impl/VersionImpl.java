package com.example.hello.service.impl;

import com.example.hello.model.Version;
import com.example.hello.repository.VersionRepository;
import com.example.hello.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class VersionImpl implements VersionService {
    @Autowired
    private VersionRepository versionRepository;
    @Override
    public Iterable<Version> findAll() {
        return versionRepository.findAll();
    }

    @Override
    public Optional<Version> findById(Long id) {
        return versionRepository.findById(id);
    }

    @Override
    public void save(Version version) {
        versionRepository.save(version);
    }

    @Override
    public void delete(Long id) {
        versionRepository.deleteById(id);
    }

//    @Override
//    public Iterable<Version> findAllByProductIdAndVariantIdAnAndSelectionId(Long productId, Long variantId, Long selectionId) {
//        return versionRepository.findAllByProductIdAndVariantIdAnAndSelectionId(productId, variantId, selectionId);
//    }

}
