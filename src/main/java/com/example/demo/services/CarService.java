package com.example.demo.services;

import com.example.demo.dao.CarDAO;
import com.example.demo.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void saveCar(Car car){
        carDAO.save(car);
    }

    public void deleteCar(int id){
        Car one = carDAO.getOne(id);
        carDAO.delete(one);
    }

    public Car findById(int id){
        Car one = carDAO.getOne(id);
        return one;
    }

    public void updateCar(Car car){
        carDAO.save(car);
    }

    public List<Car> findAll(){
        return carDAO.findAll();
    }
}
