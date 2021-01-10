//package com.jinshi.util;
//
//import java.io.*;
//import java.net.ConnectException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.security.*;
//import java.security.spec.InvalidParameterSpecException;
//import java.util.*;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.github.wxpay.sdk.WXPayConstants;
//import com.jinshi.wx.MyX509TrustManager;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.codehaus.xfire.util.Base64;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.input.SAXBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.jinshi.entity.Token;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import static com.github.wxpay.sdk.WXPayUtil.HMACSHA256;
//
//
//public class WxPayUtils {
//
//    private static Logger log = LoggerFactory.getLogger(WxPayUtils.class);
//
//    // 凭证获取（GET）
//    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd440bee620002bdc&secret=855a1757db22d24419cd14363fa5ff09";
//
//    /**
//     * 发送https请求
//     *
//     * @param requestUrl 请求地址
//     * @param requestMethod 请求方式（GET、POST）
//     * @param outputStr 提交的数据
//     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
//     */
//    /*public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
//        JSONObject jsonObject = null;
//        try {
//            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            TrustManager[] tm = { new MyX509TrustManager()};
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//            URL url = new URL(requestUrl);
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setSSLSocketFactory(ssf);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            conn.setRequestMethod(requestMethod);
//            // 当outputStr不为null时向输出流写数据
//            if (null != outputStr) {
//                OutputStream outputStream = conn.getOutputStream();
//                // 注意编码格式
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//            // 从输入流读取返回内容
//            InputStream inputStream = conn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String str = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            // 释放资源
//            bufferedReader.close();
//            inputStreamReader.close();
//            inputStream.close();
//            inputStream = null;
//            conn.disconnect();
//            jsonObject = JSONObject.parseObject(buffer.toString());
//        } catch (ConnectException ce) {
//            log.error("连接超时：{}", ce);
//        } catch (Exception e) {
//            log.error("https请求异常：{}", e);
//        }
//        return jsonObject;
//    }*/
//    /**
//     * 获取接口访问凭证
//     *
//     * @param appid 凭证
//     * @param appsecret 密钥
//     * @return
//     */
//    /*public static Token getToken(String appid, String appsecret) {
//        Token token = null;
//        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
//        // 发起GET请求获取凭证
//        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
//        if (null != jsonObject) {
//            try {
//                token = new Token();
//                token.setAccessToken(jsonObject.getString("access_token"));
//                token.setExpiresIn(jsonObject.getInteger("expires_in"));
//            } catch (JSONException e) {
//                token = null;
//                // 获取token失败
//                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
//            }
//        }
//        return token;
//    }*/
//    /**
//     * URL编码（utf-8）
//     *
//     * @param source
//     * @return
//     */
//    /*public static String urlEncodeUTF8(String source) {
//        String result = source;
//        try {
//            result = java.net.URLEncoder.encode(source, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }*/
//    /**
//     * 根据内容类型判断文件扩展名
//     *
//     * @param contentType 内容类型
//     * @return
//     */
//    /*public static String getFileExt(String contentType) {
//        String fileExt = "";
//        if ("image/jpeg".equals(contentType))
//            fileExt = ".jpg";
//        else if ("audio/mpeg".equals(contentType))
//            fileExt = ".mp3";
//        else if ("audio/amr".equals(contentType))
//            fileExt = ".amr";
//        else if ("video/mp4".equals(contentType))
//            fileExt = ".mp4";
//        else if ("video/mpeg4".equals(contentType))
//            fileExt = ".mp4";
//        return fileExt;
//    }*/
//
//    /**
//     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
//     * @param params 需要排序并参与字符拼接的参数组
//     * @return 拼接后字符串
//     */
//    public static String createLinkString(Map<String, String> params) {
//        List<String> keys = new ArrayList<String>(params.keySet());
//        Collections.sort(keys);
//        String prestr = "";
//        for (int i = 0; i < keys.size(); i++) {
//            String key = keys.get(i);
//            String value = params.get(key);
//            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
//                prestr = prestr + key + "=" + value;
//            } else {
//                prestr = prestr + key + "=" + value + "&";
//            }
//        }
//        return prestr;
//    }
//
//
//    /**
//     * 签名字符串
//     * @param text 需要签名的字符串
//     * @param key 密钥
//     * @param input_charset 编码格式
//     * @return 签名结果
//     */
//    public static String sign(String text, String key, String input_charset) {
//        text = text + "&key=" + key;
//        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
//    }
//    /**
//     * @param content
//     * @param charset
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public static byte[] getContentBytes(String content, String charset) {
//        if (charset == null || "".equals(charset)) {
//            return content.getBytes();
//        }
//        try {
//            return content.getBytes(charset);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
//        }
//    }
//
//
//    /**
//     *  发送http请求
//     * @param requestUrl 请求地址
//     * @param requestMethod 请求方法
//     * @param outputStr 参数
//     */
//    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
//        // 创建SSLContext
//        StringBuffer buffer = null;
//        try{
//            URL url = new URL(requestUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod(requestMethod);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.connect();
//            //往服务器端写内容
//            if(null !=outputStr){
//                OutputStream os=conn.getOutputStream();
//                os.write(outputStr.getBytes("utf-8"));
//                os.close();
//            }
//            // 读取服务器端返回的内容
//            InputStream is = conn.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is, "utf-8");
//            BufferedReader br = new BufferedReader(isr);
//            buffer = new StringBuffer();
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                buffer.append(line);
//            }
//            br.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return buffer.toString();
//    }
//
//
//    /**
//     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
//     * @param strxml
//     * @return
//     * @throws IOException
//     */
//    public static Map doXMLParse(String strxml) throws Exception {
//        if(null == strxml || "".equals(strxml)) {
//            return null;
//        }
//
//        Map m = new HashMap();
//        InputStream in = String2Inputstream(strxml);
//        SAXBuilder builder = new SAXBuilder();
//        Document doc = builder.build(in);
//        Element root = doc.getRootElement();
//        List list = root.getChildren();
//        Iterator it = list.iterator();
//        while(it.hasNext()) {
//            Element e = (Element) it.next();
//            String k = e.getName();
//            String v = "";
//            List children = e.getChildren();
//            if(children.isEmpty()) {
//                v = e.getTextNormalize();
//            } else {
//                v = getChildrenText(children);
//            }
//
//            m.put(k, v);
//        }
//
//        //关闭流
//        in.close();
//
//        return m;
//    }
//
//    /**
//     * 获取子结点的xml
//     * @param children
//     * @return String
//     */
//    public static String getChildrenText(List children) {
//        StringBuffer sb = new StringBuffer();
//        if(!children.isEmpty()) {
//            Iterator it = children.iterator();
//            while(it.hasNext()) {
//                Element e = (Element) it.next();
//                String name = e.getName();
//                String value = e.getTextNormalize();
//                List list = e.getChildren();
//                sb.append("<" + name + ">");
//                if(!list.isEmpty()) {
//                    sb.append(getChildrenText(list));
//                }
//                sb.append(value);
//                sb.append("</" + name + ">");
//            }
//        }
//
//        return sb.toString();
//    }
//    public static InputStream String2Inputstream(String str) {
//        return new ByteArrayInputStream(str.getBytes());
//    }
//
//
//    /**
//     * 除去数组中的空值和签名参数
//     * @param sArray 签名参数组
//     * @return 去掉空值与签名参数后的新签名参数组
//     */
//    /*public static Map<String, String> paraFilter(Map<String, String> sArray) {
//        Map<String, String> result = new HashMap<String, String>();
//        if (sArray == null || sArray.size() <= 0) {
//            return result;
//        }
//        for (String key : sArray.keySet()) {
//            String value = sArray.get(key);
//            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
//                    || key.equalsIgnoreCase("sign_type")) {
//                continue;
//            }
//            result.put(key, value);
//        }
//        return result;
//    }*/
//
//
//    /**
//     * 微信回调参数解析
//     *
//     * @param request
//     * @return
//     */
//    public static String getPostStr(HttpServletRequest request) {
//        StringBuffer sb = new StringBuffer();
//        try {
//            InputStream is = request.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//            BufferedReader br = new BufferedReader(isr);
//            String s = "";
//
//            while ((s = br.readLine()) != null) {
//                sb.append(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String xml = sb.toString(); //即为接收到微信端发送过来的xml数据
//        return xml;
//    }
//
//    /**
//     * XML格式字符串转换为Map
//     *
//     * @param strXML XML字符串
//     * @return XML数据转换后的Map
//     * @throws Exception
//     */
//    public static Map<String, String> xmlToMap(String strXML) throws Exception {
//        Map<String, String> data = new HashMap<String, String>();
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
//        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
//        org.w3c.dom.Document doc = documentBuilder.parse(stream);
//        doc.getDocumentElement().normalize();
//        NodeList nodeList = doc.getDocumentElement().getChildNodes();
//        for (int idx=0; idx<nodeList.getLength(); ++idx) {
//            Node node = nodeList.item(idx);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
//                data.put(element.getNodeName(), element.getTextContent());
//            }
//        }
//        try {
//            stream.close();
//        }
//        catch (Exception ex) {
//
//        }
//        return data;
//    }
//
//    /**
//     * 将Map转换为XML格式的字符串
//     *
//     * @param data Map类型数据
//     * @return XML格式的字符串
//     * @throws Exception
//     */
//    public static String mapToXml(Map<String, String> data) throws Exception {
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
//        org.w3c.dom.Document document = documentBuilder.newDocument();
//        org.w3c.dom.Element root = document.createElement("xml");
//        document.appendChild(root);
//        for (String key: data.keySet()) {
//            String value = data.get(key);
//            if (value == null) {
//                value = "";
//            }
//            value = value.trim();
//            org.w3c.dom.Element filed = document.createElement(key);
//            filed.appendChild(document.createTextNode(value));
//            root.appendChild(filed);
//        }
//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer transformer = tf.newTransformer();
//        DOMSource source = new DOMSource(document);
//        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        StringWriter writer = new StringWriter();
//        StreamResult result = new StreamResult(writer);
//        transformer.transform(source, result);
//        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
//        try {
//            writer.close();
//        }
//        catch (Exception ex) {
//        }
//        return output;
//    }
//
//    /**
//     * 生成签名
//     *
//     * @param data 待签名数据
//     * @param key API密钥
//     * @return 签名
//     */
//    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
//        return generateSignature(data, key, WXPayConstants.SignType.MD5);
//    }
//
//    /**
//     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
//     *
//     * @param data 待签名数据
//     * @param key API密钥
//     * @param signType 签名方式
//     * @return 签名
//     */
//    public static String generateSignature(final Map<String, String> data, String key, WXPayConstants.SignType signType) throws Exception {
//        Set<String> keySet = data.keySet();
//        String[] keyArray = keySet.toArray(new String[keySet.size()]);
//        Arrays.sort(keyArray);
//        StringBuilder sb = new StringBuilder();
//        for (String k : keyArray) {
//            if (k.equals(WXPayConstants.FIELD_SIGN)) {
//                continue;
//            }
//            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//                sb.append(k).append("=").append(data.get(k).trim()).append("&");
//        }
//        sb.append("key=").append(key);
//        if (WXPayConstants.SignType.MD5.equals(signType)) {
//            return MD5(sb.toString()).toUpperCase();
//        }
//        else if (WXPayConstants.SignType.HMACSHA256.equals(signType)) {
//            return HMACSHA256(sb.toString(), key);
//        }
//        else {
//            throw new Exception(String.format("Invalid sign_type: %s", signType));
//        }
//    }
//
//    /**
//     * 生成 MD5
//     *
//     * @param data 待处理数据
//     * @return MD5结果
//     */
//    public static String MD5(String data) throws Exception {
//        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] array = md.digest(data.getBytes("UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        for (byte item : array) {
//            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//        }
//        return sb.toString().toUpperCase();
//    }
//
//    /**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url 发送请求的 URL
//     * @return 所代表远程资源的响应结果
//     */
//    public static String sendPost(String url, Map<String, ?> paramMap) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//
//        String param = "";
//        Iterator<String> it = paramMap.keySet().iterator();
//
//        while(it.hasNext()) {
//            String key = it.next();
//            param += key + "=" + paramMap.get(key) + "&";
//        }
//
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * 向指定URL发送GET方法的请求
//     *
//     * @param url
//     *            发送请求的URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return URL 所代表远程资源的响应结果
//     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * AES解密
//     *
//     * @param encryptedData  包括敏感数据在内的完整用户信息的加密数据，
//     * @param key    秘钥
//     * @param iv     加密算法的初始向量，
//     * @param encodingFormat 解密后的结果需要进行的编码
//     * @return String
//     * @see  Exception
//     */
//    public static String decrypt(String encryptedData,String key, String iv, String encodingFormat) throws Exception {
//
//        //被加密的数据
//        byte[] dataByte = org.codehaus.xfire.util.Base64.decode(encryptedData);
//        //加密秘钥
//        byte[] keyByte = org.codehaus.xfire.util.Base64.decode(key);
//        //偏移量
//        byte[] ivByte = Base64.decode(iv);
//
//
//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//
//            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//
//            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//            parameters.init(new IvParameterSpec(ivByte));
//            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
//            byte[] resultByte = cipher.doFinal(dataByte);
//            if (null != resultByte && resultByte.length > 0) {
//                String result = new String(resultByte, encodingFormat);
//                return result;
//            }
//            return null;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidParameterSpecException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
