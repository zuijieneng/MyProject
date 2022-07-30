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
@Table(name = "crowd_back")
@SuppressWarnings(value = "all")
@ApiModel("项目回报表")
public class CrowdBack implements Serializable {
    @Id
    @Column(name = "cid")
    @ApiModelProperty(value = "回报主键")
    private String  cid;
    @Column(name = "pbid")
    @ApiModelProperty(value = "项目ID")
    private String  pbid;
    @Column(name = "clevel")
    @ApiModelProperty(value = "回报等级")
    private String  clevel;
    @Column(name = "ctitle")
    @ApiModelProperty(value = "回报标题")
    private String  ctitle;
    @Column(name = "cdescribe")
    @ApiModelProperty(value = "回报描述")
    private String  cdescribe;
    @Column(name = "cpost_way")
    @ApiModelProperty(value = "邮递方式")
    private String  cpostWay;
    @Column(name = "csend_date")
    @ApiModelProperty(value = "发送日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    csendDate;
    @Column(name = "csend_way")
    @ApiModelProperty(value = "发送方式")
    private String  csendWay;
    @Column(name = "cmoney")
    @ApiModelProperty(value = "回报档金额")
    private Float   cmoney;
    @Column(name = "ctype")
    @ApiModelProperty(value = "回报类型")
    private String  ctype;
}
