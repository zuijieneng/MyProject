package com.edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.zut.entity.User;
import com.edu.zut.mapper.UserMapper;
import com.edu.zut.service.UserService;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
