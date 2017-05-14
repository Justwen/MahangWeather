package com.mahang.weather.weatherlist.mvp;


import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.entity.WeatherInfo;

import java.util.List;


public class WeatherListPresenter implements WeatherListContract.Presenter {

    private WeatherListContract.View mView;

    private WeatherModel mWeatherModel;

     public WeatherListPresenter(WeatherModel model,WeatherListContract.View view) {
        mView = view;
        mWeatherModel = model;
        mView.setPresenter(this);
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void loadWeatherInfo() {
        List<WeatherInfo> infos = mWeatherModel.getWeatherList();
        mView.setData(infos);
    }

    @Override
    public void swap(int start, int end) {
        mWeatherModel.swap(start,end);
        mView.setActivityResult("update",true);
    }

    @Override
    public void remove(int index) {
        mWeatherModel.remove(index);
        mView.setActivityResult("update",true);
    }

    @Override
    public void onClick(int index) {
        mView.setActivityResult("index",index);
        mView.finish();
    }
}
