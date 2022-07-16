package com.edu.zut.util;

import com.edu.zut.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_config.xml","classpath:springmvc_config.xml","classpath:mybatis_config.xml"})
public class CurdTest {
    @Autowired
    private FileService fileService;
    @Test
    public void updaet(){
        System.out.println(fileService.selectByFmd5("82b5bf3d2e04f04f332a83831d7dcbe6"));

    }
}
