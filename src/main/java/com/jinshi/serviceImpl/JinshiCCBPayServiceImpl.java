package com.jinshi.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.bankPay.*;
import com.jinshi.entity.JinshiCCBPay;
import com.jinshi.mapper.JinshiCCBPayMapper;
import com.jinshi.service.JinshiCCBPayService;
import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JinshiCCBPayServiceImpl implements JinshiCCBPayService {
    private static Logger logger = Logger.getLogger(JinshiCCBPayServiceImpl.class.getName());

    @Value("${ccb.MERCHANTID}")
    private String MERCHANTID;  //商户代码,固定写死的,需要申请
    //@Value("${ccb.POSID}")  //这里用yml提取出来就会报错..
    private String POSID = "041287924";           //商户柜台代码,固定写死的,需要申请
    @Value("${ccb.BRANCHID}")
    private String BRANCHID;  //分行代码,固定写死的,需要申请
    @Value("${ccb.PUB32TR2}")
    private String PUB32TR2; //公钥后30位
    private String BEGORDERID = "";
    private String ENDORDERID = "";
//    private String QUPWD = "fancy1589";
    private String QUPWD = "luan12345678"; //商户平台的登陆密码

    //交易码 这个参数的值是固定的，不可以修改
    private String TXCODE = "410408";
    /*必输项
    1页面形式
    2文件返回形式 (提供TXT和XML格式文件的下载)
    3 XML页面形式*/
    private String SEL_TYPE = "3";
    //预留字段，现值为空，但CHANNEL元素必须有。
    private String CHANNEL = "";
    //操作员 OPERATOR，非必输项。OPERATOR元素必须有,但值可为空。主管查询的时候为空。
    private String OPERATOR = "";

    @Autowired
    private JinshiCCBPayMapper jinshiCCBPayMapper;

    @Override
    public JSONObject CCBPay(JSONObject jsonObject) {
        logger.info("CCBPay -- 进入支付，携带参数：" + jsonObject.toJSONString());
        String CURCODE = "01";     //付款币种,固定写01 代表支付金额
        String TXCODE = "530550";  //由建行统一分配为530550
        String REMARK1 = "";
        String REMARK2 = "";
        String RETURNTYPE = "3"; // 返回类型,固定参数是3  ,代表是返回带url的支付信息
        String TIMEOUT = String.valueOf(jsonObject.get("TIMEOUT"));
        String ORDERID = String.valueOf(jsonObject.get("orderId"));    //订单号  由商户提供，最长40位,不能重复
        String PAYMENT = String.valueOf(jsonObject.get("payment"));//支付金额,测试的时候先写0.01 ,生产环境就按实际金额来算了.
        StringBuffer tmp = new StringBuffer();
        tmp.append("MERCHANTID=");
        tmp.append(MERCHANTID);
        tmp.append("&POSID=");
        tmp.append(POSID);
        tmp.append("&BRANCHID=");
        tmp.append(BRANCHID);
        tmp.append("&ORDERID=");
        tmp.append(ORDERID);
        tmp.append("&PAYMENT=");
        tmp.append(PAYMENT);
        tmp.append("&CURCODE=");
        tmp.append(CURCODE);
        tmp.append("&TXCODE=");
        tmp.append(TXCODE);
        tmp.append("&REMARK1=");
        tmp.append(REMARK1);
        tmp.append("&REMARK2=");
        tmp.append(REMARK2);
        tmp.append("&RETURNTYPE=");
        tmp.append(RETURNTYPE);
        tmp.append("&TIMEOUT=");
        tmp.append(TIMEOUT);
        tmp.append("&PUB=");
        tmp.append(PUB32TR2);
        Map map = new HashMap();
        map.put("CCB_IBSVersion", "V6");
        map.put("MERCHANTID", MERCHANTID);
        map.put("BRANCHID", BRANCHID);
        map.put("POSID", POSID);
        map.put("ORDERID", ORDERID);
        map.put("PAYMENT", PAYMENT);
        map.put("CURCODE", CURCODE);
        map.put("TXCODE", TXCODE);
        map.put("REMARK1", REMARK1);
        map.put("REMARK2", REMARK2);
        map.put("RETURNTYPE", RETURNTYPE);
        map.put("TIMEOUT", TIMEOUT);
        map.put("MAC", MD5.md5Str(tmp.toString()));
        // 这个url是建设银行指定的,尽量不要换
        String ret = HttpClientUtil.httpPost("https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain?CCB_IBSVersion=V6", map);
        logger.info("\n"+"ret:  " + ret);
        QrURLDemo qrURLDemo = JSONObject.parseObject(ret, QrURLDemo.class);
        logger.info("\n"+"qrURLDemo:  " + JSONObject.toJSON(qrURLDemo));
        // 这个url触发get请求会获取到一个新的页面
        String s = HttpClientUtil.httpGet(qrURLDemo.getPAYURL(), "UTF-8");

        //获取QRURL
        QrURLDemo qrURLDemo1 = JSONObject.parseObject(s, QrURLDemo.class);
        logger.info("\n"+"qrURLDemo:  " + JSONObject.toJSON(qrURLDemo1));
        String decode = URLDecoder.decode(qrURLDemo1.getQRURL());
        logger.info("\n"+"decode:  " + decode);
        String code = qrURLDemo1.getSUCCESS();
        logger.info("\n"+"code:  " + code);
        // 安卓通过这个url就可以支付了
        JSONObject json = new JSONObject();
        json.put("decode",decode);
        json.put("code",code);
        return json;
    }

    @Override
    public Integer insert(JinshiCCBPay jinshiCCBPay) {
        return jinshiCCBPayMapper.insertSelective(jinshiCCBPay);
    }

    @Override
    public Integer updateCarOut(JinshiCCBPay jinshiCCBPay) {
        return jinshiCCBPayMapper.updateCarOut(jinshiCCBPay);
    }

    @Override
    public List<JinshiCCBPay> selectCCBPayByPlateAndParkId(String carNumber, Integer parkId) {
        return jinshiCCBPayMapper.selectCCBPayByPlateAndParkId(carNumber,parkId);
    }

    @Override
    public JinshiCCBPay checkOrderRent(String carNumber, Integer parkId, String ccbThandle) {
        return jinshiCCBPayMapper.checkOrderRent(carNumber,parkId,ccbThandle);
    }

    @Override
    public Map<String, Object> selectByOrderId(String orderId) {
        logger.info("selectByOrderId -- 进入根据订单号查询是否支付成功，携带参数orderId：" + orderId);
        //定单号  如果有了定单号,下面的 ORDERDATE BEGORDERTIME ENDORDERTIME 就无效了..所以就置空了.
        String ORDERID = orderId;
        String ORDERDATE = "20200114";  // 因为有ORDERID,所以这个字段无效了,但是不能删
        String BEGORDERTIME = "00:00:00";// 因为有ORDERID,所以这个字段无效了,但是不能删
        String ENDORDERTIME = "23:59:59";// 因为有ORDERID,所以这个字段无效了,但是不能删
        //txcode=410408
		/* 流程类型
		必输项
		0支付流水
		1退款流水*/
        String TYPE = "0";
		/*必输项（当日只有未结算流水可供查询）
		0 未结算流水
		1 已结算流水*/
        String KIND = "1";
		/*必输项
		0失败
		1成功
		2不确定
		3全部（已结算流水查询不支持全部）*/
        String STATUS = "1";
        //页码必输项，输入将要查询的页码。
        String PAGE = "1";
        String xmlString = this.getStringByHttpClient(ORDERDATE, BEGORDERTIME, ENDORDERTIME, ORDERID, TYPE, KIND, STATUS, PAGE);
        logger.info("xmlString:  " + xmlString);
        Document document = (Document) this.getDocumentByXMLStr(xmlString);
        logger.info("document:  " + JSONObject.toJSON(document));
        List<QUERYORDER> queryorders = document.getQUERYORDER();
        Map<String, Object> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(queryorders)) { // 如果没有查询到定单就是空的
            QUERYORDER queryOrder = queryorders.get(0); //因为只有一个结果,所以就获取索引为0的元素
            resultMap.put("queryOrder", queryOrder);
            logger.info("queryOrder:  " + JSONObject.toJSON(queryOrder));
        }
        //不管查询成功查询失败都会有这个消息
        resultMap.put("returnCode", document.getRETURN_CODE());
        resultMap.put("returnMsg", document.getRETURN_MSG());
        logger.info("订单号：" + orderId +  "查询后 returnCode:  " + document.getRETURN_CODE());
        logger.info("订单号：" + orderId +  "查询后 returnMsg:  " + document.getRETURN_MSG());
        return resultMap;
    }
    /**
     * 发送请求获取 String格式的字符串
     *
     * @param ORDERDATE
     * @param BEGORDERTIME
     * @param ENDORDERTIME
     * @param ORDERID
     * @param TYPE
     * @param KIND
     * @param STATUS
     * @param PAGE
     * @return string 格式的xml
     */
    private String getStringByHttpClient(Object ORDERDATE, Object BEGORDERTIME, Object ENDORDERTIME, String ORDERID, String TYPE, String KIND, String STATUS, String PAGE) {
        String param = "MERCHANTID=" + MERCHANTID + "&BRANCHID=" + BRANCHID + "&POSID=" + POSID + "&ORDERDATE=" + ORDERDATE + "&BEGORDERTIME=" + BEGORDERTIME
                + "&ENDORDERTIME=" + ENDORDERTIME + "&BEGORDERID=" + BEGORDERID + "&ENDORDERID=" + ENDORDERID + "&QUPWD=&TXCODE=" + TXCODE
                + "&SEL_TYPE=" + SEL_TYPE + "&OPERATOR=" + OPERATOR;
        if ("410408".equals(TXCODE)) {
            param = "MERCHANTID=" + MERCHANTID + "&BRANCHID=" + BRANCHID + "&POSID=" + POSID + "&ORDERDATE="
                    + ORDERDATE + "&BEGORDERTIME=" + BEGORDERTIME + "&ENDORDERTIME=" + ENDORDERTIME + "&ORDERID="
                    + ORDERID + "&QUPWD=&TXCODE=" + TXCODE + "&TYPE=" + TYPE + "&KIND=" + KIND + "&STATUS=" + STATUS +
                    "&SEL_TYPE=" + SEL_TYPE + "&PAGE=" + PAGE + "&OPERATOR=" + OPERATOR + "&CHANNEL=" + CHANNEL;
        }
        Map map = new HashMap();
        map.put("MERCHANTID", MERCHANTID);
        map.put("BRANCHID", BRANCHID);
        map.put("POSID", POSID);
        map.put("ORDERDATE", ORDERDATE);
        map.put("BEGORDERTIME", BEGORDERTIME);
        map.put("ENDORDERTIME", ENDORDERTIME);
        map.put("BEGORDERID", BEGORDERID);
        map.put("ENDORDERID", ENDORDERID);
        map.put("QUPWD", QUPWD);
        map.put("TXCODE", TXCODE);
        if ("410408".equals(TXCODE)) {
            map.put("TYPE", TYPE);
            map.put("KIND", KIND);
            map.put("STATUS", STATUS);
            map.put("ORDERID", ORDERID);
            map.put("PAGE", PAGE);
            map.put("CHANNEL", CHANNEL);
        }
        map.put("SEL_TYPE", SEL_TYPE);
        map.put("OPERATOR", OPERATOR);
        map.put("MAC", MD5.md5Str(param));
        // 调用银行的接口 基本是固定的地址
        String s = HttpClientUtil.httpPost("https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain?", map);
        //删除字符串防止解析xml报错
        s = s.replaceAll("\\n", "").
                replaceAll("\\t", "").
                replaceAll("\\r", "");
        return s;
    }

    /**
     * 从 string 格式的xml 里面提出取出 Document 实体类
     *
     * @param ret string 格式的xml
     * @return
     */
    private Object getDocumentByXMLStr(String ret) {
        XStream xStream = new XStream();
        xStream.alias("DOCUMENT", Document.class);
        xStream.processAnnotations(Document.class);
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypesByWildcard(
                new String[]{"com.jinshi.**"}
        );
        return xStream.fromXML(ret);
    }

    @Override
    public JinshiCCBPay selectOrder(String orderId) {
        return jinshiCCBPayMapper.selectOrder(orderId);
    }

    @Override
    public Integer updateOrderId(JinshiCCBPay jinshiCCBPay) {
        return jinshiCCBPayMapper.updateOrderId(jinshiCCBPay);
    }

    @Override
    public List<JinshiCCBPay> selectPlate(String car_number) {
        return jinshiCCBPayMapper.selectPlate(car_number);
    }
}
