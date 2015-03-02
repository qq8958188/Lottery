package com.grey_zoo.lottery.view.manager;

import java.util.Observable;
import java.util.Observer;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.global.GlobalValue;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TitleManager implements Observer{

	//单例设计模式
	private static TitleManager buttomManager=new TitleManager();
	
//	维护一个View对象
	private View view;
	
	//维护title对象
	private RelativeLayout commom_title;
	private RelativeLayout unlogin_title;
	private RelativeLayout login_title;
	
	//账号信息
	private TextView tv_zhanghao;
	
	//维护期号
	private String issue;
	
	//后退键iv_title_goback
	private ImageView iv_goback;
	
	//comomtitle的标题
	private TextView tv_comTitle;

	private ImageView iv_login;
	private ImageView iv_register;
	
	private boolean issueFlag=false;
	
	
	//设置View
	public void setView(View view)
	{
		this.view=view;
		
		init();
	}
	
	//隐蔽构造函数
	private TitleManager()
	{
		
	}
	
	public void init() {
		// TODO 自动生成的方法存根
		//获取实例
		commom_title=(RelativeLayout) view.findViewById(R.id.id_title_commom);
		unlogin_title=(RelativeLayout) view.findViewById(R.id.iv_title_unlogin);
		login_title=(RelativeLayout) view.findViewById(R.id.iv_title_login);
		tv_comTitle=(TextView) view.findViewById(R.id.iv_title_text);
		iv_login=(ImageView) view.findViewById(R.id.iv_btn_titlelogin);
		tv_zhanghao=(TextView) view.findViewById(R.id.iv_title_tv_zhanghao);
		iv_goback=(ImageView) view.findViewById(R.id.iv_title_goback);
		iv_register=(ImageView) view.findViewById(R.id.iv_btn_titleregist);
		
		
		//登陆
		iv_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 弹出登陆界面
				MiddleManager.getInstance().changeUI("login", null);
			}
		});
		
		//后退
		iv_goback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				MiddleManager.getInstance().BackKey();
			}
		});
		
		//注册
		iv_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(GlobalValue.context, "亲，无需注册喔~~，直接使用QQ账号登陆吧~~", Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//获取实例
	public static TitleManager getInstance()
	{
		return buttomManager;
	}
	
	//转换普通UI
	public void showCommom() {
		// TODO 自动生成的方法存根
		unlogin_title.setVisibility(View.GONE);
		login_title.setVisibility(View.GONE);
		commom_title.setVisibility(View.VISIBLE);
	}
	
	//转换登陆UI
	public void showLogin() {
		unlogin_title.setVisibility(View.GONE);
		login_title.setVisibility(View.VISIBLE);
		commom_title.setVisibility(View.GONE);
	}
	
	//转换未登陆UI
	public void showUnLogin() {
		unlogin_title.setVisibility(View.VISIBLE);
		login_title.setVisibility(View.GONE);
		commom_title.setVisibility(View.GONE);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO 自动生成的方法存根
		Bundle bundle=(Bundle) data;
		
		switch (bundle.getInt("num")) {
		case GlobalValue.Hall:
		case GlobalValue.HOME:
			if(GlobalValue.hasLogin)
			{
				showLogin();
				break;
			}
			showUnLogin();
			break;

		case GlobalValue.PlaySSQ:
			showCommom();
			changeCommonTitle(bundle);
			break;
			
		case GlobalValue.ShoppingCar:
		case GlobalValue.BET:
			showCommom();
			break;
			
		case GlobalValue.Login:
			showCommom();
			break;
			
		default:
			break;
		}
	}

	private void changeCommonTitle(Bundle bundle) {
		// TODO 自动生成的方法存根
		String lastTime=bundle.getString("lastTime");
		setIssue(bundle.getString("issue"));
		//如果有消息传递过来
		if(lastTime!=null&&getIssue()!=null)
		{
			setIssueFlag(true);
			
			tv_comTitle.setText("第"+getIssue()+"期双色球");
		}
		else {
			setIssueFlag(false);
			
			tv_comTitle.setText("欢迎购买双色球");
		}
		
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
		tv_comTitle.setText("第"+getIssue()+"期双色球");
	}

	public boolean isIssueFlag() {
		return issueFlag;
	}

	public void setIssueFlag(boolean issueFlag) {
		this.issueFlag = issueFlag;
	}
	
	public void setLoginText(String text)
	{
		tv_zhanghao.setText(text+",用户您好");
	}
	
}
