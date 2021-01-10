package com.jinshi.bankPay;

public class ResultVO {
	/**
	 * 成功标记1
	 */
	private boolean flag;
	/**
	 * 业务功能状态码 成功：200  失败：500
	 */
	private Integer code;
	/**
	 * 提示信息
	 */
	private String message;
	/**
	 * 查询返回数据
	 */
	private Object data;



	public ResultVO(boolean flag) {
		this.flag = flag;
		if (flag) {
			this.code = 200;
			this.message = "请求成功";

		}
	}

	public ResultVO(boolean flag, Integer code, String message) {
		this.flag = flag;
		this.code = code;
		this.message = message;
	}

	public ResultVO(boolean flag, Integer code, String message, Object data) {
		this.flag = flag;
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



}
