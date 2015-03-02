package com.grey_zoo.lottery.net.protocal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.DES;

import android.util.Log;
import android.util.Xml;

public class SendBody {

	private static final String TAG = "MY_Body";
	//包含着请求
	private List<ElementBase> elements=new ArrayList<ElementBase>(); 
	
	public void serializate(XmlSerializer serializer)
	{
		
		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");
			//对请求的内容进行序列化
			for(ElementBase element:getElements())
			{
				element.serializate(serializer);
			}
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			Log.e(TAG, "序列化出错");
			e.printStackTrace();
		}
		
	}
	
	public String getBody() {
		
		StringWriter writer=new StringWriter();
		
		XmlSerializer serializer=Xml.newSerializer();
		String str=null;
		try {
			serializer.setOutput(writer);
			serializer.startDocument(GlobalValue.ENCODEING, true);
			serializate(serializer);
			serializer.endDocument();
			str=writer.toString();;
		} catch (Exception e) {
			Log.e(TAG, "获取Body出错");
			e.printStackTrace();
		}
		
		return str;
	}

	public List<ElementBase> getElements() {
		return elements;
	}
	
	public String getBodyInsideDESInfo()
	{
		// 加密数据
		String wholeBody = getBody();
		String orgDesInfo=StringUtils.substringBetween(wholeBody, "<body>", "</body>");
		
		// 加密
		// 加密调试——2天
		// ①加密算法实现不同
		// ②加密的原始数据不同
		
		DES des=new DES();
		return des.authcode(orgDesInfo, "DECODE", GlobalValue.DES_PASSWORD);
	}
	
}
