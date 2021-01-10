package com.jinshi.bankPay;


import com.jinshi.util.QianYiCameraUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 建行httpclient工具类
 *
 */
public class HttpClientUtil {
	
	private static Logger logger = Logger.getLogger(HttpClientUtil.class.getName());

	public static String httpReader(String url, String code){
		logger.info("GetPage:"+url);
		logger.info("GetPage:"+url);
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		
		String result = null;
		try {
			client.executeMethod(method);
			int status = method.getStatusCode();
			if (status == HttpStatus.SC_OK) {
				result = method.getResponseBodyAsString();
			} else {
				logger.info("Method failed: " + method.getStatusLine());
			}
		} catch (HttpException e) {
			logger.info("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("���������쳣��");
			e.printStackTrace();
		} finally{
			if(method!=null)method.releaseConnection();
			method = null;
			client = null;
		}
		return result;
	}
	
	public static String httpGet(String url,String code) {
		logger.info("GetPage:"+url);
		String content = null;
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
		GetMethod method = new GetMethod(url);
		try {
			int statusCode = httpClient.executeMethod(method);
			logger.info("httpClientUtils::statusCode="+statusCode);
			logger.info(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
			
		} catch (Exception e) {
			logger.info("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		return content;
	}
	
	public static String httpPost(String url, Map paramMap, String code) {
		logger.info("GetPage:"+url);
		String content = null;
		if (url == null || url.trim().length() == 0 || paramMap == null
				|| paramMap.isEmpty())
			return null;
		HttpClient httpClient = new HttpClient();
		//����header
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");//
		
		//��������
		//httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);
		
		PostMethod method = new PostMethod(url);
		Iterator it = paramMap.keySet().iterator();
		

		while (it.hasNext()) {
			String key = it.next() + "";
			Object o = paramMap.get(key);
			if (o != null && o instanceof String) {
				method.addParameter(new NameValuePair(key, o.toString()));
			}
			if (o != null && o instanceof String[]) {
				String[] s = (String[]) o;
				if (s != null)
					for (int i = 0; i < s.length; i++) {
						method.addParameter(new NameValuePair(key, s[i]));
					}
			}
		}
		try {
			
			int statusCode = httpClient.executeMethod(method);
			
			logger.info("httpClientUtils::statusCode="+statusCode);

			logger.info(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
			
		} catch (Exception e) {
			logger.info("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		return content;

	}

	public static String httpPost(String url, Map paramMap) {

		return HttpClientUtil.httpPost(url, paramMap, "UTF-8");
	}
}
