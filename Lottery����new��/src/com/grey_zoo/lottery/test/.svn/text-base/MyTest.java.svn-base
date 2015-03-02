package com.grey_zoo.lottery.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.HttpUtils;
import com.grey_zoo.lottery.net.NetUtil;
import com.grey_zoo.lottery.net.protocal.Oelement;
import com.grey_zoo.lottery.net.protocal.RecBody;
import com.grey_zoo.lottery.net.protocal.RecHead;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.SendBody;
import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;
import com.grey_zoo.lottery.net.protocal.SendMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.net.protocal.sendelement.GetLotteryInfoElement;
import com.grey_zoo.lottery.util.DES;
import com.grey_zoo.lottery.util.ElementFactory;
import com.grey_zoo.lottery.util.MyStringUtil;

import android.R.integer;
import android.R.xml;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Xml;

public class MyTest extends AndroidTestCase {

	public void Test() throws Exception
	{
		ElementFactory elementFactory=new ElementFactory();
		ElementBase base=elementFactory.getElement("SSQInfo");
		SendMessage message=new SendMessage(base);
		String xml=message.getString();
		GlobalValue.enPrintln(xml);
		String string = "不能获取到";
		RecMessage recMessage = null;
		if(NetUtil.checkNet(getContext()))
		{
			HttpUtils client=new HttpUtils();
			InputStream inputStream=client.sendXml(GlobalValue.LOTTERY_URI,xml);
			XmlPullParser parser=Xml.newPullParser();
			parser.setInput(inputStream, GlobalValue.ENCODEING);
			int event=parser.getEventType();
			while(event!=XmlPullParser.END_DOCUMENT)
			{
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					recMessage=new RecMessage();
					break;
				
				case XmlPullParser.START_TAG:
					if("messengerid".equals(parser.getName()))
					{
						Leaf messengerid=new Leaf("messengerid");
						messengerid.setValue(parser.nextText());
						GlobalValue.enPrintln("messengerid:"+messengerid.getValue());
						recMessage.getHead().setTimestamp(messengerid);
					}
					else if ("timestamp".equals(parser.getName())) {
						Leaf timestamp=new Leaf("timestamp");
						timestamp.setValue(parser.nextText());
						GlobalValue.enPrintln("timestamp:"+timestamp.getValue());
						recMessage.getHead().setTimestamp(timestamp);
					}
					else if ("transactiontype".equals(parser.getName())) {
						Leaf transactiontype=new Leaf("transactiontype");
						transactiontype.setValue(parser.nextText());
						GlobalValue.enPrintln("transactiontype:"+transactiontype.getValue());
						recMessage.getHead().setTransactiontype(transactiontype);
					}
					else if ("digest".equals(parser.getName())) {
						Leaf digest=new Leaf("digest");
						digest.setValue(parser.nextText());
						GlobalValue.enPrintln("digest:"+digest.getValue());
						recMessage.getHead().setDigest(digest);
					}
					else if ("compress".equals(parser.getName())) {
						Leaf compress=new Leaf("compress");
						compress.setValue(parser.nextText());
						GlobalValue.enPrintln("compress:"+compress.getValue());
						recMessage.getHead().setCompress(compress);
					}
					else if ("agenterid".equals(parser.getName())) {
						Leaf agenterid=new Leaf("agenterid");
						agenterid.setValue(parser.nextText());
						GlobalValue.enPrintln("agenterid:"+agenterid.getValue());
						recMessage.getHead().setAgenterid(agenterid);
					}
					else if ("header".equals(parser.getName())) {
						
						recMessage.setHead(new RecHead());
						
					}
					else if ("body".equals(parser.getName())) {
						String body=parser.nextText();
						recMessage.setStr_body(body);
						recMessage.setBody(new RecBody());
						GlobalValue.enPrintln("body:"+body);
					}
					break;

				default:
					break;
				}
				event=parser.next();
			}
			
			DES des = new DES();
			String body = "<body>"
					+ des.authcode(recMessage.getStr_body(), "ENCODE",GlobalValue.DES_PASSWORD) + "</body>";
//			System.out.println(body);
			
			parser=Xml.newPullParser();
			parser.setInput(new StringReader(body));
			event=parser.getEventType();
			RecLotteryInfoElement element=new RecLotteryInfoElement();
			while(event!=XmlPullParser.END_DOCUMENT)
			{
				switch (event) {
				case XmlPullParser.START_TAG:
					if("oelement".equals(parser.getName()))
					{
						Oelement oelement=new Oelement();
						recMessage.getBody().setOelement(oelement);
					}else if ("lotteryid".equals(parser.getName())) {
						Leaf lotteryid=new Leaf("lotteryid");
						lotteryid.setValue(parser.nextText());
						GlobalValue.enPrintln("lotteryid:"+lotteryid.getValue());
						element.setLotteryid(lotteryid);
					}else if ("lotteryname".equals(parser.getName())) {
						Leaf lotteryname=new Leaf("lotteryname");
						lotteryname.setValue(parser.nextText());
						GlobalValue.enPrintln("lotteryname:"+lotteryname.getValue());
						element.setLotteryname(lotteryname);
					}else if ("issue".equals(parser.getName())) {
						Leaf issue=new Leaf("issue");
						issue.setValue(parser.nextText());
						GlobalValue.enPrintln("issue:"+issue.getValue());
						element.setIssue(issue);
					}else if ("lasttime".equals(parser.getName())) {
						Leaf lasttime=new Leaf("lasttime");
						lasttime.setValue(parser.nextText());
						GlobalValue.enPrintln("lasttime:"+lasttime.getValue());
						element.setLasttime(lasttime);
					}
					break;

				default:
					break;
				}
				event=parser.next();
			}
			
		}
	}
	
	
	void Test2()
	{
		UserEngin engin=new UserEngin();
		
		RecMessage recMessage = engin.login("13200000000", "00000000");
		
		String str1 = recMessage.getBody().getOelement().getErrorcode().getValue();
		String str2 = recMessage.getBody().getOelement().getErrormsg().getValue();
	}
	
}
