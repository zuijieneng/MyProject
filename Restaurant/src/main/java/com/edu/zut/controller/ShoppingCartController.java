package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.zut.entity.ShoppingCart;
import com.edu.zut.entity.User;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/shoppingCart")
@Slf4j
@RestController

public class ShoppingCartController {
    @Resource
    private ShoppingCartService service;

    /**
     * 获取购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        List<ShoppingCart> list = service.list();
        return R.success(list);
    }

    /**
     * 添加购物车，前端给的数据：amount,image,name,setmealld
     * 要考虑重复添加的数据行（菜品和口味相同），要修改number
     * 三种情况：
     * 1、如果存在菜名和口味一样的菜品，那就修改number
     * 2、如果存在菜名相同但是口味不同的菜品，那么就重新添加
     * 2、如果不存在菜品，那么就添加
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart, HttpSession session){
        log.info("往购物车添加菜品或者套餐{}",shoppingCart.toString());
        //获取用户id
        User user = (User)session.getAttribute("user");

        //条件构造器查看数据库是否存在该菜品（菜id和口味都一样），如果存在，那么只需添加number数量即可
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();

        //如果是菜品
        if(shoppingCart.getDishId()!=null){
            wrapper.eq(ShoppingCart::getDishFlavor,shoppingCart.getDishFlavor()).eq(ShoppingCart::getDishId,shoppingCart.getDishId());
            ShoppingCart existShop = service.getOne(wrapper);
            //判断是否存在，修改number
            if(existShop!=null){
                existShop.setNumber(existShop.getNumber()+1);
                service.updateById(existShop);
                return R.success("多添加一份成功！");
            }
        }
        //如果是套餐
        if(shoppingCart.getSetmealId()!=null){
            wrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            ShoppingCart existShop = service.getOne(wrapper);
            //判断是否存在，修改number
            if(existShop!=null){
                existShop.setNumber(existShop.getNumber()+1);
                service.updateById(existShop);
                return R.success("多添加一份成功！");
            }
        }
        //如果不存在，那么就添加，有什么添加什么
        shoppingCart.setUserId(user.getId());
        service.save(shoppingCart);
        return R.success("添加成功！");
    }

    /**
     * 清除该用户的购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> cleanAll(HttpSession session){
        User user=(User)session.getAttribute("user");
        //条件构造器
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,user.getId());
        service.remove(wrapper);
        return R.success("清空购物车所有东西了！");
    }

    /**
     * 删除一个，这里考虑number，如果number>1，那么直接修改number，如果number=1，那么就删除
     * @param session
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public R<String> cleanOne(HttpSession session,@RequestBody ShoppingCart shoppingCart){
        log.info("想要清除的购物车数据行{}",shoppingCart.toString());
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();

        //如果是菜品
        if(shoppingCart.getDishId()!=null){
             //select * from shoppingCar where dish_id=shoppingCart.getDishId() and dish_flavor=shoppingCart.getDishFlavor()
             wrapper.eq(ShoppingCart::getDishId, shoppingCart.getDishId()).eq(ShoppingCart::getDishFlavor, shoppingCart.getDishFlavor());
            ShoppingCart one = service.getOne(wrapper);
            //如果存在多个，那么只需数量减一份即可
            if(one.getNumber()>1){
                one.setNumber(one.getNumber()-1);
                service.updateById(one);
                return R.success("删除多余一份菜品！");
            }
            //如果只有一份，那么直接删除
            service.remove(wrapper);
        }
        //如果是套餐，不用考虑口味，只需要考虑数量
        if(shoppingCart.getSetmealId()!=null){
            wrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            ShoppingCart one = service.getOne(wrapper);
            if(one.getNumber()>1){
                one.setNumber(one.getNumber()-1);
                service.updateById(one);
                return R.success("删除一份多余套餐！");
            }
            //如果只有一份，那么直接删除
            service.remove(wrapper);
        }
        return R.success("删除成功！");
    }

}
