package com.edu.zut.service;

import com.edu.zut.entity.CarIn;

import java.util.List;

public interface CarInService {
    int deleteByPrimaryKey(String carInId);

    int insert(CarIn record);

    CarIn selectByPrimaryKey(String carInId);

    List<CarIn> selectAll();

    int updateByPrimaryKey(CarIn record);

    CarIn selectByCarId(String carId);
}
