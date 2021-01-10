package com.jinshi.mapper;

import com.jinshi.entity.WxPayOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxPayOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxPayOrder record);

    int insertSelective(WxPayOrder record);

    WxPayOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPayOrder record);

    int updateByPrimaryKey(WxPayOrder record);
}