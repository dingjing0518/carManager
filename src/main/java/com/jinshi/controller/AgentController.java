package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Agent;
import com.jinshi.service.AgentService;
import com.jinshi.util.QianYiCameraUtil;
//import javafx.geometry.Pos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RestController
@CrossOrigin
@RequestMapping("/agent")
@Api(tags = "代理商管理")
public class AgentController {

    private static Logger logger = Logger.getLogger(AgentController.class);
    private static Logger log = LogManager.getLogger(AgentController.class.getName());
    @Autowired
    private AgentService agentService;

    /**
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectAgentAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "代理商管理查询所有")
    public String selectAgentAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String agentId = (String) jsonParam.get("agentId");
        String roleName = (String) jsonParam.get("roleName");
        pageNum = (pageNum-1)*pageSize;
        List<Agent> res= new ArrayList<>();
        int getaGentRecords = 0;
        if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
            res = agentService.selectAgentPageForAdmin(pageNum, pageSize);
            getaGentRecords = agentService.getaGentRecordsForAdmin();
        } else {
            res= agentService.selectAgentPage(pageNum,pageSize,Integer.parseInt(agentId));
            getaGentRecords = agentService.getaGentRecords(Integer.parseInt(agentId));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AgentData",res);
        jsonObject.put("AgentRecords",getaGentRecords);
        return jsonObject.toJSONString();
    }

    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchAgent",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "代理商管理搜索")
    public String searchAgent(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<Agent> res = agentService.searchAgent(keyWord,pageNum,pageSize);
        int memberTotalRecords = agentService.getAgentRecordsSearch(keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("AgentData",res);
        resJo.put("AgentRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /***
     * 根据用户名字查找
     * @param
     * @return
     */
    @ApiOperation(value = "根据用户名字查找")
    @RequestMapping(value = "/selectByuserName",method = RequestMethod.POST)
    @ResponseBody
    public Agent selectByuserName(String username) {

        return agentService.selectByuserName(username);
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据id删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return agentService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "代理商管理添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        Agent agent = JSONObject.parseObject(jsonParam.toJSONString(), Agent.class);
        return agentService.insert(agent);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(Agent record) {
        return agentService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    @ResponseBody
    @ApiOperation(value = "根据id查询代理商")
    public Agent selectByPrimaryKey(@RequestParam("id") Integer id) {
        return agentService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(Agent record) {

        return agentService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "代理商管理编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Agent agent  = JSONObject.parseObject(jsonParam.toJSONString(), Agent.class);
        return agentService.updateByPrimaryKey(agent);
    }

    @GetMapping(value = "/findAll")
    @ResponseBody
    public List<Agent> findAll() {
        logger.info("---   agentService.findAll();------------");
        log.info("---   agentService222222222------------");
        Agent agent = agentService.selectByPrimaryKey(1);
        System.out.println(JSONObject.toJSON(agent));
        log.info("---   agentService11111111111111------------");
        return agentService.findAll();
    }
}
