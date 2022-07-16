package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.entity.*;
import com.edu.zut.entity.common.R;
import com.edu.zut.entity.dto.OrderDto;
import com.edu.zut.mapper.AddressBookMapper;
import com.edu.zut.mapper.OrdersMapper;
import com.edu.zut.mapper.UserMapper;
import com.edu.zut.service.OrderDetailService;
import com.edu.zut.service.OrdersService;
import com.edu.zut.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private OrdersMapper mapper;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AddressBookMapper addressBookMapper;
    @Resource
    private ShoppingCartService shoppingCartService;

    @Override
    public void saveWithDetail(Orders orders) {
        //根据address_book_Id获取地址对象、userId（业务层无法通过session获取userid）
        AddressBook addressBook = addressBookMapper.selectById(orders.getAddressBookId());
        Long userId = addressBook.getUserId();
        //根据用户id获取用户信息
        User user = userMapper.selectById(userId);
        //根据用户id获取购物车中的所有菜品
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(wrapper);
        //生成订单
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAddress(addressBook.getDetail());
        orders.setPhone(user.getPhone());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setOrderTime(LocalDateTime.now());
        //计算支付的总金额
        BigDecimal reduce = shoppingCartList.stream().map(ShoppingCart::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        orders.setAmount(reduce);
        mapper.insert(orders);

        //同时将信息插入订单详情表
        List<OrderDetail> list=new ArrayList<>();
        shoppingCartList.stream().map((item)->{
            OrderDetail orderDetail=new OrderDetail();
            BeanUtils.copyProperties(item,orderDetail);
            orderDetail.setId(null); //注意id不能copy
            orderDetail.setOrderId(orders.getId()); //将订单详情表与订单表关联起来
            list.add(orderDetail);
            return item;
        }).collect(Collectors.toList());
        orderDetailService.saveBatch(list);
    }

    /**
     * 逻辑分析：首先前端需要的肯定是一个Page对象，recoreds里存储的有orderDetails、sumNum
     * 返回的对象：OrderDto对象
     * 逻辑过程：
     * @param page
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public R<Page> getWithDetail(int page, int pageSize, Long userId) {
        //创建OrderDto
        List<OrderDto> dtoList=new ArrayList<>();
        //根据userid获取订单信息
        LambdaQueryWrapper<Orders> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId,userId);
        List<Orders> orders = mapper.selectList(wrapper);
        //将所有的订单赋值给Dto中
        orders.stream().map((item)->{
            OrderDto orderDto=new OrderDto();
            BeanUtils.copyProperties(item,orderDto); //已经有了基本类型
            LambdaQueryWrapper<OrderDetail> orderDetailWrapper=new LambdaQueryWrapper<>();
            orderDetailWrapper.eq(OrderDetail::getOrderId,item.getId());
            List<OrderDetail> details = orderDetailService.list(orderDetailWrapper);
            orderDto.setOrderDetails(details);
            orderDto.setSumNum(details.size()); //已经有了
            dtoList.add(orderDto);
            return item;
        }).collect(Collectors.toList());
        Page<OrderDto> pageInfo=new Page<>(page,pageSize);
        pageInfo.setRecords(dtoList);
        return R.success(pageInfo);

    }


}
