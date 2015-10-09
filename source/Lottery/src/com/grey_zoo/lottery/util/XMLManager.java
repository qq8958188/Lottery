package com.grey_zoo.lottery.util;

import java.io.InputStream;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;
import com.grey_zoo.lottery.net.protocal.Oelement;
import com.grey_zoo.lottery.net.protocal.RecBody;
import com.grey_zoo.lottery.net.protocal.RecHead;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.SendMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecBuyLotteryElement;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.net.protocal.recelement.RecUserInfoElement;

public class XMLManager {

	private ElementFactory elementFactory;
	private ElemnetChange elemnetChange;
	
	public XMLManager(ElemnetChange elemnetChange) {
		
		elementFactory=new ElementFactory();
		this.elemnetChange=elemnetChange;
	}
	
	/**
	 * 获取能发送的数据
	 * @return
	 */
	public String getSendXml(String name)
	{
		/**
		 * 通过工厂获取Element，获取信息且打印
		 */
		elementFactory=new ElementFactory();
		ElementBase base=elementFactory.getElement(name);
		SendMessage message=new SendMessage(base);
		
		if(elemnetChange!=null)
		{
			//接口进行改变
			elemnetChange.changeElement(base);
		}
		
		String xml=message.getString();
		GlobalValue.enPrintln(xml);
		return xml;
	}
	
	/**
	 * 获取能发送的数据
	 * @return
	 */
	public String getSendXml(String name,String username)
	{
		/**
		 * 通过工厂获取Element，获取信息且打印
		 */
		elementFactory=new ElementFactory();
		ElementBase base=elementFactory.getElement(name);
		SendMessage message=new SendMessage(base,username);
		
		//接口进行改变
		base = elemnetChange.changeElement(base);
		
		String xml=message.getString();
		GlobalValue.enPrintln(xml);
		return xml;
	}
	
	public interface ElemnetChange{
		ElementBase changeElement(ElementBase base);
	}
	
	//解析XML
	/**
	 * 通用的解析
	 * @param xml 返回信息的XML文件
	 * @return
	 * @throws Exception 
	 */
	public RecMessage CommomXmlPullur(InputStream inputStream) throws Exception
	{
		RecMessage recMessage = null;
		//=httpUtils.sendXml(GlobalValue.LOTTERY_URI,xml);
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
		return recMessage;
	}
	
	/**
	 * 解析彩票信息Body的数据
	 * @param recMessage
	 * @return
	 * @throws Exception
	 */
	public RecMessage LotteryInfoBodyXmlPullur(RecMessage recMessage) throws Exception
	{
		XmlPullParser parser=Xml.newPullParser();
		DES des = new DES();
		String body = "<body>"
				+ des.authcode(recMessage.getStr_body(), "ENCODE",GlobalValue.DES_PASSWORD) + "</body>";
//		System.out.println(body);
		
		parser=Xml.newPullParser();
		parser.setInput(new StringReader(body));
		int event = parser.getEventType();
		RecLotteryInfoElement element=new RecLotteryInfoElement();
		Oelement oelement=null;
		while(event!=XmlPullParser.END_DOCUMENT)
		{
			switch (event) {
			case XmlPullParser.START_TAG:
				if("oelement".equals(parser.getName()))
				{
					oelement=new Oelement();
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
					recMessage.getBody().getElementList().add(element);
				}else if ("errorcode".equals(parser.getName())) {
					Leaf errorcode=new Leaf("errorcode");
					errorcode.setValue(parser.nextText());
					oelement.setErrorcode(errorcode);
				}else if ("errormsg".equals(parser.getName())) {
					Leaf errormsg=new Leaf("errormsg");
					errormsg.setValue(parser.nextText());
					oelement.setErrormsg(errormsg);
				}
				break;

			default:
				break;
			}
			event=parser.next();
		}
		return recMessage;
	}

	/**
	 * 解析登陆的数据
	 * @param recMessage
	 * @return
	 * @throws Exception 
	 */
	public RecMessage UserLoginBodyXmlPullur(RecMessage recMessage) throws Exception {
		//解析
		
		XmlPullParser parser=Xml.newPullParser();
		DES des = new DES();
		String body = "<body>"
				+ des.authcode(recMessage.getStr_body(), "ENCODE",GlobalValue.DES_PASSWORD) + "</body>";
//		System.out.println(body);
		
		parser=Xml.newPullParser();
		parser.setInput(new StringReader(body));
		int event = parser.getEventType();
		Oelement oelement=null;
		while(event!=XmlPullParser.END_DOCUMENT)
		{
			switch (event) {
			case XmlPullParser.START_TAG:
				if("oelement".equals(parser.getName()))
				{
					oelement=new Oelement();
					recMessage.getBody().setOelement(oelement);
				}else if ("errorcode".equals(parser.getName())) {
					Leaf errorcode=new Leaf("errorcode");
					errorcode.setValue(parser.nextText());
					oelement.setErrorcode(errorcode);
				}else if ("errormsg".equals(parser.getName())) {
					Leaf errormsg=new Leaf("errormsg");
					errormsg.setValue(parser.nextText());
					oelement.setErrormsg(errormsg);
				}
			default:
				break;
			}
			event=parser.next();
		}
		
		return recMessage;
	}
	
