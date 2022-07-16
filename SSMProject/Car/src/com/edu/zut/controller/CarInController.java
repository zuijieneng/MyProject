package com.edu.zut.controller;

import com.edu.zut.entity.CarIn;
import com.edu.zut.service.CarInService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("carin")
@RequestMapping("/carin")
public class CarInController {
    @Autowired
    CarInService service;
    @RequestMapping(value = "/getAll.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAll(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        pageNumber=pageNumber==null?1:pageNumber;
        System.out.println("pageNumber="+pageNumber);
        PageHelper.startPage(pageNumber, 10);
        List<CarIn> list=service.selectAll();
        PageInfo<CarIn> pageInfo=new PageInfo<CarIn>(list,5);
        System.out.println("pageInfo的数据："+pageInfo);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("forward:/car_in_manager.jsp");
        return model;
    }

    /**
     * 只有车检过且出租的汽车才能入库
     * @param carIn
     * @return
     */
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insert(CarIn carIn){
        ModelAndView model=new ModelAndView();
        service.insert(carIn);
        model.setViewName("forward:/car_in_manager.jsp");
        return model;
    }
}
