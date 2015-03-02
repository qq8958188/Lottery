package com.grey_zoo.lottery.util;

import android.R.integer;

public class MyStringUtil {

	public static String formatSendId(int sendId) {
		//转换成字符串
		String strSendId=String.valueOf(sendId);
		if(strSendId.length()<6)
		{
			//创建
			StringBuilder builder=new StringBuilder();
			//补充到6位
			for(int i=strSendId.length();i<6;++i)
			{
				builder.append("0");
			}
			builder.append(strSendId);
			
			return builder.toString();
		}
		
		return strSendId;
	}
	
	
	
}
