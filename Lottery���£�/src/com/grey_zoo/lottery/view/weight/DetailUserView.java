package com.grey_zoo.lottery.view.weight;


import com.grey_zoo.lottery.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailUserView extends LinearLayout{

	private RoundImageView riv_headphoto;
	private TextView tv_name;
	private TextView tv_money;
	
	public DetailUserView(Context context, AttributeSet attrs) {
		super(context, attrs);

		//把一个布局文件---转换成一个View,并且加载在SettingItemView
		View view = View.inflate(context,R.layout.view_detail_user,this);
		
		riv_headphoto=(RoundImageView) view.findViewById(R.id.riv_headphoto);
		tv_name=(TextView) findViewById(R.id.tv_name);
		
		tv_money = (TextView) findViewById(R.id.tv_money);
	}

	public RoundImageView getRiv_headphoto() {
		return riv_headphoto;
	}

	public TextView getTv_name() {
		return tv_name;
	}

	public TextView getTv_money() {
		return tv_money;
	}

}
