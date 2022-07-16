package com.edu.zut.controller;

import com.edu.zut.entity.Department;
import com.edu.zut.mapper.DepartmentMapper;
import com.edu.zut.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("department")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService service;
    @Autowired
    DepartmentMapper mapper;
    @RequestMapping(value = "/list.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAll(@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        ModelAndView model=new ModelAndView();
        PageHelper.startPage(pageNumber,6);
        List<Department> list = service.selectAll();
        PageInfo<Department> pageInfo=new PageInfo<>(list,5);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("/department_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insert(Department department){
        ModelAndView model=new ModelAndView();
        service.insert(department);
        model.setViewName("/department_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delete(String id,String current){
        ModelAndView model=new ModelAndView();
        service.deleteByPrimaryKey(id);
        model.addObject("pageNumber",current);
        model.setViewName("/department_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/update.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView update(Department department){
        ModelAndView model=new ModelAndView();
        service.updateByPrimaryKey(department);
        model.setViewName("/department_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/getNames.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List getDepartNames(HttpServletResponse resp){
        List<String> list = mapper.getDepartMentsName();
        return list;
    }
    @RequestMapping(value = "/getId.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void getId(String departmentName,HttpServletResponse resp) throws IOException {
        String idByName = mapper.getIdByName(departmentName);
        resp.getWriter().write(idByName);
    }
}
