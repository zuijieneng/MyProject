package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zut.entity.Orders;
import com.edu.zut.entity.User;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    private OrdersService service;

    /**
     * （有问题！！！！！是订单明细，不是订单，未改）分页+模糊查询+下单时间,注意时间用字符串接收
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String number, String beginTime,String endTime){
        //分页构造器
        Page<Orders> pageInfo=new Page<>(page,pageSize);
        //条件构造器:模糊查询like，下单时间小于endTime而大于beginTime
        LambdaQueryWrapper<Orders> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(number!=null,Orders::getNumber,number).le(endTime!=null,Orders::getOrderTime,endTime).ge(beginTime!=null,Orders::getOrderTime,beginTime);
        service.page(pageInfo, wrapper); //结果又返回给pageInfo了
        return R.success(pageInfo);
    }

    /**
     * 生成订单，将地址信息添加到数据库中，前端给的参数：addressBookId、payMethod、remark
     * 数剧添加到订单表中，同时订单中所有菜品添加到订单关系表中
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> generateOrder(@RequestBody Orders orders){
        System.out.println(orders.toString());
        service.saveWithDetail(orders);
        return R.success("生成订单成功！");
    }

    /**
     * 订单结算页面：订单时间，状态，共x件商品
     * 返回的是扩展类（从前端页面可以看出）
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> pageOrder(int page, int pageSize, HttpSession session){
        User user = (User)session.getAttribute("user");
        R<Page> pageInfo = service.getWithDetail(page, pageSize,user.getId());
        return pageInfo;
    }

}
