package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiRole;
import com.jinshi.service.JinshiRoleService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.SortUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/jinshiRole")
@Api(tags = "权限管理")
public class JinshiRoleController {

    private static Logger logger = Logger.getLogger(JinshiRoleController.class.getName());

    @Autowired
    private JinshiRoleService jinshiRoleService;

    /**
     * 添加权限
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "权限管理---添加权限")
    public JSONObject insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiRole jinshiRole = new JinshiRole();
        JSONObject json = new JSONObject();
        String rolename = (String) jsonParam.get("jsRolename");
        String rolename1 =  jinshiRoleService.selectUserRolename(rolename);
        if (rolename1 == null) {
            jinshiRole.setJsRolename(rolename);
            String jsRolearray = (String) jsonParam.get("jsRolearray");
            String sort = SortUtil.sort(jsRolearray);
            jinshiRole.setJsRolearray(sort);
            jinshiRole.setJsUserid((Integer) jsonParam.get("userid"));
            jinshiRole.setParkid(Integer.valueOf((String) jsonParam.get("parkId")));
            jinshiRole.setAgentid(Integer.valueOf((String) jsonParam.get("agentId")));
            jinshiRoleService.insert(jinshiRole);
            json.put("code","0");
            return json;
        } else{
            json.put("code", "1");
            return json;
        }
    }

    @RequestMapping(value = "/selectRoleAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "权限管理---查询所有")
    public String selectRoleAll(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        Integer id = (Integer) jsonParam.get("id");
        String roleName = (String) jsonParam.get("roleName");
        String parkId = (String) jsonParam.get("parkId");
        List<JinshiRole> res = jinshiRoleService.selectRoleForPage(pageNum,pageSize,roleName,parkId);
        int memberTotalRecords = 0;
        if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
            memberTotalRecords = jinshiRoleService.getRoleTotalRecords();
        } else if (roleName.contains("代理商")){
            memberTotalRecords = jinshiRoleService.getRoleTotalForAgent(Integer.parseInt(parkId));
        } else if ("车场管理员".equals(roleName)) {
            memberTotalRecords = jinshiRoleService.getRoleTotalForPark(Integer.parseInt(parkId));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("roleData",res);
        resJo.put("roleTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "权限管理---编辑")
    public JinshiRole updateRole (@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Integer id = (Integer) jsonParam.get("id");
        String rolename = (String) jsonParam.get("jsRolename");
        String rolearray = (String) jsonParam.get("jsRolearray");
        String sort = SortUtil.sort(rolearray);
        String parkid = (String) jsonParam.get("parkId");
        String agentid = (String) jsonParam.get("agentId");
        return jinshiRoleService.updateRole(id,rolename,sort,Integer.parseInt(parkid),Integer.parseInt(agentid));
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "权限管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Integer id = (Integer) jsonParam.get("id");
        return jinshiRoleService.deleteByPrimaryKey(id);
    }

}

