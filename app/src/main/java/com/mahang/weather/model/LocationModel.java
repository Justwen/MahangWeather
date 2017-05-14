package com.mahang.weather.model;

/**
 * Created by hasee on 2017/3/19.
 */

public interface LocationModel {

    void requestLocation();

    void saveLocation(String cityName);
}
