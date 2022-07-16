package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.zut.entity.User;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 发送短信没有使用短信，只是随机生成了随机数
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送验证码
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if(phone==""||phone.equals("")||phone==null){
            return R.error("请输入手机号！");
        }
        //随机生成验证码
        int yzm=(int)(Math.random()*1000+2000);
        log.info("用户登录，发送验证码{}",yzm);
        session.setAttribute("yzm",yzm);
        return R.success("已经发送！请等待");
    }

    /**
     * 客户端登录
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        //检查手机号是否正确
        String phone=(String)map.get("phone");
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(phone!=null,User::getPhone,phone);
        User user = userService.getOne(wrapper);
        if(user==null) return R.error("手机号不存在！");
        //获取验证码
        int yzm =(int) session.getAttribute("yzm");
        int code =Integer.parseInt((String) map.get("code"));
        //检查验证码是否正确
        if (code==yzm) {
            session.setAttribute("user",user);
            return R.success(user);
        }
        return R.error("验证码不正确！");
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
