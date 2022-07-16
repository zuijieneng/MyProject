package com.edu.zut.entity.dto;

import com.edu.zut.entity.Setmeal;
import com.edu.zut.entity.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 扩展类，一个套餐有多个菜品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SetmealDto extends Setmeal  {
    private List<SetmealDish> setmealDishes=new ArrayList<>(); //套餐下的所有菜品，参数名setmealDishes与前端传来的数组名保持一致,由于前端给的dish内容的id是dishId，参数名只匹配SetmealDish表中的id，不匹配Dish表，因此数组里存储SetmealDish，不存储Dish
    private String categoryName; //前端需要套餐名称，与前端参数名保持一致
}
