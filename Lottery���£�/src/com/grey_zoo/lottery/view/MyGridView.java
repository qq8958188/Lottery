package com.grey_zoo.lottery.view;

import com.grey_zoo.lottery.R;
import com.grey_zoo.lottery.util.DensityUtil;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyGridView extends GridView {

	//泡泡
	private PopupWindow popupWindow;
	//Ball
	private TextView ball;
	
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
		View view = View.inflate(context, R.layout.il_gridview_item_pop, null);
		ball = (TextView) view.findViewById(R.id.iv_tv_mygridview);
		//创建POPUPWindows
		popupWindow=new PopupWindow(context);
		popupWindow.setContentView(view);
		popupWindow.setBackgroundDrawable(null);
		//设置宽高
		popupWindow.setWidth(DensityUtil.dip2px(context, 50));
		popupWindow.setHeight(DensityUtil.dip2px(context, 50));
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		//在内部的X,Y
		int x=(int) ev.getX();
		int y=(int) ev.getY();
		//获取到位置
		int position = pointToPosition(x, y);
		
		//如果区域中没有任何东西，则隐藏
		if (position == INVALID_POSITION) {
			hidePop();
			return false;
		}
		
		//获取到TextView
		TextView child = (TextView) this.getChildAt(position);
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			// 当手指按下的时候，接管ScrollView滑动
			this.getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
			
			showPop(child);
			break;
			
		case MotionEvent.ACTION_MOVE:
			updatePop(child);
			break;
			
		case MotionEvent.ACTION_UP:
			// 当手指弹起的时候，放行
			this.getParent().getParent().getParent().requestDisallowInterceptTouchEvent(false);
			
			hidePop();
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(ev);
	}

	//展示
	private void showPop(TextView child) {
		// TODO 自动生成的方法存根
		int xOffSet=-(popupWindow.getWidth() - child.getWidth()) / 2;
		int yOffSet=-(popupWindow.getHeight() + child.getHeight());;
		ball.setText(child.getText());
		popupWindow.showAsDropDown(child, xOffSet, yOffSet);
	}
	
	//隐藏
	private void hidePop()
	{
		if(popupWindow.isShowing())
			popupWindow.dismiss();
		
	}
	
	private void updatePop(TextView child)
	{
		int xOffSet=-(popupWindow.getWidth() - child.getWidth()) / 2;
		int yOffSet=-(popupWindow.getHeight() + child.getHeight());;
		ball.setText(child.getText());
		popupWindow.update(child, xOffSet, yOffSet,-1,-1);
	}
	
}
