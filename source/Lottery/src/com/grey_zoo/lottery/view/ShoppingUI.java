package com.grey_zoo.lottery.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.CommomEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.net.protocal.RecMessage;
import com.grey_zoo.lottery.net.protocal.recelement.RecLotteryInfoElement;
import com.grey_zoo.lottery.util.ShopingCar;
import com.grey_zoo.lottery.util.ViewMessageUtils;
import com.grey_zoo.lottery.view.BaseUI.MyHttpTask;
import com.grey_zoo.lottery.view.manager.BottomManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.manager.TitleManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingUI extends BaseUI {

	//控件
	//增加人工选号
	private Button btn_add_optionalnums;
	//增加机选号
	private Button btn_add_randomnums;
	//清除list
	private Button btn_clean_list;
	//列表
	private ListView lv_nums;
	//购买
	private Button btn_buy;
	//适配器
	private MyAdapter adapter;
	
	//清空数据iv_btn_shopcar_clearlist
	private Button btn_clean_data;
	
	//发起合买iv_btn_shopcar_hemai
	private Button btn_hemai;
	
	//对话框
	private ProgressDialog mProgressDialog = null;
	
	//维护的数据,数据列
	private List<ArrayList<Integer>> list_reds;
	private List<ArrayList<Integer>> list_blues;
	
	public ShoppingUI(Context context) {
		super(context);
		
		//获取两个队列
		list_blues=ShopingCar.getInstance().getList_blue();
		list_reds=ShopingCar.getInstance().getList_red();
		
		adapter=new MyAdapter();
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根

		switch (v.getId()) {
		case R.id.iv_btn_shopcar_buy: //购买，先判断是否登陆
			if(lv_nums.getCount()<1)
			{
				Toast.makeText(context, "请至少选择一注", Toast.LENGTH_LONG).show();
				return;
			}
			if(!GlobalValue.hasLogin)
			{
				MiddleManager.getInstance().changeUI("login", null);
			}else {
				
				MiddleManager.getInstance().changeUI("beitou", null);
			}
			break;

		case R.id.iv_btn_shopcar_clearlist:
			//清除数据
			list_blues.clear();
			list_reds.clear();
			//更新数据
			adapter.notifyDataSetChanged();
			break;
			
		case R.id.iv_btn_shopcar_add_optionalnum://添加人工选号
			
			//切换到人工选择的界面
			MiddleManager.getInstance().changeUI("PlaySSQ", null);			
			
			break;
			
		case R.id.iv_btn_shopcar_add_randomnum://添加自动选号
			
			//自动添加红篮球
			Random random=new Random();
			autoSelectBlueNums(random);
			autoSelectRedNums(random);
			adapter.notifyDataSetChanged();
			
			break;
			
		case R.id.iv_btn_shopcar_hemai: //发起合买
			
			Toast.makeText(context, "该功能还没推出，敬请期待..", Toast.LENGTH_LONG).show();
			
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public View createView() {
		view=View.inflate(context, R.layout.il_shoppingcar, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
//		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		//初始化控件
		init();
		
		return view;
	}

	private void init() {
		// TODO 自动生成的方法存根
		
		btn_clean_list=(Button) view.findViewById(R.id.iv_btn_shopcar_clearlist);
		btn_add_optionalnums=(Button) view.findViewById(R.id.iv_btn_shopcar_add_optionalnum);
		btn_add_randomnums=(Button) view.findViewById(R.id.iv_btn_shopcar_add_randomnum);
		btn_buy=(Button) view.findViewById(R.id.iv_btn_shopcar_buy);
		lv_nums=(ListView) view.findViewById(R.id.iv_lv_shopcar_list);
		btn_hemai=(Button) view.findViewById(R.id.iv_btn_shopcar_hemai);
		
		
		//设置监听
		btn_add_optionalnums.setOnClickListener(this);
		btn_add_randomnums.setOnClickListener(this);
		btn_buy.setOnClickListener(this);
		btn_clean_list.setOnClickListener(this);
		btn_hemai.setOnClickListener(this);
		
		
		//设置适配器
		lv_nums.setAdapter(adapter);
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.ShoppingCar;
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
				item=View.inflate(context, R.layout.il_shoppingcar_item, null);
				viewHolder=new ViewHolder();
				viewHolder.ib_delete = (ImageButton) item.findViewById(R.id.iv_tv_shopcaritem_delete);
				viewHolder.tv_rednums = (TextView) item.findViewById(R.id.iv_tv_shopcaritem_rednums);
				viewHolder.tv_bluenums = (TextView) item.findViewById(R.id.iv_tv_shopcaritem_bluenums);
				item.setTag(viewHolder);
			}
			
			//设置细节
			viewHolder.ib_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					list_reds.remove(position);
					list_blues.remove(position);
					notifyDataSetChanged();
					changeNotice();
				}
			});
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
			ImageButton ib_delete;
			TextView tv_rednums;
			TextView tv_bluenums;
		}
		
	}
	
	private void changeNotice()
	{
		BottomManager.getInstance().getTv_gamebottom_text().setText("共"+ShopingCar.getInstance().getTotalCount()
				+"注，共"+2*ShopingCar.getInstance().getTotalCount()+"元");
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
	
	//自动选择红球
	private void autoSelectBlueNums(Random random) {
		List<Integer> blueNums=new ArrayList<Integer>();
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

//		blueAdapter.notifyDataSetChanged();
		
		//添加到数据中
		list_blues.add((ArrayList<Integer>) blueNums);
		
	}

	//自动选择蓝球
	private void autoSelectRedNums(Random random) {
		List<Integer> redNums=new ArrayList<Integer>();
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
	//	redAdapter.notifyDataSetChanged();
		
		//添加到数据中
		list_reds.add((ArrayList<Integer>) redNums);
		
	}
	
	//覆盖后退的方法,清除购物车的数据
	@Override
	public void onBack() {
		// TODO 自动生成的方法存根
		
		ViewMessageUtils.showCleanData(context);
		
	}
	
}
