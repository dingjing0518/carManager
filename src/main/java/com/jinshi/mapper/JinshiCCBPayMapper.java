package com.jinshi.mapper;

import com.jinshi.entity.JinshiCCBPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiCCBPayMapper {
    int deleteByPrimaryKey(Integer ccbId);

    int insert(JinshiCCBPay record);

    int insertSelective(JinshiCCBPay record);

    JinshiCCBPay selectByPrimaryKey(Integer ccbId);

    int updateByPrimaryKeySelective(JinshiCCBPay record);

    int updateByPrimaryKey(JinshiCCBPay record);

    List<JinshiCCBPay> selectCCBPayByPlateAndParkId(String carNumber, Integer parkId);

    Integer updateCarOut(JinshiCCBPay jinshiCCBPay);

    JinshiCCBPay checkOrderRent(String carNumber, Integer parkId, String ccbThandle);

    JinshiCCBPay selectOrder(String orderId);

    Integer updateOrderId(JinshiCCBPay jinshiCCBPay);

    List<JinshiCCBPay> selectPlate(String car_number);
}