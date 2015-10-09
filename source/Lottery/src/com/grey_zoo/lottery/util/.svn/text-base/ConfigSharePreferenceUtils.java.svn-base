package com.grey_zoo.lottery.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * config的sharepreference对象，简化以后的使用
 */
public class ConfigSharePreferenceUtils {
	
	//上下文
	private Context context;
	//sharePreference对象
	private SharedPreferences sp;
	//sharePrefernce的编辑器
	private Editor editor;
	
	/**
	 * config的sharepreference对象，简化以后的使用
	 * @param context 设备上下文
	 */
	public ConfigSharePreferenceUtils(Context context)
	{
		sp=context.getSharedPreferences("config", context.MODE_PRIVATE);
		editor=sp.edit();
	}
	
	/**
	 * 获取config的sharepreference对象保存的key的值，默认返回bool_default
	 * @param key 键值
	 * @param bool_default 默认值
	 * @return 返回默认值
	 */
	public boolean getBool(String key,boolean bool_default)
	{
		return sp.getBoolean(key, bool_default);
	}
	
	/**
	 * 获取config的sharepreference对象保存的key的值，默认返回str_default
	 * @param key 键值
	 * @param str_default 默认值
	 * @return 返回默认值
	 */
	public String getString(String key,String str_default)
	{
		return sp.getString(key, str_default);
	}
	
	/**
	 * 向config的sharepreference对象写入键值对
	 * @param key 键
	 * @param value 值
	 */
	public void putString(String key,String value)
	{
		editor.putString(key, value);
		editor.commit();
	}
	
	/**
	 * 向config的sharepreference对象写入键值对
	 * @param key 键
	 * @param value 值
	 */
	public void putBool(String key,boolean value)
	{
		editor.putBoolean(key, value);
		editor.commit();
	}
}
