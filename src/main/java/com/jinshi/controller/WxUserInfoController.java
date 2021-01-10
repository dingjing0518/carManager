package com.jinshi.controller;

import com.jinshi.entity.JinshiWxcar;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.WxUserInfo;
import com.jinshi.service.JinshiWxcarService;
import com.jinshi.service.LincensePlateService;
import com.jinshi.service.WxUserInfoService;
import com.jinshi.wxPay.PayUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxInfo")
@CrossOrigin
public class WxUserInfoController {

    private static Logger logger = LoggerFactory.getLogger(WxUserInfoController.class);

    @Autowired
    private WxUserInfoService wxUserInfoService;

    @Autowired
    private LincensePlateService lincensePlateService;

    @Autowired
    private JinshiWxcarService jinshiWxcarService;


    /**
     * 微信登录
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @GetMapping("/wxLogin")
    @CrossOrigin
    public @ResponseBody Map wxLogin(@RequestParam(value="encryptedData")String encryptedData,
                                     @RequestParam(value="iv")String iv,
                                     @RequestParam(value="code")String code) {
        logger.info("-------------------------进入登录授权-------------------------");
        Map map = new HashMap();

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        // 小程序唯一标识
        String wxspAppid = "wxd440bee620002bdc";
        // 小程序的 app secret
        String wxspSecret = "855a1757db22d24419cd14363fa5ff09";
        // 授权
        String grant_type = "authorization_code";

        //向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = PayUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //对encryptedData加密数据进行AES解密
        try {
            String result = PayUtils.decrypt(encryptedData, session_key, iv,"UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = new JSONObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("language",userInfoJSON.get("language"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("phoneNumber", userInfoJSON.get("phoneNumber"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//                userInfo.put("unionId", userInfoJSON.get("unionId"));
                /*// 解密unionId
                if (!userInfoJSON.isNull("unionId")) {
                    userInfo.put("unionId", userInfoJSON.get("unionId"));

                }*/

                //判断用户是否登录过小程序
                String openId = wxUserInfoService.selectByOpenid((String) userInfoJSON.get("openId"));
                if (null == openId) {
                    //添加用户信息到数据库
                    WxUserInfo wxUserInfo = new WxUserInfo();
                    wxUserInfo.setNickName((String) userInfoJSON.get("nickName"));
                    //设置性别
                    if ("1".equals(String.valueOf(userInfoJSON.get("gender")))) {
                        wxUserInfo.setGender("男");
                    } else {
                        wxUserInfo.setGender("女");
                    }
                    wxUserInfo.setLanguage((String) userInfoJSON.get("language"));
                    wxUserInfo.setCity((String) userInfoJSON.get("city"));
                    wxUserInfo.setProvince((String) userInfoJSON.get("province"));
                    wxUserInfo.setCountry((String) userInfoJSON.get("country"));
                    //                wxUserInfo.setPhoneNumber((String) userInfoJSON.get("phoneNumber"));
                    //                wxUserInfo.setPhoneNumber("13132121892");
                    wxUserInfo.setAvatarurl((String) userInfoJSON.get("avatarUrl"));
                    wxUserInfo.setOpenid(openid);
                    wxUserInfoService.insert(wxUserInfo);
                } else {
                    //如果不为空则更新用户信息
                    //因为微信个人信息修改一般两小时后才生效，所以会出现更新不及时的情况
                    WxUserInfo wxUserInfo = new WxUserInfo();
                    wxUserInfo.setNickName((String) userInfoJSON.get("nickName"));
                    //设置性别
                    if ("1".equals(String.valueOf(userInfoJSON.get("gender")))) {
                        wxUserInfo.setGender("男");
                    } else {
                        wxUserInfo.setGender("女");
                    }
                    wxUserInfo.setLanguage((String) userInfoJSON.get("language"));
                    wxUserInfo.setCity((String) userInfoJSON.get("city"));
                    wxUserInfo.setProvince((String) userInfoJSON.get("province"));
                    wxUserInfo.setCountry((String) userInfoJSON.get("country"));
                    //                wxUserInfo.setPhoneNumber((String) userInfoJSON.get("phoneNumber"));
                    //                wxUserInfo.setPhoneNumber("13132121892");
                    wxUserInfo.setAvatarurl((String) userInfoJSON.get("avatarUrl"));
                    wxUserInfo.setOpenid(openid);
                    Integer i = wxUserInfoService.updateByOpenid(wxUserInfo);
                }

                map.put("userInfo", userInfo);
            } else {
                map.put("status", 0);
                map.put("msg", "解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 查询小程序用户信息
     * @return
     */
    @GetMapping("/selectWxUser")
    @CrossOrigin
    @ResponseBody
    public List<JinshiWxcar> selectWxUser(String openid) {

        logger.info("查询用户信息接收到的用户openid: " + openid);
        List<JinshiWxcar> list = jinshiWxcarService.selectWxUser(openid);
        return  list;
    }


    /**
     * 查询小程序用户停车记录
     * @return
     */
    @GetMapping("/selectPlateRecord")
    @CrossOrigin
    @ResponseBody
    public List<LincensePlate> selectPlateRecord (String openid) {

        logger.info("查询停车记录接收到的用户openid: " + openid);
        List<LincensePlate> plateList = lincensePlateService.selectByOpenid(openid);
        return  plateList;
    }

}
