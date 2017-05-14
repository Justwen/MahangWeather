package com.mahang.weather.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class MvpBaseActivity extends BaseActivity {

    private List<MvpBasePresenter>  mPresenterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addPresenter(MvpBasePresenter presenter){
        if (mPresenterList == null){
            mPresenterList = new ArrayList<>();
        }
        mPresenterList.add(presenter);
    }

    @Override
    protected void onDestroy() {
        if (mPresenterList != null){
            for (MvpBasePresenter presenter : mPresenterList){
                presenter.onDestroy();
            }
        }
        super.onDestroy();
    }
}
