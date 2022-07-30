package com.edu.zut.mapper;

import com.edu.zut.entity.AddressBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookMapper extends CrudRepository<AddressBook,Integer> {
    @Query(value = "select * from user_address where uid=?",nativeQuery = true)
    public List<AddressBook> getAllByUid(String uid);
}
