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
@Table(name = "project_team")
@SuppressWarnings(value = "all")
@ApiModel("团员设置表")
public class ProjectTeam implements Serializable {
    @Id
    @Column(name = "pbid")
    @ApiModelProperty("team主键")
    private String pbid;
    @Column(name = "phone")
    @ApiModelProperty("team主键")
    private String phone;
    @Column(name = "pqq")
    @ApiModelProperty("team主键")
    private String pqq;
    @Column(name = "ptemail")
    @ApiModelProperty("team主键")
    private String ptemail;
    @Column(name = "pworker")
    @ApiModelProperty("team主键")
    private String pworker;

}
