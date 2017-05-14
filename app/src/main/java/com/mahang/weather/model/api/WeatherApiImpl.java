package com.mahang.weather.model.api;

import android.content.Context;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mahang.weather.common.Constants;
import com.mahang.weather.common.EventHandler;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.R;

public  class WeatherApiImpl implements WeatherApi {

	protected Context mContext;
	
	private WeatherModel mWeatherModel;

	@Override
	public void queryWeather(String cityName) {

	}

	@Override
	public void queryWeather(String cityName, WeatherInfo weatherInfo) {

	}

	public enum Status{
		OK,ANR,UNKNOWN_CITY,NO_MORE_REQUESTS,NETWORK_UNAVAILABLE,OTHERS
	}

	public WeatherApiImpl(Context context, WeatherModel manager) {
		mContext = context;
		mWeatherModel = manager;
	}

	protected void convertToWeather(ConvertFactory factory){
		WeatherInfo info = factory.converToWeather();
		mWeatherModel.save(info);
	}


	protected void handleErrorInfo(Status status,String cityName) {
		Message msg = Message.obtain();
		msg.what = Constants.MSG_QUERY_WEATHER_FAILURE;
		msg.obj = cityName;
		msg.arg2 = Constants.MSG_FLAG_FAILURE;
		switch (status) {
		case UNKNOWN_CITY:
			msg.arg1 = R.string.msg_error_unknown_city;
			break;
		case ANR:
			msg.arg1 = R.string.msg_error_anr;
		    break;
		case NO_MORE_REQUESTS:
			msg.arg1 = R.string.msg_error_no_more_requests;
			break;
		default:
			msg.arg1 = R.string.msg_error_default;
			break;
		}
		EventHandler.getInstance().postMessage(msg);
	}
	
	

	protected void handleVolleyError(VolleyError error,String cityName){
        Message msg = Message.obtain();
        msg.what = Constants.MSG_QUERY_WEATHER_FAILURE;
        msg.obj = cityName;
		msg.arg2 = Constants.MSG_FLAG_FAILURE;
        if (error instanceof TimeoutError){
            msg.arg1 = R.string.msg_error_timed_out;
        }else if (error instanceof ServerError || error instanceof AuthFailureError){
            msg.arg1 = R.string.msg_error_server_unavailable;
        }else if (error instanceof NetworkError || error instanceof NoConnectionError){
            msg.arg1 = R.string.msg_error_network_unavailable;
        } else{
            msg.arg1 = R.string.msg_error_default;
        }
		EventHandler.getInstance().postMessage(msg);
	}
}
