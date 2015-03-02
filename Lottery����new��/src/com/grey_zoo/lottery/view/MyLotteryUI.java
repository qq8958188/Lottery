package com.grey_zoo.lottery.view;

import java.util.ArrayList;
import java.util.List;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.bean.BmobLotteryData;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.engin.UserEngin.MyLotteryCheck;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.PromptManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.weight.RefreshableListView;
import com.grey_zoo.lottery.view.weight.RefreshableListView.PullToRefreshListener;
import com.grey_zoo.lottery.view.weight.RefreshableView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;

public class MyLotteryUI extends BaseUI {

	private ListView lv;
	
	private RefreshableListView refreshableView;
	
	private List<String> list_qs=new ArrayList<String>();
	
	private List<String> list_time=new ArrayList<String>();
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			//完成
			case 0:
		//		synchronized (MyLotteryUI.class) {
					adapter.notifyDataSetChanged();
		//		}
				PromptManager.closeProgressDialog();
				break;

			//弹出
			case 1:
				PromptManager.showProgressDialog(GlobalValue.context);
				break;
				
			default:
				break;
			}
			
		};
	};
	
	MyBaseAdapter adapter = new MyBaseAdapter();
	
	public MyLotteryUI(Context context) {
		super(context);
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	public View createView() {
		view = View.inflate(context, R.layout.il_mylottery, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	private void init() {
		// TODO 自动生成的方法存根
		
		refreshableView = (RefreshableListView) view.findViewById(R.id.refreshable_view);
		
		lv=(ListView) view.findViewById(R.id.lv);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Bundle bundle=new Bundle();
				bundle.putString("qs", list_qs.get(position));
				bundle.putString("time", list_time.get(position));
				MiddleManager.getInstance().changeUI("detaillottery", bundle);
			}
		});
		
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				getDataFromNet();
				refreshableView.finishRefreshing();
			}
		}, 0);
		
		getDataFromNet();
		
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.MYLOTTERY;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void OnResume() {
//		adapter.notifyDataSetChanged();
	}

	private void getDataFromNet()
	{
		//		PromptManager.showProgressDialog(GlobalValue.context);
		//		new Thread() {
		//			
		//			@Override
		//			public void run() {
		//				// TODO 自动生成的方法存根

		//清空原来的
//		list_qs.clear();
//		list_time.clear();
		
		handler.sendEmptyMessage(1);

		UserEngin engin=new UserEngin();
		engin.GetMyLottery(new MyLotteryCheck() {

			@Override
			public void onSuuccd(List<BmobLotteryData> arg0) {
				
				if(arg0.size()==0)
				{
					return ;
				}
				
				list_qs.clear();
				list_time.clear();
				
				// TODO 自动生成的方法存根
				for(BmobLotteryData lotteryData:arg0){

					list_qs.add(lotteryData.getIssue());

					list_time.add(lotteryData.getCreatedAt());

					handler.sendEmptyMessage(0);

				}
			}

			@Override
			public void onFail() {
				// TODO 自动生成的方法存根
				Toast.makeText(GlobalValue.context, "查找失败，请稍后再试", Toast.LENGTH_SHORT).show();
			}

		});

		//			}
		//		}.start();
	}
	
	class MyBaseAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return list_qs.size();
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
			
//			TextView tv=new TextView(context);
//			
//			LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, 125);
//			
//			tv.setLayoutParams(params);
//			
//			tv.setText("第"+GlobalValue.list_ssqQS.get(position)+"期："
//					+GlobalValue.list_ssqResult.get(position));
//			
//			tv.setGravity(Gravity.CENTER);
//			
//			tv.setTextSize(15);
			
			ViewHolder holder;
			
			View view;
			
			if(convertView!=null)
			{
				view=convertView;
				holder=(ViewHolder) view.getTag();
			}else {
				view=View.inflate(GlobalValue.context, R.layout.item_mylottery, null);
				holder=new ViewHolder();
				holder.tv_qs=(TextView) view.findViewById(R.id.tv_qs);
				holder.tv_rq=(TextView) view.findViewById(R.id.tv_rq);
				view.setTag(holder);
			}
			
//			if(list_qs.size()<position+1)
//			{
//				//Toast.makeText(GlobalValue.context, "加载异常", Toast.LENGTH_SHORT).show();
//				holder.tv_qs.setText("加载异常");
//				
//				holder.tv_rq.setText("加载异常");
//				return view;
//			}
			
			holder.tv_qs.setText(list_qs.get(position));
			
			holder.tv_rq.setText(list_time.get(position));
			
			return view;
		}
		
	}
	
	class ViewHolder{
		TextView tv_qs;
		TextView tv_rq;
	}
	
}
