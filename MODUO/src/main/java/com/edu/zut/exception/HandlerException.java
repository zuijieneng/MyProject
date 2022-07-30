package com.edu.zut.exception;
import com.edu.zut.entity.R;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class, Service.class})
@ResponseBody
public class HandlerException {
    @ExceptionHandler(LoginException.class)
    public R<String> login(LoginException ex){
        return R.error(ex.getMessage());
    }
    @ExceptionHandler(PageException.class)
    public R<String> login(PageException ex){
        return R.error(ex.getMessage());
    }
}

