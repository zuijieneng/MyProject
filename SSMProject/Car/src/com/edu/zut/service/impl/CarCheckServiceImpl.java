package com.edu.zut.service.impl;

import com.edu.zut.entity.Car;
import com.edu.zut.entity.CarCheck;
import com.edu.zut.exception.CarCheckException;
import com.edu.zut.mapper.CarCheckMapper;
import com.edu.zut.mapper.CarInMapper;
import com.edu.zut.mapper.CarMapper;
import com.edu.zut.mapper.CarOutMapper;
import com.edu.zut.service.CarCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarCheckServiceImpl implements CarCheckService{
	@Autowired
	private CarCheckMapper mapper;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private CarInMapper carInMapper;
	@Autowired
	CarOutMapper carOutMapper;
	@Override
	public int deleteByPrimaryKey(String carCheckId) {
		//删除车检表之前要先删除入库和出库记录
		CarCheck carCheck = mapper.selectByPrimaryKey(carCheckId);
		System.out.println("hhhhhhhhhhhh"+carInMapper.selectByCarId(carCheck.getCarId()).toString());
		if(carInMapper.selectByCarId(carCheck.getCarId())!=null||carOutMapper.selectByCarId(carCheck.getCarId())!=null){
			throw new CarCheckException("入库表和出库表正在使用这条记录，不可以删除哦！");
		}
		return mapper.deleteByPrimaryKey(carCheckId);
	}

	@Override
	public int insert(CarCheck record) {
		String id="car_check_"+UUID.randomUUID().toString().replace("-","").substring(0,30);
		record.setCarCheckId(id);
		//修改汽车状态
		Car car=carMapper.selectByPrimaryKey(record.getCarId());
		car.setCarStatus(3);
		carMapper.updateByPrimaryKey(car);
		return mapper.insert(record);
	}

	@Override
	public CarCheck selectByPrimaryKey(String carCheckId) {
		return mapper.selectByPrimaryKey(carCheckId);
	}

	@Override
	public List<CarCheck> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(CarCheck record) {
		return mapper.updateByPrimaryKey(record);
	}

}
