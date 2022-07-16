package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zut.entity.Category;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    /**
     * 分页管理
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        Page<Category> pageInfo=new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        //进行分页查询
        categoryService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }

    /**
     * 新增分类，如果是菜品type是1，如果是套餐分配type是2，但都用的这个方法
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("新增category{}",category);
        categoryService.save(category);
        return R.success("新增数据成功！");
    }

    /**
     * 删除操作，如果关联了菜品或者套餐，就不能删除！
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        categoryService.remove(id);
        return R.success("sucess!");
    }

    /**
     * 进行修改操作
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功！");
    }

    /**
     * 前端传来参数type：1或者2，直接使用Category对象接收
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> getCategoryList(Category category){
        System.out.println("值行了1111111111111111111111111111111111111");
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        //select * from category where type = #{type} order by sort or order by updatetime
        wrapper.eq(category.getType()!=null,Category::getType,category.getType()).orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> categoryList = categoryService.list(wrapper);
        return R.success(categoryList);
    }

}
