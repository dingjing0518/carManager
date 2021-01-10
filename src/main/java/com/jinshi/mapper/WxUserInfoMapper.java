package com.jinshi.mapper;

import com.jinshi.entity.WxUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    WxUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);

    int updateByOpenid(WxUserInfo wxUserInfo);

    String selectByOpenid(String openId);
}