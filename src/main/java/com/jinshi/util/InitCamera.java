//package com.jinshi.util;
//
//import com.jinshi.netCamera.control.ContainerControl;
//import com.jinshi.netCamera.utils.ByteArrayToMAC;
//import com.jinshi.netCamera.utils.HandleManage;
//import com.jinshi.netCamera.utils.StatusCode;
//import com.jinshi.netCamera.view.InitView;
//import com.sun.jna.Pointer;
//import com.sun.jna.PointerType;
//import com.sun.jna.ptr.IntByReference;
//import net.sdk.bean.serviceconfig.imagesnap.Data_T_DCImageSnap.T_DCImageSnap;
//import net.sdk.bean.systemconfig.finddevice.Data_T_RcvMsg.T_RcvMsg.ByReference;
//import net.sdk.function.main.NET;
//import net.sdk.function.systemcommon.control.finddevice.callback.Callback_NET_FIND_DEVICE_CALLBACK.NET_FIND_DEVICE_CALLBACK;
//import net.sdk.function.systemcommon.control.finddevice.callback.Callback_NET_FIND_DEVICE_IP_CALLBACK.NET_FIND_DEVICE_IP_CALLBACK;
//import net.sdk.function.systemcommon.control.finddevice.callback.Callback_NET_FIND_DEVICE_MAC_CALLBACK.NET_FIND_DEVICE_MAC_CALLBACK;
//import net.sdk.function.systemcommon.control.message.callback.Callback_FGetReportCBEx.FGetReportCBEx;
//import net.sdk.function.systemcommon.control.message.callback.Callback_NET_CONTROLCALLBACKEx.NET_CONTROLCALLBACKEx;
//import net.sdk.function.systemcommon.imagesnap.callback.Callback_FGetImageCB.FGetImageCB;
//import net.sdk.function.systemcommon.imagesnap.callback.Callback_FGetImageCBEx.FGetImageCBEx;
//import uk.co.caprica.vlcj.player.MediaPlayer;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.util.UUID;
//
//public class InitCamera {
//	private InitView initView;
//	private NET net;
//	private String ptIp;
//	private MediaPlayer player;
//	private boolean iSPlay;
//
//	public InitCamera(NET net) {
//		this.net = net;
//		player = initView.getEmbeddedMediaPlayerComponent().getMediaPlayer();
//		iSPlay = false;
//		initCallback();
//	}
//
//	private void initCallback() {
//		int rire = net.Net_RegImageRecv(fcb);
//		System.out.println(StatusCode.getStatusCode(rire,
//				"Net_RegImageRecvEx"));
//		if (rire == 0) {
//			System.out.println("强制抓拍Net_RegImageRecv回调函数注册完毕");
//		}
//
////		int rrme = net.Net_RegReportMessEx(HandleManage.gettHandle(net), fgrcb,
////				Pointer.NULL);
////		containerControl.message(StatusCode.getStatusCode(rrme,
////				"Net_RegReportMessEx"));
////		if (rrme == 0) {
////			containerControl.message("回调函数Net_RegReportMessEx注册完毕");
////		}
//	}
//
//	/**
//	  * 将String数据存为文件
//	  */
//	 public static File getFileFromString(String name,String path) {
//	    byte[] b=name.getBytes();
//	    BufferedOutputStream stream = null;
//	     File file = null;
//	     try {
//	         file = new File(path);
//	         FileOutputStream fstream = new FileOutputStream(file);
//	         stream = new BufferedOutputStream(fstream);
//	         stream.write(b);
//	     } catch (Exception e) {
//	         e.printStackTrace();
//	     } finally {
//	         if (stream != null) {
//	             try {
//	                 stream.close();
//	             } catch (IOException e1) {
//	                 e1.printStackTrace();
//	             }
//	         }
//	     }
//	     return file;
//	 }
//
//	 /**
//	  * 将byte数据存为文件
//	  */
//	 public static File getFileFromBytes(byte[] b,String path) {
//	     BufferedOutputStream stream = null;
//	     File file = null;
//	     try {
//	         file = new File(path);
//	         FileOutputStream fstream = new FileOutputStream(file);
//	         stream = new BufferedOutputStream(fstream);
//	         stream.write(b);
//	     } catch (Exception e) {
//	         e.printStackTrace();
//	     } finally {
//	         if (stream != null) {
//	             try {
//	                 stream.close();
//	             } catch (IOException e1) {
//	                 e1.printStackTrace();
//	             }
//	         }
//	     }
//	     return file;
//	 }
//	 //
//
//
//	// 回调
//	FGetImageCB fcb = new FGetImageCB() {
//
//		@Override
//		public int invoke(
//				int tHandle,
//				int uiImageId,
//				net.sdk.bean.serviceconfig.imagesnap.Data_T_ImageUserInfo.T_ImageUserInfo.ByReference ptImageInfo,
//				net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo) {
//			System.out.println("FGetImageCB");
//				try {
//					String result = new String(ptImageInfo.szLprResult,"GBK");
//					initView.getjLabelCp().setText(result.trim());
//					System.out.println("回调输出：" + "\n - 车牌颜色："
//							+ ptImageInfo.ucPlateColor + "\n - 车牌类型："
//							+ ptImageInfo.ucLprType + "\n - 全景图片大小："
//							//+ ptPicInfo.uiPanoramaPicLen + "\n - 车牌号：");
//							+ ptPicInfo.uiPanoramaPicLen + "\n - 车牌号："
//							+ result.trim());
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				//测试保存抓拍图片到本地
//				getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), "C:/aaa.jpeg");
//				//initView.getjLabelMaxImg().setIcon(new ImageIcon("C:/aaa.jpeg"));
//
//				ImageIcon imgMaxIco = new ImageIcon("C:/aaa.jpeg");
//				Image imgMax = imgMaxIco.getImage();
//				imgMax = imgMax.getScaledInstance(285, 150, Image.SCALE_DEFAULT);
//				initView.getjLabelMaxImg().setIcon(new ImageIcon(imgMax));
//				//imageMax.setImage(imageMax.getImage().getScaledInstance(ptImageInfo.usWidth, ptImageInfo.usHeight,Image.SCALE_DEFAULT ));
//
//				getFileFromBytes(ptPicInfo.ptVehiclePicBuff.getByteArray(0, ptPicInfo.uiVehiclePicLen), "C:/aaa_Vehicle.jpeg");
//				//initView.getjLabelMiniImg().setIcon(new ImageIcon("C:/aaa_Vehicle.jpeg"));
//
//				ImageIcon imgMiniIco = new ImageIcon("C:/aaa_Vehicle.jpeg");
//				Image imgMini = imgMiniIco.getImage();
//				imgMini = imgMini.getScaledInstance(210, 45, Image.SCALE_DEFAULT);
//				initView.getjLabelMiniImg().setIcon(new ImageIcon(imgMini));
//
//			return 0;
//		}
//	};
//
//	// 回调
//	private NET_FIND_DEVICE_CALLBACK net_find_device = new NET_FIND_DEVICE_CALLBACK() {
//
//		@Override
//		public void invoke(ByReference ptFindDevice, Pointer pUserData) {
//			String ancAppVersion = new String(ptFindDevice.ancAppVersion);
//			String ancSerialNum = new String(ptFindDevice.ancSerialNum);
//			String aucDstMACAdd = ByteArrayToMAC.getMacAddress(ptFindDevice.aucDstMACAdd);
//			System.out.println("回调输出结果：\n软件版本：" + ancAppVersion
//					+ "\nptFindDevice:" + ptFindDevice.uiFlag + "\n设备序列号："
//					+ ancSerialNum+"\n目标mac地址："+aucDstMACAdd);
//		}
//	};
//	// 回调
//	private NET_FIND_DEVICE_IP_CALLBACK net_find_device_ip = new NET_FIND_DEVICE_IP_CALLBACK() {
//
//		@Override
//		public void invoke(String strFindDeviceIp, Pointer pUserData) {
//			System.out.println("回调输出结果：\nip地址：" + strFindDeviceIp);
//		}
//
//	};
//	// 回调
//	private NET_FIND_DEVICE_MAC_CALLBACK net_find_device_mac = new NET_FIND_DEVICE_MAC_CALLBACK() {
//
//		@Override
//		public void invoke(String strFindDeviceMac, Pointer pUserData) {
//			System.out.println("回调输出结果：\nMac地址：" + strFindDeviceMac);
//		}
//
//	};
//	// 回调
//	private NET_CONTROLCALLBACKEx controlcallbackEx = new NET_CONTROLCALLBACKEx() {
//
//		@Override
//		public void invoke(int tHandle, byte ucCtrlConnState, Pointer pUser) {
//			String status = ucCtrlConnState == 0 ? "无意义"
//					: ucCtrlConnState == 1 ? "连接中"
//							: ucCtrlConnState == 2 ? "连接成功" : "连接失败";
//			System.out.println("句柄为" + tHandle + "的相机状态：" + status);
//		}
//
//	};
//
//	// 回调
//	FGetReportCBEx fgrcb = new FGetReportCBEx() {
//
//		@Override
//		public int invoke(int tHandle, byte ucType, PointerType ptMessage,
//				Pointer pUserData) {
//			if (ucType == 5) {
//
//			}
//
//			return 0;
//		}
//
//	};
//
//	FGetImageCBEx cfun = new FGetImageCBEx() {
//
//		@Override
//		public int invoke(
//				int tHandle,
//				int uiImageId,
//				net.sdk.bean.serviceconfig.imagesnap.Data_T_ImageUserInfo.T_ImageUserInfo.ByReference ptImageInfo,
//				net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo,
//				Pointer pUser) {
//			System.out.println("回调输出：" + "\n车牌颜色："
//					+ ptImageInfo.ucPlateColor + "\n车牌类型："
//					+ ptImageInfo.ucLprType + "\n全景图片大小："
//					+ ptPicInfo.uiPanoramaPicLen);
//			return 0;
//		}
//	};
//
//	private void initListener() {
//
//
//
//		initView.getBtn_Net_ConnCamera().addActionListener(
//				new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						new Thread(new Runnable() {
//
//							@Override
//							public void run() {
//								int tHandle = Integer.parseInt(initView
//										.getTHandle().getText());
//								short usPort = (short) Integer
//										.parseInt(initView.getText_port()
//												.getText());
//								short usTimeout = (short) Integer
//										.parseInt(initView.getText_timeout()
//												.getText());
//								int cc = net.Net_ConnCamera(tHandle, usPort,
//										usTimeout);
//								if (cc == 0) {
//									startRtspVideo(ptIp);
//									HandleManage.settHandle(tHandle);
//									containerControl.message("全局设备句柄" + tHandle
//											+ "已生效");
//								}
//								containerControl.message(StatusCode
//										.getStatusCode(cc, "Net_ConnCamera"));
//							}
//						}).start();
//					}
//				});
//
//		initView.getBtn_Net_QueryConnState().addActionListener(
//				new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						/*int tHandle = Integer.parseInt(initView.getTHandle()
//								.getText());
//						int qcs = net.Net_QueryConnState(tHandle);
//						containerControl.message(StatusCode.getStatusCode(qcs,
//								"Net_QueryConnState"));*/
//
//						int jm = net.Net_ModifyDeviceIpByMac("00:2F:A8:0D:EA:00", "192.168.0.150", "255.255.255.0");
//						containerControl.message(StatusCode.getStatusCode(jm,
//							"Net_ModifyDeviceIpByMac"));
//					}
//				});
//
//		initView.getBtn_Net_DisConnCamera().addActionListener(
//				new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						stopRtspVideo();
//						int tHandle = HandleManage.gettHandle(net);
//						int dcc = net.Net_DisConnCamera(tHandle);
//						containerControl.message("全局句柄："
//								+ tHandle
//								+ " (为-1请先连接相机)\n"
//								+ StatusCode.getStatusCode(dcc,
//										"Net_DisConnCamera"));
//					}
//				});
//		initView.getBtn_Net_DelCamera().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				stopRtspVideo();
//				int tHandle = HandleManage.gettHandle(net);
//				int dc = net.Net_DelCamera(tHandle);
//				if (dc == 0) {
//					HandleManage.settHandle(-1);
//					containerControl.message("全局设备句柄已置为-1");
//				}
//				containerControl.message(StatusCode.getStatusCode(dc,
//						"Net_DelCamera"));
//			}
//		});
//
//		initView.getBtn_Net_SetControlCallBack().addActionListener(
//				new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						int sccb = net.Net_SetControlCallBackEx(
//								HandleManage.gettHandle(net),
//								controlcallbackEx, Pointer.NULL);
//						containerControl.message(StatusCode.getStatusCode(sccb,
//								"Net_SetControlCallBackEx"));
//					}
//				});
//
//		initView.getBtn_Net_SaveImageToJpeg().addActionListener(
//				new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								File file = new File("C:/netimage/");
//								if (!file.exists()) {
//									file.mkdir();
//								}
//								StringBuffer sbf = new StringBuffer(
//										"C:/netimage/");
//								sbf.append(UUID.randomUUID());
//								sbf.append(".jpeg");
//								int sitp = net.Net_SaveImageToJpeg(
//										HandleManage.gettHandle(net),
//										sbf.toString());
//								containerControl.message(StatusCode
//										.getStatusCode(sitp,
//												"Net_SaveImageToJpeg")
//										+ "\n图像保存路径：" + sbf.toString());
//							}
//						}).start();
//					}
//				});
//		initView.getBtn_Net_ImageSnap().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				T_DCImageSnap.ByReference ptImageSnap = new T_DCImageSnap.ByReference();
//				int is = net.Net_ImageSnap(HandleManage.gettHandle(net), ptImageSnap);
//				containerControl.message(StatusCode.getStatusCode(is, "Net_ImageSnap"));
//				if(is == 0){
//					containerControl.message("等待回调输出：");
//				}
//			}
//		});
//		initView.getBtn_Net_SetSnapMode().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int niSnapMode = initView.getRb_cfzp().isSelected() ? 0 : 1;
//				int ssm = net.Net_SetSnapMode(HandleManage.gettHandle(net), niSnapMode);
//				containerControl.message(StatusCode.getStatusCode(ssm, "Net_SetSnapMode"));
//			}
//		});
//	}
//
//	public InitView getInitView() {
//		return initView;
//	}
//	private void startRtspVideo(String ip)
//	{
//	   if(iSPlay)
//		   player.stop();
//	   String[] options =
//	        {"video-filter=motionblur",  "network-caching=200", "no-plugins-cache",":rtsp-tcp"};
//	   iSPlay = true;
//	   player.playMedia("rtsp://"+ip+":50000/video",options);
//	}
//	private void stopRtspVideo()
//	{
//		if(iSPlay)
//			player.stop();
//		iSPlay = false;
//	}
//}