package com.edu.zut.service.impl;

import com.edu.zut.entity.Car;
import com.edu.zut.entity.CarIn;
import com.edu.zut.exception.CarInException;
import com.edu.zut.mapper.CarInMapper;
import com.edu.zut.mapper.CarMapper;
import com.edu.zut.mapper.CarOutMapper;
import com.edu.zut.service.CarInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarInServiceImpl implements CarInService {
    @Autowired
    private CarInMapper mapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarOutMapper carOutMapper;
    @Override
    public int deleteByPrimaryKey(String carInId) {
        return mapper.deleteByPrimaryKey(carInId);
    }

    @Override
    public int insert(CarIn record) {
        String id= "car_in_"+UUID.randomUUID().toString().replace("-","").substring(0,30);
        record.setCarInId(id);
        if(carMapper.selectByPrimaryKey(record.getCarId()).getCarStatus()==1){
            throw new CarInException("入库表已经存在该条数据！请出租后再入库");
        }
        int insert = mapper.insert(record);
        //入库之后汽车的状态改变了
        Car car=carMapper.selectByPrimaryKey(record.getCarId());
        car.setCarStatus(1);
        carMapper.updateByPrimaryKey(car);
        //添加入库表中
        return insert;
    }

    @Override
    public CarIn selectByPrimaryKey(String carInId) {
        return mapper.selectByPrimaryKey(carInId);
    }

    @Override
    public List<CarIn> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CarIn record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public CarIn selectByCarId(String carId) {
        return mapper.selectByCarId(carId);
    }

}
