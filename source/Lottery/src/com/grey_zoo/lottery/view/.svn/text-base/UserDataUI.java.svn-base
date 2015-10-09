package com.grey_zoo.lottery.view;


import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.PromptManager;
import com.grey_zoo.lottery.view.manager.MiddleManager;
import com.grey_zoo.lottery.view.weight.DetailUserView;
import com.grey_zoo.lottery.view.weight.RefreshableView;
import com.grey_zoo.lottery.view.weight.RefreshableView.PullToRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataUI extends BaseUI{

	private Context context;
	
	private DetailUserView detailUserView;
	
	private ListView lv;
	
	private String text[]={"充值金额","退出登录","退出软件"};
	
	private DisplayImageOptions options;

	private RefreshableView refreshableView;
	
	
	
	public UserDataUI(Context context) {
		super(context);
		this.context=context;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		 
	}

	@Override
	public View createView() {
		
		view = View.inflate(context, R.layout.il_userdata, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	private void getBitmap() {
		// TODO 自动生成的方法存根

		if(GlobalValue.info.getImgUrl()!=null){
			GlobalValue.imageLoader.displayImage(GlobalValue.info.getImgUrl(),
					detailUserView.getRiv_headphoto(), options,
					new SimpleImageLoadingListener(){
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					// TODO 自动生成的方法存根
					super.onLoadingStarted(imageUri, view);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view,
						Bitmap loadedImage) {
					// TODO 自动生成的方法存根
					super.onLoadingComplete(imageUri, view, loadedImage);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view,
						FailReason failReason) {
					// TODO 自动生成的方法存根
					super.onLoadingFailed(imageUri, view, failReason);
				}

			});
		}

	}

	private void init() {
		// TODO 自动生成的方法存根
		detailUserView=(DetailUserView) view.findViewById(R.id.duv_userdata);
		lv=(ListView) view.findViewById(R.id.lv);
		
		lv.setDividerHeight(30);
		
		lv.setBackgroundResource(R.color.allbackground);
		
		refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);
		
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				//Toast.makeText(GlobalValue.context, "下拉刷新", Toast.LENGTH_LONG).show();
				//System.out.println("下拉刷新");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				refreshableView.finishRefreshing();
			}
		}, 0);
		
		lv.setAdapter(new MyBaseAdapter());
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				switch (position) {
				case 0:
					MiddleManager.getInstance().changeUI("chongzhi", null);
					break;
					
				case 1:
					UserEngin.loginout();
					MiddleManager.getInstance().changeUI("home", null);
					break;
					
				case 2:
					PromptManager.showExitSystem(context);
					break;

				default:
					break;
				}
			}
		});
		
		//配置下载
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
		 .bitmapConfig(Bitmap.Config.ARGB_8888) // default
		 .displayer(new SimpleBitmapDisplayer()) // default
		 .handler(new Handler()) // default
		 .build();
		
		
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.USERDATA;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根
		getBitmap();

		detailUserView.getTv_name().setText(GlobalValue.username);

		detailUserView.getTv_money().setText("剩余金额为："+GlobalValue.userMoney+"元");
	}

	class MyBaseAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return text.length;
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
			
			LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, 150);
			
			tv.setLayoutParams(params);
			
			tv.setText(text[position]);
			
			tv.setGravity(Gravity.CENTER);
			
			tv.setTextSize(20);
			
			
			tv.setBackgroundResource(R.drawable.button01);
			
			return tv;
		}
		
	}
	
}
