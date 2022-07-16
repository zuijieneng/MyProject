package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.entity.ShoppingCart;
import com.edu.zut.mapper.ShoppingCartMapper;
import com.edu.zut.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper mapper;


}
