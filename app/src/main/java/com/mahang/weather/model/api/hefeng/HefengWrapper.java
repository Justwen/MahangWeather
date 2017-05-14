package com.mahang.weather.model.api.hefeng;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.api.WeatherApiImpl;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.utils.LogUtils;

public class HefengWrapper extends WeatherApiImpl {
	
	private static final String STATUS_OK = "ok";
	
	private static final String STATUS_INVALIDE_KEY = "invalid key";
	
	private static final String STATUS_UNKNOWN_CITY = "unknown city";
	
	private static final String STATUS_ANR = "anr";
	
	private static final String STATUS_PERMISSION_DENIED = "permission denied";
	
	private static final String STATUS_NO_MORE_REQUESTS = "no more requests";
	
	private static final String API = "https://api.heweather.com/x3/weather?key=11a5a372536c4ea9b51a87bb6ffdc23a&city=";
		
	private static final String API_BAIDU = "http://apis.baidu.com/heweather/pro/weather?city=";

	private static final String API_BAIDU_KEY = "d6981c4708cd3b3cbb6a98b56ec1db79";
	
	private RequestQueue mRequestQueue;
	
	public HefengWrapper(Context context, WeatherModel manager) {
		super(context, manager);
		mRequestQueue = Volley.newRequestQueue(mContext);
	}


	private void parseJsonString(String result,String cityName,WeatherInfo info){
		LogUtils.d(result);
		try{
			JSONObject object = new JSONObject(result);
			JSONArray array = object.getJSONArray("HeWeather data service 3.0");
			object = (JSONObject) array.get(0);
			switch (object.getString("status")) {
				case STATUS_OK:
					convertToWeather(new ConvertFactoryImpl(object.toString(),info));
					break;
				case STATUS_ANR:
					handleErrorInfo(Status.ANR,cityName);
					break;
				case STATUS_UNKNOWN_CITY:
					handleErrorInfo(Status.UNKNOWN_CITY,cityName);
					break;
				case STATUS_NO_MORE_REQUESTS:
					handleErrorInfo(Status.NO_MORE_REQUESTS,cityName);
					break;
				default:
					handleErrorInfo(Status.OTHERS,cityName);
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void queryWeather(String cityName) {
		queryWeather(cityName,null);
	}

	@Override
	public void queryWeather(final String cityName, final WeatherInfo info) {
		StringBuilder builder = new StringBuilder();
		String url = builder.append(API_BAIDU).append(cityName).toString();

		mRequestQueue.add(new StringRequest(Method.GET, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String result) {
				parseJsonString(result,cityName,info);
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				handleVolleyError(error, cityName);
			}
		}){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> map = new HashMap<String, String>();
				map.put("apikey",API_BAIDU_KEY);
				return map;
			}
		});
		mRequestQueue.start();
	}
}
