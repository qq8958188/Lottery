package com.grey_zoo.lottery.util;

import java.util.List;

import org.json.JSONArray;

import android.R.bool;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

import com.grey_zoo.lottery.bean.BmobUserData;
import com.grey_zoo.lottery.global.GlobalValue;

public class BmobUtils {
	
	/**
	 * 根据用户名检测数据是否已经存在
	 * @param username 用户名
	 * @return
	 */
	public boolean checkIsExit(String username)
	{
		BmobQuery<BmobUserData> bmobQuery	 = new BmobQuery<BmobUserData>();
		bmobQuery.addWhereEqualTo("name", username);
//		bmobQuery.addWhereNotEqualTo("age", 25);
//		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);	// 先从缓存取数据，如果没有的话，再从网络取。
		MyFindListener myFindListener = new MyFindListener();
		bmobQuery.findObjects(GlobalValue.context,myFindListener);
		
		return myFindListener.isfind;
		
	}
	
	/**
	 * 插入用户
	 * @param userData 用户
	 * @return
	 */
	public boolean insertUser(final BmobUserData userData)
	{
		MySaveListner mySaveListner = new MySaveListner();
		userData.save(GlobalValue.context, mySaveListner);
		return mySaveListner.isSave;
	}
	
	/**
	 * 获得用户信息
	 * @param username 用户名
	 * @return
	 */
	public BmobUserData getUser(String username)
	{
		BmobQuery<BmobUserData> bmobQuery = new BmobQuery<BmobUserData>();
		bmobQuery.addWhereEqualTo("name", username);
//		bmobQuery.addWhereNotEqualTo("age", 25);
//		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);	
		// 先从缓存取数据，如果没有的话，再从网络取。
		MyFindListener myFindListener = new MyFindListener();
		bmobQuery.findObjects(GlobalValue.context, myFindListener);
		
		return myFindListener.t;
	}
	
	class MyFindListener extends FindListener<BmobUserData>{

		public boolean isfind=true;
		
		public BmobUserData t=null;
		
		
		@Override
		public void onError(int arg0, String arg1) {
			// TODO 自动生成的方法存根
			isfind=false;
		}

		@Override
		public void onSuccess(List<BmobUserData> arg0) {
			// TODO 自动生成的方法存根
			if(arg0.size()>=1)
				isfind=true;
			t=arg0.get(0);
			System.out.println("已存在");
		}
		
		
	}
	
	class MySaveListner extends SaveListener{

		public boolean isSave=false;
		
		@Override
		public void onFailure(int arg0, String arg1) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void onSuccess() {
			// TODO 自动生成的方法存根
			isSave=true;
		}
		
	}
	
}


