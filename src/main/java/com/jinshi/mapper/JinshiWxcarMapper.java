package com.jinshi.mapper;

import com.jinshi.entity.JinshiWxcar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiWxcarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JinshiWxcar record);

    int insertSelective(JinshiWxcar record);

    JinshiWxcar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JinshiWxcar record);

    int updateByPrimaryKey(JinshiWxcar record);

    JinshiWxcar selectByCarNumber(String carNumber);

    List<JinshiWxcar> selectWxUser(String openid);

    int updateByOpenid(JinshiWxcar jinshiWxcar1);
}