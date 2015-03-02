package com.grey_zoo.lottery.view;

import org.json.JSONException;
import org.json.JSONObject;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.listener.BaseUIListener;
import com.grey_zoo.lottery.net.NetUtil;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.ConfigSharePreferenceUtils;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.view.BaseUI.MyHttpTask;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUINew extends BaseUI {
	
	//对话框
	private Button btn_qqlogin;
	
	private ConfigSharePreferenceUtils sharePreferenceUtils;
	
	private UserEngin userEngin=new UserEngin();
	
	public LoginUINew(Context context) {
		super(context);

		//保存
		sharePreferenceUtils=
				new ConfigSharePreferenceUtils(GlobalValue.context);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_qqlogin_userloginui:
			
			if(!NetUtil.checkNet(GlobalValue.context)){
				
				Toast.makeText(GlobalValue.context, "请检查网络~~", Toast.LENGTH_SHORT).show();
				
				break;
				
			}
			
//			//qq登录
//			
//			if (!GlobalValue.tencent.isSessionValid())
//			{
//				GlobalValue.tencent.login(GlobalValue.activity, "all", new IUiListener() {
//					
//					@Override
//					public void onError(UiError arg0) {
//						// TODO 自动生成的方法存根
//						
//					}
//					
//					@Override
//					public void onComplete(Object arg0) {
//						// TODO 自动生成的方法存根
//						
//						try {
//							JSONObject jsonObject=(JSONObject) arg0;
//							
//							String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
//							String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
//							String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
//							
//							saveToSp(token,expires,openId);
//							
//							if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
//							        && !TextUtils.isEmpty(openId)) {
//								GlobalValue.tencent.setAccessToken(token, expires);
//								GlobalValue.tencent.setOpenId(openId);
//							}
//							
//							loginSuccess();
//							
//						} catch (JSONException e) {
//							// TODO 自动生成的 catch 块
//							e.printStackTrace();
//						}
//					}
//					
//					private void loginSuccess() {
//						// TODO 自动生成的方法存根
//						GlobalValue.hasLogin=true;
//						//获取用户信息
//						MiddleManager.getInstance().pop();
//						TitleManager.getInstance().setLoginText(GlobalValue.username);
//					}
//
//					private void saveToSp(String token, String expires,
//							String openId) {
//						// TODO 自动生成的方法存根
//						sharePreferenceUtils.putString("token", token);
//						sharePreferenceUtils.putString("expires", expires);
//						sharePreferenceUtils.putString("openId", openId);
//					}
//
//					@Override
//					public void onCancel() {
//						// TODO 自动生成的方法存根
//						
//					}
//				});
//			};
			
			userEngin.qqLogin();
			
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_user_login_new, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
//		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		//初始化控件
		init();
		
		return view;
	}

	private void init() {

		//实例化
		btn_qqlogin=(Button) view.findViewById(R.id.btn_qqlogin_userloginui);
		
		//设置监听事件
		btn_qqlogin.setOnClickListener(this);
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
	
}
