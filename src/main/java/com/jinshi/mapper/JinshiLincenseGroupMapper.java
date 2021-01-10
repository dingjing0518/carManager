package com.jinshi.mapper;

import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.bo.JinshiLincenseGroupBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiLincenseGroupMapper {
    int deleteByPrimaryKey(Integer jsLgId);

    int insert(JinshiLincenseGroup record);

    int insertSelective(JinshiLincenseGroup record);

    JinshiLincenseGroup selectByPrimaryKey(Integer jsLgId);

    int updateByPrimaryKeySelective(JinshiLincenseGroup record);

    int updateByPrimaryKey(JinshiLincenseGroup record);

    List<JinshiLincenseGroup> selectLincenseGroupPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getLincenseGroupRecords(Integer parkId);

    List<JinshiLincenseGroup> searchLincenseGroup(String keyWord, Integer pageNum, Integer pageSize, Integer parkId);

    List<JinshiLincenseGroup> selectALl();

    int getLincenseGroupRecordsSearch(String keyWord, Integer parkId);

    JinshiLincenseGroup selectAllByJsNumber(JinshiLincenseGroup jinshiLincenseGroup);

    List<JinshiLincenseGroup> selectByParkIdAndAreaId(JinshiLincenseGroup jinshiLincenseGroup);

    List<JinshiLincenseGroupBo> findByparkId(Integer pageNum, Integer pageSize, Integer parkId);
}