package com.grey_zoo.lottery.view;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.engin.UserEngin;
import com.grey_zoo.lottery.global.GlobalValue;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChongZhiUI extends BaseUI {

	private Button btn;
	private EditText et;
	
	private int money;
	
	public ChongZhiUI(Context context) {
		super(context);
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
		switch (v.getId()) {
		case R.id.btn:
			
			money = Integer.valueOf(et.getText().toString().trim());
			
			if(money<=0)
			{
				Toast.makeText(context, "金额不能小于0", Toast.LENGTH_SHORT).show();
			}
			
			//充值
			new UserEngin().chongzhi(money);
			
			
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public View createView() {
		view = View.inflate(context, R.layout.il_chongzhi, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		init();
		
		return view;
	}

	private void init() {
		// TODO 自动生成的方法存根
		btn=(Button) view.findViewById(R.id.btn);
		et=(EditText) view.findViewById(R.id.et);
		
		btn.setOnClickListener(this);
	}

	@Override
	public Integer getId() {
		// TODO 自动生成的方法存根
		return GlobalValue.CHONGZHI;
	}

	@Override
	public void OnPause() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void OnResume() {
		// TODO 自动生成的方法存根

	}

}
