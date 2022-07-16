package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.ExceptionHandler.CategoryException;
import com.edu.zut.entity.Category;
import com.edu.zut.entity.Dish;
import com.edu.zut.entity.Setmeal;
import com.edu.zut.mapper.CategoryMapper;
import com.edu.zut.mapper.DishMapper;
import com.edu.zut.mapper.SetmealMapper;
import com.edu.zut.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 根据id删除分类，删除之前进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //1、菜品条件构造器,查询是否关联了菜品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,id);
        Integer count = dishMapper.selectCount(wrapper);
        if(count > 0){
            //已经关联了菜品，不能删除，抛出异常
            throw new CategoryException("已经关联了菜品，无法删除！");
        }
        //2、套餐条件构造器,查询是否关联了菜品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setMealWrapper=new LambdaQueryWrapper<>();
        setMealWrapper.eq(Setmeal::getCategoryId,id);
        Integer count2 = setmealMapper.selectCount(setMealWrapper);
        if(count2 > 0){
            //已经关联了套餐，不能删除，抛出异常
            throw new CategoryException("已经关联了套餐，无法删除！");
        }
        //3、没有关联，正常删除
        super.removeById(id);
    }
}
