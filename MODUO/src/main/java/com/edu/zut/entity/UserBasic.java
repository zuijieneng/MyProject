package com.edu.zut.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_basic")
@SuppressWarnings(value = "all")
@ApiModel("用户基本信息")
public class UserBasic implements Serializable {
    @Id
    @Column(name = "uid")
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @ApiModelProperty(value = "用户ID")
    private String uid;
    @Column(name = "uname")
    @ApiModelProperty(value = "用户姓名")
    private String uname;
    @Column(name = "uphoto")
    @ApiModelProperty(value = "用户头像")
    private String uphoto;
    @Column(name = "usex")
    @ApiModelProperty(value = "用户性别")
    private String usex;
    @Column(name = "ubirth")
    @ApiModelProperty(value = "用户生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ubirth;
    @Column(name = "uaddress")
    @ApiModelProperty(value = "居住地（省市）")
    private String uaddress;
    @Column(name = "unative")
    @ApiModelProperty(value = "家乡")
    private String unative;
    @Column(name = "ushow")
    @ApiModelProperty(value = "简介")
    private String ushow;
    @Column(name = "utype")
    @ApiModelProperty(value = "是否认证")
    private Integer utype;
}
