package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zut.entity.Category;
import com.edu.zut.entity.Setmeal;
import com.edu.zut.entity.SetmealDish;
import com.edu.zut.entity.common.R;
import com.edu.zut.entity.dto.SetmealDto;
import com.edu.zut.service.CategoryService;
import com.edu.zut.service.SetmealDishService;
import com.edu.zut.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Resource
    private SetmealService setmealService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SetmealDishService setmealDishService;

    /**
     * 分页+模糊查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //分页构造器：分页查询
        Page<Setmeal> pageInfo=new Page<>(page,pageSize);
        Page<SetmealDto> setmealDtoPage=new Page<>(page,pageSize);
        //条件构造器：模糊查询
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Setmeal::getName,name);
        setmealService.page(pageInfo,wrapper);
        //获取records记录，设置分类名
        List<Setmeal> setmealList = pageInfo.getRecords();
        List<SetmealDto> collect = setmealList.stream().map((item) -> {
            //创建对应的Setme象
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto); //此时扩展对象已经有了setmeal的基本信息
            //获取套餐分类名称
            Category category = categoryService.getById(item.getCategoryId());
            String categoryName = category.getName();
            setmealDto.setCategoryName(categoryName); //套餐分类名称也有了
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(collect);
        return R.success(setmealDtoPage);
    }

    /**
     * 添加套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        boolean b = setmealService.saveWithDish(setmealDto);
        return b==true?R.success("添加成功！"):R.error("添加失败！");
    }

    /**
     * 删除套餐/批量删除
     * 要操作两张表：套餐表和套餐关系表
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam("ids")List<Long> ids){
        //套餐条件构造器
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(Setmeal::getId,ids);
        setmealService.remove(wrapper);//删除套餐
        //套餐关系表的条件构造器
        LambdaQueryWrapper<SetmealDish> mealdishWrapper=new LambdaQueryWrapper<>();
        mealdishWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(mealdishWrapper); //删除关系
        return R.success("删除成功！");
    }

    /**
     * 更新时候查询套餐所有信息并填充信息
     * @param setmealId
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> seletOneWhenUpdate(@PathVariable("id")Long setmealId){
        SetmealDto setmealDto = setmealService.getByIdWhenUpdate(setmealId);
        return R.success(setmealDto);
    }

    /**
     * 修改套餐信息
     * @param setmealDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithDish(setmealDto);
        return R.success("修改成功！");
    }

    /**
     * 启售、停售、批量启售、批量禁售
     * @param ids
     * @return
     */
    @PostMapping("/status/{staId}")
    public R<String> updateStatus(@PathVariable("staId")int status, @RequestParam("ids") List<Long> ids){
        //条件构造器，如果是批量的话，使用in
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(Setmeal::getId,ids);
        //进行（批量）修改
        List<Setmeal> list = setmealService.list(wrapper);
        list.forEach((item)->item.setStatus(status));
        setmealService.updateBatchById(list);
        return R.success((status==1?"已经启售!":"已经停售！"));
    }

    /**
     * 根据套餐类型获取这种类型的所有套餐
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list(Long categoryId){
        //根据套餐类型获取套餐集合
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Setmeal::getCategoryId,categoryId);
        List<Setmeal> list = setmealService.list(wrapper);
        return R.success(list);
    }

}
