package com.edu.zut.controller;

import com.edu.zut.entity.CarCheck;
import com.edu.zut.mapper.CarCheckMapper;
import com.edu.zut.service.CarCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("check")
@RequestMapping("/check")
public class CarCheckController {
    @Autowired
    CarCheckService service;
    @Autowired
    CarCheckMapper mapper;
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView checkCar(CarCheck carCheck){
        service.insert(carCheck);
        ModelAndView model=new ModelAndView();
        model.setViewName("/car_check_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/list.action",method =  {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAll(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        pageNumber=pageNumber==null?1:pageNumber;
        System.out.println("pageNumber="+pageNumber);
        PageHelper.startPage(pageNumber, 6);
        List<CarCheck> list = service.selectAll();
        //获取分页之后的数据，导航栏页的信息，分页的数量
        PageInfo<CarCheck> pageInfo=new PageInfo<>(list,5);
        System.out.println("pageInfo的数据："+pageInfo);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("forward:/car_check_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteOne(String carCheckId){
        ModelAndView model=new ModelAndView();
        service.deleteByPrimaryKey(carCheckId);
        model.setViewName("forward:/car_check_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/getCheckId.action",method = {RequestMethod.GET})
    public void getCheckId(String carId, HttpServletResponse resp) throws IOException {
        String checkId = mapper.getCheckIdByCarId(carId);
        resp.getWriter().write(checkId,0,checkId.length());
    }
}
