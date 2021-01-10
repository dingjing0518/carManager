package com.jinshi.service;

import com.jinshi.entity.JinshiArea;
import com.jinshi.entity.JinshiCameras;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JinshiCameraService {

    int deleteByPrimaryKey(Integer id);

    int insert(JinshiCameras record);

    int insertSelective(JinshiCameras record);

    JinshiCameras selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiCameras record);

    int updateByPrimaryKey(JinshiCameras record);



    List<JinshiCameras> searchJinshiCameras(@Param(value = "keyWork") String keyWork, @Param(value = "pageNum") Integer pageNum, @Param(value = "pageSize") Integer pageSize);

    List<JinshiCameras> selectJinshiCamerasForPage(Integer pageNum, Integer pageSize,Integer jcParking);

    int getJinshiCamerasTotalRecords(Integer parkId);

    JinshiCameras selectByIpAddress(String ipAddress);

    List<JinshiCameras> selectCameraByParkId(Integer parkId);

    JinshiCameras selectByParkIdAndJcName(@Param(value="jcParking") Integer jcParking, @Param(value="jcName") String jcName);

    JinshiCameras selectByParkIdAndtHandle(@Param(value="jcParking") Integer jcParking, @Param(value="jcThandle") String jcThandle);

    Integer updateLedScreenType(JinshiCameras jinshiCameras);

    Integer updateByCameras(JinshiCameras jinshiCameras);

    JinshiCameras selectByThandle(String jcParking, String jcThandle);

    Integer updateCamerasCode(JinshiCameras jinshiCameras);

    List<JinshiCameras> selectCamerasByParkId(String parkid);

    Integer updateCamerasSort(JinshiCameras jinshiCameras);

    List<JinshiCameras> selectCamerasForKS();

    List<JinshiCameras> selectCamerasForWJ();

    List<JinshiCameras> selectByParkIdDesc(String parkId);

    Integer updateCarTeam(JinshiCameras jinshiCameras);

    Integer updateOpenMode(JinshiCameras jinshiCameras);

    Boolean updateIsType(JinshiCameras jinshiCameras);

    JinshiCameras selectCameraId(JinshiCameras jinshiCameras);

    List<JinshiCameras> selectAllCameras();

    List<JinshiCameras> selectCamerasForTY();

    List<JinshiCameras> selectByThandleDesc(String parkId);
}
