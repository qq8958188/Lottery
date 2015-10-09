package com.grey_zoo.lottery.net.protocal.sendelement;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import com.grey_zoo.lottery.net.protocal.ElementBase;

public class GetUserInfoElement extends ElementBase{

	@Override
	public String getTransactionType() {
		// TODO 自动生成的方法存根
		return "11007";
	}

	@Override
	public void serializate(XmlSerializer serializer) {
		// TODO 自动生成的方法存根
		try {
			serializer.text("");
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
