package com.edu.zut.mapper;

import com.edu.zut.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountMapper extends CrudRepository<Account,Integer> {
    @Query(value = "select * from user_account where uid=?",nativeQuery = true)
    public List<Account> getAllByUid(String uid);
}
