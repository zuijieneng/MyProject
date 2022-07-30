package com.edu.zut.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_crowd")
@SuppressWarnings(value = "all")
@ApiModel("众筹项目基本表")
public class ProjectCrowd implements Serializable {
    @Id
    @Column(name = "pcid")
    @ApiModelProperty(value = "主键")
    private String pcid;
    @Column(name = "pbid")
    @ApiModelProperty(value = "项目基本信息")
    private String pbid;
    @Column(name = "pcrowd_money")
    @ApiModelProperty(value = "众筹目标金额")
    private Float pcrowdMoney;
    @Column(name = "pcrowd_start")
    @ApiModelProperty(value = "众筹开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pcrowdStart;
    @Column(name = "pcrowd_end")
    @ApiModelProperty(value = "众筹结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pcrowdEnd;
}
