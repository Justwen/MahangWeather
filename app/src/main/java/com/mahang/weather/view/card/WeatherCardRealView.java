package com.mahang.weather.view.card;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahang.weather.R;
import com.mahang.weather.common.Constants;
import com.mahang.weather.common.ResManagerImpl;
import com.mahang.weather.model.entity.RealTimeInfo;

public class WeatherCardRealView extends CardView {

	private ImageView mWeatherIcon;

	private TextView mTempTv;

	private TextView mWeatherNameTv;
	
	private TextView mUpdateTimeTv;
	
	private TextView mHumTextView;
	

	public WeatherCardRealView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.card_real_view, this, true);
	}

	public void setRealInfo(RealTimeInfo info, boolean dayTime) {
		mWeatherIcon.setImageDrawable(ResManagerImpl.getInstance(getContext()).getWeatherIcon(info.getCode()));
		mTempTv.setText(info.getTemp()+Constants.DEGREE_SIGN);
		mWeatherNameTv.setText(info.getText());
		mHumTextView.setText(info.getHumidity()+"%");
		mUpdateTimeTv.setText(String.format(getResources().getString(R.string.card_air_update_time), info.getUpdateTime()));
	}

	@Override
	protected void onFinishInflate() {
		mWeatherIcon = (ImageView) findViewById(R.id.card_real_icon);
		mTempTv = (TextView) findViewById(R.id.card_real_temp);
		mWeatherNameTv = (TextView) findViewById(R.id.card_real_weather);
		mUpdateTimeTv = (TextView) findViewById(R.id.card_real_update_time);
		mHumTextView = (TextView)findViewById(R.id.card_real_hum);
		super.onFinishInflate();
	}


}