	/**
	 * 解析账户信息
	 * @param recMessage
	 * @return
	 * @throws Exception
	 */
	public RecMessage UserInfoXmlPullur(RecMessage recMessage) throws Exception
	{
		XmlPullParser parser=Xml.newPullParser();
		DES des = new DES();
		String body = "<body>"
				+ des.authcode(recMessage.getStr_body(), "ENCODE",GlobalValue.DES_PASSWORD) + "</body>";
//		System.out.println(body);
		
		parser=Xml.newPullParser();
		parser.setInput(new StringReader(body));
		int event = parser.getEventType();
		RecUserInfoElement element=new RecUserInfoElement();
		Oelement oelement=null;
		while(event!=XmlPullParser.END_DOCUMENT)
		{
			switch (event) {
			case XmlPullParser.START_TAG:
				if("oelement".equals(parser.getName()))
				{
					oelement=new Oelement();
					recMessage.getBody().setOelement(oelement);
				}else if ("accountname".equals(parser.getName())) {
					Leaf accountname=new Leaf("accountname");
					accountname.setValue(parser.nextText());
					GlobalValue.enPrintln("accountname:"+accountname.getValue());
					element.setAccountname(accountname);
				}else if ("accountid".equals(parser.getName())) {
					Leaf accountid=new Leaf("accountid");
					accountid.setValue(parser.nextText());
					GlobalValue.enPrintln("accountid:"+accountid.getValue());
					element.setAccountid(accountid);
				}else if ("accounvalues".equals(parser.getName())) {
					Leaf accounvalues=new Leaf("accounvalues");
					accounvalues.setValue(parser.nextText());
					GlobalValue.enPrintln("accounvalues:"+accounvalues.getValue());
					element.setAccounvalues(accounvalues);
				}else if ("investvalues".equals(parser.getName())) {
					Leaf investvalues=new Leaf("investvalues");
					investvalues.setValue(parser.nextText());
					GlobalValue.enPrintln("investvalues:"+investvalues.getValue());
					element.setInvestvalues(investvalues);
	//				recMessage.getBody().getElementList().add(element);
				}else if ("cashvalues".equals(parser.getName())) {
					Leaf cashvalues=new Leaf("cashvalues");
					cashvalues.setValue(parser.nextText());
					GlobalValue.enPrintln("cashvalues:"+cashvalues.getValue());
					element.setCashvalues(cashvalues);
	//				recMessage.getBody().getElementList().add(element);
				}else if ("errorcode".equals(parser.getName())) {
					Leaf errorcode=new Leaf("errorcode");
					errorcode.setValue(parser.nextText());
					oelement.setErrorcode(errorcode);
				}else if ("errormsg".equals(parser.getName())) {
					Leaf errormsg=new Leaf("errormsg");
					errormsg.setValue(parser.nextText());
					oelement.setErrormsg(errormsg);
				}
				break;

			default:
				break;
			}
			event=parser.next();
		}
		
		recMessage.getBody().getElementList().add(element);
		
		return recMessage;
	}

	/**
	 * 买彩票的解析
	 * @param recMessage
	 * @return
	 * @throws Exception
	 */
	public RecMessage BuyLotteryXmlPullur(RecMessage recMessage) throws Exception {
		
		XmlPullParser parser=Xml.newPullParser();
		DES des = new DES();
		String body = "<body>"
				+ des.authcode(recMessage.getStr_body(), "ENCODE",GlobalValue.DES_PASSWORD) + "</body>";
//		System.out.println(body);
		
		parser=Xml.newPullParser();
		parser.setInput(new StringReader(body));
		int event = parser.getEventType();
		RecBuyLotteryElement element=new RecBuyLotteryElement();
		Oelement oelement=null;
		while(event!=XmlPullParser.END_DOCUMENT)
		{
			switch (event) {
			case XmlPullParser.START_TAG:
				if("oelement".equals(parser.getName()))
				{
					oelement=new Oelement();
					recMessage.getBody().setOelement(oelement);
				}else if ("serialid".equals(parser.getName())) {
					Leaf serialid=new Leaf("serialid");
					serialid.setValue(parser.nextText());
					GlobalValue.enPrintln("serialid:"+serialid.getValue());
					element.setSerialid(serialid);
				}else if ("tradevalue".equals(parser.getName())) {
					Leaf tradevalue=new Leaf("tradevalue");
					tradevalue.setValue(parser.nextText());
					GlobalValue.enPrintln("tradevalue:"+tradevalue.getValue());
					element.setTradevalue(tradevalue);
				}else if ("actvalue".equals(parser.getName())) {
					Leaf actvalue=new Leaf("actvalue");
					actvalue.setValue(parser.nextText());
					GlobalValue.enPrintln("actvalue:"+actvalue.getValue());
					element.setActvalue(actvalue);
				}else if ("errorcode".equals(parser.getName())) {
					Leaf errorcode=new Leaf("errorcode");
					errorcode.setValue(parser.nextText());
					oelement.setErrorcode(errorcode);
				}else if ("errormsg".equals(parser.getName())) {
					Leaf errormsg=new Leaf("errormsg");
					errormsg.setValue(parser.nextText());
					oelement.setErrormsg(errormsg);
				}
				break;

			default:
				break;
			}
			event=parser.next();
		}
		
		recMessage.getBody().getElementList().add(element);
		
		return recMessage;
	}
	
}
