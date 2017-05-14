package com.mahang.weather.main.mvp;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahang.weather.R;
import com.mahang.weather.base.BaseFragment;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.ui.activity.CityListActivity;
import com.mahang.weather.view.NavigationHeaderLayout;
import com.mahang.weather.view.WeatherViewPager;

public class WeatherMainFragment extends BaseFragment implements WeatherMainViewContract.View {

	private WeatherViewPager mViewPager;

	private WeatherPagerAdapter mAdapter;

	private WeatherMainViewContract.Presenter mPresenter;

    private NavigationHeaderLayout mHeaderView;

	private ViewPager.SimpleOnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
		@Override
		public void onPageSelected(int position) {
			mPresenter.onItemChange(position);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mAdapter = new WeatherPagerAdapter(getChildFragmentManager());
        mPresenter.loadWeatherInfo();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
		mViewPager = (WeatherViewPager) view.findViewById(R.id.view_pager);
		mViewPager.setAdapter(mAdapter);
		mViewPager.addOnPageChangeListener(mPageChangeListener);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        mHeaderView = (NavigationHeaderLayout) navigationView.getHeaderView(0);
        mPresenter.updateHeaderView();
		return view;
	}

	@Override
	public void notifyDataSetChanged() {
		mAdapter.notifyDataSetChanged();
	}


	@Override
	public void setCurrentItem(int index){
		mViewPager.setCurrentItem(index, true);
	}

	@Override
	public int getCurrentItem() {
		return mViewPager.getCurrentItem();
	}

	@Override
	public void setTitle(CharSequence title) {
		getActivity().setTitle(title);
	}

    @Override
    public void updateHeaderView(WeatherInfo info) {
        mHeaderView.setWeatherInfo(info);
    }

    @Override
    public void startQueryPage() {
        Intent i = new Intent(getContext(), CityListActivity.class);
        i.putExtra("REQUEST_CODE", Constants.REQUEST_CODE_QUERY_CITY);
        startActivityForResult(i, Constants.REQUEST_CODE_QUERY_CITY);
    }


    @Override
	public void setPresenter(WeatherMainViewContract.Presenter presenter) {
		mPresenter = presenter;
	}

	@Override
	public void setData(List<WeatherInfo> weatherInfos) {
		mAdapter.setData(weatherInfos);
	}
}
