package com.mahang.weather.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mahang.weather.R;
import com.mahang.weather.base.BaseFragment;
import com.mahang.weather.model.WeatherModel;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.presenter.WeatherPresenter;
import com.mahang.weather.ui.WeatherViewInterface;
import com.mahang.weather.ui.adapter.CityListAdapter;
import com.mahang.weather.view.SwipeDismissListView;

public class CityListFragment extends BaseFragment implements View.OnClickListener,MenuItemCompat.OnActionExpandListener,WeatherViewInterface {

	private List<WeatherInfo> mWeatherList = new ArrayList<>();

	private SwipeDismissListView mListView;

	private WeatherModel mWeatherModel;

	private CityListAdapter mAdapter;

	private Intent mResultData;

	private WeatherPresenter mWeatherPresenter;

	private SwipeDismissListView.onTouchCallBack mTouchCallBack = new SwipeDismissListView.onTouchCallBack() {
		@Override
		public boolean onMove(int start, int end) {
			mWeatherModel.swap(start,end);
			if (mResultData == null) {
				mResultData = new Intent();
			}
			mResultData.putExtra("update",true);
			getActivity().setResult(Activity.RESULT_OK,mResultData);
			return true;
		}

		@Override
		public void onSwipe(int position) {
			mWeatherModel.remove(position);
			if (mResultData == null) {
				mResultData = new Intent();
			}
			mResultData.putExtra("update",true);
			getActivity().setResult(Activity.RESULT_OK,mResultData);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mWeatherModel = WeatherModelImpl.getInstance(getActivity());
		Bundle bundle = getArguments();
		if (bundle != null) {
		//	mWeatherList = bundle.getParcelableArrayList("data");
		} else {
		//	mWeatherList = mWeatherModel.getWeatherList();
		}
		mAdapter = new CityListAdapter(getContext(),mWeatherList);
		mWeatherPresenter = new WeatherPresenter(this);
		mWeatherPresenter.attach(this);
        mWeatherPresenter.loadWeather();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		mWeatherPresenter.detach();
		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_city_list, container, false);
		mListView = (SwipeDismissListView) view.findViewById(R.id.list);
		mListView.setAdapter(mAdapter);
        mListView.setOnTouchCallBack(mTouchCallBack);
        mAdapter.setOnClickListener(this);
		return view;
	}

	@Override
	public void notifyDataSetChanged() {
		mAdapter.notifyDataSetChanged();
	}


	@Override
	public void onClick(View v) {
		int index = (int) v.getTag();
		if (mResultData == null){
			mResultData = new Intent();
		}
		mResultData.putExtra("index",index);
		getActivity().setResult(Activity.RESULT_OK,mResultData);
		getActivity().finish();
	}

	@Override
	public boolean onMenuItemActionExpand(MenuItem item) {
		mListView.setSwipeEnabled(false);
		return false;
	}

	@Override
	public boolean onMenuItemActionCollapse(MenuItem item) {
		mListView.setSwipeEnabled(true);
		return false;
	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showToast(String string) {

	}

	@Override
	public void updateWeather() {

	}

	@Override
	public void showWeather(List<WeatherInfo> infos) {

		if (mWeatherList == null){
			mWeatherList = new ArrayList<>();
		} else {
			mWeatherList.clear();
		}
		mWeatherList.addAll(infos);
		mAdapter.notifyDataSetChanged();
	}
}
