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
@Table(name = "team_user")
@SuppressWarnings(value = "all")
@ApiModel("团队成员表")
public class TeamUser implements Serializable {
    @Id
    @Column(name = "pbid")
    @ApiModelProperty("项目编号")
    private String pbid;
    @Column(name = "uid")
    @ApiModelProperty("用户编号")
    private String uid;
}
