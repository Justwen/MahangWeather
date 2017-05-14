package com.mahang.weather.model.api.hefeng;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mahang.weather.model.entity.RealTimeInfo;
import com.mahang.weather.model.entity.AirInfo;
import com.mahang.weather.model.entity.CityInfo;
import com.mahang.weather.model.entity.DailyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mahang.weather.model.api.ConvertFactory;
import com.mahang.weather.model.entity.HourlyInfo;
import com.mahang.weather.model.entity.SuggestionInfo;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.utils.LogUtils;

class ConvertFactoryImpl implements ConvertFactory {
	
	private static final int DAILY_COUNT = 7;
	
	private static Map<Integer, Integer> mCodeMap = new HashMap<Integer, Integer>();

	private WeatherInfo mTarget;

	private String mResult;
	
	static{
		//��
		mCodeMap.put(100, 0);
		//����
		mCodeMap.put(101, 1);
		// ����
		mCodeMap.put(102, 1);
		// ������
		mCodeMap.put(103, 1);
		//��
		mCodeMap.put(104,2);
	    //�з�
		mCodeMap.put(200,16);
	    //ƽ��
		mCodeMap.put(201,16);
	    //΢��
		mCodeMap.put(202,16);
	    //�ͷ�
		mCodeMap.put(203,16);
	    //���
		mCodeMap.put(204,16);
	    //ǿ��/����
		mCodeMap.put(205,16);
	    //����
		mCodeMap.put(206,16);
	    //���
		mCodeMap.put(207,16);
	    //�ҷ�
		mCodeMap.put(208,16);
	    //�籩
		mCodeMap.put(209,16);
	    //�񱩷�
		mCodeMap.put(210,16);
	    //쫷�
		mCodeMap.put(211,16);
	    //�����
		mCodeMap.put(212,16);
	    //�ȴ��籩
		mCodeMap.put(213,16);
	    //����
		mCodeMap.put(300,3);
	    //ǿ����
		mCodeMap.put(301,3);
		//������
		mCodeMap.put(302,4);
		//ǿ������
		mCodeMap.put(303,4);
		//��������б���
		mCodeMap.put(304,5);
		//С��
		mCodeMap.put(305,7);
		//����
		mCodeMap.put(306,7);
		//����
		mCodeMap.put(307,8);
		//���˽���
		mCodeMap.put(308,8);
		//ϸ�� ëë��
		mCodeMap.put(309,7);
		//����
		mCodeMap.put(310,8);
		//����
		mCodeMap.put(311,8);
		//�ش���
		mCodeMap.put(312,8);
		//����
		mCodeMap.put(313,13);
		//Сѩ
		mCodeMap.put(400,9);
		//��ѩ
		mCodeMap.put(401,9);
		//��ѩ
		mCodeMap.put(402,10);
		//��ѩ
		mCodeMap.put(403,10);
		//���ѩ
		mCodeMap.put(404,6);
		//��ѩ����
		mCodeMap.put(405,6);
		//�����ѩ
		mCodeMap.put(406,6);
		//��ѩ
		mCodeMap.put(407,9);
		//����
		mCodeMap.put(500,11);
		//��
		mCodeMap.put(501,11);
		//��
		mCodeMap.put(502,11);
		//��ɳ
		mCodeMap.put(503, 12);
		//����
		mCodeMap.put(504, 12);
		//��ɽ��
		mCodeMap.put(506, 12);
		//ɳ����
		mCodeMap.put(507, 12);
		//ǿɳ����
		mCodeMap.put(508, 12);
		//��
		mCodeMap.put(900, 15);
		//��
		mCodeMap.put(901, 14);
	}

	public ConvertFactoryImpl(String result,WeatherInfo target) {
		mTarget = target;
		mResult = result;
	}

	private void convertCityInfo(WeatherInfo info, WeatherBean bean){
		CityInfo cityInfo = info.getCityInfo();
		if (cityInfo == null){
			cityInfo = new CityInfo();
		}
		cityInfo.setCityId(bean.getBasic().getId());
		cityInfo.setCityName(bean.getBasic().getCity());
		cityInfo.setLat(bean.getBasic().getLat());
		cityInfo.setLon(bean.getBasic().getLon());
		info.setCityInfo(cityInfo);
	}
	
	private void convertAirInfo(WeatherInfo info,WeatherBean bean){
		if (bean.getAqi() == null){
			return;
		}
		AirInfo airInfo = info.getAirInfo();
		if (airInfo == null){
			airInfo = new AirInfo();
		}
		airInfo.setCityName(bean.getBasic().getCity());
		airInfo.setUpdateTime(bean.getBasic().getUpdate().getLoc());
		airInfo.setAqi(bean.getAqi().getCity().getAqi());
		airInfo.setQuality(bean.getAqi().getCity().getQlty());
		airInfo.setPm25(bean.getAqi().getCity().getPm25());
		airInfo.setPm10(bean.getAqi().getCity().getPm10());
		info.setAirInfo(airInfo);
	}
	
