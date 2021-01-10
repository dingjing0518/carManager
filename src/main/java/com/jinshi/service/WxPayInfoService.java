package com.jinshi.service;

import com.jinshi.entity.WxPayInfo;

public interface WxPayInfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(WxPayInfo record);

    int insertSelective(WxPayInfo record);

    WxPayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPayInfo record);

    int updateByPrimaryKey(WxPayInfo record);
}
