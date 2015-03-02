package com.grey_zoo.lottery.net.element;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.util.Log;

import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;

public class GetSSQInfoElement extends ElementBase {
	
	private static final String TAG = "GetLotteryInfoElement";
	//彩票种类
	Leaf lotteryid=new Leaf("lotteryid");
	//期号
	Leaf issue=new Leaf("issue");
	
	public GetSSQInfoElement() {
		// TODO 自动生成的构造函数存根
		lotteryid.setValue("118");
		issue.setValue("1");
	}
	
	@Override
	public String getTransactionType() {
		// TODO 自动生成的方法存根
		return "12002";
	}

	@Override
	public void serializate(XmlSerializer serializer) {
		// TODO 自动生成的方法存根
		try {
			serializer.startTag(null, "element");
			lotteryid.serializate(serializer);
			issue.serializate(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			Log.e(TAG, "获取彩票协议出错");
		}
		
	}

}
