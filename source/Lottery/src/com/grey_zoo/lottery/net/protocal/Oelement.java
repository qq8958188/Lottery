package com.grey_zoo.lottery.net.protocal;

public class Oelement {

	//处理的结果号码
	private Leaf errorcode;
	//结果描述信息
	private Leaf errormsg;
	
	public Leaf getErrorcode() {
		return errorcode;
	}
	
	public void setErrorcode(Leaf errorcode) {
		this.errorcode = errorcode;
	}
	
	public Leaf getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(Leaf errormsg) {
		this.errormsg = errormsg;
	}
	
}
