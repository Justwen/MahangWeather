package com.mahang.weather.weatherlist.mvp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mahang.weather.R;
import com.mahang.weather.base.MvpBaseActivity;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.ui.activity.CityListActivity;


public class WeatherListActivity extends MvpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_content);
        initViews();
    }

    private void initViews(){
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCityList();
            }
        });
        WeatherListFragment fragment = new WeatherListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        WeatherListPresenter presenter = new WeatherListPresenter(WeatherModelImpl.getInstance(this),fragment);
        addPresenter(presenter);
    }

    private void startCityList(){
        startActivity(new Intent(this,CityListActivity.class));
    }

}
