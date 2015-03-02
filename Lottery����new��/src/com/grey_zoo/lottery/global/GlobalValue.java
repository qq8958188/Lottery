package com.grey_zoo.lottery.global;

import java.util.List;

import com.grey_zoo.lottery.bean.MyUserInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;

import android.R.integer;
import android.app.Activity;
import android.content.Context;

public class GlobalValue {

	/**
	 * 发送的订单的ID
	 */
	private static int SendId=0;

	public static int getSendId() {
		return SendId++;
	}
	
	/**
	 * 子代理商的密钥(.so)
	 */
	 public static String AGENTER_PASSWORD = "9ab62a694d8bf6ced1fab6acd48d02f8";
	
	/**
	 * 编码方式
	 */
	 public static String ENCODEING="utf-8";
	 
	 /**
	  * 代理主机号(或者是IP)
	  */
	 public static String MYPROXY=null;
	 
	 /**
	  * 代理的端口号
	  */
	 public static int PORT;
	 
	 /**
	  * 服务器地址
	  */
	 public static String LOTTERY_URI = "http://192.168.1.102:8080/ZCWService/Entrance";
	 
	 /**
	  * 服务器地址
	  */
	 public static String SERVER_URI = "http://192.168.2.103:8080/";
	 
	 /**
	  * 打印的开关
	  */
	 public static boolean openPrintln=true;
	 
	 /**
	  * 可控打印
	  */
	 public static void enPrintln(String str) {
		if(openPrintln)
		{
			System.out.println(str);
		}
	}
	
	 /**
	  * 不可控打印
	  */
	 public static void unPrintln(String str) {
		 System.out.println(str);
	 }
	 
	 /**
	  * des加密用密钥
	  */
	 public static String DES_PASSWORD = "9b2648fcdfbad80f";
	 
	 /**
	  * 双色球的代号
	  */
	 public static final int SSQ=0;
	 
	 /**
	  * 七乐彩的代号
	  */
	 public static final int QLC=1;
	 
	 /**
	  * 福彩3D的代号
	  */
	 public static  int FC3D=2;
	 
	 /**
	  * PlaySSQ的ID
	  */
	 public static final int PlaySSQ=11;
	 
	 /**
	  * Hall的代号
	  */
	 public static final int Hall=12;
	 
	 /**
	  * ShoppingCar的代号
	  */
	 public static final int ShoppingCar=13;
	 
	 /**
	  * Login的代号
	  */
	 public static final int Login=14;

	 
	 /**
	  * bet的代号
	  */
	 public static final int BET = 0;

	 /**
	  * home的代号
	  */
	 public static final int HOME = 19;

	 /**
	  * userdata的代号
	  */
	 public static final int USERDATA = 16;

	 /**
	  * chongzhi的代号
	  */
	 public static final int CHONGZHI = 17;

	 /**
	  * BMOBKEY
	  */
	 public static final String BMOBKEY = "9c5ed345788dfe74913c44fb7c307aed";

	 /**
	  * 开采的代号
	  */
	public static final int KAICAI = 18;

	/**
	 * 我的彩票
	 */
	public static final int MYLOTTERY = 20;

	/**
	 * 具体的彩票
	 */
	public static final int DETAILLOTTERY = 21;
	 
	 /**
	  * 判断是否已经登录
	  */
	 public static boolean hasLogin=false;
	 
	 /**
	  * 用户名
	  */
	 public static String username="";
	 
	 /**
	  * 倍投
	  */
	 public static int BeiTou=15;
	 
	 /**
	  * APPID
	  */
	 public static String APP_ID="1104213735";
	 
	 /**
	  * APPKEY
	  */
	 public static String APP_KEY="nQPPSjzEGmzfOopt";
	 
	 /**
	  * Context
	  */
	 public static Context context=null;
	 
	 /**
	  * Tecent类
	  */
	 public static Tencent tencent=null;
	 
	 /**
	  * Activity
	  */
	 public static Activity activity=null;
	 
	 /**
	  * User的info
	  */
	 public static MyUserInfo info=null;
	 
	 /**
	  * ImageLoader
	  */
	 public static ImageLoader imageLoader;
	 
	 /**
	  * 金额
	  */
	 public static int userMoney=0;
	 
	 /**
	  * 彩票结果
	  */
	 public static String SSQURL="http://f.opencai.net/utf8/ssq.json";
	 
	 /**
	  * times期数
	  */
	 public static String SSQQS=null;
	 
	 /**
	  * List开彩结果
	  */
	 public static List<String> list_ssqResult=null;
	 
	 /**
	  * 期数
	  */
	 public static List<String> list_ssqQS=null;
}
