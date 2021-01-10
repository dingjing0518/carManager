package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiParkSetting;
import com.jinshi.service.JinshiParkSettingService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/JinshiParkSetting")
@Api(tags = "收费设置")
public class JinshiParkingSettingController {
    private static Logger logger = Logger.getLogger(JinshiParkingSettingController.class.getName());
    @Autowired
    private JinshiParkSettingService jinshiParkSettingService;

//    @RequestMapping(value = "updateJinshiParkSetting",method = RequestMethod.POST)
//    @ResponseBody
//    public int updateJinshiParkSetting(@RequestBody JSONObject jsonParam){
//        Integer carType = Integer.parseInt(jsonParam.get("carType").toString());
//        String s = jsonParam.toJSONString();
//        JSONObject jsonObject = JSONObject.parseObject(s);
//        JSONObject settingForm = (JSONObject) jsonObject.get("settingForm");
//        logger.info(settingForm);
////        JSONObject settingForm = JSONObject.parseObject(jsonParam.get("settingForm").toString());
//        JinshiParkSetting jinshiParkSetting = new JinshiParkSetting();
//        jinshiParkSetting.setJpsParkId(GlobalVariable.parkId);
//        jinshiParkSetting.setJpsAgentId(GlobalVariable.agentId);
//        if(jinshiParkSetting!=null){
//            if(carType==11){
//                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) settingForm.get("freeTime")));
//                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) settingForm.get("firstTime")));
//                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) settingForm.get("firstCharge")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) settingForm.get("followCharge")));
//                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) settingForm.get("allDayLimit")));
//                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) settingForm.get("payAdvanceOutTime")));
//                jinshiParkSetting.setJpsCarType(0);
//                GlobalVariable.blueLicenseFreeTime = Integer.parseInt((String) settingForm.get("freeTime"));
//                GlobalVariable.blueLicenseFirstTime = Integer.parseInt((String) settingForm.get("firstTime"));
//                GlobalVariable.blueLincenseFirstCharge = Double.parseDouble((String) settingForm.get("firstCharge"));
//                GlobalVariable.blueLincenseFollowTime = Integer.parseInt((String) settingForm.get("followTime"));
//                GlobalVariable.blueLincenseFollowCharge = Double.parseDouble((String) settingForm.get("followCharge"));
//                GlobalVariable.blueLincenseAllDayLimit = Double.parseDouble((String) settingForm.get("allDayLimit"));
//                GlobalVariable.blueLincensePayAdvanceOutTime = Integer.parseInt((String) settingForm.get("payAdvanceOutTime"));
//            }else if(carType==12){
//                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) settingForm.get("freeTime")));
//                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) settingForm.get("firstTime")));
//                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) settingForm.get("firstCharge")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) settingForm.get("followCharge")));
//                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) settingForm.get("allDayLimit")));
//                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) settingForm.get("payAdvanceOutTime")));
//                jinshiParkSetting.setJpsCarType(1);
//                GlobalVariable.yellowLicenseFreeTime = Integer.parseInt((String) settingForm.get("freeTime"));
//                GlobalVariable.yellowLicenseFirstTime = Integer.parseInt((String) settingForm.get("firstTime"));
//                GlobalVariable.yellowLincenseFirstCharge = Double.parseDouble((String) settingForm.get("firstCharge"));
//                GlobalVariable.yellowLincenseFollowTime = Integer.parseInt((String) settingForm.get("followTime"));
//                GlobalVariable.yellowLincenseFollowCharge = Double.parseDouble((String) settingForm.get("followCharge"));
//                GlobalVariable.yellowLincenseAllDayLimit = Double.parseDouble((String) settingForm.get("allDayLimit"));
//                GlobalVariable.yellowLincensePayAdvanceOutTime = Integer.parseInt((String) settingForm.get("payAdvanceOutTime"));
//            }else if(carType==13){
//                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) settingForm.get("freeTime")));
//                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) settingForm.get("firstTime")));
//                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) settingForm.get("firstCharge")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) settingForm.get("followCharge")));
//                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) settingForm.get("allDayLimit")));
//                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) settingForm.get("payAdvanceOutTime")));
//                jinshiParkSetting.setJpsCarType(4);
//                GlobalVariable.yellowLicenseFreeTimeBig = Integer.parseInt((String) settingForm.get("freeTime"));
//                GlobalVariable.yellowLicenseFirstTimeBig = Integer.parseInt((String) settingForm.get("firstTime"));
//                GlobalVariable.yellowLincenseFirstChargeBig = Double.parseDouble((String) settingForm.get("firstCharge"));
//                GlobalVariable.yellowLincenseFollowTimeBig = Integer.parseInt((String) settingForm.get("followTime"));
//                GlobalVariable.yellowLincenseFollowChargeBig = Double.parseDouble((String) settingForm.get("followCharge"));
//                GlobalVariable.yellowLincenseAllDayLimitBig = Double.parseDouble((String) settingForm.get("allDayLimit"));
//                GlobalVariable.yellowLincensePayAdvanceOutTimeBig = Integer.parseInt((String) settingForm.get("payAdvanceOutTime"));
//            }else if(carType ==14){
//                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) settingForm.get("freeTime")));
//                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) settingForm.get("firstTime")));
//                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) settingForm.get("firstCharge")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) settingForm.get("followCharge")));
//                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) settingForm.get("allDayLimit")));
//                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) settingForm.get("payAdvanceOutTime")));
//                jinshiParkSetting.setJpsCarType(2);
//                GlobalVariable.greenLicenseFreeTime = Integer.parseInt((String) settingForm.get("freeTime"));
//                GlobalVariable.greenLicenseFirstTime = Integer.parseInt((String) settingForm.get("firstTime"));
//                GlobalVariable.greenLincenseFirstCharge = Double.parseDouble((String) settingForm.get("firstCharge"));
//                GlobalVariable.greenLincenseFollowTime = Integer.parseInt((String) settingForm.get("followTime"));
//                GlobalVariable.greenLincenseFollowCharge = Double.parseDouble((String) settingForm.get("followCharge"));
//                GlobalVariable.greenLincenseAllDayLimit = Double.parseDouble((String) settingForm.get("allDayLimit"));
//                GlobalVariable.greenLincensePayAdvanceOutTime = Integer.parseInt((String) settingForm.get("payAdvanceOutTime"));
//            }else if(carType==15){
//                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) settingForm.get("freeTime")));
//                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) settingForm.get("firstTime")));
//                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) settingForm.get("firstCharge")));
//                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) settingForm.get("followTime")));
//                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) settingForm.get("followCharge")));
//                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) settingForm.get("allDayLimit")));
//                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) settingForm.get("payAdvanceOutTime")));
//                jinshiParkSetting.setJpsCarType(3);
//                GlobalVariable.specialLincenseFreeTime = Integer.parseInt((String) settingForm.get("freeTime"));
//                GlobalVariable.specialLicenseFirstTime = Integer.parseInt((String) settingForm.get("firstTime"));
//                GlobalVariable.specialLincenseFirstCharge = Double.parseDouble((String) settingForm.get("firstCharge"));
//                GlobalVariable.specialLincenseFollowTime = Integer.parseInt((String) settingForm.get("followTime"));
//                GlobalVariable.specialLincenseFollowCharge = Double.parseDouble((String) settingForm.get("followCharge"));
//                GlobalVariable.specialLincenseAllDayLimit = Double.parseDouble((String) settingForm.get("allDayLimit"));
//                GlobalVariable.specialLincensePayAdvanceOutTime = Integer.parseInt((String) settingForm.get("payAdvanceOutTime"));
//            }
//        }
//        return jinshiParkSettingService.updateByParkidAndCarType(jinshiParkSetting);
//    }

//    @RequestMapping(value = "getGlobalVariable",method = RequestMethod.POST)
//    @ResponseBody
//    public String getGlobalVariable(@RequestBody JSONObject jsonParam){
//        Integer carType = Integer.parseInt((String) jsonParam.get("carType"));
//        JSONObject resJO = new JSONObject();
//        if(carType==11){
//            resJO.put("freeTime",GlobalVariable.blueLicenseFreeTime.toString());
//            resJO.put("firstTime",GlobalVariable.blueLicenseFirstTime.toString());
//            resJO.put("firstCharge",GlobalVariable.blueLincenseFirstCharge.toString());
//            resJO.put("followTime",GlobalVariable.blueLincenseFollowTime.toString());
//            resJO.put("followCharge",GlobalVariable.blueLincenseFollowCharge.toString());
//            resJO.put("allDayLimit",GlobalVariable.blueLincenseAllDayLimit.toString());
//            resJO.put("payAdvanceOutTime",GlobalVariable.blueLincensePayAdvanceOutTime.toString());
//        }else if(carType==12){
//            resJO.put("freeTime",GlobalVariable.yellowLicenseFreeTime.toString());
//            resJO.put("firstTime",GlobalVariable.yellowLicenseFirstTime.toString());
//            resJO.put("firstCharge",GlobalVariable.yellowLincenseFirstCharge.toString());
//            resJO.put("followTime",GlobalVariable.yellowLincenseFollowTime.toString());
//            resJO.put("followCharge",GlobalVariable.yellowLincenseFollowCharge.toString());
//            resJO.put("allDayLimit",GlobalVariable.yellowLincenseAllDayLimit.toString());
//            resJO.put("payAdvanceOutTime",GlobalVariable.yellowLincensePayAdvanceOutTime.toString());
//        }else if(carType==13){
//            resJO.put("freeTime",GlobalVariable.yellowLicenseFreeTimeBig.toString());
//            resJO.put("firstTime",GlobalVariable.yellowLicenseFirstTimeBig.toString());
//            resJO.put("firstCharge",GlobalVariable.yellowLincenseFirstChargeBig.toString());
//            resJO.put("followTime",GlobalVariable.yellowLincenseFollowTimeBig.toString());
//            resJO.put("followCharge",GlobalVariable.yellowLincenseFollowChargeBig.toString());
//            resJO.put("allDayLimit",GlobalVariable.yellowLincenseAllDayLimitBig.toString());
//            resJO.put("payAdvanceOutTime",GlobalVariable.yellowLincensePayAdvanceOutTimeBig.toString());
//        }else if(carType==14){
//            resJO.put("freeTime",GlobalVariable.greenLicenseFreeTime.toString());
//            resJO.put("firstTime",GlobalVariable.greenLicenseFirstTime.toString());
//            resJO.put("firstCharge",GlobalVariable.greenLincenseFirstCharge.toString());
//            resJO.put("followTime",GlobalVariable.greenLincenseFollowTime.toString());
//            resJO.put("followCharge",GlobalVariable.greenLincenseFollowCharge.toString());
//            resJO.put("allDayLimit",GlobalVariable.greenLincenseAllDayLimit.toString());
//            resJO.put("payAdvanceOutTime",GlobalVariable.greenLincensePayAdvanceOutTime.toString());
//        }else if(carType==15){
//            resJO.put("freeTime",GlobalVariable.specialLincenseFreeTime.toString());
//            resJO.put("firstTime",GlobalVariable.specialLicenseFirstTime.toString());
//            resJO.put("firstCharge",GlobalVariable.specialLincenseFirstCharge.toString());
//            resJO.put("followTime",GlobalVariable.specialLincenseFollowTime.toString());
//            resJO.put("followCharge",GlobalVariable.specialLincenseFollowCharge.toString());
//            resJO.put("allDayLimit",GlobalVariable.specialLincenseAllDayLimit.toString());
//            resJO.put("payAdvanceOutTime",GlobalVariable.specialLincensePayAdvanceOutTime.toString());
//        }
//
////        resJO.put("parkId",GlobalVariable.parkId);
////        resJO.put("parkName",GlobalVariable.parkName);
////        resJO.put("parkNumber",GlobalVariable.parkNumber);
////        resJO.put("parkPlaceNumber",GlobalVariable.parkPlaceNumber);
////        resJO.put("parkCameraBrand",GlobalVariable.parkCameraBrand);
////        resJO.put("parkPicturePath",GlobalVariable.parkPicturePath);
//        return resJO.toString();
//    }

