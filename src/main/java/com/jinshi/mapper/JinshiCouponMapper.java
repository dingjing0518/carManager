package com.jinshi.mapper;

import com.jinshi.entity.JinshiCoupon;
import com.jinshi.entity.JinshiCouponBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiCouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(JinshiCoupon record);

    int insertSelective(JinshiCoupon record);

    JinshiCoupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(JinshiCoupon record);

    int updateByPrimaryKey(JinshiCoupon record);

    List<JinshiCoupon> selectCouponPage(Integer pageNum, Integer pageSize,Integer couponParkingId);

    int getCouponRecords();

    List<JinshiCoupon> searchCoupon(String keyWord, Integer pageNum, Integer pageSize);

    Integer updateCouponCount(JinshiCoupon jinshiCoupon);

    JinshiCoupon findJC(JinshiCoupon jinshiCoupon);

    JinshiCoupon findByname(JinshiCoupon jinshiCoupon);

}