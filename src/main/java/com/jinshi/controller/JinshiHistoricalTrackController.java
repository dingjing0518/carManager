package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiHistoricalTrack;
import com.jinshi.entity.JinshiPresenceTrack;
import com.jinshi.service.JinshiHistoricalTrackService;
import com.jinshi.service.JinshiPresenceTrackService;
import com.jinshi.util.DateUtils;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
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
@RequestMapping("/jinshiHistoricalTrack")
@Api(tags = "历史轨迹")
public class JinshiHistoricalTrackController {
    private static Logger logger = Logger.getLogger(JinshiHistoricalTrackController.class.getName());
    @Autowired
    private JinshiHistoricalTrackService jinshiHistoricalTrackService;

    @Autowired
    private JinshiPresenceTrackService jinshiPresenceTrackService;

    /**
     * 查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectHistoricalTrackAll",method = RequestMethod.POST)
    @ResponseBody
    public String selectHistoricalTrackAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiHistoricalTrack> res= jinshiHistoricalTrackService.selectHistoricalTrackPage(pageNum,pageSize,parkId);
        int getHistoricalTrackRecords = jinshiHistoricalTrackService.getHistoricalTrackRecords(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("HistoricalTrackData",res);
        jsonObject.put("HistoricalTrackRecords",getHistoricalTrackRecords);
        return jsonObject.toJSONString();
    }

    /**
     * 搜索
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchHistoricalTrack",method = RequestMethod.POST)
    @ResponseBody
    public String searchHistoricalTrack(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiHistoricalTrack> res = jinshiHistoricalTrackService.searchHistoricalTrack(keyWord,pageNum,pageSize,parkId);
        int trackTotalRecords = jinshiHistoricalTrackService.getHistoricalTrackRecordsSearch(parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("HistoricalTrackData",res);
        resJo.put("HistoricalTrackRecords",trackTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 条件查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectHistoricalTrackByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectHistoricalTrackByTime(@RequestBody JSONObject jsonParam) throws Exception{
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
        List<JinshiHistoricalTrack> res = jinshiHistoricalTrackService.selectHistoricalTrackByTime(pageNum,pageSize,startTime,endTime,parkId,keyWord);
        int trackTotalRecords = jinshiHistoricalTrackService.getHistoricalTrackByTime(startTime,endTime,parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("HistoricalTrackData",res);
        resJo.put("HistoricalTrackRecords",trackTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 添加到历史表
     * @return
     */
    @RequestMapping(value = "/insertHistoryTrack",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer insertHistoryTrack(@RequestBody JSONObject jsonParam) {

        logger.info(jsonParam.toJSONString());
        Integer ptLpId = (Integer) jsonParam.get("ptLpId");
        List<JinshiPresenceTrack> jinshiPresenceTrackList = jinshiPresenceTrackService.selectByPtLpId(ptLpId);
        for (JinshiPresenceTrack jinshiPresenceTrack : jinshiPresenceTrackList) {
            JinshiHistoricalTrack jinshiHistoricalTrack = new JinshiHistoricalTrack();
            jinshiHistoricalTrack.setHtLpId(jinshiPresenceTrack.getPtLpId());
            jinshiHistoricalTrack.setHtCreatTime(jinshiPresenceTrack.getPtCreatTime());
            jinshiHistoricalTrack.setHtLincensePlateId(jinshiPresenceTrack.getPtLincensePlateId());
            jinshiHistoricalTrack.setHtThandle(jinshiPresenceTrack.getPtThandle());
            jinshiHistoricalTrack.setHtStatus(jinshiPresenceTrack.getPtStatus());
            jinshiHistoricalTrack.setHtParkid(jinshiPresenceTrack.getPtParkid());
            jinshiHistoricalTrackService.insertHistoryTrack(jinshiHistoricalTrack);
        }
        return 1;
    }
}
