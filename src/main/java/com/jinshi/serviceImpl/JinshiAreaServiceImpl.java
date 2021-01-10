package com.jinshi.serviceImpl;

import com.jinshi.entity.Agent;
import com.jinshi.entity.JinshiArea;
import com.jinshi.entity.JinshiParking;
import com.jinshi.mapper.AgentMapper;
import com.jinshi.mapper.JinshiAreaMapper;
import com.jinshi.mapper.JinshiParkingMapper;
import com.jinshi.service.JinshiAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiAreaServiceImpl implements JinshiAreaService {

    @Autowired
    private JinshiAreaMapper jinshiAreaMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiAreaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(JinshiArea jinshiArea) {
        //添加区域编号测试代码
//        Integer parkId = jinshiArea.getParkId();
//        int areaTotalRecords = jinshiAreaMapper.getAreaTotalRecords(parkId);
//        JinshiParking jinshiParking = jinshiParkingMapper.selectByPrimaryKey(parkId);
//        StringBuilder sb = new StringBuilder();
//        sb.append(jinshiParking.getJpParkNumber());
//        if (areaTotalRecords > 0) {
//            String total = String.valueOf(areaTotalRecords);
//            while (total.length() < 2) {
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("0");
//                sb1.append(Integer.valueOf(areaTotalRecords) + 1);
//                total = sb1.toString();
//            }
//            sb.append(total);
//        } else {
//            sb.append("01");
//        }
//        if (jinshiArea.getAreaName().contains("全区域")) {
//            sb.append("00");
//        }
//        jinshiArea.setAreaNumber(sb.toString());
        return jinshiAreaMapper.insert(jinshiArea);
    }

    @Override
    public List<JinshiArea> selectAreaForPage(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiAreaMapper.selectAreaForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getAreaTotalRecords(Integer parkId) {
        return jinshiAreaMapper.getAreaTotalRecords(parkId);
    }

    @Override
    public List<JinshiArea> searchArea(String keyWord, Integer pageNum, Integer pageSize,Integer parkId) {
        return jinshiAreaMapper.searchArea(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public Integer updateByArea(JinshiArea jinshiArea) {
        return jinshiAreaMapper.updateByPrimaryKey(jinshiArea);
    }

    @Override
    public List<JinshiArea> selectAreaNameAll(Integer parkId) {
        return jinshiAreaMapper.selectAreaNameAll(parkId);
    }

    @Override
    public JinshiArea selectByJcArea(String jcArea, Integer parkId) {
        return jinshiAreaMapper.selectByJcArea(jcArea,parkId);
    }

    @Override
    public List<JinshiArea> selectAllArea(Integer parkId) {
        return jinshiAreaMapper.selectAllArea( parkId);
    }
}
