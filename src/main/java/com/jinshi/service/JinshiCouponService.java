package com.jinshi.service;

import com.jinshi.entity.JinshiCoupon;
import com.jinshi.entity.JinshiCouponBo;

import java.util.List;

public interface JinshiCouponService {
    List<JinshiCouponBo> selectCouponPage(Integer pageNum, Integer pageSize,Integer cpId);

    int getCouponRecords();

    List<JinshiCoupon> searchCoupon(String keyWord, Integer pageNum, Integer pageSize);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(JinshiCouponBo jinshiCouponBo);

    Integer updateByPrimaryKey(JinshiCouponBo jinshiCouponBo);

    Integer updateCouponCount(JinshiCoupon jinshiCoupon);

    JinshiCoupon selectByPrimaryKey(Integer jcId);
}
