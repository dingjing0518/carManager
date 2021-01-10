package com.jinshi.wxPay;

public class WxConst {

//    //微信小程序appid
//    public static String appId = "wxd440bee620002bdc";
//    //微信小程序appsecret
//    public static String appSecret = "855a1757db22d24419cd14363fa5ff09";
    //公司服务号
//    public static String appId = "wxa93aeac9e8d90a60";
//    public static String appSecret = "51e6b653a0b5267f580afc3dee52c184";

    //金石客户wx568c587ae29d2733
    public static String appId = "wx568c587ae29d2733";
    public static String appSecret = "786a3488d271f75b16ea847dd4150b75";

    //微信支付主体
    public static String title = "停车场支付";
    //订单号随机生成
    public static String orderNo = PayUtils.order();
    //微信商户号
    public static String mch_id="1546856631";
    //微信支付的商户密钥
    public static final String key = "Fancynetworkwangluokeji888888888";
    //支付成功后的服务器回调url
    public static final String notify_url="https://api.weixin.qq.com/sns/jscode2session";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

}
