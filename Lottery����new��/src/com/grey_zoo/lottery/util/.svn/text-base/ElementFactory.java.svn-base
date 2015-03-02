package com.grey_zoo.lottery.util;

import java.io.IOException;
import java.util.Properties;

import com.grey_zoo.lottery.net.protocal.ElementBase;

public class ElementFactory {

	private static Properties properties;
	static{
		properties=new Properties();
		try {
			properties.load(ElementFactory.class.getClassLoader().getResourceAsStream("element.properties"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public ElementBase getElement(String key)
	{
		String classNameString=(String) properties.get(key);
		if(classNameString!=null)
		{
			try {
				return (ElementBase) Class.forName(classNameString).newInstance();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
}
