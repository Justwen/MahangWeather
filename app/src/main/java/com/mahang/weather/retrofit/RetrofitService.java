package com.mahang.weather.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Justwen on 2017/10/10.
 */

public interface RetrofitService {

    @GET
    Observable<String> get(@Url String url);

    @GET
    Observable<String> get(@QueryMap Map<String, String> map);

    @POST
    Observable<String> post(@Url String url);

    @FormUrlEncoded
    Observable<String> post(@FieldMap Map<String, String> map);

}
