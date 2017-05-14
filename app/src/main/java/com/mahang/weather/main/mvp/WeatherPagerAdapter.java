package com.mahang.weather.main.mvp;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.mahang.weather.main.mvp.detail.WeatherInfoPresenter;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.main.mvp.detail.WeatherInfoFragment;
import com.mahang.weather.model.impl.WeatherModelImpl;

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {

	private List<WeatherInfo> mWeatherInfos;

	public WeatherPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		WeatherInfoFragment fragment = (WeatherInfoFragment) super.instantiateItem(container, position);
		if (mWeatherInfos != null && mWeatherInfos.size() > 0) {
			fragment.getPresenter().setData(mWeatherInfos.get(position));
		}
		return fragment;
	}
	
	@Override
	public Fragment getItem(int position) {
        WeatherInfoFragment fragment = new WeatherInfoFragment();
        new WeatherInfoPresenter(WeatherModelImpl.getInstance(),fragment);
		return fragment;
	}

	@Override
	public int getCount() {
		return mWeatherInfos == null ? 0 : mWeatherInfos.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == ((Fragment) obj).getView();
	}

	public void setData(List<WeatherInfo> infos){
		mWeatherInfos = infos;
		notifyDataSetChanged();
	}

}
