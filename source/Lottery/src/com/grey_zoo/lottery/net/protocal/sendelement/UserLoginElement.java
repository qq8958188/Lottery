package com.grey_zoo.lottery.net.protocal.sendelement;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;
/**
 * 用户登录请求
 * @author Administrator
 *
 */
public class UserLoginElement extends ElementBase {
	
	//请求登录
	Leaf actpassword=new Leaf("actpassword");
	
	@Override
	public String getTransactionType() {
		// TODO 自动生成的方法存根
		return "14001";
	}

	@Override
	public void serializate(XmlSerializer serializer) {
		
		try {
			serializer.startTag(null, "element");
			actpassword.serializate(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

	public void setPassword(String password)
	{
		actpassword.setValue(password);
	}
	
}
