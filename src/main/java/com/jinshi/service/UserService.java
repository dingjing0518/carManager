package com.jinshi.service;

import com.jinshi.entity.JinshiRole;
import com.jinshi.entity.JinshiUserRole;
import com.jinshi.entity.User;

import java.util.List;


public interface UserService {
    User Sel(int id);

    Integer insert(User user);

    Integer deleteByPrimaryKey(Integer id);

    List<JinshiUserRole> selectUserForPage(Integer pageNum, Integer pageSize,Integer parkid);

    int getUserTotalRecords();

    List<User> searchUser(String keyWord, Integer pageNum, Integer pageSize,Integer parkid);

    String selectByUsername(String userName);

    String selectUsername(String username);

    Integer updatePassword(User user);

    User selectAllByUserName(String username);

    int updateUser(User user);

    List<User> selectUserAll(Integer pageNum, Integer pageSize, Integer parkId);

    List<User> selectUser(Integer pageNum, Integer pageSize);

    List<User> selectByAgentId(Integer agentId);

    int getUserTotalByAgentId(Integer agentId);

    List<User> selectByParkId(Integer parkId);

    int getUserTotalByParkId(Integer parkId);
}