package com.edu.zut.service.impl;

import com.edu.zut.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddressBookServiceImpl {
    @Resource
    private AddressBookMapper addressBookMapper;
}
