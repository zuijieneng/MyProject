package com.edu.zut.service.impl;

import com.edu.zut.entity.CompanyAuth;
import com.edu.zut.entity.R;
import com.edu.zut.mapper.CompanyAuthMapper;
import com.edu.zut.service.CompanyAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanAuthServiceImpl implements CompanyAuthService {
    @Resource
    private CompanyAuthMapper companyAuthMapper;

    @Override
    public R<String> save(CompanyAuth entity) {
        if (entity.getUcompanyName().equals("")||entity.getUcompanyName()==null) return R.error("公司名字不能为空");
        if (entity.getUcompanyId().equals("")||entity.getUcompanyId()==null) return R.error("信用代码不能为空");
        if (entity.getUcompanyPerson().equals("")||entity.getUcompanyPerson()==null) return R.error("法人不能为空");
        if (entity.getUcompanyPersonPaperId().equals("")||entity.getUcompanyPersonPaperId()==null) return R.error("请输入您的证件号码");
        if (!entity.getUcompanyPersonPaperId().matches("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")) return R.error("证件号码格式不正确");
        if (companyAuthMapper.findCompanyAuthByUid(entity.getUid())!=null){
            companyAuthMapper.deleteById(companyAuthMapper.findCompanyAuthByUid(entity.getUid()).getUcaid());
        }
        CompanyAuth save = companyAuthMapper.save(entity);
        return save!=null?R.success("提交成功"):R.error("提交失败");
    }
}
