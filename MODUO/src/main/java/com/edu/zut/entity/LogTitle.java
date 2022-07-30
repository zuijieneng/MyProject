package com.edu.zut.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log_title")
@SuppressWarnings(value = "all")
public class LogTitle implements Serializable {
    @Id
    @Column(name = "uloid")
    @ApiModelProperty(value = "动态主键")
    private String uloid;
    @Column(name = "tid")
    @ApiModelProperty(value = "话题主键")
    private String tid;



}
