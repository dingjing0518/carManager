package com.jinshi.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * 阿里云 短信验证码
 *
 *
 */
@RestController
@RequestMapping("/send")
@CrossOrigin
public class SmsController {


    /**
     * 通知关注的车辆
     * @param phone
     * @param lincense
     * @param datatime
     * @param yard
     * @param thandle
     * @return
     * @throws Exception
     */
    @GetMapping("/sms")
    public Map<String,String> sms(String phone,String lincense,String datatime,String yard,String thandle) throws Exception{

        Map<String, String> map = new HashMap<>();
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();

        //phone 手机号是否合法
        if (phoneFormatCheckUtils.isPhoneLegal(phone)) {

//            //生成六位随机验证码
//            String randomNumeric = RandomStringUtils.randomNumeric(6);
            //System.out.println(randomNumeric);
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";
            //替换AK
//            final String accessKeyId = "LTAI4FgKGQ4vnSv8NgtcMdhH";//你的accessKeyId,参考本文档步骤2
//            final String accessKeySecret = "0MmwRQWmU1SBSTMvkWE9nJPAN39v8J";//你的accessKeySecret，参考本文档步骤2

            final String accessKeyId = "LTAICKdKW8U8JCrJ";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "FmmOb6UfBFeFE1iIHSceo3cQmcXxNv";//你的accessKeySecret，参考本文档步骤2   金石
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("金石停车");
            //必填:短信模板-可在短信控制台中找到
//            request.setTemplateCode("SMS_175485127");SMS_175490140
            request.setTemplateCode("SMS_175490140");
            //可选:模板中的变量替换JSON串

            request.setTemplateParam("{'lincense':'"+lincense+"','datatime':'" + datatime + "','yard':'" + yard + "','thandle':'" + thandle + "'}");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                map.put("code", "SUCCESS");
                map.put("msg", "短信发送成功");
//                map.put("randomNumeric", randomNumeric);

                return map;
            }
        }else {

         map.put("code","FAIL");
         map.put("msg","输入的手机号不合法");
         return map;

        }

        map.put("code","FAIL");
        map.put("msg","短信发送失败");
        return map;

    }

    public static void main(String[] args) throws Exception {
        SmsController smsController = new SmsController();
//        Map<String, String> sms = smsController.sms("18622139742","津MYL400","2019年10月11日 13:21:15","森林公园体育中心P1停车场","南出口1");
        Map<String, String> sms = smsController.smsThandle("13132121892","昆山停车场","南出口","2020年03月17日 13:21:15");
        System.out.println(sms);
    }

    /**
     * 摄像机异常通知
     * @param phone
     * @param parkName
     * @param thandle
     * @return
     * @throws Exception
     */
    @GetMapping("/smsThandle")
    public Map<String,String> smsThandle(String phone, String parkName, String thandle, String datatime) throws Exception{
        Map<String, String> map = new HashMap<>();
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        //phone 手机号是否合法
        if (phoneFormatCheckUtils.isPhoneLegal(phone)) {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";
            //替换AK
//            final String accessKeyId = "LTAI4FgKGQ4vnSv8NgtcMdhH";//你的accessKeyId,参考本文档步骤2
//            final String accessKeySecret = "0MmwRQWmU1SBSTMvkWE9nJPAN39v8J";//你的accessKeySecret，参考本文档步骤2
            final String accessKeyId = "LTAICKdKW8U8JCrJ";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "FmmOb6UfBFeFE1iIHSceo3cQmcXxNv";//你的accessKeySecret，参考本文档步骤2   金石
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("金石停车");
            //必填:短信模板-可在短信控制台中找到
//            request.setTemplateCode("SMS_175485127");SMS_175490140
            request.setTemplateCode("SMS_185842994");
            //可选:模板中的变量替换JSON串
            request.setTemplateParam("{'parkName':'"+parkName+"','tHandle':'" + thandle + "','dateTime':'" + datatime + "'}");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                map.put("code", "SUCCESS");
                map.put("msg", "短信发送成功");
                return map;
            }
        }else {
            map.put("code","FAIL");
            map.put("msg","输入的手机号不合法");
            return map;
        }
        map.put("code","FAIL");
        map.put("msg","短信发送失败");
        return map;
    }
}
