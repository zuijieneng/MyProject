package com.edu.zut.mapper;

import com.edu.zut.entity.CarCheck;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CarCheckMapper {

    int deleteByPrimaryKey(@Param("carCheckId") String carCheckId);

    int insert(CarCheck record);

    CarCheck selectByPrimaryKey(String carCheckId);

    List<CarCheck> selectAll();

    int updateByPrimaryKey(CarCheck record);
    @Select("select  car_check_id from tab_car_check where car_id=#{catId} order by car_check_time desc limit 1")
    String getCheckIdByCarId(String carId);
}