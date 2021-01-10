package com.jinshi.service;

import com.jinshi.entity.JinshiWxcar;

import java.util.List;

public interface JinshiWxcarService {

    Integer insertWxUser(JinshiWxcar jinshiWxcar);

    JinshiWxcar selectByCarNumber(String carNumber);

    List<JinshiWxcar> selectWxUser(String openid);

    int updateByOpenid(JinshiWxcar jinshiWxcar1);
}
