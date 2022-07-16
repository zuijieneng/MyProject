package com.edu.zut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.edu.zut.mapper")
@EnableTransactionManagement //事务管理，比如DishServiceImpl使用到了
//@PropertySource("xx.properties")
//@ServletComponentScan(basePackages = "com.edu.zut.filter.LoginFilter")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        System.out.println("SpringBoot项目已经启动...");
    }
}
