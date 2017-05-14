package com.mahang.weather.view.card;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahang.weather.R;
import com.mahang.weather.common.ResManagerImpl;
import com.mahang.weather.model.entity.DailyInfo;
import com.mahang.utils.view.ViewHolderUtils;

public class WeatherCardDailyView extends CardView {

	private WeatherCardDailyChartView mChartView;
	
	private GridView mGridView;
	
	private ArrayList<DailyInfo> mDailyInfoList;
	
	private ResManagerImpl mResManagerImpl;
	
	private Calendar mCalendar;
	
	private SimpleDateFormat mWeekFormat = new SimpleDateFormat("E", Locale.getDefault());
	
	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
	
	private BaseAdapter mAdapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null){
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_daily_view_item, parent, false);
			}
			TextView week = ViewHolderUtils.get(convertView, R.id.week);
			TextView date = ViewHolderUtils.get(convertView, R.id.date);
			ImageView icon_day = ViewHolderUtils.get(convertView, R.id.icon_day);
			TextView text_day = ViewHolderUtils.get(convertView, R.id.text_day);
			ImageView icon_night = ViewHolderUtils.get(convertView, R.id.icon_night);
			TextView text_night = ViewHolderUtils.get(convertView, R.id.text_night);
			
			DailyInfo info = (DailyInfo) getItem(position);
			text_day.setText(info.getTextDay());
			text_night.setText(info.getTextNight());
			icon_day.setImageDrawable(mResManagerImpl.getWeatherIcon(info.getCodeDay()));
			icon_night.setImageDrawable(mResManagerImpl.getWeatherIcon(info.getCodeNight()));
			
			
			mCalendar.setTimeInMillis(System.currentTimeMillis());
			mCalendar.roll(Calendar.DAY_OF_YEAR, position);
			
			if (position == 0){
				week.setText(getResources().getString(R.string.today));
			} else if (position == 1){
				week.setText(getResources().getString(R.string.tommorow));
			} else {
				week.setText(mWeekFormat.format(mCalendar.getTime()).replace("����", "��"));
			}
			date.setText(mDateFormat.format(mCalendar.getTime()));
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mDailyInfoList.get(position);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDailyInfoList == null ? 0 : mDailyInfoList.size() - 1;
		}
	};
	
	public WeatherCardDailyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.card_daily_view, this, true);
		mResManagerImpl = ResManagerImpl.getInstance(getContext());
		mCalendar = Calendar.getInstance();
	}
	
	@Override
	protected void onFinishInflate() {
		mChartView = (WeatherCardDailyChartView) findViewById(R.id.daily_info_chart);
		mGridView = (GridView) findViewById(R.id.daily_info);
		mGridView.setAdapter(mAdapter);
		mGridView.setFocusable(false);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		super.onFinishInflate();
	}
	
	public void setDailyInfo(ArrayList<DailyInfo> list){
		mDailyInfoList = list;
		mAdapter.notifyDataSetChanged();
		mChartView.addData(list);
		mChartView.invalidate();
	}

}
