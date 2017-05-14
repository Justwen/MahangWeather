package com.mahang.weather.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;

import com.mahang.weather.R;
import com.mahang.weather.common.EventHandler;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.api.WeatherApi;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.api.hefeng.HefengWrapper;
import com.mahang.weather.model.entity.WeatherInfo;

public class WeatherModelImpl implements WeatherModel {

	private static WeatherModel sInstance;

	private WeatherApi mApi;
	
	private List<WeatherApi> mApiList = new ArrayList<>();
	
	private Context mContext;
	
	private ArrayList<WeatherInfo> mWeatherList;

	private WeatherModelImpl(Context context) {
		mContext = context.getApplicationContext();
		initApiList(mContext);
		loadData();
	}
	
	private void initApiList(Context context){
		mApiList.add(0, new HefengWrapper(context,this));
		mApi = mApiList.get(0);
	}

	public synchronized static WeatherModel getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new WeatherModelImpl(context);
		}
		return sInstance;
	}

	public synchronized static WeatherModel getInstance() {
		return sInstance;
	}


	@Override
	public void queryWeather(String cityName) {
        for (WeatherInfo info:mWeatherList){
            if (info.getCityName().equals(cityName)){
                EventHandler.getInstance().postMessage(Constants.MSG_QUERY_WEATHER_FAILURE, R.string.msg_error_existed_city,0,cityName);
                return;
            }
        }
		mApi.queryWeather(cityName);
	}
	
	private void loadData() {
        if (mWeatherList != null){
            mWeatherList.clear();
        }
		DataBase liteOrm = LiteOrm.newCascadeInstance(mContext, Constants.DB_WEATHER_NAME);
		mWeatherList = liteOrm.cascade().query(WeatherInfo.class);
		liteOrm.close();
	}
	
	public ArrayList<WeatherInfo> getWeatherList() {
		return mWeatherList;
	}



	public void save(WeatherInfo info){
        DataBase liteOrm = LiteOrm.newCascadeInstance(mContext, Constants.DB_WEATHER_NAME);
        liteOrm.cascade().save(info);
        liteOrm.close();
        if (!mWeatherList.contains(info)){
            mWeatherList.add(info);
            EventHandler.getInstance().postMessage(Constants.MSG_QUERY_WEATHER_SUCCESS,0,0,info.getCityName());
        } else{
            EventHandler.getInstance().postMessage(Constants.MSG_UPDATE_WEATHER_SUCCESS,0,0,info.getCityName());
        }
	}

	@Override
	public void updateApi(int index) {
		mApi = mApiList.get(index);
	}


	@Override
	public WeatherInfo get(int index){
		return mWeatherList.get(index);
	}


    @Override
    public WeatherInfo get(String cityName) {
        for (WeatherInfo info: mWeatherList){
            if (cityName.equals(info.getCityName())){
                return info;
            }
        }
        return null;
    }


	@Override
	public void remove(int index) {
		DataBase liteOrm = LiteOrm.newCascadeInstance(mContext, Constants.DB_WEATHER_NAME);
		liteOrm.cascade().delete(mWeatherList.get(index));
		liteOrm.close();
		mWeatherList.remove(index);
	}


	@Override
	public void updateWeather(String cityName) {
		for (WeatherInfo info: mWeatherList){
			if (cityName.equals(info.getCityName())){
				mApi.queryWeather(cityName,info);
				return;
			}
		}
	}


	@Override
	public void swap(int start, int end) {
		Collections.swap(mWeatherList,start,end);
		DataBase liteOrm = LiteOrm.newCascadeInstance(mContext, Constants.DB_WEATHER_NAME);
		liteOrm.cascade().save(mWeatherList);
		liteOrm.close();
	}
}
