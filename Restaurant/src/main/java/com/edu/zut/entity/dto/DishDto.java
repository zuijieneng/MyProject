package com.edu.zut.entity.dto;

import com.edu.zut.entity.Dish;
import com.edu.zut.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、由于数据不能够对应，比如前端传来的数据除了Dish实体类可以接收之外，还有DishFlavor，因此写一个扩展类来接收所有数据
 * 2、DTO全称为Data Tranfer Object，即数据传输对象，一般用于展示层与服务层之间的数据传输
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DishDto extends Dish {
    private List<DishFlavor> flavors=new ArrayList<>(); //每盘菜的多个口味集合
    private String categoryName; //菜品分类名称，由于Dish表中只有categoryId，前端需要渲染categoryName
    private Integer copies;//暂时不用
}
