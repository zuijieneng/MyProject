package com.edu.zut.test;

import org.springframework.util.DigestUtils;

public class  Demo {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
        System.out.println((int)(Math.random()*1000+2000));
    }
}