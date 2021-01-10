package com.jinshi.netCamera.view;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
public class BasicConfView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_Net_QueryDevSetup;
	private JButton btn_Net_QueryMACSetup;
	private JButton btn_Net_NETSetup;
	private JTextField text_dnsname;
	private JLabel jLabel16;
	private JTextField text_dns2;
	private JLabel jLabel15;
	private JTextField text_dns1;
	private JButton btn_Net_StoreConfig;
	private JComboBox cb_datefm;
	private JLabel jLabel14;
	private JTextField text_wg;
	private JLabel jLabel13;
	private JTextField text_zwym;
	private JLabel jLabel12;
	private JTextField text_ipaddr;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JPanel jPanel1;
	private ButtonGroup buttonGroup1;
	private JButton btn_Net_TimeSetup;
	private JTextField text_zone;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JRadioButton rb_timefm1;
	private JRadioButton rb_timefm;
	private JLabel jLabel7;
	private JTextField text_second;
	private JLabel jLabel6;
	private JTextField text_minute;
	private JLabel jLabel5;
	private JTextField text_hour;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JTextField text_day;
	private JTextField text_month;
	private JLabel jtext;
	private JTextField text_year;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jp222;
	private JButton btn_Net_ExportConfig;
	private JButton btn_Net_GetSysState;
	private JButton btn_Net_RebootCamera;
	private JButton btn_Net_QueryRS485Setup;
	private JButton btn_Net_RestoreConfig;
	private JButton btn_Net_SetupRestore;
	private JButton btn_Net_QueryRebootTimeSetup;
	private JButton btn_Net_QueryImageWDRSetup;
	private JButton btn_Net_QueryImageEvSetup;
	private JButton btn_Net_QueryMulticastSetup;
	private JButton btn_Net_QuerySntpSetup;
	private JButton btn_Net_QueryTimeSetup;
	private JButton btn_Net_QueryParkRS485Setup;
	private JButton btn_Net_QueryNETSetup;

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
		
	public BasicConfView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(672, 481));
			this.setLayout(null);
			this.setSize(671, 481);
			this.setBackground(new java.awt.Color(230,230,230));
			{
				btn_Net_QueryDevSetup = new JButton();
				this.add(getBtn_Net_QueryDevSetup());
				btn_Net_QueryDevSetup.setText("设备信息查询");
				btn_Net_QueryDevSetup.setBounds(6, 6, 100, 30);
			}
			{
				btn_Net_QueryNETSetup = new JButton();
				this.add(getBtn_Net_QueryNETSetup());
				btn_Net_QueryNETSetup.setText("相机网络参数");
				btn_Net_QueryNETSetup.setBounds(112, 6, 100, 30);
			}
			{
				btn_Net_QueryMACSetup = new JButton();
				this.add(getBtn_Net_QueryMACSetup());
				btn_Net_QueryMACSetup.setText("相机MAC");
				btn_Net_QueryMACSetup.setBounds(218, 6, 77, 30);
			}
			{
				btn_Net_QueryRS485Setup = new JButton();
				this.add(getBtn_Net_QueryRS485Setup());
				btn_Net_QueryRS485Setup.setText("RS485参数");
				btn_Net_QueryRS485Setup.setBounds(301, 6, 90, 30);
			}
			{
				btn_Net_QueryParkRS485Setup = new JButton();
				this.add(getBtn_Net_QueryParkRS485Setup());
				btn_Net_QueryParkRS485Setup.setText("停车场RS485参数");
				btn_Net_QueryParkRS485Setup.setBounds(397, 6, 126, 30);
			}
			{
				btn_Net_QueryTimeSetup = new JButton();
				this.add(getBtn_Net_QueryTimeSetup());
				btn_Net_QueryTimeSetup.setText("相机时间参数");
				btn_Net_QueryTimeSetup.setBounds(529, 6, 113, 30);
			}
			{
				btn_Net_QuerySntpSetup = new JButton();
				this.add(getBtn_Net_QuerySntpSetup());
				btn_Net_QuerySntpSetup.setText("sntp参数");
				btn_Net_QuerySntpSetup.setBounds(6, 38, 78, 30);
			}
			{
				btn_Net_QueryMulticastSetup = new JButton();
				this.add(getBtn_Net_QueryMulticastSetup());
				btn_Net_QueryMulticastSetup.setText("组播配置参数");
				btn_Net_QueryMulticastSetup.setBounds(86, 38, 100, 30);
			}
			{
				btn_Net_QueryImageEvSetup = new JButton();
				this.add(getBtn_Net_QueryImageEvSetup());
				btn_Net_QueryImageEvSetup.setText("顺逆光配置参数");
				btn_Net_QueryImageEvSetup.setBounds(186, 38, 112, 30);
			}
			{
				btn_Net_QueryImageWDRSetup = new JButton();
				this.add(getBtn_Net_QueryImageWDRSetup());
				btn_Net_QueryImageWDRSetup.setText("相机宽动态配置");
				btn_Net_QueryImageWDRSetup.setBounds(298, 38, 112, 30);
			}
			{
				btn_Net_QueryRebootTimeSetup = new JButton();
				this.add(getBtn_Net_QueryRebootTimeSetup());
				btn_Net_QueryRebootTimeSetup.setText("定时重启参数+日志级别");
				btn_Net_QueryRebootTimeSetup.setBounds(410, 38, 156, 30);
			}
			{
				btn_Net_SetupRestore = new JButton();
				this.add(getBtn_Net_SetupRestore());
				btn_Net_SetupRestore.setText("恢复出厂设置");
				btn_Net_SetupRestore.setBounds(6, 72, 100, 30);
			}
			{
				btn_Net_RestoreConfig = new JButton();
				this.add(getBtn_Net_RestoreConfig());
				btn_Net_RestoreConfig.setText("恢复最后一次保存的配置");
				btn_Net_RestoreConfig.setBounds(112, 72, 160, 30);
			}
			{
				btn_Net_StoreConfig = new JButton();
				this.add(getBtn_Net_StoreConfig());
				btn_Net_StoreConfig.setText("保存当前配置");
				btn_Net_StoreConfig.setBounds(278, 72, 100, 30);
			}
			{
				btn_Net_RebootCamera = new JButton();
				this.add(getBtn_Net_RebootCamera());
				btn_Net_RebootCamera.setText("立即重启系统");
				btn_Net_RebootCamera.setBounds(384, 72, 106, 30);
			}
			{
				btn_Net_GetSysState = new JButton();
				this.add(getBtn_Net_GetSysState());
				btn_Net_GetSysState.setText("获取系统状态");
				btn_Net_GetSysState.setBounds(496, 72, 146, 30);
			}
			{
				btn_Net_ExportConfig = new JButton();
				this.add(getBtn_Net_ExportConfig());
				btn_Net_ExportConfig.setText("导出配置");
				btn_Net_ExportConfig.setBounds(566, 38, 76, 30);
			}
			{
				jp222 = new JPanel();
				this.add(jp222);
				this.add(getJPanel1());
				jp222.setBounds(5, 108, 637, 87);
				jp222.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jp222.setLayout(null);
				{
					jLabel1 = new JLabel();
					jp222.add(jLabel1);
					jLabel1.setText("相机时间：");
					jLabel1.setBounds(4, 3, 60, 18);
				}
				{
					jLabel2 = new JLabel();
					jp222.add(jLabel2);
					jLabel2.setText("年：");
					jLabel2.setBounds(17, 26, 24, 18);
				}
				{
					text_year = new JTextField();
					jp222.add(getText_year());
					text_year.setText("2016");
					text_year.setBounds(35, 21, 40, 30);
				}
				{
					jtext = new JLabel();
					jp222.add(getJtext());
					jtext.setText("月(1~12)：");
					jtext.setBounds(81, 27, 64, 18);
				}
				{
					text_month = new JTextField();
					jp222.add(getText_month());
					text_month.setText("12");
					text_month.setBounds(136, 21, 26, 30);
				}
				{
					jLabel3 = new JLabel();
					jp222.add(jLabel3);
					jLabel3.setText("日(1~31)：");
					jLabel3.setBounds(168, 27, 61, 18);
				}
				{
					text_day = new JTextField();
					jp222.add(getText_day());
					text_day.setText("28");
					text_day.setBounds(225, 21, 26, 30);
				}
				{
					jLabel4 = new JLabel();
					jp222.add(jLabel4);
					jLabel4.setText("时(0~23)：");
					jLabel4.setBounds(257, 27, 60, 18);
				}
				{
					text_hour = new JTextField();
					jp222.add(getText_hour());
					text_hour.setText("10");
					text_hour.setBounds(312, 20, 26, 30);
				}
				{
					jLabel5 = new JLabel();
					jp222.add(jLabel5);
					jLabel5.setText("分(0~59)：");
					jLabel5.setBounds(346, 26, 60, 18);
				}
				{
					text_minute = new JTextField();
					jp222.add(getText_minute());
					text_minute.setText("10");
					text_minute.setBounds(403, 20, 26, 30);
				}
				{
					jLabel6 = new JLabel();
					jp222.add(jLabel6);
					jLabel6.setText("秒(0~59)：");
					jLabel6.setBounds(435, 26, 60, 18);
				}
				{
					text_second = new JTextField();
					jp222.add(getText_second());
					text_second.setText("10");
					text_second.setBounds(495, 20, 26, 30);
				}
				{
					jLabel7 = new JLabel();
					jp222.add(jLabel7);
					jLabel7.setText("时间格式：");
					jLabel7.setBounds(17, 57, 60, 18);
				}
				{
					rb_timefm = new JRadioButton();
					jp222.add(getRb_timefm());
					rb_timefm.setText("12小时制");
					rb_timefm.setBounds(75, 57, 72, 18);
				}
				{
					rb_timefm1 = new JRadioButton();
					jp222.add(getRb_timefm1());
					rb_timefm1.setText("24小时制");
					rb_timefm1.setBounds(150, 57, 72, 18);
					rb_timefm1.setSelected(true);
				}
				{
					jLabel8 = new JLabel();
					jp222.add(jLabel8);
					jLabel8.setText("日期格式：");
					jLabel8.setBounds(231, 57, 60, 18);
				}
				{
					ComboBoxModel cb_datefmModel = 
							new DefaultComboBoxModel(
									new String[] { "年-月-日", "月-日-年","日-月-年" });
					cb_datefm = new JComboBox();
					jp222.add(getCb_datefm());
					cb_datefm.setModel(cb_datefmModel);
					cb_datefm.setBounds(288, 52, 96, 28);
				}
				{
					jLabel9 = new JLabel();
					jp222.add(jLabel9);
					jLabel9.setText("时区(0~24)：");
					jLabel9.setBounds(396, 57, 77, 18);
				}
				{
					text_zone = new JTextField();
					jp222.add(getText_zone());
					text_zone.setText("20");
					text_zone.setBounds(465, 51, 28, 30);
				}
				{
					btn_Net_TimeSetup = new JButton();
					jp222.add(getBtn_Net_TimeSetup());
					btn_Net_TimeSetup.setText("设置时间");
					btn_Net_TimeSetup.setBounds(554, 51, 76, 30);
				}
				{}
				getButtonGroup1();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JButton getBtn_Net_QueryDevSetup() {
		return btn_Net_QueryDevSetup;
	}
	
	public JButton getBtn_Net_QueryNETSetup() {
		return btn_Net_QueryNETSetup;
	}
	
	public JButton getBtn_Net_QueryMACSetup() {
		return btn_Net_QueryMACSetup;
	}
	
	public JButton getBtn_Net_QueryRS485Setup() {
		return btn_Net_QueryRS485Setup;
	}
	
	public JButton getBtn_Net_QueryParkRS485Setup() {
		return btn_Net_QueryParkRS485Setup;
	}
	
	public JButton getBtn_Net_QueryTimeSetup() {
		return btn_Net_QueryTimeSetup;
	}
	
	public JButton getBtn_Net_QuerySntpSetup() {
		return btn_Net_QuerySntpSetup;
	}
	
	public JButton getBtn_Net_QueryMulticastSetup() {
		return btn_Net_QueryMulticastSetup;
	}
	
	public JButton getBtn_Net_QueryImageEvSetup() {
		return btn_Net_QueryImageEvSetup;
	}
	
	public JButton getBtn_Net_QueryImageWDRSetup() {
		return btn_Net_QueryImageWDRSetup;
	}
	
	public JButton getBtn_Net_QueryRebootTimeSetup() {
		return btn_Net_QueryRebootTimeSetup;
	}
	
	public JButton getBtn_Net_SetupRestore() {
		return btn_Net_SetupRestore;
	}
	
	public JButton getBtn_Net_RestoreConfig() {
		return btn_Net_RestoreConfig;
	}
	
	public JButton getBtn_Net_StoreConfig() {
		return btn_Net_StoreConfig;
	}
	
	public JButton getBtn_Net_RebootCamera() {
		return btn_Net_RebootCamera;
	}
	
	public JButton getBtn_Net_GetSysState() {
		return btn_Net_GetSysState;
	}
	
	public JButton getBtn_Net_ExportConfig() {
		return btn_Net_ExportConfig;
	}
	
	public JTextField getText_year() {
		return text_year;
	}
	
	public JLabel getJtext() {
		return jtext;
	}
	
	public JTextField getText_month() {
		return text_month;
	}
	
	public JTextField getText_day() {
		return text_day;
	}
	
	public JTextField getText_hour() {
		return text_hour;
	}
	
	public JTextField getText_minute() {
		return text_minute;
	}
	
	public JTextField getText_second() {
		return text_second;
	}
	
	public JRadioButton getRb_timefm() {
		return rb_timefm;
	}
	
	public JRadioButton getRb_timefm1() {
		return rb_timefm1;
	}
	
	public JComboBox getCb_datefm() {
		return cb_datefm;
	}
	
	public JTextField getText_zone() {
		return text_zone;
	}
	
	public JButton getBtn_Net_TimeSetup() {
		return btn_Net_TimeSetup;
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
			buttonGroup1.add(rb_timefm1);
			buttonGroup1.add(rb_timefm);
		}
		return buttonGroup1;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBounds(6, 207, 636, 91);
			jPanel1.setEnabled(false);
			jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			jPanel1.setLayout(null);
			jPanel1.add(getJLabel10());
			jPanel1.add(getJLabel11());
			jPanel1.add(getText_ipaddr());
			jPanel1.add(getJLabel12());
			jPanel1.add(getText_zwym());
			jPanel1.add(getJLabel13());
			jPanel1.add(getText_wg());
			jPanel1.add(getJLabel14());
			jPanel1.add(getText_dns1());
			jPanel1.add(getJLabel15());
			jPanel1.add(getText_dns2());
			jPanel1.add(getJLabel16());
			jPanel1.add(getText_dnsname());
			jPanel1.add(getBtn_Net_NETSetup());
		}
		return jPanel1;
	}
	
	private JLabel getJLabel10() {
		if(jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("网络配置：");
			jLabel10.setBounds(6, 3, 60, 18);
		}
		return jLabel10;
	}
	
	private JLabel getJLabel11() {
		if(jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("IP地址：");
			jLabel11.setBounds(18, 23, 47, 18);
		}
		return jLabel11;
	}
	
	public JTextField getText_ipaddr() {
		if(text_ipaddr == null) {
			text_ipaddr = new JTextField();
			text_ipaddr.setText("192.168.0.29");
			text_ipaddr.setBounds(61, 18, 91, 30);
		}
		return text_ipaddr;
	}
	
	private JLabel getJLabel12() {
		if(jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("子网掩码：");
			jLabel12.setBounds(156, 24, 60, 18);
		}
		return jLabel12;
	}
	
	public JTextField getText_zwym() {
		if(text_zwym == null) {
			text_zwym = new JTextField();
			text_zwym.setText("255.255.255.0");
			text_zwym.setBounds(208, 18, 110, 30);
		}
		return text_zwym;
	}
	
	private JLabel getJLabel13() {
		if(jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("网关：");
			jLabel13.setBounds(326, 24, 36, 18);
		}
		return jLabel13;
	}
	
	public JTextField getText_wg() {
		if(text_wg == null) {
			text_wg = new JTextField();
			text_wg.setText("192.168.0.1");
			text_wg.setBounds(356, 18, 77, 30);
		}
		return text_wg;
	}
	
	private JLabel getJLabel14() {
		if(jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("DNS1：");
			jLabel14.setBounds(18, 60, 45, 18);
		}
		return jLabel14;
	}
	
	public JTextField getText_dns1() {
		if(text_dns1 == null) {
			text_dns1 = new JTextField();
			text_dns1.setText("192.168.0.1");
			text_dns1.setBounds(62, 54, 89, 30);
		}
		return text_dns1;
	}
	
	private JLabel getJLabel15() {
		if(jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("DNS2：");
			jLabel15.setBounds(170, 60, 45, 18);
		}
		return jLabel15;
	}
	
	public JTextField getText_dns2() {
		if(text_dns2 == null) {
			text_dns2 = new JTextField();
			text_dns2.setText("0.0.0.0");
			text_dns2.setBounds(208, 54, 110, 30);
		}
		return text_dns2;
	}
	
	private JLabel getJLabel16() {
		if(jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("DNS名称：");
			jLabel16.setBounds(324, 60, 62, 18);
		}
		return jLabel16;
	}
	
	public JTextField getText_dnsname() {
		if(text_dnsname == null) {
			text_dnsname = new JTextField();
			text_dnsname.setText("dns1");
			text_dnsname.setBounds(379, 54, 54, 30);
		}
		return text_dnsname;
	}
	
	public JButton getBtn_Net_NETSetup() {
		if(btn_Net_NETSetup == null) {
			btn_Net_NETSetup = new JButton();
			btn_Net_NETSetup.setText("设置网络");
			btn_Net_NETSetup.setBounds(553, 55, 76, 30);
		}
		return btn_Net_NETSetup;
	}

}
