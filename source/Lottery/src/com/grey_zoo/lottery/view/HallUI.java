package com.grey_zoo.lottery.view;

import java.util.List;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.HttpUtils;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.PromptManager;
import com.grey_zoo.lottery.view.KaicaiUI.MyBaseAdapter;
import com.grey_zoo.lottery.view.manager.MiddleManager;

import android.R.color;
import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HallUI extends BaseUI {

	public HallUI(Context context) {
		super(context);
		
	}

	private Button btn_kaicai;
	
	private ListView lv;
	
	private MyBaseAdapter adapter=new MyBaseAdapter();
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			PromptManager.closeProgressDialog();
			
			bundle=new Bundle();
			bundle.putString("issue", GlobalValue.SSQQS);
			bundle.putString("lastTime", GlobalValue.SSQQS);
			tv_ssqda.setText("第"+GlobalValue.SSQQS+"期");
			
			lv.setAdapter(adapter);
			
		};
	};
	
	private ImageView btn_ssq_bet;
	private ImageView btn_3d_bet;
	private ImageView btn_qlc_bet;
	
	//传递消息
	private Bundle bundle;
	
	private TextView tv_ssqda;

	private void init() {
		

		btn_ssq_bet=(ImageView) view.findViewById(R.id.iv_hall_iv_ssqbet);
		
		lv=(ListView) view.findViewById(R.id.lv);
		btn_ssq_bet.setOnClickListener(this);
//		btn_qlc_bet.setOnClickListener(this);
//		btn_3d_bet.setOnClickListener(this);
		
		tv_ssqda=(TextView) view.findViewById(R.id.iv_hall_tv_ssqdata);
		
		btn_kaicai=(Button) view.findViewById(R.id.btn_kaicai);
		
		btn_kaicai.setOnClickListener(this);
		
		getDataFromNet();
		
	}

	//从网络上获取数据
	private void getDataFromNet() {
		
	//	new MyHttpTaskSX().executeProxy(GlobalValue.SSQ);
		
		PromptManager.showProgressDialog(GlobalValue.context);
		new Thread() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				
				CommomEngin engin=new CommomEngin();
				
				try {
					GlobalValue.list_ssqResult = engin.getSSQData();
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				handler.sendEmptyMessage(0);
				
			}
		}.start();
		
	}
	
	
	private class MyHttpTaskSX extends MyHttpTask<Integer>
	{

		@Override
		protected RecMessage doInBackground(Integer... params) {
			switch (params[0]) {
			case GlobalValue.SSQ:
				
//				CommomEngin engin=new CommomEngin();
//				return engin.getLotteryInfo("SSQInfo");
				

			default:
				
				break;
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(RecMessage result) {
			
			if(result!=null)
			{
				RecLotteryInfoElement element =(RecLotteryInfoElement) result.getBody().getElementList().get(0);
				bundle=new Bundle();
				bundle.putString("issue", GlobalValue.SSQQS);
				bundle.putString("lastTime", element.getLasttime().getValue());
				tv_ssqda.setText("第"+GlobalValue.SSQQS+"期");
			}
		}
		
	}


	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.iv_hall_iv_ssqbet:
			
			MiddleManager.getInstance().changeUI("PlaySSQ",bundle);
			
			break;

		case R.id.btn_kaicai:
			
	//		MiddleManager.getInstance().changeUI("kaicai", null);
			
			if(lv.getVisibility()==ListView.GONE){
				
				lv.setVisibility(ListView.VISIBLE);
				
				adapter.notifyDataSetChanged();
				
				btn_kaicai.setText("隐藏最近五期开彩结果");
				
			}else {
				
				lv.setVisibility(ListView.GONE);
				
				btn_kaicai.setText("查看最近五期开彩结果");
			}
			
			break;
			
		default:
			//暂未开通
			Toast.makeText(context, "暂未开通此服务，敬请期待", Toast.LENGTH_LONG).show();
			break;
		}
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.Hall;
	}

	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_hall, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
//		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		//初始化控件
		init();
		
		return view;
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
			if(GlobalValue.list_ssqResult == null)
				return 0;
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
			
			TextView tv;
			
			if(convertView==null)
			{
				tv=new TextView(context);
				
				LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, 125);
				
				tv.setLayoutParams(params);
				
				tv.setGravity(Gravity.CENTER);
				
				tv.setTextSize(18);
				
				tv.setTextColor(0XFF880000);
				
			}else {
				tv=(TextView) convertView;
			}
			
			tv.setText("第"+GlobalValue.list_ssqQS.get(position)+"期："
					+GlobalValue.list_ssqResult.get(position));
			
			
			return tv;
		}
		
	}
	
}
