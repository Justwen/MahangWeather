package com.mahang.weather.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mahang.weather.common.EventHandler;
import com.squareup.otto.Subscribe;


public abstract class BaseActivity extends AppCompatActivity {

    private EventHandler mEventHandler = EventHandler.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mEventHandler.register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mEventHandler.unregister(this);
        super.onDestroy();
    }

	public void handleMessage(Message msg){

	}


    protected void sendEmptyMessage(int what){
        mEventHandler.postEmptyMessage(what);
    }

    protected void sendMessage(int what,int arg1,int arg2,Object obj){
        mEventHandler.postMessage(what,arg1,arg2,obj);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