    @RequestMapping(value = "updateJinshiParkSetting",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "收费设置---保存")
    public int updateJinshiParkSetting(@RequestBody JSONObject jsonParam){
        logger.info("updateJinshiParkSetting-----" + jsonParam.toJSONString());
        Integer carType = Integer.parseInt(jsonParam.get("carType").toString());
        Integer parkId = Integer.parseInt(jsonParam.get("parkId").toString());
        Integer agentId = Integer.parseInt(jsonParam.get("agentId").toString());
        String s = jsonParam.toJSONString();
        JSONObject jsonObject = JSONObject.parseObject(s);
//        JSONObject settingForm = (JSONObject) jsonObject.get("settingForm");
//        logger.info(settingForm);
//        JSONObject settingForm = JSONObject.parseObject(jsonParam.get("settingForm").toString());
        JinshiParkSetting jinshiParkSetting = new JinshiParkSetting();
        jinshiParkSetting.setJpsParkId(parkId);
        jinshiParkSetting.setJpsAgentId(agentId);
        if(jinshiParkSetting!=null){
            if(carType==11){//小型车
                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) jsonObject.get("freeTime")));
                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) jsonObject.get("firstTime")));
                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) jsonObject.get("firstCharge")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) jsonObject.get("followCharge")));
                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) jsonObject.get("allDayLimit")));
                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) jsonObject.get("payAdvanceOutTime")));
                jinshiParkSetting.setJpsCarType(0);
                GlobalVariable.blueLicenseFreeTime = Integer.parseInt((String) jsonObject.get("freeTime"));
                GlobalVariable.blueLicenseFirstTime = Integer.parseInt((String) jsonObject.get("firstTime"));
                GlobalVariable.blueLincenseFirstCharge = Double.parseDouble((String) jsonObject.get("firstCharge"));
                GlobalVariable.blueLincenseFollowTime = Integer.parseInt((String) jsonObject.get("followTime"));
                GlobalVariable.blueLincenseFollowCharge = Double.parseDouble((String) jsonObject.get("followCharge"));
                GlobalVariable.blueLincenseAllDayLimit = Double.parseDouble((String) jsonObject.get("allDayLimit"));
                GlobalVariable.blueLincensePayAdvanceOutTime = Integer.parseInt((String) jsonObject.get("payAdvanceOutTime"));
            }else if(carType==12){//中型车(黄牌)
                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) jsonObject.get("freeTime")));
                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) jsonObject.get("firstTime")));
                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) jsonObject.get("firstCharge")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) jsonObject.get("followCharge")));
                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) jsonObject.get("allDayLimit")));
                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) jsonObject.get("payAdvanceOutTime")));
                jinshiParkSetting.setJpsCarType(1);
                GlobalVariable.yellowLicenseFreeTime = Integer.parseInt((String) jsonObject.get("freeTime"));
                GlobalVariable.yellowLicenseFirstTime = Integer.parseInt((String) jsonObject.get("firstTime"));
                GlobalVariable.yellowLincenseFirstCharge = Double.parseDouble((String) jsonObject.get("firstCharge"));
                GlobalVariable.yellowLincenseFollowTime = Integer.parseInt((String) jsonObject.get("followTime"));
                GlobalVariable.yellowLincenseFollowCharge = Double.parseDouble((String) jsonObject.get("followCharge"));
                GlobalVariable.yellowLincenseAllDayLimit = Double.parseDouble((String) jsonObject.get("allDayLimit"));
                GlobalVariable.yellowLincensePayAdvanceOutTime = Integer.parseInt((String) jsonObject.get("payAdvanceOutTime"));
            }else if(carType==13){//大型车(黄牌)
                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) jsonObject.get("freeTime")));
                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) jsonObject.get("firstTime")));
                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) jsonObject.get("firstCharge")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) jsonObject.get("followCharge")));
                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) jsonObject.get("allDayLimit")));
                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) jsonObject.get("payAdvanceOutTime")));
                jinshiParkSetting.setJpsCarType(4);
                GlobalVariable.yellowLicenseFreeTimeBig = Integer.parseInt((String) jsonObject.get("freeTime"));
                GlobalVariable.yellowLicenseFirstTimeBig = Integer.parseInt((String) jsonObject.get("firstTime"));
                GlobalVariable.yellowLincenseFirstChargeBig = Double.parseDouble((String) jsonObject.get("firstCharge"));
                GlobalVariable.yellowLincenseFollowTimeBig = Integer.parseInt((String) jsonObject.get("followTime"));
                GlobalVariable.yellowLincenseFollowChargeBig = Double.parseDouble((String) jsonObject.get("followCharge"));
                GlobalVariable.yellowLincenseAllDayLimitBig = Double.parseDouble((String) jsonObject.get("allDayLimit"));
                GlobalVariable.yellowLincensePayAdvanceOutTimeBig = Integer.parseInt((String) jsonObject.get("payAdvanceOutTime"));
            }else if(carType ==14){//新能源车
                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) jsonObject.get("freeTime")));
                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) jsonObject.get("firstTime")));
                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) jsonObject.get("firstCharge")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) jsonObject.get("followCharge")));
                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) jsonObject.get("allDayLimit")));
                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) jsonObject.get("payAdvanceOutTime")));
                jinshiParkSetting.setJpsCarType(2);
                GlobalVariable.greenLicenseFreeTime = Integer.parseInt((String) jsonObject.get("freeTime"));
                GlobalVariable.greenLicenseFirstTime = Integer.parseInt((String) jsonObject.get("firstTime"));
                GlobalVariable.greenLincenseFirstCharge = Double.parseDouble((String) jsonObject.get("firstCharge"));
                GlobalVariable.greenLincenseFollowTime = Integer.parseInt((String) jsonObject.get("followTime"));
                GlobalVariable.greenLincenseFollowCharge = Double.parseDouble((String) jsonObject.get("followCharge"));
                GlobalVariable.greenLincenseAllDayLimit = Double.parseDouble((String) jsonObject.get("allDayLimit"));
                GlobalVariable.greenLincensePayAdvanceOutTime = Integer.parseInt((String) jsonObject.get("payAdvanceOutTime"));
            }else if(carType==15){//特种车
                jinshiParkSetting.setJpsFreeTime(Integer.parseInt((String) jsonObject.get("freeTime")));
                jinshiParkSetting.setJpsFirstTime(Integer.parseInt((String) jsonObject.get("firstTime")));
                jinshiParkSetting.setJpsFirstCharge(Double.parseDouble((String) jsonObject.get("firstCharge")));
                jinshiParkSetting.setJpsFollowTime(Integer.parseInt((String) jsonObject.get("followTime")));
                jinshiParkSetting.setJpsFollowCharge(Double.parseDouble((String) jsonObject.get("followCharge")));
                jinshiParkSetting.setJpsAlldayLimit(Double.parseDouble((String) jsonObject.get("allDayLimit")));
                jinshiParkSetting.setJpsAdvanceChargeOuttime(Integer.parseInt((String) jsonObject.get("payAdvanceOutTime")));
                jinshiParkSetting.setJpsCarType(3);
                GlobalVariable.specialLincenseFreeTime = Integer.parseInt((String) jsonObject.get("freeTime"));
                GlobalVariable.specialLicenseFirstTime = Integer.parseInt((String) jsonObject.get("firstTime"));
                GlobalVariable.specialLincenseFirstCharge = Double.parseDouble((String) jsonObject.get("firstCharge"));
                GlobalVariable.specialLincenseFollowTime = Integer.parseInt((String) jsonObject.get("followTime"));
                GlobalVariable.specialLincenseFollowCharge = Double.parseDouble((String) jsonObject.get("followCharge"));
                GlobalVariable.specialLincenseAllDayLimit = Double.parseDouble((String) jsonObject.get("allDayLimit"));
                GlobalVariable.specialLincensePayAdvanceOutTime = Integer.parseInt((String) jsonObject.get("payAdvanceOutTime"));
            }
        }
        return jinshiParkSettingService.updateByParkidAndCarType(jinshiParkSetting);
    }

    @RequestMapping(value = "getGlobalVariable",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "收费设置---查询")
    public String getGlobalVariable(@RequestBody JSONObject jsonParam){
        logger.info("getGlobalVariable-----------" + jsonParam.toJSONString());
        Integer carType = Integer.parseInt((String) jsonParam.get("carType"));
        Integer parkId = Integer.parseInt((String) jsonParam.get("parkId"));
        JSONObject resJO = new JSONObject();
        if(carType==11){//小型车 0
            JinshiParkSetting jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(parkId,0);
            resJO.put("freeTime",jinshiParkSetting.getJpsFreeTime());
            resJO.put("firstTime",jinshiParkSetting.getJpsFirstTime());
            resJO.put("firstCharge",jinshiParkSetting.getJpsFirstCharge());
            resJO.put("followTime",jinshiParkSetting.getJpsFollowTime());
            resJO.put("followCharge",jinshiParkSetting.getJpsFollowCharge());
            resJO.put("allDayLimit",jinshiParkSetting.getJpsAlldayLimit());
            resJO.put("payAdvanceOutTime",jinshiParkSetting.getJpsAdvanceChargeOuttime());
//            resJO.put("freeTime",GlobalVariable.blueLicenseFreeTime.intValue());
//            resJO.put("firstTime",GlobalVariable.blueLicenseFirstTime.intValue());
//            resJO.put("firstCharge",GlobalVariable.blueLincenseFirstCharge.intValue());
//            resJO.put("followTime",GlobalVariable.blueLincenseFollowTime.intValue());
//            resJO.put("followCharge",GlobalVariable.blueLincenseFollowCharge.intValue());
//            resJO.put("allDayLimit",GlobalVariable.blueLincenseAllDayLimit.intValue());
//            resJO.put("payAdvanceOutTime",GlobalVariable.blueLincensePayAdvanceOutTime.intValue());
            }else if(carType==12){//中型车(黄牌) 1
            JinshiParkSetting jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(GlobalVariable.parkId,1);
            resJO.put("freeTime",jinshiParkSetting.getJpsFreeTime());
            resJO.put("firstTime",jinshiParkSetting.getJpsFirstTime());
            resJO.put("firstCharge",jinshiParkSetting.getJpsFirstCharge());
            resJO.put("followTime",jinshiParkSetting.getJpsFollowTime());
            resJO.put("followCharge",jinshiParkSetting.getJpsFollowCharge());
            resJO.put("allDayLimit",jinshiParkSetting.getJpsAlldayLimit());
            resJO.put("payAdvanceOutTime",jinshiParkSetting.getJpsAdvanceChargeOuttime());
//            resJO.put("freeTime",GlobalVariable.yellowLicenseFreeTime.intValue());
//            resJO.put("firstTime",GlobalVariable.yellowLicenseFirstTime.intValue());
//            resJO.put("firstCharge",GlobalVariable.yellowLincenseFirstCharge.intValue());
//            resJO.put("followTime",GlobalVariable.yellowLincenseFollowTime.intValue());
//            resJO.put("followCharge",GlobalVariable.yellowLincenseFollowCharge.intValue());
//            resJO.put("allDayLimit",GlobalVariable.yellowLincenseAllDayLimit.intValue());
//            resJO.put("payAdvanceOutTime",GlobalVariable.yellowLincensePayAdvanceOutTime.intValue());
        }else if(carType==13){//大型车(黄牌)4
            JinshiParkSetting jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(GlobalVariable.parkId,4);
            resJO.put("freeTime",jinshiParkSetting.getJpsFreeTime());
            resJO.put("firstTime",jinshiParkSetting.getJpsFirstTime());
            resJO.put("firstCharge",jinshiParkSetting.getJpsFirstCharge());
            resJO.put("followTime",jinshiParkSetting.getJpsFollowTime());
            resJO.put("followCharge",jinshiParkSetting.getJpsFollowCharge());
            resJO.put("allDayLimit",jinshiParkSetting.getJpsAlldayLimit());
            resJO.put("payAdvanceOutTime",jinshiParkSetting.getJpsAdvanceChargeOuttime());
//            resJO.put("freeTime",GlobalVariable.yellowLicenseFreeTimeBig.intValue());
//            resJO.put("firstTime",GlobalVariable.yellowLicenseFirstTimeBig.intValue());
//            resJO.put("firstCharge",GlobalVariable.yellowLincenseFirstChargeBig.intValue());
//            resJO.put("followTime",GlobalVariable.yellowLincenseFollowTimeBig.intValue());
//            resJO.put("followCharge",GlobalVariable.yellowLincenseFollowChargeBig.intValue());
//            resJO.put("allDayLimit",GlobalVariable.yellowLincenseAllDayLimitBig.intValue());
//            resJO.put("payAdvanceOutTime",GlobalVariable.yellowLincensePayAdvanceOutTimeBig.intValue());
        }else if(carType==14){//新能源车 2
            JinshiParkSetting jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(GlobalVariable.parkId,2);
            resJO.put("freeTime",jinshiParkSetting.getJpsFreeTime());
            resJO.put("firstTime",jinshiParkSetting.getJpsFirstTime());
            resJO.put("firstCharge",jinshiParkSetting.getJpsFirstCharge());
            resJO.put("followTime",jinshiParkSetting.getJpsFollowTime());
            resJO.put("followCharge",jinshiParkSetting.getJpsFollowCharge());
            resJO.put("allDayLimit",jinshiParkSetting.getJpsAlldayLimit());
            resJO.put("payAdvanceOutTime",jinshiParkSetting.getJpsAdvanceChargeOuttime());
//            resJO.put("freeTime",GlobalVariable.greenLicenseFreeTime.intValue());
//            resJO.put("firstTime",GlobalVariable.greenLicenseFirstTime.intValue());
//            resJO.put("firstCharge",GlobalVariable.greenLincenseFirstCharge.intValue());
//            resJO.put("followTime",GlobalVariable.greenLincenseFollowTime.intValue());
//            resJO.put("followCharge",GlobalVariable.greenLincenseFollowCharge.intValue());
//            resJO.put("allDayLimit",GlobalVariable.greenLincenseAllDayLimit.intValue());
//            resJO.put("payAdvanceOutTime",GlobalVariable.greenLincensePayAdvanceOutTime.intValue());
        }else if(carType==15){//特种车 3
            JinshiParkSetting jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(GlobalVariable.parkId,3);
            resJO.put("freeTime",jinshiParkSetting.getJpsFreeTime());
            resJO.put("firstTime",jinshiParkSetting.getJpsFirstTime());
            resJO.put("firstCharge",jinshiParkSetting.getJpsFirstCharge());
            resJO.put("followTime",jinshiParkSetting.getJpsFollowTime());
            resJO.put("followCharge",jinshiParkSetting.getJpsFollowCharge());
            resJO.put("allDayLimit",jinshiParkSetting.getJpsAlldayLimit());
            resJO.put("payAdvanceOutTime",jinshiParkSetting.getJpsAdvanceChargeOuttime());
//            resJO.put("freeTime",GlobalVariable.specialLincenseFreeTime.intValue());
//            resJO.put("firstTime",GlobalVariable.specialLicenseFirstTime.intValue());
//            resJO.put("firstCharge",GlobalVariable.specialLincenseFirstCharge.intValue());
//            resJO.put("followTime",GlobalVariable.specialLincenseFollowTime.intValue());
//            resJO.put("followCharge",GlobalVariable.specialLincenseFollowCharge.intValue());
//            resJO.put("allDayLimit",GlobalVariable.specialLincenseAllDayLimit.intValue());
//            resJO.put("payAdvanceOutTime",GlobalVariable.specialLincensePayAdvanceOutTime.intValue());
        }

//        resJO.put("parkId",GlobalVariable.parkId);
//        resJO.put("parkName",GlobalVariable.parkName);
//        resJO.put("parkNumber",GlobalVariable.parkNumber);
//        resJO.put("parkPlaceNumber",GlobalVariable.parkPlaceNumber);
//        resJO.put("parkCameraBrand",GlobalVariable.parkCameraBrand);
//        resJO.put("parkPicturePath",GlobalVariable.parkPicturePath);
        return resJO.toString();
    }
}
