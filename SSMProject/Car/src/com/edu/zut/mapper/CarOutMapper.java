package com.edu.zut.mapper;

import com.edu.zut.entity.CarOut;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CarOutMapper {
    int deleteByPrimaryKey(String carOutId);

    int insert(CarOut record);

    CarOut selectByPrimaryKey(String carOutId);

    List<CarOut> selectAll();

    int updateByPrimaryKey(CarOut record);

    CarOut selectByCarId(String carId);

    @Select("select  car_out_id from tab_car_out where car_id=#{catId} order by car_out_start_time desc limit 1")
    String  getcarOutIdByCarId(String carId);
}