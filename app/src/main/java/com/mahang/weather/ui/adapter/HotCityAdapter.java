package com.mahang.weather.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahang.weather.R;
import com.mahang.utils.listener.SimpleBaseAdapter;

public class HotCityAdapter extends SimpleBaseAdapter {

	private Context mContext;

	private String[] mHotCityList;

	public HotCityAdapter(Context context) {
		mContext = context;
		mHotCityList = mContext.getResources().getStringArray(R.array.hot_city);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_hot_city_item, parent, false);
		}
		((TextView) convertView.findViewById(R.id.name)).setText(mHotCityList[position]);
		convertView.setTag(mHotCityList[position]);
		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mHotCityList.length;
	}

}
