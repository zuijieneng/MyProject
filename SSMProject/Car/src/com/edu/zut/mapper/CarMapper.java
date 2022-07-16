package com.edu.zut.mapper;

import com.edu.zut.entity.Car;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(String carId);

    int insert(Car record);

    Car selectByPrimaryKey(String carId);

    List<Car> selectAll();

    int updateByPrimaryKey(Car record);

    @Select("select * from tab_car order by car_id desc limit 1")
    Car getTheLast();

}