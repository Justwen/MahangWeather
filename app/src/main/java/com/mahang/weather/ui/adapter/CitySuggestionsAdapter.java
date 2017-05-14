package com.mahang.weather.ui.adapter;

import com.mahang.weather.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CitySuggestionsAdapter extends CursorAdapter {

	public CitySuggestionsAdapter(Context context) {
		super(context, null, 0);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		return LayoutInflater.from(context).inflate(R.layout.activity_city_list_query_item, parent, false);
	}

	@Override
	public void bindView(View convertView, Context context, Cursor cursor) {

		TextView city = (TextView) convertView.findViewById(R.id.city);
		TextView district = (TextView) convertView.findViewById(R.id.district);
		String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
		String districtName = cursor.getString(cursor.getColumnIndexOrThrow("DISTRICT"));
		city.setText(name);
		district.setText(districtName);
		convertView.setTag(name);

	}
	

}
