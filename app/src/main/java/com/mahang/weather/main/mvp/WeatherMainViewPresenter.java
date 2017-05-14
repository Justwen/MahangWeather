package com.mahang.weather.main.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mahang.weather.common.Constants;
import com.mahang.weather.common.PreferenceKey;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.view.animation.AnimationController;
import com.mahang.weather.view.animation.AnimationControllerImpl;

import java.util.List;



public class WeatherMainViewPresenter implements WeatherMainViewContract.Presenter {

    private WeatherModel mWeatherModel;

    private WeatherMainViewContract.View mView;

    private List<WeatherInfo> mWeatherInfos;

    private AnimationController mAnimationController;

    private Context mContext;

    public WeatherMainViewPresenter(Context context,WeatherMainViewContract.View view) {
        mContext = context;
        mWeatherModel = WeatherModelImpl.getInstance(context);
        mView = view;
        mView.setPresenter(this);
        mAnimationController = AnimationControllerImpl.getInstance();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadWeatherInfo() {
        mWeatherInfos = mWeatherModel.getWeatherList();
        if (mWeatherInfos == null || mWeatherInfos.isEmpty()){
            mView.startQueryPage();
            return;
        }
        mView.setData(mWeatherInfos);
        onItemChange(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_CITY_LIST){
            if (resultCode == Activity.RESULT_OK) {
                int index = data.getIntExtra("index", 0);
                boolean shouldUpdate = data.getBooleanExtra("update",false);
                if (shouldUpdate){
                    mView.notifyDataSetChanged();
                }
                if (index != mView.getCurrentItem()){
                    mView.setCurrentItem(index);
                } else{
                    onItemChange(index);
                }
            }
        } else if (requestCode == Constants.REQUEST_CODE_SETTINGS){
            if (resultCode == Activity.RESULT_OK){
                mView.notifyDataSetChanged();
            }
        } else if (requestCode == Constants.REQUEST_CODE_QUERY_CITY){
            if (resultCode == Activity.RESULT_OK){
                mView.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemChange(int index) {
        mView.setTitle(mWeatherInfos.get(index).getCityName());
        int code = mWeatherInfos.get(index).getRealTimeInfo().getCode();
        mAnimationController.setWeatherCode(code);
    }

    @Override
    public void updateHeaderView() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String cityName = prefs.getString(PreferenceKey.KET_LOCATION_CITY,null);
        for (WeatherInfo info : mWeatherInfos){
            if (info.getCityName().equals(cityName)){
                mView.updateHeaderView(info);
                return;
            }
        }
        mView.updateHeaderView(null);
    }
}
