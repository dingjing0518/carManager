package com.jinshi.bankPay;

/**
 * 建行无感支付实体类
 */
public class QrURLDemo {

	private String SUCCESS;
	private String PAYURL;
	private String QRURL;  //安卓点这个会直接跳到支付页面

	public String getQRURL() {
		return QRURL;
	}

	public void setQRURL(String QRURL) {
		this.QRURL = QRURL;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}
	public void setSUCCESS(String success) {
		SUCCESS = success;
	}
	public String getPAYURL() {
		return PAYURL;
	}
	public void setPAYURL(String payurl) {
		PAYURL = payurl;
	}
}
