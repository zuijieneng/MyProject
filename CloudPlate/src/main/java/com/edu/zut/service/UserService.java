package com.edu.zut.service;


import com.edu.zut.entity.R;
import com.edu.zut.entity.User;

public interface UserService extends BaseService<User,Integer> {
    R<User> checkLogin(String uname, String upwd);
}
