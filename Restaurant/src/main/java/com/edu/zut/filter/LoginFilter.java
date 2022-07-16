package com.edu.zut.filter;

import com.alibaba.fastjson.JSON;
import com.edu.zut.entity.common.R;
import com.edu.zut.util.BaseThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginFilter",urlPatterns = "/**")
@Component
public class LoginFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    /**
     * 获取URL，判断是否需要处理，不需要放行，需要判断登录状态，已经登陆放行，未登录返回未登录结果
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String requestURL = request.getRequestURI();
        //存储无须拦截的请求，比如登录等等
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**"
        };
        boolean check = check(urls, requestURL);
        if (check){ //如果无需拦截的路径，直接放行
            log.info("因为是静态资源，本次请求{"+requestURL+"}无需拦截");
            filterChain.doFilter(request,response);
            return;
        }
        //需要拦截判断登录状态
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户"+request.getSession().getAttribute("employee")+"你好!"+"因为登录过，本次请求{"+requestURL+"}无需拦截");
            //将用户的id存入ThreadLocal中以便于MetaHandler使用
            Long employeeID =(Long) request.getSession().getAttribute("employee");
            BaseThreadLocal.setUserId(employeeID);
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        log.info("本次请求{"+requestURL+"}被拦截，因为未登录");
        //未登录响应数据，错误信息为NOTLOGIN，前端也有拦截器会判断跳转
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 封装一个方法来判断是否是需要拦截的路径
     * @param requestURI
     * @param urls
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI); //匹配成功返回true，匹配成功说明不用拦截
            if (match) return true; //放行
        }
        return false;
    }
}
