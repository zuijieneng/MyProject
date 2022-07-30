package com.edu.zut.mapper;

import com.edu.zut.entity.ImgMp4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImgMp4Mapper extends JpaRepository<ImgMp4,Integer> {
    @Query(value = "select * from img_mp4 where  im_owner_id=?",nativeQuery = true)
    public List<ImgMp4> getList(String im_owner_id);
}
