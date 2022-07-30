package com.edu.zut.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_address")
@SuppressWarnings(value = "all")
@ApiModel("地址簿")
public class AddressBook implements Serializable {
    @Id
    @Column(name = "uaid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "地址簿主键")
    private Integer uaid;
    @Column(name = "uaddress_name")
    @ApiModelProperty(value = "为该地址起个别名")
    private String uaddressName;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户id")
    private String uid;
    @Column(name = "uaddress_receiver")
    @ApiModelProperty(value = "收货人姓名")
    private String uaddressReceiver;
    @Column(name = "uaddress_province")
    @ApiModelProperty(value = "省份")
    private String uaddressProvince;
    @Column(name = "city_text")
    @ApiModelProperty(value = "城市")
    private String cityText;
    @Column(name = "uaddress_detail")
    @ApiModelProperty(value = "详细地址")
    private String uaddressDetail;
    @Column(name = "uaddress_email")
    @ApiModelProperty(value = "邮箱")
    private String uaddressEmail;
    @Column(name = "uaddress_phone")
    @ApiModelProperty(value = "电话")
    private String uaddressPhone;
    @Column(name = "uaddress_postal_code")
    @ApiModelProperty(value = "邮编")
    private String uaddressPostalCode;
    @Column(name = "is_default")
    @ApiModelProperty(value = "是否是默认地址")
    private Integer idDefault;
    @Column(name = "province_id")
    @ApiModelProperty(value = "省份类型ID")
    private Integer provinceId;
    @Column(name = "city_id")
    @ApiModelProperty(value = "城市类型ID")
    private Integer cityId;

}
