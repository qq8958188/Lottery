package com.grey_zoo.lottery.listener;

import org.json.JSONException;
import org.json.JSONObject;

import com.grey_zoo.lottery.bean.MyUserInfo;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.engin.UserEngin.LoginListener;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class BaseUIListener implements IUiListener {
	private Context mContext;
	private String mScope;
	private boolean mIsCaneled;
	private static final int ON_COMPLETE = 0;
	private static final int ON_ERROR = 1;
	private static final int ON_CANCEL = 2;
	
	private LoginListener listener=null;
	
	private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case ON_COMPLETE:
                JSONObject response = (JSONObject)msg.obj;
                
                if(mScope.equals("get_simple_userinfo")){
                	//如果是获取用户信息，则解析json
                	
                	UserEngin.analyzeUserInfo(response);
                	
                	listener.onSucceed();
                	
                }
                
 //               Util.showResultDialog(mContext, response.toString(), "onComplete");
 //               Util.dismissDialog();
                break;
            case ON_ERROR:
                UiError e = (UiError)msg.obj;
                
                listener.onFail();
                
                break;
            case ON_CANCEL:
                Util.toastMessage((Activity)mContext, "onCancel");
                break;
            }
        }

//		private void analyzeUserInfo(JSONObject response) {
//			// TODO 自动生成的方法存根
//			
//			if(GlobalValue.info==null){
//				GlobalValue.info=new MyUserInfo();
//				String nickName = null;
//				String imgUrl = null;
//				try {
//					nickName=response.getString("nickname");
//					imgUrl=response.getString("figureurl");
//				} catch (JSONException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
//				
//				if(TextUtils.isEmpty(nickName)||TextUtils.isEmpty(imgUrl)){
//					
//					Toast.makeText(GlobalValue.context, "数据出错~~", Toast.LENGTH_SHORT).show();
//					return ;
//				}else {
//					GlobalValue.info.setImgUrl(imgUrl);
//					GlobalValue.info.setNickName(nickName);
//				}
//			}
//			
//		}	    
	};
	
	public BaseUIListener(Context mContext) {
		super();
		this.mContext = mContext;
	}

	
	public BaseUIListener(Context mContext, String mScope, LoginListener listener) {
		super();
		this.mContext = mContext;
		this.mScope = mScope;
		this.listener=listener;
	}
	
	public void cancel() {
		mIsCaneled = true;
	}


	@Override
	public void onComplete(Object response) {
		if (mIsCaneled) return;
	    Message msg = mHandler.obtainMessage();
	    msg.what = ON_COMPLETE;
	    msg.obj = response;
	    mHandler.sendMessage(msg);
	}

	@Override
	public void onError(UiError e) {
		if (mIsCaneled) return;
	    Message msg = mHandler.obtainMessage();
        msg.what = ON_ERROR;
        msg.obj = e;
        mHandler.sendMessage(msg);
	}

	@Override
	public void onCancel() {
		if (mIsCaneled) return;
	    Message msg = mHandler.obtainMessage();
        msg.what = ON_CANCEL;
        mHandler.sendMessage(msg);
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

}
