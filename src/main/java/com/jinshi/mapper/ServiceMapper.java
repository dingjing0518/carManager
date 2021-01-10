package com.jinshi.mapper;

import com.jinshi.entity.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    List<Service> selectServiceAll();

    List<Service> selectServcieForPage(Integer pageNum, Integer pageSize);

    int getServiceTotalRecords();

    List<Service> searchService(String keyWord,Integer pageNum,Integer pageSize);

    int getServiceTotalRecordsSearch(String keyWord);
}