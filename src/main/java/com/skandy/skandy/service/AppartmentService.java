package com.skandy.skandy.service;

import com.skandy.skandy.dao.entity.Apartment;
import com.skandy.skandy.dao.repository.AppartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AppartmentService {
    public final AppartmentRepository appartmentRepository;

    public List<Apartment> getAll() {
        return appartmentRepository.findAll();
    }

    public Apartment save(Integer id, Integer floor, Integer gate, Float area, Integer roomCount, Integer houseBuilding, Integer houseNumber) {
        Apartment apartment = Apartment
                .builder()
                .id(UUID.randomUUID())
                .flatNumber(id)
                .floor(floor)
                .gate(gate)
                .area(area)
                .roomCount(roomCount)
                .houseBuilding(houseBuilding)
                .houseNumber(houseNumber)
                .build();
        return appartmentRepository.save(apartment);
    }

    public List<Apartment> getByHouseAndFlat(Integer house, Integer flat) {
        return appartmentRepository.findByHouseNumberAndFlatNumber(house, flat);
    }
}
