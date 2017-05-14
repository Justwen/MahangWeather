//package com.mahang.weather.model.api.juhe;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.mahang.weather.model.entity.WeatherInfo;
//
//class ConvertFactory extends com.mahang.weather.model.api.ConvertFactory {
//
//	private AirBean mAirBean;
//
//	private WeatherBean mWeatherBean;
//
//	private String pm25;
//
//	public ConvertFactory(WeatherInfo weatherInfo) {
//		super(weatherInfo);
//	}
//
//	public ConvertFactory(String cityName) {
//		super(cityName);
//		// TODO Auto-generated constructor stub
//	}
//
//	public void setAirBean(AirBean airBean) {
//		mAirBean = airBean;
//	}
//
//	public void setWeatherBean(WeatherBean weatherBean) {
//		mWeatherBean = weatherBean;
//	}
//
//	private void addAirInfo(WeatherInfo weather) {
////		AirInfo info = weather.getAirInfo();
////		if (info == null) {
////			info = new AirInfo();
////			info.setCityName(mCityName);
////		}
////
////		if (mAirBean != null && mAirBean.result != null && mAirBean.result.size() > 0) {
////			info.airIndex = mAirBean.result.get(0).AQI;
////			info.quality = mAirBean.result.get(0).quality;
////			info.setUpdateTime(mAirBean.result.get(0).time);
////			info.pm10 = mAirBean.result.get(0).PM10;
////			info.pm25 = pm25 ;
////			LogUtils.d(info.pm10);
////			LogUtils.d(info.pm25);
////			LogUtils.d(info.getUpdateTime() );
////		}
////		weather.setAirInfo(info);
//	}
//
//	private void addRealTimeWeatehrInfo(WeatherInfo weather) {
////		RealTimeInfo info = weather.getRealTimeInfo();
////		if (info == null) {
////			info = new RealTimeInfo();
////		}
////		info.cityName = mCityName;
////		info.temp = mWeatherBean.result.sk.temp;
////		info.humidity = mWeatherBean.result.sk.humidity;
////		info.wind = mWeatherBean.result.sk.wind_direction
////				+ mWeatherBean.result.sk.wind_strength;
////		info.fa = Integer.parseInt(mWeatherBean.result.future.get(0).weather_id.fa);
////		info.weatherName = mWeatherBean.result.today.weather;
////		weather.setRealTimeInfo(info);
//	}
//
//	public boolean isPrepared() {
//		return mWeatherBean != null;
//	}
//
//	private void addForcastWeatherInfo(WeatherInfo weather) {
//
////		ArrayList<ForcastDailyInfo> forcastList = mWeatherInfo.getForcastInfo();
////		if (forcastList == null) {
////			forcastList = new ArrayList<ForcastDailyInfo>();
////			for (Forcast bean : mWeatherBean.result.future) {
////				ForcastDailyInfo info = new ForcastDailyInfo();
////				info.cityName = mCityName;
////				info.fa = Integer.parseInt(bean.weather_id.fa);
////				info.fb = Integer.parseInt(bean.weather_id.fb);
////				int index = bean.temperature.indexOf(WeatherConstants.DEGREE_SIGN_FORMAL);
////				info.lowTemp = bean.temperature.substring(0, index);
////				info.highTemp = bean.temperature.substring(index + 2, bean.temperature.length() - 1);
////
////				LogUtils.d(info.lowTemp+"  "+info.highTemp);
////				info.weatherName = bean.weather;
////				forcastList.add(info);
////			}
////			while (forcastList.size() < 7) {
////				ForcastDailyInfo info = new ForcastDailyInfo();
////				forcastList.add(info);
////			}
////
////		} else {
////			int size = mWeatherBean.result.future.size();
////			int i = 0;
////			for (; i < size; i++) {
////				Forcast bean = mWeatherBean.result.future.get(i);
////				ForcastDailyInfo info = forcastList.get(i);
////				info.cityName = mCityName;
////				info.fa = Integer.parseInt(bean.weather_id.fa);
////				info.fb = Integer.parseInt(bean.weather_id.fb);
////				int index = bean.temperature.indexOf(WeatherConstants.DEGREE_SIGN_FORMAL);
////				info.lowTemp = bean.temperature.substring(0, index);
////				info.highTemp = bean.temperature.substring(index + 2, bean.temperature.length() - 1);
////				info.weatherName = bean.weather;
////			}
////
////			for (i++; i < 7; i++) {
////				ForcastDailyInfo info = forcastList.get(i);
////				info.cityName = null;
////			}
////		}
////
////		weather.setForcastInfo(forcastList);
//	}
//
//	@Override
//	public WeatherInfo converToWeather() {
//		if (mWeatherInfo == null) {
//			mWeatherInfo = new WeatherInfo();
//			mWeatherInfo.setCityName(mCityName);
//		}
//		addAirInfo(mWeatherInfo);
//		addRealTimeWeatehrInfo(mWeatherInfo);
//		addForcastWeatherInfo(mWeatherInfo);
//		SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());
//		mWeatherInfo.setUpdateTime(newFormat.format(new Date()));
//		return mWeatherInfo;
//	}
//
//	public void getPM25(String result){
//		try {
//			JSONArray array = new JSONObject(result).getJSONArray("result");
//			JSONObject object = (JSONObject) array.get(0);
//			pm25 = (String) object.get("PM2.5");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
