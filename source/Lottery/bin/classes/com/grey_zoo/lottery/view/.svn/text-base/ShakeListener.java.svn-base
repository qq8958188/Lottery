package com.grey_zoo.lottery.view;

import android.R.integer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class ShakeListener implements SensorEventListener {

	//最后的X,Y,Z的值
	private float lastX=0;
	private float lastY=0;
	private float lastZ=0;
	
	//初始值
	private boolean firstTime=true;
	
	//总值
	private float total=0;
	
	//阀值
	private float gate=200;
	
	//时间戳
	private long timeStamp=0;
	
	//时间间隔
	private int time_delay=100;
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO 自动生成的方法存根
		if(timeStamp==0)
		{
			//给各个轴加上值
			lastX = event.values[0];
			lastY = event.values[1];
			lastZ = event.values[2];
			
			timeStamp=System.currentTimeMillis();
		}else {
			long delaytime=System.currentTimeMillis()-timeStamp;
			if(delaytime>time_delay)
			{

				//获取值
				float x=event.values[0];
				float y=event.values[1];
				float z=event.values[2];

				//计算差值
				float dx=Math.abs(x-lastX);
				float dy=Math.abs(y-lastY);
				float dz=Math.abs(z-lastZ);

				//屏蔽小的差别
				if(dx<1)
				{
					dx=0;
				}

				if(dy<1)
				{
					dy=0;
				}

				if(dz<1)
				{
					dz=0;
				}
				
				if((dz+dz+dy)==0)
				{
					init();
//					return;
				}

				//加上总值
				total+=(dx+dy+dz);

				if(total>gate)
				{
					//震动
					//调用函数随机选择一注
					ringAndSelectNum();
					//重新置值
					init();
				}else {
					lastX = event.values[SensorManager.DATA_X];
					lastY = event.values[SensorManager.DATA_Y];
					lastZ = event.values[SensorManager.DATA_Z];

					timeStamp = (int) System.currentTimeMillis();
				}
			}
		}
		
	}

	private void init() {
		// TODO 自动生成的方法存根
		lastX=0;
		lastY=0;
		lastZ=0;
		//重置首次，与累加值
		total=0;
		timeStamp=0;
	}

	//发生了摇一摇，需要震动与选号
	protected abstract void ringAndSelectNum();
	
}
