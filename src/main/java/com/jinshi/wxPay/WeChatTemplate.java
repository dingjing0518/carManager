package com.jinshi.wxPay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import com.mall.common.utils.HttpSend;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jinshi.wxPay.WxConst.appId;
import static com.jinshi.wxPay.WxConst.appSecret;

@Controller
@RequestMapping("/WeChatTemplate")
@CrossOrigin
public class WeChatTemplate {
    
    private static Logger logger = LogManager.getLogger(WeChatTemplate.class.getName());
    
    @GetMapping("/sendTemp")
    @ResponseBody
    public void sendTemp(@RequestParam("openId") String openId,
                         @RequestParam("parkId") String parkId,
                         @RequestParam("cameraName") String cameraName) {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "grant_type=client_credential" + "&appid=" + appId + "&secret=" + appSecret;
        String accTemp = PayUtils.sendGet(url, param);
        JSONObject jsonObject = JSONObject.parseObject(accTemp);
        String accessToken = jsonObject.getString("access_token"); // 获取到了access_token，调用接口都要用到的，有时效

        // 封装要发送的json
        Map<String, Object> map = new HashMap();
        map.put("touser", openId);//你要发送给某个用户的openid 前提是已关注该公众号,该openid是对应该公众号的，不是普通的openid
//        map.put("template_id", "g-xysl1gLc3m0jETGbUvWarYCtv0UmUPeN1rA_X3I70");//模板消息id
        map.put("template_id", "Nzn0ORLkd82PdvWs1L9gbC9z52evYHvgakD8gwrZ-5Y");//模板消息id
        //map.put("url","https://www.vipkes.cn");//用户点击模板消息，要跳转的地址
        // 封装miniprogram 跳转小程序用,不跳不要填
//        Map<String, String> mapA = new HashMap<>();
//        mapA.put("appid", ""); //小程序appid
//        mapA.put("pagepath", ""); //小程序页面pagepath
//        map.put("miniprogram", mapA);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**
         * {{first.DATA}}
         * Time：{{keyword1.DATA}}
         * Host：{{keyword2.DATA}}
         * Service：{{keyword3.DATA}}
         * Status：{{keyword4.DATA}}
         * Message：{{keyword5.DATA}}
         * {{remark.DATA}}
         */
        // 封装first
        Map firstMap = new HashMap();
        firstMap.put("value", "摄像机异常通知"); //内容
        firstMap.put("color", "#173177"); //字体颜色

        // 封装keyword1 提交的问题
        Map keyword1Map = new HashMap();
        keyword1Map.put("value", simpleDateFormat.format(new Date()));
        keyword1Map.put("color", "#fff");

        // 封装keyword2此处也可以是商品名
        Map keyword2Map = new HashMap();
        if ("1".equals(parkId)) {
            keyword2Map.put("value", "森林公园P1P3停车场");
        } else if ("3".equals(parkId)){
            keyword2Map.put("value", "吴江停车场");
        }
        keyword2Map.put("color", "#DD5347"); //红色

        // 封装keyword2此处可以是商品价格
        Map keyword3Map = new HashMap();
        keyword3Map.put("value", cameraName);
        keyword3Map.put("color", "#DD5347");

        // 封装keyword4
        // 封装remark
        Map keyword4Map = new HashMap();
        keyword4Map.put("value", "error");
        keyword4Map.put("color", "#DD5347");//红色

        // 封装keyword5
        // 封装remark
        Map keyword5Map = new HashMap();
        keyword5Map.put("value", "摄像机异常");
        keyword5Map.put("color", "#fff");//红色

        Map remarkMap = new HashMap();
        remarkMap.put("value", "尊敬的用户," + cameraName + "摄像机" + "在" + simpleDateFormat.format(date) + "出现异常");
        remarkMap.put("color", "#fff");

        // 封装data
        Map dataMap = new HashMap();
        dataMap.put("first", firstMap);
        dataMap.put("keyword1", keyword1Map);
        dataMap.put("keyword2", keyword2Map);
        dataMap.put("keyword3", keyword3Map);
        dataMap.put("keyword4", keyword4Map);
        dataMap.put("keyword5", keyword5Map);
        dataMap.put("remark", remarkMap);

        map.put("data", dataMap);
        String r = HttpUtil.getJsonData(JSONObject.parseObject(JSON.toJSONString(map)), "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken);
        logger.info("-->" + r);
    }


//    public static void sendTempPoint(String appid,String appSecret, Integer Id, String name, String mobile, Double amount, String wechatOpenId) {
////        String url = "https://api.weixin.qq.com/cgi-bin/token";
////        String param = "grant_type=client_credential" + "&appid=" + appid + "&secret=" + appSecret;
////        String accTemp = HttpSend.sendGet(url, param);
//        String accessToken = WeChatController.getAccessToken(appid, appSecret).toJSONString();
////        JSONObject jsonObject = JSONObject.parseObject(accTemp);
////        logger.info("1--->" + jsonObject);
////        accessToken = jsonObject.getString("access_token"); // 获取到了access_token，调用接口都要用到的，有时效
//        // 封装要发送的json
//        Map<String, Object> map = new HashMap();
//        map.put("touser", wechatOpenId);//你要发送给某个用户的openid 前提是已关注该公众号,该openid是对应该公众号的，不是普通的openid
//        map.put("template_id", "672dPs0Jwl9zLvhVrncI0nyEHxX3Mx-fPNLBcIEb05A");//模板消息id
//        //map.put("url","https://www.vipkes.cn");//用户点击模板消息，要跳转的地址
//        // 封装miniprogram 跳转小程序用,不跳不要填
//        Map<String, String> mapA = new HashMap<>();
//        mapA.put("appid", ""); //小程序appid
//        mapA.put("pagepath", ""); //小程序页面pagepath
//        map.put("miniprogram", mapA);
//
//        // 以下就是根据模板消息的格式封装好，我模板的是：问题反馈结果通知  可以和我一样试试
//        // 封装first
//        Map firstMap = new HashMap();
//        firstMap.put("value", "会员积分消费提醒！"); //内容
//        firstMap.put("color", "#173177"); //字体颜色
//        String newname;
//        try {
//            newname = (java.net.URLDecoder.decode(name, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            newname = name;
//        }
//
//        // 封装keyword1 提交的问题
//        Map XM = new HashMap();
//        XM.put("value", newname);
//        XM.put("color", "#fff");
//
//        // 封装keyword2此处也可以是商品名
//        Map KH = new HashMap();
//        KH.put("value", Id);
//        KH.put("color", "#fff");
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 封装keyword3此处可以是商品价格
//        Map CONTENTS = new HashMap();
//        CONTENTS.put("value", "尊敬的用户,您的账户于" + simpleDateFormat.format(date) + "增加了" + amount + "积分");
//        CONTENTS.put("color", "#fff");
//
//
//        Map remarkMap = new HashMap();
//        remarkMap.put("value", "消息惠顾");
//        remarkMap.put("color", "#fff");
//
//        // 封装data
//        Map dataMap = new HashMap();
//        dataMap.put("first", firstMap);
//        dataMap.put("XM", XM);
//        dataMap.put("KH", KH);
//        dataMap.put("CONTENTS", CONTENTS);
//        dataMap.put("remark", remarkMap);
//
//        map.put("data", dataMap);
//        String r = HttpUtil.getJsonData(JSONObject.parseObject(JSON.toJSONString(map)), "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken);
//        logger.info("-->" + r);
//    }

}