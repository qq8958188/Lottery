package com.grey_zoo.lottery.net.protocal.recelement;

import com.grey_zoo.lottery.net.protocal.Leaf;

public class RecBuyLotteryElement {

	//流水账单号
	private Leaf serialid;
	
	//实际扣费金额
	private Leaf tradevalue;
	
	//用户账户余额
	private Leaf actvalue;

	public Leaf getSerialid() {
		return serialid;
	}

	public void setSerialid(Leaf serialid) {
		this.serialid = serialid;
	}

	public Leaf getTradevalue() {
		return tradevalue;
	}

	public void setTradevalue(Leaf tradevalue) {
		this.tradevalue = tradevalue;
	}

	public Leaf getActvalue() {
		return actvalue;
	}

	public void setActvalue(Leaf actvalue) {
		this.actvalue = actvalue;
	}
	
}
