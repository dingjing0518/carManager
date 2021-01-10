package com.jinshi.mapper;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JinshiCamerasSpareMapper {
    int deleteByPrimaryKey(Integer jcsId);

    int insert(JinshiCamerasSpare record);

    int insertSelective(JinshiCamerasSpare record);

    JinshiCamerasSpare selectByPrimaryKey(Integer jcsId);

    int updateByPrimaryKeySelective(JinshiCamerasSpare record);

    int updateByPrimaryKey(JinshiCamerasSpare record);

    Integer updateCamerasSpare(JinshiCamerasSpare jinshiCamerasSpare);

    JinshiCamerasSpare selectCameraId(JinshiCamerasSpare jinshiCamerasSpare);

    JinshiCamerasSpare selectRealCameraId(JinshiCamerasSpare jinshiCamerasSpare);

    Integer deleteByParkId(String jcCamerasId, Integer parkId);
}