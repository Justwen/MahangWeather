package com.mahang.weather.view.card;

import java.util.List;

import com.mahang.weather.R;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.entity.HourlyInfo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

public class WeatherCardHourlyChartView extends View {
	
	private Paint mPaint;

	private Paint mPathPaint;

	private Paint mPointPaint;
	
	private List<HourlyInfo> mHourlyInfos;
	
	private int mPadding;
	
	private float mOffset;
	
	private int mItemWidth;
	
	private int mDisplayCount;
	
	private int mChartViewHeight;
	
	private int mMaxTemp;
	
	private int mMinTemp;

	public WeatherCardHourlyChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public WeatherCardHourlyChartView(Context context) {
		super(context);
		init();
	}
	
	private void init() {

		mPathPaint = new Paint();
		mPathPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_path_width));
		mPathPaint.setAntiAlias(true);
		mPathPaint.setShadowLayer(1.0f, 1.0f, 0, getResources().getColor(android.R.color.black));
		mPathPaint.setColor(getContext().getResources().getColor(android.R.color.holo_green_light));

		mPointPaint = new Paint();
		mPointPaint.setStyle(Paint.Style.FILL);
		mPointPaint.setColor(getResources().getColor(R.color.text_color));
		mPathPaint.setShadowLayer(1.0f, 1.0f, 0, getResources().getColor(android.R.color.black));
		mPointPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_point_size));


		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAntiAlias(true);// ȥ���
		mPaint.setColor(getResources().getColor(R.color.text_color));// ��ɫ
		mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_text_size));
		mPaint.setTextAlign(Align.CENTER);
		mPaint.setShadowLayer(1.0f, 1.0f, 0, getResources().getColor(android.R.color.black));
		
		mPadding = getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_padding);
		mItemWidth = getResources().getDimensionPixelSize(R.dimen.card_hourly_chart_view_item_width);
		mChartViewHeight = getResources().getDimensionPixelSize(R.dimen.card_hourly_chart_view_height) / 2;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		float y = mPadding;
		
		float startX = 0;
		float stopX;
		float startY = 0;
		float stopY;
		
		for (int i = 0; i < mDisplayCount; i++) {
			HourlyInfo info = mHourlyInfos.get(i);
			stopX = mItemWidth / 2 + mItemWidth * i;
		
			
			stopY = (mMaxTemp - Integer.parseInt(info.getTemp())) * mOffset + y;
			
			
			canvas.drawText(info.getTemp() + Constants.DEGREE_SIGN, stopX, stopY
					- mPaint.descent()*2, mPaint);
			if (i != 0) {
				canvas.drawLine(startX, startY, stopX, stopY, mPathPaint);
			}
			canvas.drawCircle(stopX, stopY, mPointPaint.getTextSize(), mPointPaint);
			if (startX != 0) {
				canvas.drawCircle(startX, startY, mPointPaint.getTextSize(), mPointPaint);
			}

			startX = stopX;
			startY = stopY;
		}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = mItemWidth*mDisplayCount;
		width = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		setMeasuredDimension(width, heightMeasureSpec);
		super.onMeasure(width, heightMeasureSpec);
	}
	
	public void setData(List<HourlyInfo> infos){
		mHourlyInfos = infos;
		mDisplayCount = infos.size();
		mMaxTemp = Integer.MIN_VALUE;
		mMinTemp = Integer.MAX_VALUE;
		for (HourlyInfo info:mHourlyInfos){
			int temp = Integer.parseInt(info.getTemp());
			if (mMaxTemp < temp){
				mMaxTemp = temp;
			}
			if (mMinTemp > temp){
				mMinTemp = temp;
			}
		}
		mOffset = mChartViewHeight / (mMaxTemp - mMinTemp);
		
		invalidate();
	}

}
