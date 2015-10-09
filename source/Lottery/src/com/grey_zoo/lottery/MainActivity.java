package com.grey_zoo.lottery;

import java.io.File;

import cn.bmob.v3.Bmob;

import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.engin.UserEngin.LoginListener;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.ConfigSharePreferenceUtils;
import com.grey_zoo.lottery.util.PromptManager;
import com.grey_zoo.lottery.view.BaseUI;
import com.grey_zoo.lottery.view.FirstUI;
import com.grey_zoo.lottery.view.HallUI;
import com.grey_zoo.lottery.view.factory.MainUIFactory;
import com.grey_zoo.lottery.view.manager.BottomManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.tauth.Tencent;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//sharepareference
	private ConfigSharePreferenceUtils sharePreferenceUtils;
	
	//中间的布局
	private LinearLayout ll_middle;
	//Title的布局
	private RelativeLayout rl_title;
	//Buttom的布局
	private RelativeLayout rl_buttom;
	//OnResume的时候判断是否为第一次加载
	private boolean hasLoad=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		             .build();
		
		ImageLoader.getInstance().init(config);
		
		GlobalValue.imageLoader=ImageLoader.getInstance();
		
		//进行Bmob的初始化
		Bmob.initialize(MainActivity.this, GlobalValue.BMOBKEY);
		
		//初始化（获取对象与进行初始布局）
		init();
	}

	private void init() {
		
		/*
		 * 设置Context
		 */
		GlobalValue.context=this;
		
		/*
		 *实例化Tencent 
		 */
		GlobalValue.tencent=Tencent.createInstance(GlobalValue.APP_ID, this);
		
		/*
		 * Activity初始化
		 */
		GlobalValue.activity=this;
		
		//登录数据
		final UserEngin engin=new UserEngin();
		
		PromptManager.showProgressDialog(this);
		
		
		// 获取main中的ViewGroup
		rl_title=(RelativeLayout) findViewById(R.id.iv_mian_title);
		rl_buttom=(RelativeLayout) findViewById(R.id.iv_mian_buttom);
		ll_middle=(LinearLayout) findViewById(R.id.iv_main_middle);
		//设置单例的
		TitleManager.getInstance().setView(rl_title);
		BottomManager.getInstance().setView(rl_buttom);
		
//		TitleManager.getInstance().init(this);
//		ButtomManager.getInstance().init(this);
		
//		//展示普通的title与buttom
//		TitleManager.getInstance().showCommom();
//		ButtomManager.getInstance().showCommom();
		
		//展示主页
//		MainUIFactory factory=new MainUIFactory();
//		BaseUI baseUI=MainUIFactory.getInstance("hall",this);
		
		//添加到manager
		MiddleManager.getInstance().init(ll_middle);
		MiddleManager.getInstance().changeUI("home", null);
		
		/*
		 *初始化登录数据 
		 */
		engin.tryLogin(new LoginListener() {
			
			@Override
			public void onSucceed() {
				// TODO 自动生成的方法存根
				Toast.makeText(MainActivity.this, "登陆成功~~", Toast.LENGTH_SHORT).show();
				PromptManager.closeProgressDialog();
				MiddleManager.getInstance().NoticeAll();
				TitleManager.getInstance().setLoginText(GlobalValue.username);

				GlobalValue.userMoney= engin.checkAndGetBmobMoney();

				
			}
			
			@Override
			public void onFail() {
				// TODO 自动生成的方法存根
				Toast.makeText(MainActivity.this, "登陆失败~~,请稍后再试~~", Toast.LENGTH_SHORT).show();
				PromptManager.closeProgressDialog();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStop() {
		
		super.onStop();
	}
	
	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
		
		if(hasLoad)
		{
			MiddleManager.getInstance().onResume();
		}
		hasLoad=true;
		super.onResume();
	}
	
	//实现返回的方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
//		return super.onKeyDown(keyCode, event);
		
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			MiddleManager.getInstance().BackKey();
			return true;
			
		}else {
			return super.onKeyDown(keyCode, event);
		}

		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		GlobalValue.tencent.onActivityResult(requestCode, resultCode, data);
	}
	
}
