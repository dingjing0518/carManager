package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Agent;
import com.jinshi.entity.JinshiArea;
import com.jinshi.entity.JinshiParking;
import com.jinshi.service.JinshiAreaService;
import com.jinshi.service.JinshiParkingService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/parkingJinshi")
@Api(tags = "车场管理")
public class ParkingJinshiController {

    private static Logger logger = Logger.getLogger(QianYiCameraUtil.class.getName());


    @Autowired
    private JinshiParkingService jinshiParkingService;

    @Autowired
    private JinshiAreaService jinshiAreaService;

    @RequestMapping(value = "/selectParkingAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---查询所有")
    public String selectParkingAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String agentIdStr = (String) jsonParam.get("agentId");
        Integer agentId = Integer.parseInt(agentIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiParking> res = jinshiParkingService.selectParkingForPage(pageNum,pageSize,agentId);
        int parkingTotalRecords = jinshiParkingService.getParkingTotalRecords(agentId);
        JSONObject resJo = new JSONObject();
        resJo.put("parkingData",res);
        resJo.put("parkingTotalRecords",parkingTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchParking",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车场管理---搜索")
    public String searchParking(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String agentIdStr = (String) jsonParam.get("agentId");
        Integer agentId = Integer.parseInt(agentIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<JinshiParking> res = jinshiParkingService.searchParking(keyWord,pageNum,pageSize,agentId);
        int parkingTotalRecords = jinshiParkingService.getParkingTotalRecords(agentId);
        JSONObject resJo = new JSONObject();
        resJo.put("parkingData",res);
        resJo.put("parkingTotalRecords",parkingTotalRecords);
        return resJo.toJSONString();
    }
//
//    @RequestMapping(value = "/searchByParking",method = RequestMethod.POST)
//    @ResponseBody
//    public String searchByParking(@RequestBody JSONObject jsonParam){
//        logger.info(jsonParam.toJSONString());
//        Integer pageNum = (Integer) jsonParam.get("pageNum");
//        Integer pageSize = (Integer) jsonParam.get("pageSize");
//        String keyWord = (String) jsonParam.get("keyWord");
//        String agentIdStr = (String) jsonParam.get("agentId");
//        Integer agentId = Integer.parseInt(agentIdStr);
////        List<JinshiParking> jinshiParking1 = new ArrayList<>();
//        JSONObject resJo = new JSONObject();
//        try {
//            List<JinshiParking> jinshiParking1 = jinshiParkingService.searchParking(keyWord,pageNum,pageSize,agentId);
//            resJo.put("parkingData",jinshiParking1);
//        }catch (Exception e){
//            e.printStackTrace();
//            return resJo.toJSONString();
//        }
//        logger.info(resJo.toJSONString());
//        return resJo.toJSONString();
//    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---添加")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiParking jinshiParking = JSONObject.parseObject(jsonParam.toJSONString(), JinshiParking.class);
        return jinshiParkingService.insert(jinshiParking);
    }

    @RequestMapping(value = "/deleteByJpNumber", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---删除")
    public Integer deleteByJpNumber(@RequestBody JSONObject jsonParam) {
        Integer jpNumber = (Integer) jsonParam.get("id");
        return jinshiParkingService.deleteByPrimaryKey(jpNumber);
    }

    @RequestMapping(value = "/updateByParking",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---编辑")
    public Integer updateByParking(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        JinshiParking jinshiParking  = JSONObject.parseObject(jsonParam.toJSONString(), JinshiParking.class);
        return jinshiParkingService.updateByPrimaryKey(jinshiParking);
    }

    /**
     * 查询所有代理商名字
     * @return
     */
    @RequestMapping(value = "/selectAllAgent",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---查询所有代理商名字")
    public String selectAllAgent(){
        List<Agent> list = jinshiParkingService.selectAllAgent();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agentNameData",list);
        return jsonObject.toJSONString();
    }

    /**
     * 查询所有停车场名字
     * @return
     */
    @RequestMapping(value = "/selectAllParkingName",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---查询所有停车场名字")
    public String selectAllParkingName(Integer parkId){
        List<JinshiParking> list = jinshiParkingService.selectAllParkingName(parkId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parkingNameData",list);
        return jsonObject.toJSONString();
    }

    /**
     * 查询所有停车场
     * @return
     */
    @PostMapping(value = "/selectAllPark")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---查询所有停车场")
    public List<JinshiParking> selectAllPark(){
        return jinshiParkingService.selectAllPark();
    }

    /**
     * 根据车场id查询所属的区域
     * @return
     */
    @PostMapping(value = "/selectAreaByParkId")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---根据车场id查询所属的区域")
    public List<JinshiArea> selectAreaByParkId(@RequestBody JSONObject jsonObject){
        Integer jpId = (Integer) jsonObject.get("jpId");
        return jinshiAreaService.selectAreaNameAll(jpId);
    }

    /**
     * 根据车场id查询车场
     * @return
     */
    @PostMapping(value = "/selectParkByParkId")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车场管理---根据车场id查询车场")
    public List<JinshiParking> selectParkByParkId(@RequestBody JSONObject jsonObject){
        Object parkId = jsonObject.get("parkId");
        String s = parkId.toString();
        return jinshiParkingService.selectParkByParkId(Integer.parseInt(s));
    }
}
