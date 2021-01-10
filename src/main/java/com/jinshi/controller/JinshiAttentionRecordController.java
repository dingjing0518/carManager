package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Agent;
import com.jinshi.entity.JinshiAttentionRecord;
import com.jinshi.service.JinshiAttentionRecordService;
import com.jinshi.util.DateUtils;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/JinshiAttentionRecord")
@Api(tags = "关注记录")
public class JinshiAttentionRecordController {
    private static Logger logger = Logger.getLogger(JinshiAttentionRecordController.class.getName());
    @Autowired
    private JinshiAttentionRecordService jinshiAttentionRecordService;

    /**
     * 关注记录页面查询所有
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectAttentionRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "关注记录---查询所有")
    public String selectAttentionRecord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiAttentionRecord> jinshiAttentionRecordList = jinshiAttentionRecordService.selectAttentionRecord(pageNum, pageSize, Integer.parseInt(parkId));
        int attentionRecord = jinshiAttentionRecordService.getAttentionRecord(Integer.parseInt(parkId));
        JSONObject resJo = new JSONObject();
        resJo.put("attentionRecordData",jinshiAttentionRecordList);
        resJo.put("attentionRecords",attentionRecord);
        return resJo.toJSONString();
    }

    /**
     * 关注记录根据选择的时间段去查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "关注记录---查询 + 搜索")
    public String selectByTime(@RequestBody JSONObject jsonParam) throws Exception{
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startTimeJS = (String) jsonParam.get("startTime");
        String endTimeJS = (String) jsonParam.get("endTime");
        Date startTime = null;
        Date endTime = null;
        if (startTimeJS != null && endTimeJS != null) {
            startTime = DateUtils.getDayStartTime(format.parse(startTimeJS));
            endTime = DateUtils.getDayEndTime(format.parse(endTimeJS));
        }
        List<JinshiAttentionRecord> jinshiAttentionRecordList =
                jinshiAttentionRecordService.selectAttentionRecordByTime(pageNum, pageSize, Integer.parseInt(parkId),startTime,endTime,keyWord);
        int attentionRecords = jinshiAttentionRecordService.getAttentionRecordByTime(Integer.parseInt(parkId),startTime,endTime,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("attentionRecordData",jinshiAttentionRecordList);
        resJo.put("attentionRecords",attentionRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注记录---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer id = (Integer) jsonParam.get("id");
        return jinshiAttentionRecordService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注记录---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        JinshiAttentionRecord jinshiAttentionRecord = JSONObject.parseObject(jsonParam.toJSONString(), JinshiAttentionRecord.class);
        return jinshiAttentionRecordService.insert(jinshiAttentionRecord);
    }

    @RequestMapping(value = "/updateByOrderId",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "关注记录---编辑")
    public Integer updateByOrderId(@RequestBody JSONObject jsonParam) {
        JinshiAttentionRecord jinshiAttentionRecord = JSONObject.parseObject(jsonParam.toJSONString(), JinshiAttentionRecord.class);
        return jinshiAttentionRecordService.updateByOrderId(jinshiAttentionRecord);
    }
}
