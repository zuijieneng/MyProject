package com.edu.zut.service;

import com.edu.zut.entity.CarOut;

import java.util.List;

public interface CarOutService {
    int deleteByPrimaryKey(String carOutId);

    int insert(CarOut record);

    CarOut selectByPrimaryKey(String carOutId);

    List<CarOut> selectAll();

    int updateByPrimaryKey(CarOut record);


    CarOut selectByCarId(String carId);

}
