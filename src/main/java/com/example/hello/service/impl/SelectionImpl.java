package com.example.hello.service.impl;

import com.example.hello.model.Selection;
import com.example.hello.repository.SelectionRepository;
import com.example.hello.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SelectionImpl implements SelectionService {
    @Autowired
    private SelectionRepository selectionRepository;
    @Override
    public Iterable<Selection> findAll() {
        return selectionRepository.findAll();
    }

    @Override
    public Optional<Selection> findById(Long id) {
        return selectionRepository.findById(id);
    }

    @Override
    public void save(Selection selection) {
        selectionRepository.save(selection);
    }

    @Override
    public void delete(Long id) {
        selectionRepository.deleteById(id);
    }

    @Override
    public Long countAllByVariantId(Long id) {
        return selectionRepository.countAllByVariantId(id);
    }


}
