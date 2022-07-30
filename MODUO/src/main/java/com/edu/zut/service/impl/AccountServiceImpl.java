package com.edu.zut.service.impl;

import com.edu.zut.mapper.AccountMapper;
import com.edu.zut.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

}
