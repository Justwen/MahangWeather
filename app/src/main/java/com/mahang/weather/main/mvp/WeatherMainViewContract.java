package com.mahang.weather.main.mvp;

import android.content.Intent;

import com.mahang.weather.base.MvpBasePresenter;
import com.mahang.weather.base.MvpBaseView;
import com.mahang.weather.model.entity.WeatherInfo;

import java.util.List;

/**
 * Created by hasee on 2017/5/7.
 */

public interface WeatherMainViewContract {

    interface Presenter extends MvpBasePresenter{

        void loadWeatherInfo();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onItemChange(int index);

        void updateHeaderView();


    }

    interface View extends MvpBaseView<Presenter>{

        void setData(List<WeatherInfo> weatherInfos);

        void notifyDataSetChanged();

        void setCurrentItem(int index);

        int getCurrentItem();

        void setTitle(CharSequence title);

        void updateHeaderView(WeatherInfo info);

        void startQueryPage();
    }
}
