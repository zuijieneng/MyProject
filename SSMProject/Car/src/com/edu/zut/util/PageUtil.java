package com.edu.zut.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static Map pageInfo(List list){
        PageInfo pageInfo=new PageInfo(list);
        List objs = pageInfo.getList();
        Map<String,Object> map=new HashMap<>();
        map.put("list",objs);
        map.put("pageInfo",pageInfo);
        return map;
    }
}
