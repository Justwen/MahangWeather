package com.mahang.weather.weatherlist.mvp;


import com.mahang.weather.base.MvpBasePresenter;
import com.mahang.weather.base.MvpBaseView;
import com.mahang.weather.model.entity.WeatherInfo;

import java.util.List;

public interface WeatherListContract {

    interface Presenter extends MvpBasePresenter{

        void loadWeatherInfo();

        void swap(int start,int end);

        void remove(int index);

        void onClick(int index);


    }

    interface View extends MvpBaseView<Presenter>{

        void setData(List<WeatherInfo> weatherInfos);

        void setActivityResult(String name,boolean value);

        void setActivityResult(String name,int value);

        void finish();
    }
}
