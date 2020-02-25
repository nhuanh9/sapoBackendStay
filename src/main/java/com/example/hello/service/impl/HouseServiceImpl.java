package com.example.hello.service.impl;

import com.example.hello.model.House;
import com.example.hello.repository.HouseRepository;
import com.example.hello.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;
    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public void save(House house) {
        houseRepository.save(house);
    }

    @Override
    public void delete(Long id) {
        Optional<House> house = houseRepository.findById(id);
        if (house.isPresent()) {
            houseRepository.delete(house.get());
        }
    }
}
