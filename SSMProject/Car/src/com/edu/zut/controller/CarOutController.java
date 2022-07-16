package com.edu.zut.controller;

import com.edu.zut.entity.CarOut;
import com.edu.zut.mapper.CarOutMapper;
import com.edu.zut.service.CarOutService;
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

@Controller("carout")
@RequestMapping("/carout")
public class CarOutController {
    @Autowired
    CarOutService service;
    @Autowired
    CarOutMapper mapper;

    @RequestMapping(value = "/getAll.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getAll(Integer pageNumber){
        ModelAndView model=new ModelAndView();
        pageNumber=pageNumber==null?1:pageNumber;
        System.out.println("pageNumber="+pageNumber);
        PageHelper.startPage(pageNumber, 10);
        List<CarOut> list = service.selectAll();
        PageInfo<CarOut> pageInfo=new PageInfo<CarOut>(list,5);
        System.out.println("pageInfo的数据："+pageInfo);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("forward:/car_out_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insert(CarOut carOut){
        service.insert(carOut);
        ModelAndView model=new ModelAndView();
        model.setViewName("forward:/car_out_manager.jsp");
        return model;
    }
    @RequestMapping(value = "/getCheckId.action",method = {RequestMethod.GET})
    public void getCheckId(String carId, HttpServletResponse resp) throws IOException {
        String checkId = mapper.getcarOutIdByCarId(carId);
        resp.getWriter().write(checkId,0,checkId.length());
    }
}
