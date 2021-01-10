package com.jinshi.bankPay;

public class SuccessVo extends ResultVO {

	public SuccessVo() {
		super(true, 200, "成功" );
		this.setCode(200);
		this.setFlag(true);
		this.setMessage("请求成功");
	}

	public SuccessVo(Object data) {
		super(true, 200, "成功" );
		this.setCode(200);
		this.setFlag(true);
		this.setMessage("请求成功");
		this.setData(data);
	}
}
