package com.mahang.weather.main.mvp.detail;

import android.os.Message;
import android.widget.Toast;

import com.mahang.weather.common.Constants;
import com.mahang.weather.common.EventHandler;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.entity.WeatherInfo;
import com.squareup.otto.Subscribe;

/**
 * Created by hasee on 2017/5/7.
 */

public class WeatherInfoPresenter implements WeatherInfoContract.Presenter{

    private WeatherInfoContract.View mView;

    private WeatherInfo mWeatherInfo;

    private WeatherModel mWeatherModel;

    public WeatherInfoPresenter(WeatherModel model,WeatherInfoContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mWeatherModel = model;
        EventHandler.getInstance().register(this);
    }

    @Subscribe
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Constants.MSG_QUERY_WEATHER_SUCCESS:
            case Constants.MSG_UPDATE_WEATHER_SUCCESS:
                if (msg.obj.equals(mWeatherInfo.getCityName())){
                    mView.setRefreshing(false);
                    mView.notifyDataSetChanged();
                }
                break;
            case Constants.MSG_QUERY_WEATHER_FAILURE:
            case Constants.MSG_UPDATE_WEATHER_FAILURE:
                if (msg.obj.equals(mWeatherInfo.getCityName())){
                    mView.setRefreshing(false);
                }
                Toast.makeText(mView.getContext(),msg.arg1,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDestroy() {
        EventHandler.getInstance().unregister(this);
    }

    @Override
    public void setData(WeatherInfo info) {
        mWeatherInfo = info;
        mView.setData(info);
    }

    @Override
    public void onRefresh() {
        mWeatherModel.updateWeather(mWeatherInfo.getCityName());
    }
}
