package com.grey_zoo.lottery.view.fragment;

import com.grey_zoo.lottery.MainActivity;
import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.util.ConfigSharePreferenceUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Welcome2Fragment extends Fragment implements OnClickListener{

	private Button btn;
	private ConfigSharePreferenceUtils sharePreferenceUtils;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.il_firstwelcomeui, container,false);
		
		btn=(Button) view.findViewById(R.id.btn);
		
		setListener();
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.btn:

			Intent intent=new Intent(getActivity(),MainActivity.class);
			startActivity(intent);
			
			sharePreferenceUtils=new ConfigSharePreferenceUtils(getActivity());
			
			if(!sharePreferenceUtils.getBool("xstart", false)){
				//设置已经加载过
				sharePreferenceUtils.putBool("xstart", true);
			}
			
			getActivity().finish();
			break;

		default:
			break;
		}
	}
	
	public void setListener()
	{
		btn.setOnClickListener(this);
	}
	
}
