package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiRole;
import com.jinshi.entity.JinshiUserRole;
import com.jinshi.entity.JinshiWeb;
import com.jinshi.mapper.JinshiRoleMapper;
import com.jinshi.mapper.JinshiWebMapper;
import com.jinshi.service.JinshiRoleService;
import com.jinshi.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JinshiRoleServiceImpl implements JinshiRoleService {

    @Autowired
    private JinshiRoleMapper jinshiRoleMapper;

    @Autowired
    private JinshiWebMapper jinshiWebMapper;

    @Override
    public Integer insert(JinshiRole jinshiRole) {
        return jinshiRoleMapper.insert(jinshiRole);
    }

    @Override
    public List<JinshiRole> selectRoleForPage(Integer pageNum, Integer pageSize,String roleName, String parkId) {
        List<JinshiRole> jinshiRoles = new ArrayList<>();
        if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
            jinshiRoles = jinshiRoleMapper.selectRoleForAdmin(pageNum, pageSize);
        } else if (roleName.contains("代理商")){
            jinshiRoles = jinshiRoleMapper.selectRoleForAgent(pageNum,pageSize,Integer.parseInt(parkId));
        } else if ("车场管理员".equals(roleName)) {
            jinshiRoles = jinshiRoleMapper.selectRoleForPark(pageNum,pageSize,Integer.parseInt(parkId));
        }
//        List<JinshiRole> jinshiRoles = jinshiRoleMapper.selectRoleForPage(id ,pageNum, pageSize,parkId);
        List<JinshiWeb> jinshiWebs = jinshiWebMapper.selectAll();
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();
        for (JinshiWeb jinshiWeb : jinshiWebs) {
            Integer ids = jinshiWeb.getId();
            String title = jinshiWeb.getTitle();
            integerStringHashMap.put(ids,title);
        }
        for(JinshiRole jinshiUserRole : jinshiRoles){
            String jsRole = jinshiUserRole.getJsRolearray();
            String sort = SortUtil.sort(jsRole);
            List<String> jsRoleArrayList = new ArrayList<>();
            String[] split = sort.split(",");
            for (int i=0;i<split.length;i++){
                int i1 = Integer.parseInt(split[i]);
                String s = integerStringHashMap.get(i1);
                jsRoleArrayList.add(s);
            }
            jinshiUserRole.setJsRolearray(jsRoleArrayList.toString());
            jinshiUserRole.setJsRolearrayForWeb(sort);
        }
        return jinshiRoles;
    }

    @Override
    public int getRoleTotalRecords() {
        return jinshiRoleMapper.getRoleTotalRecords();
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiRoleMapper.deleteByPrimaryKey(id);
    }


    @Override
    public JinshiRole updateRole(Integer id,String rolename, String rolearray,Integer parkid,Integer agentid) {

        jinshiRoleMapper.updateRoleByRolename(id,rolename,rolearray,parkid,agentid);
        JinshiRole jinshiRole = jinshiRoleMapper.selectByPrimaryKey(id);
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();
        List<JinshiWeb> jinshiWebs = jinshiWebMapper.selectAll();

        for (JinshiWeb jinshiWeb : jinshiWebs) {
            Integer ids = jinshiWeb.getId();
            String title = jinshiWeb.getTitle();
            integerStringHashMap.put(ids,title);
        }

        String jsRolearray = jinshiRole.getJsRolearray();
        List<String> jsRoleArrayList = new ArrayList<>();
        String[] split = jsRolearray.split(",");
        for (int i=0;i<split.length;i++){
            int i1 = Integer.parseInt(split[i]);
            String s = integerStringHashMap.get(i1);
            jsRoleArrayList.add(s);
        }
        jinshiRole.setJsRolearray(jsRoleArrayList.toString());

        return jinshiRole;
    }

    @Override
    public String selectUserRolename(String rolename) {
        return jinshiRoleMapper.selectUserRolename(rolename);
    }

    @Override
    public List<JinshiRole> selectRoleForAdmin(Integer pageNum, Integer pageSize) {
        return jinshiRoleMapper.selectRoleForAdmin(pageNum,pageSize);
    }

    @Override
    public List<JinshiRole> selectRoleForAgent(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiRoleMapper.selectRoleForAgent(pageNum,pageSize,parkId);
    }

    @Override
    public int getRoleTotalForAgent(Integer parkId) {
        return jinshiRoleMapper.getRoleTotalForAgent(parkId);
    }

    @Override
    public List<JinshiRole> selectRoleForPark(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiRoleMapper.selectRoleForPark(pageNum,pageSize,parkId);
    }

    @Override
    public int getRoleTotalForPark(Integer parkId) {
        return jinshiRoleMapper.getRoleTotalForPark(parkId);
    }
}
