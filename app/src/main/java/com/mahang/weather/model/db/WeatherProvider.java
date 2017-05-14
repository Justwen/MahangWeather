package com.mahang.weather.model.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.mahang.weather.common.Constants;

public class WeatherProvider extends ContentProvider  {
	
	private DbOpenHelper mWeatherHelper;
	
	private DbOpenHelper mCityHelper;
	
	private static final int WEATHER = 1;
	
	private static final int CITY = 2;
	
	private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static{
		mUriMatcher.addURI(Constants.DB_AUTHORITY, Constants.DB_WEATHER, WEATHER);
		mUriMatcher.addURI(Constants.DB_AUTHORITY, Constants.DB_CITY, CITY);
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {

		mWeatherHelper = new DbOpenHelper(getContext(), Constants.DB_WEATHER_NAME, null, 1);
		copyDatabaseFromAsset(Constants.DB_CITY_LIST_DB);
		mCityHelper = new DbOpenHelper(getContext(), Constants.DB_CITY_LIST_DB, null, 1);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String orderBy) {
		
		SQLiteDatabase db = null;
		switch (mUriMatcher.match(uri)) {
		case WEATHER:
			db = mWeatherHelper.getReadableDatabase();
			return db.query("WEATHER_INFO", projection, selection, selectionArgs, null, null, orderBy);
		case CITY:
			db = mCityHelper.getReadableDatabase();
			return db.query("CITY_LIST", projection, selection, selectionArgs, null, null, orderBy);
		default:
			throw new IllegalArgumentException();
		}
		
		
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		
		// TODO Auto-generated method stub
		return 0;
	}

	private void copyDatabaseFromAsset(String db){
		
		String path = "data/data/"+getContext().getPackageName()+"/databases";
		String datebaseFileName = path+"/"+db;
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
		if (!new File(datebaseFileName).exists()){
			try {
				InputStream in = getContext().getAssets().open(db);
				FileOutputStream out = new FileOutputStream(datebaseFileName);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = in.read(buffer)) > 0)
					out.write(buffer,0,count);
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
}
