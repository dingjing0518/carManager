package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiParking;
import com.jinshi.service.JinshiParkingService;
import com.jinshi.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/jinshiParking")
public class JinshiParkingController {

    @Autowired
    private JinshiParkingService jinshiParkingService;

    @RequestMapping(value = "selectByNumber",method = RequestMethod.POST)
    @ResponseBody
    public String selectByNumber(@RequestBody JSONObject jsonObject){
        String parkId = (String) jsonObject.get("parkId");
//        String parkNumber = GlobalVariable.parkNumber;
        JinshiParking jinshiParking = jinshiParkingService.selectByJpId(Integer.valueOf(parkId));
        return JSONObject.toJSONString(jinshiParking);
    }

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiParkingService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(JinshiParking record) {

        return jinshiParkingService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(JinshiParking record) {

        return jinshiParkingService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public JinshiParking selectByPrimaryKey(Integer id) {

        return jinshiParkingService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(JinshiParking record) {

        return jinshiParkingService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        String s = jsonParam.toJSONString();
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject settingForm = (JSONObject) jsonObject.get("parkSetting");
        JinshiParking jinshiParking = JSONObject.parseObject(settingForm.toJSONString(), JinshiParking.class);
        return jinshiParkingService.updateByPrimaryKey(jinshiParking);
    }
}
