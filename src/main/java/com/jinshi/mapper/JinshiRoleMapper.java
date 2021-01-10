package com.jinshi.mapper;

import com.jinshi.entity.JinshiRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JinshiRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiRole record);

    int insertSelective(JinshiRole record);

    JinshiRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiRole record);

    int updateByPrimaryKey(JinshiRole record);

    List<JinshiRole> selectRolename();

    List<JinshiRole> selectByUsername(String username);

    List<String> selectByUsernameList();

    List<JinshiRole> selectRoleForPage(Integer id ,Integer pageNum, Integer pageSize,Integer parkid);

    int getRoleTotalRecords();

    List<String> selectByRolename(String roleName);

    int updateRoleByRolename(Integer id,String rolename, String rolearray,Integer parkid,Integer agentid);

    String selectUserRolename(String rolename);

    JinshiRole selectRoles(String roleName);

    String selectArrayByRolename(String rolename);

    List<JinshiRole> selectRoleForAdmin(Integer pageNum, Integer pageSize);

    List<JinshiRole> selectRoleForAgent(Integer pageNum, Integer pageSize, Integer parkId);

    int getRoleTotalForAgent(Integer parkId);

    List<JinshiRole> selectRoleForPark(Integer pageNum, Integer pageSize, Integer parkId);

    int getRoleTotalForPark(Integer parkId);
}