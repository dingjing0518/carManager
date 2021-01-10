package com.jinshi.serviceImpl;

import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.JinshiBusinessAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JinshiBusinessAccountServiceImpl implements JinshiBusinessAccountService {

    @Autowired
    private JinshiBusinessAccountMapper jinshiBusinessAccountMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Autowired
    private JinshiAreaMapper jinshiAreaMapper;

    @Override
    public List<JinshiBusinessAccountBo> selectBusinessAccountPage(Integer pageNum, Integer pageSize,Integer parkId) {
        List<JinshiBusinessAccountBo> list = new ArrayList<>();
        List<JinshiBusinessAccount> jinshiBusinessAccountList = jinshiBusinessAccountMapper.selectBusinessAccountPage(pageNum, pageSize,parkId);
        for (JinshiBusinessAccount jinshiBusinessAccount : jinshiBusinessAccountList) {
            JinshiBusinessAccountBo jinshiBusinessAccountBo = new JinshiBusinessAccountBo();
            jinshiBusinessAccountBo.setBcId(jinshiBusinessAccount.getBcId());
            jinshiBusinessAccountBo.setBcName(jinshiBusinessAccount.getBcName());
            jinshiBusinessAccountBo.setBcCode(jinshiBusinessAccount.getBcCode());
            jinshiBusinessAccountBo.setBcPhone(jinshiBusinessAccount.getBcPhone());
            jinshiBusinessAccountBo.setBcTel(jinshiBusinessAccount.getBcTel());
            jinshiBusinessAccountBo.setBcWechat(jinshiBusinessAccount.getBcWechat());
            jinshiBusinessAccountBo.setBcQq(jinshiBusinessAccount.getBcQq());
            jinshiBusinessAccountBo.setBcOpenid(jinshiBusinessAccount.getBcOpenid());
            jinshiBusinessAccountBo.setBcContactsName(jinshiBusinessAccount.getBcContactsName());
            jinshiBusinessAccountBo.setBcUsername(jinshiBusinessAccount.getBcUsername());
            jinshiBusinessAccountBo.setBcPassword(jinshiBusinessAccount.getBcPassword());
            jinshiBusinessAccountBo.setBcCreatetime(jinshiBusinessAccount.getBcCreatetime());
            jinshiBusinessAccountBo.setBcRemarks(jinshiBusinessAccount.getBcRemarks());

            Integer bcAgentId = jinshiBusinessAccount.getBcAgentId();
            Agent agent = agentMapper.selectByPrimaryKey(bcAgentId);
            jinshiBusinessAccountBo.setBcAgentName(agent.getUsername());

            Integer bcParkingId = jinshiBusinessAccount.getBcParkingId();
            JinshiParking jinshiParking = jinshiParkingMapper.selectByPrimaryKey(bcParkingId);
            jinshiBusinessAccountBo.setBcParkingName(jinshiParking.getJpName());

            Integer bcAreaId = jinshiBusinessAccount.getBcAreaId();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByPrimaryKey(bcAreaId);
            jinshiBusinessAccountBo.setBcAreaName(jinshiArea.getAreaName());
            list.add(jinshiBusinessAccountBo);
        }
        return list;
    }

    @Override
    public int getBusinessAccountRecords(Integer parkId) {
        return jinshiBusinessAccountMapper.getBusinessAccountRecords(parkId);
    }

    @Override
    public List<JinshiBusinessAccount> searchBusinessAccount(String keyWord, Integer pageNum, Integer pageSize) {
        return jinshiBusinessAccountMapper.searchBusinessAccount(keyWord,pageNum,pageSize);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiBusinessAccountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(JinshiBusinessAccountBo jinshiBusinessAccountBo) {
        JinshiBusinessAccount jinshiBusinessAccount = new JinshiBusinessAccount();
        jinshiBusinessAccount.setBcName(jinshiBusinessAccountBo.getBcName());
        jinshiBusinessAccount.setBcCode(jinshiBusinessAccountBo.getBcCode());
        jinshiBusinessAccount.setBcPhone(jinshiBusinessAccountBo.getBcPhone());
        jinshiBusinessAccount.setBcTel(jinshiBusinessAccountBo.getBcTel());
        jinshiBusinessAccount.setBcWechat(jinshiBusinessAccountBo.getBcWechat());
        jinshiBusinessAccount.setBcQq(jinshiBusinessAccountBo.getBcQq());
        jinshiBusinessAccount.setBcOpenid(jinshiBusinessAccountBo.getBcOpenid());
        jinshiBusinessAccount.setBcContactsName(jinshiBusinessAccountBo.getBcContactsName());
        jinshiBusinessAccount.setBcUsername(jinshiBusinessAccountBo.getBcUsername());
        jinshiBusinessAccount.setBcPassword("123456");
        jinshiBusinessAccount.setBcCreatetime(new Date());
        jinshiBusinessAccount.setBcRemarks(jinshiBusinessAccountBo.getBcRemarks());
        String bcAgentName = jinshiBusinessAccountBo.getBcAgentName();
//        Agent agent = agentMapper.selectByuserName(bcAgentName);
        jinshiBusinessAccount.setBcAgentId(Integer.parseInt(bcAgentName));
        jinshiBusinessAccountBo.setAgentId(Integer.parseInt(bcAgentName));
        String bcParkingName = jinshiBusinessAccountBo.getBcParkingName();
        JinshiParking jinshiParking = jinshiParkingMapper.selectByJpName(bcParkingName);
        Integer a = jinshiParking.getJpId();
        jinshiBusinessAccount.setBcParkingId(jinshiParking.getJpId());
        String bcAreaName = jinshiBusinessAccountBo.getBcAreaName();
        JinshiArea jinshiArea = jinshiAreaMapper.findByAreaName(bcAreaName,jinshiBusinessAccountBo.getParkId());
        jinshiBusinessAccount.setBcAreaId(jinshiArea.getId());
        jinshiBusinessAccountBo.setAreaId(jinshiArea.getId());
        JinshiBusinessAccount findbc = jinshiBusinessAccountMapper.findbc(jinshiBusinessAccountBo);
        if (findbc ==null) {
             jinshiBusinessAccountMapper.insert(jinshiBusinessAccount);
        }else {
            return 500;
        }
        return 200;
    }

    @Override
    public Integer updateByPrimaryKey(JinshiBusinessAccountBo jinshiBusinessAccountBo) {

        JinshiBusinessAccount jinshiBusinessAccount = new JinshiBusinessAccount();
        jinshiBusinessAccount.setBcId(jinshiBusinessAccountBo.getBcId());
        jinshiBusinessAccount.setBcName(jinshiBusinessAccountBo.getBcName());
        jinshiBusinessAccount.setBcCode(jinshiBusinessAccountBo.getBcCode());
        jinshiBusinessAccount.setBcPhone(jinshiBusinessAccountBo.getBcPhone());
        jinshiBusinessAccount.setBcTel(jinshiBusinessAccountBo.getBcTel());
        jinshiBusinessAccount.setBcWechat(jinshiBusinessAccountBo.getBcWechat());
        jinshiBusinessAccount.setBcQq(jinshiBusinessAccountBo.getBcQq());
        jinshiBusinessAccount.setBcOpenid(jinshiBusinessAccountBo.getBcOpenid());
        jinshiBusinessAccount.setBcContactsName(jinshiBusinessAccountBo.getBcContactsName());
        jinshiBusinessAccount.setBcUsername(jinshiBusinessAccountBo.getBcUsername());
        jinshiBusinessAccount.setBcPassword(jinshiBusinessAccountBo.getBcPassword());
        jinshiBusinessAccount.setBcCreatetime(new Date());
        jinshiBusinessAccount.setBcRemarks(jinshiBusinessAccountBo.getBcRemarks());

        String bcAgentName = jinshiBusinessAccountBo.getBcAgentName();
        Agent agent = agentMapper.selectByuserName(bcAgentName);
        jinshiBusinessAccount.setBcAgentId(agent.getId());

        String bcParkingName = jinshiBusinessAccountBo.getBcParkingName();
        JinshiParking jinshiParking = jinshiParkingMapper.selectByJpName(bcParkingName);
        jinshiBusinessAccount.setBcParkingId(jinshiParking.getJpId());

        String bcAreaName = jinshiBusinessAccountBo.getBcAreaName();
        JinshiArea jinshiArea = jinshiAreaMapper.findByAreaName(bcAreaName,jinshiBusinessAccountBo.getParkId());
        jinshiBusinessAccount.setBcAreaId(jinshiArea.getId());

        return jinshiBusinessAccountMapper.updateByPrimaryKey(jinshiBusinessAccount);
    }

    @Override
    public List<JinshiBusinessAccount> selectAllBusinessName(Integer bpId, Integer areaId) {
        return jinshiBusinessAccountMapper.selectAllBusinessName(bpId,areaId);
    }
}
