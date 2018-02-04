package com.mahang.weather.common;

import android.content.Context;

/**
 * Created by Justwen on 2018/2/4.
 */

public class ApplicationContextHolder {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }
}
