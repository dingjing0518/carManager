package com.jinshi.service;

import com.jinshi.entity.Agent;

import java.util.List;
public interface AgentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Agent record);

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
    List<Agent> searchAgent(String keyWork,Integer pageNum,Integer pageSize);

    int getaGentRecords(Integer agentId);

    Agent selectByuserName(String username);

    List<Agent> findAll();

    int getAgentRecordsSearch(String keyWord);

    List<Agent> selectAgentPageForAdmin(Integer pageNum, Integer pageSize);

    int getaGentRecordsForAdmin();

}
