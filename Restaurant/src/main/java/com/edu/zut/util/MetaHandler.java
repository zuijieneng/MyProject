package com.edu.zut.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元素对象处理器，数据处理，比如日期的插入，MyBatisPlus专用的触发器
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {

    /**
     * 添加时候触发
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段的自动填充===INSERT==="+metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseThreadLocal.getUserId());
        metaObject.setValue("updateUser", BaseThreadLocal.getUserId());
    }

    /**
     * 更新操作
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段的自动填充===UPADTE==="+metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",BaseThreadLocal.getUserId());
    }
}
