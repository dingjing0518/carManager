package com.jinshi.mapper;

import com.jinshi.entity.Subs;

import java.util.List;

public interface JinshiWebSubsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subs record);

    int insertSelective(Subs record);

    Subs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subs record);

    int updateByPrimaryKey(Subs record);

    List<Subs> selectByWebid(Integer id);
}