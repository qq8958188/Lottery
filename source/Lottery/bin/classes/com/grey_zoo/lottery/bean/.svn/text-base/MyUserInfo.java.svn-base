package com.grey_zoo.lottery.bean;

import android.graphics.Bitmap;

public class MyUserInfo {

	//图片网络地址
	private String imgUrl;
	//昵称
	private String nickName;
	//头像
	private Bitmap imgBitmap;
	//账号
	private String openid;
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public synchronized Bitmap getImgBitmap() {
		return imgBitmap;
	}
	
	public synchronized void setImgBitmap(Bitmap imgBitmap) {
		this.imgBitmap = imgBitmap;
	}

	public String getOpenid() {
		return openid;
	}

	public synchronized void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
