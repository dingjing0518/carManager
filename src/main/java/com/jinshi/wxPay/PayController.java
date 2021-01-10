package com.jinshi.wxPay;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.jinshi.entity.JinshiParking;
import com.jinshi.entity.JinshiWxcar;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.WxPayOrder;
import com.jinshi.service.JinshiParkingService;
import com.jinshi.service.JinshiWxcarService;
import com.jinshi.service.LincensePlateService;
import com.jinshi.service.WxPayOrderService;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.HttpClient;
import com.jinshi.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/wxPay")
@CrossOrigin
public class PayController {

    @Autowired
    private WxPayOrderService  wxPayOrderService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LincensePlateService lincensePlateService;

    @Autowired
    private JinshiWxcarService jinshiWxcarService;

    @Autowired
    private JinshiParkingService jinshiParkingService;

    private static Logger logger = Logger.getLogger(String.valueOf(PayController.class));

    @GetMapping("/wxPayRequest")
    @ResponseBody
    @CrossOrigin
    public JSONObject wxPayRequest(String openid, String money, HttpServletRequest request){

        logger.info("-------------------------进入支付-------------------------");
        JSONObject json = new JSONObject();
        try{
            //生成的随机字符串
            String nonce_str = WXPayUtil.generateNonceStr();
            //商品名称
//            String body = new String(WxConst.title.getBytes("ISO-8859-1"),"UTF-8");
            String body = WxConst.title;
            //获取本机的ip地址
            String spbill_create_ip = PayUtils.getIpAddr(request);
            //获取随机订单号
            String orderNo =String.valueOf(idWorker.nextId());
            //支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            String totalFee = Integer.valueOf((Integer.parseInt(money)*100)).toString();

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxConst.appId);
            packageParams.put("mch_id", WxConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);
            packageParams.put("total_fee", money);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxConst.notify_url);
            packageParams.put("trade_type", WxConst.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtils.paraFilter(packageParams);
            // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String prestr = PayUtils.createLinkString(packageParams);

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtils.sign(prestr, WxConst.key, "utf-8").toUpperCase();
            logger.info("=========第一次签名：" + mysign + "=========");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='UTF-8'>" + "<appid>" + WxConst.appId + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxConst.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxConst.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxConst.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtils.httpRequest(WxConst.pay_url, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtils.doXMLParse(result);

            //返回状态码
            String return_code = (String) map.get("return_code");

            //返回给移动端需要的参数
            Map<String, Object> responseMap = new HashMap<String, Object>();
            if("SUCCESS".equals(return_code)){
                // 业务结果
                //返回的预付单信息
                String prepay_id = (String) map.get("prepay_id");
                responseMap.put("nonceStr", nonce_str);
                responseMap.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                responseMap.put("timeStamp", timeStamp + "");

                String stringSignTemp = "appId=" + WxConst.appId +"&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WxConst.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtils.sign(stringSignTemp, WxConst.key, "utf-8").toUpperCase();
                logger.info("=========第二次签名：" + paySign + "========");
                responseMap.put("paySign", paySign);
            }
            responseMap.put("out_trade_no",orderNo);
            responseMap.put("appId", WxConst.appId);
            json.put("errMsg", "OK");
            json.put("data", responseMap);
        }catch(Exception e){
            e.printStackTrace();
            json.put("errMsg", "Failed");
        }
        return json;
    }

    /**
     * 支付成功页面回调
     *
     * @return
     */
    @RequestMapping(value = "/paynotify",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> paynotify(@RequestParam(value="out_trade_no")String out_trade_no,
                                         @RequestParam(value="serviceType")String serviceType,
                                         @RequestParam(value="openid")String openid,
                                         @RequestParam(value="carType")String carType,
                                         @RequestParam(value="ownerName")String ownerName,
                                         @RequestParam(value="carNumber")String carNumber,
                                         @RequestParam(value="phone")String phone) {

        logger.info("订单号: " + out_trade_no + "-----------" + "会员类型: " + serviceType);

        String url = "https://api.mch.weixin.qq.com/pay/orderquery";

        LincensePlate lincensePlate = new LincensePlate();
        JinshiWxcar jinshiWxcar = new JinshiWxcar();
        HttpClient httpClient = new HttpClient(url);
        httpClient.setHttps(true);
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("appid", WxConst.appId);
        hashMap.put("mch_id", WxConst.mch_id);
        hashMap.put("nonce_str", WXPayUtil.generateNonceStr());
        hashMap.put("out_trade_no", out_trade_no);

//        String serviceType = (String) cardInfo.get("serviceType");

        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            String signature = WXPayUtil.generateSignature(hashMap, WxConst.key);
            hashMap.put("sign", signature);

            String mapToXml = WXPayUtil.mapToXml(hashMap);
            httpClient.setXmlParam(mapToXml);
            httpClient.post();

            String content = httpClient.getContent();

            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(content);
            if ("SUCCESS".equals(xmlToMap.get("return_code"))) {
                if ("SUCCESS".equals(xmlToMap.get("result_code"))) {
                    if ("SUCCESS".equals(xmlToMap.get("trade_state"))) {

                        String userCarNumber = lincensePlateService.selectByPlate(carNumber);
                        JinshiWxcar jinshiWxcar1 = jinshiWxcarService.selectByCarNumber(carNumber);
                        //判断是否添加过此车牌
                        if (null == userCarNumber) {
                            //车辆表添加记录
                            lincensePlate.setLpMemberIdCar(ownerName);
                            lincensePlate.setLpLincensePlateIdCar(carNumber);
                            lincensePlate.setLpInboundTime(new Date());
                            lincensePlate.setLpServiceTypeCar(serviceType);
                            lincensePlate.setLpCarType(carType);
                            Integer parkId = GlobalVariable.parkId;
                            JinshiParking jinshiParking = jinshiParkingService.selectByJpId(parkId);
                            lincensePlate.setLpParkingName(jinshiParking.getJpName());
                            lincensePlateService.insertWxUser(lincensePlate);

                            //wxcar添加记录
                            jinshiWxcar.setJwOpenid(xmlToMap.get("openid"));
                            jinshiWxcar.setJwName(ownerName);
                            jinshiWxcar.setJwCarType(carType);
                            jinshiWxcar.setJwCarNumber(carNumber);
                            jinshiWxcar.setJwPhone(phone);
                            jinshiWxcar.setJwServiceName(serviceType);

                            Date date = new Date();
                            Calendar rightNow = Calendar.getInstance();
                            rightNow.setTime(date);
                            jinshiWxcar.setJwCreateTime(date);
                            if ("年卡".equals(serviceType)) {
                                //日期加1年
                                rightNow.add(Calendar.YEAR,1);
                                Date date1=rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else if ("季卡".equals(serviceType)) {
                                //日期加3个月
                                rightNow.add(Calendar.MONTH,3);
                                Date date1=rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else if ("月卡".equals(serviceType)) {
                                //日期加1个月
                                rightNow.add(Calendar.MONTH, 1);
                                Date date1 = rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else {
                                jinshiWxcar.setJwExpirationTime(date);
                            }
                            jinshiWxcarService.insertWxUser(jinshiWxcar);
                        } else if (null != userCarNumber  ) {
                            //如果数据库有此车牌记录，则为此车牌续期买卡
                            Calendar rightNow = Calendar.getInstance();
                            rightNow.setTime(jinshiWxcar1.getJwExpirationTime());
                            if ("年卡".equals(serviceType)) {
                                //日期加1年
                                rightNow.add(Calendar.YEAR,1);
                                Date date1=rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else if ("季卡".equals(serviceType)) {
                                //日期加3个月
                                rightNow.add(Calendar.MONTH,3);
                                Date date1=rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else if ("月卡".equals(serviceType)) {
                                //日期加1个月
                                rightNow.add(Calendar.MONTH, 1);
                                Date date1 = rightNow.getTime();
                                jinshiWxcar.setJwExpirationTime(date1);
                            } else {
                                jinshiWxcar.setJwExpirationTime(new Date());
                            }
                            jinshiWxcarService.updateByOpenid(jinshiWxcar1);
                        }

                        //生成微信订单信息
                        WxPayOrder wxPayOrder = new WxPayOrder();
                        wxPayOrder.setOutTradeNo(out_trade_no);
                        wxPayOrder.setTotalFee(xmlToMap.get("total_fee"));
                        wxPayOrder.setOpenId(xmlToMap.get("openid"));
                        wxPayOrder.setCreatTime(new Date());
                        wxPayOrder.setType(serviceType);
                        wxPayOrderService.insert(wxPayOrder);

                        linkedHashMap.put("code", "SUCCESS");
                        linkedHashMap.put("successmsg", "成功信息111");
                        return linkedHashMap;
                    }
                }
            } else {
                linkedHashMap.put("code", "FAIL");
                linkedHashMap.put("errmsg", "erro错误信息111");
                return linkedHashMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
            linkedHashMap.put("code", "FAIL");
            linkedHashMap.put("errmsg", "erro错误信息222");
            return linkedHashMap;
        }
        return null;
    }
}
