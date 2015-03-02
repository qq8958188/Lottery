package com.grey_zoo.lottery.net.protocal;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.DES;
import com.grey_zoo.lottery.util.MyStringUtil;
import org.apache.commons.codec.digest.DigestUtils;

public class SendHead {

	//代理ID
	Leaf agenterid=new Leaf("agenterid");
	//信息来源
	Leaf source=new Leaf("source");
	//加密算法类型
	Leaf compress=new Leaf("compress");
	//时间戳
	Leaf timestamp=new Leaf("timestamp");
	//时间戳+六位的随机数
	Leaf messengerid=new Leaf("messengerid");
	//数据摘要：MD5
	//MD5的原始数据的组成：时间戳+代理的密码
	Leaf digest=new Leaf("digest");
	//请求的唯一的标示
	Leaf transactiontype=new Leaf("transactiontype");
	//用户名
	Leaf username=new Leaf("username");
	
	public SendHead() {
		//设置固定的值
		agenterid.setValue("889931");
		source.setValue("ivr");
		compress.setValue("DES");
		
		//设置默认值
		username.setValue("13000000000");
	}
	
	/**
	 * 手动设置用户名
	 */
	public void setUserName(String username)
	{
		this.username.setValue(username);
	}
	
	public void setTransactionType(String transactiontype)
	{
		this.transactiontype.setValue(transactiontype);
	}
	
	public void serializate(XmlSerializer serializer,String strBody) throws Exception
	{
		
		//设置时间戳
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String strTimeStamp=format.format(new Date());
		timestamp.setValue(strTimeStamp);

		//设置ID
		String strMessengerId=strTimeStamp+MyStringUtil.formatSendId(GlobalValue.getSendId());
		messengerid.setValue(strMessengerId);

		//Body的处理
//		String strBody=body.getBody();
		strBody=StringUtils.substringBetween(strBody, "<elements>", "</elements>");
		//MD5加密
		String strDigest=strTimeStamp+GlobalValue.AGENTER_PASSWORD+strBody;
		strDigest=DigestUtils.md5Hex(strDigest);
		digest.setValue(strDigest);
		
		if(username.isValueEmpty()||transactiontype.isValueEmpty())
		{
			throw new Exception("用户名或者请求类型为空");
		}
		
		//序列化XML
		serializer.startTag(null, "head");
		agenterid.serializate(serializer);
		source.serializate(serializer);
		compress.serializate(serializer);
		timestamp.serializate(serializer);
		messengerid.serializate(serializer);
		digest.serializate(serializer);
		transactiontype.serializate(serializer);
		username.serializate(serializer);
		serializer.endTag(null, "head");
		
	}
	
}
