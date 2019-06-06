package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping ("/login")
    public void login(){
        System.out.println("login");
    }

    @PostMapping("/create")
    public List<Car> createCar(Car car){
        carService.saveCar(car);
//        System.out.println(car);
        return carService.findAll();
    }

    @DeleteMapping("/car{id}")
    public List<Car> delete(@PathVariable int id){
        carService.deleteCar(id);
        System.out.println(id);
        return carService.findAll();
    }

    @GetMapping("/car{id}")
    public Car getCarById(@PathVariable int id){
        Car byId = carService.findById(id);
        return byId;
    }

    @PostMapping("/car{id}")
    public List<Car> updateCar(Car car, @PathVariable int id){
        //update
        carService.updateCar(car);
        return  carService.findAll();
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carService.findAll();
    }


}
