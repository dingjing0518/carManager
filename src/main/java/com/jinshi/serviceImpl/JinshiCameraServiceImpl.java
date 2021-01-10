package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiArea;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.mapper.JinshiCamerasMapper;
import com.jinshi.service.JinshiCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiCameraServiceImpl implements JinshiCameraService {

    @Autowired
    private JinshiCamerasMapper jinshiCamerasMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jinshiCamerasMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JinshiCameras record) {
        return jinshiCamerasMapper.insert(record);
    }

    @Override
    public int insertSelective(JinshiCameras record) {
        return jinshiCamerasMapper.insertSelective(record);
    }

    @Override
    public JinshiCameras selectByPrimaryKey(Integer id) {
        return jinshiCamerasMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(JinshiCameras record) {
        return jinshiCamerasMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JinshiCameras record) {
        return jinshiCamerasMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<JinshiCameras> searchJinshiCameras(String keyWork, Integer pageNum, Integer pageSize) {
        return jinshiCamerasMapper.searchCameras(keyWork,pageNum,pageSize);
    }

    @Override
    public List<JinshiCameras> selectJinshiCamerasForPage(Integer pageNum, Integer pageSize,Integer jcParking) {
        return jinshiCamerasMapper.selectCamerasForPage(pageNum,pageSize,jcParking);
    }

    @Override
    public int getJinshiCamerasTotalRecords(Integer parkId) {
        return jinshiCamerasMapper.getCameraTotalRecords(parkId);
    }

    @Override
    public JinshiCameras selectByIpAddress(String ipAddress) {
        return jinshiCamerasMapper.selectByIpAddress(ipAddress);
    }

    @Override
    public List<JinshiCameras> selectCameraByParkId(Integer parkId) {
        return jinshiCamerasMapper.selectCameraByParkId(parkId);
    }

    @Override
    public JinshiCameras selectByParkIdAndJcName(Integer jcParking, String jcName) {
        return jinshiCamerasMapper.selectByParkIdAndJcName(jcParking,jcName);
    }

    @Override
    public JinshiCameras selectByParkIdAndtHandle(Integer jcParking, String jcThandle) {
        return jinshiCamerasMapper.selectByParkIdAndtHandle(jcParking,jcThandle);
    }

    @Override
    public Integer updateLedScreenType(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateLedScreenType(jinshiCameras);
    }

    @Override
    public Integer updateByCameras(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateByCameras(jinshiCameras);
    }

    @Override
    public JinshiCameras selectByThandle(String jcParking, String jcThandle) {
        return jinshiCamerasMapper.selectByThandle(jcParking,jcThandle);
    }

    @Override
    public Integer updateCamerasCode(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateCamerasCode(jinshiCameras);
    }

    @Override
    public List<JinshiCameras> selectCamerasByParkId(String parkid) {
        return jinshiCamerasMapper.selectCamerasByParkId(parkid);
    }

    @Override
    public Integer updateCamerasSort(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateCamerasSort(jinshiCameras);
    }

    @Override
    public List<JinshiCameras> selectCamerasForKS() {
        return jinshiCamerasMapper.selectCamerasForKS();
    }

    @Override
    public List<JinshiCameras> selectCamerasForWJ() {
        return jinshiCamerasMapper.selectCamerasForWJ();
    }

    @Override
    public List<JinshiCameras> selectByParkIdDesc(String parkId) {
        return jinshiCamerasMapper.selectByParkIdDesc(parkId);
    }

    @Override
    public Integer updateCarTeam(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateCarTeam(jinshiCameras);
    }

    @Override
    public Integer updateOpenMode(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateOpenMode(jinshiCameras);
    }

    @Override
    public Boolean updateIsType(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.updateIsType(jinshiCameras);
    }

    @Override
    public JinshiCameras selectCameraId(JinshiCameras jinshiCameras) {
        return jinshiCamerasMapper.selectCameraId(jinshiCameras);
    }

    @Override
    public List<JinshiCameras> selectAllCameras() {
        return jinshiCamerasMapper.selectAllCameras();
    }

    @Override
    public List<JinshiCameras> selectCamerasForTY() {
        return jinshiCamerasMapper.selectCamerasForTY();
    }

    @Override
    public List<JinshiCameras> selectByThandleDesc(String parkId) {
        return jinshiCamerasMapper.selectByThandleDesc(parkId);
    }
}
