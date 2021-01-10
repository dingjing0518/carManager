package com.jinshi.service;

import com.jinshi.entity.Service;

import java.util.List;

public interface ServiceService {

    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    List<Service> selectServiceAll();

    List<Service> selectServcieForPage(Integer pageNum, Integer pageSize);

    int getServiceTotalRecords();

    List<Service> searchService(String keyWord, Integer pageNum, Integer pageSize);

    int getServiceTotalRecordsSearch(String keyWord);
}
