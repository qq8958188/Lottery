package com.grey_zoo.lottery.view.manager;

import java.util.Observable;
import java.util.Observer;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.view.BaseUI;
import com.grey_zoo.lottery.view.PlaySSQUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BottomManager implements Observer{

	//单例设计模式
	private static BottomManager buttomManager=new BottomManager();
	
	//维护一个View对象
	private View view;
	
	//维护title对象
	private LinearLayout commom_buttom;
	private LinearLayout game_buttom;
	
	//Buttom的底部TextView
	private TextView tv_gamebottom_text;
	
	//选好了与清空
	private ImageButton ib_clear;
	private ImageButton ib_done;
	
	//首页与购彩大厅
	private ImageButton ib_hall;
	private ImageButton ib_home;
	//首页与购彩大厅
	private ImageButton ib_userdata;
	private ImageButton ib_mylottery;
	
	//设置View
	public void setView(View view)
	{
		this.view=view;
		
		init();
	}
	
	public void init() {
		//获取实例
		
		commom_buttom=(LinearLayout) view.findViewById(R.id.iv_bottom_normal);
		game_buttom=(LinearLayout) view.findViewById(R.id.iv_bottom_game);
		
		tv_gamebottom_text=(TextView) view.findViewById(R.id.iv_tv_gamebottom_text);
		
		ib_clear=(ImageButton) view.findViewById(R.id.iv_ib_bottom_clear);
		ib_done=(ImageButton) view.findViewById(R.id.iv_ib_bottom_done);
		
		ib_hall=(ImageButton) view.findViewById(R.id.iv_ib_bottom_hall);
		ib_home=(ImageButton) view.findViewById(R.id.iv_ib_bottom_home);
		
		ib_userdata=(ImageButton) view.findViewById(R.id.iv_ib_bottom_userdata);
		ib_mylottery=(ImageButton) view.findViewById(R.id.iv_ib_bottom_mylottery);
		
		//设置监听事件
		ib_done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				BaseUI baseUI = MiddleManager.getInstance().getCurrentUI();
				if(baseUI instanceof PlaySSQUI)
				{
					
					PlaySSQUI ui=(PlaySSQUI) baseUI;
	
					if(!ui.isEngouh())
					{
						Toast.makeText(view.getContext(), "至少要选择一注", Toast.LENGTH_LONG).show();
						return;
					}
					
					//判断是否需要从网络上获取数据
					if(TitleManager.getInstance().isIssueFlag())
					{
						//获取数据
						ui.done();
						ShopingCar.getInstance().setIssue(TitleManager.getInstance().getIssue());
					}else {
						//没有数据调用ui去获取数据
						ui.getDataFromNet();
						
					}
					
				}
			}
		});
		
		//清空的按钮
		ib_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				BaseUI baseUI = MiddleManager.getInstance().getCurrentUI();
				if(baseUI instanceof PlaySSQUI)
				{
					
					PlaySSQUI ui=(PlaySSQUI) baseUI;
	
					ui.cleanAllData();
				}
			}
		});
		
		//首页与购彩大厅事件
		ib_hall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				MiddleManager.getInstance().clean();
				MiddleManager.getInstance().changeUI("hall", null);
			}
		});
		
		ib_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				MiddleManager.getInstance().clean();
				MiddleManager.getInstance().changeUI("home", null);
			}
		});
		
		//用户信息
		ib_userdata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				if(!GlobalValue.hasLogin){
					MiddleManager.getInstance().changeUI("login", null);
					return;
				}
				
				MiddleManager.getInstance().clean();
				MiddleManager.getInstance().changeUI("userdata", null);
			}
		});
		
		ib_mylottery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				if(!GlobalValue.hasLogin){
					MiddleManager.getInstance().changeUI("login", null);
					return;
				}
				MiddleManager.getInstance().clean();
				MiddleManager.getInstance().changeUI("mylottery", null);
			}
		});
		
	}

	
	
	//隐蔽构造函数
	private BottomManager()
	{
		
	}
	
	//获取实例
	public static BottomManager getInstance()
	{
		return buttomManager;
	}
	
	//转换普通UI
	public void showCommom() {
		// TODO 自动生成的方法存根
		commom_buttom.setVisibility(View.VISIBLE);
		game_buttom.setVisibility(View.GONE);
	}
	
	//转换登陆UI
	public void showGame() {
		commom_buttom.setVisibility(View.GONE);
		game_buttom.setVisibility(View.VISIBLE);
	}

	//观察者更新界面
	@Override
	public void update(Observable observable, Object data) {
		
		Bundle bundle=(Bundle) data;
		
		switch (bundle.getInt("num")) {
		case GlobalValue.Hall:
		case GlobalValue.HOME:
		case GlobalValue.USERDATA:
		case GlobalValue.DETAILLOTTERY:
		case GlobalValue.MYLOTTERY:
			showCommom();
			break;

		case GlobalValue.PlaySSQ:
			showGame();
			break;
			
		case GlobalValue.ShoppingCar:
		case GlobalValue.BET:
			hide();
			break;
			
		case GlobalValue.Login:
			showCommom();
			break;
			
		default:
			break;
		}
		
	}

	//隐藏底部界面
	private void hide() {
		// TODO 自动生成的方法存根
		commom_buttom.setVisibility(View.GONE);
		game_buttom.setVisibility(View.GONE);
	}

	public TextView getTv_gamebottom_text() {
		return tv_gamebottom_text;
	}

	
}
