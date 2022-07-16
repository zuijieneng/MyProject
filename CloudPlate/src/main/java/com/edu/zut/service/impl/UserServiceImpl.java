package com.edu.zut.service.impl;

import com.edu.zut.entity.R;
import com.edu.zut.entity.User;
import com.edu.zut.mapper.UserMapper;
import com.edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer key) {
        return userMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(User row) {
        return userMapper.insert(row);
    }

    @Override
    public User selectByPrimaryKey(Integer key) {
        return selectByPrimaryKey(key);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User row) {
        return userMapper.updateByPrimaryKey(row);
    }

    @Override
    public R<User> checkLogin(String uname, String upwd) {
        //情况一：账号不存在
        if(userMapper.selectByUname(uname)==null) return R.error("用户名错误！");
        //情况二：密码错误
        if(!userMapper.selectByUname(uname).getUpwd().equals(upwd)) return R.error("密码错误！");
        //情况三：账号密码均正确
        User user = userMapper.checkLogin(uname, upwd);
        return R.success(user);
    }
}
