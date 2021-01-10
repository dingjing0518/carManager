package com.jinshi.bankPay;

/**
 * 建行支付回调实体类
 * 具体看文档 :
 *    商户通知_聚合扫码使用V1.1.doc
 */
public class PayCallBackEntity {
	//商户柜台代码
	private String POSID;

	//分行代码
	private String BRANCHID;
	private String ORDERID; //定单号
	private String PAYMENT; //付款金额
	private String CURCODE;  //币种
	private String REMARK1;  //备注一
	private String REMARK2;  //备注二
	private String ACC_TYPE;  //账户类型  服务器通知中有此字段返回且参与验签
	private String SUCCESS;  //成功标志  成功－Y，失败－N
	private String TYPE;   //接口类型  分行业务人员在P2员工渠道后台设置防钓鱼的开关。 1.开关关闭时，无此字段返回且不参与验签 2.开关打开时，有此字段返回且参与验签。参数值为 1-防钓鱼接口

	private String REFERER;  //Referer信息  分行业务人员在P2员工渠道后台设置防钓鱼开关。 1.开关关闭时，无此字段返回且不参与验签。 2.开关打开时，有此字段返回且参与验签
	private String CLIENTIP;  //客户端IP  分行业务人员在P2员工渠道后台设置防钓鱼的开关。 1.开关关闭时，无此字段返回且不参与验签 2.开关打开时，有此字段返回且参与验签。参数值为 客户在建行系统中的IP
	private String ACCDATE;  //系统记账日期  商户登陆商户后台设置返回记账日期的开关 1.开关关闭时，无此字段返回且不参与验签。 2.开关打开时，有此字段返回且参与验签。参数值格式为YYYYMMDD（如20100907）。
	private String USRMSG; //支付账户信息  分行业务人员在P2员工渠道后台设置防钓鱼开关和返回账户信息的开关。 1.开关关闭时，无此字段返回且不参与验签。2.开关打开但支付失败时，无此字段返回且不参与验签。3.开关打开且支付成功时，有此字段返回且参与验签。无PAYTYPE返回时，参数值格式如下：“姓名|账号加密后的密文”。有PAYTYPE返回时，该参数值为空。
	private String USRINFO;   //客户加密信息   分行业务人员在P2员工渠道后台设置防钓鱼开关和客户信息加密返回的开关。 1.开关关闭时，无此字段返回且不参与验签
	private String PAYTYPE;  //支付方式   ALIPAY:支付宝 WEIXIN：微信 为空：建行龙支付 该字段有返回时参与验签，无此字段返回时不参与验签。
	private String SIGN;  //数字签名

	@Override
	public String toString() {
		return "PayCallBackEntity{" +
				"POSID='" + POSID + '\'' +
				", BRANCHID='" + BRANCHID + '\'' +
				", ORDERID='" + ORDERID + '\'' +
				", PAYMENT='" + PAYMENT + '\'' +
				", CURCODE='" + CURCODE + '\'' +
				", REMARK1='" + REMARK1 + '\'' +
				", REMARK2='" + REMARK2 + '\'' +
				", ACC_TYPE='" + ACC_TYPE + '\'' +
				", SUCCESS='" + SUCCESS + '\'' +
				", TYPE='" + TYPE + '\'' +
				", REFERER='" + REFERER + '\'' +
				", CLIENTIP='" + CLIENTIP + '\'' +
				", ACCDATE='" + ACCDATE + '\'' +
				", USRMSG='" + USRMSG + '\'' +
				", USRINFO='" + USRINFO + '\'' +
				", PAYTYPE='" + PAYTYPE + '\'' +
				", SIGN='" + SIGN + '\'' +
				'}';
	}

	public String getPOSID() {
		return POSID;
	}

	public void setPOSID(String POSID) {
		this.POSID = POSID;
	}

	public String getBRANCHID() {
		return BRANCHID;
	}

	public void setBRANCHID(String BRANCHID) {
		this.BRANCHID = BRANCHID;
	}

	public String getORDERID() {
		return ORDERID;
	}

	public void setORDERID(String ORDERID) {
		this.ORDERID = ORDERID;
	}

	public String getPAYMENT() {
		return PAYMENT;
	}

	public void setPAYMENT(String PAYMENT) {
		this.PAYMENT = PAYMENT;
	}

	public String getCURCODE() {
		return CURCODE;
	}

	public void setCURCODE(String CURCODE) {
		this.CURCODE = CURCODE;
	}

	public String getREMARK1() {
		return REMARK1;
	}

	public void setREMARK1(String REMARK1) {
		this.REMARK1 = REMARK1;
	}

	public String getREMARK2() {
		return REMARK2;
	}

	public void setREMARK2(String REMARK2) {
		this.REMARK2 = REMARK2;
	}

	public String getACC_TYPE() {
		return ACC_TYPE;
	}

	public void setACC_TYPE(String ACC_TYPE) {
		this.ACC_TYPE = ACC_TYPE;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public void setSUCCESS(String SUCCESS) {
		this.SUCCESS = SUCCESS;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	public String getREFERER() {
		return REFERER;
	}

	public void setREFERER(String REFERER) {
		this.REFERER = REFERER;
	}

	public String getCLIENTIP() {
		return CLIENTIP;
	}

	public void setCLIENTIP(String CLIENTIP) {
		this.CLIENTIP = CLIENTIP;
	}

	public String getACCDATE() {
		return ACCDATE;
	}

	public void setACCDATE(String ACCDATE) {
		this.ACCDATE = ACCDATE;
	}

	public String getUSRMSG() {
		return USRMSG;
	}

	public void setUSRMSG(String USRMSG) {
		this.USRMSG = USRMSG;
	}

	public String getUSRINFO() {
		return USRINFO;
	}

	public void setUSRINFO(String USRINFO) {
		this.USRINFO = USRINFO;
	}

	public String getPAYTYPE() {
		return PAYTYPE;
	}

	public void setPAYTYPE(String PAYTYPE) {
		this.PAYTYPE = PAYTYPE;
	}

	public String getSIGN() {
		return SIGN;
	}

	public void setSIGN(String SIGN) {
		this.SIGN = SIGN;
	}
}
