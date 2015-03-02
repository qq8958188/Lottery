package com.grey_zoo.lottery.view;

import cn.bmob.v3.listener.InitListener;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.global.GlobalValue;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;

public class KaicaiUI extends BaseUI {

	private ListView lv;
	
	private MyBaseAdapter adapter=new MyBaseAdapter();
	
	public KaicaiUI(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public View createView() {
		view = View.inflate(context, R.layout.il_kaicai, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	private void init() {
		// TODO 自动生成的方法存根
		lv=(ListView) view.findViewById(R.id.lv);
		
		lv.setAdapter(adapter);
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.KAICAI;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		adapter.notifyDataSetChanged();
	}

	class MyBaseAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return GlobalValue.list_ssqResult.size();
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
			
			TextView tv=new TextView(context);
			
			LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, 125);
			
			tv.setLayoutParams(params);
			
			tv.setText("第"+GlobalValue.list_ssqQS.get(position)+"期："
					+GlobalValue.list_ssqResult.get(position));
			
			tv.setGravity(Gravity.CENTER);
			
			tv.setTextSize(15);
			
			return tv;
		}
		
	}
	
}
