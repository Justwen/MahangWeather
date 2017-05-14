package com.mahang.weather.main.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.mahang.weather.R;
import com.mahang.weather.base.MvpBaseActivity;
import com.mahang.weather.common.Constants;
import com.mahang.weather.weatherlist.mvp.WeatherListActivity;
import com.mahang.weather.ui.activity.CityListActivity;
import com.mahang.weather.ui.activity.SettingActivity;
import com.mahang.weather.view.animation.AnimationControllerImpl;

public class WeatherMainActivity extends MvpBaseActivity implements NavigationView.OnNavigationItemSelectedListener{

	private DrawerLayout mDrawerLayout;

	private Toolbar mToolbar;

	private WeatherMainViewContract.Presenter mMainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}

	private void initToolbar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
	}

	private void initDrawerLayout() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar, R.string.app_name, R.string.city_list);
		mDrawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();
	}

	private void initialize() {
		setContentView(R.layout.activity_main_layout);
		initToolbar();
		initDrawerLayout();
		WeatherMainFragment fragment = new WeatherMainFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
		mMainPresenter = new WeatherMainViewPresenter(this,fragment);
		addPresenter(mMainPresenter);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
        AnimationControllerImpl.getInstance().initialize((ViewGroup) mDrawerLayout.getChildAt(0),mToolbar,getWindow());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main_activity, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.query:
			startQueryPage();
			break;
            case R.id.nav_city_list:
            startWeatherList();
                break;
		case R.id.nav_settings:
		case R.id.setting:
			startSettings();
			break;

		case R.id.share:
		//	ToastUtils.
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void startQueryPage() {
		Intent i = new Intent(this, CityListActivity.class);
		i.putExtra("REQUEST_CODE",Constants.REQUEST_CODE_QUERY_CITY);
		startActivityForResult(i, Constants.REQUEST_CODE_QUERY_CITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mMainPresenter.onActivityResult(requestCode,resultCode,data);
	}


	private void startSettings() {
		Intent i = new Intent(this, SettingActivity.class);
		startActivityForResult(i, Constants.REQUEST_CODE_SETTINGS);
	}


	private void startWeatherList(){
		Intent i = new Intent(this, WeatherListActivity.class);
		i.putExtra("REQUEST_CODE",Constants.REQUEST_CODE_CITY_LIST);
		startActivityForResult(i, Constants.REQUEST_CODE_CITY_LIST);
	}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        onOptionsItemSelected(item);
        return false;
    }
}
