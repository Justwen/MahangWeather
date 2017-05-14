package com.mahang.weather.model.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mahang.weather.common.PreferenceKey;
import com.mahang.weather.model.LocationModel;
import com.mahang.weather.model.api.location.LocationApi;
import com.mahang.weather.model.api.location.baidu.BaiduLocation;

import java.util.ArrayList;
import java.util.List;


public class LocationModelImpl implements LocationModel {

    private Context mContext;

    private static LocationModel sInstance;

    private List<LocationApi> mApiList;

    private LocationApi mApi;

    public static LocationModel getInstance(Context context) {
        if (sInstance == null){
            sInstance = new LocationModelImpl(context);
        }
        return sInstance;
    }

    private LocationModelImpl(Context context) {
        mContext = context.getApplicationContext();
        mApiList = new ArrayList<>();
        mApi = new BaiduLocation(context);
        mApiList.add(mApi);
    }

    @Override
    public void requestLocation(){
        mApi.requestLocation();
    }

    @Override
    public void saveLocation(String cityName){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefs.edit().putString(PreferenceKey.KET_LOCATION_CITY,cityName).apply();
    }
}
