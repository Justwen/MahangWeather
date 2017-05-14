package com.mahang.weather.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class AnimationSnowView extends AnimationRainView {

	private class SnowDropView implements DropView{

		@Override
		public void move() {

		}

		@Override
		public void draw(Canvas canvas) {

		}
	}

	public AnimationSnowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimationSnowView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DropView getDropView() {
		return new SnowDropView();
	}

	@Override
	protected int getCount() {
		return super.getCount();
	}
}
