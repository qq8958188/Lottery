package com.grey_zoo.lottery.view;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.view.BaseUI.MyHttpTask;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUI extends BaseUI {

	//账号与密码
	private EditText et_username;
	private EditText et_password;
	
	//对话框
	private ProgressDialog mProgressDialog = null;
	
	//登陆、注册按钮
	private Button btn_login;
	private Button btn_register;
	
	public LoginUI(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ii_user_login:
			
			new MyHttpTaskSX().executeProxy(GlobalValue.Login);
			
			break;
			
		case R.id.ii_user_login_regist:
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_user_login, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
//		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		//初始化控件
		init();
		
		return view;
	}

	private void init() {

		//实例化
		et_username=(EditText) view.findViewById(R.id.ii_user_login_username);
		et_password=(EditText) view.findViewById(R.id.ii_user_login_password);
		btn_login=(Button) view.findViewById(R.id.ii_user_login);
		btn_register=(Button) view.findViewById(R.id.ii_user_login_regist);
		
		//设置监听事件
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.Login;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		
	}
	

	private class MyHttpTaskSX extends MyHttpTask<Integer>
	{

		@Override
		protected RecMessage doInBackground(Integer... params) {
			switch (params[0]) {
			case GlobalValue.Login:
				//用户引擎
				UserEngin engin=new UserEngin();
				//获取返回数据
				RecMessage recMessage = engin.login(et_username.getText().toString(), et_password.getText().toString());
				
				return recMessage;

			default:
				
				break;
			}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO 自动生成的方法存根
			super.onPreExecute();
			
			 if (mProgressDialog != null && mProgressDialog.isShowing()) 
			 {
			  mProgressDialog.dismiss();
			 }
			  mProgressDialog = new ProgressDialog(context);
			  mProgressDialog.setMessage("正在努力处理中...");
			  mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			  mProgressDialog.setCancelable(false);
			  mProgressDialog.show();
			
		}
		
		//执行中进行界面的显示
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO 自动生成的方法存根
			super.onProgressUpdate(values);
			
		}
		
		@Override
		protected void onPostExecute(RecMessage recMessage) {

			if(recMessage!=null)
			{
				//处理返回的数据
				String str1 = recMessage.getBody().getOelement().getErrorcode().getValue();
				if(recMessage!=null&&str1.equals("0"))
				{
					Toast.makeText(context, "登陆成功", Toast.LENGTH_LONG).show();
					GlobalValue.hasLogin=true;
					GlobalValue.username=et_username.getText().toString();
					MiddleManager.getInstance().pop();
					TitleManager.getInstance().setLoginText(GlobalValue.username);
				}

			}else {
				Toast.makeText(context, "网络连接超时，请稍后再试..", Toast.LENGTH_LONG).show();
			}
			mProgressDialog.dismiss();
		}
	}
	
}
