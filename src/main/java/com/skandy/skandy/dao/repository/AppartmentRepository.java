package com.skandy.skandy.dao.repository;

import com.skandy.skandy.dao.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppartmentRepository extends JpaRepository<Apartment, Integer> {

    List<Apartment> findByHouseNumberAndFlatNumber(Integer house, Integer flatNumber);
}
