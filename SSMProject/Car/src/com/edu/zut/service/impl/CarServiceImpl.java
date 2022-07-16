package com.edu.zut.service.impl;

import com.edu.zut.entity.Car;
import com.edu.zut.exception.CarException;
import com.edu.zut.mapper.CarInMapper;
import com.edu.zut.mapper.CarMapper;
import com.edu.zut.mapper.CarOutMapper;
import com.edu.zut.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
	@Autowired
	private CarMapper mapper;
	@Autowired
	private CarInMapper carInMapper;
	@Autowired
	private CarOutMapper carOutMapper;
	@Override
	public int deleteByPrimaryKey(String carId) {
		return mapper.deleteByPrimaryKey(carId);
	}

	@Override
	public int insert(Car record){
		//实现主键自增效果
		Car theLast = mapper.getTheLast();
		String carId=theLast.getCarId().substring(theLast.getCarId().indexOf("_")+1); //获取后面的id
		String id="car_00"+(Integer.valueOf(carId)+1);
		if (mapper.selectByPrimaryKey(id)!=null){
			throw new CarException("系统出现错误，请重新添加！");
		}else if (record.getCarNumber().length()>7){
			throw new CarException("车辆牌照不能过长！");
		}else if(record.getCarTime()==null){
			throw new CarException("请选择添加时间!");
		}else if(record.getCarPhoto()==null){
			throw new CarException("请上传照片！");
		}
		record.setCarId(id);
//		record.setCarStatus(1);
		return mapper.insert(record);
	}

	@Override
	public Car selectByPrimaryKey(String carId) {
		return mapper.selectByPrimaryKey(carId);
	}

	@Override
	public List<Car> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Car record) {
		return mapper.updateByPrimaryKey(record);
	}



}
