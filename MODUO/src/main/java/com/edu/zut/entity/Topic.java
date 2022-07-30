package com.edu.zut.entity;

import io.swagger.annotations.Api;
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
@Table(name = "title")
@SuppressWarnings(value = "all")
@Api("话题")
public class Topic implements Serializable {
    @Id
    @Column(name = "tid")
    @ApiModelProperty(value = "话题ID")
    private String tid;
    @Column(name = "tcontent")
    @ApiModelProperty(value = "话题内容")
    private String tcontent;
    @Column(name = "ttime")
    @ApiModelProperty(value = "话题创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ttime;
    @Column(name = "tphoto")
    @ApiModelProperty(value = "话题图片")
    private String tphoto;
    @Column(name = "tuser_count")
    @ApiModelProperty(value = "话题讨论人数")
    private Integer tuserCount;
    @Column(name = "tlog_count")
    @ApiModelProperty(value = "话题动态人数")
    private Integer tlogCount;

}
