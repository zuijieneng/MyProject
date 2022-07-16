package com.edu.zut.controller;

import com.edu.zut.entity.R;
import com.edu.zut.entity.User;
import com.edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 登录+验证码
     * @param uname
     * @param upwd
     * @return
     */
    @GetMapping("/login")
    public R<User> login(String uname, String upwd, String uyzm, HttpServletRequest req){
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //防止用户重复提交表单，删除session里的验证码，null
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        //查询结果
        R<User> result = userService.checkLogin(uname, upwd);
        //如果没有达到条件，返回响应的错误提示
        if(result.getCode()==0) return R.error(result.getMsg());
        //如果达到了条件，但如果session存储的验证码为null，说明用户重复提交
        if(token==null) return R.error("验证码过期，请点击刷新！");
        //如果达到了条件，但是验证码不正确，返回提示信息
        if(!token.equals(uyzm)) return R.error("验证码不正确！");
        //如果达到了条件,验证码正确，返回数据即可
        req.getSession().setAttribute("user",result.getData());
        System.out.println(result);
        return result;
    }

    @GetMapping("/logout")
    public R<String> logout(HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return R.success("登出成功！");
    }
}
