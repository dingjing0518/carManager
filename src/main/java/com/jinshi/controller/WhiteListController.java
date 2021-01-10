package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.BlackList;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.WhiteList;
import com.jinshi.service.BlackListService;
import com.jinshi.service.LincensePlateService;
import com.jinshi.service.WhiteListService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/whiteList")
@Api(tags = "白名单管理")
public class WhiteListController {
    private static Logger logger = Logger.getLogger(WhiteListController.class.getName());

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private LincensePlateService lincensePlateService;

    @RequestMapping("/checkWhiteListByLincese")
    @ResponseBody
    public WhiteList checkWhiteListByLincese(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        WhiteList whiteList = JSONObject.parseObject(jsonParam.toJSONString(), WhiteList.class);
        String lincensePlateNumber = whiteList.getLincensePlateNumber();
        return whiteListService.checkWhiteListByLincese(lincensePlateNumber,whiteList.getParkId());
    }
    @CrossOrigin
    @RequestMapping(value = "/selectWhiteListAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "白名单管理---查询所有")
    public String selectWhiteListAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<WhiteList> res = whiteListService.selectWhiteListForPage(pageNum,pageSize,parkId);
        int whiteListTotalRecords = whiteListService.getWhiteListTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("whiteListData",res);
        resJo.put("whiteListTotalRecords",whiteListTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchWhiteList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "白名单管理---搜索")
    public String searchWhiteList(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        Integer parkId = (Integer) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<WhiteList> res = whiteListService.searchWhiteList(keyWord,pageNum,pageSize,parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("whiteListData",res);
        return resJo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "白名单管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer whiteListid = (Integer) jsonParam.get("id");
        int i = whiteListService.deleteByPrimaryKey(whiteListid);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    @ApiOperation(value = "白名单管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        WhiteList whiteList = new WhiteList();
        whiteList = JSONObject.parseObject(jsonParam.toJSONString(), WhiteList.class);
        whiteList.setCreateTime(new Date());
        return whiteListService.updateByPrimaryKey(whiteList);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "白名单管理---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        WhiteList whiteList = new WhiteList();
        whiteList.setDescribtion((String) jsonParam.get("describtion"));
        whiteList.setLincensePlateNumber((String) jsonParam.get("lincensePlateNumber"));
        whiteList.setName((String) jsonParam.get("name"));
        whiteList.setCreateTime(new Date());
        whiteList.setIsFlag("未关注");
        whiteList.setListType((String) jsonParam.get("listType"));
        whiteList.setParkId(GlobalVariable.parkId);
        whiteList.setAgentId(GlobalVariable.agentId);
        WhiteList checkMemberByLincese = whiteListService.checkWhiteListByLincese((String) jsonParam.get("lincensePlateNumber"),GlobalVariable.parkId);
        if(checkMemberByLincese!=null){
            return 5;
        }
        return whiteListService.insert(whiteList);
    }

    @RequestMapping(value = "/updateFlagZero", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "黑白名单管理未关注改为已关注")
    public String updateFlagZero(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        String isFlag = (String) jsonParam.get("isFlag");
        Integer id = (Integer) jsonParam.get("id");
        JSONObject resJo = new JSONObject();

        if ("未关注".equals(isFlag)) {
            whiteListService.updateByIsFlag(id);
            isFlag = "已关注";
            resJo.put("isFlag",isFlag);
        }

        return resJo.toJSONString();
    }

    @RequestMapping(value = "/updateFlagOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "白名单管理未关注改为已关注")
    public String updateFlagOne(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam);
        String isFlag = (String) jsonParam.get("isFlag");
        Integer id = (Integer) jsonParam.get("id");
        JSONObject resJo = new JSONObject();

        if ("已关注".equals(isFlag)) {
            whiteListService.updateByIsFlagNo(id);
            isFlag = "未关注";
            resJo.put("isFlag",isFlag);
        }
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectFlag", method = RequestMethod.POST)
    @ResponseBody
    public String selectFlag(@RequestBody JSONObject jsonParam) throws ParseException{
        logger.info(jsonParam);
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        JSONObject resJo = new JSONObject();
        List<LincensePlate> lincensePlate = lincensePlateService.selectByWbPlate(pageNum,pageSize,parkIdStr);
        resJo.put("lincensePlate",lincensePlate);
        return resJo.toJSONString();
    }

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return whiteListService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(WhiteList record) {

        return whiteListService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(WhiteList record) {

        return whiteListService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public WhiteList selectByPrimaryKey(Integer id) {

        return whiteListService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(WhiteList record) {

        return whiteListService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(WhiteList record) {

        return whiteListService.updateByPrimaryKey(record);
    }

}
