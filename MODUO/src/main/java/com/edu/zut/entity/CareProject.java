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
@Table(name = "user_project_care")
@SuppressWarnings(value = "all")
@ApiModel("用户项目关注表")
public class CareProject implements Serializable {
    @Id
    @Column(name = "pbid")
    @ApiModelProperty(value = "项目编号")
    private String pbid;
    @Column(name = "uid")
    @ApiModelProperty(value = "用户编号")
    private String uid;
}
