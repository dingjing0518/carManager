package com.jinshi.mapper;

import com.jinshi.entity.JinshiMemberOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiMemberOrderMapper {
    int deleteByPrimaryKey(Integer jmoId);

    int insert(JinshiMemberOrder record);

    int insertSelective(JinshiMemberOrder record);

    JinshiMemberOrder selectByPrimaryKey(Integer jmoId);

    int updateByPrimaryKeySelective(JinshiMemberOrder record);

    int updateByPrimaryKey(JinshiMemberOrder record);

    List<JinshiMemberOrder> selectMemberOrderForPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getMemberOrderRecords(Integer parkId);

    List<JinshiMemberOrder> searchMemberOrder(String keyWord, Integer pageNum, Integer pageSize, Integer parkId);

    int getMemberOrderRecordsSearch(String keyWord, Integer parkId);

    List<JinshiMemberOrder> selectByMemberId(Integer id);

    JinshiMemberOrder selectByOrderId(String orderId);

    int updateByOrderId(JinshiMemberOrder jinshiMemberOrder);
}