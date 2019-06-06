package com.example.demo.dao;

import com.example.demo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {
}
