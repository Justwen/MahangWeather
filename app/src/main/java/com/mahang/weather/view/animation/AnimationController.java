package com.mahang.weather.view.animation;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

public interface AnimationController {

    void setWeatherCode(int code);

    void initialize(ViewGroup rootView, Toolbar toolbar, Window window);
}
