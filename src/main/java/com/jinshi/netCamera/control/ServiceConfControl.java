package com.jinshi.netCamera.control;

import com.jinshi.netCamera.utils.HandleManage;
import com.jinshi.netCamera.utils.StatusCode;
import com.jinshi.netCamera.view.ServiceConfView;
import net.sdk.bean.serviceconfig.detectdevice.Data_T_DetectModeSetup.T_DetectModeSetup;
import net.sdk.bean.serviceconfig.detectdevice.Data_T_LoopParamSetup.T_LoopParamSetup;
import net.sdk.bean.serviceconfig.detectdevice.Data_T_VehicleVAFunSetup.T_VehicleVAFunSetup;
import net.sdk.bean.serviceconfig.detectdevice.Data_T_VideoDetectParamSetup.T_VideoDetectParamSetup;
import net.sdk.bean.serviceconfig.platedevice.Data_T_RS485Data.T_RS485Data;
import net.sdk.bean.serviceconfig.roadlaneconf.Data_T_VideoParaSetup.T_VideoParaSetup;
import net.sdk.bean.serviceconfig.storageconf.Data_T_HardDiskInfo.T_HardDiskInfo;
import net.sdk.bean.serviceconfig.storageconf.Data_T_StorageSetup.T_StorageSetup;
import net.sdk.bean.serviceconfig.subtitleconf.Data_T_Subtitle.T_Subtitle;
import net.sdk.bean.serviceconfig.videocode.Data_T_DynamicCode.T_DynamicCode;
import net.sdk.function.main.NET;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ServiceConfControl {
	private NET net;
	private ServiceConfView serviceConfView;
	private ContainerControl containerControl;
	
	public ServiceConfControl(NET net, ContainerControl containerControl){
			this.net = net;
			this.serviceConfView = new ServiceConfView();
			this.containerControl = containerControl;
			
			initLitener();
			initListener2();
	}

	private void initListener2() {
		serviceConfView.getBtn_Net_VehicleVAFunSetup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_VehicleVAFunSetup.ByReference ptVehicleVAFunSetup = new T_VehicleVAFunSetup.ByReference();
				ptVehicleVAFunSetup.ucDoubleYellowPlate = (byte) (serviceConfView.getRb_hpsb().isSelected()?0:1);
				ptVehicleVAFunSetup.ucPlateDirection = (byte) (serviceConfView.getRb_cpfx().isSelected()?0:1);
				ptVehicleVAFunSetup.ucPlateFeature = (byte) (serviceConfView.getRb_cptx().isSelected()?0:1);
				ptVehicleVAFunSetup.ucPlateRealTimeShowEn = (byte) (serviceConfView.getRb_sscp1().isSelected()?0:1);
				ptVehicleVAFunSetup.ucPolicePlate = (byte) (serviceConfView.getRb_wjcp().isSelected()?0:1);
				ptVehicleVAFunSetup.ucRecRegShowEn = (byte) (serviceConfView.getRb_sbqy1().isSelected()?0:1);
				ptVehicleVAFunSetup.ucVehicleBrandRecogEn = (byte) (serviceConfView.getRb_cb1().isSelected()?0:1);
				ptVehicleVAFunSetup.ucVehicleColorRecogEn = (byte) (serviceConfView.getRb_csys().isSelected()?0:1);
				ptVehicleVAFunSetup.ucLocalCity = (byte) (serviceConfView.getCb_second().getSelectedIndex()+65);
				ptVehicleVAFunSetup.ucVehicleInterval = (byte) Integer.parseInt(serviceConfView.getText_zpjg().getText());
				ptVehicleVAFunSetup.usMaxPlateW = (short) Integer.parseInt(serviceConfView.getText_maxpix().getText());
				ptVehicleVAFunSetup.usMinPlateW = (short) Integer.parseInt(serviceConfView.getText_minpix().getText());
				
				int vvs = net.Net_VehicleVAFunSetup(HandleManage.gettHandle(net), ptVehicleVAFunSetup);
				containerControl.message(StatusCode.getStatusCode(vvs, "Net_VehicleVAFunSetup"));
			}
		});
		
		
		
		
		serviceConfView.getCb_jcms().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					T_DetectModeSetup.ByReference ptDetectModeSetup = new T_DetectModeSetup.ByReference();
					ptDetectModeSetup.ucDetectMode = (byte) serviceConfView.getCb_jcms().getSelectedIndex();
					int dms = net.Net_DetectModeSetup(HandleManage.gettHandle(net), ptDetectModeSetup);
					containerControl.message(StatusCode.getStatusCode(dms, "Net_DetectModeSetup"));
				}
			}
		});
		
		serviceConfView.getBtn_Net_StorageSetup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_StorageSetup.ByReference ptStorageSetup = new T_StorageSetup.ByReference();
				ptStorageSetup.ucBackupPolicyIllegal = (byte) (serviceConfView.getCb_wftp().isSelected()?1:0);
				ptStorageSetup.ucBackupPolicyIRecord = (byte) (serviceConfView.getCb_wflx().isSelected()?1:0);
				ptStorageSetup.ucBackupPolicyNormal = (byte) (serviceConfView.getCb_kktp().isSelected()?1:0);
				ptStorageSetup.ucDirStuct = (byte) (serviceConfView.getRb_ccjg1().isSelected()?0:1);
				ptStorageSetup.ucEnable = (byte) (serviceConfView.getCb_bdcc().isSelected()?1:0);
				ptStorageSetup.ucIllegalRecordEn = (byte) (serviceConfView.getCb_wflxkq().isSelected()?1:0);
				ptStorageSetup.ucIllegalRecordReadyTime = (byte) Integer.parseInt(serviceConfView.getText_ylsc().getText());
				ptStorageSetup.ucIllegalRecordTime = (byte) Integer.parseInt(serviceConfView.getText_wflxsc().getText());
				ptStorageSetup.ucRealTimeRecordDuration = (byte) Integer.parseInt(serviceConfView.getText_sslx().getText());
				ptStorageSetup.ucRealTimeRecordEn = (byte) (serviceConfView.getCb_sslxkq().isSelected()?1:0);
				ptStorageSetup.ucWritePolicy = (byte) (serviceConfView.getRb_pmcl().isSelected()?0:1);
				
				int ss = net.Net_StorageSetup(HandleManage.gettHandle(net), ptStorageSetup);
				containerControl.message(StatusCode.getStatusCode(ss, "Net_StorageSetup"));
			}
		});
		
		serviceConfView.getBtn_Send_Rs485AndHex().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_RS485Data.ByReference rs485 = new T_RS485Data.ByReference();
				int selectedIndex = serviceConfView.getCb_rs485com().getSelectedIndex();
				int length = serviceConfView.getText_rs485().getText().length();
				byte[] bytes = serviceConfView.getText_rs485().getText().getBytes();
				rs485.rs485Id = (byte)(serviceConfView.getCb_rs485com().getSelectedIndex());
				rs485.dataLen = (short)serviceConfView .getText_rs485().getText().length();
				rs485.data = serviceConfView .getText_rs485().getText().getBytes();
				//serviceConfView.getBtn_Send_Rs485AndHex().setText(serviceConfView .getText_rs485().getText());
				int rs = net.Net_SendRS485Data(HandleManage.gettHandle(net), rs485);
				containerControl.message(StatusCode.getStatusCode(rs, serviceConfView .getText_rs485().getText()));
			}
			});
	}
	

	private void initLitener() {
		serviceConfView.getBtn_Net_QueryViceVideoCodeSetup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DynamicCode.ByReference ptDynamicCode = new T_DynamicCode.ByReference();
				int qdcs = net.Net_QueryDynamicCodeSetup(HandleManage.gettHandle(net), ptDynamicCode);
				containerControl.message(StatusCode.getStatusCode(qdcs, "Net_QueryDynamicCodeSetup"));
				if(qdcs == 0){
					containerControl.message("使能标志："+ptDynamicCode.ucEnabled+"\n码率："+ptDynamicCode.uiRate+"\n切换时间："+ptDynamicCode.usTime);
				}
			}
		});
		
		serviceConfView.getBtn_Net_QueryDynamicCodeSetup().addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DynamicCode.ByReference ptDynamicCode = new T_DynamicCode.ByReference();
				int qdcs = net.Net_QueryDynamicCodeSetup(HandleManage.gettHandle(net), ptDynamicCode);
				containerControl.message(StatusCode.getStatusCode(qdcs, "Net_QueryDynamicCodeSetup"));
				if(qdcs == 0){
					containerControl.message("使能标志："+ptDynamicCode.ucEnabled+"\n码率："+ptDynamicCode.uiRate+"\n切换时间:"+ptDynamicCode.usTime
							);
				}
			}
		});
		
		serviceConfView.getBtn_Net_QueryDetectModeSetup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_DetectModeSetup.ByReference ptDetectModeSetup = new T_DetectModeSetup.ByReference();
				int qdms = net.Net_QueryDetectModeSetup(HandleManage.gettHandle(net), ptDetectModeSetup);
				containerControl.message(StatusCode.getStatusCode(qdms, "Net_QueryDetectModeSetup"));
				if(qdms == 0){
					containerControl.message("相机检测模式："+ptDetectModeSetup.ucDetectMode);
				}
			}
		});
		
		serviceConfView.getBtn_Net_QueryLoopDetectSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_LoopParamSetup.ByReference ptLoopParamSetup= new T_LoopParamSetup.ByReference();
				int qlds = net.Net_QueryLoopDetectSetup(HandleManage.gettHandle(net), ptLoopParamSetup);
				containerControl.message(StatusCode.getStatusCode(qlds, "Net_QueryLoopDetectSetup"));
				if(qlds == 0){
					containerControl.message("线圈对应车道索引："+ptLoopParamSetup.ucLaneIndex
							+"\n车检器对应车道索引："+ptLoopParamSetup.ucLoopLaneIndex
							+"\n线圈模式："+ptLoopParamSetup.ucMode
							+"\n线圈间隔："+ptLoopParamSetup.usLoopInterval
							+"\n线圈宽度："+ptLoopParamSetup.usLoopWidth);
				}
			}
		});
		serviceConfView.getBtn_Net_QueryVideoDetectSetup().
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				T_VideoDetectParamSetup.ByReference ptVideoDetectParamSetup = new T_VideoDetectParamSetup.ByReference();
				int qvds = net.Net_QueryVideoDetectSetup(HandleManage.gettHandle(net), ptVideoDetectParamSetup);
				containerControl.message(StatusCode.getStatusCode(qvds, "Net_QueryVideoDetectSetup"));
				if(qvds == 0){
					containerControl.message("车道数："+ptVideoDetectParamSetup.ucLanes
							+"\n识别区域点个数："+ptVideoDetectParamSetup.ucPlateRegPointNum
							+"\n摩托车抓拍："+ptVideoDetectParamSetup.ucSnapAutoBike
							+"\n虚拟线圈："+ptVideoDetectParamSetup.ucVirLoopNum
							+"\n违停时长："+ptVideoDetectParamSetup.usBanTime
							+"\n相机安装高度："+ptVideoDetectParamSetup.usCameraHeight
							+"\n路面矩形长度："+ptVideoDetectParamSetup.usRectLength
							+"\n路面矩形宽度："+ptVideoDetectParamSetup.usRectWidth
							+"\n垂直投影距离："+ptVideoDetectParamSetup.usTotalDis
							);
				}
			}
		});
		serviceConfView.getBtn_Net_QueryVehicleVAFunSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_VehicleVAFunSetup.ByReference ptVehicleVAFunSetup = new T_VehicleVAFunSetup.ByReference();
				int qvvafs = net. Net_QueryVehicleVAFunSetup(HandleManage.gettHandle(net), ptVehicleVAFunSetup);
				containerControl.message(StatusCode.getStatusCode(qvvafs, "Net_QueryVehicleVAFunSetup"));
				if(qvvafs == 0){
					containerControl.message("同一辆车抓拍时间间隔高字节："+ptVehicleVAFunSetup.ucCpHightTI
							+"\n同一辆车抓拍时间间隔低字节："+ptVehicleVAFunSetup.ucCpTimeInterval
							+"\n双层黄牌识别："+ptVehicleVAFunSetup.ucDoubleYellowPlate
							+"\n车牌的第二个字符："+ptVehicleVAFunSetup.ucLocalCity
							+"\n车牌方向："+ptVehicleVAFunSetup.ucPlateDirection
							+"\n车牌特写："+ptVehicleVAFunSetup.ucPlateFeature
							+"\n显示实时车牌车牌："+ptVehicleVAFunSetup.ucPlateRealTimeShowEn
							+"\n武警车牌汉字输出："+ptVehicleVAFunSetup.ucPolicePlate
							+"\n车标识别使能 ："+ptVehicleVAFunSetup.ucVehicleBrandRecogEn
							+"\n车身颜色识别使能 ："+ptVehicleVAFunSetup.ucVehicleColorRecogEn
							+"\n前后车抓拍间隔："+ptVehicleVAFunSetup.ucVehicleInterval
							+"\n默认省份："+ptVehicleVAFunSetup.uiPlateDefaultWord
							+"\n车牌识别最大宽度："+ptVehicleVAFunSetup.usMaxPlateW
							+"\n车牌识别最小宽度："+ptVehicleVAFunSetup.usMinPlateW);
				}
			}
		});
		
		
		
		
		serviceConfView.getBtn_Net_QueryVideoSubtitleSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_Subtitle.ByReference ptSubtitle = new T_Subtitle.ByReference();
				int qvss = net.Net_QueryVideoSubtitleSetup(HandleManage.gettHandle(net), ptSubtitle);
				containerControl.message(StatusCode.getStatusCode(qvss, "Net_QueryVideoSubtitleSetup"));
				if(qvss == 0){
					containerControl.message("使能标志："+ptSubtitle.ucEnabled
				+"\n字体大小："+ptSubtitle.ucFontSize
				+"\n字幕序号："+ptSubtitle.ucIndex
				+"\n星期显示："+ptSubtitle.ucWeekEnabled
				+"\n背景色："+ptSubtitle.uiBackColor
				+"\n前景色："+ptSubtitle.uiForeColor
				+"\nX坐标："+ptSubtitle.usPosX
				+"\nY坐标："+ptSubtitle.usPosY);
				}
			}
		});
		
		serviceConfView.getBtn_Net_QueryVideoParaSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_VideoParaSetup.ByReference ptVideoParaSetup = new T_VideoParaSetup.ByReference();
				int qvps = net.Net_QueryVideoParaSetup(HandleManage.gettHandle(net), ptVideoParaSetup);
				containerControl.message(StatusCode.getStatusCode(qvps, "Net_QueryVideoParaSetup"));
				if(qvps == 0){
					containerControl.message("增益模式："+ptVideoParaSetup.ucGainMode
							+"\n快门模式："+ptVideoParaSetup.ucShutterMode
							+"\n快门值："+ptVideoParaSetup.uiShutterValue
							+"\n亮度的标准参考值："+ptVideoParaSetup.usBrightnessTH
							+"\n增益："+ptVideoParaSetup.usGainValue);
				}
			}
		});
		serviceConfView.getBtn_Net_QueryStorageSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_StorageSetup.ByReference ptStorageSetup = new T_StorageSetup.ByReference();
				int qss = net.Net_QueryStorageSetup(HandleManage.gettHandle(net), ptStorageSetup);
				containerControl.message(StatusCode.getStatusCode(qss, "Net_QueryStorageSetup"));
				if(qss == 0){
					containerControl.message("违法图片的备份策略："+ptStorageSetup.ucBackupPolicyIllegal
							+"\n违法录像是否保存："+ptStorageSetup.ucBackupPolicyIRecord
							+"\n卡口图片的备份策略："+ptStorageSetup.ucBackupPolicyNormal
							+"\n目录结构："+ptStorageSetup.ucDirStuct
							+"\n使能："+ptStorageSetup.ucEnable
							+"\n违法录像使能标志 ："+ptStorageSetup.ucIllegalRecordEn
							+"\n预录时长："+ptStorageSetup.ucIllegalRecordReadyTime
							+"\n违法录像录制时长："+ptStorageSetup.ucIllegalRecordTime
							+"\n实时录像时长,分钟(1~120)："+ptStorageSetup.ucRealTimeRecordDuration
							+"\n实时录像是否启动："+ptStorageSetup.ucRealTimeRecordEn
							+"\n磁盘满时的处理策略："+ptStorageSetup.ucWritePolicy);
				}
			}
		});
		serviceConfView.getBtn_Net_QueryHardInfo().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_HardDiskInfo.ByReference ptHardDiskInfo = new T_HardDiskInfo.ByReference();
				int qhi = net.Net_QueryHardInfo(HandleManage.gettHandle(net), ptHardDiskInfo);
				containerControl.message(StatusCode.getStatusCode(qhi, "Net_QueryHardInfo"));
				if(qhi == 0){
					containerControl.message("磁盘分区索引编号:"+ptHardDiskInfo.ucPartitionCnt
							+"\n分区信息:"+ptHardDiskInfo.atPartition);
				}
			}
		});
	}

	public ServiceConfView getServiceConfView() {
		return serviceConfView;
	}
}
