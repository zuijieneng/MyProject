package com.edu.zut.util;

/**
 * 基于ThreadLocal工具类，用户保存和获取当前登录用户id
 * 作用范围：同一个线程内，以线程为作用域
 * 作用线（同一个线程）：过滤器--JsonObejectMapper--MetaHandler
 */
public class BaseThreadLocal {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    /**
     * 设置id
     * @param id
     */
    public static void setUserId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取id
     * @return
     */
    public static Long getUserId(){
        return threadLocal.get();
    }
}
