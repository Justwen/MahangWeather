package com.mahang.weather.common;

import android.content.Context;

public class Environment {

	private static Environment mInstance;

	private Context mContext;

	private Environment(Context context) {
		mContext = context.getApplicationContext();
	}

	public synchronized static Environment getSingleInstance(Context context) {
		if (mInstance == null) {
			mInstance = new Environment(context);
		}
		return mInstance;
	}
	
	public boolean isLight(){
		
		return true;
		
	}

}
