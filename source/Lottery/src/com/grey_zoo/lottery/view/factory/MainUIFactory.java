package com.grey_zoo.lottery.view.factory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import android.content.Context;

import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.util.ElementFactory;
import com.grey_zoo.lottery.view.BaseUI;

public class MainUIFactory {

	private static Properties properties;
	static{
		properties=new Properties();
		try {
			properties.load(MainUIFactory.class.getClassLoader().getResourceAsStream("mainui.properties"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	private MainUIFactory(){
		
	}
	
	public static BaseUI getInstance(String key,Context context)
	{
		String classNameString=(String) properties.get(key);
		if(classNameString!=null)
		{
			try {
//				return 
				Constructor<BaseUI> constructor = (Constructor<BaseUI>) Class.forName(classNameString).getConstructor(Context.class);
				return constructor.newInstance(context);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
}
