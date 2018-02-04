package com.mahang.weather.rxjava;

/**
 * Created by Justwen on 2017/11/25.
 */

public class RxEvent {

    public int what;

    public int arg;

    public Object obj;

    public RxEvent(int what) {
        this.what = what;
    }

    public RxEvent(int what, int arg) {
        this.what = what;
        this.arg = arg;
    }

    public RxEvent(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public RxEvent(int what, int arg, Object obj) {
        this.what = what;
        this.arg = arg;
        this.obj = obj;
    }
}
