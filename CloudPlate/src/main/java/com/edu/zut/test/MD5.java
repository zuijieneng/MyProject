package com.edu.zut.test;

import org.springframework.util.DigestUtils;

public class MD5 {
    public static void main(String[] args) {
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()).getClass()); //e10adc3949ba59abbe56e057f20f883e
    }
}
