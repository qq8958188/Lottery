package com.grey_zoo.lottery.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.grey_zoo.lottery.global.GlobalValue;

import android.R.integer;


public class JSONUtils {

	public static List<String> getResultFromJson(JSONObject object) throws JSONException
	{
		
		List<String> list=new ArrayList<String>();
		
		GlobalValue.list_ssqQS=new ArrayList<String>();
		
		JSONArray array=object.getJSONArray("data");
		
		for(int i=0;i<array.length();++i)
		{
			JSONObject jsonObject=array.getJSONObject(i);
			list.add(jsonObject.getString("opencode"));
			GlobalValue.list_ssqQS.add(jsonObject.getString("expect"));
			if(i==0){
				int times = Integer.valueOf(jsonObject.getString("expect"));
				GlobalValue.SSQQS=times+1+"";
			}
		}
		
		return list;
	}
	
	public static String ALToJson(ArrayList<ArrayList<Integer>> arrayList)
	{
		
		JSONObject object=new JSONObject();
		
		JSONArray array=new JSONArray();
		
		for(ArrayList<Integer> lArrayList:arrayList){
			
			StringBuilder num = new StringBuilder();
			
			for(Integer integer:lArrayList)
			{
				num.append(integer+" ");
			}
			
			array.put(num.toString());
			
		}
		
		try {
			object.put("datas", array);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return object.toString();
	}
	
	/**
	 * 从JSON中获取数据红球和蓝球的数据
	 * @param blue 蓝球的json
	 * @param red 红球的json
	 * @param lblue 蓝球的list
	 * @param lred 红球的list
	 * @return 是否成功
	 */
	public static boolean getDetailLotteryFromJSON(String blue,String red,List<String> lblue,
			List<String> lred){
		
		try {
			JSONObject blueobject=new JSONObject(blue);
			JSONArray array=blueobject.getJSONArray("datas");
			for(int i=0;i<array.length();++i)
			{
				String str_blue=(String) array.get(i);
				str_blue = str_blue.replaceAll(" ", ",");
				str_blue = str_blue.substring(0, str_blue.length()-1);
				
				//转换号码
				String[] splits = str_blue.split(",");
				
				StringBuilder builder=new StringBuilder();
				
				for(String s: splits)
				{
					int ii=Integer.valueOf(s);
					if(ii<10)
					{
						builder.append(" 0"+s);
					}else {
						builder.append(" "+s);
					}
				}
								
				lblue.add(builder.toString());
			}
			
			JSONObject redobject=new JSONObject(red);
			array=redobject.getJSONArray("datas");
			for(int i=0;i<array.length();++i)
			{
				String str_red=(String) array.get(i);
				str_red = str_red.replaceAll(" ", ",");
				str_red = str_red.substring(0, str_red.length()-1);
				
				//转换号码
				String[] splits = str_red.split(",");
				
				StringBuilder builder=new StringBuilder();
				
				for(String s: splits)
				{
					int ii=Integer.valueOf(s);
					if(ii<10)
					{
						builder.append(" 0"+s);
					}else {
						builder.append(" "+s);
					}
				}
				
				lred.add(builder.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
}
