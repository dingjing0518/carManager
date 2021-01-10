package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiParkingSetup;
import com.jinshi.service.JinshiParkingSetupService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/JinshiParkingSetup")
@CrossOrigin
@Api(tags = "车场设置")
public class JinshiParkingSetupController {

    private static Logger logger = LogManager.getLogger(JinshiParkingSetupController.class.getName());

    @Autowired
    private JinshiParkingSetupService jinshiParkingSetupService;

    /**
     * 修改车场设置
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updateSetup")
    @CrossOrigin
    @ApiOperation(value = "车场设置---修改车场设置")
    public Integer updateSetup(@RequestBody JSONObject jsonParam){
        logger.info("修改车场设置-----------" + jsonParam.toString());
        JinshiParkingSetup jinshiParkingSetup = new JinshiParkingSetup();
        jinshiParkingSetup.setJpsParkId((Integer) jsonParam.get("jpsParkId"));
        jinshiParkingSetup.setJpsAgentId((Integer) jsonParam.get("jpsAgentId"));
        jinshiParkingSetup.setJpsOpenLocalInterface((Integer) jsonParam.get("jpsOpenLocalInterface"));
        jinshiParkingSetup.setJpsFreeMode((Integer) jsonParam.get("jpsFreeMode"));
        jinshiParkingSetup.setJpsOpenMode((Integer) jsonParam.get("jpsOpenMode"));
        jinshiParkingSetup.setJpsMemberCar((Integer) jsonParam.get("jpsMemberCar"));
        jinshiParkingSetup.setJpsInvalidRelease((Integer) jsonParam.get("jpsInvalidRelease"));
        jinshiParkingSetup.setJpsYellowLincenseAllow((Integer) jsonParam.get("jpsYellowLincenseAllow"));
        Integer allowNegativeCar = (Integer) jsonParam.get("jpsAllowNegativeCar");
        if (0 == allowNegativeCar) {
            jinshiParkingSetup.setJpsAllowNegativeCar(9999);
        } else if (1 == allowNegativeCar) {
            jinshiParkingSetup.setJpsAllowNegativeCar(0);
        }
        jinshiParkingSetup.setJpsUrlNewPc((String) jsonParam.get("jpsUrlNewPc"));
        jinshiParkingSetup.setJpsUrlNewShopPc((String) jsonParam.get("jpsUrlNewShopPc"));
        jinshiParkingSetup.setJpsHolidayFree((Integer) jsonParam.get("jpsHolidayFree"));
        jinshiParkingSetup.setJpsExportConfirm((Integer) jsonParam.get("jpsExportConfirm"));
        jinshiParkingSetup.setJpsImportConfirm((Integer) jsonParam.get("jpsImportConfirm"));
        String jpsPayType = (String) jsonParam.get("jpsPayType");
        if ("模式一".equals(jpsPayType)) {
            jinshiParkingSetup.setJpsPayType(0);
        } else if ("模式二".equals(jpsPayType)){
            jinshiParkingSetup.setJpsPayType(1);
        }
        GlobalVariable.jinshiParkingSetup = jinshiParkingSetup;
        return jinshiParkingSetupService.updateSetup(jinshiParkingSetup);
    }

    /**
     * 查询车场设置
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/selectParkSetup")
    @CrossOrigin
    @ApiOperation(value = "车场设置---查询车场设置")
    public JinshiParkingSetup selectParkSetup(@RequestBody JSONObject jsonParam) {
        Integer parkId = (Integer) jsonParam.get("parkId");
        return jinshiParkingSetupService.selectParkSetup(parkId);
    }

    /**
     * 返回车场当前图片保存路径---只显示
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/backPicPath")
    @CrossOrigin
    @ApiOperation(value = "车场设置---返回车场当前图片保存路径")
    public JSONObject backPicPath(@RequestBody JSONObject jsonParam) {
        Integer parkId = (Integer) jsonParam.get("parkId");
        JSONObject jsonObject = new JSONObject();
        if (parkId != null && parkId == 1) {
            jsonObject.put("picPath", "D:/Tomcat/webapps/carmanagerPic/");
        } else if (parkId == 3){
            jsonObject.put("picPath", "D://apache-tomcat-9.0.22-windows-x64/apache-tomcat-9.0.22/webapps/carmanagerPic/");
        } else if (parkId == 27){
            jsonObject.put("picPath", "C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/carmanagerPic/");
        }
        return jsonObject;
    }

    /**
     * 修改车场设置节假日属性
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updateSetupHoliday")
    @CrossOrigin
    @ApiOperation(value = "车场设置---修改车场设置节假日属性")
    public Integer updateSetupHoliday(@RequestBody JSONObject jsonParam) {
        Integer parkId = (Integer) jsonParam.get("jpsHoliday");
        return jinshiParkingSetupService.updateSetupHoliday(parkId);
    }
}
