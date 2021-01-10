package com.jinshi.serviceImpl;

import com.jinshi.entity.Agent;
import com.jinshi.entity.User;
import com.jinshi.mapper.AgentMapper;
import com.jinshi.mapper.ShAreaMapper;
import com.jinshi.mapper.UserMapper;
import com.jinshi.service.AgentService;
import com.jinshi.util.ShiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShAreaMapper shAreaMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return agentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Agent record) {
        agentMapper.insert(record);

        //添加代理商编号测试代码
        //此处添加代理商编号，前面是具体到区县的编号，后面id是3位，如果不足用0补齐
//        String size = String.valueOf(record.getId());
//        String agentNumber = record.getAgentNumber();//这个字段前端传区县那条记录的 zip_code 的值
//        StringBuilder sb = new StringBuilder();
//        sb.append(agentNumber);
//        while (size.length() < 3) {
//            StringBuilder sb1 = new StringBuilder();
//            sb1.append("0");
//            sb1.append(size);
//            size = sb1.toString();
//        }
//        sb.append(size);
//        record.setAgentNumber(sb.toString());
//        agentMapper.updateAgentNumber(record);

        User user = new User();
        user.setAgentid(record.getId());
        user.setRoleName(record.getRoleName());
        user.setPassWord(ShiroConfig.stringMD5(record.getLoginPassword()));
        user.setUserName(record.getLoginName());
        user.setProvince(record.getProvince());
        user.setCity(record.getCity());
        user.setDistrict(record.getDistrict());
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(Agent record) {
        return agentMapper.insertSelective(record);
    }

    @Override
    public Agent selectByPrimaryKey(Integer id) {
        return agentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Agent record) {
        return agentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Agent record) {
        return agentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Agent> selectAgentAll(){return agentMapper.selectAgentAll();}

    @Override
    public List<Agent> selectAgentPage(Integer pageNum, Integer pageSize, Integer agentId) {
        return agentMapper.selectAgentPage(pageNum,pageSize,agentId);
    }

    @Override
    public List<Agent> searchAgent(String keyWork, Integer pageNum, Integer pageSize) {
        return agentMapper.searchAgent(keyWork,pageNum,pageSize);
    }

    @Override
    public int getaGentRecords(Integer agentId) {
        return agentMapper.getaGentRecords(agentId);
    }

    @Override
    public Agent selectByuserName(String username) {
        return selectByuserName(username);
    }

    @Override
    public List<Agent> findAll() {
        return agentMapper.findAll();
    }

    @Override
    public int getAgentRecordsSearch(String keyWord) {
        return agentMapper.getAgentRecordsSearch(keyWord);
    }

    @Override
    public List<Agent> selectAgentPageForAdmin(Integer pageNum, Integer pageSize) {
        return agentMapper.selectAgentPageForAdmin(pageNum,pageSize);
    }

    @Override
    public int getaGentRecordsForAdmin() {
        return agentMapper.getaGentRecordsForAdmin();
    }
}