	private void convertDailyInfo(WeatherInfo info,WeatherBean bean){
		ArrayList<DailyInfo> list = info.getDailyInfo();
		boolean newClass = false;
		if (list == null){
			list = new ArrayList<>();
			newClass = true;
		}
		
		for (int i=0; i < DAILY_COUNT ;i++){
			
			DailyInfo dailyInfo;
			if (newClass){
				dailyInfo = new DailyInfo();
				dailyInfo.setCityName(bean.getBasic().getCity());
			} else{
				dailyInfo = list.get(i);
			}

			if (bean.getDaily_forecast().size() <= i ){
				dailyInfo.setDate(null);
				continue;
			}
			
			WeatherBean.Daily_forecast daily = bean.getDaily_forecast().get(i);
			dailyInfo.setDate(daily.getDate());
			dailyInfo.setCodeDay(getFixCode(daily.getCond().getCode_d()));
			dailyInfo.setCodeNight(getFixCode(daily.getCond().getCode_n()));
			dailyInfo.setTextDay(daily.getCond().getTxt_d());
			dailyInfo.setTextNight(daily.getCond().getTxt_n());
			dailyInfo.setMax(daily.getTmp().getMax());
			dailyInfo.setMin(daily.getTmp().getMin());
			dailyInfo.setSunrise(daily.getAstro().getSr());
			dailyInfo.setSunset(daily.getAstro().getSs());
			if (newClass){
				list.add(dailyInfo);
			}
		}
		info.setDailyInfo(list);
		
	}
	
	private void convertRealTimeInfo(WeatherInfo info,WeatherBean bean){
		RealTimeInfo timeInfo = info.getRealTimeInfo();
		if (timeInfo == null){
			timeInfo = new RealTimeInfo();
		}
		timeInfo.setCityName(bean.getBasic().getCity());
		timeInfo.setUpdateTime(bean.getBasic().getUpdate().getLoc());
		timeInfo.setCode(getFixCode(bean.getNow().getCond().getCode()));
		timeInfo.setText(bean.getNow().getCond().getTxt());
		timeInfo.setTemp(bean.getNow().getTmp());
		timeInfo.setHumidity(bean.getNow().getHum());
		timeInfo.setWind(bean.getNow().getWind().getSc());
		info.setRealTimeInfo(timeInfo);
		
	}
	
	private void converSuggestionInfo(WeatherInfo info,WeatherBean bean){
		ArrayList<SuggestionInfo> infos = info.getSuggestionInfos();
		if (infos == null){
			infos = new ArrayList<SuggestionInfo>();
		}else{
			infos.clear();
		}
		
//		Suggestion suggestion = bean.getSuggestion();
//		SuggestionInfo suggestionInfo = new SuggestionInfo();
//		suggestionInfo.setTitle(title);
		
		info.setSuggestionInfos(infos);
		
	}
	
	
	private void convertHourlyInfo(WeatherInfo info,WeatherBean bean){
		ArrayList<HourlyInfo> infos = info.getHourlyInfo();
		if (infos == null){
			infos = new ArrayList<HourlyInfo>();
		}else{
			infos.clear();
		}
		for (int i=0; i<bean.getHourly_forecast().size(); i++){
			HourlyInfo hourlyInfo = new HourlyInfo();
			WeatherBean.Hourly_forecast hourly_forecast = bean.getHourly_forecast().get(i);
			hourlyInfo.setTemp(hourly_forecast.getTmp());
			hourlyInfo.setDate(hourly_forecast.getDate());
			hourlyInfo.setHum(hourly_forecast.getHum());
			hourlyInfo.setPop(hourly_forecast.getPop());
			infos.add(hourlyInfo);
		}
		info.setHourlyInfo(infos);
	}
	
	private int getFixCode(int code){
		if (mCodeMap.containsKey(code)){
			return mCodeMap.get(code);
		}
		return 1;
	}
	
	
	
	private WeatherBean createBean(String result){
		try{
			Gson gson = new Gson();
			Type type = new TypeToken<WeatherBean>() {}.getType();
			return gson.fromJson(result , type);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


//	@Override
//	public WeatherInfo converToWeather(String result, WeatherInfo info) {
//		WeatherBean bean = createBean(result);
//		if (bean == null){
//			LogUtils.e("weather info is unavailable!!");
//			return null;
//		}
//		if (info == null){
//			info = new WeatherInfo();
//		}
//		info.setCityName(bean.getBasic().getCity());
//		info.setUpdateTime(bean.getBasic().getUpdate().getLoc());
//		convertCityInfo(info,bean);
//		convertAirInfo(info, bean);
//		convertDailyInfo(info, bean);
//		convertHourlyInfo(info, bean);
//		convertRealTimeInfo(info, bean);
//		return info;
//	}

	@Override
	public WeatherInfo converToWeather() {
		WeatherBean bean = createBean(mResult);
		if (bean == null){
			LogUtils.e("weather info is unavailable!!");
			return null;
		}
		if (mTarget == null){
			mTarget = new WeatherInfo();
		}
		mTarget.setCityName(bean.getBasic().getCity());
		mTarget.setUpdateTime(bean.getBasic().getUpdate().getLoc());
		convertCityInfo(mTarget,bean);
		convertAirInfo(mTarget, bean);
		convertDailyInfo(mTarget, bean);
		convertHourlyInfo(mTarget, bean);
		convertRealTimeInfo(mTarget,bean);
		return mTarget;
	}
}
