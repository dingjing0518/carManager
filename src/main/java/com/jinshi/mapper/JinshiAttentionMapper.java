package com.jinshi.mapper;

import com.jinshi.entity.JinshiAttention;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JinshiAttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiAttention record);

    int insertSelective(JinshiAttention record);

    JinshiAttention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiAttention record);

    int updateByPrimaryKey(JinshiAttention record);

    List<JinshiAttention> selectAttentionForPage(Integer pageNum, Integer pageSize,String jcParkid);

    int getAttentionTotalRecords(String jcParkid);

    List<JinshiAttention> searchAttention(String keyWord,Integer pageNum, Integer pageSize,String parkId);

    List<JinshiAttention> searchAttentionUtil(String jcLincensePlateId);

    List<JinshiAttention> selectByParkId(String parkId);
}