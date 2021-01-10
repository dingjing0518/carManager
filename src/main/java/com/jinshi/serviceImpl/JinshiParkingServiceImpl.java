package com.jinshi.serviceImpl;

import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.JinshiParkingService;
import com.jinshi.util.ShiroConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JinshiParkingServiceImpl implements JinshiParkingService {

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JinshiParkSettingMapper jinshiParkSettingMapper;

    @Autowired
    private JinshiParkingSetupMapper jinshiParkingSetupMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jinshiParkingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JinshiParking record) {
        JinshiParking jinshiParking = new JinshiParking();
        jinshiParking.setJpId(record.getJpId());
        jinshiParking.setJpName(record.getJpName());
        jinshiParking.setJpSite(record.getJpSite());
        jinshiParking.setJpTotalTurnover(record.getJpTotalTurnover());
        jinshiParking.setJpNumber(record.getJpNumber());
        jinshiParking.setJpPhoneNumber(record.getJpPhoneNumber());
        jinshiParking.setJpPhoneNumberBackup(record.getJpPhoneNumberBackup());
        jinshiParking.setJpPhoneNumberBackupTwo(record.getJpPhoneNumberBackupTwo());
        jinshiParking.setJpPlaceNumber("500");
        jinshiParking.setJpCameraBrand(record.getJpCameraBrand());
        jinshiParking.setJpRemark(record.getJpRemark());
        Agent agent = agentMapper.selectByuserName(record.getJpAgentName());
        jinshiParking.setJpAgentId(agent.getId());
        jinshiParking.setJpAgentName(record.getJpAgentName());
        jinshiParking.setJpCreatetime(new Date());
        jinshiParking.setJpUsername(record.getJpUsername());
        jinshiParking.setJpLoginname(record.getJpLoginname());
        jinshiParking.setJpProvince(record.getJpProvince());
        jinshiParking.setJpCity(record.getJpCity());
        jinshiParking.setJpDistrict(record.getJpDistrict());
        jinshiParking.setJpRoleName(record.getJpRoleName());
        jinshiParkingMapper.insert(jinshiParking);

        //添加车场编号测试代码
        //添加车场后把编号更新进去，代理商编号加车场id（不足4位用0补齐）
//        String jpId = String.valueOf(jinshiParking.getJpId());
//        String agentNumber = agent.getAgentNumber();
//        StringBuilder sb = new StringBuilder();
//        sb.append(agentNumber);
//        while (jpId.length() < 4) {
//            StringBuilder sb1 = new StringBuilder();
//            sb1.append("0");
//            sb1.append(jpId);
//            jpId = sb1.toString();
//        }
//        sb.append(jpId);
//        jinshiParking.setJpParkNumber(sb.toString());
//        jinshiParkingMapper.updateParkNumber(jinshiParking);

        //添加车场的时候添加相关默认设置
        List<JinshiParking> jinshiParkingList = jinshiParkingMapper.selectByAgentId(1);//默认用昆山的
        if (jinshiParkingList.size() > 0) {
            JinshiParking jinshiParking1 = jinshiParkingList.get(0);
            List<JinshiParkSetting> jinshiParkSettings = jinshiParkSettingMapper.selectByParkKey(jinshiParking1.getJpId());
            for (JinshiParkSetting parkSetting : jinshiParkSettings) {
                JinshiParkSetting jinshiParkSetting = new JinshiParkSetting();
                BeanUtils.copyProperties(parkSetting,jinshiParkSetting);
                jinshiParkSetting.setJpsId(null);
                jinshiParkSetting.setJpsParkId(jinshiParking.getJpId());
                jinshiParkSetting.setJpsAgentId(agent.getId());
                jinshiParkSetting.setJpsCreatetime(new Date());
                jinshiParkSettingMapper.insert(jinshiParkSetting);
            }
        }
        List<JinshiParkingSetup> jinshiParkingSetupList = jinshiParkingSetupMapper.selectByAgentId(1);//默认用昆山的
        if (jinshiParkingSetupList.size() > 0) {
            JinshiParkingSetup jinshiParkingSetup = jinshiParkingSetupList.get(0);
            JinshiParkingSetup jinshiParkingSetup1 = new JinshiParkingSetup();
            BeanUtils.copyProperties(jinshiParkingSetup,jinshiParkingSetup1);
            jinshiParkingSetup1.setId(null);
            jinshiParkingSetup1.setJpsParkId(jinshiParking.getJpId());
            jinshiParkingSetup1.setJpsAgentId(agent.getId());
            jinshiParkingSetupMapper.insert(jinshiParkingSetup1);
        }
        //添加车场的时候添加默认车场管理员
        User user = new User();
        user.setParkid(jinshiParking.getJpId());
        user.setAgentid(agent.getId());
        user.setUserName(record.getJpLoginname());
        user.setRoleName(record.getJpRoleName());
        user.setPassWord(ShiroConfig.stringMD5("123456"));
        user.setProvince(record.getJpProvince());
        user.setCity(record.getJpCity());
        user.setDistrict(record.getJpDistrict());
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(JinshiParking record) {
        return jinshiParkingMapper.insertSelective(record);
    }

    @Override
    public JinshiParking selectByPrimaryKey(Integer id) {
        return jinshiParkingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(JinshiParking record) {
        return jinshiParkingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JinshiParking record) {
        return jinshiParkingMapper.updateByPrimaryKey(record);
    }

    @Override
    public JinshiParking selectByJpId(Integer jpId) {
        return jinshiParkingMapper.selectByJpId(jpId);
    }

    @Override
    public List<JinshiParking> selectParkingAll(){return jinshiParkingMapper.selectParkingAll();}

    @Override
    public JinshiParking selectByNumber(String jpNumber){
        return jinshiParkingMapper.selectByNumber(jpNumber);
    }

    @Override
    public List<JinshiParking> selectParkingForPage(Integer pageNum, Integer pageSize,Integer agentId) {

        return jinshiParkingMapper.selectParkingForPage(pageNum,pageSize,agentId);
    }

    @Override
    public int getParkingTotalRecords(Integer agentId) {
        return jinshiParkingMapper.getParkingTotalRecords(agentId);
    }

    @Override
    public List<JinshiParking> searchParking(String keyWord, Integer pageNum, Integer pageSize,Integer agentId) {
        return jinshiParkingMapper.searchParking(keyWord,pageNum,pageSize,agentId);
    }

    @Override
    public List<Agent> selectAllAgent() {
        return agentMapper.selectAllAgent();
    }

    @Override
    public List<JinshiParking> selectAllParkingName(Integer jpId) {
        return jinshiParkingMapper.selectAllParkingName(jpId);
    }

    @Override
    public List<JinshiParking> selectAllPark() {
        return jinshiParkingMapper.selectAllPark();
    }

    @Override
    public List<JinshiParking> selectParkByParkId(Integer parkId) {
        return jinshiParkingMapper.selectParkByParkId(parkId);
    }
}
