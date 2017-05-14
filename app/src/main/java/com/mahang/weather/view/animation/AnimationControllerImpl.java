package com.mahang.weather.view.animation;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.Window;

import com.mahang.weather.common.ResManagerImpl;


public class AnimationControllerImpl implements AnimationController {

    private static AnimationController sInstance;

    private Context mContext;

    private ViewGroup mRootView;

    private Toolbar mToolbar;

    private Window mWindow;

    private ResManagerImpl mResManager;

    private AnimationView mAnimationView;


    private AnimationControllerImpl(Context context) {
        mContext = context.getApplicationContext();
        mResManager = ResManagerImpl.getInstance();
    }


    public static synchronized AnimationController newInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AnimationControllerImpl(context);
        }

        return sInstance;
    }


    public static synchronized AnimationController getInstance() {
        return sInstance;
    }


    @Override
    public void setWeatherCode(int code) {
        mWindow.setStatusBarColor(mResManager.getToolbarBgColor(code));
        mToolbar.setBackgroundColor(mResManager.getToolbarBgColor(code));
        updateAnimationView(code);
    }

    @Override
    public void initialize(ViewGroup rootView, Toolbar toolbar, Window window) {
        mRootView = rootView;
        mToolbar = toolbar;
        mWindow = window;
        mAnimationView = null;
    }


    private void updateAnimationView(int code) {

        AnimationView view = getWeatherAnimationView(code);

        if (mAnimationView != null && view.getClass().getName().equals(mAnimationView.getClass().getName())) {
            mAnimationView.setBackground(mResManager.getWeatherBg(code));
            return;
        }
        if (mAnimationView != null) {
            mRootView.removeView(mAnimationView);
        }
        mAnimationView = view;
        ViewGroup.LayoutParams lp = mAnimationView.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mAnimationView.setLayoutParams(lp);
        }
        mAnimationView.setBackground(mResManager.getWeatherBg(code));
        mRootView.addView(mAnimationView, 1);
    }

    private AnimationView getWeatherAnimationView(int code) {
        switch (code) {
            case 7:
            case 8:
                return new AnimationRainView(mContext);
            default:
                return new AnimationView(mContext);
        }

    }
}
