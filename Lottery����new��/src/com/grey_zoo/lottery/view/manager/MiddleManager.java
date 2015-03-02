package com.grey_zoo.lottery.view.manager;

import java.io.ObjectOutputStream.PutField;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.util.ViewMessageUtils;
import com.grey_zoo.lottery.view.BaseUI;
import com.grey_zoo.lottery.view.factory.MainUIFactory;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MiddleManager extends Observable{

	//模拟栈
	private LinkedList<BaseUI> views;
	//存放对象
	private Map<Integer,BaseUI> viewsMap;
	
	private BaseUI currentUI;
	
	//维护main中的对象
	private LinearLayout linearLayout;

	private boolean load=false;
	//单例
	private static MiddleManager instance=new MiddleManager();
	
	//传输信息到其他界面
	public Bundle bundle;
	
	private MiddleManager()
	{
		views=new LinkedList<BaseUI>();
		viewsMap=new HashMap<Integer, BaseUI>();
	}
	
	public void init(LinearLayout linearLayout) {
		// TODO 自动生成的方法存根
		
		this.linearLayout=linearLayout;

		//添加观察者
		addObserver(TitleManager.getInstance());
		addObserver(BottomManager.getInstance());
		
	}
	
	public static MiddleManager getInstance()
	{
		return instance;
	}
	//压入views
	public void push(BaseUI view)
	{
		viewsMap.put(view.getId(), view);
		views.addFirst(view);
		changeUI();
	}
	
	//改变UI
	private void changeUI() {
		// TODO 自动生成的方法存根
		linearLayout.removeAllViews();
		BaseUI view=views.getFirst();
		linearLayout.addView(view.getView());
		setCurrentUI(view);
	}

	public void pop()
	{
		BaseUI view=views.removeFirst();
		viewsMap.remove(view.getId());
		changeUI();
		NoticeAll();
	}
	
	//给外部调用的
	public void changeUI(String name,Bundle bundle)
	{
		
		//实际上的VIEW
		BaseUI baseUI = MainUIFactory.getInstance(name, linearLayout.getContext());
		
		//currentUI进行退出处理
		if(views.size()>=1)
		{
			currentUI.OnPause();
		}
		
		if(!viewsMap.containsKey(baseUI.getId()))
		{
			//View的控制结构
			push(baseUI);
			
		}
		else {
			baseUI=viewsMap.get(baseUI.getId());
			views.remove(baseUI);
			push(baseUI);
		}
		
		//初始化处理
		currentUI.OnResume();
		
		this.bundle=bundle;
		
		NoticeAll();
		
//		push(baseUI);
		
	}
	
	//观察者模式发送信息
	public void NoticeAll(){
		
		setChanged();
		int num = getCurrentUI().getId();
		if(bundle==null)
		{
			bundle=new Bundle();
		}
		
		bundle.putInt("num", num);
		
		notifyObservers(bundle);
	}
	
	public void onResume()
	{
		changeUI();
	}

	public BaseUI getCurrentUI() {
		return currentUI;
	}

	public void setCurrentUI(BaseUI currentUI) {
		this.currentUI = currentUI;
	}

	public void BackKey() {
		// TODO 自动生成的方法存根
		
		if(currentUI.getId()==GlobalValue.DETAILLOTTERY){
			MiddleManager.getInstance().clean();
			MiddleManager.getInstance().changeUI("mylottery", null);
			return;
		}
		
		if(views.size()==1||currentUI.getId()==GlobalValue.Hall
				||currentUI.getId()==GlobalValue.HOME||currentUI.getId()==GlobalValue.MYLOTTERY
				||currentUI.getId()==GlobalValue.USERDATA)
		{
			
			ViewMessageUtils.showExit("要退出程序吗？", linearLayout.getContext());
			
		}else {
			currentUI.onBack();
		}
		
	}

	public void backToHall() {
		// TODO 自动生成的方法存根
		
		//退出到首页
		while(currentUI.getId()!=GlobalValue.Hall)
		{
			pop();
		}
		
		//清除购物车数据
		ShopingCar.getInstance().clean_data();
		
	}
	
	public void clean()
	{
		while(!(currentUI.getId()==GlobalValue.Hall
				||currentUI.getId()==GlobalValue.HOME||currentUI.getId()==GlobalValue.MYLOTTERY
				||currentUI.getId()==GlobalValue.USERDATA)){
			
			pop();
			
		}
	}
	
}
