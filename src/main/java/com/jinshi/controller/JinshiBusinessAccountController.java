package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiBusinessAccount;
import com.jinshi.entity.JinshiBusinessAccountBo;
import com.jinshi.service.JinshiBusinessAccountService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JinshiBusinessAccount")
@CrossOrigin
@Api(tags = "商户管理")
public class JinshiBusinessAccountController {
    private static Logger logger = Logger.getLogger(JinshiBusinessAccountController.class.getName());
    @Autowired
    private JinshiBusinessAccountService jinshiBusinessAccountService;

    /***
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectBusinessAccountAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "商户管理---查询所有")
    public String selectBusinessAccountAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String)jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiBusinessAccountBo> res= jinshiBusinessAccountService.selectBusinessAccountPage(pageNum,pageSize,parkId);
        int businessAccountRecords = jinshiBusinessAccountService.getBusinessAccountRecords(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessAccountData",res);
        jsonObject.put("BusinessAccountRecords",businessAccountRecords);
        return jsonObject.toJSONString();
    }

    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchBusinessAccount",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "商户管理---搜索")
    public String searchBusinessAccount(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiBusinessAccount> res = jinshiBusinessAccountService.searchBusinessAccount(keyWord,pageNum,pageSize);
//        int businessAccountRecords = jinshiBusinessAccountService.getBusinessAccountRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("BusinessAccountData",res);
//        resJo.put("BusinessAccountRecords",businessAccountRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "商户管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiBusinessAccountService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "商户管理---添加")
    public Integer insert(@RequestBody JinshiBusinessAccountBo jinshiBusinessAccountBo) {
        return jinshiBusinessAccountService.insert(jinshiBusinessAccountBo);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "商户管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JinshiBusinessAccountBo jinshiBusinessAccountBo) {
        return jinshiBusinessAccountService.updateByPrimaryKey(jinshiBusinessAccountBo);
    }

    /**
     * 查询所有商户名字
     * @return
     */
    @RequestMapping(value = "/selectAllBusinessName",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "商户管理---查询所有商户名字")
    public String selectAllBusinessName(@RequestBody JSONObject jsonParam){
        String parkId = (String) jsonParam.get("parkId");
        Integer areaId = (Integer) jsonParam.get("areaId");
        List<JinshiBusinessAccount> list = jinshiBusinessAccountService.selectAllBusinessName(Integer.parseInt(parkId),areaId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessNameData",list);
        return jsonObject.toJSONString();
    }

}
