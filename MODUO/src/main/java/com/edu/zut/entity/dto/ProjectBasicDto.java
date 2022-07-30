package com.edu.zut.entity.dto;

import com.edu.zut.entity.ImgMp4;
import com.edu.zut.entity.ProjectBasic;
import com.edu.zut.entity.ProjectCrowd;
import com.edu.zut.entity.UserBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("项目基本表扩展类")
public class ProjectBasicDto extends ProjectBasic {
    private ProjectCrowd projectCrowd; //众筹项目的其他的信息
    @ApiModelProperty("关注的人数")
    private Integer  careCount; //被关注的人数
    @ApiModelProperty("支持的人数")
    private Integer supportCount; //被支持的人数
    @ApiModelProperty("评论人数")
    private Integer commentCount; //评论的人数
    @ApiModelProperty("项目类型")
    private String type; //项目类型
    @ApiModelProperty("支持者列表")
    private List<UserBasic> supprotList; //支持者列表从
    @ApiModelProperty("评论列表楼中楼")
    private List<UserBasicDto> commentList; //评论列表楼中楼
    @ApiModelProperty("项目图片介绍")
    private List<ImgMp4> picList; //图片列表
}
