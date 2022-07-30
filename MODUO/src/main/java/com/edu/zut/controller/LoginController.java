package com.edu.zut.controller;

import com.edu.zut.entity.R;
import com.edu.zut.entity.UserBasic;
import com.edu.zut.entity.UserLogin;
import com.edu.zut.mapper.UserLoginMapper;
import com.edu.zut.service.UserBasicService;
import com.edu.zut.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
@Api(tags ="登录")
public class LoginController {
    @Resource
    private UserLoginService userLoginService;
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private UserBasicService userBasicService;

    @GetMapping("/phoneLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "verify",value = "验证码", dataTypeClass = String.class,required = true)
    })
    @ApiOperation("手机号登录或注册")
    public R<UserLogin> phoneLogin(String phone, String verify, HttpSession session){
        R<UserLogin> userLoginR = userLoginService.loginPhone(phone, verify);
        //存储用户的id
        if(userLoginR.getCode()==200){
            UserBasic userBasic = userBasicService.selectById(userLoginR.getData().getUid());
            session.setAttribute("user",userBasic);
        }
        return userLoginR;
    }

    @GetMapping("/updatePwd")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "输入手机号",dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pwd",value = "输入密码",dataTypeClass = String.class,required = true)
    })
    public R<String> updatePwd(String phone,String pwd,HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
        if(!userLoginMapper.findByUphone(phone).getUid().equals(user.getUid())||userLoginMapper.findByUphone(phone)==null) return R.error("只能修改自己的账号密码");
        if(pwd==null) R.error("密码不能为空");
        UserLogin byUphone = userLoginMapper.findByUphone(phone);
        byUphone.setUpwd(pwd);
        userLoginMapper.save(byUphone);
        return R.success("修改成功！");
    }

    @GetMapping("/account")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号，可以使用15290285123", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pwd",value = "密码，可以使用12", dataTypeClass = String.class,required = true)
    })
    @ApiOperation("账号密码登录")
    public R<UserLogin> pwdLogin(String phone,String pwd,HttpSession session){
        R<UserLogin> userLoginR = userLoginService.loginPwd(phone, pwd);
        //存储用户
        if(userLoginR.getCode()==200) {
            UserBasic userBasic = userBasicService.selectById(userLoginR.getData().getUid());
            session.setAttribute("user", userBasic);
        }
        return userLoginR;
    }

    @GetMapping("/sendCode")
    @ApiOperation("发送验证码")
    @ApiImplicitParam(name = "phone",value = "输入手机号，发送验证码",dataTypeClass = String.class,required = true)
    public R<String> sendCode(@RequestParam("phone") String phone){
        R<String> stringR = userLoginService.sendCode(phone);
        return stringR;
    }

}
