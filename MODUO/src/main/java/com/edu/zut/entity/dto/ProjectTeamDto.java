package com.edu.zut.entity.dto;

import com.edu.zut.entity.ProjectTeam;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("团队设置表")
public class ProjectTeamDto extends ProjectTeam {
    private List<String> userBasicList; //一个团队有多个成员
}
