package com.jinshi.mapper;

import com.jinshi.entity.JinshiUserRole;

public interface JinshiUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiUserRole record);

    int insertSelective(JinshiUserRole record);

    JinshiUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiUserRole record);

    int updateByPrimaryKey(JinshiUserRole record);
}