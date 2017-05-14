package com.mahang.weather.base;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.mahang.weather.common.EventHandler;

public abstract class BaseFragment extends Fragment {


	protected EventHandler mEventHandler = EventHandler.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		mEventHandler.register(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		mEventHandler.unregister(this);
		super.onDestroy();
	}

	protected void sendEmptyMessage(int what){
		mEventHandler.postEmptyMessage(what);
	}

	protected void sendMessage(int what,int arg1,int arg2,Object obj){
		mEventHandler.postMessage(what,arg1,arg2,obj);
	}


	public void notifyDataSetChanged(){

	}

	public void handleMessage(Message msg){

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

}
