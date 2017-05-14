package com.mahang.weather.model.db;

import android.database.ContentObserver;
import android.os.Handler;

public class WeatherObserver extends ContentObserver {

	public WeatherObserver(Handler handler) {
		super(handler);
		
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
	}

}
