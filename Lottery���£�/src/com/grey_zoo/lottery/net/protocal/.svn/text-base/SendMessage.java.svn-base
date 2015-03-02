package com.grey_zoo.lottery.net.protocal;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import com.grey_zoo.lottery.global.GlobalValue;

import android.util.Log;
import android.util.Xml;

public class SendMessage {
	
	public SendMessage(ElementBase element,String userName) {
		// TODO 自动生成的构造函数存根
		setRequest(element,userName);
	}
	
	public SendMessage(ElementBase element) {
		// TODO 自动生成的构造函数存根
		setRequest(element);
	}
	
	private static final String TAG ="My_Message";
	//头部
	SendHead head=new SendHead();
	//中间部分
	SendBody body=new SendBody();
	
	public void serializate(XmlSerializer serializer)
	{
		try {
			serializer.startTag(null, "message");
			head.serializate(serializer, body.getBody());
//			body.serializate(serializer);
			serializer.startTag(null, "body");
			serializer.text(body.getBodyInsideDESInfo());
			serializer.endTag(null, "body");
			serializer.endTag(null, "message");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 设置头中的具体请求
	 * @param element
	 */
	private void setRequest(ElementBase element)
	{
	
		setRequest(element, "13000000000");
		
	}
	
	/**
	 * 设置头中的具体请求，两参数都不能为空
	 * @param element
	 * @param userName
	 */
	private void setRequest(ElementBase element,String userName)
	{
	//	element.
		body.getElements().add(element);
		head.setTransactionType(element.getTransactionType());
		head.setUserName(userName);	
	}
	
	public String getString()
	{
		
		StringWriter writer=new StringWriter();
		
		XmlSerializer serializer=Xml.newSerializer();
		
		try {
			serializer.setOutput(writer);
			serializer.startDocument(GlobalValue.ENCODEING, true);
			serializate(serializer);
			serializer.endDocument();
		} catch (Exception e) {
			Log.e(TAG, "Message中序列化出错");
			e.printStackTrace();
		}
		
		return writer.toString();
	}
	
}
