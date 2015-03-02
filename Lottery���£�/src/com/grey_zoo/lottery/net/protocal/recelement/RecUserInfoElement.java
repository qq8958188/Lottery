package com.grey_zoo.lottery.net.protocal.recelement;

import com.grey_zoo.lottery.net.protocal.Leaf;

public class RecUserInfoElement {

	//账户名称
	private Leaf accountname;
	//账户ID
	private Leaf accountid;
	//账户金额
	private Leaf accounvalues;
	//可投注金额
	private Leaf investvalues;
	//可提现金额
	private Leaf cashvalues;
	
	public Leaf getAccountname() {
		return accountname;
	}
	
	public void setAccountname(Leaf accountname) {
		this.accountname = accountname;
	}
	
	public Leaf getAccountid() {
		return accountid;
	}
	
	public void setAccountid(Leaf accountid) {
		this.accountid = accountid;
	}
	
	public Leaf getAccounvalues() {
		return accounvalues;
	}
	
	public void setAccounvalues(Leaf accounvalues) {
		this.accounvalues = accounvalues;
	}
	
	public Leaf getInvestvalues() {
		return investvalues;
	}
	
	public void setInvestvalues(Leaf investvalues) {
		this.investvalues = investvalues;
	}
	
	public Leaf getCashvalues() {
		return cashvalues;
	}
	
	public void setCashvalues(Leaf cashvalues) {
		this.cashvalues = cashvalues;
	}
	
}
