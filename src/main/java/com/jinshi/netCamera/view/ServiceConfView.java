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
public class ServiceConfView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_Net_QueryViceVideoCodeSetup;
	private JButton btn_Net_QueryDynamicCodeSetup;
	private JButton btn_Net_QueryVehicleVAFunSetup;
	private JButton btn_Net_QueryPicOsdSetup;
	private JButton btn_Net_StorageSetup;
	private JTextField text_sslx;
	private JLabel jLabel26;
	private JTextField text_ylsc;
	private JLabel jLabel25;
	private JTextField text_wflxsc;
	private JLabel jLabel24;
	private JCheckBox cb_sslxkq;
	private JLabel jLabel23;
	private JCheckBox cb_wflxkq;
	private JLabel jLabel22;
	private JCheckBox cb_wflx;
	private JLabel jLabel21;
	private JCheckBox cb_wftp;
	private JCheckBox cb_kktp;
	private JCheckBox cb_bdcc;
	private JLabel jLabel20;
	private JLabel jLabel19;
	private JRadioButton rb_pmcl;
	private JRadioButton rb_pmcl1;
	private JLabel jLabel18;
	private JRadioButton rb_ccjg2;
	private JRadioButton rb_ccjg1;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JLabel jLabel15;
	private JPanel jpanel32;
	private JComboBox cb_jcms;
	private JLabel jLabel14;
	private JTextField text_province;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private ButtonGroup buttonGroup3;
	private ButtonGroup buttonGroup4;
	private ButtonGroup buttonGroup5;
	private ButtonGroup buttonGroup6;
	private ButtonGroup buttonGroup7;
	private ButtonGroup buttonGroup8;
	private ButtonGroup buttonGroup9;
	private ButtonGroup buttonGroup10;
	private JTextField text_maxpix;
	private JLabel jLabel13;
	private JTextField text_minpix;
	private JLabel jLabel12;
	private JRadioButton rb_cptx;
	private JRadioButton rb_cptx1;
	private JLabel jLabel11;
	private JRadioButton rb_wjcp2;
	private JRadioButton rb_wjcp;
	private JLabel jLabel10;
	private JTextField text_zpjg;
	private JLabel jLabel9;
	private JRadioButton rb_hpsb2;
	private JRadioButton rb_hpsb;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JRadioButton rb_cpfx2;
	private JRadioButton rb_cpfx;
	private JLabel jLabel6;
	private JComboBox cb_second;
	private JLabel jLabel5;
	private JButton btn_Net_QueryStorageSetup;
	private JButton btn_Net_QueryHardInfo;
	private JLabel jLabel4;
	private JRadioButton rb_cb2;
	private JRadioButton rb_cb1;
	private JRadioButton rb_csys2;
	private JRadioButton rb_csys;
	private JLabel jLabel3;
	private JRadioButton rb_sbqy2;
	private JRadioButton rb_sbqy1;
	private JLabel jLabel2;
	private JRadioButton rb_sscp2;
	private JRadioButton rb_sscp1;
	private JLabel jLabel1;
	private JButton btn_Net_VehicleVAFunSetup;
	private JPanel jPanel1;
	private JButton btn_Net_QueryVideoParaSetup;
	private JButton btn_Net_QueryVideoSubtitleSetup;
	private JButton btn_Net_QueryVideoDetectSetup;
	private JButton btn_Net_QueryLoopDetectSetup;
	private JButton btn_Net_QueryDetectModeSetup;
	private JLabel jLabel485;
	private JComboBox cb_rs485com;
	private JTextField text_rs485;
	private JButton btn_Send_Rs485AndHex;
	private String[] cp_2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private String[] Rs485cp_2 = {"端口1","端口2"};

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
		
	public ServiceConfView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(671, 481));
			this.setSize(671, 481);
			this.setLayout(null);
			this.setBackground(new java.awt.Color(230,230,230));
			{
				btn_Net_QueryViceVideoCodeSetup = new JButton();
				this.add(getBtn_Net_QueryViceVideoCodeSetup());
				btn_Net_QueryViceVideoCodeSetup.setText("相机辅视编码");
				btn_Net_QueryViceVideoCodeSetup.setBounds(6, 6, 100, 30);
			}
			{
				btn_Net_QueryDynamicCodeSetup = new JButton();
				this.add(getBtn_Net_QueryDynamicCodeSetup());
				btn_Net_QueryDynamicCodeSetup.setText("相机动态编码参数");
				btn_Net_QueryDynamicCodeSetup.setBounds(111, 6, 124, 30);
			}
			{
				btn_Net_QueryDetectModeSetup = new JButton();
				this.add(getBtn_Net_QueryDetectModeSetup());
				btn_Net_QueryDetectModeSetup.setText("相机检测模式");
				btn_Net_QueryDetectModeSetup.setBounds(241, 6, 100, 30);
			}
			{
				btn_Net_QueryLoopDetectSetup = new JButton();
				this.add(getBtn_Net_QueryLoopDetectSetup());
				btn_Net_QueryLoopDetectSetup.setText("线圈检测模式");
				btn_Net_QueryLoopDetectSetup.setBounds(347, 6, 100, 30);
			}
			{
				btn_Net_QueryVideoDetectSetup = new JButton();
				this.add(getBtn_Net_QueryVideoDetectSetup());
				btn_Net_QueryVideoDetectSetup.setText("视频检测模式");
				btn_Net_QueryVideoDetectSetup.setBounds(453, 6, 100, 30);
			}
			{
				btn_Net_QueryVehicleVAFunSetup = new JButton();
				this.add(getBtn_Net_QueryVehicleVAFunSetup());
				btn_Net_QueryVehicleVAFunSetup.setText("车牌识别配置");
				btn_Net_QueryVehicleVAFunSetup.setBounds(559, 6, 100, 30);
			}
			{
				btn_Net_QueryPicOsdSetup = new JButton();
				this.add(getBtn_Net_QueryPicOsdSetup());
				btn_Net_QueryPicOsdSetup.setText("图片字幕配置");
				btn_Net_QueryPicOsdSetup.setBounds(6, 42, 100, 30);
			}
			{
				btn_Net_QueryVideoSubtitleSetup = new JButton();
				this.add(getBtn_Net_QueryVideoSubtitleSetup());
				btn_Net_QueryVideoSubtitleSetup.setText("视频字幕");
				btn_Net_QueryVideoSubtitleSetup.setBounds(112, 42, 76, 30);
			}
			{
				btn_Net_QueryVideoParaSetup = new JButton();
				this.add(getBtn_Net_QueryVideoParaSetup());
				btn_Net_QueryVideoParaSetup.setText("视频参数");
				btn_Net_QueryVideoParaSetup.setBounds(194, 42, 76, 30);
			}
			{
				btn_Net_QueryStorageSetup = new JButton();
				this.add(getBtn_Net_QueryStorageSetup());
				btn_Net_QueryStorageSetup.setText("存储策略");
				btn_Net_QueryStorageSetup.setBounds(276, 42, 76, 30);
			}
			{
				btn_Net_QueryHardInfo = new JButton();
				this.add(getBtn_Net_QueryHardInfo());
				btn_Net_QueryHardInfo.setText("硬盘信息");
				btn_Net_QueryHardInfo.setBounds(358, 42, 76, 30);
			}
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				this.add(getJLabel14());
				this.add(getCb_jcms());
				this.add(getJpanel32());
				jPanel1.setBounds(6, 78, 659, 131);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setLayout(null);
				{
					btn_Net_VehicleVAFunSetup = new JButton();
					jPanel1.add(getBtn_Net_VehicleVAFunSetup());
					btn_Net_VehicleVAFunSetup.setText("车牌识别配置");
					btn_Net_VehicleVAFunSetup.setBounds(553, 97, 100, 30);
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("实时车牌：");
					jLabel1.setBounds(7, 7, 60, 18);
				}
				{
					rb_sscp1 = new JRadioButton();
					jPanel1.add(getRb_sscp1());
					rb_sscp1.setText("不显示");
					rb_sscp1.setBounds(66, 7, 62, 18);
					rb_sscp1.setSelected(true);
				}
				{
					rb_sscp2 = new JRadioButton();
					jPanel1.add(getRb_sscp2());
					rb_sscp2.setText("显示");
					rb_sscp2.setBounds(128, 7, 46, 18);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("识别区域：");
					jLabel2.setBounds(186, 7, 60, 18);
				}
				{
					rb_sbqy1 = new JRadioButton();
					jPanel1.add(getRb_sbqy1());
					rb_sbqy1.setText("关闭");
					rb_sbqy1.setBounds(244, 7, 46, 18);
				}
				{
					rb_sbqy2 = new JRadioButton();
					jPanel1.add(getRb_sbqy2());
					rb_sbqy2.setText("打开");
					rb_sbqy2.setBounds(296, 7, 46, 18);
					rb_sbqy2.setSelected(true);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("车身颜色：");
					jLabel3.setBounds(352, 7, 61, 18);
				}
				{
					rb_csys = new JRadioButton();
					jPanel1.add(getRb_csys());
					rb_csys.setText("不识别");
					rb_csys.setBounds(408, 7, 58, 18);
					rb_csys.setSelected(true);
				}
				{
					rb_csys2 = new JRadioButton();
					jPanel1.add(getRb_csys2());
					rb_csys2.setText("识别");
					rb_csys2.setBounds(472, 7, 46, 18);
				}}
			{
				jLabel4 = new JLabel();
				jPanel1.add(jLabel4);
				jLabel4.setText("车标：");
				jLabel4.setBounds(7, 37, 36, 18);
			}
			{
				rb_cb1 = new JRadioButton();
				jPanel1.add(getRb_cb1());
				rb_cb1.setText("不识别");
				rb_cb1.setBounds(39, 37, 58, 18);
				rb_cb1.setSelected(true);
			}
			{
				rb_cb2 = new JRadioButton();
				jPanel1.add(getRb_cb2());
				rb_cb2.setText("识别");
				rb_cb2.setBounds(97, 37, 46, 18);
			}
			{
				jLabel5 = new JLabel();
				jPanel1.add(jLabel5);
				jLabel5.setText("车牌第二个字符：");
				jLabel5.setBounds(7, 67, 96, 18);
			}
			{
				ComboBoxModel cb_secondModel = 
						new DefaultComboBoxModel(
								cp_2);
				cb_second = new JComboBox();
				jPanel1.add(getCb_second());
				cb_second.setModel(cb_secondModel);
				cb_second.setBounds(103, 62, 58, 28);
			}
			{
				jLabel6 = new JLabel();
				jPanel1.add(jLabel6);
				jLabel6.setText("车牌方向：");
				jLabel6.setBounds(155, 37, 60, 18);
			}
			{
				rb_cpfx = new JRadioButton();
				jPanel1.add(getRb_cpfx());
				rb_cpfx.setText("车头车牌");
				rb_cpfx.setBounds(213, 37, 70, 18);
				rb_cpfx.setSelected(true);
			}
			{
				rb_cpfx2 = new JRadioButton();
				jPanel1.add(getRb_cpfx2());
				rb_cpfx2.setText("车尾车牌");
				rb_cpfx2.setBounds(289, 37, 70, 18);
			}
			{
				jLabel7 = new JLabel();
				jPanel1.add(jLabel7);
				jLabel7.setText("默认省份：");
				jLabel7.setBounds(173, 67, 60, 18);
			}
			{
				text_province = new JTextField();
				jPanel1.add(getText_province());
				text_province.setText("粤");
				text_province.setBounds(230, 61, 33, 30);
			}
			{
				jLabel8 = new JLabel();
				jPanel1.add(jLabel8);
				jLabel8.setText("双层黄牌识别：");
				jLabel8.setBounds(371, 37, 84, 18);
			}
			{
				rb_hpsb = new JRadioButton();
				jPanel1.add(getRb_hpsb());
				rb_hpsb.setText("不识别");
				rb_hpsb.setBounds(452, 37, 58, 18);
			}
			{
				rb_hpsb2 = new JRadioButton();
				jPanel1.add(getJRadioButton1());
				rb_hpsb2.setText("识别");
				rb_hpsb2.setBounds(513, 37, 46, 18);
				rb_hpsb2.setSelected(true);
			}
			{
				jLabel9 = new JLabel();
				jPanel1.add(jLabel9);
				jLabel9.setText("抓拍间隔(s)：");
				jLabel9.setBounds(275, 67, 75, 18);
			}
			{
				text_zpjg = new JTextField();
				jPanel1.add(getText_zpjg());
				text_zpjg.setText("8");
				text_zpjg.setBounds(348, 61, 31, 30);
			}
			{
				jLabel10 = new JLabel();
				jPanel1.add(getJLabel10());
				jLabel10.setText("武警车牌汉字：");
				jLabel10.setBounds(385, 67, 84, 18);
			}
			{
				rb_wjcp = new JRadioButton();
				jPanel1.add(getRb_wjcp());
				rb_wjcp.setText("不输出");
				rb_wjcp.setBounds(465, 67, 58, 18);
			}
			{
				rb_wjcp2 = new JRadioButton();
				jPanel1.add(getRb_wjcp2());
				rb_wjcp2.setText("输出");
				rb_wjcp2.setBounds(529, 67, 46, 18);
				rb_wjcp2.setSelected(true);
			}
			{
				jLabel11 = new JLabel();
				jPanel1.add(getJLabel11());
				jLabel11.setText("车牌特写：");
				jLabel11.setBounds(7, 97, 60, 18);
			}
			{
				rb_cptx1 = new JRadioButton();
				jPanel1.add(getRb_cptx1());
				rb_cptx1.setText("不开启");
				rb_cptx1.setBounds(67, 97, 58, 18);
				rb_cptx1.setSelected(true);
			}
			{
				rb_cptx = new JRadioButton();
				jPanel1.add(getRb_cptx());
				rb_cptx.setText("开启");
				rb_cptx.setBounds(131, 97, 46, 18);
			}
			{
				jLabel12 = new JLabel();
				jPanel1.add(getJLabel12());
				jLabel12.setText("最小宽度(pix)：");
				jLabel12.setBounds(196, 97, 87, 18);
			}
			{
				text_minpix = new JTextField();
				jPanel1.add(getText_minpix());
				text_minpix.setText("100");
				text_minpix.setBounds(275, 91, 41, 30);
			}
			{
				jLabel13 = new JLabel();
				jPanel1.add(jLabel13);
				jLabel13.setText("最大宽度：");
				jLabel13.setBounds(329, 97, 60, 18);
			}
			{
				text_maxpix = new JTextField();
				jPanel1.add(getText_maxpix());
				text_maxpix.setText("300");
				text_maxpix.setBounds(388, 91, 38, 30);
			}
			{
				jLabel485 = new JLabel();
				this.add(getJLabel485());
				jLabel485.setText("485通信");
				jLabel485.setBounds(7, 355, 50, 18);
			}
			{
				ComboBoxModel cb_rs485comModel = 
						new DefaultComboBoxModel(
								Rs485cp_2);
				cb_rs485com = new JComboBox();
				this.add(getCb_rs485com());
				cb_rs485com.setModel(cb_rs485comModel);
				cb_rs485com.setBounds(57,350,70,28);
				
			}
			{
				text_rs485 = new JTextField();
				this.add(getText_rs485());
				text_rs485.setBounds(135,350,150,28);
			}
			{
				btn_Send_Rs485AndHex = new JButton();
				this.add(getBtn_Send_Rs485AndHex());
				btn_Send_Rs485AndHex.setText("发送485数据");
				btn_Send_Rs485AndHex.setBounds(290,350,100,28);
			}
			{
				getButtonGroup1();
				getButtonGroup2();
				getButtonGroup3();
				getButtonGroup4();
				getButtonGroup5();
				getButtonGroup6();
				getButtonGroup7();
				getButtonGroup8();
				getButtonGroup9();
				getButtonGroup10();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JButton getBtn_Send_Rs485AndHex()
	{
		return btn_Send_Rs485AndHex;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getCb_rs485com()
	{
		return cb_rs485com;
	}
	public JTextField getText_rs485()
	{
		return text_rs485;
	}
	public JButton getBtn_Net_QueryViceVideoCodeSetup() {
		return btn_Net_QueryViceVideoCodeSetup;
	}
	
	public JButton getBtn_Net_QueryDynamicCodeSetup() {
		return btn_Net_QueryDynamicCodeSetup;
	}
	
	public JButton getBtn_Net_QueryDetectModeSetup() {
		return btn_Net_QueryDetectModeSetup;
	}
	
	public JButton getBtn_Net_QueryLoopDetectSetup() {
		return btn_Net_QueryLoopDetectSetup;
	}
	
	public JButton getBtn_Net_QueryVideoDetectSetup() {
		return btn_Net_QueryVideoDetectSetup;
	}
	
	public JButton getBtn_Net_QueryVehicleVAFunSetup() {
		return btn_Net_QueryVehicleVAFunSetup;
	}
	
	public JButton getBtn_Net_QueryPicOsdSetup() {
		return btn_Net_QueryPicOsdSetup;
	}
	
	public JButton getBtn_Net_QueryVideoSubtitleSetup() {
		return btn_Net_QueryVideoSubtitleSetup;
	}
	
	public JButton getBtn_Net_QueryVideoParaSetup() {
		return btn_Net_QueryVideoParaSetup;
	}
	
	public JButton getBtn_Net_QueryStorageSetup() {
		return btn_Net_QueryStorageSetup;
	}
	
	public JButton getBtn_Net_QueryHardInfo() {
		return btn_Net_QueryHardInfo;
	}
	
	public JButton getBtn_Net_VehicleVAFunSetup() {
		return btn_Net_VehicleVAFunSetup;
	}
	
	public JRadioButton getRb_sscp1() {
		return rb_sscp1;
	}
	
	public JRadioButton getRb_sscp2() {
		return rb_sscp2;
	}
	
	public JRadioButton getRb_sbqy1() {
		return rb_sbqy1;
	}
	
	public JRadioButton getRb_sbqy2() {
		return rb_sbqy2;
	}
	
	public JRadioButton getRb_csys() {
		return rb_csys;
	}
	
	public JRadioButton getRb_csys2() {
		return rb_csys2;
	}
	
	public JRadioButton getRb_cb1() {
		return rb_cb1;
	}
	
	public JRadioButton getRb_cb2() {
		return rb_cb2;
	}
	
	public JComboBox getCb_second() {
		return cb_second;
	}
	
	public JRadioButton getRb_cpfx() {
		return rb_cpfx;
	}
	
	public JRadioButton getRb_cpfx2() {
		return rb_cpfx2;
	}
	
	public JTextField getText_province() {
		return text_province;
	}
	
	public JRadioButton getRb_hpsb() {
		return rb_hpsb;
	}
	
	public JRadioButton getJRadioButton1() {
		return rb_hpsb2;
	}
	
	public JTextField getText_zpjg() {
		return text_zpjg;
	}
	
	public JLabel getJLabel10() {
		return jLabel10;
	}
	
	public JRadioButton getRb_wjcp() {
		return rb_wjcp;
	}
	
	public JRadioButton getRb_wjcp2() {
		return rb_wjcp2;
	}
	
	public JLabel getJLabel11() {
		return jLabel11;
	}
	
	public JRadioButton getRb_cptx1() {
		return rb_cptx1;
	}
	
	public JRadioButton getRb_cptx() {
		return rb_cptx;
	}
	
	public JLabel getJLabel12() {
		return jLabel12;
	}
	public JLabel getJLabel485() {
		return jLabel485;
	}
	
	public JTextField getText_minpix() {
		return text_minpix;
	}
	
	public JTextField getText_maxpix() {
		return text_maxpix;
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
			buttonGroup1.add(rb_wjcp);
			buttonGroup1.add(rb_wjcp2);
		}
		return buttonGroup1;
	}
	private ButtonGroup getButtonGroup2() {
		if(buttonGroup2 == null) {
			buttonGroup2 = new ButtonGroup();
			buttonGroup2.add(rb_sscp1);
			buttonGroup2.add(rb_sscp2);
		}
		return buttonGroup2;
	}
	private ButtonGroup getButtonGroup3() {
		if(buttonGroup3 == null) {
			buttonGroup3 = new ButtonGroup();
			buttonGroup3.add(rb_sbqy1);
			buttonGroup3.add(rb_sbqy2);
		}
		return buttonGroup3;
	}
	private ButtonGroup getButtonGroup4() {
		if(buttonGroup4 == null) {
			buttonGroup4 = new ButtonGroup();
			buttonGroup4.add(rb_csys);
			buttonGroup4.add(rb_csys2);
		}
		return buttonGroup4;
	}
	private ButtonGroup getButtonGroup5() {
		if(buttonGroup5 == null) {
			buttonGroup5 = new ButtonGroup();
			buttonGroup5.add(rb_cptx);
			buttonGroup5.add(rb_cptx1);
		}
		return buttonGroup5;
	}
	private ButtonGroup getButtonGroup6() {
		if(buttonGroup6 == null) {
			buttonGroup6 = new ButtonGroup();
			buttonGroup6.add(rb_cpfx);
			buttonGroup6.add(rb_cpfx2);
		}
		return buttonGroup6;
	}
	private ButtonGroup getButtonGroup7() {
		if(buttonGroup7 == null) {
			buttonGroup7 = new ButtonGroup();
			buttonGroup7.add(rb_cb1);
			buttonGroup7.add(rb_cb2);
		}
		return buttonGroup7;
	}
	private ButtonGroup getButtonGroup8() {
		if(buttonGroup8 == null) {
			buttonGroup8 = new ButtonGroup();
			buttonGroup8.add(rb_hpsb);
			buttonGroup8.add(rb_hpsb2);
		}
		return buttonGroup8;
	}
	private ButtonGroup getButtonGroup9() {
		if(buttonGroup9 == null) {
			buttonGroup9 = new ButtonGroup();
			buttonGroup9.add(rb_ccjg1);
			buttonGroup9.add(rb_ccjg2);
		}
		return buttonGroup9;
	}
	private ButtonGroup getButtonGroup10() {
		if(buttonGroup10 == null) {
			buttonGroup10 = new ButtonGroup();
			buttonGroup10.add(rb_pmcl);
			buttonGroup10.add(rb_pmcl1);
		}
		return buttonGroup10;
	}
	
	private JLabel getJLabel14() {
		if(jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("相机检测模式：");
			jLabel14.setBounds(6, 216, 84, 18);
		}
		return jLabel14;
	}
	
	public JComboBox getCb_jcms() {
		if(cb_jcms == null) {
			ComboBoxModel cb_jcmsModel = 
					new DefaultComboBoxModel(
							new String[] { "线圈检测", "雷达检测","视频检测","线圈+视频","雷达+视频" });
			cb_jcms = new JComboBox();
			cb_jcms.setModel(cb_jcmsModel);
			cb_jcms.setBounds(84, 211, 110, 28);
		}
		return cb_jcms;
	}
	
	private JPanel getJpanel32() {
		if(jpanel32 == null) {
			jpanel32 = new JPanel();
			jpanel32.setBounds(6, 242, 659, 103);
			jpanel32.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			jpanel32.setLayout(null);
			jpanel32.add(getJLabel15());
			jpanel32.add(getJLabel16());
			jpanel32.add(getJLabel17());
			jpanel32.add(getRb_ccjg1());
			jpanel32.add(getRb_ccjg2());
			jpanel32.add(getJLabel18());
			jpanel32.add(getRb_pmcl1());
			jpanel32.add(getRb_pmcl());
			jpanel32.add(getJLabel19());
			jpanel32.add(getJLabel20());
			jpanel32.add(getCb_bdcc());
			jpanel32.add(getCb_kktp());
			jpanel32.add(getCb_wftp());
			jpanel32.add(getJLabel21());
			jpanel32.add(getCb_wflx());
			jpanel32.add(getJLabel22());
			jpanel32.add(getCb_wflxkq());
			jpanel32.add(getJLabel23());
			jpanel32.add(getCb_sslxkq());
			jpanel32.add(getJLabel24());
			jpanel32.add(getText_wflxsc());
			jpanel32.add(getJLabel25());
			jpanel32.add(getText_ylsc());
			jpanel32.add(getJLabel26());
			jpanel32.add(getText_sslx());
			jpanel32.add(getBtn_Net_StorageSetup());
		}
		return jpanel32;
	}
	
	private JLabel getJLabel15() {
		if(jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("存储信息配置：");
			jLabel15.setBounds(5, 3, 84, 18);
		}
		return jLabel15;
	}
	
	private JLabel getJLabel16() {
		if(jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("本地存储：");
			jLabel16.setBounds(16, 21, 60, 18);
		}
		return jLabel16;
	}

	private JLabel getJLabel17() {
		if(jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("存储结构：");
			jLabel17.setBounds(131, 21, 60, 18);
		}
		return jLabel17;
	}
	
	public JRadioButton getRb_ccjg1() {
		if(rb_ccjg1 == null) {
			rb_ccjg1 = new JRadioButton();
			rb_ccjg1.setText("设备编号+日期+时间");
			rb_ccjg1.setBounds(189, 21, 132, 18);
			rb_ccjg1.setSelected(true);
		}
		return rb_ccjg1;
	}
	
	public JRadioButton getRb_ccjg2() {
		if(rb_ccjg2 == null) {
			rb_ccjg2 = new JRadioButton();
			rb_ccjg2.setText("设备编号+方向+车道号+日期+时间");
			rb_ccjg2.setBounds(327, 21, 206, 18);
		}
		return rb_ccjg2;
	}
	
	private JLabel getJLabel18() {
		if(jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("盘满策略：");
			jLabel18.setBounds(16, 45, 60, 18);
		}
		return jLabel18;
	}
	
	public JRadioButton getRb_pmcl1() {
		if(rb_pmcl1 == null) {
			rb_pmcl1 = new JRadioButton();
			rb_pmcl1.setText("覆盖");
			rb_pmcl1.setBounds(74, 45, 46, 18);
		}
		return rb_pmcl1;
	}
	
	public JRadioButton getRb_pmcl() {
		if(rb_pmcl == null) {
			rb_pmcl = new JRadioButton();
			rb_pmcl.setText("停写");
			rb_pmcl.setBounds(121, 45, 46, 18);
			rb_pmcl.setSelected(true);
		}
		return rb_pmcl;
	}
	
	private JLabel getJLabel19() {
		if(jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("卡口图片：");
			jLabel19.setBounds(179, 45, 60, 18);
		}
		return jLabel19;
	}

	private JLabel getJLabel20() {
		if(jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("违法图片：");
			jLabel20.setBounds(294, 45, 60, 18);
		}
		return jLabel20;
	}
	
	public JCheckBox getCb_bdcc() {
		if(cb_bdcc == null) {
			cb_bdcc = new JCheckBox();
			cb_bdcc.setText("开启");
			cb_bdcc.setBounds(75, 21, 50, 18);
			cb_bdcc.setSelected(true);
		}
		return cb_bdcc;
	}
	
	public JCheckBox getCb_kktp() {
		if(cb_kktp == null) {
			cb_kktp = new JCheckBox();
			cb_kktp.setText("备份");
			cb_kktp.setBounds(236, 45, 46, 18);
			cb_kktp.setSelected(true);
		}
		return cb_kktp;
	}
	
	public JCheckBox getCb_wftp() {
		if(cb_wftp == null) {
			cb_wftp = new JCheckBox();
			cb_wftp.setText("备份");
			cb_wftp.setBounds(352, 45, 46, 18);
			cb_wftp.setSelected(true);
		}
		return cb_wftp;
	}
	
	private JLabel getJLabel21() {
		if(jLabel21 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("违法录像：");
			jLabel21.setBounds(407, 45, 60, 18);
		}
		return jLabel21;
	}
	
	public JCheckBox getCb_wflx() {
		if(cb_wflx == null) {
			cb_wflx = new JCheckBox();
			cb_wflx.setText("备份");
			cb_wflx.setBounds(462, 45, 46, 18);
			cb_wflx.setSelected(true);
		}
		return cb_wflx;
	}
	
	private JLabel getJLabel22() {
		if(jLabel22 == null) {
			jLabel22 = new JLabel();
			jLabel22.setText("违法录像：");
			jLabel22.setBounds(520, 45, 60, 18);
		}
		return jLabel22;
	}
	
	public JCheckBox getCb_wflxkq() {
		if(cb_wflxkq == null) {
			cb_wflxkq = new JCheckBox();
			cb_wflxkq.setText("开启");
			cb_wflxkq.setBounds(578, 45, 46, 18);
			cb_wflxkq.setSelected(true);
		}
		return cb_wflxkq;
	}
	
	private JLabel getJLabel23() {
		if(jLabel23 == null) {
			jLabel23 = new JLabel();
			jLabel23.setText("实时录像：");
			jLabel23.setBounds(16, 69, 60, 18);
		}
		return jLabel23;
	}
	
	public JCheckBox getCb_sslxkq() {
		if(cb_sslxkq == null) {
			cb_sslxkq = new JCheckBox();
			cb_sslxkq.setText("开启");
			cb_sslxkq.setBounds(75, 69, 46, 18);
			cb_sslxkq.setSelected(true);
		}
		return cb_sslxkq;
	}
	
	private JLabel getJLabel24() {
		if(jLabel24 == null) {
			jLabel24 = new JLabel();
			jLabel24.setText("违法录像录制时长：");
			jLabel24.setBounds(133, 69, 108, 18);
		}
		return jLabel24;
	}
	
	public JTextField getText_wflxsc() {
		if(text_wflxsc == null) {
			text_wflxsc = new JTextField();
			text_wflxsc.setText("10");
			text_wflxsc.setBounds(236, 64, 26, 30);
		}
		return text_wflxsc;
	}
	
	private JLabel getJLabel25() {
		if(jLabel25 == null) {
			jLabel25 = new JLabel();
			jLabel25.setText("预录时长：");
			jLabel25.setBounds(273, 69, 60, 18);
		}
		return jLabel25;
	}
	
	public JTextField getText_ylsc() {
		if(text_ylsc == null) {
			text_ylsc = new JTextField();
			text_ylsc.setText("5");
			text_ylsc.setBounds(331, 64, 25, 30);
		}
		return text_ylsc;
	}
	
	private JLabel getJLabel26() {
		if(jLabel26 == null) {
			jLabel26 = new JLabel();
			jLabel26.setText("实时录像时长：");
			jLabel26.setBounds(362, 70, 84, 18);
		}
		return jLabel26;
	}
	
	public JTextField getText_sslx() {
		if(text_sslx == null) {
			text_sslx = new JTextField();
			text_sslx.setText("100");
			text_sslx.setBounds(444, 65, 33, 30);
		}
		return text_sslx;
	}
	
	public JButton getBtn_Net_StorageSetup() {
		if(btn_Net_StorageSetup == null) {
			btn_Net_StorageSetup = new JButton();
			btn_Net_StorageSetup.setText("设置存储策略");
			btn_Net_StorageSetup.setBounds(553, 69, 100, 30);
		}
		return btn_Net_StorageSetup;
	}

}
