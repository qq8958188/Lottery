package com.grey_zoo.lottery.util;

import com.grey_zoo.lottery.view.manager.MiddleManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.style.BulletSpan;

public class ViewMessageUtils {

	/**
	 * 弹出警告的对话框
	 */
	public static void showExit(String msg,Context context) {
		//设置bulider
		AlertDialog.Builder builder=new Builder(context);
		
		builder.setMessage(msg);
		builder.setTitle("提示");
		
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				android.os.Process.killProcess(android.os.Process.myPid());
				
			}
		});
		
		builder.setNegativeButton("取消", null);
		
		builder.create().show();
		
	}
	
	/**
	 * 弹出清除数据的对话框
	 */
	public static void showCleanData(Context context) {
		//设置bulider
		AlertDialog.Builder builder=new Builder(context);
		
		builder.setMessage("退出将会清除当前购物车的数据，确定要退出吗？");
		builder.setTitle("提示");
		
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				ShopingCar.getInstance().clean_data();
				
				MiddleManager.getInstance().backToHall();
			}
		});
		
		builder.setNegativeButton("取消", null);
		
		builder.create().show();
		
	}
	
}
