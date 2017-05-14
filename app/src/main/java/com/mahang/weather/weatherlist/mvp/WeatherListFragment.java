package com.mahang.weather.weatherlist.mvp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahang.weather.R;
import com.mahang.weather.base.BaseFragment;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.view.SwipeDismissListView;

public class WeatherListFragment extends BaseFragment implements View.OnClickListener,WeatherListContract.View {

    private SwipeDismissListView mListView;

    private WeatherListAdapter mAdapter;

    private Intent mResultData;

    private WeatherListContract.Presenter mPresenter;

    private SwipeDismissListView.onTouchCallBack mTouchCallBack = new SwipeDismissListView.onTouchCallBack() {
        @Override
        public boolean onMove(int start, int end) {
            mPresenter.swap(start,end);
            return true;
        }

        @Override
        public void onSwipe(int position) {
            mPresenter.remove(position);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mAdapter = new WeatherListAdapter(getContext());
        mPresenter.loadWeatherInfo();
        super.onCreate(savedInstanceState);
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
    public void onClick(View v) {
        int index = (int) v.getTag();
        mPresenter.onClick(index);
    }


    @Override
    public void setPresenter(WeatherListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(List<WeatherInfo> weatherInfos) {
        mAdapter.setData(weatherInfos);
    }

    @Override
    public void setActivityResult(String name, boolean value) {
        if (mResultData == null) {
            mResultData = new Intent();
        }
        mResultData.putExtra(name,value);
        getActivity().setResult(Activity.RESULT_OK,mResultData);
    }

    @Override
    public void setActivityResult(String name, int value) {
        if (mResultData == null) {
            mResultData = new Intent();
        }
        mResultData.putExtra(name,value);
        getActivity().setResult(Activity.RESULT_OK,mResultData);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
