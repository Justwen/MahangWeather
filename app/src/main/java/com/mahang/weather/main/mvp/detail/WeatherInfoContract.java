package com.mahang.weather.main.mvp.detail;

import android.content.Context;

import com.mahang.weather.base.MvpBasePresenter;
import com.mahang.weather.base.MvpBaseView;
import com.mahang.weather.model.entity.WeatherInfo;


public class WeatherInfoContract {

    public interface Presenter extends MvpBasePresenter{

        void setData(WeatherInfo info);

        void onRefresh();

    }

    public interface View extends MvpBaseView<Presenter>{

        void setData(WeatherInfo info);

        void setRefreshing(boolean refresh);

        void notifyDataSetChanged();

        Context getContext();
    }
}
