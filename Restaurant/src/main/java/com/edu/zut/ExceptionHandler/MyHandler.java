package com.edu.zut.ExceptionHandler;

import com.edu.zut.entity.common.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class}) //专门为了@ExceptionHandler类使用的，拦截注解为RestController和..的类
@ResponseBody
public class MyHandler {

    /**
     * 异常处理方法：SQLIntegrityConstraintViolationException
     * 这里比如重复添加用户相同的员工信息
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) //如果不指定就是拦截所有异常信息
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" "); //使用空格分开，比如异常信息是Duplicate entry ‘zhangsan’ for key 'idx_username'
            String errorMsg = split[2]+"已经存在！";
            return R.error(errorMsg);
        }
        return R.error("未知错误！");
    }

    /**
     * 重载异常处理方法：CategoryException
     * 这里比如重复添加用户相同的员工信息
     * @return
     */
    @ExceptionHandler(CategoryException.class)
    public R<String> exceptionHandler(CategoryException ex){
        return R.error(ex.getMessage());
    }

}
