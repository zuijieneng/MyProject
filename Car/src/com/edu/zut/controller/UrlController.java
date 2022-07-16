package com.edu.zut.controller;

import com.edu.zut.entity.Operation;
import com.edu.zut.mapper.OperationMapper;
import com.edu.zut.service.OperationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("url")
@RequestMapping("/operation")
public class UrlController {
    @Autowired
    OperationMapper mapper;
    @Autowired
    OperationService service;

    @RequestMapping(value = "/list.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        PageHelper.startPage(pageNumber,8);
        List<Operation> operations = service.selectAll();
        PageInfo<Operation> pageInfo=new PageInfo<>(operations,6);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("/url_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insert(Operation operation) throws IOException {
        ModelAndView model=new ModelAndView();
        service.insert(operation);
        model.setViewName("/url_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delete(String urlId){
        ModelAndView model=new ModelAndView();
        service.deleteByPrimaryKey(urlId);
        model.setViewName("/url_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/update.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView update(Operation operation) throws Exception {
        ModelAndView model=new ModelAndView();
        service.updateByPrimaryKey(operation);
        model.setViewName("/url_manager.jsp");
        return  model;
    }

    @RequestMapping(value = "/getNames.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<String> getNames() throws Exception {
        List<String> names = mapper.getAllOperationNames();
        return  names;
    }

    @RequestMapping(value = "/getId.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void getId(String urlName,HttpServletResponse resp) throws Exception {
        String id = mapper.getId(urlName);
        resp.getWriter().write(id);
    }


}
