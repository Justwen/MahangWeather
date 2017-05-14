package com.mahang.weather.view.card;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

import com.mahang.weather.R;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.entity.DailyInfo;

public class WeatherCardDailyChartView extends View {
	
	private List<DailyInfo> mDailyInfoList;

	private int mMaxHigh;
	
	private int mMinHigh;
	
	private int mMaxLow;
	
	private int mMinLow;

	private int mWidth;

	private int mChartViewHeight;

	private float mOffSetHighY; 
	
	private float mOffSetLowY;

	private Paint mPaint;

	private Paint mPathPaint;

	private Paint mPointPaint;

	private int mDisplayCount;
	
	private int mPadding;

	public WeatherCardDailyChartView(Context context) {
		super(context);
		init();
	}

	public WeatherCardDailyChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}
	
	private void init() {

		mPathPaint = new Paint();
		mPathPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_path_width));
		mPathPaint.setAntiAlias(true);
		mPathPaint.setShadowLayer(1.0f, 1.0f, 0, getResources().getColor(android.R.color.black));

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
		mChartViewHeight = getResources().getDimensionPixelSize(R.dimen.card_daily_chart_view_height) - 3*mPadding;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mWidth = getWidth() / mDisplayCount;
	}
	

	public void addData(List<DailyInfo> data) {

		mDailyInfoList = data;
		mDisplayCount = mDailyInfoList.size() - 1;
		mMaxHigh = Integer.MIN_VALUE;
		mMinHigh = Integer.MAX_VALUE;
		mMaxLow = Integer.MIN_VALUE;
		mMinLow = Integer.MAX_VALUE;
		
		for (int i = 0; i < mDisplayCount; i++) {
			DailyInfo info = mDailyInfoList.get(i);
			
			int high = Integer.parseInt(info.getMax());
			int low = Integer.parseInt(info.getMin());
			
			if (mMaxHigh < high){
				mMaxHigh  = high;
			}
			if (mMinHigh > high){
				mMinHigh = high;
			}
			
			if (mMaxLow < low){
				mMaxLow = low;
			}
			if (mMinLow > low){
				mMinLow = low;
			}
		}
		if (mMaxHigh == mMinHigh){
			mOffSetHighY = 0;
		}else{
			mOffSetHighY= (mChartViewHeight - mPadding) / 2 / (mMaxHigh - mMinHigh);
		}
		if (mMaxLow == mMinLow){
			mOffSetLowY = 0;
		}else{
			mOffSetLowY = (mChartViewHeight - mPadding) / 2 / (mMaxLow - mMinLow);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		float y = mPadding;
		drawHighText(canvas, y);
		y = y + mChartViewHeight / 2 + mPadding;
		drawLowText(canvas, y);
		super.onDraw(canvas);
	}
	



	private void drawHighText(Canvas canvas, float y) {
		float startX = 0;
		float stopX;
		float startY = 0;
		float stopY;
		mPathPaint.setColor(getContext().getResources().getColor(android.R.color.holo_red_light));
		for (int i = 0; i < mDisplayCount; i++) {
			DailyInfo info = mDailyInfoList.get(i);
			stopX = mWidth / 2 + mWidth * i;
			if (mOffSetHighY == 0){
				stopY = y + mChartViewHeight /4;
			} else{
				stopY = (mMaxHigh - Integer.parseInt(info.getMax())) * mOffSetHighY + y;
			}
			
			canvas.drawText(info.getMax() + Constants.DEGREE_SIGN, stopX, stopY
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
	}

	private void drawLowText(Canvas canvas, float y) {
		float startX = 0;
		float stopX;
		float startY = 0;
		float stopY;
		
		mPathPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
		for (int i = 0; i < mDisplayCount; i++) {
			DailyInfo info = mDailyInfoList.get(i);
			stopX = mWidth / 2 + mWidth * i;
			if ( mOffSetLowY == 0){
				stopY = y + mChartViewHeight / 4;
			} else{
				stopY = (mMaxLow - Integer.parseInt(info.getMin())) * mOffSetLowY + y;	
			}
			canvas.drawText(info.getMin() + Constants.DEGREE_SIGN, stopX, stopY
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
	}

}
