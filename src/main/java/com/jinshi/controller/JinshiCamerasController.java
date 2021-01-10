package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.mapper.JinshiAreaMapper;
import com.jinshi.service.JinshiAreaService;
import com.jinshi.service.JinshiCameraService;
import com.jinshi.service.JinshiCamerasSpareService;
import com.jinshi.service.UserService;
import com.jinshi.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/jinshiCameras")
@CrossOrigin
@Api(tags = "摄像机管理")
public class JinshiCamerasController {

    private static Logger logger = Logger.getLogger(JinshiCamerasController.class.getName());

    @Autowired
    private JinshiCameraService jinshiCameraService;

    @Autowired
    private QianYiCameraUtil qianYiCameraUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private JinshiCamerasSpareService jinshiCamerasSpareService;

    @Autowired
    private JinshiAreaService jinshiAreaService;

//    @CrossOrigin
//    @RequestMapping(value = "/connCamera",method = RequestMethod.POST)
//    @ResponseBody
//    public String connCamera(@RequestBody JSONObject jsonParam){
//        String ipAddress = (String) jsonParam.get("ipAddress");
//        QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil(ipAddress, CameraParam.net);
//        Integer integer = qianYiCameraUtil.connCamera();
//        return integer.toString();
//    }

    /**
     * 出口在前排列
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectByParkIdDesc",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "摄像机管理---查询所有出口在前排列")
    public List<JinshiCameras> selectByParkIdDesc(@RequestBody JSONObject jsonParam) {
        String parkId = (String) jsonParam.get("parkId");
        return jinshiCameraService.selectByParkIdDesc(parkId);
    }
    /**
     * 设置车队模式开启（jcStartTime + jcEndTime + jc_is_car_team=1）
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @PostMapping("/openCarTeam")
    @ApiOperation(value = "摄像机管理---设置车队模式开启")
    public Integer openCarTeam(@RequestBody JSONObject jsonParam) throws Exception{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer jcId = (Integer) jsonParam.get("jcId");
        String jcStartTime = (String) jsonParam.get("jcStartTime");
        String jcEndTime = (String) jsonParam.get("jcEndTime");
        int lengthStart = jcStartTime.length();
        int lengthEnd = jcEndTime.length();
        if (lengthStart < 8) {
            StringBuffer sb = new StringBuffer();
            sb.append(jcStartTime);
            sb.append(":00");
            jcStartTime = sb.toString();
        }
        if (lengthEnd < 8) {
            StringBuffer sb = new StringBuffer();
            sb.append(jcEndTime);
            sb.append(":00");
            jcEndTime = sb.toString();
        }
        JSONObject jsonObject = DateUtils.subtimeToDate(jcStartTime, jcEndTime);
        String jpsStartTime = (String) jsonObject.get("jpsStartTime");
        String jpsEndTime = (String) jsonObject.get("jpsEndTime");
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcId(jcId);
        jinshiCameras.setJcStartTime(format.parse(jpsStartTime));
        jinshiCameras.setJcEndTime(format.parse(jpsEndTime));
        GlobalVariable.jcIsCarTeam = 1;
        return jinshiCameraService.updateCarTeam(jinshiCameras);
    }
    /**
     * 关闭车队模式（jc_is_car_team=0）
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @PostMapping("/closeCarTeam")
    @ApiOperation(value = "摄像机管理---关闭车队模式")
    public Integer closeCarTeam(@RequestBody JSONObject jsonParam) {
        JinshiCameras jinshiCameras = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCameras.class);
        GlobalVariable.jcIsCarTeam = 0;
        return jinshiCameraService.updateOpenMode(jinshiCameras);
    }
    @CrossOrigin
    @RequestMapping(value = "/selectCamerasAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "摄像机管理---查询所有")
    public String selectCamerasAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiCameras> res = jinshiCameraService.selectJinshiCamerasForPage(pageNum,pageSize,Integer.parseInt(parkId));
        int camerasTotalRecords = jinshiCameraService.getJinshiCamerasTotalRecords(Integer.parseInt(parkId));
        JSONObject resJo = new JSONObject();
        resJo.put("camerasListData",res);
        resJo.put("camerasTotalRecords",camerasTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 总控查询摄像机信息
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectCameras",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设备管理---查询所有")
    public JSONObject selectCameras(@RequestBody JSONObject jsonParam){
        String username = (String) jsonParam.get("username");
        JSONObject jsonObject = new JSONObject();
        if (null != username) {
            if ("admin".equals(username)) {
                List<JinshiCameras> list1 = jinshiCameraService.selectCamerasForKS();
                List<JinshiCameras> list2 = jinshiCameraService.selectCamerasForWJ();
                List<JinshiCameras> list3 = jinshiCameraService.selectCamerasForTY();
                jsonObject.put("parkKS",list1);//昆山P1P3
                jsonObject.put("parkWJ",list2);//吴江
                jsonObject.put("parkTY",list3);//昆山体育中心  todo--------------------
            } else {
                User user = userService.selectAllByUserName(username);
                List<JinshiCameras> list = jinshiCameraService.selectCamerasByParkId(String.valueOf(user.getParkid()));
                jsonObject.put("park",list);
            }
        }
        return jsonObject;
    }

    /**
     * 修改摄像机状态
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/updateCamerasCode",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateCamerasCode(@RequestBody JSONObject jsonParam){
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcCode((String) jsonParam.get("jcCode"));
        jinshiCameras.setJcThandle((String) jsonParam.get("jcThandle"));
        jinshiCameras.setJcParking((String) jsonParam.get("jcParking"));
        return jinshiCameraService.updateCamerasCode(jinshiCameras);
    }

    /**
     * 总控抬杆发请求
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/openGateSend")
    @ResponseBody
    @ApiOperation(value = "设备管理---总控抬杆")
    public Integer openGateSend(@RequestParam("thandle") Integer thandle, @RequestParam("jcParking") String jcParking){
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcThandle(String.valueOf(thandle));
        jinshiCameras.setJcParking(jcParking);
        jinshiCameras.setJcSort("0");
        return jinshiCameraService.updateCamerasSort(jinshiCameras);
    }

    /**
     * 总控落杆发请求
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/closeGateSend")
    @ResponseBody
    @ApiOperation(value = "设备管理---总控落杆")
    public Integer closeGateSend(@RequestParam("thandle") Integer thandle, @RequestParam("jcParking") String jcParking){
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcThandle(String.valueOf(thandle));
        jinshiCameras.setJcParking(jcParking);
        jinshiCameras.setJcSort("1");
        return jinshiCameraService.updateCamerasSort(jinshiCameras);
    }

    @CrossOrigin
    @RequestMapping(value = "/searchCameras",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "摄像机管理---搜索")
    public String searchCameras(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<JinshiCameras> res = jinshiCameraService.searchJinshiCameras(keyWord,pageNum,pageSize);
        int cameraTotalRecords = jinshiCameraService.getJinshiCamerasTotalRecords(GlobalVariable.parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("camerasListData",res);
        resJo.put("camerasTotalRecords",cameraTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 修改所有
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    @ApiOperation(value = "摄像机管理---编辑")
    public Integer updateByPrimaryKey(@RequestBody JSONObject jsonParam) {
        JinshiCameras jinshiCameras = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCameras.class);
        return jinshiCameraService.updateByPrimaryKey(jinshiCameras);
    }

    /**
     * 修改摄像机
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateByCameras", method = RequestMethod.POST)
    @ApiOperation(value = "摄像机管理---修改")
    public Integer updateByCameras(@RequestBody JSONObject jsonParam) {
        JSONObject jsonObject = jsonParam.getJSONObject("setData");
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcId((Integer) jsonObject.get("jcId"));
        jinshiCameras.setJcName((String) jsonObject.get("jcName"));
        jinshiCameras.setJcIpAddress((String) jsonObject.get("jcIpAddress"));
        jinshiCameras.setJcAccess((String) jsonObject.get("jcAccess"));
        jinshiCameras.setJcRemarks((String) jsonObject.get("jcRemarks"));
        jinshiCameras.setJcCamerasId((String) jsonObject.get("jcCamerasId"));
        jinshiCameras.setJcMac((String) jsonObject.get("jcMac"));
        jinshiCameras.setJcArea((String) jsonObject.get("jcArea"));
        jinshiCameras.setJcParking((String) jsonParam.get("parkId"));
        jinshiCameras.setJcAgent((String) jsonParam.get("agentId"));
        return jinshiCameraService.updateByCameras(jinshiCameras);
    }

    @RequestMapping(value = "/cameraTest", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String cameraTest(@RequestBody JSONObject jsonParam){
        QianYiCameraUtil qyu = new QianYiCameraUtil((String) jsonParam.get("jcIpAddress"), CameraParam.net);
//        JSONObject deviceNetMessage = qyu.getDeviceNetMessage();
//        String maskAddress = (String) deviceNetMessage.get("maskAddress");
//        if(maskAddress!=null){
//            return "状态正常";
//        }else{
//            return "状态异常,请删除后重新添加";
//        }
        return "";
    }
    @RequestMapping(value = "/startSocket", method = RequestMethod.GET)
    @ResponseBody
    public String startServiceSocket() throws IOException {
        SocketServer.getClientMessage4Socket();
        return "success";
    }

    @RequestMapping(value = "/startClientSocket", method = RequestMethod.GET)
    @ResponseBody
    public String startClientSocket() throws IOException {
        SocketClient.connectService4Socket();
        return "success";
    }

    @RequestMapping(value = "/sentClient", method = RequestMethod.GET)
    @ResponseBody
    public String sentClient() throws IOException {
        GlobalVariable.sentClientMessage = "津MYL400";
        return "success";
    }
    @RequestMapping(value = "/addCamera", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String addCamera(){
//        String jcIpAddress = (String) jsonParam.get("jcIpAddress");
        String ip = "192.168.1.108";
        String ip1 = "192.168.1.105";
        QianYiCameraUtil qyu = new QianYiCameraUtil(ip, CameraParam.net);
        QianYiCameraUtil qyu1 = new QianYiCameraUtil(ip1, CameraParam.net);
        Integer integer = qyu.addCamera(ip);
        Integer integer1 = qyu1.addCamera(ip1);
        qyu.connCamera(integer);
        qyu1.connCamera(integer1);
        int tHandle = qyu.gettHandle();
        int tHandle1 = qyu1.gettHandle();
        JinshiCameras jinshiCameras = jinshiCameraService.selectByIpAddress(ip);
        JinshiCameras jinshiCameras1 = jinshiCameraService.selectByIpAddress(ip1);
        CameraParam.cameraMap.put(tHandle,jinshiCameras);
        CameraParam.cameraMap.put(tHandle1,jinshiCameras1);
        return "success";
    }
    @RequestMapping(value = "/connCamera", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String connCamera(){
//        String jcIpAddress = (String) jsonParam.get("jcIpAddress");
        String ip = "192.168.1.108";
        String ip1 = "192.168.1.105";
        QianYiCameraUtil qyu = new QianYiCameraUtil(ip, CameraParam.net);
        QianYiCameraUtil qyu1 = new QianYiCameraUtil(ip1, CameraParam.net);
//        qyu.addCamera(jcIpAddress);
//        qyu.connCamera();
//        qyu1.connCamera();
        int tHandle = qyu.gettHandle();
        int tHandle1 = qyu1.gettHandle();
        JinshiCameras jinshiCameras = jinshiCameraService.selectByIpAddress(ip);
        JinshiCameras jinshiCameras1 = jinshiCameraService.selectByIpAddress(ip1);
        CameraParam.cameraMap.put(tHandle,jinshiCameras);
        CameraParam.cameraMap.put(tHandle1,jinshiCameras1);
        return "success";
//        if(tHandle!=-1){
//            jinshiCameras.setJcThandle(String.valueOf(tHandle));
//            jinshiCameras.setJcSubnet((String) qyu.getDeviceNetMessage().get("maskAddress"));
//            jinshiCameras.setJcGateway((String) qyu.getDeviceNetMessage().get("gateWayAddress"));
//            jinshiCameras.setJcDns((String) qyu.getDeviceNetMessage().get("dns1"));
//            jinshiCameras.setJcLicense(qyu.getDeviceLicense());
//            jinshiCameras.setJcMac(qyu.getDeviceMac());
//            int i = jinshiCameraService.updateByPrimaryKey(jinshiCameras);
//            for (int j = 0; j < CameraParam.cameraList.size(); j++) {
//                JinshiCameras jc = CameraParam.cameraList.get(j);
//                if(jc.getJcIpAddress().equals(jinshiCameras.getJcIpAddress())){
//                    CameraParam.cameraList.remove(j);
//                    CameraParam.cameraList.add(jinshiCameras);
//                }
//            }
//            return "连接成功";
//        }else{
//            return "连接失败：设备已连接";
//        }
    }

    /**
     * 添加线上版本
     */
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin
//    @ApiOperation(value = "摄像机管理---添加线上版本")
//    public Integer insert(@RequestBody JSONObject jsonParam) throws ParseException {
//        String jcIpAddress = (String) jsonParam.get("jcIpAddress");
//        Integer thandle = GlobalVariable.util.addCamera(jcIpAddress);
//        GlobalVariable.util.connCamera(thandle);
//        JinshiCameras jinshiCameras = new JinshiCameras();
//        String deviceLicense = GlobalVariable.util.getDeviceLicense(thandle);
//        String deviceMac = GlobalVariable.util.getDeviceMac(thandle);
//        JSONObject deviceNetMessage = GlobalVariable.util.getDeviceNetMessage(thandle);
//        jinshiCameras.setJcThandle(String.valueOf(thandle));
//        jinshiCameras.setJcSubnet((String) deviceNetMessage.get("maskAddress"));
//        jinshiCameras.setJcGateway((String) deviceNetMessage.get("gateWayAddress"));
//        jinshiCameras.setJcDns((String) deviceNetMessage.get("dns1"));
//        jinshiCameras.setJcLicense(deviceLicense);
//        jinshiCameras.setJcMac(deviceMac);
//        jinshiCameras.setJcAccess((String) jsonParam.get("jcAccess"));
//        jinshiCameras.setJcAgent(String.valueOf(GlobalVariable.agentId));
//        jinshiCameras.setJcCreatetime(new Date());
//        jinshiCameras.setJcIpAddress((String) jsonParam.get("jcIpAddress"));
//        jinshiCameras.setJcName((String) jsonParam.get("jcName"));
//        jinshiCameras.setJcParking(String.valueOf(GlobalVariable.parkId));
//        jinshiCameras.setJcSort((String) jsonParam.get("jcSort"));
//        jinshiCameras.setJcRemarks((String) jsonParam.get("jcRemarks"));
//        jinshiCameras.setJcCamerasId((String) jsonParam.get("jcCamerasId"));
//        jinshiCameras.setJcArea((String) jsonParam.get("jcArea"));
//        CameraParam.cameraMap.put(thandle,jinshiCameras);
//        jinshiCameraService.insert(jinshiCameras);
//
//        JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
//        jinshiCamerasSpare.setJcsSpareThandle(0);
//        jinshiCamerasSpare.setJcsCameraId((String) jsonParam.get("jcCamerasId"));
//        jinshiCamerasSpare.setJcsParkId(Integer.valueOf((String) jsonParam.get("parkId")));
//        jinshiCamerasSpare.setJcsAgentId(Integer.valueOf((String) jsonParam.get("agentId")));
//        return jinshiCamerasSpareService.insert(jinshiCamerasSpare);
//    }

