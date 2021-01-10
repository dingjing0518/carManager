package com.jinshi.mapper;

import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.LincensePlatess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LincensePlatessMapper {
    List<LincensePlatess> searchLincense(String keyWork, Integer pageNum, Integer pageSize);

    List<LincensePlatess> selectLincenseForPage(Integer pageNum, Integer pageSize);

    int getLincenseTotalRecords();


    List<LincensePlatess> selectss(Integer pageNum, Integer pageSize,String parkId);
}