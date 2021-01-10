package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.BlackList;
import com.jinshi.entity.LincensePlate;
import com.jinshi.service.BlackListService;
import com.jinshi.service.LincensePlateService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/blackList")
@Api(tags = "黑名单管理")
public class BlackListController {
    private static Logger logger = Logger.getLogger(BlackListController.class.getName());
    @Autowired
    private BlackListService blackListService;

    @Autowired
    private LincensePlateService lincensePlateService;

    @RequestMapping("/checkBlackListByLincese")
    @ResponseBody
    public BlackList checkBlackListByLincese(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        BlackList blackList = JSONObject.parseObject(jsonParam.toJSONString(), BlackList.class);
        String lincensePlateNumber = blackList.getLincensePlateNumber();
        return blackListService.checkBlackListByLincese(lincensePlateNumber);
    }

    @PostMapping("/checkBlackListByLinceseAndParkId")
    @ResponseBody
    public String checkBlackListByLinceseAndParkId(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        BlackList blackList = JSONObject.parseObject(jsonParam.toJSONString(), BlackList.class);
        String lincensePlateNumber = blackList.getLincensePlateNumber();
        Integer parkId = blackList.getParkId();
        List<BlackList> blackListList = blackListService.checkBlackListByLinceseAndParkId(lincensePlateNumber, parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("blackList",blackListList);
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/selectBlackListAll",method = RequestMethod.POST)
    @ApiOperation(value = "黑名单管理查询所有")
    public String selectBlackListAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<BlackList> res = blackListService.selectBlackListForPage(pageNum,pageSize,parkId);
        int blackListTotalRecords = blackListService.getBlackListTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("blackListData",res);
        resJo.put("blackListTotalRecords",blackListTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchBlackList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "黑名单管理搜索")
    public String searchBlackList(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        Integer parkId = (Integer) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<BlackList> res = blackListService.searchBlackList(keyWord,pageNum,pageSize,parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("blackListData",res);
        return resJo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "黑名单管理删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer blackListid = (Integer) jsonParam.get("id");
        int i = blackListService.deleteByPrimaryKey(blackListid);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    @ApiOperation(value = "黑名单管理编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        BlackList blackList = new BlackList();
        blackList = JSONObject.parseObject(jsonParam.toJSONString(), BlackList.class);
        blackList.setCreateTime(new Date());
        return blackListService.updateByPrimaryKey(blackList);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "黑名单管理添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject jsonObject = jsonParam.getJSONObject("setData");
        BlackList blackList = new BlackList();
        blackList.setName((String) jsonObject.get("name"));
        blackList.setLincensePlateNumber((String) jsonObject.get("lincensePlateNumber"));
        blackList.setDescribtion((String) jsonObject.get("describtion"));
        blackList.setListType((String) jsonObject.get("listType"));
        blackList.setCreateTime(new Date());
        blackList.setIsFlag("未关注");
        blackList.setParkId(Integer.valueOf((String) jsonObject.get("parkId")));
        blackList.setAgentId(Integer.valueOf((String) jsonObject.get("agentId")));
        BlackList checkMemberByLincese = blackListService.checkBlackListByLincese((String) jsonObject.get("lincensePlateNumber"));
        if(checkMemberByLincese!=null){
            return 5;
        }
        return blackListService.insert(blackList);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(BlackList record) {

        return blackListService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public BlackList selectByPrimaryKey(Integer id) {

        return blackListService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(BlackList record) {

        return blackListService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/updateFlagZero", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "黑名单管理未关注改为已关注")
    public String updateFlagZero(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        String isFlag = (String) jsonParam.get("isFlag");
        Integer id = (Integer) jsonParam.get("id");
        String lincensePlateNumber = (String) jsonParam.get("lincensePlateNumber");
        JSONObject resJo = new JSONObject();

        if ("未关注".equals(isFlag)) {
            List<LincensePlate> lincensePlateRecords =  lincensePlateService.selectByLincensePlate(lincensePlateNumber);
            if(lincensePlateRecords.size()>0){
                resJo.put("lincensePlateRecords",lincensePlateRecords.get(0));
            }else{
                resJo.put("lincensePlateRecords",null);
            }
            blackListService.updateByIsFlag(id);
            isFlag = "已关注";
            resJo.put("isFlag",isFlag);
//            resJo.put("lincensePlateRecords",lincensePlateRecords.get(0));
        }

        return resJo.toJSONString();
    }

    @RequestMapping(value = "/updateFlagOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "黑名单管理未已关注改为未关注")
    public String updateFlagOne(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        String isFlag = (String) jsonParam.get("isFlag");
        Integer id = (Integer) jsonParam.get("id");
        JSONObject resJo = new JSONObject();

        if ("已关注".equals(isFlag)) {
            blackListService.updateByIsFlagNo(id);
            isFlag = "未关注";
            resJo.put("isFlag",isFlag);
        }
        return resJo.toJSONString();
    }

}
