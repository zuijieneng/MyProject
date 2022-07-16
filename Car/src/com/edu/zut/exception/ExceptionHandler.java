package com.edu.zut.exception;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) {
        //控制台打印
        System.out.println("ExceptionHandler拦截的异常路径------"+req.getServletPath()+"捕获的异常信息是："+e.getMessage());
        //设置跳转的
        ModelAndView model=new ModelAndView();
        //判断异常种类，跳转至不同页面
        if(e instanceof LoginException){
            model.addObject("message",e.getMessage());
            model.setViewName("/login.jsp");
        }
        if(e instanceof UpdatePwdException){
            model.addObject("mainPage","user_update_pwd.jsp");
            model.addObject("pageName","修改密码");
            model.addObject("message",e.getMessage());
            model.setViewName("/index.jsp");
        }
        if(e instanceof  CarException){
            model.addObject("carmessage",e.getMessage());
            model.setViewName("/car_manager.jsp");
        }
        if(e instanceof  CarCheckException){
            model.addObject("checkmessage",e.getMessage());
            model.setViewName("/car_check_manager.jsp");
        }
        if(e instanceof CarOutException){ //正在出租中，跳转到入库页面
            model.addObject("caroutmessage",e.getMessage());
            model.setViewName("/car_In_insert.jsp");
        }
        if(e instanceof  CarInException){ //入库已经存在，跳转到出租页面出租
            model.addObject("carinmessage",e.getMessage());
            model.setViewName("/car_out_insert.jsp");
        }
        if(e instanceof  DepartmentException){
            model.addObject("departmessage",e.getMessage());
            model.setViewName("/department_option.jsp");
        }
        if(e instanceof  PermissionExceptoin){
            model.addObject("permissionerror",e.getMessage());
            model.setViewName("/welcome.jsp");
        }
        if(e instanceof  OperationException){
            model.addObject("operationerror",e.getMessage());
            model.setViewName("/url_option.jsp");
        }
        return  model;

    }
}
