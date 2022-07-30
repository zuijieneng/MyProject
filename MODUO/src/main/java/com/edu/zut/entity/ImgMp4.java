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
@Table(name = "img_mp4")
@SuppressWarnings(value = "all")
@ApiModel("存放图片视频表")
public class ImgMp4 implements Serializable {
    @Id
    @Column(name = "imid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer imid;
    @Column(name = "im_url")
    @ApiModelProperty(value = "图片路径")
    private String imUrl;
    @Column(name = "im_owner_id")
    @ApiModelProperty(value = "图片所属对象的编号")
    private String imOwnerId;
}
