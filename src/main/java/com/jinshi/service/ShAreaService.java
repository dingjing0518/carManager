package com.jinshi.service;

import com.jinshi.entity.ShArea;

import java.util.List;

public interface ShAreaService {
    int deleteByPrimaryKey(Integer id);

    int insert(ShArea record);

    int insertSelective(ShArea record);

    ShArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShArea record);

    int updateByPrimaryKey(ShArea record);

    List<ShArea> selects();

    List<ShArea> selectcity(String name);
}