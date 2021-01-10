package com.jinshi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshi.controller.JinshiCCBPayController;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import com.jinshi.entity.JinshiParking;
import com.jinshi.entity.Log;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.ibm.media.codec.audio.g723.G723Tables.s;

public class OrderIdForCCB {

    private static org.apache.log4j.Logger logger = Logger.getLogger(JinshiCCBPayController.class.getName());

    public static String getOrderIdByUUId() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        String order_id = dateName+random_order;
        return order_id;
    }

    public static String getOrderIdByUUIdForCCB() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+9)+10000;
        String random_order = String.valueOf(x);
        String order_id = dateName+random_order;
        return order_id;
    }
    public static String getOrderIdByUUIdAddPlate(String carNumber) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String substring = carNumber.substring(carNumber.length() - 6);
        String s = ChineseUtil.filterChinese(substring);
        if (s.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (s.length() < 6) {
                sb.append("I");
                sb.append(s);
                s = sb.toString();
            }
        }
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        JinshiParking jinshiParking = selectRealCameraId();
        String jpParkNumber = jinshiParking.getJpParkNumber();
        String order_id = "01" + dateName +jpParkNumber + s;
        return order_id;
    }
    public static String getOrderIdByUUIdAddPlate(String carNumber,Integer parkId) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String substring = carNumber.substring(carNumber.length() - 6);
        String s = ChineseUtil.filterChinese(substring);
        if (s.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (s.length() < 6) {
                sb.append("I");
                sb.append(s);
                s = sb.toString();
            }
        }
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        JinshiParking jinshiParking = selectRealCameraId(parkId);
        String jpParkNumber = jinshiParking.getJpParkNumber();
        String order_id = "01" + dateName +jpParkNumber + s;
        return order_id;
    }

    public static String toChineseHex(String s) {
        String ss = s;
        byte[] bt = new byte[0];
        try {
            bt = ss.getBytes("GBK");
        }catch (Exception e){
            e.printStackTrace();
        }
        String s1 = "";
        for (int i = 0; i < bt.length; i++) {
            String tempStr = Integer.toHexString(bt[i]);
            if (tempStr.length() > 2)
                tempStr = tempStr.substring(tempStr.length() - 2);
            s1 = s1 + tempStr + "";
        }
        return s1.toUpperCase();
    }

    public static JinshiParking selectRealCameraId(){
        String url = GlobalVariable.urlNew + "parkingJinshi/selectParkByParkId";
        RestTemplate restTemplate=new RestTemplate();
        JinshiParking jinshiParking = new JinshiParking();
        jinshiParking.setJpId(GlobalVariable.parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiParking,String.class);
        String body = responseEntity.getBody();
        JinshiParking spare = JSONObject.parseObject(body,JinshiParking.class);
        return spare;
    }
    public static JinshiParking selectRealCameraId(Integer parkId){
        String url = GlobalVariable.urlNew + "parkingJinshi/selectParkByParkId";
        //String url = "http://192.168.0.29:8080/carmanager_war/parkingJinshi/selectParkByParkId";
        RestTemplate restTemplate=new RestTemplate();
        JinshiParking jinshiParking = new JinshiParking();
        jinshiParking.setParkId(parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiParking,String.class);
        String body = responseEntity.getBody();
        JSONArray objects = JSONArray.parseArray(body);
        JSONObject o = new JSONObject();
        if(objects.size()>0){
            o = (JSONObject) objects.get(0);
        }
        JinshiParking spare = JSONObject.parseObject(o.toJSONString(),JinshiParking.class);
      return spare;
    }

    public static String getOrderIdByUUId(String carNumber,String parkId) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String substring = carNumber.substring(carNumber.length() - 6);
        String s = ChineseUtil.filterChinese(substring);
        String a = "";
        if (s.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (s.length() < 6) {
                sb.append("I");
                sb.append(s);
                s = sb.toString();
            }
        }
        Integer jcgParkingId = Integer.valueOf(parkId);

        if (jcgParkingId==1){
            a="00001";
        }
        if (jcgParkingId==3) {
            a = "00003";
        }
        if (jcgParkingId==27){
            a="00004";
        }
        if (jcgParkingId==28){
            a="00005";
        }

        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        String order_id = dateName +a + s;
        return order_id;
    }
}
