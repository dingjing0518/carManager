package com.jinshi.service;

import com.jinshi.entity.WxPayOrder;

public interface WxPayOrderService {

    int deleteByPrimaryKey(Integer id);

    int insert(WxPayOrder record);

    int insertSelective(WxPayOrder record);

    WxPayOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPayOrder record);

    int updateByPrimaryKey(WxPayOrder record);
}
