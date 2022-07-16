package com.edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.zut.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id); //删除分类，需要判断菜品和套餐的关联
}
