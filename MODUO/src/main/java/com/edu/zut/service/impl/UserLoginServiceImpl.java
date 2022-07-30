package com.edu.zut.service.impl;

import com.edu.zut.entity.R;
import com.edu.zut.entity.UserBasic;
import com.edu.zut.entity.UserLogin;
import com.edu.zut.exception.LoginException;
import com.edu.zut.mapper.UserBasicMapper;
import com.edu.zut.mapper.UserLoginMapper;
import com.edu.zut.service.UserLoginService;
import com.edu.zut.util.PhoneMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    public R<UserLogin> loginPhone(String phone, String verify) {
        //防止用户直接输入手机号验证码，不经过发送验证码的手机号的校验
        if(userLoginMapper.findByUphone(phone)==null) throw new LoginException("账号/验证码错误");
        UserLogin userLogin = userLoginMapper.findByUphone(phone);
        //如果验证码错误，说明手机号正确但是可能不是本人的，此时要将点击发送验证码的时候向数据库里的注册的信息删除，避免产生无效数据
        if(!verify.equals(userLogin.getUyzm())){
            return R.error("验证码错误");
        }
        //如果验证码和手机号都争取，那么就登录成功，此时要更新登录时间
        userLogin.setUbeforeLoginTime(userLogin.getUcurrentLoginTime());
        userLogin.setUcurrentLoginTime(new Date());
        userLoginMapper.save(userLogin);
        return R.success(userLogin);
    }

    @Override
    public R<UserLogin> loginPwd(String phone, String pwd) {
        if(!phone.matches("[0-9]{11}")) return R.error("手机号格式不正确");
        UserLogin userLogin = userLoginMapper.findByUphone(phone);
        if(userLogin==null||userLogin.getUpwd()==null||!userLogin.getUpwd().equals(pwd)) return R.error("账号/密码错误");
        //登录成功修改登录时间
        userLogin.setUbeforeLoginTime(userLogin.getUcurrentLoginTime());
        userLogin.setUcurrentLoginTime(new Date());
        userLoginMapper.save(userLogin);
        return R.success(userLogin);
    }

    /**
     * 手机号的格式校验是在点击发送验证码开始校验的
     * @param phone
     * @return
     */
    @Override
    public R<String> sendCode(String phone) {
        if(!phone.matches("[0-9]{11}")) return R.error("手机号格式不正确");
        int randomCode=(int)(Math.random()*1000+2000);
        UserLogin byUphone = userLoginMapper.findByUphone(phone);
        //如果用户不存在，就直接注册
        UserBasic userBasic=null;
        UserLogin userLogin=null;
        //如果用户不存在，但是手机号格式有效，注册，需要操作两个表：基本信息表和登录表
        if (byUphone==null) {
            System.out.println("验证码。。。。"+randomCode);
            PhoneMessage.sendMessage(String.valueOf(randomCode),phone);
            //创建基本信息
            String uname= UUID.randomUUID().toString().replace("-","").substring(0,7);
            userBasic= userBasicMapper.save(new UserBasic(null, uname, null, null, new Date(), null, null, null, 0));
            userLogin= userLoginMapper.save(new UserLogin(null, userBasic.getUid(), phone, null, null, String.valueOf(randomCode), new Date(), null, new Date(), 20));
        }else { //如果用户存在，那么就修改验证码，更新用户登录信息
            if((new Date().getTime()-byUphone.getUyzmTime().getTime())/1000/60<5) return R.error("5分钟之后才能发送验证码");
            System.out.println("验证码。。。。"+randomCode);
            PhoneMessage.sendMessage(String.valueOf(randomCode),phone);
            byUphone.setUyzm(String.valueOf(randomCode));
            byUphone.setUyzmTime(new Date());
            userLogin = userLoginMapper.save(byUphone);
        }
        return R.success("发送成功！");
    }
}
