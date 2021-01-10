package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Cartt;
import com.jinshi.entity.Log;
import com.jinshi.util.GlobalVariable;
import io.swagger.annotations.Api;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/changshuController")
@CrossOrigin
@Api(tags = "常熟接口管理")
public class ChangshuController {

    private static Logger logger = LogManager.getLogger(ChangshuController.class.getName());

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String get(@RequestBody Log log){
        //进出时间
        Date addtime = log.getAddtime();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = ft.format(addtime);
        //小区名称
        String communityname = log.getCommunityname();
        //道闸的名称
        String doorname = log.getDoorname();
        //车牌号
        String licenseplate = log.getLicenseplate();
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse response;
        String reponseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("communityname", communityname);
            jsonObject.put("addtime", time);
            jsonObject.put("doorname",doorname);
            jsonObject.put("licenseplate",licenseplate);
            httpPost = new HttpPost("http://47.97.216.24:33333/ssm/iotRoadgateRecordController/insert");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            reponseContent = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
            return reponseContent;
        } catch (Exception e) {
            return " 45";
        }
    }

    /**
     * 修改状态
     */
//    @CrossOrigin
//    @GetMapping("/updateCSCODE")
//    public void updateCSCODE(@RequestParam("code") Integer code) {
//        if (1 == code) {
//            GlobalVariable.csCode = 1;
//            logger.info("修改对接接口为1：常熟");
//        } else {
//            GlobalVariable.csCode = 0;
//            logger.info("修改对接接口为0：无");
//        }
//    }

    @RequestMapping(value = "/car",method = RequestMethod.POST)
    @CrossOrigin
    public String car(@RequestBody Cartt cartt){
        //进出时间
        Date addtime = cartt.getSnapTime();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = ft.format(addtime);
        //小区名称
        String communityname = cartt.getCommunityName();
        //摄像头品牌
        String cameraBrand = cartt.getCameraBrand();
        //摄像头型号
        String cameraType = cartt.getCameraType();
        //摄像头序列号
        String cameraNumber = cartt.getCameraNumber();
        //车牌颜色
        String colorPlate = cartt.getColorPlate();
        //车牌号码
        String licensePlate = cartt.getLicensePlate();
        //过车场景图参数
        String snapPhotoMul = cartt.getSnapPhotoMul();
        //过车通道
        String crossChannel = cartt.getCrossChannel();
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse response;
        String reponseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("communityname", communityname);
            jsonObject.put("snapTime", time);
            jsonObject.put("cameraBrand",cameraBrand);
            jsonObject.put("cameraType",cameraType);
            jsonObject.put("cameraNumber",cameraNumber);
            jsonObject.put("licensePlate",licensePlate);
            jsonObject.put("colorPlate",colorPlate);
            jsonObject.put("snapPhotoMul",snapPhotoMul);
            jsonObject.put("crossChannel",crossChannel);
            httpPost = new HttpPost("http://47.97.216.24:33333/ssm/upload/iotCarCrossInserts");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            reponseContent = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
            return reponseContent;
        } catch (Exception e) {
            return " 45";
        }
    }
}
