package com.jinshi.service;

import com.jinshi.entity.WxUserInfo;


public interface WxUserInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    WxUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);

    int updateByOpenid(WxUserInfo wxUserInfo);

    String selectByOpenid(String openId);
}
