package com.mahang.weather.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.mahang.utils.LogUtils;


public class ResManagerImpl implements WeatherResource,ResManager {

	private static ResManagerImpl mInstance;

	private Context mContext;

	private Resources mResources;
	
	private ResManagerImpl(Context context) {
		mContext = context.getApplicationContext();
		mResources = mContext.getResources();
	}

	public static ResManagerImpl newInstance(Context context) {
		if (mInstance == null) {
			mInstance = new ResManagerImpl(context);
		}
		return mInstance;
	}
	
	public static ResManagerImpl getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new ResManagerImpl(context);
		}
		return mInstance;
	}

	public static ResManagerImpl getInstance() {
		return mInstance;
	}

	private boolean isDay() {
//		Calendar calendar = Calendar.getInstance();
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);
//		if (hour < 6 || hour >= 18) {
//			return false;
//		} else {
//			return true;
//		}
		return true;
	}
	
	
	public Drawable getWeatherIcon(int code){
		if (isDay()){
			return mResources.getDrawable(WEATHER_ICON_D[code], null); 
		} else{
			return mResources.getDrawable(WEATHER_ICON_N[code], null);
		}
	}
	
	public Drawable getWeatherBg(int code){
		LogUtils.d(code);
		if (isDay()){
			return mResources.getDrawable(WEATHER_BG_D[code], null); 
		} else{
			return mResources.getDrawable(WEATHER_BG_N[code], null);
		}
	}
	
	public int getToolbarBgColor(int code){
		if (isDay()){
			return mResources.getColor(STATUS_BAR_COLOR_D[code]);
		} else{
			return mResources.getColor(STATUS_BAR_COLOR_N[code]);
		}
	}
	public Drawable getWeatherIcon(int code,boolean isDay){
		if (isDay){
			return mResources.getDrawable(WEATHER_ICON_D[code], null); 
		} else{
			return mResources.getDrawable(WEATHER_ICON_N[code], null);
		}
	}
	
	public Drawable getWeatherBg(int code,boolean isDay){
		
		if (isDay){
			return mResources.getDrawable(WEATHER_BG_D[code], null); 
		} else{
			return mResources.getDrawable(WEATHER_BG_N[code], null);
		}
	}
	
	public int getToolbarBgColor(int code,boolean isDay){
		if (isDay){
			return mResources.getColor(STATUS_BAR_COLOR_D[code]);
		} else{
			return mResources.getColor(STATUS_BAR_COLOR_N[code]);
		}
	}
	
	
	public Drawable getDrawable(String id){
		String uri = "drawable/" + id;
		return mResources.getDrawable(getResourceId(uri),null);
		
	}
	
	private int getResourceId(String uri){
		return  mResources.getIdentifier(uri, null, mContext.getPackageName());
	}

}
