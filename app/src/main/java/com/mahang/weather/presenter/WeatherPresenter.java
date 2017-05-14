package com.mahang.weather.presenter;


import android.os.Message;

import com.mahang.weather.ui.BasePresenter;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.ui.WeatherViewInterface;
import com.squareup.otto.Subscribe;

import java.util.List;

public class WeatherPresenter extends BasePresenter {

    private WeatherModel mWeatherModel;

    private WeatherViewInterface mWeatherView;


    public WeatherPresenter(WeatherViewInterface weatherView) {
        mWeatherModel = WeatherModelImpl.getInstance(weatherView.getContext());
        mWeatherView = weatherView;

    }

    public void queryWeather(String cityName){
        mWeatherModel.queryWeather(cityName);
    }

    public void loadWeather(){
        List<WeatherInfo> infos = mWeatherModel.getWeatherList();
        mWeatherView.showWeather(infos);
    }

    @Subscribe
    @Override
    public void handleMessage(Message msg) {

        switch (msg.what){
            case Constants.MSG_QUERY_WEATHER_SUCCESS:
                mWeatherModel.save((WeatherInfo) msg.obj);
                mWeatherView.hideLoading();
                mWeatherView.updateWeather();
                break;
            case Constants.MSG_QUERY_WEATHER_FAILURE:
                mWeatherView.hideLoading();
                mWeatherView.showToast(null);
                break;
        }

    }
}
