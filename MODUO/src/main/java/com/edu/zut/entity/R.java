package com.edu.zut.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel("响应结果")
public class R<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Integer code; //编码：1成功，0和其它数字为失败
    @ApiModelProperty(value = "错误信息")
    private String msg; //错误信息
    @ApiModelProperty(value = "响应结果")
    private T data; //数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 500;
        return r;
    }

}