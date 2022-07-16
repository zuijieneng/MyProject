package com.edu.zut.controller;

import com.edu.zut.entity.Car;
import com.edu.zut.service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller("car")
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService service;

	/**
	 * 获取汽车详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value= {"/getOne/{car.carId}/detail.action"},method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getOne(@PathVariable("car.carId") String id) {
		Car car = service.selectByPrimaryKey(id);
		ModelAndView model=new ModelAndView();
		model.addObject("car",car);
		model.setViewName("/car_detail.jsp");
		return model;
	}

	/**
	 * 添加汽车信息
	 * @param photo
	 * @param car
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping(value = "/addOne.action",method = {RequestMethod.GET,RequestMethod.POST})
	public void add(@RequestParam("photo")MultipartFile photo, Car car, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("car:-----------------"+car+"\n"+"车图片的名称--------"+photo.getOriginalFilename());
		String path=req.getServletContext().getRealPath("/file");
		File file=new File(path,photo.getOriginalFilename());
		photo.transferTo(file);
		car.setCarPhoto(photo.getOriginalFilename());
		service.insert(car);
		resp.sendRedirect("/car_manager.jsp");
	}

	/**
	 * 删除汽车信息
	 * @param id
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value= {"/deleteOne/{car.carId}.action"},method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteOne(@PathVariable("car.carId") String id,int pageNumber) {
		service.deleteByPrimaryKey(id);
		ModelAndView model=new ModelAndView();
		model.addObject("pageNumber",pageNumber);
		model.setViewName("/car_manager.jsp");
		return model;
	}

	/**
	 * 修改汽车信息
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/updateOne.action",method ={RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView updateOne(@RequestParam("photo")MultipartFile photo, Car car,Integer pageNumber,HttpServletRequest req) throws Exception {
		System.out.println(photo+"---"+photo.getOriginalFilename());
		if(photo.getOriginalFilename()!=null||!photo.getOriginalFilename().equals("")){ //如果上传照片，如果没有上传，仍旧是原来的照片
			String path=req.getServletContext().getRealPath("/file");
			File file=new File(path,photo.getOriginalFilename());
			photo.transferTo(file);
			car.setCarPhoto(photo.getOriginalFilename());
		}
		service.updateByPrimaryKey(car);
		ModelAndView model=new ModelAndView();
		model.addObject("pageNumber",pageNumber);
		model.setViewName("forward:/car_manager.jsp");
		return model;
	}

	/**
	 * 获取所有以及分页
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value= {"/getAllFenye.action"},method={RequestMethod.GET})
	public ModelAndView getAll(Integer pageNumber) {
		ModelAndView model=new ModelAndView();
		pageNumber=pageNumber==null?1:pageNumber;
		System.out.println("pageNumber="+pageNumber);
        PageHelper.startPage(pageNumber, 6);
        List<Car> list = service.selectAll();
        //获取分页之后的数据，导航栏页的信息，分页的数量
        PageInfo<Car> pageInfo=new PageInfo<>(list,5);
        System.out.println("pageInfo的数据："+pageInfo);
        model.addObject("pageInfo",pageInfo);
        model.setViewName("forward:/car_manager.jsp");
        return model;
	}
	/**
	 * 汽车业务查询
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value= {"/getAll.action"},method={RequestMethod.GET})
	public ModelAndView getService(Integer pageNumber) {
		ModelAndView model=new ModelAndView();
		pageNumber=pageNumber==null?1:pageNumber;
		System.out.println("pageNumber="+pageNumber);
		PageHelper.startPage(pageNumber, 6);
		List<Car> list = service.selectAll();
		PageInfo<Car> pageInfo=new PageInfo<>(list,5);
		model.addObject("pageInfo",pageInfo);
		model.setViewName("forward:/car_service.jsp");
		return model;
	}
	
	
	
}
