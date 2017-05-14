package com.mahang.weather.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.mahang.weather.R;

public class SettingFragment extends PreferenceFragment implements
		OnPreferenceChangeListener, OnPreferenceClickListener {

	private SharedPreferences mPrefs;
	
	private MultiSelectListPreference mCardPref;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main_preference);
		init();
	}

	private void init() {

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mCardPref = (MultiSelectListPreference) findPreference("card_manager");
		mCardPref.setOnPreferenceChangeListener(this);
	}

	@Override
	public void onResume() {


		super.onResume();
	}

	@Override
	public boolean onPreferenceChange(Preference pref, Object value) {

		if (pref.getKey().equals(mCardPref.getKey())){
			getActivity().setResult(Activity.RESULT_OK);
		}
		return true;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		return false;
	}

}
