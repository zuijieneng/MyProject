package com.edu.zut.controller;

import com.edu.zut.entity.User;
import com.edu.zut.mapper.RoleMapper;
import com.edu.zut.mapper.UserRoleMapper;
import com.edu.zut.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @RequestMapping(value = "/list.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        PageHelper.startPage(pageNumber,5);
        List<User> list = service.selectAll();
        PageInfo<User> pageInfo=new PageInfo<>(list,5);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("/user_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void insert(@RequestParam("photo")MultipartFile multipartFile, User user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path=req.getServletContext().getRealPath("/file/");
        multipartFile.transferTo(new File(path+multipartFile.getOriginalFilename()));
        user.setUserPhoto(multipartFile.getOriginalFilename());
        service.insert(user);
        resp.sendRedirect("/user_manager.jsp");
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delete(String userId){
        ModelAndView model=new ModelAndView();
        service.deleteByPrimaryKey(userId);
        model.setViewName("/user_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/update.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void update(@RequestParam("photo")MultipartFile multipartFile,User user,HttpServletRequest req,HttpServletResponse resp) throws Exception {
        int index=multipartFile.getOriginalFilename().indexOf(".");
        String houzhui=multipartFile.getOriginalFilename().substring(index);
        String path=req.getServletContext().getRealPath("/file/")+user.getUserId()+houzhui;
        multipartFile.transferTo(new File(path));
        user.setUserPhoto(multipartFile.getOriginalFilename());
        service.updateByPrimaryKey(user);
        req.getRequestDispatcher("/user_manager.jsp").forward(req,resp);
    }
    @RequestMapping(value = "/getNames.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<String> getNames() throws Exception {
        List<String> names = roleMapper.getAllNames();
        return  names;
    }

    @RequestMapping(value = "/getId.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void getId(String roleName,HttpServletResponse resp) throws Exception {
        String roleId = roleMapper.getId(roleName);
        resp.getWriter().write(roleId);
    }



}
