package com.edu.zut.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_user")
@SuppressWarnings(value = "all")
@ApiModel("用户项目支持表")
public class SupportProject implements Serializable {
    @Id
    @Column(name = "pbid")
    @ApiModelProperty(value = "项目编号")
    private String pbid;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户编号")
    private String uid;
}
