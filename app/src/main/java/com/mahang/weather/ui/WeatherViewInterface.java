package com.mahang.weather.ui;


import android.content.Context;

import com.mahang.weather.model.entity.WeatherInfo;

import java.util.List;

public interface WeatherViewInterface {

    void hideLoading();

    void showToast(String string);

    void updateWeather();

    void showWeather(List<WeatherInfo> infos);

    Context getContext();

}
