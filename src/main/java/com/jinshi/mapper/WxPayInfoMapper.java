package com.jinshi.mapper;

import com.jinshi.entity.WxPayInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxPayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxPayInfo record);

    int insertSelective(WxPayInfo record);

    WxPayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPayInfo record);

    int updateByPrimaryKey(WxPayInfo record);
}