package com.edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.zut.entity.Dish;
import com.edu.zut.entity.dto.DishDto;

public interface DishService extends IService<Dish> {

    //操作两张表（dish、dishflavor）：新增菜品、同时插入菜品对应的口味
    public void saveWithFlavor(DishDto dishDto);
    //获取菜品以及菜品的所有口味，最后封装成DishDto扩展类，操作两张表（dish、dishflavor）：修改某条菜品信息时候用的
    public DishDto getByIdWithFlavor(Long dishId);
    //根据DishDto对象中的dishId修改菜品信息以及菜品口味
    public void updateWithFlavor(DishDto dishDto);
}
