package com.grey_zoo.lottery.view;

import com.grey_zoo.lottery.net.NetUtil;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.view.manager.MiddleManager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public abstract class BaseUI implements OnClickListener{

	protected Context context;
	
	//VIEW的本对象
	protected View view;
	
	//是否已经生成了VIEW
	protected boolean isViewCre=false;
	
	public BaseUI(Context context) {
		super();
		this.context = context;
		
	}
	
	public abstract View createView() ;
	
	public View getView()
	{
		if(!isViewCre)
		{
			view=createView();
			if(view!=null)
			{
				isViewCre=true;
			}
		}
		
		return view;
	}
	
	public abstract class MyHttpTask<Params> extends AsyncTask<Params, Void, RecMessage>{
		
		public final AsyncTask<Params, Void, RecMessage> executeProxy(
				Params... params) {
			if (NetUtil.checkNet(context)) {
				return super.execute(params);
			} else {
				Toast.makeText(context, "网络连接不可用", Toast.LENGTH_LONG).show();
			}
			return null;
		}
	}
	
	public abstract Integer getId();
	
	//暂停
	public abstract void OnPause();
	
	//回复
	public abstract void OnResume();

	//一般情况下的后退键
	public void onBack() {
		// TODO 自动生成的方法存根
		OnPause();
		MiddleManager.getInstance().pop();
		OnResume();
	}
	
}
