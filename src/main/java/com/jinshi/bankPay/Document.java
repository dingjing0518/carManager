package com.jinshi.bankPay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;
@XStreamAlias("DOCUMENT")
public class Document {
	@XStreamImplicit(itemFieldName ="QUERYORDER")
	private List<QUERYORDER> QUERYORDER ; // 查询订单

	private String RETURN_CODE;   // 返回状态码
	private String RETURN_MSG;   // 返回消息
	private String CURPAGE;   //当前页
	private String PAGECOUNT;  //总页数
	private String TOTAL;   //总数
	private String PAYAMOUNT;  //付款方式
	private String REFUNDAMOUNT;  //  修正???

	public List<com.jinshi.bankPay.QUERYORDER> getQUERYORDER() {
		return QUERYORDER;
	}

	public void setQUERYORDER(List<QUERYORDER> QUERYORDER) {
		this.QUERYORDER = QUERYORDER;
	}

	public String getRETURN_CODE() {
		return RETURN_CODE;
	}

	public void setRETURN_CODE(String RETURN_CODE) {
		this.RETURN_CODE = RETURN_CODE;
	}

	public String getRETURN_MSG() {
		return RETURN_MSG;
	}

	public void setRETURN_MSG(String RETURN_MSG) {
		this.RETURN_MSG = RETURN_MSG;
	}

	public String getCURPAGE() {
		return CURPAGE;
	}

	public void setCURPAGE(String CURPAGE) {
		this.CURPAGE = CURPAGE;
	}

	public String getPAGECOUNT() {
		return PAGECOUNT;
	}

	public void setPAGECOUNT(String PAGECOUNT) {
		this.PAGECOUNT = PAGECOUNT;
	}

	public String getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(String TOTAL) {
		this.TOTAL = TOTAL;
	}

	public String getPAYAMOUNT() {
		return PAYAMOUNT;
	}

	public void setPAYAMOUNT(String PAYAMOUNT) {
		this.PAYAMOUNT = PAYAMOUNT;
	}

	public String getREFUNDAMOUNT() {
		return REFUNDAMOUNT;
	}

	public void setREFUNDAMOUNT(String REFUNDAMOUNT) {
		this.REFUNDAMOUNT = REFUNDAMOUNT;
	}
}
