package com.edu.zut.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generate {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite=true;
        File configFile=new File("F:\\Java_ZhiYou\\Project\\CloudPlate\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp=new ConfigurationParser(warnings);
        Configuration configuration=cp.parseConfiguration(configFile);
        DefaultShellCallback callback=new DefaultShellCallback(overwrite);
        MyBatisGenerator mgenerate=new MyBatisGenerator(configuration,callback,warnings);
        mgenerate.generate(null);
    }
}
