package com.mahang.weather.model.api.juhe;

import java.util.List;

public class WeatherBean  {

	public String resultCode;

	public String reason;

	public Result result;

	public String error_code;

	

	public static class Result {

		public Real sk;

		public Today today;
		
		public List<Forcast> future;

	}

	public static class Real {

		public String temp;

		public String wind_direction;

		public String wind_strength;

		public String humidity;

		public String time;
	}

	public static class Today {

		public String city;

		public String week;

		public String date_y;

		public String temperature;

		public String weather;

		public WeatherId weather_id;

		public String wind;

		public String dressing_index;

		public String dressing_advice;

		public String uv_index;

		public String comfort_index;

		public String wash_index;

		public String travel_index;

		public String exercise_index;

		public String drying_index;
	}

	public static class WeatherId {

		public String fa;

		public String fb;

	}

	public static class Forcast {

		public String temperature;

		public String weather;

		public WeatherId weather_id;

		public String wind;

		public String week;

		public String date;

	}


}
