package com.mahang.weather.common;

import android.os.Message;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventHandler {

	private Bus mEventBus;
	
	private static EventHandler mInstance;
	
	private EventHandler() {
		mEventBus = new Bus(ThreadEnforcer.ANY);
	}
	

	public synchronized static EventHandler getInstance() {
		if (mInstance == null){
			mInstance = new EventHandler();
		}
		return mInstance;
	}


	public void postEmptyMessage(int what){
		Message msg = Message.obtain();
		msg.what = what;
		postMessage(msg);
	}

	public void postMessage(Message msg){
		mEventBus.post(msg);
	}

	public void postMessage(int what,int arg1,int arg2,Object obj){
		Message msg = Message.obtain();
		msg.what = what;
		msg.arg1 = arg1;
		msg.arg2 = arg2;
		msg.obj = obj;
		postMessage(msg);
	}
	
	public void register(Object object) {
		mEventBus.register(object);
	}

	public void unregister(Object object) {
		mEventBus.unregister(object);
	}

}
