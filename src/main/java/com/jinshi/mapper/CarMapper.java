package com.jinshi.mapper;

import com.jinshi.entity.Car;

public interface CarMapper {
    int deleteByPrimaryKey(Integer lpId);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer lpId);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}