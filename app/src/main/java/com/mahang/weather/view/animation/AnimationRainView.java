package com.mahang.weather.view.animation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mahang.weather.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class AnimationRainView extends AnimationView {
	
	private Paint mPaint;
	
	private Random mRandom;
	
	private int mMaxDropCount;
	
	private int mSpeedX;
	
	private int mSpeedY;
	
	private int mWidth;
	
	private int mHeight;

    private List<DropView> mDropViews = new ArrayList<>();

    public class RainDropView implements DropView{

        private int mCanvasWidth;

        private int mCanvasHeight;

        private Paint mPaint;

        private Random mRandom;

        private float mSpeedX;

        private float mSpeedY;

        private float mStartX;

        private float mStartY;

        private float mStopX;

        private float mStopY;

        private float mSizeX;

        private float mSizeY;


        public RainDropView(int width,int height) {
            mCanvasWidth = width;
            mCanvasHeight = height;
            init();
        }


        public RainDropView(int width,int height,Paint paint) {
            mCanvasWidth = width;
            mCanvasHeight = height;
            mPaint = paint;
            init();

        }

        public void setParams(float sizeX,float sizeY,float speedX,float speedY){
            mSpeedX = speedX;
            mSpeedY = speedY - mRandom.nextInt(10);
            mSizeX = sizeX;
            mSizeY = sizeY - mRandom.nextInt(10);
        }


        private void init(){

            if (mRandom == null){
                mRandom = new Random();
            }
            if (mPaint == null){
                mPaint = new Paint();
            }
            mStartX = mRandom.nextInt(mCanvasWidth);
            mStartY = 0;
            mStopX = mStartX + mSizeX;
            mStopY = mStartY + mSizeY;
        }

        @Override
        public void draw(Canvas canvas){
            canvas.drawLine(mStartX, mStartY, mStopX, mStopY, mPaint);
        }

        @Override
        public void move(){
            mStartX += mSpeedX;
            mStartY += mSpeedY;
            mStopX = mStartX + mSizeX;
            mStopY = mStartY + mSizeY;
            if (mStartY > mCanvasHeight || mStartX > mCanvasWidth){
                init();
            }

        }
    }


	public AnimationRainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initParams();
	}

	public AnimationRainView(Context context) {
		this(context,null);
	}
	
	private void initParams(){
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setAlpha(125);
		mRandom = new Random();
		mMaxDropCount = getResources().getInteger(R.integer.rain_drop_max_count);
		mSpeedX = getResources().getDimensionPixelSize(R.dimen.animation_rain_drop_speed_x_max);
		mSpeedY = getResources().getDimensionPixelSize(R.dimen.animation_rain_drop_speed_y_max);
		mWidth = getResources().getDimensionPixelSize(R.dimen.animation_rain_drop_width_max);
		mHeight = getResources().getDimensionPixelSize(R.dimen.animation_rain_drop_height_max);
	}
	

	
	protected DropView getDropView() {
        RainDropView dropView = new RainDropView(getMeasuredWidth(), getMeasuredHeight(), mPaint);
		dropView.setParams(mWidth, mHeight, mSpeedX, mSpeedY);
		return dropView;
	}
	
	protected int getCount() {
		return mMaxDropCount - mRandom.nextInt(10);
	}


	@Override
    protected void drawSub(Canvas canvas) {
        for (DropView drop : mDropViews){
            drop.draw(canvas);
        }
    }


    @Override
    protected void onRunning() {
        for (DropView drop : mDropViews){
            drop.move();
        }
    }

    @Override
    protected void onPrepareRunning() {
        mDropViews.clear();
        for (int i=0; i< getCount();i++){
            mDropViews.add(getDropView());
        }
    }

}
