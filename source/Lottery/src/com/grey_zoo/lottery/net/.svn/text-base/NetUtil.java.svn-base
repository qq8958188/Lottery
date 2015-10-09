package com.grey_zoo.lottery.net;

import java.util.IllegalFormatCodePointException;

import com.grey_zoo.lottery.global.GlobalValue;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.NoCopySpan.Concrete;

public class NetUtil {

	public static boolean checkNet(Context context) {
		//判断是否WIFI连接
		boolean isWiFi=isWiFiConnection(context);
		//判断是否手机连接
		boolean isMOBILE=isMOBILEConnection(context);
		
		if(isMOBILE)
		{
//			readAPN(context);
		}
		
		if(!isWiFi&&!isMOBILE)
		{
			return false;
		}
		
		return true;
	}

	private static void readAPN(Context context) {
		// TODO 自动生成的方法存根
		Uri PREFERRED_APN_URI=Uri.parse("content://telephony/carriers/preferapn");
		ContentResolver resolver=context.getContentResolver();
		Cursor cursor=resolver.query(PREFERRED_APN_URI, null, null, null, null);
		if(cursor!=null&&cursor.moveToFirst())
		{
			GlobalValue.MYPROXY=cursor.getString(cursor.getColumnIndex("proxy"));
			GlobalValue.PORT=cursor.getInt(cursor.getColumnIndex("port"));
		}
	}

	private static boolean isMOBILEConnection(Context context) {
		// TODO 自动生成的方法存根
		
		ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo networkInfo=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if(networkInfo!=null){
			return networkInfo.isConnected();
		}
		
		return false;
	}

	private static boolean isWiFiConnection(Context context) {
		ConnectivityManager manager=(ConnectivityManager)
				context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo=
				manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if(networkInfo!=null){
			return networkInfo.isConnected();
		}

		return false;
	}
	
}
