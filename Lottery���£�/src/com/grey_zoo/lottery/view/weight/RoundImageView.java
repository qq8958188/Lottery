package com.grey_zoo.lottery.view.weight;


import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView{

	private Paint paint = new Paint();

	public RoundImageView(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO 自动生成的构造函数存根
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	/*
	public Bitmap toRoundBitmap(Bitmap bitmap){

		//圆形图片宽高
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();

		//长方形的边长
		int r=0;

		//取最短边做边长
		if(width>height){
			r=height;
		}else {
			r=width;
		}

		//构建一个Bitmap
		Bitmap backgroundBmp=Bitmap.createBitmap(width,
				height,Config.ARGB_8888);

		//new一个Canvas,在backgroundBmp上画图
		Canvas canvas=new Canvas(backgroundBmp);
		Paint paint=new Paint();
		//设置边缘光滑,去掉锯齿
		paint.setAntiAlias(true);
		//宽高相等，即正方形
		RectF rect=new RectF(0, 0, r, r);
		//通过制定的rect画一个圆角矩阵，当圆角的X轴方向的半径等于Y轴方向的半径时，
		//且都等于r/2时，画出来的圆角矩阵即为圆形
		canvas.drawRoundRect(rect, r/2, r/2, paint);
		//设置当两个圆形相交时的模式，SRC_IN为取SRC圆形相交的部分，多余的将被去掉
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		//canvas将bitmap画在backgroundBmp上
		canvas.drawBitmap(bitmap, null, rect, paint);
		return backgroundBmp;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		//toRoundBitmap(getDrawable());
		BitmapDrawable bd = (BitmapDrawable) getDrawable();
		Bitmap bm = bd.getBitmap();
		bm=toRoundBitmap(bm);
		setImageBitmap(bm);

		super.onDraw(canvas);
	}
	 */

	@Override  
	protected void onDraw(Canvas canvas) {  

		Drawable drawable = getDrawable();  
		if (null != drawable) {  
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();  
			bitmap=com.grey_zoo.lottery.util.BitmapUtils.toRectangle(bitmap);
			Bitmap b = toRoundCorner(bitmap, 14);  
			//判断长和宽
			int r=b.getHeight()>b.getWidth()?b.getWidth():b.getHeight();
			final Rect rect1 = new Rect(0, 0, r, r); 
			final Rect rect2 = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();  
			canvas.drawBitmap(b, rect1, rect2, paint);

		} else {  
			super.onDraw(canvas);  
		}  
	}  

	private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {  
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
				bitmap.getHeight(), Config.ARGB_8888);  
		Canvas canvas = new Canvas(output);  

		final int color = 0xff424242; 

		//判断长和宽
		int r=bitmap.getHeight()>bitmap.getWidth()?bitmap.getWidth():bitmap.getHeight();

		//矩阵
		// final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final Rect rect = new Rect(0, 0, r, r);
		paint.setAntiAlias(true);  
		canvas.drawARGB(0, 0, 0, 0);  
		paint.setColor(color);  
		//int x = bitmap.getWidth();  
		//canvas.drawCircle(x / 2, x / 2, x / 2, paint);  
		canvas.drawCircle(r / 2, r / 2, r / 2, paint);  
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  

		/*
		 * 把bitmap的一部分
		 * 就是src所包括的部分
		 * 绘制到dst指定的矩形处
		 * 关键就是dst
		 * 它确定了bitmap要画的大小跟位置
		 */

		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;  
	}  

}
