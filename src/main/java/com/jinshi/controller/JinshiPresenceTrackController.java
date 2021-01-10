package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiPresenceTrack;
import com.jinshi.service.JinshiPresenceTrackService;
import com.jinshi.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/jinshiPresenceTrack")
@Api(tags = "在场轨迹")
public class JinshiPresenceTrackController {

    @Autowired
    private JinshiPresenceTrackService jinshiPresenceTrackService;

    /**
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectPresenceTrackAll",method = RequestMethod.POST)
    @ResponseBody
    public String selectPresenceTrackAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiPresenceTrack> res= jinshiPresenceTrackService.selectPresenceTrackPage(pageNum,pageSize,parkId);
        int getPresenceTrackRecords = jinshiPresenceTrackService.getPresenceTrackRecords(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PresenceTrackData",res);
        jsonObject.put("PresenceTrackRecords",getPresenceTrackRecords);
        return jsonObject.toJSONString();
    }

    /**
     * 搜索 XXXX
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchPresenceTrack",method = RequestMethod.POST)
    @ResponseBody
    public String searchPresenceTrack(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiPresenceTrack> res = jinshiPresenceTrackService.searchPresenceTrack(keyWord,pageNum,pageSize,parkId);
        int trackTotalRecords = jinshiPresenceTrackService.getPresenceTrackRecordsSearch(parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("PresenceTrackData",res);
        resJo.put("PresenceTrackRecords",trackTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 条件查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectPresenceTrackByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectPresenceTrackByTime(@RequestBody JSONObject jsonParam) throws Exception{
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
        List<JinshiPresenceTrack> res = jinshiPresenceTrackService.selectPresenceTrackByTime(pageNum,pageSize,startTime,endTime,parkId,keyWord);
        int trackTotalRecords = jinshiPresenceTrackService.getPresenceTrackByTime(startTime,endTime,parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("PresenceTrackData",res);
        resJo.put("PresenceTrackRecords",trackTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/insertPresenceTrack",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer insertPresenceTrack(@RequestBody JSONObject jsonParam) {
        JinshiPresenceTrack jinshiPresenceTrack = JSONObject.parseObject(jsonParam.toJSONString(), JinshiPresenceTrack.class);
        return jinshiPresenceTrackService.insertPresenceTrack(jinshiPresenceTrack);
    }

    @RequestMapping(value = "/selectPresenceTrack",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<JinshiPresenceTrack> selectAllInfo(@RequestBody JSONObject jsonParam) {
        String lincense = (String) jsonParam.get("ptLincensePlateId");
        String ptParkid = (String) jsonParam.get("ptParkid");
        return jinshiPresenceTrackService.selectAllInfo(lincense,ptParkid);
    }

    @RequestMapping(value = "/deletePresenceTrack",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer deletePresenceTrack(@RequestBody JSONObject jsonParam) {
        Integer ptLpId = (Integer) jsonParam.get("ptLpId");
        return jinshiPresenceTrackService.deletePresenceTrack(ptLpId);
    }
}


