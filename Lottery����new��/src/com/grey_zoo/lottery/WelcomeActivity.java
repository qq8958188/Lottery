package com.grey_zoo.lottery;

import java.util.ArrayList;
import java.util.List;

import com.grey_zoo.lottery.global.GlobalValue;
import com.grey_zoo.lottery.util.ConfigSharePreferenceUtils;
import com.grey_zoo.lottery.view.fragment.Welcome1Fragment;
import com.grey_zoo.lottery.view.fragment.Welcome2Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

public class WelcomeActivity extends FragmentActivity{

	private ViewPager viewPager;

	private Welcome1Fragment welcome1Fragment=new Welcome1Fragment();
	private Welcome2Fragment welcome2Fragment=new Welcome2Fragment();
	
	private List<Fragment> list_fragment=new ArrayList<Fragment>();

	private ConfigSharePreferenceUtils sharePreferenceUtils;
	
	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.il_welcome1);
		hasload();
		init();
		super.onCreate(arg0);
	}

	private void hasload() {
		// TODO 自动生成的方法存根
		sharePreferenceUtils=new ConfigSharePreferenceUtils(this);
		if(!sharePreferenceUtils.getBool("xstart", false)){
			
		}else {
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	private void init() {
		// TODO 自动生成的方法存根
		viewPager=(ViewPager) findViewById(R.id.vp_welcome);
		
		list_fragment.add(welcome1Fragment);
		list_fragment.add(welcome2Fragment);
		
		viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		
	}
	
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{  
		  
	    public MyFragmentPagerAdapter(FragmentManager fm) {  
	        super(fm);  
	    }  
	  
	    @Override  
	    public Fragment getItem(int position) {  
	        return list_fragment.get(position);  
	    }  
	  
	    @Override  
	    public int getCount() {  
	        return list_fragment.size(); // 代表页数  
	    }  
	      
	    @Override  
	    public void destroyItem(ViewGroup container, int position, Object object) {  
	        // 这里Destroy的是Fragment的视图层次，并不是Destroy Fragment对象  
	        super.destroyItem(container, position, object);  
	    }  
	}  
	
}
