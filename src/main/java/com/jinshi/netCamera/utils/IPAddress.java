package com.jinshi.netCamera.utils;

public class IPAddress {
	//保存选择的ip地址以及其余信息
	public static String IP = "192.168.0.92";
	public static int DEVICE_PORT = 80;
	public static String NAME = "admin";
	public static String PASSWORD = "admin";
	public static boolean isFirst = true;
	public static int TCP_PORT = 9501;
	
	public static String TCP_IP = "192.168.0.92";
	
	public static void setIPAddress(String IP,int DEVICE_PORT,String NAME,String PASSWORD){
		if(!IPAddress.IP.equals(IP)){
			
			IPAddress.IP = IP;
			IPAddress.DEVICE_PORT = DEVICE_PORT;
			IPAddress.NAME = NAME;
			IPAddress.PASSWORD = PASSWORD;
			IPAddress.isFirst = true;
		}else{
			IPAddress.isFirst = false;
		}
	}
}
