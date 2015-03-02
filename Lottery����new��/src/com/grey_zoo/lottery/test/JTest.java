package com.grey_zoo.lottery.test;

import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;

import android.test.AndroidTestCase;

public class JTest extends AndroidTestCase {

	public void Test()
	{
		UserEngin engin=new UserEngin();
		
		RecMessage recMessage = engin.login("13200000000", "00000000");
		
		String str1 = recMessage.getBody().getOelement().getErrorcode().getValue();
		GlobalValue.enPrintln(str1);
		String str2 = recMessage.getBody().getOelement().getErrormsg().getValue();
		GlobalValue.enPrintln(str2);
	}
	
	public void Test2()
	{
		
		UserEngin engin=new UserEngin();
		
		RecMessage recMessage = engin.getUserInfo();
		
		String str1 = recMessage.getBody().getOelement().getErrorcode().getValue();
		GlobalValue.enPrintln(str1);
		String str2 = recMessage.getBody().getOelement().getErrormsg().getValue();
		GlobalValue.enPrintln(str2);
		
	}
	
	public void Test3()
	{
		
		UserEngin engin=new UserEngin();
		
		RecMessage recMessage = engin.BuyLottery();
		
		String str1 = recMessage.getBody().getOelement().getErrorcode().getValue();
		GlobalValue.enPrintln(str1);
		String str2 = recMessage.getBody().getOelement().getErrormsg().getValue();
		GlobalValue.enPrintln(str2);
		
	}
	
}
