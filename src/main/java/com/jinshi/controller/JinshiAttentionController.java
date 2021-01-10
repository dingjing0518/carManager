package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiAttention;
import com.jinshi.service.JinshiAttentionService;
import com.jinshi.util.GlobalVariable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/jinshiAttention")
@Api(tags = "关注管理")
public class JinshiAttentionController {

    private static Logger logger = Logger.getLogger(JinshiAttentionController.class.getName());

    @Autowired
    private JinshiAttentionService jinshiAttentionService;

    @RequestMapping(value = "/selectAttentionAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "关注管理---查询所有")
    public String selectAttentionAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiAttention> res = jinshiAttentionService.selectAttentionForPage(pageNum,pageSize,parkId);
        int attentionTotalRecords = jinshiAttentionService.getAttentionTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("attentionData",res);
        resJo.put("attentionTotalRecords",attentionTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchByAttention",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注管理---搜索")
    public String searchAttention(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiAttention> res = jinshiAttentionService.searchAttention(keyWord,pageNum,pageSize,parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("attentionData",res);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/findAttention",method = RequestMethod.POST)
    @ResponseBody
    public JinshiAttention findAttention(@RequestBody JSONObject jsonParam){
        String keyWord = (String) jsonParam.get("keyWord");
//        List<JinshiAttention> res = jinshiAttentionService.searchAttention(keyWord,0,20);
//        if(res.size()>0){
//            return res.get(0);
//        }else{
//            return null;
//        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/searchAttentionUtil",method = RequestMethod.POST)
    @ResponseBody
    public JinshiAttention searchAttentionUtil(@RequestBody JSONObject jsonParam){
        String jcLincensePlateId = (String) jsonParam.get("jcLincensePlateId");
        List<JinshiAttention> res = jinshiAttentionService.searchAttentionUtil(jcLincensePlateId);
        if(res.size()>0){
            return res.get(0);
        }else{
            return null;
        }
    }


//    @RequestMapping(value = "/searchByAttention",method = RequestMethod.POST)
//    @ResponseBody
//    public String searchByAttention(@RequestBody JSONObject jsonParam){
//        logger.info(jsonParam.toJSONString());
//        Integer pageNum = (Integer) jsonParam.get("pageNum");
//        Integer pageSize = (Integer) jsonParam.get("pageSize");
//        String keyWord = (String) jsonParam.get("keyWord");
//        String parkId = (String) jsonParam.get("parkId");
//        List<JinshiAttention> JinshiAttention = new ArrayList<>();
//        JSONObject resJo = new JSONObject();
//        try {
//            JinshiAttention= jinshiAttentionService.searchAttention(keyWord,pageNum,pageSize,parkId);
//            resJo.put("attentionData",JinshiAttention);
//        }catch (Exception e){
//            e.printStackTrace();
//            return resJo.toJSONString();
//        }
//        logger.info(resJo.toJSONString());
//        return resJo.toJSONString();
//    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注管理---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiAttention jinshiAttention = new JinshiAttention();
        jinshiAttention.setJcLincensePlateId((String) jsonParam.get("jcLincensePlateId"));
        jinshiAttention.setJcPhone((String) jsonParam.get("jcPhone"));
        jinshiAttention.setJcWechat((String) jsonParam.get("jcWechat"));
        jinshiAttention.setJcAgent(GlobalVariable.parkId.toString());
        jinshiAttention.setJcParkid(GlobalVariable.agentId.toString());
        jinshiAttention.setJcTitle((String) jsonParam.get("title"));
        jinshiAttention.setJcRemark((String) jsonParam.get("jcRemark"));
        return jinshiAttentionService.insert(jinshiAttention);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注管理---删除")
    public Integer deleteById(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        Integer id = (Integer) jsonParam.get("id");
        return jinshiAttentionService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByAttention",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注管理---编辑")
    public Integer updateByAttention(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        JinshiAttention jinshiAttention = JSONObject.parseObject(jsonParam.toJSONString(), JinshiAttention.class);
        return jinshiAttentionService.updateByPrimaryKey(jinshiAttention);
    }
}
