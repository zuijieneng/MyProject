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
@Api("话题扩展类")
public class TopicDto extends Topic {
    @ApiModelProperty("话题下的所有动态")
    private List<UserLog> userLogList; //一个话题下有多个动态
//    private List<Message> messageList; //多个留言,一楼的
    @ApiModelProperty("动态下的用户信息以及用户评论以及被评论")
    private List<UserBasicDto> userBasicDtoList; //动态下有多个用户评论，二楼
}
