package com.jinshi.netCamera.utils;

import net.sdk.function.main.NET;

//程序内使用同一个句柄,已连接的句柄
public class HandleManage {
	
	private static int handle = -1;
	
	//获取句柄
	public static int gettHandle(NET net){
		//判断相机句柄是否有效
		if(handle == -1){
			return -1;
		}else{
			//判断相机连接状态
			int qcs = net.Net_QueryConnState(handle);
			if(qcs != 0){
				return -1;
			}
		}
		return handle;
		
	}
	
	//设置句柄
	public static void settHandle(int tHandle){
		handle = tHandle;
	}
}
