package com.edu.zut.inteceptor;

import com.edu.zut.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class FrontLoginInceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user =(User) request.getSession().getAttribute("user");
        if(user==null) {
            log.info("进入小程序之前请先登录账号！");
            response.sendRedirect("/front/page/login.html");
            return false;
        }
        return true;
    }
}
