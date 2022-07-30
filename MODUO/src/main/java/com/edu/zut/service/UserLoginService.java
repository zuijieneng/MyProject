package com.edu.zut.service;


import com.edu.zut.entity.R;
import com.edu.zut.entity.UserLogin;

public interface UserLoginService {
    public R<UserLogin> loginPhone(String phone, String verify);
    public R<UserLogin> loginPwd(String phone, String pwd);
    public R<String> sendCode(String phone);
}