    /**
     * 添加测试版本
     * @param jsonParam
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "摄像机管理---添加测试版本")
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());

        JSONObject jsonObject = jsonParam.getJSONObject("setData");
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcLicense((String) jsonObject.get("jcLicense"));
        jinshiCameras.setJcMac((String) jsonObject.get("jcMac"));
        jinshiCameras.setJcAccess((String) jsonObject.get("jcAccess"));
        jinshiCameras.setJcAgent((String) jsonParam.get("agentId"));
        jinshiCameras.setJcCreatetime(new Date());
        jinshiCameras.setJcIpAddress((String) jsonObject.get("jcIpAddress"));

        jinshiCameras.setJcName((String) jsonObject.get("jcName"));
        jinshiCameras.setJcParking((String) jsonParam.get("parkId"));
        jinshiCameras.setJcSort((String) jsonObject.get("jcSort"));
        jinshiCameras.setJcRemarks((String) jsonObject.get("jcRemarks"));
        jinshiCameras.setJcArea((String) jsonObject.get("jcArea"));
        String parkId = (String) jsonParam.get("parkId");
        List<JinshiCameras> list = jinshiCameraService.selectByThandleDesc(parkId);
        if (list.size() > 0) {
            JinshiCameras jinshiCameras1 = list.get(0);
            Integer jcThandle = Integer.parseInt(jinshiCameras1.getJcThandle());
            jinshiCameras.setJcThandle(String.valueOf(jcThandle + 1));
        } else {
            jinshiCameras.setJcThandle("1");
        }

        jinshiCameras.setJcCamerasId((String) jsonObject.get("jcCamerasId"));
        //添加摄像机编号测试代码
//        String jcArea = (String) jsonObject.get("jcArea");
//        JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(jcArea, Integer.valueOf(parkId));
//        StringBuilder sb = new StringBuilder();
//        sb.append(jinshiArea.getAreaNumber());
//        Integer size = 0;
//        String s = "";
//        if (list.size() > 0) {
//            size = list.size() + 1;
//            s = String.valueOf(size);
//            while (s.length() < 3) {
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("0");
//                sb1.append(s);
//                s = sb1.toString();
//            }
//        } else {
//            s = "001";
//        }
//        sb.append(s);
//        jinshiCameras.setJcCamerasId(sb.toString());

        jinshiCameraService.insert(jinshiCameras);

        //添加摄像机附表的记录
        JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
        jinshiCamerasSpare.setJcsSpareThandle(0);
        jinshiCamerasSpare.setJcsParkId(Integer.valueOf((String) jsonParam.get("parkId")));
        jinshiCamerasSpare.setJcsAgentId(Integer.valueOf((String) jsonParam.get("agentId")));
        jinshiCamerasSpare.setJcsCameraId((String) jsonObject.get("jcCamerasId"));
        return jinshiCamerasSpareService.insert(jinshiCamerasSpare);
    }

    /**
     * 删除线上版本
     * @param jsonParam
     * @return
     */
//    @ResponseBody
//    @CrossOrigin
//    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
//    @ApiOperation(value = "摄像机管理---删除线上版本")
//    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
//        Integer camerasid = (Integer) jsonParam.get("camerasid");
//        JinshiCameras jinshiCameras = jinshiCameraService.selectByPrimaryKey(camerasid);
//        String jcThandle = jinshiCameras.getJcThandle();
//        Integer tHandle = Integer.parseInt(jcThandle);
//        GlobalVariable.util.disConnCamera(tHandle);
//        GlobalVariable.util.delCamera(tHandle);
//        return jinshiCameraService.deleteByPrimaryKey(camerasid);
//    }

