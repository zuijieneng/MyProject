package com.edu.zut.controller;

import com.edu.zut.entity.Customer;
import com.edu.zut.mapper.CustomerMapper;
import com.edu.zut.mapper.DictionaryMapper;
import com.edu.zut.service.CustomerService;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller("customer")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;
    @Autowired
    CustomerMapper mapper;
    @Autowired
    DictionaryMapper dictionaryMapper;
    @RequestMapping(value = "/list.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void list(@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //由于需要跳转两次，因此需要使用servletContext
        ServletContext servletContext = req.getServletContext();
        pageNumber=pageNumber==null?1:pageNumber;
        PageHelper.startPage(pageNumber,6);
        List<Customer> list = service.selectAll();
        PageInfo<Customer> pageInfo=new PageInfo<>(list,5);
        System.out.println("pageInfo==="+pageInfo);
        System.out.println("list======="+list);
        //打印关键信息
        System.out.println("当前页面是::"+pageNumber);
        //存入，跳转
        req.getServletContext().setAttribute("list",list);
        req.getServletContext().setAttribute("pageInfo",pageInfo);
        req.getRequestDispatcher("/customer_manager.jsp").forward(req,resp);
    }
    @RequestMapping(value = "/insert.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void insert(@RequestParam("photo")MultipartFile multifile,Customer customer,HttpServletRequest req,HttpServletResponse resp) throws Exception {
        String path=req.getServletContext().getRealPath("/images/customer/")+customer.getCustomerId();
        File file =new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        multifile.transferTo(new File(path,multifile.getOriginalFilename()));
        customer.setCustomerPhoto(multifile.getOriginalFilename());
        service.insert(customer);
        req.getRequestDispatcher("/customer_manager.jsp").forward(req,resp);
    }
    @RequestMapping(value = "/checkId.action",method = {RequestMethod.GET,RequestMethod.POST})
    public void checkId(String customerId,HttpServletResponse resp) throws Exception {
        System.out.println("check!!!!");
        Customer customer = service.selectByPrimaryKey(customerId);
        if(customer!=null){
            resp.getWriter().write("有效ID");
        }else {
            resp.getWriter().write("无效ID");
        }
    }
    @RequestMapping(value = "/delete.action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delete(@RequestParam(value = "cid")String cid, @RequestParam("pageNumber")int pageNumber,HttpServletRequest req,HttpServletResponse resp) throws Exception {
        int i = service.deleteByCid(cid);
        ModelAndView model=new ModelAndView();
        model.addObject("pageNumber",pageNumber);
        model.setViewName("/customer/list.action");
        return  model;
    }
    //获取所有用户id
    @RequestMapping(value = "/getId.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<String> getNames() throws Exception {
        List<String> names = mapper.getAllIds();
        return  names;
    }

    //获取所有省份names
    @RequestMapping(value = "/getProvince.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<String> getProNames() throws Exception {
        List<String> names = dictionaryMapper.getProvinceNames();
        return  names;
    }
    //获取相应省份对应的市
    @RequestMapping(value = "/getCity.action",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<String> geCity(String proName) throws Exception {
        int dicId = dictionaryMapper.getDicIdByName(proName);
        List<String> names = dictionaryMapper.getCityNames(dicId);
        return  names;
    }

}
