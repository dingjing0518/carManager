package com.jinshi.service;

import com.jinshi.entity.JinshiRole;

import java.util.List;
import java.util.Map;

public interface JinshiRoleService {

    Integer insert(JinshiRole jinshiRole);

    int getRoleTotalRecords();

    Integer deleteByPrimaryKey(Integer id);

    JinshiRole updateRole(Integer id,String rolename, String rolearray,Integer parkid,Integer agentid);

    String selectUserRolename(String rolename);

    List<JinshiRole> selectRoleForAdmin(Integer pageNum, Integer pageSize);

    List<JinshiRole> selectRoleForAgent(Integer pageNum, Integer pageSize, Integer parkId);

    int getRoleTotalForAgent(Integer parkId);

    List<JinshiRole> selectRoleForPark(Integer pageNum, Integer pageSize, Integer parkId);

    int getRoleTotalForPark(Integer parkId);

    List<JinshiRole> selectRoleForPage(Integer pageNum, Integer pageSize, String roleName, String parkId);
}
