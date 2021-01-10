package com.jinshi.mapper;

import com.jinshi.entity.JinshiWeb;
import com.jinshi.entity.JinshiWebSonAll;

import java.util.List;

public interface JinshiWebMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiWeb record);

    int insertSelective(JinshiWeb record);

    JinshiWeb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiWeb record);

    int updateByPrimaryKey(JinshiWeb record);

    List<JinshiWeb> selectAll();

    List<JinshiWeb> selectData();

    List<JinshiWebSonAll> selectById(Integer id);

    Integer selectSub(Integer id);

    List<JinshiWebSonAll> selectList(Integer id);
}