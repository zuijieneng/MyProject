package com.edu.zut.service.impl;

import com.edu.zut.entity.Car;
import com.edu.zut.entity.CarOut;
import com.edu.zut.exception.CarOutException;
import com.edu.zut.mapper.CarInMapper;
import com.edu.zut.mapper.CarMapper;
import com.edu.zut.mapper.CarOutMapper;
import com.edu.zut.service.CarOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarOutServiceImpl implements CarOutService {
    @Autowired
    CarOutMapper mapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    CarInMapper carInMapper;
    @Override
    public int deleteByPrimaryKey(String carOutId) {
        return mapper.deleteByPrimaryKey(carOutId);
    }

    @Override
    public int insert(CarOut record) {
        String ouId="car_out_"+ UUID.randomUUID().toString().replace("-","").substring(0,30);
        record.setCarOutId(ouId);
        if(carMapper.selectByPrimaryKey(record.getCarId()).getCarStatus()==2){
            throw new CarOutException("该车正在出租中，无法再次出租！");
        }
        //insert
        int insert = mapper.insert(record);
        //修改汽车状态
        Car car=carMapper.selectByPrimaryKey(record.getCarId());
        car.setCarStatus(2);
        carMapper.updateByPrimaryKey(car);
        //返回出库结果
        return insert;
    }

    @Override
    public CarOut selectByPrimaryKey(String carOutId) {
        return mapper.selectByPrimaryKey(carOutId);
    }

    @Override
    public List<CarOut> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CarOut record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public CarOut selectByCarId(String carId) {
        return mapper.selectByCarId(carId);
    }
}
