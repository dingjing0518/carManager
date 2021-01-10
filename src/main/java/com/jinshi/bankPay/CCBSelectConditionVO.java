package com.jinshi.bankPay;

/**
 * 建行查询条件页面对象
 */
public class CCBSelectConditionVO {
	/*	ORDERDATE（ORDERDATE与ORDERID必须有一个输入）
		非必输项，OPERATOR元素必须有,但值可为空
		查询定单的日期，格式为YYYYMMDD，共8位。例如:20050607(表示2005年6月7日)*/
	private String orderDate; // 订单日期
	/*BEGORDERTIME （已结算流水查询只支持按日期查询，时间只参与MAC运算。未结算流水查询支持按时间段查询。不输入时默认为00:00:00）
	非必输项 ，BEGORDERTIME元素必须有,但值可为空
	查询定单的开始时间，格式为hh，共8位，如00:00:00。
	合法的时间格式：00  00:00  03:34:23  23:59:59  如果只有2位数字，最大为24，否则为23 。2位数字可解释为分钟和秒的值都为0，如12相当于12:00:00，。分钟和秒的最大值为59。
	非法的时间格式：25  24:00  23:60  23,59,59  23:59:61  0:12:2
	结束时间具有相同的规则。*/
	private String begOrderTime; //定单开始时间
	/*
	*  ENDORDERTIME（已结算流水查询只支持按日期查询，时间只参与MAC运算。未结算流水查询支持按时间段查询。不输入时默认为23:59:59）
	非必输项，ENDORDERTIME元素必须有,但值可为空
	查询定单的截止时间，格式为hh，共8位，如23:59:59。时间格式参考开始时间
	* */
	private String endOrderTime; // 定单截止时间
	/*必输项
	0支付流水
	1退款流水*/
	private String type; //流水类型
	/*必输项（当日只有未结算流水可供查询）
	//0 未结算流水
	//1 已结算流水*/
//	private String kind; //流水状态
	/*必输项
	0失败
	1成功
	2不确定
	3全部（已结算流水查询不支持全部）*/
	private String status; //交易状态码
	/*必输项，输入将要查询的页码。*/
	private String page;  //页码


	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getBegOrderTime() {
		return begOrderTime;
	}

	public void setBegOrderTime(String begOrderTime) {
		this.begOrderTime = begOrderTime;
	}

	public String getEndOrderTime() {
		return endOrderTime;
	}

	public void setEndOrderTime(String endOrderTime) {
		this.endOrderTime = endOrderTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
