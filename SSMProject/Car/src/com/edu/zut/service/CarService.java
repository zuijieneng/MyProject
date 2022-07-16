package com.edu.zut.service;

import com.edu.zut.entity.Car;

import java.util.List;

public interface CarService {
    int deleteByPrimaryKey(String carId);

    int insert(Car record);

    Car selectByPrimaryKey(String carId);

    List<Car> selectAll();

    int updateByPrimaryKey(Car record);

}
