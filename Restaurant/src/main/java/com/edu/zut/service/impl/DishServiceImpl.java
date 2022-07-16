package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.entity.Category;
import com.edu.zut.entity.Dish;
import com.edu.zut.entity.DishFlavor;
import com.edu.zut.entity.dto.DishDto;
import com.edu.zut.mapper.DishMapper;
import com.edu.zut.service.CategoryService;
import com.edu.zut.service.DishFlavorService;
import com.edu.zut.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorService dishFlavorService; //由于mapper没有批量保存，所以注入业务层
    @Resource
    private CategoryService categoryService; //由于mapper没有批量保存，所以注入业务层

    @Transactional //Application启动类注入了事务管理注解
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish（因为是父子类关系，所以可以直接插入）
        dishMapper.insert(dishDto);
        Long dishId = dishDto.getId(); //插入完，可以直接获取获取菜品id
        //保存菜品口味表到dish_flavor（批量保存）
        List<DishFlavor> flavors = dishDto.getFlavors();
        //使用jdk8的新特性处理list集合，设置每个口味flavor的菜品dish_id是什么
        flavors=flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList()); //将结果返回成一个list集合
        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public DishDto getByIdWithFlavor(Long dishId) {
        Dish dish = dishMapper.selectById(dishId);
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto); //此时DishDto里面已经有了Dish的基本数据
        //创建条件构造器，遍历dishId下的所有口味
        LambdaQueryWrapper<DishFlavor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId,dishId);
        List<DishFlavor> flavors = dishFlavorService.list(wrapper);
        dishDto.setFlavors(flavors); //此时DishDto已经有了口味集合
        //获取菜品分类名称
        Category category = categoryService.getById(dish.getCategoryId());
        dishDto.setCategoryName(category.getName()); //此时DishDto有了菜品分类名称
        return dishDto;
    }

    @Override
    public void updateWithFlavor(DishDto dishDto) {
        //修改基本属性
        dishMapper.updateById(dishDto);
        Long dishId = dishDto.getId(); //获取获取菜品id
        //清理当前菜品对应的口味数据---dish_flavor的delete操作（因为口味可能是添加/删除/更新）
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //删除完后获取前端传来的口味数据，绑定dishId，并添加到数据库中
        List<DishFlavor> flavors = dishDto.getFlavors();
        LambdaQueryWrapper<DishFlavor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId,dishId);
        flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
