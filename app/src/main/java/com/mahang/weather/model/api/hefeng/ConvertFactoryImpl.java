package com.mahang.weather.model.api.hefeng;

import com.alibaba.fastjson.JSON;
import com.mahang.weather.R;
import com.mahang.weather.common.ApplicationContextHolder;
import com.mahang.weather.model.entity.AirInfo;
import com.mahang.weather.model.entity.CityInfo;
import com.mahang.weather.model.entity.DailyInfo;
import com.mahang.weather.model.entity.HourlyInfo;
import com.mahang.weather.model.entity.RealTimeInfo;
import com.mahang.weather.model.entity.SuggestionInfo;
import com.mahang.weather.model.entity.WeatherInfo;

import java.util.ArrayList;

class ConvertFactoryImpl {

    private String mErrorMessage;

    public WeatherInfo getWeatherInfo(String js) {
        mErrorMessage = null;
        WeatherBean weatherBean = JSON.parseObject(js, WeatherBean.class);
        if (weatherBean == null) {
            return null;
        }
        WeatherInfo info = null;
        WeatherBean.HeWeather6Bean weather6Bean = weatherBean.getHeWeather6().get(0);
        String status = weather6Bean.getStatus();
        if (!status.equals("OK")) {
            info = new WeatherInfo();
            info.setCityInfo(getCityInfo(weather6Bean));
            info.setAirInfo(getAirInfo(weather6Bean));
            info.setDailyInfo(getDailyInfo(weather6Bean));
            info.setHourlyInfo(getHourlyInfo(weather6Bean));
            info.setSuggestionInfos(getSuggestionInfo(weather6Bean));
            info.setRealTimeInfo(getRealTimeInfo(weather6Bean));
        } else {
            mErrorMessage = getErrorMessage(status);
        }
        return info;
    }

    private CityInfo getCityInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        CityInfo cityInfo = new CityInfo();
        WeatherBean.HeWeather6Bean.BasicBean basicBean = weather6Bean.getBasic();
        cityInfo.setCityId(basicBean.getCid());
        cityInfo.setCityName(basicBean.getLocation());
        cityInfo.setLat(basicBean.getLat());
        cityInfo.setLon(basicBean.getLon());
        return cityInfo;
    }

    private ArrayList<HourlyInfo> getHourlyInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        ArrayList<HourlyInfo> hourlyInfos = new ArrayList<>();
        for (WeatherBean.HeWeather6Bean.HourlyBean bean : weather6Bean.getHourly()) {
            HourlyInfo info = new HourlyInfo();
            info.setHum(bean.getHum());
            info.setTemp(bean.getTmp());
            info.setPres(bean.getPres());
            info.setPop(bean.getPop());
            info.setDate(bean.getTime());
            hourlyInfos.add(info);
        }
        return hourlyInfos;
    }

    private ArrayList<DailyInfo> getDailyInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        ArrayList<DailyInfo> dailyInfos = new ArrayList<>();
        for (WeatherBean.HeWeather6Bean.DailyForecastBean bean : weather6Bean.getDaily_forecast()) {
            DailyInfo info = new DailyInfo();
            info.setCodeDay(bean.getCond_code_d());
            info.setCodeNight(bean.getCond_code_n());
            info.setTextDay(bean.getCond_txt_d());
            info.setTextNight(bean.getCond_txt_n());
            info.setMax(bean.getTmp_max());
            info.setMin(bean.getTmp_min());
            dailyInfos.add(info);
        }
        return dailyInfos;
    }

    private AirInfo getAirInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        return null;
    }

    private ArrayList<SuggestionInfo> getSuggestionInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        return null;
    }

    private RealTimeInfo getRealTimeInfo(WeatherBean.HeWeather6Bean weather6Bean) {
        RealTimeInfo info = new RealTimeInfo();
        WeatherBean.HeWeather6Bean.NowBean bean = weather6Bean.getNow();
        info.setCode(bean.getCond_code());
        info.setText(bean.getCond_txt());
        info.setTemp(bean.getTmp());
        return info;
    }

    private String getErrorMessage(String status) {
        int strId;
        switch (status) {
            case "unknown location":
                strId = R.string.msg_error_unknown_city;
                break;
            case "no data for this location":
                strId = R.string.msg_error_no_data;
                break;
            case "no more requests":
                strId = R.string.msg_error_no_more_requests;
                break;
            case "param invalid":
                strId = R.string.msg_error_param_invalid;
                break;
            case "too fast":
                strId = R.string.msg_error_too_fast;
                break;
            case "dead":
                strId = R.string.msg_error_server_unavailable;
                break;
            case "invalid key":
            case "permission denied":
            case "sign error":
                strId = R.string.msg_error_default;
                break;
            default:
                strId = R.string.msg_error_default;
                break;
        }
        return ApplicationContextHolder.getContext().getString(strId);
    }

    public String getErrorMessage() {
        if (mErrorMessage == null) {
            return ApplicationContextHolder.getContext().getString(R.string.msg_error_default);
        } else {
            return mErrorMessage;
        }

    }

}
