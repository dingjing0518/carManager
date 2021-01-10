package com.jinshi.netCamera.view;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PlateDeviceView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_Net_QueryGateAutoOpen;
	private JButton btn_Net_QueryLedSetup;
	private JButton btn_Net_QueryOffLinePayMode;
	private JButton btn_Net_QueryOffLinePayRule;
	private JButton btn_Net_Net_QueryParkLedLightSetup;
	private JButton btn_Net_GetBlackWhiteList;
	private JCheckBox cb_showtext;
	private JPanel jPanel3;
	private JComboBox cb_rkck;
	private JComboBox cb_xsms;
	private JCheckBox cb_audio;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JComboBox cb_zjkz;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JButton btn_Net_RegOffLineClient;
	private JButton btn_Net_RegOffLineImageRecvEx;
	private JButton btn_Net_GetBlackWhiteListAsCSV;
	private JButton btn_Net_QueryWLFuzzyMatchMode;
	private JButton btn_Net_QueryWhiteListMode;
	private JButton btn_save;
	private ButtonGroup buttonGroup1;
	private JLabel jLabel7;
	private JTextField text_content;
	private JLabel jLabel6;
	private JComboBox cb_mode;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JTextField text_time;
	private JLabel jLabel3;
	private JRadioButton rb_xhp;
	private JRadioButton rb_shp;
	private JButton btn_Net_DeleteAllBlackWhiteList;
	private JButton btn_Net_ReadTwoEncpyption;
	private JButton btn_Net_ReadRS485Data;
	private JButton btn_Net_ReadGPIOState;
	private JButton btn_btn_Net_LedSetup;
	private JTextField text_count;
	private JLabel jLabel15;
	private JButton btn_Net_BlackWhiteListSend;
	private JButton btn_Net_BlackWhiteListSetup;
	private JTextField text_endtime;
	private JLabel jLabel14;
	private JTextField text_starttime;
	private JLabel jLabel13;
	private JTextField text_plate;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JPanel jPanel4;
	private JButton btn_Net_ParkNumSetup;
	private JTextField text_kytcw;
	private JLabel jLabel10;
	private JButton btn_Net_ParkLedLightSetup;
	private JTextField text_ldfz;
	private JLabel jLabel9;
	private JComboBox cb_lddj;
	private JComboBox cb_ldms;
	private JLabel jLabel8;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public PlateDeviceView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(671, 454));
			this.setSize(671, 481);
			this.setBackground(new java.awt.Color(230,230,230));
			this.setLayout(null);
			this.setRequestFocusEnabled(false);
			{
				btn_Net_QueryGateAutoOpen = new JButton();
				this.add(getBtn_Net_QueryGateAutoOpen());
				btn_Net_QueryGateAutoOpen.setText("自动抬闸配置");
				btn_Net_QueryGateAutoOpen.setBounds(6, 6, 100, 30);
			}
			{
				btn_Net_QueryLedSetup = new JButton();
				this.add(getBtn_Net_QueryLedSetup());
				btn_Net_QueryLedSetup.setText("读显示屏");
				btn_Net_QueryLedSetup.setBounds(379, 42, 76, 30);
			}
			{
				btn_Net_QueryOffLinePayMode = new JButton();
				this.add(getBtn_Net_QueryOffLinePayMode());
				btn_Net_QueryOffLinePayMode.setText("脱机收费模式");
				btn_Net_QueryOffLinePayMode.setBounds(222, 6, 100, 30);
			}
			{
				btn_Net_QueryOffLinePayRule = new JButton();
				this.add(getBtn_Net_QueryOffLinePayRule());
				btn_Net_QueryOffLinePayRule.setText("脱机收费规则");
				btn_Net_QueryOffLinePayRule.setBounds(329, 6, 100, 30);
			}
			{
				btn_Net_Net_QueryParkLedLightSetup = new JButton();
				this.add(getBtn_Net_Net_QueryParkLedLightSetup());
				btn_Net_Net_QueryParkLedLightSetup.setText("LED亮度");
				btn_Net_Net_QueryParkLedLightSetup.setBounds(433, 6, 76, 30);
			}
			{
				btn_Net_ReadGPIOState = new JButton();
				this.add(getBtn_Net_ReadGPIOState());
				btn_Net_ReadGPIOState.setText("读GPIO状态");
				btn_Net_ReadGPIOState.setBounds(287, 42, 93, 30);
			}
			{
				btn_Net_ReadRS485Data = new JButton();
				this.add(getBtn_Net_ReadRS485Data());
				btn_Net_ReadRS485Data.setText("读RS485");
				btn_Net_ReadRS485Data.setBounds(6, 42, 78, 30);
			}
			{
				btn_Net_ReadTwoEncpyption = new JButton();
				this.add(getBtn_Net_ReadTwoEncpyption());
				btn_Net_ReadTwoEncpyption.setText("读用户私有数据");
				btn_Net_ReadTwoEncpyption.setBounds(109, 6, 112, 30);
			}
			{
				btn_Net_DeleteAllBlackWhiteList = new JButton();
				this.add(getBtn_Net_DeleteAllBlackWhiteList());
				btn_Net_DeleteAllBlackWhiteList.setText("清空黑白名单");
				btn_Net_DeleteAllBlackWhiteList.setBounds(84, 42, 100, 30);
			}
			{
				btn_Net_GetBlackWhiteList = new JButton();
				this.add(getBtn_Net_GetBlackWhiteList());
				btn_Net_GetBlackWhiteList.setText("导出黑白名单");
				btn_Net_GetBlackWhiteList.setBounds(185, 42, 100, 30);
			}
			{
				btn_Net_GetBlackWhiteListAsCSV = new JButton();
				this.add(getBtn_Net_GetBlackWhiteListAsCSV());
				btn_Net_GetBlackWhiteListAsCSV.setText("CVS格式导出黑白名单");
				btn_Net_GetBlackWhiteListAsCSV.setBounds(513, 6, 152, 30);
			}
			{
				btn_Net_QueryWhiteListMode = new JButton();
				this.add(getBtn_Net_QueryWhiteListMode());
				btn_Net_QueryWhiteListMode.setText("白名单工作模式");
				btn_Net_QueryWhiteListMode.setBounds(455, 42, 112, 30);
			}
			{
				btn_Net_QueryWLFuzzyMatchMode = new JButton();
				this.add(getBtn_Net_QueryWLFuzzyMatchMode());
				btn_Net_QueryWLFuzzyMatchMode.setText("模糊匹配模式");
				btn_Net_QueryWLFuzzyMatchMode.setBounds(567, 42, 100, 30);
			}
			{
				btn_Net_RegOffLineClient = new JButton();
				this.add(getBtn_Net_RegOffLineClient());
				btn_Net_RegOffLineClient.setText("绑定客户端");
				btn_Net_RegOffLineClient.setBounds(6, 76, 88, 30);
			}
			{
				btn_Net_RegOffLineImageRecvEx = new JButton();
				this.add(getBtn_Net_RegOffLineImageRecvEx());
				btn_Net_RegOffLineImageRecvEx.setText("脱机记录");
				btn_Net_RegOffLineImageRecvEx.setBounds(100, 76, 76, 30);
			}
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				this.add(getJPanel4());
				this.add(getBtn_Net_ParkNumSetup());
				this.add(getText_kytcw());
				this.add(getJLabel10());
				jPanel1.setBounds(6, 112, 659, 175);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("闸机控制：");
					jLabel1.setBounds(7, 7, 60, 18);
				}
				{
					ComboBoxModel cb_zjkzModel = 
							new DefaultComboBoxModel(
									new String[] { "开闸", "关闸"});
					cb_zjkz = new JComboBox();
					jPanel1.add(getCb_zjkz());
					cb_zjkz.setModel(cb_zjkzModel);
					cb_zjkz.setBounds(66, 2, 88, 28);
				}
				{
					jPanel2 = new JPanel();
					jPanel1.add(jPanel2);
					jPanel1.add(getJLabel8());
					jPanel1.add(getCb_lddj());
					jPanel1.add(getCb_ldms());
					jPanel1.add(getJLabel9());
					jPanel1.add(getText_ldfz());
					jPanel1.add(getBtn_Net_ParkLedLightSetup());
					jPanel2.setBounds(7, 33, 645, 101);
					jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					jPanel2.setLayout(null);
					{
						jLabel2 = new JLabel();
						jPanel2.add(jLabel2);
						jLabel2.setText("显示屏：");
						jLabel2.setBounds(6, 4, 48, 18);
					}
					{
						cb_audio = new JCheckBox();
						jPanel2.add(getCb_audio());
						cb_audio.setText("语音");
						cb_audio.setBounds(12, 28, 46, 18);
					}
					{
						ComboBoxModel cb_xsmsModel = 
								new DefaultComboBoxModel(
										new String[] { "闲时", "忙时" });
						cb_xsms = new JComboBox();
						jPanel2.add(getCb_xsms());
						cb_xsms.setModel(cb_xsmsModel);
						cb_xsms.setBounds(64, 25, 59, 28);
					}
					{
						ComboBoxModel cb_rkckModel = 
								new DefaultComboBoxModel(
										new String[] { "入口", "出口" });
						cb_rkck = new JComboBox();
						jPanel2.add(getCb_rkck());
						cb_rkck.setModel(cb_rkckModel);
						cb_rkck.setBounds(63, 62, 59, 28);
					}
					{
						jLabel7 = new JLabel();
						jPanel2.add(jLabel7);
						jLabel7.setText("流程：语音-闲时或忙时组合出口或入口-文本显示-上下行屏-时间间隔-模式-内容-保存（组合4次）-设置");
						jLabel7.setBounds(52, 4, 588, 18);
					}
					{
						jPanel3 = new JPanel();
						jPanel2.add(jPanel3);
						jPanel2.add(getBtn_btn_Net_LedSetup());
						jPanel3.setBounds(125, 27, 454, 69);
						jPanel3.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jPanel3.setLayout(null);
						{
							cb_showtext = new JCheckBox();
							jPanel3.add(getCb_showtext());
							cb_showtext.setText("显示文本");
							cb_showtext.setBounds(7, 7, 70, 18);
							cb_showtext.setSelected(true);
						}
						{
							rb_shp = new JRadioButton();
							jPanel3.add(getRb_shp());
							rb_shp.setText("上行屏");
							rb_shp.setBounds(266, 7, 58, 18);
							rb_shp.setSelected(true);
						}
						{
							rb_xhp = new JRadioButton();
							jPanel3.add(getRb_xhp());
							rb_xhp.setText("下行屏");
							rb_xhp.setBounds(332, 7, 58, 18);
						}
						{
							jLabel3 = new JLabel();
							jPanel3.add(jLabel3);
							jLabel3.setText("时间间隔：");
							jLabel3.setBounds(12, 37, 60, 18);
						}
						{
							text_time = new JTextField();
							jPanel3.add(getText_time());
							text_time.setText("4");
							text_time.setBounds(74, 31, 34, 30);
						}
						{
							jLabel4 = new JLabel();
							jPanel3.add(jLabel4);
							jLabel4.setText("秒");
							jLabel4.setBounds(109, 37, 12, 18);
						}
						{
							jLabel5 = new JLabel();
							jPanel3.add(jLabel5);
							jLabel5.setText("模式：");
							jLabel5.setBounds(92, 7, 36, 18);
						}
						{
							ComboBoxModel cb_modeModel = 
									new DefaultComboBoxModel(
											new String[] { "车牌", "欢迎语", "停车付费","停车时间","月租有效期","时间","剩余车位","自定义"});
							cb_mode = new JComboBox();
							jPanel3.add(getCb_mode());
							cb_mode.setModel(cb_modeModel);
							cb_mode.setBounds(128, 2, 131, 28);
							cb_mode.setSelectedIndex(5);
						}
						{
							jLabel6 = new JLabel();
							jPanel3.add(jLabel6);
							jLabel6.setText("内容：");
							jLabel6.setBounds(129, 37, 36, 18);
						}
						{
							text_content = new JTextField();
							jPanel3.add(getText_content());
							text_content.setText("欢迎光临");
							text_content.setBounds(165, 31, 228, 30);
						}
						{
							btn_save = new JButton();
							jPanel3.add(getBtn_save());
							btn_save.setText("保存");
							btn_save.setBounds(397, 4, 55, 32);
						}
						{
							getButtonGroup1();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JButton getBtn_Net_QueryGateAutoOpen() {
		return btn_Net_QueryGateAutoOpen;
	}
	
	public JButton getBtn_Net_QueryLedSetup() {
		return btn_Net_QueryLedSetup;
	}
	
	public JButton getBtn_Net_QueryOffLinePayMode() {
		return btn_Net_QueryOffLinePayMode;
	}
	
	public JButton getBtn_Net_QueryOffLinePayRule() {
		return btn_Net_QueryOffLinePayRule;
	}
	
	public JButton getBtn_Net_Net_QueryParkLedLightSetup() {
		return btn_Net_Net_QueryParkLedLightSetup;
	}
	
	public JButton getBtn_Net_ReadGPIOState() {
		return btn_Net_ReadGPIOState;
	}
	
	public JButton getBtn_Net_ReadRS485Data() {
		return btn_Net_ReadRS485Data;
	}
	
	public JButton getBtn_Net_ReadTwoEncpyption() {
		return btn_Net_ReadTwoEncpyption;
	}
	
	public JButton getBtn_Net_DeleteAllBlackWhiteList() {
		return btn_Net_DeleteAllBlackWhiteList;
	}
	
	public JButton getBtn_Net_GetBlackWhiteList() {
		return btn_Net_GetBlackWhiteList;
	}
	
	public JButton getBtn_Net_GetBlackWhiteListAsCSV() {
		return btn_Net_GetBlackWhiteListAsCSV;
	}
	
	public JButton getBtn_Net_QueryWhiteListMode() {
		return btn_Net_QueryWhiteListMode;
	}
	
	public JButton getBtn_Net_QueryWLFuzzyMatchMode() {
		return btn_Net_QueryWLFuzzyMatchMode;
	}
	
	public JButton getBtn_Net_RegOffLineClient() {
		return btn_Net_RegOffLineClient;
	}
	
	public JButton getBtn_Net_RegOffLineImageRecvEx() {
		return btn_Net_RegOffLineImageRecvEx;
	}
	
	public JComboBox getCb_zjkz() {
		return cb_zjkz;
	}
	
	public JCheckBox getCb_audio() {
		return cb_audio;
	}
	
	public JComboBox getCb_xsms() {
		return cb_xsms;
	}
	
	public JComboBox getCb_rkck() {
		return cb_rkck;
	}
	
	public JCheckBox getCb_showtext() {
		return cb_showtext;
	}
	
	public JRadioButton getRb_shp() {
		return rb_shp;
	}
	
	public JRadioButton getRb_xhp() {
		return rb_xhp;
	}
	
	public JTextField getText_time() {
		return text_time;
	}
	
	public JComboBox getCb_mode() {
		return cb_mode;
	}
	
	public JTextField getText_content() {
		return text_content;
	}
	
	public JButton getBtn_save() {
		return btn_save;
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
			buttonGroup1.add(rb_shp);
			buttonGroup1.add(rb_xhp);
		}
		return buttonGroup1;
	}
	
	public JButton getBtn_btn_Net_LedSetup() {
		if(btn_btn_Net_LedSetup == null) {
			btn_btn_Net_LedSetup = new JButton();
			btn_btn_Net_LedSetup.setText("设置");
			btn_btn_Net_LedSetup.setBounds(585, 24, 53, 38);
		}
		return btn_btn_Net_LedSetup;
	}
	
	private JLabel getJLabel8() {
		if(jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("显示屏亮度：");
			jLabel8.setBounds(7, 142, 72, 18);
		}
		return jLabel8;
	}
	
	public JComboBox getCb_ldms() {
		if(cb_ldms == null) {
			ComboBoxModel cb_ldmsModel = 
					new DefaultComboBoxModel(
							new String[] { "关闭", "常亮", "智能" });
			cb_ldms = new JComboBox();
			cb_ldms.setModel(cb_ldmsModel);
			cb_ldms.setBounds(79, 138, 75, 28);
		}
		return cb_ldms;
	}
	
	public JComboBox getCb_lddj() {
		if(cb_lddj == null) {
			ComboBoxModel cb_lddjModel = 
					new DefaultComboBoxModel(
							new String[] { "等级0（关闭）", "等级1" , "等级2" , "等级3" , "等级4" , "等级5" , "等级6" , "等级7"});
			cb_lddj = new JComboBox();
			cb_lddj.setModel(cb_lddjModel);
			cb_lddj.setBounds(160, 138, 116, 28);
		}
		return cb_lddj;
	}
	
	private JLabel getJLabel9() {
		if(jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("亮度阀值(1~100)：");
			jLabel9.setBounds(282, 143, 105, 18);
		}
		return jLabel9;
	}
	
	public JTextField getText_ldfz() {
		if(text_ldfz == null) {
			text_ldfz = new JTextField();
			text_ldfz.setText("50");
			text_ldfz.setBounds(385, 138, 26, 30);
		}
		return text_ldfz;
	}
	
	public JButton getBtn_Net_ParkLedLightSetup() {
		if(btn_Net_ParkLedLightSetup == null) {
			btn_Net_ParkLedLightSetup = new JButton();
			btn_Net_ParkLedLightSetup.setText("设置");
			btn_Net_ParkLedLightSetup.setBounds(417, 138, 52, 30);
		}
		return btn_Net_ParkLedLightSetup;
	}
	
	private JLabel getJLabel10() {
		if(jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("空余停车位：");
			jLabel10.setBounds(188, 82, 72, 18);
		}
		return jLabel10;
	}
	
	public JTextField getText_kytcw() {
		if(text_kytcw == null) {
			text_kytcw = new JTextField();
			text_kytcw.setText("100");
			text_kytcw.setBounds(258, 76, 37, 30);
		}
		return text_kytcw;
	}
	
	public JButton getBtn_Net_ParkNumSetup() {
		if(btn_Net_ParkNumSetup == null) {
			btn_Net_ParkNumSetup = new JButton();
			btn_Net_ParkNumSetup.setText("设置");
			btn_Net_ParkNumSetup.setBounds(295, 76, 52, 30);
		}
		return btn_Net_ParkNumSetup;
	}
	
	private JPanel getJPanel4() {
		if(jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBounds(6, 293, 659, 94);
			jPanel4.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			jPanel4.setLayout(null);
			jPanel4.add(getJLabel11());
			jPanel4.add(getJLabel12());
			jPanel4.add(getText_plate());
			jPanel4.add(getJLabel13());
			jPanel4.add(getText_starttime());
			jPanel4.add(getJLabel14());
			jPanel4.add(getText_endtime());
			jPanel4.add(getBtn_Net_BlackWhiteListSetup());
			jPanel4.add(getBtn_Net_BlackWhiteListSend());
			jPanel4.add(getJLabel15());
			jPanel4.add(getText_count());
		}
		return jPanel4;
	}
	
	public JLabel getJLabel11() {
		if(jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("黑白名单：");
			jLabel11.setBounds(5, 3, 60, 18);
		}
		return jLabel11;
	}
	
	private JLabel getJLabel12() {
		if(jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("车牌号：");
			jLabel12.setBounds(7, 27, 48, 18);
		}
		return jLabel12;
	}
	
	public JTextField getText_plate() {
		if(text_plate == null) {
			text_plate = new JTextField();
			text_plate.setText("粤B12345");
			text_plate.setBounds(52, 21, 67, 30);
		}
		return text_plate;
	}
	
	private JLabel getJLabel13() {
		if(jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("有效开始T：");
			jLabel13.setBounds(125, 27, 68, 18);
		}
		return jLabel13;
	}
	
	public JTextField getText_starttime() {
		if(text_starttime == null) {
			text_starttime = new JTextField();
			text_starttime.setText("20161227101010");
			text_starttime.setBounds(187, 21, 110, 30);
		}
		return text_starttime;
	}
	
	private JLabel getJLabel14() {
		if(jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("有效结束T：");
			jLabel14.setBounds(303, 27, 70, 18);
		}
		return jLabel14;
	}
	
	public JTextField getText_endtime() {
		if(text_endtime == null) {
			text_endtime = new JTextField();
			text_endtime.setText("20170101101010");
			text_endtime.setBounds(368, 21, 110, 30);
		}
		return text_endtime;
	}
	
	public JButton getBtn_Net_BlackWhiteListSetup() {
		if(btn_Net_BlackWhiteListSetup == null) {
			btn_Net_BlackWhiteListSetup = new JButton();
			btn_Net_BlackWhiteListSetup.setText("本地增加");
			btn_Net_BlackWhiteListSetup.setBounds(472, 57, 85, 30);
		}
		return btn_Net_BlackWhiteListSetup;
	}
	
	public JButton getBtn_Net_BlackWhiteListSend() {
		if(btn_Net_BlackWhiteListSend == null) {
			btn_Net_BlackWhiteListSend = new JButton();
			btn_Net_BlackWhiteListSend.setText("发送至相机");
			btn_Net_BlackWhiteListSend.setBounds(563, 57, 89, 30);
		}
		return btn_Net_BlackWhiteListSend;
	}
	
	private JLabel getJLabel15() {
		if(jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("增加条数：");
			jLabel15.setBounds(490, 27, 63, 18);
		}
		return jLabel15;
	}
	
	public JTextField getText_count() {
		if(text_count == null) {
			text_count = new JTextField();
			text_count.setText("1");
			text_count.setEditable(false);
			text_count.setBounds(553, 21, 28, 30);
		}
		return text_count;
	}

}
