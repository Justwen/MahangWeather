//package com.mahang.weather.model.api.juhe;
//
//import java.lang.reflect.Type;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.content.Context;
//
//import com.mahang.weather.model.WeatherModelImpl;
//import com.mahang.weather.model.entity.WeatherInfo;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.mahang.weather.R;
//import com.mahang.weather.engine.BaseWeatherEngine;
//import com.mahang.utils.LogUtils;
//import com.thinkland.sdk.android.DataCallBack;
//import com.thinkland.sdk.android.JuheData;
//import com.thinkland.sdk.android.Parameters;
//
//public class JuheEngine extends BaseWeatherEngine {
//
//
//	public JuheEngine(Context context, WeatherModelImpl manager) {
//		super(context, manager);
//		// TODO Auto-generated constructor stub
//	}
//
//	private void queryAirInfo(String cityName, final ConvertFactory factory) {
//		Parameters params = new Parameters();
//		params.add("city", cityName);
//		JuheData.executeWithAPI(mContext, 33, "http://web.juhe.cn:8080/environment/air/pm", JuheData.GET, params, new DataCallBack() {
//
//			@Override
//			public void onFailure(int statusCode, String result, Throwable throwable) {
//				LogUtils.e("statusCode = " + statusCode);
//				LogUtils.e("result = " + result);
////				if (throwable != null) {
////					LogUtils.e(throwable.getMessage());
////				}
////				msg = convertErrorMessage(statusCode);
//			}
//
//			@Override
//			public void onFinish() {
//				convertToWeather(factory);
//			}
//
//			@Override
//			public void onSuccess(int statusCode, String result) {
//
//				LogUtils.d(result);
//				try{
//					Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
//					Type type = new TypeToken<AirBean>() {
//					}.getType();
//					AirBean bean = gson.fromJson(result, type);
//					if (factory != null){
//						factory.setAirBean(bean);
//					    factory.getPM25(result);
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//
//			}
//		});
//	}
//
//	private void querForcastWeather(){
//		Parameters params = new Parameters();
//		params.add("cityname", "���");
//		JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/forecast3h", JuheData.GET, params, new DataCallBack() {
//
//			@Override
//			public void onFailure(int arg0, String arg1, Throwable arg2) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onFinish() {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onSuccess(int statusCode, String result) {
//				LogUtils.d(result);
//
//			}
//
//
//		});
//
//	}
//
//	private void queryWeather(final String cityName, final ConvertFactory factory) {
//		querForcastWeather();
//		Parameters params = new Parameters();
//		params.add("cityname", cityName);
//		params.add("dtype", "json");
//		params.add("format", "2");
//		LogUtils.d(cityName);
//		JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/index", JuheData.GET, params, new DataCallBack() {
//
//			@Override
//			public void onFailure(int statusCode, String result, Throwable throwable) {
//				LogUtils.e("statusCode = " + statusCode);
//				LogUtils.e("result = " + result);
////				if (throwable != null)
////					LogUtils.e(throwable.getMessage());
////				msg = convertErrorMessage(statusCode);
//
//			}
//
//			@Override
//			public void onFinish() {
//				if (factory.isPrepared()) {
//					queryAirInfo(cityName, factory);
//				}
//			}
//
//			@Override
//			public void onSuccess(int statusCode, String result) {
//				LogUtils.d(result);
//				LogUtils.d(statusCode);
//
//				int errorCode = getErrorCode(result);
//				if (errorCode == 0) {
//					Gson gson = new Gson();
//					Type type = new TypeToken<WeatherBean>() {
//					}.getType();
//					WeatherBean bean = gson.fromJson(result, type);
//					factory.setWeatherBean(bean);
//				} else {
//					HandleErrorCode(factory, errorCode);
//				}
//
//			}
//
//		});
//	}
//
//	private void HandleErrorCode(ConvertFactory factory, int code) {
//		String errorMsg = null;
//		switch (code) {
//		case 10005:
//			errorMsg = mContext.getString(R.string.msg_error_network_unavailable);
//			break;
//		default:
//			break;
//		}
//	//	postErrorEvent(factory, errorMsg);
//	}
//
//	private int getErrorCode(String result) {
//		JSONObject object;
//		try {
//			object = new JSONObject(result);
//			return Integer.parseInt(object.getString("error_code"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return -1;
//
//	}
//
//	@Override
//	public void refresh(WeatherInfo weatherInfo) {
//		ConvertFactory factory = new ConvertFactory(weatherInfo);
//		queryWeather(weatherInfo.getCityName(), factory);
//
//	}
//
//	@Override
//	public void query(String cityName) {
//		ConvertFactory factory = new ConvertFactory(cityName);
//		queryWeather(cityName, factory);
//	}
//
//}
