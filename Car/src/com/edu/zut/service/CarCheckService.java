package com.edu.zut.service;

import com.edu.zut.entity.CarCheck;

import java.util.List;

public interface CarCheckService {
    int deleteByPrimaryKey(String carCheckId);

    int insert(CarCheck record);

    CarCheck selectByPrimaryKey(String carCheckId);

    List<CarCheck> selectAll();

    int updateByPrimaryKey(CarCheck record);
}
