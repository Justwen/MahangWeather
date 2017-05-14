package com.mahang.weather.model.api.juhe;

import java.util.List;


public class AirBean {
	
	public String resultcode;
	
	public String reason;
	
	public List<Result> result;
	
	public String error_code;
	
	public static class Result{
		
		public String city;
		
		public String PM25;
		
		public String AQI;
		
		public String quality;
		
		public String PM10;
		
		public String CO;
		
		public String NO2;
		
		public String O3;
		
		public String SO2;
		
		public String time;
	}

}
