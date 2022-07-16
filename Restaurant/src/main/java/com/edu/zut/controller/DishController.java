package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zut.entity.Category;
import com.edu.zut.entity.Dish;
import com.edu.zut.entity.DishFlavor;
import com.edu.zut.entity.common.R;
import com.edu.zut.entity.dto.DishDto;
import com.edu.zut.service.CategoryService;
import com.edu.zut.service.DishFlavorService;
import com.edu.zut.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 操作菜品以及口味：Dish表和DishFlavor表
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private DishFlavorService dishFlavorService;
    @Resource
    private CategoryService categoryService;

    /**
     * 如果简单的写分页，前端不会渲染菜单分类这一选项，因此只有先查询所有的categoryId，然后根据id查找名称，将所有的结果
     * 封装在扩展类DishDto中，然后渲染给前端
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //分页构造器
        Page<Dish> pageInfo=new Page<>();
        Page<DishDto>  dishDtoPage=new Page<>();
        //条件构造器，使得数据按照分类排序渲染
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Dish::getName,name).orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,wrapper);
        //对象拷贝,但是忽略records这个list<Dish>集合，因为这个集合里面只有categoryId没有Name
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> list=pageInfo.getRecords(); //获取所有的list<Dish> 中的categoryId
        List<DishDto> dishDtoList=list.stream().map((item)->{
            DishDto dishDto=new DishDto();
            //将dish的其他的普通属性copy到dishDto的Dish中
            BeanUtils.copyProperties(item,dishDto);
            Long categoryId=item.getCategoryId(); //分类Id
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName(); //根据Id查询category表获取categoryName，然后进行赋值
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(dishDtoList);
        return R.success(dishDtoPage);
    }

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("DishController接收菜品种类以及菜品口味={}",dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("添加成功！");
    }

    /**
     * 删除菜品+批量删除
     * 批量删除时候，前端传来的参数格式：xxx,xxx,xxx
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long> ids){
        //删除菜品，使用参数是Collection集合的removeByIds方法
        dishService.removeByIds(ids);
        //删除口味表中数据
        LambdaQueryWrapper<DishFlavor> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(wrapper);
        return R.success("删除成功！");
    }

    /**
     * 修改菜品时候调用axios获取这个菜品所有信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> selectOneWhenUpdate(@PathVariable("id")Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id); //根据id获取菜品信息以及菜品所有口味信息
        return R.success(dishDto);
    }

    /**
     * 修改菜品信息，注意这里比较麻烦的是口味的更新（因为不确定是删除还是添加还是修改）
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("修改成功！");
    }

    /**
     * 启售、停售、批量启售、批量禁售
     * @param ids
     * @return
     */
    @PostMapping("/status/{staId}")
    public R<String> updateStatus(@PathVariable("staId")int status,@RequestParam("ids")List<Long> ids){
        //条件构造器，如果是批量的话，使用in
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(Dish::getId,ids);
        //进行（批量）修改
        List<Dish> list = dishService.list(wrapper);
        list.forEach((item)->item.setStatus(status));
        dishService.updateBatchById(list);
        return R.success((status==1?"已经启售!":"已经停售！"));
    }

    //套餐管理-添加套餐-添加菜品-要根据categoryId获取有哪些菜品+菜品有哪些口味（不能少了口味，否则小程序端的添加购物车会出问题，判断是套餐还是菜品就是根据是否有口味）
//    @GetMapping("/list")
//    public R<List<Dish>> listByCategoryId(Long categoryId,String name){
//        //获取该种类下的所有菜品
//        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq(Dish::getCategoryId,categoryId).like(name!=null,Dish::getName,name);
//        List<Dish> list = dishService.list(wrapper);
//        return R.success(list);
//    }

    //套餐管理-添加套餐-添加菜品-要根据categoryId获取有哪些菜品+菜品有哪些口味（不能少了口味，否则小程序端的添加购物车会出问题，判断是套餐还是菜品就是根据是否有口味）
    @GetMapping("/list")
    public R<List<DishDto>> listByCategoryId(Long categoryId,String name){
        //获取菜品扩展类集合
        List<DishDto> list=new ArrayList<>();

        //获取该种类下的所有菜品
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,categoryId).like(name!=null,Dish::getName,name);
        List<Dish> dishList = dishService.list(wrapper);

        //获取这个菜的口味
        dishList.stream().map((item)->{
            log.info("该种类下的菜品id={}",item.getId());
            DishDto byIdWithFlavor = dishService.getByIdWithFlavor(item.getId());
            list.add(byIdWithFlavor);
            return item;
        }).collect(Collectors.toList());
        System.out.println(list);
        return R.success(list);
    }

}
