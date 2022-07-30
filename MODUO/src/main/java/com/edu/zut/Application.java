package com.edu.zut;

import com.edu.zut.entity.dto.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({UserLogDto.class, UserBasicDto.class, TopicDto.class, ProjectBasicDto.class, ProjectTeamDto.class}) //扫描扩展类，因为无法自动创建Dto的bean，有用
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("SpringBoot启动中...");
    }

}
