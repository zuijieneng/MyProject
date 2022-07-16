package com.edu.zut.controller;

import com.edu.zut.entity.User;
import com.edu.zut.exception.LoginException;
import com.edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller("login")
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService service;

    /**
     * 登录验证
     * @param userName
     * @param userPwd
     * @param userYzm
     * @param req
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/login.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView userlogin(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd, @RequestParam("userYzm") String userYzm, HttpServletRequest req) throws ServletException, IOException {
        ModelAndView mode=new ModelAndView();
        String yzm =(String)req.getSession().getAttribute("yzm");
        System.out.println("登录传来的参数结果------------"+userName+userPwd+userYzm+"yzm:"+yzm);
        User login = service.login(userName, userPwd);
        if(!yzm.equals(userYzm)){
            throw new LoginException("验证码错误！");
        }else if(login==null){
            throw new LoginException("该用户不存在！");
        }
        req.getSession().setAttribute("user",login);
        mode.setViewName("/index.jsp");
        return mode;
    }

    /**
     * 修改账号密码
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/updatePwd.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView updatePwd(@RequestParam("userName") String name, @RequestParam("userPwd") String pwd,HttpServletRequest req){
        System.out.println("您已经进入修改密码控制层......");
        User user=(User)req.getSession().getAttribute("user");
        int i = service.updatePwd(name,pwd,user.getUserId());
        ModelAndView model=new ModelAndView();
        if(i==1) {
            model.setViewName("/login.jsp");
        }
        return model;
    }

    /**
     * 登出
     * @param req
     * @return
     */
    @RequestMapping("/logout.action")
    public ModelAndView logout(HttpServletRequest req){
        ModelAndView model=new ModelAndView();
        System.out.println("登出ing.......");
        req.getSession().invalidate();
        model.setViewName("/login.jsp");
        return model;
    }

    /**
     * 验证码
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "/ajax/getYzm.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void yzm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str="1234567834238791";
        //设置一个缓冲区BufferImage，宽，高，各种类型
        BufferedImage bin =new BufferedImage(70,30,BufferedImage.TYPE_INT_RGB);
        //设置画布
        Graphics2D graphics =(Graphics2D) bin.getGraphics();
        //画桌布
        graphics.setColor(Color.WHITE); //设置背景
        graphics.fillRect(0,0,70,30); //坐标，大小
        //写字
        String ymz="";
        for(int i=0;i<4;i++){
            ymz+=str.charAt((int)(Math.random()*str.length()));
        }
        //设置文字大小
        Font font=new Font(null,Font.BOLD,12);
        graphics.setFont(font);
        //设置文字颜色，并写在画布中
        for(int i=0;i<4;i++){
            graphics.setColor(Color.black);
            graphics.drawString(ymz.charAt(i)+"",i*15+4,23);
        }
        req.getSession().setAttribute("yzm",ymz);
        ImageIO.write(bin,"JPG",resp.getOutputStream());
    }


}
