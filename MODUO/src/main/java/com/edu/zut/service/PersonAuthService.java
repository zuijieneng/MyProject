package com.edu.zut.service;


import com.edu.zut.entity.PersonAuth;
import com.edu.zut.entity.R;

public interface PersonAuthService  {
    public R<String> save(PersonAuth entity);
}
