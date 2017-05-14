package com.mahang.weather.model.entity;


import com.litesuits.orm.db.annotation.Table;

import android.os.Parcel;
import android.os.Parcelable;


@Table("ForcastInfo")
public class DailyInfo extends BaseInfo implements Parcelable{
	
	
	private String wind;
	
	private int codeDay;
	
	private int codeNight;
	
	private String textDay;
	
	private String textNight;
	
    private String max;
    
    private String min;
    
    private String date;
    
    private String sunrise;
    
    private String sunset;
    
    public static final Parcelable.Creator<DailyInfo> CREATOR = new Creator<DailyInfo>() {
		
		@Override
		public DailyInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DailyInfo[size];
		}
		
		@Override
		public DailyInfo createFromParcel(Parcel source) {
			DailyInfo info = new DailyInfo();
			info.cityName = source.readString();
			info.textDay = source.readString();
			info.max = source.readString();
			info.min = source.readString();
			info.setWind(source.readString());
			info.codeDay = source.readInt();
			info.codeNight = source.readInt();
			return info;
		}
	};
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cityName);
		dest.writeString(textDay);
		dest.writeString(max);
		dest.writeString(min);
		dest.writeString(getWind());
		dest.writeInt(codeDay);
		dest.writeInt(codeNight);
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}


	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public int getCodeDay() {
		return codeDay;
	}

	public void setCodeDay(int codeDay) {
		this.codeDay = codeDay;
	}

	public int getCodeNight() {
		return codeNight;
	}

	public void setCodeNight(int codeNight) {
		this.codeNight = codeNight;
	}

	public String getTextDay() {
		return textDay;
	}

	public void setTextDay(String textDay) {
		this.textDay = textDay;
	}

	public String getTextNight() {
		return textNight;
	}

	public void setTextNight(String textNight) {
		this.textNight = textNight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}


}
