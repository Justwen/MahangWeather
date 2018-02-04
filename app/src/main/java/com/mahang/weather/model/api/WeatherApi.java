package com.mahang.weather.model.api;

import com.mahang.weather.model.OnHttpCallBack;
import com.mahang.weather.model.entity.WeatherInfo;

public interface WeatherApi {
	
	void queryWeather(String cityName, OnHttpCallBack<WeatherInfo> callBack);

}
