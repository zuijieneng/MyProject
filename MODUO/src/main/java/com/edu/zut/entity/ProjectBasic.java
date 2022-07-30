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
@Table(name = "project_basic")
@SuppressWarnings(value = "all")
@ApiModel("项目基本信息")
public class ProjectBasic implements Serializable {
    @Id
    @Column(name = "pbid")
    @ApiModelProperty(value = "项目基本信息主键ID")
    private String pbid;
    @Column(name = "pname")
    @ApiModelProperty(value = "项目标题")
    private String pname;
    @Column(name = "ptype")
    @ApiModelProperty(value = "项目类别编码，字典表中类型，比如动漫、潮玩...")
    private Integer ptype;
    @Column(name = "pdevelop_type")
    @ApiModelProperty(value = "众筹/创意/预热/众筹中/众筹成功")
    private String pdevelopType;
    @Column(name = "pshow_first")
    @ApiModelProperty(value = "项目简称")
    private String pshowFirst;
    @Column(name = "pcontent")
    @ApiModelProperty(value = "项目具体内容")
    private String pcontent;
    @Column(name = "uid")
    @ApiModelProperty(value = "项目发起者")
    private String uid;
    @Column(name = "ptime")
    @ApiModelProperty(value = "项目发起时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ptime;
    @Column(name = "pstatus")
    @ApiModelProperty(value = "1审核通过2未通过")
    private Integer pstatus;
}
