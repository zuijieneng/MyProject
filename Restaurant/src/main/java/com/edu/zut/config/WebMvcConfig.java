package com.edu.zut.config;

import com.edu.zut.inteceptor.FrontLoginInceptor;
import com.edu.zut.util.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 配置类，以前都是使用web.xml进行配置过滤器，拦截器等
 */
@Slf4j
@Configuration //少了会识别不出来
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private FrontLoginInceptor frontLoginInceptor; //配置类配置拦截器

    /**
     * 设置静态资源映射，当设置拦截器拦截所有路径“/”，dispatchservlet不会拦截静态资源，比SpringMvc的静态资源设置更加高效
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行资源的映射...............");
        //目的是：防止有些link引用的css导致了引用不到，这里直接映射地址栏的url
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展SpringMVC的消息转换器，一启动就会调用
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("开始进行消息转换器的扩展...............");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将java对象转换为Json
        converter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到SpringMvc框架的转换器集合中，设置索引相当于设置优先级
        converters.add(0,converter);
        super.extendMessageConverters(converters);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(frontLoginInceptor)
                .addPathPatterns("/front/**")
                .excludePathPatterns("/front/page/login.html"); //不拦截登录页面
    }
}
