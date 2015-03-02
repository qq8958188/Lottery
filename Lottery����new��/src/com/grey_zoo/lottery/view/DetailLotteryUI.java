package com.grey_zoo.lottery.view;

import java.util.ArrayList;
import java.util.List;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.bean.BmobLotteryData;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.engin.UserEngin.MyLotteryCheck;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.JSONUtils;
import com.grey_zoo.lottery.util.PromptManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.tencent.connect.avatar.b;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;

public class DetailLotteryUI extends BaseUI {

	private ListView lv;

	private BaseAdapter adapter=new MyBaseAdapter();
	
	/**
	 * 设置数据
	 */
	Handler handler=new Handler()
	{
		public void handleMessage(android.os.Message msg) {
			
			adapter.notifyDataSetChanged();
			
			PromptManager.closeProgressDialog();
			
		};
	};
	
	/**
	 * 期数
	 */
	private String qs;
	
	/**
	 * 时间
	 */
	private String time;
	
	/**
	 * 用户事务处理
	 */
	private UserEngin engin;
	
	/**
	 * blue的list
	 */
	private List<String> list_blue=new ArrayList<String>();
	
	/**
	 * red的list
	 */
	private List<String> list_red=new ArrayList<String>();
	
	public DetailLotteryUI(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根

	}

	@Override
	public View createView() {
		view = View.inflate(context, R.layout.il_detaillottery, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	private void init() {
		// TODO 自动生成的方法存根
		lv=(ListView) view.findViewById(R.id.lv);
		
		qs=(String) MiddleManager.getInstance().bundle.get("qs");
		time=(String) MiddleManager.getInstance().bundle.get("time");
		
		engin=new UserEngin();
		
		getDataFromNet(qs,time);
		
		lv.setAdapter(adapter);
		
	}

	private void getDataFromNet(String qs, String time) {
		// TODO 自动生成的方法存根
		PromptManager.showProgressDialog(GlobalValue.context);
		engin.GetDetailLottery(new MyLotteryCheck() {
			
			@Override
			public void onSuuccd(List<BmobLotteryData> arg0) {
				if(arg0.size()>0)
				{
					// 成功获取数据，进行加载显示
					BmobLotteryData data=arg0.get(0);
					JSONUtils.getDetailLotteryFromJSON(data.getBlue(), data.getRed(), list_blue, list_red);
					handler.sendEmptyMessage(0);
				}else {
					
				}
			}
			
			@Override
			public void onFail() {
				// TODO 自动生成的方法存根
				
			}
		}, qs, time);
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.DETAILLOTTERY;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根

	}

	class MyBaseAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return list_blue.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			TextView tv;
			
			if(convertView!=null){
				tv=(TextView) convertView;
			}else {
				
				tv=new TextView(context);
				
				LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, 125);
				
				tv.setLayoutParams(params);
				
				tv.layout(50, 0, 0, 0);
				
				tv.setGravity(Gravity.CENTER_VERTICAL);
				
				tv.setTextSize(20);
				
				tv.setTextColor(0XFF880000);
				
				tv.setGravity(Gravity.CENTER);
			}
			
			tv.setText(list_red.get(position)+" : "
					+list_blue.get(position));
			
			return tv;
		}
		
	}
	
}
