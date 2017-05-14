package com.mahang.weather.view.card;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.mahang.weather.R;
import com.mahang.weather.model.entity.AirInfo;

public class WeatherCardAirView extends CardView {
	
	private AirInfo mAirInfo;
	
	private TextView mQualityTv;
	
	private TextView mAirIndexTv;
	
	private TextView mUpdateTv;
	
	private TextView mPm25Tv;
	
	public WeatherCardAirView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.card_air_view, this, true);
	}
	
	@Override
	protected void onFinishInflate() {
		mAirIndexTv = (TextView) findViewById(R.id.air_index);
		mUpdateTv = (TextView)findViewById(R.id.detail_air_update_time);
		mPm25Tv = (TextView) findViewById(R.id.detail_air_pm25);
		mQualityTv = (TextView)findViewById(R.id.air_quality);
		super.onFinishInflate();
	}
	
	public void setAirInfo(AirInfo airInfo){
		mAirIndexTv.setText(airInfo.getAqi());
		mPm25Tv.setText(airInfo.getPm25());
		mQualityTv.setText(airInfo.getQuality());
		
		mUpdateTv.setText(String.format(getResources().getString(R.string.card_air_update_time), airInfo.getUpdateTime()));
	}
	

}
