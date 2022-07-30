package com.edu.zut.entity.dto;

import com.edu.zut.entity.Message;
import com.edu.zut.entity.UserBasic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("用户基本信息扩展类")
public class UserBasicDto extends UserBasic {
    @ApiModelProperty("该楼用户自己的发言，一楼")
    private List<Message> mymessageList; //一楼的评论，自己的评论
    @ApiModelProperty("该楼用户被评论的，二楼")
    private List<Message> messageList;//该用户下的所有评论，楼中楼评论，二楼
}
