package com.mahang.weather.model.api.test;

import android.content.Context;

import com.mahang.weather.model.api.WeatherApi;
import com.mahang.weather.model.api.hefeng.HefengWrapper;
import com.mahang.weather.model.entity.WeatherInfo;

/**
 * Created by hasee on 2017/4/5.
 */

public class WeatherApiImpl implements WeatherApi{

    private WeatherApi mWeatherApi;

    public interface CallBack{
    }


    public WeatherApiImpl(Context context) {
        mWeatherApi = getWeatherApi(context);
    }

    @Override
    public void queryWeather(String cityName) {
        mWeatherApi.queryWeather(cityName);
    }

    @Override
    public void queryWeather(String cityName, WeatherInfo info) {
        mWeatherApi.queryWeather(cityName,info);
    }

    private WeatherApi getWeatherApi(Context context){
        return new HefengWrapper(context,null);
    }
}
