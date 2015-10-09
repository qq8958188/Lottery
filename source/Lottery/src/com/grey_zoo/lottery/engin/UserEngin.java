package com.grey_zoo.lottery.engin;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.R.bool;
import android.R.integer;
import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Xml;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.grey_zoo.lottery.bean.BmobLotteryData;
import com.grey_zoo.lottery.bean.BmobUserData;
import com.grey_zoo.lottery.bean.MyUserInfo;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.listener.BaseUIListener;
import com.grey_zoo.lottery.net.HttpUtils;
import com.grey_zoo.lottery.net.NetUtil;
import com.grey_zoo.lottery.net.protocal.ElementBase;
import com.grey_zoo.lottery.net.protocal.Leaf;
import com.grey_zoo.lottery.net.protocal.Oelement;
import com.grey_zoo.lottery.net.protocal.RecBody;
import com.grey_zoo.lottery.net.protocal.RecHead;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.SendMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.net.protocal.sendelement.BuyLotteryElement;
import com.grey_zoo.lottery.net.protocal.sendelement.UserLoginElement;
import com.grey_zoo.lottery.util.BmobUtils;
import com.grey_zoo.lottery.util.ConfigSharePreferenceUtils;
import com.grey_zoo.lottery.util.DES;
import com.grey_zoo.lottery.util.ElementFactory;
import com.grey_zoo.lottery.util.JSONUtils;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.util.Util;
import com.grey_zoo.lottery.util.XMLManager;
import com.grey_zoo.lottery.util.XMLManager.ElemnetChange;
import com.grey_zoo.lottery.view.LoginUI;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class UserEngin implements ElemnetChange{
	
	private HttpUtils httpUtils;
	private XMLManager manager;
	
	private String password;
	
	private static ConfigSharePreferenceUtils sharePreferenceUtils=
			new ConfigSharePreferenceUtils(GlobalValue.context);
	
	//设置flag控制changeElement，为0的时候是登陆，1时为
	private int flag=0;
	
	public UserEngin() {
		httpUtils=new HttpUtils();
		manager=new XMLManager(this);
	}
	
	public RecMessage login(String username,String password)
	{
		flag=0;
		
		this.password=password;
		
		String xml = manager.getSendXml("login",username);
		//打印
		GlobalValue.enPrintln(xml);
		/**
		 * 返回信息
		 */
		RecMessage recMessage=null;
		try {
			//发送网络数据
			InputStream inputStream = httpUtils.sendXml(GlobalValue.LOTTERY_URI,xml);
			
			if(inputStream==null)
			{
				return null;
			}
			
			//先进行普通的解析
			recMessage= manager.CommomXmlPullur(inputStream);
			//再进行彩票数据的解析
			recMessage = manager.UserLoginBodyXmlPullur(recMessage);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			GlobalValue.enPrintln("CommomEngin出错");
			recMessage=null;
		}

		//判断是否成功
		return recMessage;
	}

	public RecMessage getUserInfo()
	{
		
		flag=1;
		
		String xml = manager.getSendXml("userInfo",GlobalValue.username);
		/**
		 * 返回信息
		 */
		RecMessage recMessage=null;
		try {
			//发送网络数据
			InputStream inputStream = httpUtils.sendXml(GlobalValue.LOTTERY_URI,xml);
			
			if(inputStream==null)
			{
				return null;
			}
			
			//先进行普通的解析
			recMessage= manager.CommomXmlPullur(inputStream);
			//再进行用户信息的解析
			recMessage = manager.UserInfoXmlPullur(recMessage);
			
			
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			GlobalValue.enPrintln("CommomEngin出错");
			recMessage=null;
		}

		//判断是否成功
		return recMessage;
	}
	
	public RecMessage BuyLottery()
	{
		
		ShopingCar.getInstance().prepare();
		
		flag=3;
		
		String xml = manager.getSendXml("buyLottery",GlobalValue.username);
		/**
		 * 返回信息
		 */
		RecMessage recMessage=null;
		try {
			//发送网络数据
			InputStream inputStream = httpUtils.sendXml(GlobalValue.LOTTERY_URI,xml);
			
			if(inputStream==null)
			{
				return null;
			}
			
			//先进行普通的解析
			recMessage= manager.CommomXmlPullur(inputStream);
			//再进行用户信息的解析
			recMessage = manager.BuyLotteryXmlPullur(recMessage);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			GlobalValue.enPrintln("CommomEngin出错");
			recMessage=null;
		}

		//判断是否成功
		return recMessage;
	}
	
	//根据特定的形式修改element
	@Override
	public ElementBase changeElement(ElementBase base) {
		// TODO 自动生成的方法存根
		if(flag==0)
		{
			//登陆的时候设置密码
			UserLoginElement element=(UserLoginElement) base;
			element.setPassword(password);
		}
		if(flag==1)
		{
			
		}
		if(flag==3)
		{
			//购物的时候设置购物车
			BuyLotteryElement element=(BuyLotteryElement) base;
			element.getAppnumbers().setValue(ShopingCar.getInstance().getAppnumbers());
			element.getBonusstop().setValue(ShopingCar.getInstance().getBonusstop()+"");
			element.getIssue().setValue(ShopingCar.getInstance().getIssue());
			element.getIssueflag().setValue(ShopingCar.getInstance().getIssueflag()+"");
			element.getIssuesnumbers().setValue(ShopingCar.getInstance().getIssuesnumbers());
			element.getLotterycode().setValue(ShopingCar.getInstance().getLotterycode());
			element.getLotteryid().setValue(ShopingCar.getInstance().getLotteryid());
			element.getLotterynumber().setValue(ShopingCar.getInstance().getLotterynumber());
			element.getLotteryvalue().setValue(ShopingCar.getInstance().getLotteryvalue());
			
		}
		return base;
	}
	
	/**
	 * 尝试登陆
	 * @return 如果成功返回True
	 */
	public boolean tryLogin(LoginListener listener){
		
		if(!NetUtil.checkNet(GlobalValue.context)){
			
			Toast.makeText(GlobalValue.context, "请检查网络~~", Toast.LENGTH_SHORT).show();
			
			listener.onFail();
			
			return false;
			
		}
		
		String token = sharePreferenceUtils.getString("token", "");
		String expires = sharePreferenceUtils.getString("expires", "");
		String openId = sharePreferenceUtils.getString("openId", "");
		
		if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
		        && !TextUtils.isEmpty(openId)) {
			GlobalValue.tencent.setAccessToken(token, expires);
			GlobalValue.tencent.setOpenId(openId);
		}
		
		if (GlobalValue.tencent.isSessionValid())
		{
			if(getQQUserInfo(listener))
			{
				GlobalValue.hasLogin=true;
			}
			
			return true;
		}else {
			listener.onFail();
			return false;
		}
	}
	
	/**
	 * 回调关闭界面
	 * @author Administrator
	 *
	 */
	public static interface LoginListener{
		
		void onSucceed();
		
		void onFail();
		
	}
	
	/**
	 * 使用QQ登陆
	 */
	public void qqLogin(){
		//qq登录
		
		if (!GlobalValue.tencent.isSessionValid())
		{
			GlobalValue.tencent.login(GlobalValue.activity, "all", new IUiListener() {
				
				@Override
				public void onError(UiError arg0) {
					// TODO 自动生成的方法存根
					Toast.makeText(GlobalValue.context, "网络错误，请稍后再试~~", 
							Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Object arg0) {
					// TODO 自动生成的方法存根
					
					try {
						JSONObject jsonObject=(JSONObject) arg0;
						
						String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
						String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
						String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
						
						saveToSp(token,expires,openId);
						
						if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
						        && !TextUtils.isEmpty(openId)) {
							GlobalValue.tencent.setAccessToken(token, expires);
							GlobalValue.tencent.setOpenId(openId);
						}
						
						loginSuccess();
						
					} catch (JSONException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				
				private void loginSuccess() {
					// TODO 自动生成的方法存根
					GlobalValue.hasLogin=true;
					//获取用户信息
					MiddleManager.getInstance().pop();
					TitleManager.getInstance().setLoginText(GlobalValue.username);
					
					GlobalValue.userMoney= checkAndGetBmobMoney();
					
				}

				private void saveToSp(String token, String expires,
						String openId) {
					// TODO 自动生成的方法存根
					sharePreferenceUtils.putString("token", token);
					sharePreferenceUtils.putString("expires", expires);
					sharePreferenceUtils.putString("openId", openId);
				}

				@Override
				public void onCancel() {
					// TODO 自动生成的方法存根
					
				}
			});
		}else {
			Toast.makeText(GlobalValue.context, "已经登录", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 获取QQ的个人信息
	 * @param listener 
	 * @return
	 */
	public boolean getQQUserInfo(LoginListener listener){
		
		String token = sharePreferenceUtils.getString("token", "");

		if(token.equals("")){

			return false;
		}
		
		UserInfo info = new UserInfo(GlobalValue.context, GlobalValue.tencent.getQQToken());
		info.getUserInfo(new BaseUIListener(GlobalValue.context,"get_simple_userinfo",listener));
		
		return true;
	}
	
	/**
	 * 解析用户登录数据
	 * @param response
	 */
	public static void analyzeUserInfo(JSONObject response) {
		// TODO 自动生成的方法存根
		
		if(GlobalValue.info==null){
			GlobalValue.info=new MyUserInfo();
			String nickName = null;
			String imgUrl = null;
			String openid = null;
			try {
				nickName=response.getString("nickname");
				imgUrl=response.getString("figureurl_qq_2");
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			if(TextUtils.isEmpty(nickName)||TextUtils.isEmpty(imgUrl)){
				
				Toast.makeText(GlobalValue.context, "数据出错~~", Toast.LENGTH_SHORT).show();
				return ;
			}else {
				GlobalValue.info.setImgUrl(imgUrl);
				GlobalValue.info.setNickName(nickName);
				
				//设置全局的用户名
				GlobalValue.username=nickName;
				
//				getBitmap();
				
			}
		}
		
	}

	public static void getBitmap() {

		if(!NetUtil.checkNet(GlobalValue.context)){

			Toast.makeText(GlobalValue.context, "请检查网络~~", Toast.LENGTH_SHORT).show();

			return ;

		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				Bitmap bitmap = Util.getbitmap(GlobalValue.info.getImgUrl());
				GlobalValue.info.setImgBitmap(bitmap);
			}
		}).run();
	}

	/**
	 * 登出界面
	 */
	public static void loginout() {
		// TODO 自动生成的方法存根
		GlobalValue.tencent.logout(GlobalValue.context);
		GlobalValue.hasLogin=false;
		MiddleManager.getInstance().NoticeAll();
	}
	
	/**
	 * 检测是否创建了，如果没有创建则进行创建，否则获取金额
	 * @return
	 */
	public int checkAndGetBmobMoney(){
		
		
		final String openid = sharePreferenceUtils.getString("openId", "");
		
		BmobQuery<BmobUserData> bmobQuery	 = new BmobQuery<BmobUserData>();
		bmobQuery.addWhereEqualTo("name", openid);
//		bmobQuery.addWhereNotEqualTo("age", 25);
//		bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		// 先从缓存取数据，如果没有的话，再从网络取。
		bmobQuery.findObjects(GlobalValue.context,new FindListener<BmobUserData>() {
			
			@Override
			public void onSuccess(List<BmobUserData> arg0) {
				//成功
				GlobalValue.userMoney=arg0.get(0).getMoney();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				//失败
				BmobUserData userData=new BmobUserData();
				userData.setMoney(0);
				userData.setName(openid);
				userData.save(GlobalValue.context, new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "创建成功", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "创建失败", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		return 0;
	}

	public void chongzhi(final int money) {
		// TODO 自动生成的方法存根

		final String openid = sharePreferenceUtils.getString("openId", "");

		BmobQuery<BmobUserData> bmobQuery	 = new BmobQuery<BmobUserData>();
		bmobQuery.addWhereEqualTo("name", openid);
	
		bmobQuery.findObjects(GlobalValue.context,new FindListener<BmobUserData>() {

			@Override
			public void onSuccess(List<BmobUserData> arg0) {
				//成功
				final BmobUserData userData= arg0.get(0);
				String id=arg0.get(0).getObjectId();
				int orimoney=arg0.get(0).getMoney();
				
				userData.setMoney(orimoney+money);
				
				userData.update(GlobalValue.context,new UpdateListener() {
					
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "充值成功", Toast.LENGTH_SHORT).show();
						GlobalValue.userMoney=userData.getMoney();
						MiddleManager.getInstance().changeUI("userdata", null);
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "充值失败，请稍后再试~~", Toast.LENGTH_SHORT).show();
					}
				});
				
			}

			@Override
			public void onError(int arg0, String arg1) {
				//失败
				Toast.makeText(GlobalValue.context, "充值失败", Toast.LENGTH_LONG).show();
			}
		});

	}
	
	public void jianshao(final int money) {
		// TODO 自动生成的方法存根

		final String openid = sharePreferenceUtils.getString("openId", "");

		BmobQuery<BmobUserData> bmobQuery	 = new BmobQuery<BmobUserData>();
		bmobQuery.addWhereEqualTo("name", openid);
	
		bmobQuery.findObjects(GlobalValue.context,new FindListener<BmobUserData>() {

			@Override
			public void onSuccess(List<BmobUserData> arg0) {
				//成功
				final BmobUserData userData= arg0.get(0);
				String id=arg0.get(0).getObjectId();
				int orimoney=arg0.get(0).getMoney();
				
				userData.setMoney(orimoney-money);
				
				userData.update(GlobalValue.context,new UpdateListener() {
					
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "购买成功", Toast.LENGTH_SHORT).show();
						GlobalValue.userMoney=userData.getMoney();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Toast.makeText(GlobalValue.context, "购买失败，请稍后再试~~", Toast.LENGTH_SHORT).show();
					}
				});
				
			}

			@Override
			public void onError(int arg0, String arg1) {
				//失败
				
			}
		});
	}
	
	public void setLotteryData()
	{
		final String openid = sharePreferenceUtils.getString("openId", "");

		ArrayList<ArrayList<Integer>> blue=ShopingCar.getInstance().getList_blue();
		ArrayList<ArrayList<Integer>> red=ShopingCar.getInstance().getList_red();
		
		String strblue=JSONUtils.ALToJson(blue);
		
		String strred=JSONUtils.ALToJson(red);
		
		BmobLotteryData lotteryData=new BmobLotteryData();
		lotteryData.setIssue(GlobalValue.SSQQS);
		lotteryData.setOpenid(openid);
		lotteryData.setRed(strred);
		lotteryData.setBlue(strblue);
		lotteryData.save(GlobalValue.context, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO 自动生成的方法存根
			//	Toast.makeText(GlobalValue.context, "创建成功", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				System.out.println("创建失败"+arg1);
			//	Toast.makeText(GlobalValue.context, "创建失败", Toast.LENGTH_SHORT).show();
			}
		});

	}
	
	public interface MyLotteryCheck{
		void onSuuccd(List<BmobLotteryData> arg0);
		void onFail();
	}
	
	public void GetMyLottery(final MyLotteryCheck lotteryCheck){
		final String openid = sharePreferenceUtils.getString("openId", "");

		BmobQuery<BmobLotteryData> bmobQuery	 = new BmobQuery<BmobLotteryData>();
		bmobQuery.addWhereEqualTo("openid", openid);
	
		bmobQuery.findObjects(GlobalValue.context,new FindListener<BmobLotteryData>() {

			@Override
			public void onSuccess(List<BmobLotteryData> arg0) {
				//成功
				
				lotteryCheck.onSuuccd(arg0);
				
			}

			@Override
			public void onError(int arg0, String arg1) {
				//失败
				lotteryCheck.onFail();
			}
		});
	}
	
	public void GetDetailLottery(final MyLotteryCheck lotteryCheck,String qs,String time){
		final String openid = sharePreferenceUtils.getString("openId", "");

		BmobQuery<BmobLotteryData> bmobQuery	 = new BmobQuery<BmobLotteryData>();
		bmobQuery.addWhereEqualTo("openid", openid);
		bmobQuery.addWhereEqualTo("issue", qs);
		bmobQuery.addWhereEqualTo("createAt", time);
	
		bmobQuery.findObjects(GlobalValue.context,new FindListener<BmobLotteryData>() {

			@Override
			public void onSuccess(List<BmobLotteryData> arg0) {
				//成功
				
				lotteryCheck.onSuuccd(arg0);
				
			}

			@Override
			public void onError(int arg0, String arg1) {
				//失败
				lotteryCheck.onFail();
			}
		});
	}
	
}
