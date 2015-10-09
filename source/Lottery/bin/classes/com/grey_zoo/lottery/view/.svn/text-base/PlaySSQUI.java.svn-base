package com.grey_zoo.lottery.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.view.BaseUI.MyHttpTask;
import com.grey_zoo.lottery.view.manager.BottomManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaySSQUI extends BaseUI{

	
	private GridView gv_rednums;
	private GridView gv_bluenums;
	
	//红篮球的list
	private List<Integer> redNums;
	private List<Integer> blueNums;
	
	//判断是否已选够一注
	private boolean engouh=false;
	
	//机选蓝球与红球
	private Button btn_auto_red;
	private Button btn_auto_blue;
	
	//适配器，方便以后更新
	private ContainerAdapter redAdapter;
	private ContainerAdapter blueAdapter;
	
	//加速度传感器
	private SensorManager manager;
	private ShakeListener listener;
	
	//双色球的期号
	private String issue;
	
	//shopingcar的数据
	//维护的数据,数据列
	private List<ArrayList<Integer>> list_reds;
	private List<ArrayList<Integer>> list_blues;
	
	//对话框
	private ProgressDialog mProgressDialog = null;
	
	//震动
	private Vibrator vibrator;
	
	public PlaySSQUI(Context context) {
		super(context);
		
		redNums=new ArrayList<Integer>();
		blueNums=new ArrayList<Integer>();
		
		//获取两个队列
		list_blues=ShopingCar.getInstance().getList_blue();
		list_reds=ShopingCar.getInstance().getList_red();
		
		//初始化震动服务
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		
		//获取传感器服务器
		manager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		//注册监听事件
		listener=new ShakeListener() {
			
			@Override
			protected void ringAndSelectNum() {
				// TODO 自动生成的方法存根
				Random random=new Random();
				//自动选择红篮球
				autoSelectBlueNums(random);
				autoSelectRedNums(random);
				
				//震动
				vibrator.vibrate(500);
				
			}
		};
		
		
	}

	private void init() {
		
		//GRIDVIEW获取对象
		gv_rednums=(GridView) view.findViewById(R.id.iv_gv_playssq_redcontainer);
		gv_bluenums=(GridView) view.findViewById(R.id.iv_gv_playssq_bluecontainer);
		//红球选择适配器
		redAdapter = new ContainerAdapter(33, R.drawable.id_redball,redNums);
		gv_rednums.setAdapter(redAdapter);
		//蓝球选择适配器
		blueAdapter=new ContainerAdapter(16, R.drawable.id_blueball,blueNums);
		gv_bluenums.setAdapter(blueAdapter);
		//自动选号的按钮
		btn_auto_red= (Button) view.findViewById(R.id.iv_playssq_autorednums);
		btn_auto_blue= (Button) view.findViewById(R.id.iv_playssq_autovbluenums);
		
		setOnClick();
	}

	//点击事件
	@Override
	public void onClick(View v) {
		
		//随机选号
		Random random=new Random();
		
		switch (v.getId()) {
		case R.id.iv_playssq_autorednums:
			
			autoSelectRedNums(random);
			break;
			
		case R.id.iv_playssq_autovbluenums:
			
			autoSelectBlueNums(random);
			
			break;

		default:
			break;
		}
		changeNotice();
	}

	private void autoSelectBlueNums(Random random) {
		blueNums.clear();
		//机选
		while(blueNums.size()<1)
		{
			int num = random.nextInt(16)+1;
			if(!blueNums.contains(num))
			{
				blueNums.add(num);
			}
		}

		blueAdapter.notifyDataSetChanged();
	}

	private void autoSelectRedNums(Random random) {
		redNums.clear();
		//机选
		while(redNums.size()<6)
		{
			int num = random.nextInt(33)+1;
			if(!redNums.contains(num))
			{
				redNums.add(num);
			}
		}
		redAdapter.notifyDataSetChanged();
	}

	
	class ContainerAdapter extends BaseAdapter
	{

		//维护数目
		private int num;
		private int reid;
		private List<Integer> list;
		
		public ContainerAdapter(int num,int reid,List<Integer> list) {
			this.num=num;
			this.reid=reid;
			this.list=list;
		}
		
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return num;
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
			tv.setText(String.valueOf(position+1));
			if(list.contains(position+1))
			{
				tv.setBackgroundResource(reid);
			}else {
				tv.setBackgroundResource(R.drawable.id_defalut_ball);
			}
			tv.setGravity(Gravity.CENTER);
//			tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//					ViewGroup.LayoutParams.WRAP_CONTENT));
			
			return tv;
		}
		
		//更新list
		public void reflash(List<Integer> list)
		{
			this.list=list;
		}
		
	}

	private void setOnClick(){
		
		//选择红绿球
		gv_rednums.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				// 当小于16个的时候可选
				
				if(redNums.size()<12)
				{
					if(!redNums.contains(position+1))
					{
						redNums.add(position+1);
						view.setBackgroundResource(R.drawable.id_redball);
					}else {
						redNums.remove((Integer)(position+1));
						view.setBackgroundResource(R.drawable.id_defalut_ball);
					}
				}
				changeNotice();
			}

		});
		
		//选蓝球
		gv_bluenums.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				if(blueNums.size()<2)
				{
					if(!blueNums.contains(position+1))
					{
						blueNums.add(position+1);
						view.setBackgroundResource(R.drawable.id_blueball);
					}else {
						blueNums.remove((Integer)(position+1));
						view.setBackgroundResource(R.drawable.id_defalut_ball);
					}
				}
				changeNotice();
			}
		});
		
		//机选的点击事件
		btn_auto_red.setOnClickListener(this);
		btn_auto_blue.setOnClickListener(this);
		
	}

	@Override
	public Integer getId() {
		return GlobalValue.PlaySSQ;
	}

	//创建View
	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_playssq, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		init();
		
		return view;
	}
	
	//改变BOTTOM的底部信息
	private void changeNotice() {
		// TODO 自动生成的方法存根
		
		setEngouh(false);
		
		String message = null;
		if(redNums.size()<6)
		{
			message="您还需要选择"+(6-redNums.size())+"个红球";
		}else {
			if(blueNums.size()<1)
			{
				message="您还需要选择"+(1-blueNums.size())+"个蓝球";
			}else {
				setEngouh(true);
				int count =lincheng(redNums.size(), 6)*blueNums.size();
				message="共"+count+"注,共"+2*count+"元";
			}
		}
		
		BottomManager.getInstance().getTv_gamebottom_text().setText(message);
		
	}
	
	
	//计算集合C63
	private int lincheng(int num1,int num2)
	{
		int result=0;
		int result1=1;
		int result2=1;
		
		for(int i=num1;i>num1-num2;--i)
		{
			result1=result1*i;
		}
		
		for(int i=num2;i>1;--i)
		{
			result2=result2*i;
		}
		
		result=result1/result2;
		
		return result;
	}
	
	//提交数据给购物车，并跳转界面
	public void done()
	{
		//加入红球
		list_blues.add((ArrayList<Integer>) blueNums);
		//加入蓝球
		list_reds.add((ArrayList<Integer>) redNums);
		//设置期号
		ShopingCar.getInstance().setIssue(issue);
		
		MiddleManager.getInstance().changeUI("shoping", null);
		
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根
		
		manager.unregisterListener(listener);
		
		//创建对象
		redNums=new ArrayList<Integer>();
		blueNums=new ArrayList<Integer>();
		
		redAdapter.reflash(redNums);
		blueAdapter.reflash(blueNums);
		
	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		
		//足够变为假
		setEngouh(false);
		
//		redNums.clear();
//		blueNums.clear();
		
		redAdapter.notifyDataSetChanged();
		blueAdapter.notifyDataSetChanged();
		
		//注册
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
		
		
	}

	public boolean isEngouh() {
		return engouh;
	}

	private void setEngouh(boolean engouh) {
		this.engouh = engouh;
	}
	
	public void cleanAllData()
	{
		setEngouh(false);

		//清除所有的数据
		redNums.clear();
		blueNums.clear();

		//改变显示的数据
		redAdapter.notifyDataSetChanged();
		blueAdapter.notifyDataSetChanged();
		
		//改变底部的提示
		changeNotice();
	}
	
	private class MyHttpTaskSX extends MyHttpTask<Integer>
	{

		@Override
		protected RecMessage doInBackground(Integer... params) {
			switch (params[0]) {
			case GlobalValue.SSQ:
				
				CommomEngin engin=new CommomEngin();
				return engin.getLotteryInfo("SSQInfo");
				

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
		protected void onPostExecute(RecMessage result) {

			if(result!=null)
			{
				RecLotteryInfoElement element =(RecLotteryInfoElement) result.getBody().getElementList().get(0);
				TitleManager.getInstance().setIssue(element.getIssue().getValue());
				
				//执行下一步
				done();
				ShopingCar.getInstance().setIssue(TitleManager.getInstance().getIssue());

			}else {
				Toast.makeText(context, "网络连接超时，请稍后再试..", Toast.LENGTH_LONG).show();
			}
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 获取issue等信息
	 * @return 
	 */
	public void getDataFromNet()
	{		
		new MyHttpTaskSX().executeProxy(GlobalValue.SSQ);
	}
	
}