    /**
     * 删除测试版本
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "摄像机管理---删除测试版本")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer camerasid = (Integer) jsonParam.get("camerasid");
        JinshiCameras jinshiCameras = jinshiCameraService.selectByPrimaryKey(camerasid);
        String jcCamerasId = jinshiCameras.getJcCamerasId();
        String jcParking = jinshiCameras.getJcParking();
        jinshiCamerasSpareService.deleteByParkId(jcCamerasId,Integer.valueOf(jcParking));
        return jinshiCameraService.deleteByPrimaryKey(camerasid);
    }

    @RequestMapping(value = "/updateLedScreenType",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer updateLedScreenType(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiCameras jinshiCameras = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCameras.class);
        return jinshiCameraService.updateLedScreenType(jinshiCameras);
    }

    /**
     * 修改摄像机进出场确认模式
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/updateIsType",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Boolean updateIsType(@RequestBody JSONObject jsonParam) {
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcId((Integer) jsonParam.get("jcId"));
        jinshiCameras.setJcIsType((Integer) jsonParam.get("jcIsType"));
        return jinshiCameraService.updateIsType(jinshiCameras);
    }

    @RequestMapping(value = "/selectByThandle",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JinshiCameras selectByThandle(@RequestBody JSONObject jsonParam) {
        String jcThandle = (String) jsonParam.get("jcThandle");
        String jcParking = (String) jsonParam.get("jcParking");
        return jinshiCameraService.selectByThandle(jcParking,jcThandle);
    }
    @RequestMapping(value = "/selectCameraId",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JinshiCameras selectCameraId(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        JinshiCameras jinshiCameras = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCameras.class);
        return jinshiCameraService.selectCameraId(jinshiCameras);
    }
//    @RequestMapping("/insert")
//    public Integer insert(JinshiCameras record) {
//
//        return jinshiCameraService.insert(record);
//    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(JinshiCameras record) {

        return jinshiCameraService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public JinshiCameras selectByPrimaryKey(Integer id) {

        return jinshiCameraService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(JinshiCameras record) {

        return jinshiCameraService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(JinshiCameras record) {

        return jinshiCameraService.updateByPrimaryKey(record);
    }

}
