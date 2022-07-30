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

/**
 * 情况一：用户对某个动态直接留言
 * 情况二：用户对某个动态下的用户留言
 * 情况三：用户对项目留言
 * 情况四：用户对某个项目下的用户留言
 * 综上所述：最多需要三个id才能显示所有留言，包括楼中楼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
@SuppressWarnings(value = "all")
@ApiModel("留言")
public class Message implements Serializable {
    @Id
    @Column(name = "messageid")
    @ApiModelProperty(value = "留言主键")
    private String messageid;
    @Column(name = "message_time")
    @ApiModelProperty(value = "留言时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date messageTime;
    @Column(name = "message_content")
    @ApiModelProperty(value = "留言内容")
    private String messageContent;
    @Column(name = "uid")
    @ApiModelProperty(value = "留言发布者")
    private String uid;
    @Column(name = "m_owner_id")
    @ApiModelProperty(value = "所属对象的编号")
    private String mOwnerId;
    @Column(name = "target_uid")
    @ApiModelProperty(value = "某个动态或者项目下的其他的用户的ID，用户与用户也是多对多，同时如果存在说明是评论的楼中楼，便于前端的设计")
    private String targetUid;
}
