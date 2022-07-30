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
@Table(name = "dictionary")
@SuppressWarnings(value = "all")
@ApiModel("字典表")
public class Dictionary implements Serializable {
    @Id
    @Column(name = "did")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "字典表主键")
    private Integer did;
    @Column(name = "dname")
    @ApiModelProperty(value = "名称")
    private String dname;
    @Column(name = "dtype")
    @ApiModelProperty(value = "类型")
    private Integer dtype;
    @Column(name = "fdid")
    @ApiModelProperty(value = "关联类型")
    private Integer fdid;
}
