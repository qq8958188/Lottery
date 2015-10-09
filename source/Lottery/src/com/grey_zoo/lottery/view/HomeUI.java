package com.grey_zoo.lottery.view;

import java.util.ArrayList;

import org.json.JSONObject;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tencent.open.utils.Util;
import com.tencent.open.weiyun.RecordManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeUI extends BaseUI {

	private Context context;
	
	private ViewPager viewPager;

	private LinearLayout pointGroup;	

	private Button btn_hall;
	
	private Button btn_userdata;
	
	private Button btn_mylottery;
	
	// 图片资源ID
	private final int[] imageIds = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e };

	//图片标题集合
	private final String[] imageDescriptions = {
			"优美风景一",
			"优美风景二",
			"优美风景三",
			"优美风景四",
			"优美风景五"
	};

	private String imageUrls[]={"http://pic27.nipic.com/20130330/11899980_151839920000_2.jpg",
			"http://pic1a.nipic.com/2008-09-16/2008916102727989_2.jpg",
			"http://pic1a.nipic.com/2009-02-10/2009210232143716_2.jpg",
			"http://pic31.nipic.com/20130806/11947767_125554686132_2.jpg",
			"http://pic5.nipic.com/20100222/4208636_195625083505_2.jpg"};
	
	private TextView iamgeDesc;
	
	private ArrayList<ImageView> imageList;

	/**
	 * 上一个页面的位置
	 */
	protected int lastPosition;
	
	public HomeUI(Context context) {
		super(context);
		this.context=context;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根

		switch (v.getId()) {
		case R.id.hall:
			MiddleManager.getInstance().changeUI("hall", null);
			break;
			
		case R.id.mylottery:
			if(!GlobalValue.hasLogin){
				MiddleManager.getInstance().changeUI("login", null);
				return;
			}
			MiddleManager.getInstance().changeUI("mylottery", null);
			break;
			
		case R.id.userdata:
			if(!GlobalValue.hasLogin){
				MiddleManager.getInstance().changeUI("login", null);
				return;
			}
			MiddleManager.getInstance().changeUI("userdata", null);
			break;

		default:
			break;
		}
		
	}

	@Override
	public View createView() {
		view = View.inflate(context, R.layout.il_home, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	/**
	 * 获取各种控件
	 */
	private void init() {
		
		
		//init各个控件
		btn_hall=(Button) view.findViewById(R.id.hall);
		
		btn_mylottery=(Button) view.findViewById(R.id.mylottery);
		
		btn_userdata=(Button) view.findViewById(R.id.userdata);
		
		btn_hall.setOnClickListener(this);
		btn_mylottery.setOnClickListener(this);
		btn_userdata.setOnClickListener(this);
		
		viewPager=(ViewPager) view.findViewById(R.id.viewpager);
		pointGroup=(LinearLayout) view.findViewById(R.id.point_group);
		iamgeDesc = (TextView) view.findViewById(R.id.image_desc);
		iamgeDesc.setText(imageDescriptions[0]);
		
		imageList = new ArrayList<ImageView>();
		
		DisplayImageOptions options;

		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher) // resource or
		// drawable
		.showImageForEmptyUri(R.drawable.ic_launcher) // resource or
		// drawable
		.showImageOnFail(R.drawable.ic_launcher) // resource or
		// drawable
		.resetViewBeforeLoading(false) // default
		.delayBeforeLoading(1000).cacheInMemory(true) // default
		.cacheOnDisk(true) // default
		.considerExifParams(false) // default
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
		.displayer(new SimpleBitmapDisplayer()) // default
		.handler(new Handler()) // default
		.build();
		
		for (int i = 0; i <imageIds.length; i++) {
			
			
			//初始化图片资源
			ImageView image = new ImageView(context);
//			image.setBackgroundResource(imageIds[i]);
			
			GlobalValue.imageLoader.displayImage(imageUrls[i],
					image, options);
			
			imageList.add(image);
			
			//添加指示点
			ImageView point =new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			params.rightMargin = 20;
			point.setLayoutParams(params);
			
			point.setBackgroundResource(R.drawable.point_bg);
			if(i==0){
				point.setEnabled(true);
			}else{
				point.setEnabled(false);
			}
			pointGroup.addView(point);
		}
		
		 viewPager.setAdapter(new MyPagerAdapter());
		 
//		 viewPager.setCurrentItem(Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2%imageList.size())) ;
		 
		 viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			

			@Override
			/**
			 * 页面切换后调用 
			 * position  新的页面位置
			 */
			public void onPageSelected(int position) {
				
				position = position%imageList.size();
				
				//设置文字描述内容
				iamgeDesc.setText(imageDescriptions[position]);
				
				//改变指示点的状态
				//把当前点enbale 为true 
				pointGroup.getChildAt(position).setEnabled(true);
				//把上一个点设为false
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				lastPosition = position;
				
			}
			
			@Override
			/**
			 * 页面正在滑动的时候，回调
			 */
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}
			
			@Override
			/**
			 * 当页面状态发生变化的时候，回调
			 */
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	
		 /*
		  * 自动循环：
		  * 1、定时器：Timer
		  * 2、开子线程 while  true 循环
		  * 3、ColckManager 
		  * 4、 用handler 发送延时信息，实现循环
		  */
		 isRunning = true;
		 handler.sendEmptyMessageDelayed(0, 2000);
		
		 
		 
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.HOME;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根
		isRunning = false;
	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		isRunning = true;
	}

	
	/**
	 * 判断是否自动滚动
	 */
	private boolean isRunning = false;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			//让viewPager 滑动到下一页
			viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
			if(isRunning){
				handler.sendEmptyMessageDelayed(0, 2000);
			}
		};
	};
	
	protected void onDestroy() {
		
		isRunning = false;
	};

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		/**
		 * 获得页面的总数
		 */
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		/**
		 * 获得相应位置上的view
		 * container  view的容器，其实就是viewpager自身
		 * position 	相应的位置
		 */
		public Object instantiateItem(ViewGroup container, int position) {
			
			
			// 给 container 添加一个view
			container.addView(imageList.get(position%imageList.size()));
			//返回一个和该view相对的object
			return imageList.get(position%imageList.size());
		}

		@Override
		/**
		 * 判断 view和object的对应关系 
		 */
		public boolean isViewFromObject(View view, Object object) {
			if(view == object){
				return true;
			}else{
				return false;
			}
		}

		@Override
		/**
		 * 销毁对应位置上的object
		 */
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
			object = null;
		}
	}
	
}
