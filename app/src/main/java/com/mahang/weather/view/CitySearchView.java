package com.mahang.weather.view;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import com.mahang.weather.R;
import com.mahang.weather.common.Constants;
import com.mahang.weather.ui.adapter.CitySuggestionsAdapter;


public class CitySearchView extends SearchView {

    private Cursor mCursor;

    private CitySuggestionsAdapter mAdapter;

    private CallBack mCallBack;
    
    public interface CallBack{
        boolean onQueryTextSubmit(String text);
        boolean onMenuItemAction(boolean isExpand);
    }
    
    public CitySearchView(Context context) {
        this(context,null);
    }

    public CitySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        ((AutoCompleteTextView)findViewById(R.id.search_src_text)).setThreshold(1);
        mAdapter = new CitySuggestionsAdapter(getContext());
        setSuggestionsAdapter(mAdapter);
        setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                if (mCallBack != null){
                    return mCallBack.onQueryTextSubmit(text);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                if (mCursor != null) {
                    mCursor.close();
                }
                mCursor = getContext().getContentResolver().query(Constants.DB_CITY_CONTENT_URL, new String[] {
                        "_id", "NAME", "DISTRICT" }, "NAME like ?", new String[] { "%"
                        + text + "%" }, null);
                mAdapter.changeCursor(mCursor);
                return false;
            }
        });

        setOnSuggestionListener(new OnSuggestionListener() {

            @Override
            public boolean onSuggestionSelect(int text) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int index) {
                mCursor.moveToPosition(index);
                mCallBack.onQueryTextSubmit(mCursor.getString(mCursor.getColumnIndexOrThrow("NAME")));
                return true;
            }
        });
    }

    public void attach(MenuItem item){
        if (item != null){
            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {

                @Override
                public boolean onMenuItemActionCollapse(MenuItem arg0) {

                    if (mCallBack != null){
                        return mCallBack.onMenuItemAction(false);
                    }
                    return false;

                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem arg0) {
                    if (mCallBack != null){
                        return mCallBack.onMenuItemAction(true);
                    }
                    return false;
                }
            });
        }
    }

    public void setCallBack(CallBack callBack){
        mCallBack = callBack;
    }
}
