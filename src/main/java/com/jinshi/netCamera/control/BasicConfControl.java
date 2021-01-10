package com.jinshi.netCamera.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sdk.bean.basicconfig.devsetup.Data_T_DevSetup.T_DevSetup;
import net.sdk.bean.basicconfig.imagesetup.Data_T_ImageEv.T_ImageEv;
import net.sdk.bean.basicconfig.imagesetup.Data_T_ImageWDR.T_ImageWDR;
import net.sdk.bean.basicconfig.netsetup.Data_T_MACSetup.T_MACSetup;
import net.sdk.bean.basicconfig.netsetup.Data_T_NetSetup.T_NetSetup;
import net.sdk.bean.basicconfig.portsetup.Data_T_MulticastSetup.T_MulticastSetup;
import net.sdk.bean.basicconfig.portsetup.Data_T_ParkRS485Setup.T_ParkRS485Setup;
import net.sdk.bean.basicconfig.portsetup.Data_T_RS485Setup.T_RS485Setup;
import net.sdk.bean.basicconfig.timesetup.Data_T_DCTimeSetup.T_DCTimeSetup;
import net.sdk.bean.basicconfig.timesetup.Data_T_SNTPSetup.T_SNTPSetup;
import net.sdk.bean.systemconfig.importexport.Data_T_ExportConfigSetup.T_ExportConfigSetup;
import net.sdk.bean.systemconfig.status.Data_T_SysState.T_SysState;
import net.sdk.bean.systemconfig.timereboot.Data_T_TimeReboot.T_TimeReboot;
import net.sdk.function.main.NET;
import com.jinshi.netCamera.utils.ByteArrayToMAC;
import com.jinshi.netCamera.utils.HandleManage;
import com.jinshi.netCamera.utils.IPAndInt;
import com.jinshi.netCamera.utils.StatusCode;
import com.jinshi.netCamera.utils.StrimEncoding;
import com.jinshi.netCamera.view.BasicConfView;

public class BasicConfControl {
	private BasicConfView basicConfView;
	private NET net;
	private ContainerControl containerControl;
	
	public BasicConfControl(NET net,ContainerControl containerControl){
		this.net = net;
		this.containerControl = containerControl;
		basicConfView = new BasicConfView();
		
		initListener();
	}

