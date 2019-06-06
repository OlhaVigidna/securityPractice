package com.example.demo.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    @Id
    @GeneratedValue
    int id;
    String color;
    String model;

    public Car(String color, String model) {
        this.color = color;
        this.model = model;
    }
}
