package com.mahang.weather.ui.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.OnSuggestionListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.mahang.utils.LogUtils;
import com.mahang.weather.R;
import com.mahang.weather.base.BaseActivity;
import com.mahang.weather.common.Constants;
import com.mahang.weather.model.impl.WeatherModelImpl;
import com.mahang.weather.ui.adapter.CitySuggestionsAdapter;
import com.mahang.weather.ui.adapter.HotCityAdapter;
import com.mahang.weather.view.CitySearchView;

import butterknife.ButterKnife;

public class QueryActivity extends BaseActivity {

    private Cursor mCursor;

    private LinearLayout mLocationView;

    private LinearLayout mCityView;

    private static final int DURATION_TIME = 300;

    private ProgressDialog mDialog;

    private class MyAnimation extends Animation {

        private int topMargin;

        private int offsetY;

        public MyAnimation() {
            setDuration(DURATION_TIME);
            setInterpolator(new LinearInterpolator());
            topMargin = ((LayoutParams) mCityView.getLayoutParams()).topMargin;
            offsetY = ((LayoutParams) mLocationView.getLayoutParams()).topMargin
                    + mLocationView.getHeight();
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mLocationView.setAlpha(interpolatedTime);
            LayoutParams lp = (LayoutParams) mCityView.getLayoutParams();
            lp.setMargins(lp.leftMargin, (int) (interpolatedTime * offsetY)
                    + topMargin, lp.rightMargin, lp.bottomMargin);
            mCityView.setLayoutParams(lp);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        GridView gridView = (GridView) findViewById(R.id.hot_city);
        gridView.setAdapter(new HotCityAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                query((String) view.getTag());
            }
        });

        mLocationView = (LinearLayout) findViewById(R.id.location_view);
        mCityView = (LinearLayout) findViewById(R.id.city_view);
    }

    private void query(String city) {
        showDialog(getString(R.string.query_loading));
        WeatherModelImpl.getInstance(this).queryWeather(city);
    }

    private void showDialog(String msg) {
        mDialog = new ProgressDialog(this, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        mDialog.setMessage(msg);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.location:
                findViewById(android.R.id.content).startAnimation(new MyAnimation());
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initSearchView(SearchView searchView) {
        searchView.setQueryHint(getString(R.string.query_city_hint));
        final CitySuggestionsAdapter adapter = new CitySuggestionsAdapter(this);
        searchView.setSuggestionsAdapter(adapter);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                query(text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (mCursor != null) {
                    mCursor.close();
                }
                mCursor = getContentResolver().query(Constants.DB_CITY_CONTENT_URL, new String[]{
                        "_id", "NAME", "DISTRICT"}, "NAME like ?", new String[]{"%"
                        + text + "%"}, null);
                adapter.changeCursor(mCursor);

                return false;
            }
        });

        ((AutoCompleteTextView) searchView.findViewById(R.id.search_src_text)).setThreshold(1);

        searchView.setOnSuggestionListener(new OnSuggestionListener() {

            @Override
            public boolean onSuggestionSelect(int text) {

                return false;
            }

            @Override
            public boolean onSuggestionClick(int index) {
                mCursor.moveToPosition(index);
                query(mCursor.getString(mCursor.getColumnIndexOrThrow("NAME")));
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.query_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        item.expandActionView();
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionCollapse(MenuItem arg0) {
                QueryActivity.this.finish();
                return false;

            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        });

        initSearchView((SearchView) item.getActionView());

        CitySearchView searchView = (CitySearchView) item.getActionView();
        searchView.setCallBack(new CitySearchView.CallBack() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }


            @Override
            public boolean onMenuItemAction(boolean isExpand) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!dismiss()) {
            finish();
        }

    }

    private boolean dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
        super.onDestroy();
    }


//	@Subscribe
//	@Override
//	public void handleEvent(Event event) {
//		switch (event.what) {
//		case EventHandler.QUERY_FAILURE:
//			HandleFailureEvent(event);
//			break;
//		case EventHandler.DATA_ADD:
//			dismiss();
//			Intent i = new Intent();
//			setResult(RESULT_OK, i);
//			finish();
//			break;
//		default:
//			break;
//		}
//	}


//	private void HandleFailureEvent(Event event) {
//		dismiss();
//		String info = event.arg1;
//		if (info == null) {
//			info = getString(R.string.query_failure);
//		}
//
//		ToastUtils.showShort(this, info);
//	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.d();
        return super.onKeyDown(keyCode, event);
    }


}
