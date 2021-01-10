package com.jinshi.service;

import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.bo.JinshiLincenseGroupBo;

import java.util.List;

public interface JinshiLincenseGroupService {
    List<JinshiLincenseGroupBo> selectLincenseGroupPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getLincenseGroupRecords(Integer parkId);

    List<JinshiLincenseGroup> searchLincenseGroup(String keyWord, Integer pageNum, Integer pageSize, Integer parkId);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(JinshiLincenseGroup jinshiLincenseGroup);

    Integer updateByPrimaryKey(JinshiLincenseGroup jinshiLincenseGroup);

    List<JinshiLincenseGroup> selectALl();

    int getLincenseGroupRecordsSearch(String keyWord, Integer parkId);

    JinshiLincenseGroup selectByPrimaryKey(Integer lgId);

    JinshiLincenseGroup selectAllByJsNumber(JinshiLincenseGroup jsNumber);

    List<JinshiLincenseGroup> selectByParkIdAndAreaId(JinshiLincenseGroup jinshiLincenseGroup);
}
