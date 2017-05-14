package com.mahang.weather.model;

import com.mahang.weather.model.entity.WeatherInfo;

import java.util.ArrayList;


public interface WeatherModel {

    void updateWeather(String cityName);

    void queryWeather(String cityName);

    void swap(int start,int end);

    void remove(int index);

    void save(WeatherInfo info);

    void updateApi(int index);

    WeatherInfo get(String cityName);

    WeatherInfo get(int idnex);

    ArrayList<WeatherInfo> getWeatherList();

}
