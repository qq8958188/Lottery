package com.grey_zoo.lottery.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.net.protocal.recelement.RecUserInfoElement;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.view.BaseUI.MyHttpTask;
import com.grey_zoo.lottery.view.ShoppingUI.MyAdapter;
import com.grey_zoo.lottery.view.ShoppingUI.MyAdapter.ViewHolder;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;
import com.tencent.map.b.e;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BetAndBeitouUI extends BaseUI{

	//倍投的增加与删除
	private Button btn_beitou_add;
	private Button btn_beitou_delete;
	
	//追期
	private Button btn_zuiqi_add;
	private Button btn_zuiqi_delete;
	
	//购买
	private ImageButton ib_bet_buy;
	
	//显示的数字
	private TextView tv_beitou;
	private TextView tv_zuiqi;
	
	private int i_zuiqi=1;
	private int i_beitou=1;
	
	//注数和金额、余额
	private TextView tv_zhunum;
	private TextView tv_money;
	//余额
	private double leftMoney;
	
	//listview显示红蓝号
	private ListView lv_show_nums;
	
	//防止重复操作
	private boolean busy=false;
	
	//维护的数据,数据列
	private List<ArrayList<Integer>> list_reds;
	private List<ArrayList<Integer>> list_blues;
	
	int count=0;
	
	//适配器
	private MyAdapter adapter;
	
	//对话框
	private ProgressDialog mProgressDialog = null;
	
	public BetAndBeitouUI(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
		
		//初始化list
		list_blues=ShopingCar.getInstance().getList_blue();
		list_reds=ShopingCar.getInstance().getList_red();
		
		adapter=new MyAdapter();
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
		if (busy) {
			Toast.makeText(context, "请勿重复操作", Toast.LENGTH_LONG).show();
			return;
		}
		
		switch (v.getId()) {
		case R.id.ii_lottery_purchase: //购买
			
			//检测是否够钱
			if(GlobalValue.userMoney<2*ShopingCar.getInstance().getTotalCount()*i_zuiqi*i_beitou)
			{
				Toast.makeText(context, "余额不足购买", Toast.LENGTH_LONG).show();
				return;
			}
			
			System.out.println(2*ShopingCar.getInstance().getTotalCount()*i_zuiqi*i_beitou+"");
			
			System.out.println(GlobalValue.userMoney+"");
			
			//设置数据
			getData();			

			//购买
			new MyHttpTaskSX().execute(GlobalValue.BET);
			
			break;

		case R.id.ii_sub_appnumbers: //减少倍投
			
			if(i_beitou==1)
				return;
			i_beitou--;
			tv_beitou.setText(i_beitou+"");
			upDtaeTextView();
			break;
			
		case R.id.ii_add_appnumbers: //增加倍投
			if(i_beitou==99)
				return;
			i_beitou++;
			tv_beitou.setText(i_beitou+"");
			upDtaeTextView();
			break;
			
		case R.id.ii_sub_issueflagNum: //减少追期
			if(i_zuiqi==1)
				return;
			i_zuiqi--;
			tv_zuiqi.setText(i_zuiqi+"");
			upDtaeTextView();
			break;

		case R.id.ii_add_issueflagNum: //增加追期
			if(i_zuiqi==99)
				return;
			i_zuiqi++;
			tv_zuiqi.setText(i_zuiqi+"");
			upDtaeTextView();
			break;
			
		default:
			break;
		}
		
	}

	private void getData() {
		
		//设置方式
		ShopingCar.getInstance().setLotteryid("118");
		ShopingCar.getInstance().setAppnumbers(tv_zuiqi.getText().toString());
		ShopingCar.getInstance().setIssuesnumbers(tv_beitou.getText().toString());
		
		//调用UserEngin
		count = ShopingCar.getInstance().getTotalCount();
		
	}

	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_betandbeitou, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
//		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		//初始化控件
		init();
		
		return view;
	}

	private void init() {

		//倍投的加减
		btn_beitou_add=(Button) view.findViewById(R.id.ii_sub_appnumbers);
		btn_beitou_delete=(Button) view.findViewById(R.id.ii_add_appnumbers);
		
		//追期的加减
		btn_zuiqi_add=(Button) view.findViewById(R.id.ii_add_issueflagNum);
		btn_zuiqi_delete=(Button) view.findViewById(R.id.ii_sub_issueflagNum);
		
		//购买的按钮
		ib_bet_buy=(ImageButton) view.findViewById(R.id.ii_lottery_purchase);
		
		//显示的数字
		tv_beitou=(TextView) view.findViewById(R.id.ii_appnumbers);
		tv_zuiqi=(TextView) view.findViewById(R.id.ii_issueflagNum);
		
		//金钱与余额
		tv_money=(TextView) view.findViewById(R.id.ii_shopping_list_betting_money);
		tv_zhunum=(TextView) view.findViewById(R.id.ii_shopping_list_betting_num);
		
		//listview的实例化
		lv_show_nums=(ListView) view.findViewById(R.id.ii_lottery_shopping_list);
		lv_show_nums.setAdapter(adapter);
		
		//设置监听事件
		btn_beitou_add.setOnClickListener(this);
		btn_beitou_delete.setOnClickListener(this);
		btn_zuiqi_add.setOnClickListener(this);
		btn_zuiqi_delete.setOnClickListener(this);
		ib_bet_buy.setOnClickListener(this);
		
		//设置Adapter
		
		
		//查询余额
		if(GlobalValue.hasLogin)
		{
//			UserEngin engin=new UserEngin();
//			RecMessage recMessage=engin.getUserInfo();
//			String str1=recMessage.getBody().getOelement().getErrorcode().getValue();
//			if(str1.equals("0"))
//			{
//				RecUserInfoElement userInfoElement=(RecUserInfoElement) 
//						recMessage.getBody().getElementList().get(0);
//				String str=userInfoElement.getInvestvalues().getValue();
//				leftMoney=Double.valueOf(str);
//			}
			
			System.out.println("剩下"+GlobalValue.userMoney);
			
			leftMoney=GlobalValue.userMoney;
			
			/**
			 * 余额成功，继续显示
			 */
			
			upDtaeTextView();
			
		}else {
			Toast.makeText(context, "请先登录", Toast.LENGTH_LONG).show();
			MiddleManager.getInstance().changeUI("login", null);
		}
		
	}

	private void upDtaeTextView() {
		tv_zhunum.setText("注数:"+ShopingCar.getInstance().getTotalCount()*i_zuiqi*i_beitou+"注");
		tv_money.setText("金额:"+ShopingCar.getInstance().getTotalCount()*2*i_zuiqi*i_beitou+"元  账户余额:"+
				leftMoney+"元");
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.BeiTou;
	}

	class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return list_reds.size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			View item;
			ViewHolder viewHolder = null;
			if(convertView!=null)
			{
				item=convertView;
				viewHolder=(ViewHolder) item.getTag();
			}else {
				//生成View
				item=View.inflate(context, R.layout.il_betandbeitou_item, null);
				viewHolder=new ViewHolder();
				viewHolder.tv_rednums = (TextView) item.findViewById(R.id.ii_shopping_item_reds);
				viewHolder.tv_bluenums = (TextView) item.findViewById(R.id.ii_shopping_item_blues);
				item.setTag(viewHolder);
			}
			
			
			//红球的格式化
			StringBuilder sb_r=new StringBuilder();
			
			List<Integer> list_r=list_reds.get(position);
			for(Integer integer:list_r)
			{
				DecimalFormat decimalFormat = new DecimalFormat("00");
//				ball.setText(decimalFormat.format(position + 1));
				sb_r.append(decimalFormat.format(integer)+" ");
			}
			
			//蓝球的格式化
			StringBuilder sb_b=new StringBuilder();
			
			List<Integer> list_b=list_blues.get(position);
			for(Integer integer:list_b)
			{
				DecimalFormat decimalFormat = new DecimalFormat("00");
//				ball.setText(decimalFormat.format(position + 1));
				sb_b.append(decimalFormat.format(integer)+" ");
			}
			
			
			viewHolder.tv_bluenums.setText(sb_b.toString());
			viewHolder.tv_rednums.setText(sb_r.toString());
			
			return item;
		}
		
		class ViewHolder
		{
			TextView tv_rednums;
			TextView tv_bluenums;
		}
		
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		//获取余额信息
		new MyHttpTaskSX().execute(GlobalValue.ShoppingCar);
	}
	
	private class MyHttpTaskSX extends MyHttpTask<Integer>
	{

		//0代表是获取用户信息，1代表是购彩
		int flag=0;

		@Override
		protected RecMessage doInBackground(Integer... params) {
			switch (params[0]) {
			case GlobalValue.ShoppingCar: //查询用户余额
				flag=0;
				UserEngin engin=new UserEngin();
				return engin.getUserInfo();

			case GlobalValue.BET: //投注购买
				//购买
				flag=1;
				engin=new UserEngin();
				RecMessage recMessage = engin.BuyLottery();
				return recMessage;
				
			default:
				
				break;
			}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO 自动生成的方法存根
			super.onPreExecute();
			
			 if (mProgressDialog != null && mProgressDialog.isShowing()) 
			 {
			  mProgressDialog.dismiss();
			 }
			  mProgressDialog = new ProgressDialog(context);
			  mProgressDialog.setMessage("正在努力处理中...");
			  mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			  mProgressDialog.setCancelable(false);
			  mProgressDialog.show();
			
		}
		
		//执行中进行界面的显示
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO 自动生成的方法存根
			super.onProgressUpdate(values);
			
		}
		
		@Override
		protected void onPostExecute(RecMessage recMessage) {

			if(recMessage!=null)
			{
				switch (flag) {
				case 0://获取数据用户信息

					//查询余额
					String str1=recMessage.getBody().getOelement().getErrorcode().getValue();
					if(str1.equals("0"))
					{
						RecUserInfoElement userInfoElement=(RecUserInfoElement) 
								recMessage.getBody().getElementList().get(0);
						String str=userInfoElement.getInvestvalues().getValue();
						leftMoney=GlobalValue.userMoney;
					}
					upDtaeTextView();

					break;

				case 1:

					if(recMessage.getBody().getOelement().getErrorcode().getValue().equals("0"))
					{
						//上传数据并修改金额
						UserEngin userEngin=new UserEngin();
						userEngin.jianshao(count*2*i_zuiqi*i_beitou);
						
						userEngin.setLotteryData();
						
						ShopingCar.getInstance().clean_data();
						
						tv_beitou.setText("1");
						tv_zuiqi.setText("1");
						
						Toast.makeText(context, "购买成功", Toast.LENGTH_LONG).show();
						MiddleManager.getInstance().changeUI("hall", null);
						
					}else {
						
						Toast.makeText(context, "网络有问题，没有购买成功", Toast.LENGTH_LONG).show();
					}
					
					break;
					
				default:
					break;
				}

			}else {
				Toast.makeText(context, "网络连接超时，请稍后再试..", Toast.LENGTH_LONG).show();
			}
			mProgressDialog.dismiss();
		}
	}
	
}
