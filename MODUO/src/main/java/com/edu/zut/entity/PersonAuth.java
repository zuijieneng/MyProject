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

/**
 * 个人认证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_person_auth")
@SuppressWarnings(value = "all")
@ApiModel("个人认证")
public class PersonAuth implements Serializable {
    @Id
    @Column(name = "upaid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "个人认证主键")
    private Integer upaid;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户id")
    private String uid;
    @Column(name = "ureal_name")
    @ApiModelProperty(value = "用户真实姓名")
    private String urealName;
    @Column(name = "upaper_type_id")
    @ApiModelProperty(value = "证件类型编号")
    private Integer upaperTypeId;
    @Column(name = "upaper_id")
    @ApiModelProperty(value = "证件编号")
    private String upaperId;
    @Column(name = "upaper_front_photo")
    @ApiModelProperty(value = "证件照前")
    private String upaperFrontPhoto;
    @Column(name = "upaper_back_photo")
    @ApiModelProperty(value = "证件照后")
    private String upaperBackPhoto;
    @Column(name = "upaper_start")
    @ApiModelProperty(value = "证件起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upaperStart;
    @Column(name = "upaper_end")
    @ApiModelProperty(value = "证件结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upaperEnd;
    @Column(name = "uaddress")
    @ApiModelProperty(value = "联系地址")
    private String uaddress;
    @Column(name = "umail")
    @ApiModelProperty(value = "用户邮箱")
    private String uemail;

}
