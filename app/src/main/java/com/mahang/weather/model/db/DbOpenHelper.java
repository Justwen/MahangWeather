package com.mahang.weather.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {


	private final String CREATE_WEATHER_TABLE = "CREATE TABLE WEATHER_INFO(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "CITY TEXT," // ��������
			+ "DISTRICT TEXT,"
			+ "ISLOCATE INTEGER NOT NULL DEFAULT 0," // �Ƿ��Ƕ�λ����
			+ "REALTEMP TEXT," // ��ǰʵʱ����
			+ "TEMP TEXT,"
			+ "WEATHER TEXT,"
			+ "INDEXFA INTEGER,"
			+ "INDEXFB INTEGER,"
			+ "TIME TEXT,"
			+ "TEMP0 TEXT," 
			+ "TEMP1 TEXT," 
			+ "TEMP2 TEXT,"
			+ "TEMP3 TEXT,"
			+ "TEMP4 TEXT,"
			+ "TEMP5 TEXT,"
			+ "WEATHER0 TEXT,"
			+ "WEATHER1 TEXT,"
			+ "WEATHER2 TEXT,"
			+ "WEATHER3 TEXT,"
			+ "WEATHER4 TEXT,"
			+ "WEATHER5 TEXT,"
			+ "INDEX0FA INTEGER,"
			+ "INDEX0FB INTEGER,"
			+ "INDEX1FA INTEGER,"
			+ "INDEX1FB INTEGER,"
			+ "INDEX2FA INTEGER,"
			+ "INDEX2FB INTEGER,"
			+ "INDEX3FA INTEGER,"
			+ "INDEX3FB INTEGER,"
			+ "INDEX4FA INTEGER,"
			+ "INDEX4FB INTEGER,"
			+ "INDEX5FA INTEGER,"
			+ "INDEX5FB INTEGER,"
			+ "INDEX0 TEXT,"
			+ "INDEX1 TEXT,"
			+ "INDEX2 TEXT,"
			+ "INDEX3 TEXT,"
			+ "INDEX4 TEXT,"
			+ "INDEX5 TEXT,"
			+ "INDEX6 TEXT,"
			+ "INDEX7 TEXT,"
			+ "PM25 TEXT,"
			+ "QUALITY TEXT,"
			+ "AQI TEXT,"
			+ "PM10 TEXT,"
			+ "C0 TEXT,"
			+ "NO2 TEXT,"
			+ "O3 TEXT,"
			+ "SO2 TEXT"
			+ ")";
	
	


	public DbOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	//	db.execSQL(CREATE_WEATHER_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	

}
