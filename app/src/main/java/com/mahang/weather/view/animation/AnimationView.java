package com.mahang.weather.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class AnimationView extends View {
	
	
	private boolean mRunning;

	private class AnimationThread extends Thread{
		@Override
		public void run() {
			onPrepareRunning();
			while (mRunning){
				onRunning();
				postInvalidate();
				try {
					sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			super.run();
		}
	}
	
	@Override
	protected void onDetachedFromWindow() {
		mRunning = false;
		super.onDetachedFromWindow();
	}

	public AnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimationView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onDraw(Canvas canvas) {
		if (!mRunning){
			mRunning = true;
			new AnimationThread().start();
		} else{
			drawSub(canvas);
		}
	}

	protected void drawSub(Canvas canvas){

	}

	protected void onRunning(){

	}

	protected void onPrepareRunning(){
        mRunning = false;
	}


}
