package com.edu.zut.mapper;

import com.edu.zut.entity.CompanyAuth;
import org.springframework.data.repository.CrudRepository;


public interface CompanyAuthMapper extends CrudRepository<CompanyAuth,Integer> {
    public CompanyAuth findCompanyAuthByUid(String uid);

}
