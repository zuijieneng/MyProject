package com.edu.zut.service.impl;

import com.edu.zut.entity.UserBasic;
import com.edu.zut.mapper.UserBasicMapper;
import com.edu.zut.service.UserBasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    private UserBasicMapper userMapper;

    @Override
    public List<UserBasic> selectAll() {
        List<UserBasic> list=new ArrayList<>();
        Iterator<UserBasic> iterator = userMapper.findAll().iterator();
        iterator.forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserBasic selectById(String id) {
        return userMapper.findById(id).get();
    }

    @Override
    public int save(UserBasic entity) {
        return userMapper.save(entity)!=null?1:0;
    }

    @Override
    public int update(UserBasic entity) {
        return userMapper.save(entity)!=null?1:0;
    }

    @Override
    public int deleteById(String id) {
        userMapper.deleteById(id);
        UserBasic userBasic = userMapper.findById(id).get();
        return userBasic ==null?1:0;
    }



}
