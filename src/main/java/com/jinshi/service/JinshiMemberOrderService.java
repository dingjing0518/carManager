package com.jinshi.service;

import com.jinshi.entity.JinshiMemberOrder;

import java.util.List;

public interface JinshiMemberOrderService {
    Integer insert(JinshiMemberOrder jinshiMemberOrder);

    List<JinshiMemberOrder> selectMemberOrderForPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getMemberOrderRecords(Integer parkId);

    int getMemberOrderRecordsSearch(String keyWord, Integer parkId);

    List<JinshiMemberOrder> searchMemberOrder(String keyWord, Integer pageNum, Integer pageSize, Integer parkId);

    Integer deleteByPrimaryKey(Integer id);

    JinshiMemberOrder selectByOrderId(String orderId);

    int updateByOrderId(JinshiMemberOrder jinshiMemberOrder);

}
