package com.jinshi.netCamera.view;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
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
public class InitView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JButton btn_Net_FindDevice;
	private JButton btn_Net_FindDeviceIp;
	private JLabel jLabel3;
	private JLabel tHandle;
	private JLabel text_jb;
	private JButton btn_Net_QueryConnState;
	private JButton btn_Net_ConnCamera;
	private JButton btn_Net_AddCamera;
	private JLabel jLabel5;
	private JPanel jPanel2;
	private JButton btn_Net_ImageSnap;
	private JButton btn_Net_SaveImageToJpeg;
	private JButton btn_Net_SetControlCallBack;
	private JLabel jLabel6;
	private JButton btn_Net_DisConnCamera;
	private JButton btn_Net_DelCamera;
	private JLabel jLabel4;
	private JPanel jPanel1;
	private JTextField text_timeout;
	private JTextField text_port;
	private JTextField text_ip;
	private JLabel jLabel2;
	private JButton btn_Net_GetSdkVersion;
	private ButtonGroup buttonGroup1;
	private JButton btn_Net_SetSnapMode;
	private JRadioButton rb_lxzp;
	private JRadioButton rb_cfzp;
	private JLabel jLabel7;
	private JButton btn_Net_FindDeviceMac;
	private JLabel jLabelMaxImg;  
	private JLabel jLabelMiniImg;  
	private JLabel jLabelCp;
	
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public InitView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(671, 481));
			this.setSize(669, 481);
			this.setBorder(new LineBorder(new java.awt.Color(192,192,192), 1, false));
			this.setBackground(new java.awt.Color(230,230,230));
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("设备搜索：");
				jLabel1.setBounds(7, 7, 60, 18);
			}
			{
				btn_Net_FindDevice = new JButton();
				this.add(getBtn_Net_FindDevice());
				btn_Net_FindDevice.setText("设备搜索");
				btn_Net_FindDevice.setBounds(17, 31, 76, 30);
			}
			{
				btn_Net_FindDeviceIp = new JButton();
				this.add(getBtn_Net_FindDeviceIp());
				btn_Net_FindDeviceIp.setText("设备搜索IP");
				btn_Net_FindDeviceIp.setBounds(104, 31, 90, 30);
			}
			{
				btn_Net_FindDeviceMac = new JButton();
				this.add(getBtn_Net_FindDeviceMac());
				btn_Net_FindDeviceMac.setText("设备搜索Mac");
				btn_Net_FindDeviceMac.setBounds(206, 31, 98, 30);
			}
			{
				btn_Net_GetSdkVersion = new JButton();
				this.add(getBtn_Net_GetSdkVersion());
				btn_Net_GetSdkVersion.setText("SDK版本");
				btn_Net_GetSdkVersion.setBounds(316, 31, 77, 30);
			}
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				jPanel1.setBounds(7, 73, 655, 112);
				jPanel1.setLayout(null);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					text_timeout = new JTextField();
					jPanel1.add(text_timeout);
					text_timeout.setText("60");
					text_timeout.setBounds(227, 67, 26, 30);
				}
				{
					jLabel4 = new JLabel();
					jPanel1.add(jLabel4);
					jLabel4.setText("超时(s):");
					jLabel4.setBounds(183, 73, 47, 18);
				}
				{
					text_port = new JTextField();
					jPanel1.add(text_port);
					text_port.setText("30000");
					text_port.setBounds(125, 67, 47, 30);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("端口：");
					jLabel3.setBounds(89, 73, 36, 18);
				}
				{
					tHandle = new JLabel();
					jPanel1.add(tHandle);
					tHandle.setText("0");
					tHandle.setBounds(70, 73, 18, 18);
				}
				{
					text_jb = new JLabel();
					jPanel1.add(text_jb);
					text_jb.setText("相机句柄：");
					text_jb.setBounds(12, 73, 60, 18);
				}
				{
					btn_Net_QueryConnState = new JButton();
					jPanel1.add(btn_Net_QueryConnState);
					btn_Net_QueryConnState.setText("查询相机连接状态");
					btn_Net_QueryConnState.setBounds(389, 67, 124, 30);
				}
				{
					btn_Net_ConnCamera = new JButton();
					jPanel1.add(btn_Net_ConnCamera);
					btn_Net_ConnCamera.setText("添加后连接相机");
					btn_Net_ConnCamera.setBounds(265, 67, 112, 30);
				}
				{
					btn_Net_AddCamera = new JButton();
					jPanel1.add(btn_Net_AddCamera);
					btn_Net_AddCamera.setText("添加相机");
					btn_Net_AddCamera.setBounds(165, 27, 88, 30);
				}
				{
					text_ip = new JTextField();
					jPanel1.add(text_ip);
					text_ip.setText("192.168.0.149");
					text_ip.setBounds(75, 28, 84, 30);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("相机管理：");
					jLabel2.setBounds(6, 4, 60, 18);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5);
					jLabel5.setText("相机ip地址:");
					jLabel5.setBounds(12, 32, 61, 18);
				}
				{
					btn_Net_DelCamera = new JButton();
					jPanel1.add(getBtn_Net_DelCamera());
					btn_Net_DelCamera.setText("删除相机");
					btn_Net_DelCamera.setBounds(389, 27, 124, 30);
				}
				{
					btn_Net_DisConnCamera = new JButton();
					jPanel1.add(getBtn_Net_DisConnCamera());
					btn_Net_DisConnCamera.setText("断开相机连接");
					btn_Net_DisConnCamera.setBounds(265, 27, 112, 30);
				}
				{
					jLabel6 = new JLabel();
					jPanel1.add(jLabel6);
					jLabel6.setText("流程为填ip->添加相机->填端口和超时->连接相机->查询状态->断开连接->删除相机->查询状态");
					jLabel6.setBounds(64, 4, 502, 18);
				}
			}
			{
				btn_Net_SetControlCallBack = new JButton();
				this.add(getBtn_Net_SetControlCallBack());
				btn_Net_SetControlCallBack.setText("监听相机连接状态");
				btn_Net_SetControlCallBack.setBounds(7, 191, 124, 30);
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2);
				//jPanel2.setBounds(7, 229, 655, 52);
				jPanel2.setBounds(135, 191, 525, 40);
				jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel2.setLayout(null);
				{
					jLabel7 = new JLabel();
					jPanel2.add(jLabel7);
					jLabel7.setText("抓拍模式：");
					jLabel7.setBounds(7, 14, 60, 18);
				}}
			{
				rb_cfzp = new JRadioButton();
				jPanel2.add(getRb_cfzp());
				rb_cfzp.setText("触发抓拍");
				rb_cfzp.setBounds(67, 14, 70, 18);
			}
			{
				rb_lxzp = new JRadioButton();
				jPanel2.add(getRb_lxzp());
				rb_lxzp.setText("连续抓拍");
				rb_lxzp.setBounds(149, 14, 70, 18);
				rb_lxzp.setSelected(true);
			}
			{
				btn_Net_SetSnapMode = new JButton();
				jPanel2.add(getBtn_Net_SetSnapMode());
				btn_Net_SetSnapMode.setText("设置相机抓拍模式");
				btn_Net_SetSnapMode.setBounds(231, 8, 124, 30);
			}
			{
				btn_Net_SaveImageToJpeg = new JButton();
				jPanel2.add(btn_Net_SaveImageToJpeg);
				btn_Net_SaveImageToJpeg.setText("图片抓拍");
				btn_Net_SaveImageToJpeg.setBounds(361, 8, 76, 30);
			}
			{
				btn_Net_ImageSnap = new JButton();
				jPanel2.add(getBtn_Net_ImageSnap());
				btn_Net_ImageSnap.setText("强制抓拍");
				btn_Net_ImageSnap.setBounds(445, 8, 76, 30);
			}
			{
				getButtonGroup1();
			}
			{

				mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
				this.add(getEmbeddedMediaPlayerComponent());
				mediaPlayerComponent.setBounds(7,240, 355,200);
			}
			{
				jLabelMaxImg = new JLabel();
				this.add(getjLabelMaxImg());
				jLabelMaxImg.setBounds(370,240,285,150);
			}
			{
				jLabelMiniImg = new JLabel();
				this.add(getjLabelMiniImg());
				jLabelMiniImg.setBounds(370,400,200,45);
			}
			{
				jLabelCp = new JLabel();
				jLabelCp.setText("未有车牌");
				this.add(getjLabelCp());
				jLabelCp.setBounds(575,400,80,30);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JLabel getjLabelCp()
	{
		return jLabelCp;
	}
	public JLabel getjLabelMiniImg()
	{
		return jLabelMiniImg;
	}
	public JLabel getjLabelMaxImg()
	{
		return jLabelMaxImg;
	}
	public JButton getBtn_Net_FindDevice() {
		return btn_Net_FindDevice;
	}
	
	public JButton getBtn_Net_FindDeviceIp() {
		return btn_Net_FindDeviceIp;
	}
	
	public JButton getBtn_Net_FindDeviceMac() {
		return btn_Net_FindDeviceMac;
	}
	
	public JButton getBtn_Net_GetSdkVersion() {
		return btn_Net_GetSdkVersion;
	}
	
	public JTextField getText_ip() {
		return text_ip;
	}
	
	public JButton getBtn_Net_AddCamera() {
		return btn_Net_AddCamera;
	}
	
	public JButton getBtn_Net_ConnCamera() {
		return btn_Net_ConnCamera;
	}
	
	public JButton getBtn_Net_QueryConnState() {
		return btn_Net_QueryConnState;
	}
	
	public JLabel getTHandle() {
		return tHandle;
	}
	
	public JTextField getText_port() {
		return text_port;
	}
	
	public JLabel getJLabel4() {
		return jLabel4;
	}
	
	public JTextField getText_timeout() {
		return text_timeout;
	}
	
	public JButton getBtn_Net_DelCamera() {
		return btn_Net_DelCamera;
	}
	
	public JButton getBtn_Net_DisConnCamera() {
		return btn_Net_DisConnCamera;
	}
	
	public JButton getBtn_Net_SetControlCallBack() {
		return btn_Net_SetControlCallBack;
	}
	
	public JButton getBtn_Net_SaveImageToJpeg() {
		return btn_Net_SaveImageToJpeg;
	}
	
	public JButton getBtn_Net_ImageSnap() {
		return btn_Net_ImageSnap;
	}
	
	public JRadioButton getRb_cfzp() {
		return rb_cfzp;
	}
	
	public JRadioButton getRb_lxzp() {
		return rb_lxzp;
	}
	
	public JButton getBtn_Net_SetSnapMode() {
		return btn_Net_SetSnapMode;
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
			buttonGroup1.add(rb_cfzp);
			buttonGroup1.add(rb_lxzp);
		}
		return buttonGroup1;
	}
	public EmbeddedMediaPlayerComponent getEmbeddedMediaPlayerComponent() {
		return mediaPlayerComponent;
	}

}
