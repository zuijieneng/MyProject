package com.edu.zut.mapper;

import com.edu.zut.entity.PersonAuth;
import org.springframework.data.repository.CrudRepository;


public interface PersonAuthMapper extends CrudRepository<PersonAuth,Integer> {
    public PersonAuth findByUpaperId(String upaperId);
    public PersonAuth findPersonAuthByUid(String uid);
}
