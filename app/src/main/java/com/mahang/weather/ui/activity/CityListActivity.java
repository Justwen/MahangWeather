package com.mahang.weather.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mahang.weather.R;
import com.mahang.weather.base.BaseActivity;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.impl.LocationModelImpl;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.model.entity.WeatherInfo;
import com.mahang.weather.presenter.WeatherPresenter;
import com.mahang.weather.ui.WeatherViewInterface;
import com.mahang.weather.ui.fragment.CityListFragment;
import com.mahang.weather.util.PermissionUtils;
import com.mahang.weather.view.CitySearchView;
import com.squareup.otto.Subscribe;

import java.util.List;

public class CityListActivity extends BaseActivity implements WeatherViewInterface {

    private FloatingActionButton mFab;

    private ProgressDialog mDialog;

    private MenuItem mMenuItem;

    private CityListFragment mCityListFragment;

    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initViews();
        mWeatherPresenter = new WeatherPresenter(this);
        mWeatherPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        mWeatherPresenter.detach();
        super.onDestroy();
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchView();
            }
        });
        mCityListFragment = new CityListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,mCityListFragment).commit();
    }

    private void showSearchView(){
        mMenuItem.expandActionView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city_list, menu);
        mMenuItem = menu.findItem(R.id.search);
        CitySearchView searchView = (CitySearchView)mMenuItem.getActionView();
        searchView.attach(mMenuItem);
        searchView.setCallBack(new CitySearchView.CallBack() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                query(text);
                return true;
            }

            @Override
            public boolean onMenuItemAction(boolean isExpand) {
                mFab.setVisibility(isExpand ? View.GONE:View.VISIBLE);
//                if (isExpand){
//                    mCityListFragment.onMenuItemActionExpand(mMenuItem);
//                } else{
//                    mCityListFragment.onMenuItemActionCollapse(mMenuItem);
//                }
                return true;
            }
        });

        if (getIntent().getIntExtra("REQUEST_CODE",0) == Constants.REQUEST_CODE_QUERY_CITY){
            mMenuItem.expandActionView();
        }
        return super.onCreateOptionsMenu(menu);
    }


    private void query(String city) {
        showDialog(getString(R.string.city_list_query_weather));
        WeatherModelImpl.getInstance(this).queryWeather(city);
    }

    private void requestLocation(){
        showDialog(getString(R.string.city_list_request_location));
        LocationModelImpl.getInstance(this).requestLocation();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0 ; i < permissions.length; i++){
            if (permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)
                    && grantResults[i] == PackageManager.PERMISSION_GRANTED){
                requestLocation();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.loc:
                if (PermissionUtils.requestLocationPermission(this)){
                    requestLocation();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(String str) {
        if (mDialog == null){
            mDialog = new ProgressDialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCancelable(true);
        }
        mDialog.setMessage(str);
        mDialog.show();
    }

    private void dismissDialog(){
        if (mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    @Subscribe
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Constants.MSG_QUERY_WEATHER_SUCCESS:
                dismissDialog();
                Intent intent = new Intent();
                intent.putExtra("update",true);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case Constants.MSG_QUERY_WEATHER_FAILURE:
                dismissDialog();
                Toast.makeText(this,getString(msg.arg1),Toast.LENGTH_SHORT).show();
                break;
            case Constants.MSG_QEQUEST_LOCATION:
                dismissDialog();
                break;
            default:
                break;

        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateWeather() {

    }

    @Override
    public void showWeather(List<WeatherInfo> infos) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
