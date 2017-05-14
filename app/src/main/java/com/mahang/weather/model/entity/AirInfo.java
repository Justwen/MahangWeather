package com.mahang.weather.model.entity;

import com.litesuits.orm.db.annotation.Table;

import android.os.Parcel;
import android.os.Parcelable;

@Table("AirInfo")
public class AirInfo extends BaseInfo implements Parcelable {
	

	private String quality;

	private String aqi;
	
	private String pm25;
	
	private String pm10;
	
	private String so2;
	
	private String no2;
	
	private String co;
	
	private String o3;



	public AirInfo() {
		// TODO Auto-generated constructor stub
	}

	public AirInfo(String aqi, String quality) {
		this.setAqi(aqi);
		this.setQuality(quality);
	}

	protected AirInfo(Parcel in) {
		quality = in.readString();
		aqi = in.readString();
		pm25 = in.readString();
		pm10 = in.readString();
		so2 = in.readString();
		no2 = in.readString();
		co = in.readString();
		o3 = in.readString();
	}

	public static final Creator<AirInfo> CREATOR = new Creator<AirInfo>() {
		@Override
		public AirInfo createFromParcel(Parcel in) {
			return new AirInfo(in);
		}

		@Override
		public AirInfo[] newArray(int size) {
			return new AirInfo[size];
		}
	};

	public void setBasiceInfo(String aqi, String quality) {
		this.setAqi(aqi);
		this.setQuality(quality);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(quality);
		dest.writeString(aqi);
		dest.writeString(pm25);
		dest.writeString(pm10);
		dest.writeString(so2);
		dest.writeString(no2);
		dest.writeString(co);
		dest.writeString(o3);
	}


	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getSo2() {
		return so2;
	}

	public void setSo2(String so2) {
		this.so2 = so2;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getO3() {
		return o3;
	}

	public void setO3(String o3) {
		this.o3 = o3;
	}

	
}
