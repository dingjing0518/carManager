package com.jinshi.service;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiCCBPay;

import java.util.List;
import java.util.Map;

public interface JinshiCCBPayService {


    JSONObject CCBPay(JSONObject jsonObject);

    Integer insert(JinshiCCBPay jinshiCCBPay);

    Integer updateCarOut(JinshiCCBPay jinshiCCBPay);

    List<JinshiCCBPay> selectCCBPayByPlateAndParkId(String carNumber, Integer parkId);

    JinshiCCBPay checkOrderRent(String carNumber, Integer parkId, String ccbThandle);

    Map<String, Object> selectByOrderId(String orderId);

    JinshiCCBPay selectOrder(String orderId);

    Integer updateOrderId(JinshiCCBPay jinshiCCBPay);

    List<JinshiCCBPay> selectPlate(String car_number);
}
