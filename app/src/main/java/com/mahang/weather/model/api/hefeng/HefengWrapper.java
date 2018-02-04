package com.mahang.weather.model.api.hefeng;

import com.mahang.utils.LogUtils;
import com.mahang.weather.model.OnHttpCallBack;
import com.mahang.weather.model.api.WeatherApi;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.retrofit.RetrofitHelper;
import com.mahang.weather.rxjava.BaseSubscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HefengWrapper implements WeatherApi {

    static final String API_KEY = "11a5a372536c4ea9b51a87bb6ffdc23a";

    private static final String BASE_URL = "https://free-api.heweather.com/s6/";

    private ConvertFactoryImpl mConvertFactory;

    private HefengService mService;

    public HefengWrapper() {
        mService = (HefengService) RetrofitHelper.getInstance().build(BASE_URL).getService(HefengService.class);
        mConvertFactory = new ConvertFactoryImpl();
    }

    @Override
    public void queryWeather(String cityName, final OnHttpCallBack<WeatherInfo> callBack) {
        mService.getWeather(API_KEY,cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<String, WeatherInfo>() {
                    @Override
                    public WeatherInfo apply(@NonNull String s) throws Exception {
                        LogUtils.d(s);
                        WeatherInfo info = mConvertFactory.getWeatherInfo(s);
                        if (info != null) {
                            return info;
                        } else {
                            throw new IllegalStateException(mConvertFactory.getErrorMessage());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<WeatherInfo>() {
                    @Override
                    public void onNext(@NonNull WeatherInfo info) {
                        callBack.onSuccess(info);
                        super.onNext(info);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        callBack.onError(throwable.getMessage());
                        super.onError(throwable);
                    }
                });
    }
}
