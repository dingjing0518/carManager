package com.jinshi.service;

import com.jinshi.entity.JinshiArea;

import java.util.List;

public interface JinshiAreaService {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(JinshiArea jinshiArea);

    List<JinshiArea> selectAreaForPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getAreaTotalRecords(Integer parkId);

    List<JinshiArea> searchArea(String keyWord, Integer pageNum, Integer pageSize,Integer parkId);

    Integer updateByArea(JinshiArea jinshiArea);

    List<JinshiArea> selectAreaNameAll(Integer parkId);

    JinshiArea selectByJcArea(String jcArea, Integer parkId);

    List<JinshiArea> selectAllArea(Integer parkId);
}
