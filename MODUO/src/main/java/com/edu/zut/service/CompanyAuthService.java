package com.edu.zut.service;

import com.edu.zut.entity.CompanyAuth;
import com.edu.zut.entity.R;

public interface CompanyAuthService {
    public R<String> save(CompanyAuth entity);
}
