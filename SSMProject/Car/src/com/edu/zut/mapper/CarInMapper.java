package com.edu.zut.mapper;

import com.edu.zut.entity.CarIn;

import java.util.List;

public interface CarInMapper {
    int deleteByPrimaryKey(String carInId);

    int insert(CarIn record);

    CarIn selectByPrimaryKey(String carInId);

    List<CarIn> selectAll();

    int updateByPrimaryKey(CarIn record);

    CarIn selectByCarId(String carId);
}