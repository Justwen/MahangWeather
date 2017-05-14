package com.mahang.weather.main.mvp.detail;

import java.util.Iterator;
import java.util.Set;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mahang.weather.R;
import com.mahang.weather.base.BaseFragment;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.model.entity.AirInfo;
import com.mahang.weather.model.entity.RealTimeInfo;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.view.SwipeRefreshView;
import com.mahang.weather.view.card.WeatherCardAirView;
import com.mahang.weather.view.card.WeatherCardDailyView;
import com.mahang.weather.view.card.WeatherCardRealView;
import com.mahang.utils.view.ViewHolderUtils;
import com.squareup.otto.Subscribe;

public class WeatherInfoFragment extends BaseFragment implements
		SwipeRefreshLayout.OnRefreshListener,WeatherInfoContract.View {

	private WeatherInfo mWeatherInfo;

	private SwipeRefreshView mRefreshView = null;
	
	private SharedPreferences mPrefs;

	private WeatherInfoContract.Presenter mPresenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setData(WeatherInfo info)  {
		mWeatherInfo = info;
	}

    @Override
    public void setRefreshing(boolean refresh) {
        mRefreshView.setRefreshing(refresh);
    }


    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View convertView = inflater.inflate(R.layout.weather_view_pager, container, false);
		mRefreshView = (SwipeRefreshView) convertView.findViewById(R.id.pulltorefresh);
		mRefreshView.setOnRefreshListener(this);
		if (mWeatherInfo != null) {
			loadView(convertView);
		}
		return convertView;
	}

	private void loadView(View convertView) {
		if (mPrefs == null){
			return;
		}
		Set<String> cardSet = mPrefs.getStringSet("card_manager", null);
		if  (cardSet != null){
			
			Iterator<String> iterator = cardSet.iterator();
			while (iterator.hasNext()){
				String key = iterator.next();
				switch (key) {
				case "0":
					loadRealView(convertView);
					break;
				case "1":
					loadAirView(convertView);
					break;
				case "2":
					loadHourlyView(convertView);
					break;
				case "3":
					loadDailyView(convertView);
					break;
				default:
					break;
				}
			}
			
		} else{
			
			loadDailyView(convertView);
			loadAirView(convertView);
			//loadHourlyView(convertView);
			loadRealView(convertView);
		}
		setLastUpdateTime(convertView);
	}
	
	private void loadHourlyView(View convertView){
//		ArrayList<HourlyInfo> infos = mWeatherInfo.getHourlyInfo();
//		WeatherCardHourlyView hourlyView = ViewHolderUtils.get(convertView, R.id.hourly_card);
//		if (infos == null || infos.size() == 0){
//			hourlyView.setVisibility(View.GONE);
//		} else{
//			hourlyView.setData(infos,mWeatherInfo.getDailyInfo().get(0));
//			hourlyView.setVisibility(View.VISIBLE);
//		}
	}

	private void loadAirView(View convertView) {
		AirInfo airInfo = mWeatherInfo.getAirInfo();
		WeatherCardAirView airView = ViewHolderUtils.get(convertView, R.id.air_card);
		if (airInfo == null) {
			airView.setVisibility(View.GONE);
		}else{
			airView.setAirInfo(airInfo);
			airView.setVisibility(View.VISIBLE);
		}
		airView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void loadRealView(View convertView) {
		
		WeatherCardRealView realView = ViewHolderUtils.get(convertView, R.id.real_card);

		RealTimeInfo realTimeInfo = mWeatherInfo.getRealTimeInfo();
		if (realTimeInfo == null) {
			realView.setVisibility(View.GONE);
		}else{
			realView.setVisibility(View.VISIBLE);
			realView.setRealInfo(realTimeInfo, true);
			realView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

	private void setLastUpdateTime(View convertView) {
		if (mWeatherInfo.getRealTimeInfo() == null){
			return;
		}
		String lastUpdate = mWeatherInfo.getRealTimeInfo().getUpdateTime();
//		LoadingLayoutProxy proxy = (LoadingLayoutProxy) mRefreshView.getLoadingLayoutProxy(true, false);
////		proxy.setLastUpedatedLabel(getString(R.string.detail_last_update) + " "
////				+ lastUpdate);
	}

	private void loadDailyView(View convertView) {
		WeatherCardDailyView dailyView = ViewHolderUtils.get(convertView, R.id.daily_card);
		if (mWeatherInfo.getDailyInfo() == null) {
			dailyView.setVisibility(View.GONE);
		} else{
			dailyView.setVisibility(View.VISIBLE);
			dailyView.setDailyInfo(mWeatherInfo.getDailyInfo());
		}
	}

	@Override
	public void notifyDataSetChanged() {
		loadView(getView());
	}


	@Override
	public void onRefresh() {
		mPresenter.onRefresh();
	}

	@Override
	public void setPresenter(WeatherInfoContract.Presenter presenter) {
		mPresenter = presenter;
	}

	public WeatherInfoContract.Presenter getPresenter(){
		return mPresenter;
	}

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
