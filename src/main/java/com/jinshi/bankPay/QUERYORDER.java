package com.jinshi.bankPay;

public class QUERYORDER {
	private String MERCHANTID;  //商户代码
	private String BRANCHID;  //分行代码
	private String POSID;  //柜台号
	private String ORDERID;  //定单号
	private String ORDERDATE;  //定单支付的时间
	private String ACCDATE;  //访问日期记录
	private String AMOUNT;  //总计(大致应该是支付金额了....)
	private String STATUSCODE;  //状态码
	private String STATUS; //交易状态  支付成功的话, 会返回 "成功"
	private String REFUND;  //退税???
	private String SIGN;  //签名

	public String getMERCHANTID() {
		return MERCHANTID;
	}

	public void setMERCHANTID(String MERCHANTID) {
		this.MERCHANTID = MERCHANTID;
	}

	public String getBRANCHID() {
		return BRANCHID;
	}

	public void setBRANCHID(String BRANCHID) {
		this.BRANCHID = BRANCHID;
	}

	public String getPOSID() {
		return POSID;
	}

	public void setPOSID(String POSID) {
		this.POSID = POSID;
	}

	public String getORDERID() {
		return ORDERID;
	}

	public void setORDERID(String ORDERID) {
		this.ORDERID = ORDERID;
	}

	public String getORDERDATE() {
		return ORDERDATE;
	}

	public void setORDERDATE(String ORDERDATE) {
		this.ORDERDATE = ORDERDATE;
	}

	public String getACCDATE() {
		return ACCDATE;
	}

	public void setACCDATE(String ACCDATE) {
		this.ACCDATE = ACCDATE;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String AMOUNT) {
		this.AMOUNT = AMOUNT;
	}

	public String getSTATUSCODE() {
		return STATUSCODE;
	}

	public void setSTATUSCODE(String STATUSCODE) {
		this.STATUSCODE = STATUSCODE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	public String getREFUND() {
		return REFUND;
	}

	public void setREFUND(String REFUND) {
		this.REFUND = REFUND;
	}

	public String getSIGN() {
		return SIGN;
	}

	public void setSIGN(String SIGN) {
		this.SIGN = SIGN;
	}
}
