package com.jinshi.netCamera.control;

import com.jinshi.netCamera.utils.HandleManage;
import com.jinshi.netCamera.utils.StatusCode;
import com.jinshi.netCamera.view.PlateDeviceView;
import com.sun.jna.Pointer;
import net.sdk.bean.serviceconfig.imagesnap.Data_T_ImageUserInfo.T_ImageUserInfo.ByReference;
import net.sdk.bean.serviceconfig.platedevice.Data_T_AutoControlGate.T_AutoControlGate;
import net.sdk.bean.serviceconfig.platedevice.Data_T_BlackWhiteList.T_BlackWhiteList;
import net.sdk.bean.serviceconfig.platedevice.Data_T_BlackWhiteListCount.T_BlackWhiteListCount;
import net.sdk.bean.serviceconfig.platedevice.Data_T_ControlGate.T_ControlGate;
import net.sdk.bean.serviceconfig.platedevice.Data_T_GetBlackWhiteList.T_GetBlackWhiteList;
import net.sdk.bean.serviceconfig.platedevice.Data_T_LedSetup.T_LedSetup;
import net.sdk.bean.serviceconfig.platedevice.Data_T_LprResult.T_LprResult;
import net.sdk.bean.serviceconfig.platedevice.Data_T_OffLinePayMode.T_OffLinePayMode;
import net.sdk.bean.serviceconfig.platedevice.Data_T_OnTimePay.T_OnTimePay;
import net.sdk.bean.serviceconfig.platedevice.Data_T_ParkLedLightSetup.T_ParkLedLightSetup;
import net.sdk.bean.serviceconfig.platedevice.Data_T_ParkNum.T_ParkNum;
import net.sdk.bean.serviceconfig.platedevice.Data_T_PayRule.T_PayRule;
import net.sdk.bean.serviceconfig.platedevice.Data_T_RS485Data.T_RS485Data;
import net.sdk.bean.serviceconfig.platedevice.Data_T_SubLedSetup.T_SubLedSetup;
import net.sdk.bean.serviceconfig.platedevice.Data_T_TimeSectionPay.T_TimeSectionPay;
import net.sdk.bean.serviceconfig.platedevice.Data_T_TimesPay.T_TimesPay;
import net.sdk.bean.serviceconfig.platedevice.Data_T_WLFuzzyMatch.T_WLFuzzyMatch;
import net.sdk.bean.serviceconfig.platedevice.Data_T_WhiteListMode.T_WhiteListMode;
import net.sdk.function.main.NET;
import net.sdk.function.serviceoperation.platedevice.callback.Callback_FGetImageCbEx.FGetImageCbEx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class PlateDeviceControl {
	private PlateDeviceView plateDeviceView;
	private NET net;
	private ContainerControl containerControl;
	
	private T_LedSetup.ByReference ptLedSetup;

	public PlateDeviceControl(NET net, ContainerControl containerControl) {
		this.containerControl = containerControl;
		this.net = net;
		this.plateDeviceView = new PlateDeviceView();
		this.ptLedSetup = new T_LedSetup.ByReference();

		
		initLedSetup();


		initLitener();
		initLitener2();
	}

	private void initLedSetup() {
		ptLedSetup.atSubLedInIdle[0] = getT_SubLedSetup();
	}

	private void initLitener2() {
		plateDeviceView.getCb_zjkz().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					int index = plateDeviceView.getCb_zjkz().getSelectedIndex()+1;
					T_ControlGate.ByReference ptControlGate = new T_ControlGate.ByReference();
					ptControlGate.ucState = (byte) index;
					int gs = net.Net_GateSetup(HandleManage.gettHandle(net), ptControlGate);
					containerControl.message(StatusCode.getStatusCode(gs, "Net_GateSetup"));
				}
			}
		});
		
		
		
		plateDeviceView.getBtn_save().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int xsms = plateDeviceView.getCb_xsms().getSelectedIndex();
				int rkck = plateDeviceView.getCb_rkck().getSelectedIndex();
				if(xsms == 0 && rkck == 0){
					//闲时入口
					ptLedSetup.atSubLedInIdle[0] = getT_SubLedSetup();
					
				}else if(xsms == 0 && rkck == 1){
					//闲时出口
					ptLedSetup.atSubLedOutIdle[0] = getT_SubLedSetup();
				}else if(xsms == 1 && rkck == 0){
					//忙时入口
					ptLedSetup.atSubLedInBusy[0] = getT_SubLedSetup();
				}else{
					//忙时出口
					ptLedSetup.atSubLedOutBusy[0] = getT_SubLedSetup();
				}
				containerControl.message("已存");
			}

			
		});
		
	
		plateDeviceView.getBtn_btn_Net_LedSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int audio = plateDeviceView.getCb_audio().isSelected()?1:0;
				ptLedSetup.ucAudioEnable = (byte) audio;
				int ls = net.Net_LedSetup(HandleManage.gettHandle(net), ptLedSetup);
				containerControl.message(StatusCode.getStatusCode(ls, "Net_LedSetup"));
			}
		});
		
		plateDeviceView.getBtn_Net_ParkLedLightSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_ParkLedLightSetup.ByReference ptParkLedLightSetup = new T_ParkLedLightSetup.ByReference();
				ptParkLedLightSetup.ucLevel = (byte) plateDeviceView.getCb_lddj().getSelectedIndex();
				ptParkLedLightSetup.ucLumaTH = (byte) Integer.parseInt(plateDeviceView.getText_ldfz().getText());
				ptParkLedLightSetup.ucWorkMode = (byte) plateDeviceView.getCb_ldms().getSelectedIndex();
				int plls = net.Net_ParkLedLightSetup(HandleManage.gettHandle(net), ptParkLedLightSetup);
				net.Net_ParkLedLightSetup(HandleManage.gettHandle(net), ptParkLedLightSetup);
				containerControl.message(StatusCode.getStatusCode(plls, "Net_ParkLedLightSetup"));
			}
		});
		
		plateDeviceView.getBtn_Net_ParkNumSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_ParkNum.ByReference  ptParkNum = new T_ParkNum.ByReference();
				ptParkNum.ParkNum = Integer.parseInt(plateDeviceView.getText_kytcw().getText());
				int pns = net.Net_ParkNumSetup(HandleManage.gettHandle(net), ptParkNum);
				containerControl.message(StatusCode.getStatusCode(pns, "Net_ParkNumSetup"));
			}
		});
		
		plateDeviceView.getBtn_Net_BlackWhiteListSetup().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						T_LprResult.ByReference ptLprResult = new T_LprResult.ByReference();
						try {
							byte LprResult[] = /*StrimEncoding.getRealBytes(*/plateDeviceView.getText_plate().getText().trim().getBytes("GB2312")/*, 8)*/;
							System.arraycopy(LprResult, 0, ptLprResult.LprResult, 0, LprResult.length);
							
							byte EndTime[] = /*StrimEncoding.getRealBytes(*/plateDeviceView.getText_endtime().getText().trim().getBytes()/*, 15)*/;
							System.arraycopy(EndTime, 0, ptLprResult.EndTime, 0, EndTime.length);
							
							byte StartTime[] = /*StrimEncoding.getRealBytes(*/plateDeviceView.getText_starttime().getText().trim().getBytes()/*, 15)*/;
							System.arraycopy(StartTime, 0, ptLprResult.StartTime, 0, StartTime.length);
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						T_BlackWhiteListCount.ByReference ptBlackWhiteListCount = new T_BlackWhiteListCount.ByReference();
						ptBlackWhiteListCount.uiCount = Integer.parseInt(plateDeviceView.getText_count().getText().trim());
						
						byte bPath[] = "c:\\wl.ini".getBytes();
						System.arraycopy(bPath, 0, ptBlackWhiteListCount.aucLplPath, 0, bPath.length);
						//ptBlackWhiteListCount.aucLplPath = "c:\\wl.ini\0".getBytes();
	
						int bwls = net.Net_BlackWhiteListSetup(ptLprResult, ptBlackWhiteListCount);
						containerControl.message(StatusCode.getStatusCode(bwls, "Net_BlackWhiteListSetup"));
					}
				}).start();
			}
		});
		
		plateDeviceView.getBtn_Net_BlackWhiteListSend().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				T_BlackWhiteList.ByReference ptBalckWhiteList = new T_BlackWhiteList.ByReference();
				ptBalckWhiteList.aucLplPath = "C:\\wl.ini\0".getBytes();
				ptBalckWhiteList.LprCode = 1;
				ptBalckWhiteList.LprMode = 1;
				ptBalckWhiteList.Lprnew = 0;
				
				int bwls = net.Net_BlackWhiteListSend(HandleManage.gettHandle(net), ptBalckWhiteList);
				containerControl.message(StatusCode.getStatusCode(bwls, "Net_BlackWhiteListSend"));
			}
		});
	}







	// 回调
	private FGetImageCbEx fCb = new FGetImageCbEx() {

		@Override
		public int invoke(
				int tHandle,
				int uiImageId,
				ByReference ptImageInfo,
				net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo,
				Pointer pUserData) {
			System.out.println("aaadd");
			try {
				String szLprResult = new String(ptImageInfo.szLprResult,
						"GB2312");
				containerControl.message("查询结果：" + "\n - 车牌号：" + szLprResult
						+ "\n - 车牌类型：" + ptImageInfo.ucLprType + "\n - 车牌颜色："
						+ ptImageInfo.ucPlateColor);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return 0;
		}

	};

	private T_SubLedSetup getT_SubLedSetup() {
		T_SubLedSetup tmp = new T_SubLedSetup();
		tmp.ucEnable = (byte) (plateDeviceView.getCb_showtext().isSelected()?1:0);
		tmp.ucInterval = (byte) Integer.parseInt(plateDeviceView.getText_time().getText());
		tmp.ucLedLine = (byte) (plateDeviceView.getRb_shp().isSelected()?1:0);
		tmp.ucMode = (byte) plateDeviceView.getCb_mode().getSelectedIndex();
		if(tmp.ucMode == 7){
			try {
				tmp.aucContent = plateDeviceView.getText_content().getText().getBytes("GB2312");
			} catch (UnsupportedEncodingException e){
				//
			}
		}
		
		return tmp;
	}
	

	
	private void initLitener() {
		plateDeviceView.getBtn_Net_QueryGateAutoOpen().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						T_AutoControlGate.ByReference ptAutoControlGate = new T_AutoControlGate.ByReference();
						int qgao = net.Net_QueryGateAutoOpen(
								HandleManage.gettHandle(net), ptAutoControlGate);
						containerControl.message(StatusCode.getStatusCode(qgao,
								"Net_QueryGateAutoOpen"));
						if (qgao == 0) {
							containerControl.message("入口相机抬闸使能："
									+ ptAutoControlGate.ucEntryEnable
									+ "\n出口相机抬闸使能："
									+ ptAutoControlGate.ucExitEnable);
						}
					}
				});

		plateDeviceView.getBtn_Net_QueryLedSetup().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								T_LedSetup.ByReference ptLedSetup = new T_LedSetup.ByReference();
								int qls = net.Net_QueryLedSetup(
										HandleManage.gettHandle(net),
										ptLedSetup);
								containerControl.message(StatusCode
										.getStatusCode(qls, "Net_QueryLedSetup"));
								if (qls == 0) {
									try {
										String msrk = new String(
												ptLedSetup.atSubLedInBusy[0].aucContent,
												"GB2312");
										String xsrk = new String(
												ptLedSetup.atSubLedInIdle[0].aucContent,
												"GB2312");
										String msck = new String(
												ptLedSetup.atSubLedOutBusy[0].aucContent,
												"GB2312");
										String csck = new String(
												ptLedSetup.atSubLedOutIdle[0].aucContent,
												"GB2312");
										containerControl.message("语音使能标志："
												+ ptLedSetup.ucAudioEnable
												+ "\n忙时入口显示屏显示内容：" + msrk
												+ "\n闲时入口显示屏显示内容：" + xsrk
												+ "\n忙时出口显示屏显示内容：" + msck
												+ "\n闲时出口显示屏显示内容：" + csck);
									} catch (UnsupportedEncodingException e) {
										e.printStackTrace();
									}

								}
							}
						}).start();
					}
				});

		plateDeviceView.getBtn_Net_QueryOffLinePayMode().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						T_OffLinePayMode.ByReference ptOffLinePayMode = new T_OffLinePayMode.ByReference();
						int qolpm = net.Net_QueryOffLinePayMode(
								HandleManage.gettHandle(net), ptOffLinePayMode);
						containerControl.message(StatusCode.getStatusCode(
								qolpm, "Net_QueryOffLinePayMode"));
						if (qolpm == 0) {
							containerControl.message("脱机收费模式:"
									+ ptOffLinePayMode.ucMode);
						}
					}
				});

		plateDeviceView.getBtn_Net_QueryOffLinePayRule().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						T_PayRule.ByReference ptOffLinePayRule = new T_PayRule.ByReference();
						int qolpr = net.Net_QueryOffLinePayRule(
								HandleManage.gettHandle(net), ptOffLinePayRule);
						containerControl.message(StatusCode.getStatusCode(
								qolpr, "Net_QueryOffLinePayRule"));
						if (qolpr == 0) {
							StringBuffer sbf = new StringBuffer();
							for (T_OnTimePay tp : ptOffLinePayRule.atOnTimePay) {
								sbf.append("\n按时收费：");
								sbf.append("\n - 按时收费使能标志：" + tp.ucEnable
										+ "\n - 车辆类型：" + tp.ucVehType
										+ "\n - 周期收费金额 , 单位角：" + tp.usCyclePay
										+ "\n - 计费周期 , 单位分钟：" + tp.usCycleTime
										+ "\n - 免费时长 , 单位分钟：" + tp.usFreeTime
										+ "\n起步金额 , 单位角:" + tp.usStartPay
										+ "\n - 封顶金额, 单位角 :" + tp.usTotalPreDay);
							}
							for (T_TimesPay tp : ptOffLinePayRule.atTimesPay) {
								sbf.append("\n按次收费：");
								sbf.append("\n - 使能标志:" + tp.ucEnable
										+ "\n - 封顶次数:" + tp.ucMaxTime
										+ "\n - 车辆类型：" + tp.ucVehType
										+ "\n - 免费结束时间，以分钟为单位："
										+ tp.usFreeEndTime
										+ "\n - 免费开始时间，以分钟为单位："
										+ tp.usFreeStartTime + "\n - 免费时长："
										+ tp.usFreeTime + "\n - 收费结束时间，以分钟为单位："
										+ tp.usPayEndTime + "\n - 一次收费金额："
										+ tp.usPayOne + "\n - 收费开始时间，以分钟为单位："
										+ tp.usPayStartTime);
							}
							for (T_TimeSectionPay tsp : ptOffLinePayRule.atTimeSectionPay) {
								sbf.append("\n分段收费：");
								sbf.append("\n - 使能标志:" + tsp.ucEnable
										+ "\n - 车辆类型：" + tsp.ucVehType
										+ "\n - 计费周期" + tsp.usCycleTime
										+ "\n - 免费时长：" + tsp.usFreeTime
										+ "\n - 时段结束时间，以分钟为单位："
										+ tsp.usSectionEndTime
										+ "\n - 时段开始时间，以分钟为单位："
										+ tsp.usSectionStartTime + "\n - 起步金额:"
										+ tsp.usStartPay + "\n - 起步时长："
										+ tsp.usStartTime + "\n - 时段封顶金额："
										+ tsp.usTotalPreDay);
							}

							containerControl.message("收费模式："
									+ ptOffLinePayRule.ucRuleMode + ""
									+ ptOffLinePayRule.ucStrategyType
									+ "\n限制时间：" + ptOffLinePayRule.usLimitTime
									+ "\n不足一个周期的计费策略:"
									+ ptOffLinePayRule.ucStrategyType
									+ sbf.toString());
						}
					}
				});

		plateDeviceView.getBtn_Net_Net_QueryParkLedLightSetup().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						T_ParkLedLightSetup.ByReference ptParkLedLightSetup = new T_ParkLedLightSetup.ByReference();
						int plls = net.Net_QueryParkLedLightSetup(
								HandleManage.gettHandle(net),
								ptParkLedLightSetup);
						containerControl.message(StatusCode.getStatusCode(plls,
								"Net_ParkLedLightSetup"));
						if (plls == 0) {
							containerControl.message("ucLevel:"
									+ ptParkLedLightSetup.ucLevel
									+ "\nucLumaTH:"
									+ ptParkLedLightSetup.ucLumaTH
									+ "\nucWorkMode:"
									+ ptParkLedLightSetup.ucWorkMode);
						}
					}
				});

		plateDeviceView.getBtn_Net_ReadGPIOState().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								byte[] pucValue = new byte[16];
								int rs = net.Net_ReadGPIOState(
										HandleManage.gettHandle(net), (byte) 0,
										pucValue);
								containerControl.message(StatusCode
										.getStatusCode(rs, "Net_ReadGPIOState"));
								if (rs == 0) {
									containerControl.message("IO状态："
											+ Arrays.toString(pucValue));
								}

							}
						}).start();
					}
				});
		plateDeviceView.getBtn_Net_ReadRS485Data().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								T_RS485Data.ByReference ptRS485Data = new T_RS485Data.ByReference();
								int rrd = net.Net_ReadRS485Data(
										HandleManage.gettHandle(net),
										ptRS485Data);
								containerControl.message(StatusCode
										.getStatusCode(rrd, "Net_ReadRS485Data"));
								if (rrd == 0) {
									containerControl.message("数据内容："
											+ Arrays.toString(ptRS485Data.data));
								}
							}
						}).start();
					}
				});

		plateDeviceView.getBtn_Net_ReadTwoEncpyption().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								byte[] pBuff = new byte[256];
								int uiSizeBuff = 256;
								int rte = net.Net_ReadTwoEncpyption(
										HandleManage.gettHandle(net), pBuff,
										uiSizeBuff);
								if (rte != -1) {
									containerControl.message("实际用户数据字节长度："
											+ rte + "\n"
											+ Arrays.toString(pBuff));
								}
							}
						}).start();

					}
				});

		plateDeviceView.getBtn_Net_RegOffLineClient().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int rolc = net.Net_RegOffLineClient(HandleManage
								.gettHandle(net));
						containerControl.message(StatusCode.getStatusCode(rolc,
								"Net_RegOffLineClient"));
						if (rolc == 0) {
							containerControl
									.message("绑定客户端成功，与此客户端的连接状态将作为判断是否脱机的依据");
						}
					}
				});
		plateDeviceView.getBtn_Net_RegOffLineImageRecvEx().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								int rolire = net.Net_RegOffLineImageRecvEx(
										HandleManage.gettHandle(net), fCb,
										Pointer.NULL);
								containerControl.message(StatusCode
										.getStatusCode(rolire,
												"Net_RegOffLineImageRecvEx"));
								if (rolire == 0) {
									containerControl
											.message("如果没有输出细信息表示黑白名单无数据");
								}
							}
						}).start();
					}
				});

		plateDeviceView.getBtn_Net_GetBlackWhiteList().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								T_GetBlackWhiteList.ByReference ptGetBalckWhiteList = new T_GetBlackWhiteList.ByReference();
								File file = new File(
										"C:/netimage/BalckWhiteList1.ini");
								if (!file.exists()) {
									try {
										file.createNewFile();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								
								/*byte bPath[] = "C:/netimage/BalckWhiteList1.ini".getBytes();
								System.arraycopy(bPath, 0, ptGetBalckWhiteList.aucLplPath, 0, bPath.length);*/
								
								//注意：要么按照字节长度拷贝内容，要么在地址最后加 \0
								ptGetBalckWhiteList.aucLplPath = "C:/netimage/BalckWhiteList.ini\0".getBytes();
								ptGetBalckWhiteList.LprCode = 1;
								ptGetBalckWhiteList.LprMode = 1;
								int gbwl = net.Net_GetBlackWhiteList(
										HandleManage.gettHandle(net),
										ptGetBalckWhiteList);
								containerControl.message(StatusCode
										.getStatusCode(gbwl,
												"Net_GetBlackWhiteList"));
								if (gbwl == 0) {
									containerControl
											.message("导出成功，信息存储在C:/netimage/BalckWhiteList.ini");
									containerControl
											.message(ptGetBalckWhiteList.LprCode
													+ "");
								}
							}
						}).start();
					}
				});
		plateDeviceView.getBtn_Net_DeleteAllBlackWhiteList().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int dabwl = net
								.Net_DeleteAllBlackWhiteList(HandleManage
										.gettHandle(net));
						containerControl.message(StatusCode.getStatusCode(
								dabwl, "Net_DeleteAllBlackWhiteList"));
						if (dabwl == 0) {
							containerControl.message("清除黑白名单成功");
						}
					}
				});
		plateDeviceView.getBtn_Net_GetBlackWhiteListAsCSV().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								T_GetBlackWhiteList.ByReference ptGetBalckWhiteList = new T_GetBlackWhiteList.ByReference();
								File file = new File(
										"C:/netimage/BalckWhiteList.CSV");
								if (!file.exists()) {
									try {
										file.createNewFile();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								ptGetBalckWhiteList.aucLplPath = "C:/netimage/BalckWhiteList.CSV\0"
										.getBytes();
								ptGetBalckWhiteList.LprCode = 0;
								ptGetBalckWhiteList.LprMode = 1;
								int gbwl = net.Net_GetBlackWhiteListAsCSV(
										HandleManage.gettHandle(net),
										ptGetBalckWhiteList);
								containerControl.message(StatusCode
										.getStatusCode(gbwl,
												"Net_GetBlackWhiteList"));
								if (gbwl == 0) {
									containerControl
											.message("导出成功，信息存储在C:/netimage/BalckWhiteList.CSV");
									containerControl
											.message(ptGetBalckWhiteList.LprCode
													+ "");
								}
							}
						}).start();
					}
				});

		plateDeviceView.getBtn_Net_QueryWhiteListMode().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						T_WhiteListMode.ByReference ptWhiteListMode = new T_WhiteListMode.ByReference();
						int qwlm = net.Net_QueryWhiteListMode(
								HandleManage.gettHandle(net), ptWhiteListMode);
						containerControl.message(StatusCode.getStatusCode(qwlm,
								"Net_QueryWhiteListMode"));
						if (qwlm == 0) {
							containerControl.message("军警牌自动放行使能标识:"
									+ ptWhiteListMode.ucArmyPoliceEn
									+ "\n工作模式：" + ptWhiteListMode.ucMode);
						}
					}
				});
		plateDeviceView.getBtn_Net_QueryWLFuzzyMatchMode().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						T_WLFuzzyMatch.ByReference ptWlFuzzyMatch = new T_WLFuzzyMatch.ByReference();
						int qwmm = net.Net_QueryWLFuzzyMatchMode(
								HandleManage.gettHandle(net), ptWlFuzzyMatch);
						containerControl.message(StatusCode.getStatusCode(qwmm,
								"Net_QueryWLFuzzyMatchMode"));
						if (qwmm == 0) {
							containerControl.message("黑白名单查询模式："
									+ ptWlFuzzyMatch.ucMatchMode + "\nucValue:"
									+ ptWlFuzzyMatch.ucValue);
						}
					}
				});
	}

	public PlateDeviceView getPlateDeviceView() {
		return plateDeviceView;
	}

}
