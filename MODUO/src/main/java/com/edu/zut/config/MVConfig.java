package com.edu.zut.config;

import com.edu.zut.interceptor.LoginIncepetor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class MVConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginIncepetor loginIncepetor;

    // 必须添加
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIncepetor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/account")
                .excludePathPatterns("/login/phoneLogin")
                .excludePathPatterns("/login/sendCode")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**"); //不拦截swagger
    }
}
