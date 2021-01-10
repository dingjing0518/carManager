package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiMemberOrder;
import com.jinshi.service.JinshiMemberOrderService;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JinshiMemberOrder")
@CrossOrigin
@Api(tags = "车主订单管理")
public class JinshiMemberOrderController {
    private static Logger logger = Logger.getLogger(JinshiMemberOrderController.class.getName());
    @Autowired
    private JinshiMemberOrderService jinshiMemberOrderService;


    /***
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectMemberOrder",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车主订单管理---查询所有")
    public String selectMemberOrder(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiMemberOrder> res= jinshiMemberOrderService.selectMemberOrderForPage(pageNum,pageSize,Integer.valueOf(parkId));
        int getMemberOrderRecords = jinshiMemberOrderService.getMemberOrderRecords(Integer.valueOf(parkId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MemberOrderData",res);
        jsonObject.put("MemberOrderRecords",getMemberOrderRecords);
        return jsonObject.toJSONString();
    }


    /***
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchMemberOrder",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车主订单管理---搜索")
    public String searchMemberOrder(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiMemberOrder> res = jinshiMemberOrderService.searchMemberOrder(keyWord,pageNum,pageSize,Integer.valueOf(parkId));
        int getMemberOrderRecords = jinshiMemberOrderService.getMemberOrderRecordsSearch(keyWord,Integer.valueOf(parkId));
        JSONObject resJo = new JSONObject();
        resJo.put("MemberOrderData",res);
        resJo.put("MemberOrderRecords",getMemberOrderRecords);
        return resJo.toJSONString();
    }

    /**
     * 删除
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车主订单管理---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("jmoId");
        return jinshiMemberOrderService.deleteByPrimaryKey(id);
    }
}
