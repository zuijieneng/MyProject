package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.entity.Dish;
import com.edu.zut.entity.Setmeal;
import com.edu.zut.entity.SetmealDish;
import com.edu.zut.entity.dto.SetmealDto;
import com.edu.zut.mapper.SetmealMapper;
import com.edu.zut.service.DishService;
import com.edu.zut.service.SetmealDishService;
import com.edu.zut.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Resource
    private SetmealMapper mapper; //别写成业务层了，要不然就陷入死循环了，自己掉自己的父类
    @Resource
    private SetmealDishService setmealDishService;
    @Resource
    private DishService dishService;

    @Override
    public boolean saveWithDish(SetmealDto setmealDto) {
        //获取SetmealDish数组
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //将套餐的基本信息插入Setmeal套餐表中，同时获取setmealId (父子类关系可以直接插入基本信息)
        mapper.insert(setmealDto);
        Long setmealId = setmealDto.getId();
        //为每条setmealdish数据设置setmealId
        setmealDishes = setmealDishes.stream().map((item) -> {
            //根据前端传来的菜品数组，获取每个菜品的详细信息
            Dish dish = dishService.getById(item.getDishId());
            BeanUtils.copyProperties(dish,item); //关系表有了菜品的基本信息，但同时也把dish的id赋值给了关系表的id，这里是不对的
            item.setId(null); //关系表的id要按照雪花算法去弄，不能设置dish的id，否则添加套餐的时候，添加不进去，因为主键重复了（同一盘不能出现不同套餐的坏结果了）
            item.setSetmealId(setmealId); //关系表有了套餐id信息
            return item;
        }).collect(Collectors.toList());
        //设置完毕插入SetmealDish关系表中
        return setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto getByIdWhenUpdate(Long setmealId) {
        SetmealDto setmealDto=new SetmealDto();
        //根据表单获取信息
        Setmeal setmeal = mapper.selectById(setmealId);
        BeanUtils.copyProperties(setmeal,setmealDto); //有套餐的基本信息
        //获取套餐下的菜品信息
        LambdaQueryWrapper<SetmealDish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealId);
        List<SetmealDish> setmealDishes = setmealDishService.list(wrapper);
        setmealDto.setSetmealDishes(setmealDishes); //有菜品信息
        return setmealDto;
    }

    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //获取菜品数组
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //获取套餐id
        Long setmealId = setmealDto.getId();
        //首先删除关系表中的所有菜品
        LambdaQueryWrapper<SetmealDish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(wrapper);
        //然后先更新套餐信息
        mapper.updateById(setmealDto);
        //再更新关系表中的信息,在此之前要先给每个菜品设置套餐id
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealId);
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }
}
