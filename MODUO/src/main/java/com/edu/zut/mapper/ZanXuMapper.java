package com.edu.zut.mapper;

import com.edu.zut.entity.ZanXu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ZanXuMapper extends JpaRepository<ZanXu,String> {
    @Query(value = "insert into zan_xu values(?,?,?)",nativeQuery = true)
    @Modifying
    @Transactional
    public int dianZx(String uid,String zx_owner_id,Integer zx);

    @Query(value = "select count(*) from zan_xu where zx_owner_id=? and zx=1",nativeQuery = true)
    public Integer getZanCount(String zx_owner_id);
}
