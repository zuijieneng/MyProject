package com.edu.zut.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login")
@SuppressWarnings(value = "all")
@ApiModel("用户登录")
public class UserLogin implements Serializable {
    @Id
    @Column(name = "ulid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer ulid;
    @Column
    @ApiModelProperty(value = "用户id")
    private String uid;
    @Column
    @ApiModelProperty(value = "用户电话")
    private String uphone;
    @Column
    @ApiModelProperty(value = "用户邮箱")
    private String uemail;
    @Column
    @ApiModelProperty(value = "用户密码")
    private String upwd;
    @Column
    @ApiModelProperty(value = "验证码")
    private String uyzm;
    @Column
    @ApiModelProperty(value = "验证码发送时间")
    private Date uyzmTime;
    @Column
    @ApiModelProperty(value = "上一次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ubeforeLoginTime;
    @Column
    @ApiModelProperty(value = "这次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ucurrentLoginTime;
    @Column
    @ApiModelProperty(value = "用户积分")
    private Integer uscore;
}
