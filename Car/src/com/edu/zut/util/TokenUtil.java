package com.edu.zut.util;

import com.edu.zut.entity.Operation;
import com.edu.zut.entity.Role;
import com.edu.zut.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TokenUtil {
    public static List<String> getUrlPath(User user){
        Role role = user.getRole(); //获取该用户的角色
        List<Operation> operation = role.getOperation(); //获取该用户角色的所有权限
        List<String> url=new ArrayList<>();
        for (Operation ope:operation) {
            String urlPath = ope.getUrlPath(); //获取权限路径
            System.out.println("拥有的权限："+urlPath);
            url.add(urlPath); //添加到数据中去
        }
        return url;
    }
}
