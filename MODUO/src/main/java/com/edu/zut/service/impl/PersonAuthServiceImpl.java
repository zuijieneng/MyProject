package com.edu.zut.service.impl;

import com.edu.zut.entity.PersonAuth;
import com.edu.zut.entity.R;
import com.edu.zut.mapper.DictionaryMapper;
import com.edu.zut.mapper.PersonAuthMapper;
import com.edu.zut.service.PersonAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonAuthServiceImpl implements PersonAuthService {
    @Resource
    private PersonAuthMapper personAuthMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public R<String> save(PersonAuth entity) {
        if (entity.getUrealName()==null||entity.getUrealName().equals("")) return R.error("请输入您的真实姓名");
        if (entity.getUpaperId().equals("")||entity.getUpaperId()==null ) return R.error("请输入您的证件号码");
        if (!entity.getUpaperId().matches("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$") ) return R.error("证件号码格式不正确");
        if (entity.getUpaperStart()==null||entity.getUpaperEnd()==null) return R.error("请输入身份证件有效期起始时间");
        if (entity.getUaddress().equals("")|entity.getUaddress()==null) return R.error("请输入联系地址");
        if (entity.getUemail().equals("")||entity.getUemail()==null) return R.error("请输入联系电子邮箱");
        if(personAuthMapper.findByUpaperId(entity.getUpaperId())!=null){
            personAuthMapper.delete(personAuthMapper.findByUpaperId(entity.getUpaperId()));
        }
        PersonAuth save = personAuthMapper.save(entity);
        return save!=null?R.success("提交成功"):R.error("提交失败");
    }
}
