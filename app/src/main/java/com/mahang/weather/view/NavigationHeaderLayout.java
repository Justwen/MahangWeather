package com.mahang.weather.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahang.weather.R;
import com.mahang.weather.common.ResManagerImpl;
import com.mahang.weather.model.entity.WeatherInfo;


public class NavigationHeaderLayout extends FrameLayout {

    private ImageView mIconIv;

    private TextView mWeatherTv;

    private FrameLayout mEmptyView;

    private LinearLayout mContentView;


    public NavigationHeaderLayout(Context context) {
        super(context);
    }

    public NavigationHeaderLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        mEmptyView = (FrameLayout) findViewById(R.id.empty_view);
        mContentView = (LinearLayout) findViewById(R.id.content_view);
        mIconIv = (ImageView) findViewById(R.id.iv_icon);
        mWeatherTv = (TextView) findViewById(R.id.tv_weather);


        super.onFinishInflate();
    }

    public void setWeatherInfo(WeatherInfo info){
        if (info == null){
            mContentView.setVisibility(GONE);
            mEmptyView.setVisibility(VISIBLE);
        } else {
            mContentView.setVisibility(VISIBLE);
            mEmptyView.setVisibility(GONE);
            mIconIv.setImageDrawable(ResManagerImpl.getInstance(getContext()).getWeatherIcon(info.getRealTimeInfo().getCode()));
            mWeatherTv.setText(info.getCityName());
        }
    }

}
