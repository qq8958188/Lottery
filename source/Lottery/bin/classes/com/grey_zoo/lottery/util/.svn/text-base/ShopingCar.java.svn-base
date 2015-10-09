package com.grey_zoo.lottery.util;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;

public class ShopingCar {

	//单例设计模式
	private static ShopingCar shopingCar=new ShopingCar();
	
	//玩法编号
	private String lotteryid;
	
	//期号
	private String issue;
	
	//投注号码
	private String lotterycode;
	
	//注数
	private String lotterynumber;
	
	//方案金额
	private String lotteryvalue;

	//倍数
	private String appnumbers;

	//追期
	private String issuesnumbers;
	
	//是否多期追号 0否，1多期
	private int issueflag=0;

	//中奖后是否停止：0不停，1停
	private int bonusstop=1;
	
	private ShopingCar()
	{
		list_blue=new ArrayList<ArrayList<Integer>>();
		list_red=new ArrayList<ArrayList<Integer>>();
	}
	
	public static ShopingCar getInstance()
	{
		return shopingCar;
	}
	
	//蓝号的list
	private ArrayList<ArrayList<Integer>> list_blue;
	//红号的list
	private ArrayList<ArrayList<Integer>> list_red;
	
	public ArrayList<ArrayList<Integer>> getList_blue() {
		return list_blue;
	}

	public ArrayList<ArrayList<Integer>> getList_red() {
		return list_red;
	}
	
	public int getTotalCount()
	{
		int total=0;
		List<Integer> list;
		for(int i=0;i<list_blue.size();++i)
		{
			int num=1;
			list = list_red.get(i);
			num=lincheng(list.size(), 6);
			list=list_blue.get(i);
			num*=list.size();
			total+=num;
		}
		return total;
	}
	
	
	//计算集合C63
	private int lincheng(int num1,int num2)
	{
		int result=0;
		int result1=1;
		int result2=1;

		for(int i=num1;i>num1-num2;--i)
		{
			result1=result1*i;
		}

		for(int i=num2;i>1;--i)
		{
			result2=result2*i;
		}

		result=result1/result2;

		return result;
	}

	public String getLotteryid() {
		return lotteryid;
	}

	public void setLotteryid(String lotteryid) {
		this.lotteryid = lotteryid;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getLotterycode() {
		return lotterycode;
	}

	public void setLotterycode(String lotterycode) {
		this.lotterycode = lotterycode;
	}

	public String getLotterynumber() {
		return lotterynumber;
	}

	public void setLotterynumber(String lotterynumber) {
		this.lotterynumber = lotterynumber;
	}

	public String getLotteryvalue() {
		return lotteryvalue;
	}

	public void setLotteryvalue(String lotteryvalue) {
		this.lotteryvalue = lotteryvalue;
	}

	public String getAppnumbers() {
		return appnumbers;
	}

	public void setAppnumbers(String appnumbers) {
		this.appnumbers = appnumbers;
	}

	public String getIssuesnumbers() {
		return issuesnumbers;
	}

	public void setIssuesnumbers(String issuesnumbers) {
		this.issuesnumbers = issuesnumbers;
	}

	public int getIssueflag() {
		return issueflag;
	}

	public void setIssueflag(int issueflag) {
		this.issueflag = issueflag;
	}

	public int getBonusstop() {
		return bonusstop;
	}

	public void setBonusstop(int bonusstop) {
		this.bonusstop = bonusstop;
	}

	public void prepare() {
		
		//设置数目与金额
		lotterynumber=getTotalCount()+"";
		lotteryvalue=getTotalCount()*2+"";
		
	}
	
	public void clean_data() {
		// TODO 自动生成的方法存根

		list_blue.clear();
		list_red.clear();
		
	}
	
}
