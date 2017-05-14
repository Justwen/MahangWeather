package com.mahang.weather.ui;


import android.os.Message;

import com.mahang.weather.common.EventHandler;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T>{

    protected Reference<T> mViewRef;

    protected T getView(){
        return mViewRef.get();
    }

    public boolean isAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    public void attach(T view){
        mViewRef = new WeakReference<T>(view);
        EventHandler.getInstance().register(this);
    }

    public void detach(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
            EventHandler.getInstance().unregister(this);
        }

    }

    public void onDestroy(){

    }

    abstract public void handleMessage(Message msg);

}
