package com.grey_zoo.lottery.engin;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.HttpUtils;
import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;
import com.grey_zoo.lottery.net.protocal.Oelement;
import com.grey_zoo.lottery.net.protocal.RecBody;
import com.grey_zoo.lottery.net.protocal.RecHead;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.SendMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.DES;
import com.grey_zoo.lottery.util.ElementFactory;
import com.grey_zoo.lottery.util.JSONUtils;
import com.grey_zoo.lottery.util.XMLManager;

public class CommomEngin {

	private HttpUtils httpUtils;
	private XMLManager manager;
	public CommomEngin() {
		// TODO 自动生成的构造函数存根
		httpUtils=new HttpUtils();
		manager=new XMLManager(null);
	}
	
	public RecMessage getLotteryInfo(String name){
		
		String xml = manager.getSendXml(name);
		
		/**
		 * 返回信息
		 */
		RecMessage recMessage=null;
		try {
			//发送网络数据
			InputStream inputStream = httpUtils.sendXml(GlobalValue.LOTTERY_URI,xml);
			
			if(inputStream==null)
			{
				return null;
			}
			
			//先进行普通的解析
			recMessage= manager.CommomXmlPullur(inputStream);
			//再进行彩票数据的解析
			recMessage = manager.LotteryInfoBodyXmlPullur(recMessage);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			GlobalValue.enPrintln("CommomEngin出错");
			recMessage=null;
		}
		
		
		return recMessage;
	}
	
	public List<String> getSSQData() throws Exception{
		
		JSONObject object = new HttpUtils().sendGet(GlobalValue.SSQURL);
		
		return JSONUtils.getResultFromJson(object);
	}
	
}
