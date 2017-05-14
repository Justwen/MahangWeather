package com.mahang.weather.model.api.location;

import android.content.Context;

import com.mahang.weather.model.impl.LocationModelImpl;
import com.mahang.weather.model.impl.WeatherModelImpl;


public abstract class LocationApiImpl implements LocationApi {

    protected Context mContext;

    public LocationApiImpl(Context context){
        mContext = context.getApplicationContext();
    }

    protected void onReceiveLocation(String cityName){
        LocationModelImpl.getInstance(mContext).saveLocation(cityName);
        WeatherModelImpl.getInstance(mContext).queryWeather(cityName);
    }

}
