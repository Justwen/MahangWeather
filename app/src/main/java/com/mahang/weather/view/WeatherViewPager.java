package com.mahang.weather.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class WeatherViewPager extends ViewPager {


	private boolean mEnableScroll = true;

	private static final int DELAY_TIME = 300;

	public WeatherViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);

	}

	public WeatherViewPager(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setPageTransformer(true, new TransformerDepthPage());
	}

	public void setEnableScroll(boolean enable) {
		mEnableScroll = enable;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (mEnableScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (mEnableScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}

	@Override
	public void setCurrentItem(int item) {
		setCurrentItem(item, false);
	}

	@Override
	public void setCurrentItem(final int item, final boolean smoothScroll) {
		postDelayed(new Runnable() {
			@Override
			public void run() {
				WeatherViewPager.super.setCurrentItem(item,smoothScroll);
			}
		},DELAY_TIME);
	}

}
