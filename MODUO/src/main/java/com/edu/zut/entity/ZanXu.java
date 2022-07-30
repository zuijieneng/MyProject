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
@Table(name = "zan_xu")
@SuppressWarnings(value = "all")
@ApiModel("赞嘘表")
public class ZanXu implements Serializable {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private String uid;
    @Column(name = "zx_owner_id")
    @ApiModelProperty(value = "被赞嘘的编号")
    private String zxOwnerId;
    @Column(name = "zx")
    @ApiModelProperty(value = "赞 1 嘘 -1")
    private Integer zx;
}
