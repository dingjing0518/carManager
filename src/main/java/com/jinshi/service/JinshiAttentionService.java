package com.jinshi.service;

import com.jinshi.entity.JinshiAttention;

import java.util.List;

public interface JinshiAttentionService {
    List<JinshiAttention> selectAttentionForPage(Integer pageNum, Integer pageSize,String parkId);

    int getAttentionTotalRecords(String parkId);

    List<JinshiAttention> searchAttention(String keyWord, Integer pageNum, Integer pageSize,String parkId);

    Integer insert(JinshiAttention jinshiAttention);

    Integer deleteByPrimaryKey(Integer id);

    Integer updateByPrimaryKey(JinshiAttention jinshiAttention);

    List<JinshiAttention> searchAttentionUtil(String jcLincensePlateId);

    List<JinshiAttention> selectByParkId(String parkId);
}
