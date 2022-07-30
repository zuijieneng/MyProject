package com.edu.zut.entity.dto;

import com.edu.zut.entity.Topic;
import com.edu.zut.entity.UserLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("动态扩展类")
public class UserLogDto extends UserLog {
    @ApiModelProperty("该动态引用或者创建的话题")
    private List<Topic> topicList; //多个话题
//    private List<Message> messageList; //多个留言
    @ApiModelProperty("动态下的用户信息以及用户评论以及被评论")
    private List<UserBasicDto> userBasicDtoList; //动态下有多个用户评论
    @ApiModelProperty("点赞个数")
    private Integer likeCount; //点赞个数
    @ApiModelProperty("评论个数")
    private Integer commentCount; //评论个数
}
