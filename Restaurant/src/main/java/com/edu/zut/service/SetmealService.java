package com.edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.zut.entity.Setmeal;
import com.edu.zut.entity.dto.SetmealDto;

public interface SetmealService extends IService<Setmeal> {
    //需要操作表：Setmeal、SetmealDish关系表
    public boolean saveWithDish(SetmealDto setmealDto);
    //对套餐进行修改时候，前端传来一个套餐id，获取所有信息然后填充修改表单
    public SetmealDto getByIdWhenUpdate(Long setmealId);
    //修改套餐信息：Setmeal、SetmealDish关系表
    public void updateWithDish(SetmealDto setmealDto);
}
