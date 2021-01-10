package com.jinshi.mapper;

import com.jinshi.entity.Agent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgentMapper {

    int deleteByPrimaryKey(Integer id);

    Integer insert(Agent record);

    int insertSelective(Agent record);

    Agent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);

    List<Agent> selectAgentAll();

    /***
     * 分页
     * @param pageNum
     * @param pageSize
     * @param parkId
     * @return
     */
    List<Agent> selectAgentPage(Integer pageNum, Integer pageSize, Integer agentId);

    /***
     * 搜索
     * @param keyWork
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Agent> searchAgent(@Param(value="keyWork") String keyWork, @Param(value = "pageNum") Integer pageNum, @Param(value = "pageSize") Integer pageSize);

    int getaGentRecords(Integer agentId);

    Agent selectByuserName(String username);

    List<Agent> selectAllAgent();

    List<Agent> findAll();

    int getAgentRecordsSearch(String keyWord);

    List<Agent> selectAgentPageForAdmin(Integer pageNum, Integer pageSize);

    int getaGentRecordsForAdmin();

    int updateAgentNumber(Agent record);
}