package com.edu.zut.controller;

import com.edu.zut.entity.Role;
import com.edu.zut.entity.RoleUrl;
import com.edu.zut.service.RoleService;
import com.edu.zut.service.RoleUrlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller("role")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService service;
    @Autowired
    RoleUrlService roleUrlService;

    @RequestMapping(value = "/list.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        PageHelper.startPage(pageNumber,5);
        List<Role> list = service.selectAll();
        PageInfo<Role> pageInfo=new PageInfo<>(list,5);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("/role_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insert(Role role) throws IOException {
        ModelAndView model=new ModelAndView();
        service.insert(role);
        model.setViewName("/role_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delete(String roleId){
        ModelAndView model=new ModelAndView();
        service.deleteByPrimaryKey(roleId);
        model.setViewName("/role_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/update.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView update(Role role,String urlId) throws Exception {
        ModelAndView model=new ModelAndView();
        service.updateByPrimaryKey(role);
        if(urlId!=null||!urlId.equals("")){ //如果是修改表，设置权限了，返回一个权限ID
            RoleUrl roleUrl=new RoleUrl(role.getRoleId(),urlId);
            roleUrlService.insert(roleUrl);  //添加权限信息
        }
        model.setViewName("/role_manager.jsp");
        return  model;
    }

}
