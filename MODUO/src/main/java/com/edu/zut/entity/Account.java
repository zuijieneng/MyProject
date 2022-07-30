package com.edu.zut.entity;

import io.swagger.annotations.Api;
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
@Table(name = "user_account")
@SuppressWarnings(value = "all")
@Api("账户")
public class Account implements Serializable {
    @Id
    @Column(name = "uacid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer uacid;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户ID")
    private String uid;
    @Column(name = "uaccount_type_id")
    @ApiModelProperty(value = "0储蓄卡，1信用卡")
    private Integer uaccountTypeId;
    @Column(name = "uaccount_bank_id")
    @ApiModelProperty(value = "银行卡编号")
    private Integer uaccountBankId;
    @Column(name = "uaccount_id")
    @ApiModelProperty(value = "卡号")
    private String uaccountId;
    @Column(name = "uaccount_owner")
    @ApiModelProperty(value = "拥有者名字")
    private String uaccountOwner;
    @Column(name = "uaccount_owner_person_id")
    @ApiModelProperty(value = "身份证号")
    private String uaccountOwnerPersonId;
}
