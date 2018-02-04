package com.mahang.weather.model.api.hefeng;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Justwen on 2018/2/4.
 */

public interface HefengService {

    @GET("weather")
    Observable<String> getWeather(@Query("key") String key, @Query("location") String location);

    @GET
    Observable<String> get(@Url String url);
}
