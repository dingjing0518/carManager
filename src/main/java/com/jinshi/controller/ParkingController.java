package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiParkingSetup;
import com.jinshi.entity.Parking;
import com.jinshi.service.JinshiParkingSetupService;
import com.jinshi.service.ParkingService;
import com.jinshi.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private JinshiParkingSetupService jinshiParkingSetupService;

    @ResponseBody
    @RequestMapping(value = "/saveMode",method = RequestMethod.POST)
    public String saveMode(@RequestBody JSONObject jsonParam) {
        boolean freeMode = (boolean) jsonParam.get("freeMode");
        boolean openGateAllMode = (boolean) jsonParam.get("openMode");
        boolean closeGateAllMode = (boolean) jsonParam.get("closeMode");
        boolean noEnterMode = (boolean) jsonParam.get("noEnterMode");
        boolean noOutMode = (boolean) jsonParam.get("noOutMode");
        boolean invalidRelease = (boolean) jsonParam.get("invalidRelease");
        String urlNewPC = (String) jsonParam.get("urlNewPC");
        String urlNewShopPC = (String) jsonParam.get("urlNewShopPC");
        Integer yellowLincenseAllow = (Integer) jsonParam.get("yellowLincenseAllow");

        GlobalVariable.freeMode = freeMode;
        GlobalVariable.openGateAllMode = openGateAllMode;
        GlobalVariable.closeGateAllMode = closeGateAllMode;
        GlobalVariable.noEnterMode = noEnterMode;
        GlobalVariable.noOutMode = noOutMode;
        GlobalVariable.invalidRelease = invalidRelease;
        GlobalVariable.urlNewPC = urlNewPC;
        GlobalVariable.urlNewShopPC = urlNewShopPC;
        GlobalVariable.yellowLincenseAllow = yellowLincenseAllow;
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/getModeDate",method = RequestMethod.POST)
    public String getModeDate(@RequestBody JSONObject jsonObject) {
        String parkId = (String) jsonObject.get("parkId");
        JinshiParkingSetup jinshiParkingSetup = jinshiParkingSetupService.selectParkSetup(Integer.valueOf(parkId));

//        boolean freeMode = GlobalVariable.freeMode;
        boolean openGateAllMode = GlobalVariable.openGateAllMode;
        boolean closeGateAllMode = GlobalVariable.closeGateAllMode;
        boolean noEnterMode = GlobalVariable.noEnterMode;
        boolean noOutMode = GlobalVariable.noOutMode;
//        boolean invalidRelease = GlobalVariable.invalidRelease;
//        Integer yellowLincenseAllow = GlobalVariable.yellowLincenseAllow;

        JSONObject resJO = new JSONObject();
        resJO.put("freeMode",jinshiParkingSetup.getJpsFreeMode());
        resJO.put("openMode",openGateAllMode);
        resJO.put("closeMode",closeGateAllMode);
        resJO.put("noEnterMode",noEnterMode);
        resJO.put("noOutMode",noOutMode);
        resJO.put("invalidRelease",jinshiParkingSetup.getJpsInvalidRelease());
        resJO.put("yellowLincenseAllow",jinshiParkingSetup.getJpsYellowLincenseAllow());

        return resJO.toJSONString();
    }

    @CrossOrigin
    @RequestMapping("/selectParkingAll")
    @ResponseBody
    public List<Parking> selectParkingAll(@RequestBody JSONObject jsonParam){
        String agentIdStr = (String) jsonParam.get("agentId");
        Integer agentId = Integer.parseInt(agentIdStr);
        return parkingService.selectParkingAll(agentId);
    }

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return parkingService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(Parking record) {

        return parkingService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(Parking record) {

        return parkingService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public Parking selectByPrimaryKey(Integer id) {

        return parkingService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(Parking record) {

        return parkingService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(Parking record) {

        return parkingService.updateByPrimaryKey(record);
    }


}
