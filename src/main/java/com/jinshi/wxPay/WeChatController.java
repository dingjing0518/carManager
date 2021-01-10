package com.jinshi.wxPay;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jinshi.controller.WxUserInfoController;
import com.jinshi.entity.JinshiWxcar;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.Member;
import com.jinshi.entity.WxUserInfo;
import com.jinshi.service.WxUserInfoService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jinshi.wxPay.WxConst.appId;
import static com.jinshi.wxPay.WxConst.appSecret;

@Controller
@RequestMapping("/WeChat")
@CrossOrigin
public class WeChatController {

    private static Logger logger = LogManager.getLogger(WeChatController.class.getName());

//    String token = Wechat.getAccess_token(appId, appSecret).getString("access_token");

    @Autowired
    private WxUserInfoService wxUserInfoService;

    /**
     * 获取请求用户信息的access_token
     */
    @GetMapping("/getUserInfoAccessToken")
    @CrossOrigin
    @ResponseBody
    public static Map<String, Object> getUserInfoAccessToken(@RequestParam("code") String code) {
        logger.info("-----------code：" + code);
        JSONObject object = null;
//        String appid = "wxa93aeac9e8d90a60";
//        String secret = "51e6b653a0b5267f580afc3dee52c184";
        Map<String, Object> data = new HashMap();
        try {
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                    appId, appSecret, code);
            logger.info("request accessToken from url: {}", url);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String tokens = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            object = token_gson.fromJson(tokens, JSONObject.class);
            logger.info("request accessToken success. [result={}]", object);
            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            logger.error("fail to request wechat access token. [error={}]", ex);
        }
        logger.info("返回信息：" + data);
        return data;
    }

    /**
     * 获取用户手机号
     * @return
     */
    @GetMapping("/getUserPhone")
    @CrossOrigin
    @ResponseBody
    public Integer getUserPhone(@RequestParam("openId") String openId,@RequestParam("phoneNumber") String phoneNumber) {
        logger.info("openid:  " + openId);
        logger.info("手机号为:  " + phoneNumber);
        WxUserInfo wxUserInfo = new WxUserInfo();
        wxUserInfo.setPhoneNumber(phoneNumber);
        wxUserInfo.setOpenid(openId);
        wxUserInfoService.insert(wxUserInfo);
        logger.info("openId为：" + openId);
        logger.info("手机号为：" + phoneNumber);
        return  1;
    }


    /**
     * 获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static Map<String, Object> getUserInfo(String accessToken, String openId) {
        Map<String, Object> data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        logger.info("request user info from url: {}", url);
        JSONObject userInfo = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            userInfo = token_gson.fromJson(response, JSONObject.class);
            logger.info("get userinfo success. [result={}]", userInfo);
            data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
            data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
            data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
            data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
            data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            logger.error("fail to request wechat user info. [error={}]", ex);
        }
        return data;
    }


    @GetMapping("/getAccessToken")
    @ResponseBody
    public static JSONObject getAccessToken() {

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String accessToken = null;
        JSONObject jsonObj = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            accessToken = new String(jsonBytes, "UTF-8");
            System.err.println(accessToken + "-------123456489");
            jsonObj = JSONObject.parseObject(accessToken);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

}