	private void initListener() {
		
		basicConfView.getBtn_Net_ExportConfig().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable(){

					@Override
					public void run() {
						T_ExportConfigSetup.ByReference ptExportConfigSetup = new T_ExportConfigSetup.ByReference();
						ptExportConfigSetup.aucFilePath = "D:/NetConfig.ini".getBytes();
						int ec = net.Net_ExportConfig(HandleManage.gettHandle(net), ptExportConfigSetup);
						containerControl.message(StatusCode.getStatusCode(ec, "Net_ExportConfig"));
						if(ec == 0){
							containerControl.message("导出成功");
						}
						
					}
					
				}).start();
			}
		});
		
		
		basicConfView.getBtn_Net_TimeSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DCTimeSetup.ByReference ptTimeSetup = new T_DCTimeSetup.ByReference();
				ptTimeSetup.ucDay = (byte) Integer.parseInt(basicConfView.getText_day().getText());
				ptTimeSetup.ucDayFmt = (byte) (basicConfView.getCb_datefm().getSelectedIndex());
				ptTimeSetup.ucHour = (byte) Integer.parseInt(basicConfView.getText_hour().getText());
				ptTimeSetup.ucMinute = (byte) Integer.parseInt(basicConfView.getText_minute().getText());
				ptTimeSetup.ucMonth = (byte) Integer.parseInt(basicConfView.getText_month().getText());
				ptTimeSetup.ucSecond = (byte) Integer.parseInt(basicConfView.getText_second().getText());
				ptTimeSetup.ucTimeFmt = (byte) (basicConfView.getRb_timefm().isSelected()?0:1);
				ptTimeSetup.ucTimeZone = (byte) Integer.parseInt(basicConfView.getText_zone().getText());
				ptTimeSetup.usYear = (short) Integer.parseInt(basicConfView.getText_year().getText());
				
				int ts = net.Net_TimeSetup(HandleManage.gettHandle(net), ptTimeSetup);
				containerControl.message(StatusCode.getStatusCode(ts, "Net_TimeSetup"));
				
			}
		});
		
		basicConfView.getBtn_Net_QueryDevSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DevSetup.ByReference ptDevSetup = new T_DevSetup.ByReference();
				int qds = net.Net_QueryDevSetup(HandleManage.gettHandle(net), ptDevSetup);
				containerControl.message(StatusCode.getStatusCode(qds, "Net_QueryDevSetup"));
				if(qds == 0){
					String szLicenseNum = new String(ptDevSetup.szLicenseNum).trim();
					String szDistrictNumber= new String(ptDevSetup.szDistrictNumber).trim();
					String szProjectName  = new String(ptDevSetup.szProjectName).trim();
					String zDevNumber = new String(ptDevSetup.szDevNumber).trim();
					String szRoadName = new String(ptDevSetup.szRoadName).trim();
					String szRecordNum = new String(ptDevSetup.szRecordNum).trim();
					String szRoadNumber = new String(ptDevSetup.szRoadNumber).trim();
					byte ucDirection = ptDevSetup.ucDirection;
					
					containerControl.message("设备License："+szLicenseNum+"\n地区编号："+szDistrictNumber
							+"\n出入口名称:"+szRoadName+"\n项目名称:"+szProjectName+"\n设备号："+zDevNumber
							+"\n备案号："+szRecordNum+"\n出入口编号："+szRoadNumber+"\n方向："+ucDirection);
				}
				
				
			}
		});
		
		basicConfView.getBtn_Net_QueryNETSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_NetSetup.ByReference ptNetSetup = new T_NetSetup.ByReference();
				int qns = net.Net_QueryNETSetup(HandleManage.gettHandle(net), ptNetSetup);
				containerControl.message(StatusCode.getStatusCode(qns, "Net_QueryNETSetup"));
				containerControl.message("ip地址："+IPAndInt.intToIp(ptNetSetup.uiIPAddress)+"\n子网掩码："
				+IPAndInt.intToIp(ptNetSetup.uiMaskAddress)
							+"\n网关："+IPAndInt.intToIp(ptNetSetup.uiGatewayAddress)
							+"\nDNS1:"+IPAndInt.intToIp(ptNetSetup.uiDNS1)+"\nDNS2:"
							+IPAndInt.intToIp(ptNetSetup.uiDNS2)
							+"\nDNS域名名称："+new String(ptNetSetup.szHostName).trim()
						);
			}
		});
		
		basicConfView.getBtn_Net_NETSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_NetSetup.ByReference ptNetSetup = new T_NetSetup.ByReference();
				ptNetSetup.szHostName = StrimEncoding.getRealBytes(basicConfView .getText_dnsname().getText().getBytes(), 16);
				ptNetSetup.ucEth = 0;
				ptNetSetup.uiDNS1 = IPAndInt.ipToInt(basicConfView.getText_dns1().getText().trim());
				ptNetSetup.uiDNS2 = IPAndInt.ipToInt(basicConfView.getText_dns2().getText().trim());
				ptNetSetup.uiGatewayAddress = IPAndInt.ipToInt(basicConfView.getText_wg().getText().trim());
				ptNetSetup.uiIPAddress = IPAndInt.ipToInt(basicConfView.getText_ipaddr().getText().trim());
				ptNetSetup.uiMaskAddress = IPAndInt.ipToInt(basicConfView.getText_zwym().getText().trim());
				int ns = net.Net_NETSetup(HandleManage.gettHandle(net), ptNetSetup);
				containerControl.message(StatusCode.getStatusCode(ns, "Net_NETSetup"));
			}
		});
		
		basicConfView.getBtn_Net_QueryMACSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_MACSetup.ByReference ptMacSetup = new T_MACSetup.ByReference();
				int qms = net.Net_QueryMACSetup(HandleManage.gettHandle(net), ptMacSetup);
				containerControl.message(StatusCode.getStatusCode(qms, "Net_QueryMACSetup"));
				if(qms == 0){
					containerControl.message("MAC地址："+ByteArrayToMAC.getMacAddress(ptMacSetup.aucMACAddresss));
				}
			}
		});
		
		basicConfView.getBtn_Net_QueryRS485Setup().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				T_RS485Setup.ByReference ptRs485Setup = new T_RS485Setup.ByReference();
				int qrss = net.Net_QueryRS485Setup(HandleManage.gettHandle(net), ptRs485Setup);
				containerControl.message(StatusCode.getStatusCode(qrss, "Net_QueryRS485Setup"));
				if(qrss == 0){
					containerControl.message("波特率："+ptRs485Setup.ucBaudRate+"\n校验方式："+ptRs485Setup.ucCheckOut
							+"\nucFunction:"+ptRs485Setup.ucFunction);
				}
			}
		});
		basicConfView.getBtn_Net_QueryParkRS485Setup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				T_ParkRS485Setup ptRs485Setup = new T_ParkRS485Setup.ByReference();				
				int qprss = net.Net_QueryParkRS485Setup(HandleManage.gettHandle(net), ptRs485Setup);
				containerControl.message(StatusCode.getStatusCode(qprss, "Net_QueryParkRS485Setup"));
				if(qprss == 0){
					containerControl.message("波特率："+ptRs485Setup.ucBaudRate+"\n校验方式："+ptRs485Setup.ucCheckOut
							+"\nucFunction:"+ptRs485Setup.ucFunction);
				}
			}
		});
		basicConfView.getBtn_Net_QueryTimeSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DCTimeSetup.ByReference ptTimeSetup = new T_DCTimeSetup.ByReference();
				int qts = net.Net_QueryTimeSetup(HandleManage.gettHandle(net), ptTimeSetup);
				containerControl.message(StatusCode.getStatusCode(qts, "Net_QueryTimeSetup"));
				if(qts == 0){
					containerControl.message("时间："+ptTimeSetup.usYear+"年"+ptTimeSetup.ucMonth+"月"+ptTimeSetup.ucDay
							+"日 "+ptTimeSetup.ucHour+":"+ptTimeSetup.ucMinute+":"+ptTimeSetup.ucSecond
							+"\n日期格式："+ptTimeSetup.ucDayFmt+"\n时间格式："+ptTimeSetup.ucTimeFmt
							+"\n时区："+ptTimeSetup.ucTimeZone);
				}
			}
		});
		
		basicConfView.getBtn_Net_QueryMulticastSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_MulticastSetup.ByReference ptMulticastSetup = new T_MulticastSetup.ByReference();
				int qms = net.Net_QueryMulticastSetup(HandleManage.gettHandle(net), ptMulticastSetup);
				containerControl.message(StatusCode.getStatusCode(qms, "Net_QueryMulticastSetup"));
				if(qms == 0){
					containerControl.message("组播使能标志："+ptMulticastSetup.ucEnabled+"\n组播ip："+IPAndInt.intToIp(ptMulticastSetup.uiIP)
							+"\n主音频端口："+ptMulticastSetup.usMainAudPort+"\n主视频端口："+ptMulticastSetup.usMainVidPort+"\n离线组播端口："+ptMulticastSetup.usOfflinePort);
				}
			}
		});
		
		
		
		basicConfView.getBtn_Net_QuerySntpSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_SNTPSetup.ByReference ptSntpSetup = new T_SNTPSetup.ByReference();
				int ss = net.Net_QuerySntpSetup(HandleManage.gettHandle(net), ptSntpSetup);
				containerControl.message(StatusCode.getStatusCode(ss, "Net_SntpSetup"));
				if(ss == 0){
					containerControl.message("sntp使能："+ptSntpSetup.ucEnabled+"\npt服务器ip："+new String(ptSntpSetup.szSNTPServer).trim()
					+"\npt服务器端口："+ptSntpSetup.usPort);
				}
			}
		});
		
		basicConfView.getBtn_Net_QueryImageWDRSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_ImageWDR.ByReference ptImageWDR = new T_ImageWDR.ByReference();
				int qts = net.Net_QueryImageWDRSetup(HandleManage.gettHandle(net), ptImageWDR);
				containerControl.message(StatusCode.getStatusCode(qts, "Net_QueryImageWDRSetup"));
				if(qts == 0){
					containerControl.message("使能："+ptImageWDR.ucEnable+"\n快门时长1："+ptImageWDR.ucExposalTime1+"\n快门时长2："
				+ptImageWDR.ucExposalTime2+"\n感应电压1："+ptImageWDR.ucInducePressure1
				+"\n感应电压2："+ptImageWDR.ucInducePressure2);
				}
			}
		});
		
		basicConfView.getBtn_Net_QueryImageEvSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_ImageEv.ByReference ptImageEv = new T_ImageEv.ByReference();
				int qies = net.Net_QueryImageEvSetup(HandleManage.gettHandle(net), ptImageEv);
				containerControl.message(StatusCode.getStatusCode(qies, "Net_QueryImageEvSetup"));
				if(qies == 0){
					containerControl.message("背光补偿标志："+ptImageEv.ucEnable);
				}
			}
		});
		
		basicConfView.getBtn_Net_QueryRebootTimeSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_TimeReboot.ByReference ptTimeReboot = new T_TimeReboot.ByReference();
				int qrts = net.Net_QueryRebootTimeSetup(HandleManage.gettHandle(net), ptTimeReboot);
				containerControl.message(StatusCode.getStatusCode(qrts, "Net_QueryRebootTimeSetup"));
				if(qrts == 0){
					containerControl.message("使能标志："+ptTimeReboot.ucAutoRebootEn+"\nE_WeekDay："+ptTimeReboot.ucDayOfWeek
							+"\n定时重启时间："+ptTimeReboot.uiAutoRebootTime+"\n日志级别："+ptTimeReboot.ucLogLevel);
				}
			}
		});
		
		basicConfView.getBtn_Net_SetupRestore().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sr = net.Net_SetupRestore(HandleManage.gettHandle(net));
				containerControl.message(StatusCode.getStatusCode(sr,"Net_SetupRestore"));
			}
		});
		
		basicConfView.getBtn_Net_StoreConfig().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sc = net.Net_StoreConfig(HandleManage.gettHandle(net));
				containerControl.message(StatusCode.getStatusCode(sc, "Net_StoreConfig"));
			}
		});
		
		basicConfView.getBtn_Net_RestoreConfig().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rc = net.Net_ReStoreConfig(HandleManage.gettHandle(net));
				containerControl.message(StatusCode.getStatusCode(rc, "Net_ReStoreConfig"));
			}
		});
		
		basicConfView.getBtn_Net_RebootCamera().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rc = net.Net_RebootCamera(HandleManage.gettHandle(net));
				containerControl.message(StatusCode.getStatusCode(rc, "Net_RebootCamera"));
			}
		});
		basicConfView.getBtn_Net_GetSysState().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_SysState.ByReference ptSysState = new T_SysState.ByReference();
				int gss = net.Net_GetSysState(HandleManage.gettHandle(net), ptSysState);
				containerControl.message(StatusCode.getStatusCode(gss, "Net_GetSysState"));
				if(gss == 0){
					containerControl.message("以下项0表正常:\n线圈检测器状态:"+ptSysState.ucLoopStatus+"\n雷达检测器状态:"+ptSysState.ucRadarStatus
							+"\n硬盘状态:"+ptSysState.ucSSDStatus+"\n智能分析模块状态："+ptSysState.ucVAStatus
							+"\nSSD磁盘容量:"+ptSysState.uiSSDSize);
				}
			}
		});
	}

	public BasicConfView getBasicConfView() {
		return basicConfView;
	}
	
	
}
