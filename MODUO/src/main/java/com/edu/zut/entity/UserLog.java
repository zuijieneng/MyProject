package com.edu.zut.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_log")
@SuppressWarnings(value = "all")
@ApiModel("动态")
public class UserLog implements Serializable {
    @Id
    @Column(name = "uloid")
    @ApiModelProperty(value = "动态主键")
    private String uloid;
    @Column(name = "ulog_time")
    @ApiModelProperty(value = "动态发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ulogTime;
    @Column(name = "ulog_content")
    @ApiModelProperty(value = "动态内容")
    private String ulogContent;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户ID")
    private String uid;

}
