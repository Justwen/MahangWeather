package com.mahang.weather.ui.activity;

import com.mahang.weather.base.BaseActivity;
import com.mahang.weather.ui.fragment.SettingFragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(false);
		}
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment())
		.addToBackStack(null).commit();

	}

	@Override
	public void handleMessage(Message msg) {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
		FragmentManager fm = getFragmentManager();
		
		if (fm.getBackStackEntryCount() > 1){
			fm.popBackStack();
		}else{
			finish();
		}
		
	}

}
