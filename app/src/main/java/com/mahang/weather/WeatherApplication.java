package com.mahang.weather;

import android.app.Application;

import com.mahang.utils.LogUtils;
import com.mahang.weather.common.ApplicationContextHolder;
import com.mahang.weather.common.ResManagerImpl;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.view.animation.AnimationControllerImpl;

public class WeatherApplication extends Application {

	@Override
	public void onCreate() {
		ApplicationContextHolder.setContext(this);
		LogUtils.setDebug(BuildConfig.DEBUG);
		initialize();
		super.onCreate();
	}

	private void initialize(){
        WeatherModelImpl.getInstance(this);
        ResManagerImpl.newInstance(this);
		AnimationControllerImpl.newInstance(this);

    }

}
