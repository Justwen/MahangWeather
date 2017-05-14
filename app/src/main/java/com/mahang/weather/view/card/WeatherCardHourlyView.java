package com.mahang.weather.view.card;

import java.util.List;

import com.mahang.weather.R;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.entity.DailyInfo;
import com.mahang.weather.model.entity.HourlyInfo;
import com.mahang.utils.view.ViewHolderUtils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

public class WeatherCardHourlyView extends CardView {

	private HorizontalScrollView mHourlyView;

	private WeatherCardHourlyChartView mHourlyChartView;

	private FrameLayout mChartView;

	private RecyclerView mRecyclerView;

	private List<HourlyInfo> mHourlyInfos;

	private TextView mSunriseTv;

	private ListView mListView;

	private class MyViewHolder extends RecyclerView.ViewHolder {

		TextView timeTv;;
		
		View itemView;

		public MyViewHolder(View itemView) {
			super(itemView);
			this.itemView = itemView;
			timeTv = (TextView) itemView.findViewById(R.id.card_hourly_item_time);
		}
		
		public View getView(){
			return itemView;
		}

	}

	private RecyclerView.Adapter<MyViewHolder> mAdapter = new RecyclerView.Adapter<MyViewHolder>() {

		private OnClickListener mClickListener;

		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return mHourlyInfos == null ? 0 : mHourlyInfos.size();
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, final int postion) {
			String time = mHourlyInfos.get(postion).getDate();
			int index = time.indexOf(" ");
			time = time.substring(index+1, time.length());
			holder.timeTv.setText(time);
			if (mClickListener == null) {
				mClickListener = new OnClickListener() {

					@Override
					public void onClick(View v) {
						onItemClick(postion);

					}
				};
			}
			holder.getView().setOnClickListener(mClickListener);
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			View view = LayoutInflater.from(getContext()).inflate(R.layout.card_hourly_chart_view_item, parent, false);
			MyViewHolder holder = new MyViewHolder(view);
			return holder;
		}
	};

	private BaseAdapter mListViewAdapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_hourly_list_view_item, parent, false);
			}
			TextView timeTv = ViewHolderUtils.get(convertView, R.id.card_hourly_item_time);
			String time = mHourlyInfos.get(position).getDate();
			int index = time.indexOf(" ");
			time = time.substring(index, time.length());
			timeTv.setText(time);
			TextView temp = ViewHolderUtils.get(convertView, R.id.card_hourly_item_temp);
			temp.setText(mHourlyInfos.get(position).getTemp()
					+ Constants.DEGREE_SIGN);
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
			return mHourlyInfos.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mHourlyInfos == null ? 0 : mHourlyInfos.size();
		}
	};

	public WeatherCardHourlyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.card_hourly_view, this, true);
	}

	public WeatherCardHourlyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		mHourlyChartView = (WeatherCardHourlyChartView) findViewById(R.id.hourly_info_chart);
		mRecyclerView = (RecyclerView) findViewById(R.id.hourly_info);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
		mRecyclerView.setAdapter(mAdapter);
		mChartView = (FrameLayout) mHourlyChartView.getParent();
		mChartView.setMinimumWidth(500);
		mChartView.setVisibility(View.GONE);
		mSunriseTv = (TextView) findViewById(R.id.sun);

		mListView = (ListView) findViewById(R.id.card_hourly_list_view);
		mListView.setAdapter(mListViewAdapter);
		mListView.setFocusable(false);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				WeatherCardHourlyView.this.onItemClick(position);

			}
		});
		super.onFinishInflate();
	}

	public void setData(List<HourlyInfo> info, DailyInfo dailyInfo) {
		mHourlyInfos = info;
		if (info.size() <= 4) {
			loadListView();
		} else {
			loadChartView();
		}

		mSunriseTv.setText(String.format(getResources().getString(R.string.card_hourly_sunrise_sunset), dailyInfo.getSunrise(), dailyInfo.getSunset()));
	}

	private void loadChartView() {
		mChartView.setVisibility(View.VISIBLE);
		mListView.setVisibility(View.GONE);
		mHourlyChartView.setData(mHourlyInfos);
		mAdapter.notifyDataSetChanged();
	}

	private void loadListView() {
		mListView.setVisibility(View.VISIBLE);
		mChartView.setVisibility(View.GONE);
		mListViewAdapter.notifyDataSetChanged();
	}

	private void onItemClick(int index) {

	}

}
