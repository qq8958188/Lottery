package com.grey_zoo.lottery.net.protocal.sendelement;

import org.xmlpull.v1.XmlSerializer;

import android.util.Log;

import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;

public class BuyLotteryElement extends ElementBase{

	private static final String TAG = "BuyLotteryElement";

	//彩票的编号
	Leaf lotteryid=new Leaf("lotteryid");

	//期号
	Leaf issue=new Leaf("issue");

	//投注的号码
	Leaf lotterycode=new Leaf("lotterycode");

	//注数
	Leaf lotterynumber=new Leaf("lotterynumber");

	//方案金额
	Leaf lotteryvalue=new Leaf("lotteryvalue");

	//倍数
	Leaf appnumbers=new Leaf("appnumbers");

	//追期
	Leaf issuesnumbers=new Leaf("issuesnumbers");

	//是否多期追号
	Leaf issueflag=new Leaf("issueflag");

	//中奖后是否停止
	Leaf bonusstop=new Leaf("bonusstop");
	
	public Leaf getLotteryid() {
		return lotteryid;
	}

	public Leaf getIssue() {
		return issue;
	}

	public Leaf getLotterycode() {
		return lotterycode;
	}

	public Leaf getLotterynumber() {
		return lotterynumber;
	}

	public Leaf getLotteryvalue() {
		return lotteryvalue;
	}

	public Leaf getAppnumbers() {
		return appnumbers;
	}

	public Leaf getIssuesnumbers() {
		return issuesnumbers;
	}

	public Leaf getIssueflag() {
		return issueflag;
	}

	public Leaf getBonusstop() {
		return bonusstop;
	}

	@Override
	public String getTransactionType() {
		// TODO 自动生成的方法存根
		return "12006";
	}

	@Override
	public void serializate(XmlSerializer serializer) {
		// TODO 自动生成的方法存根
		
		try {
			serializer.startTag(null, "element");
			lotteryid.serializate(serializer);
			issue.serializate(serializer);
			lotterycode.serializate(serializer);
			lotterynumber.serializate(serializer);
			lotteryvalue.serializate(serializer);
			appnumbers.serializate(serializer);
			issuesnumbers.serializate(serializer);
			issueflag.serializate(serializer);
			bonusstop.serializate(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			Log.e(TAG, "购买彩票出错");
		}
		
	}

}
