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
@Table(name = "user_company_auth")
@SuppressWarnings(value = "all")
@ApiModel("企业认证")
public class CompanyAuth implements Serializable {
    @Id
    @Column(name = "ucaid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "企业认证主键")
    private Integer ucaid;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户id")
    private String uid;
    @Column(name = "ucompany_name")
    @ApiModelProperty(value = "公司名称")
    private String ucompanyName;
    @Column(name = "ucompany_id")
    @ApiModelProperty(value = "社会信用代码")
    private String ucompanyId;
    @Column(name = "ucompany_person")
    @ApiModelProperty(value = "法人")
    private String ucompanyPerson;
    @Column(name = "ucompany_person_paper_type_id")
    @ApiModelProperty(value = "法人证件类型编号")
    private Integer ucompanyPersonPaperTypeId;
    @Column(name = "ucompany_person_paper_id")
    @ApiModelProperty(value = "法人证件号")
    private String ucompanyPersonPaperId;
    @Column(name = "ucompany_person_paper_front_photo")
    @ApiModelProperty(value = "法人证件前照")
    private String ucompanyPersonFrontPhoto;
    @Column(name = "ucompany_person_paper_back_photo")
    @ApiModelProperty(value = "法人证件后照")
    private String ucompanyPersonBackPhoto;
    @Column(name = "ucompany_person_paper_start")
    @ApiModelProperty(value = "法人证件起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String ucompanyPersonPaperStart;
    @Column(name = "ucompany_person_paper_end")
    @ApiModelProperty(value = "法人证件结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String ucompanyPersonPaperEnd;
    @Column(name = "ucompany_license_photo")
    @ApiModelProperty(value = "营业执照")
    private String ucompanyLicensePhoto;
    @Column(name = "ucompany_license_start")
    @ApiModelProperty(value = "营业执照起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ucompanyLicenseStart;
    @Column(name = "ucompany_license_end")
    @ApiModelProperty(value = "营业执照结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ucompanyLicenseEnd;
    @Column(name = "ucompany_show")
    @ApiModelProperty(value = "公司介绍")
    private String ucompanyShow;
    @Column(name = "ucompany_address")
    @ApiModelProperty(value = "公司地址")
    private String ucompanyAddress;
    @Column(name = "ucompany_phone")
    @ApiModelProperty(value = "公司电话")
    private String ucompanyPhone;
    @Column(name = "ucompany_email")
    @ApiModelProperty(value = "公司邮箱")
    private String ucompanyEmail;
    @Column(name = "ucompany_url")
    @ApiModelProperty(value = "公司官网")
    private String ucompanyUrl;
}
