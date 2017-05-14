package com.mahang.weather.common;

import android.net.Uri;

public interface Constants {

	String DB_WEATHER = "weather";

	String DB_CITY = "city";

	String DB_AUTHORITY = "com.mahang.weather";

	Uri DB_WEATHER_CONTENT_URL = Uri.parse("content://"
			+ DB_AUTHORITY + "/" + DB_WEATHER);

	Uri DB_CITY_CONTENT_URL = Uri.parse("content://"
			+ DB_AUTHORITY + "/" + DB_CITY);

	String DB_WEATHER_NAME = "weather.db";

	String DB_CITY_LIST_DB = "citylist.db";
	
	public static final String DEGREE_SIGN = "℃";


    int REQUEST_CODE_CITY_LIST = 1;

    int REQUEST_CODE_SETTINGS = 2;

    int REQUEST_CODE_QUERY_CITY = 3;




	int MSG_QUERY_WEATHER_SUCCESS = 2;

	int MSG_QUERY_WEATHER_FAILURE = 3;


    int MSG_UPDATE_WEATHER_SUCCESS = 5;

    int MSG_UPDATE_WEATHER_FAILURE = 6;

    int MSG_REQUEST_LOCATION_SUCCESS = 8;

    int MSG_REQUEST_LOCATION_FAILURE = 9;





	int MSG_QEQUEST_LOCATION = 7;


	int MSG_GET_WEATHER = 1;

	int MSG_FLAG_SUCCESS = 10;

	int MSG_FLAG_FAILURE = 11;

}
