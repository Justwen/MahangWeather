package com.mahang.weather.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Table;

@Table("RealTimeInfo")
public class RealTimeInfo extends BaseInfo implements Parcelable {
	
	private String temp;

	private String wind;
	
	private String humidity;
	
	private String pres;
	
	private int code;
	
	private String text;
	
	private String max;
	
	private String min;
	
	public static final Parcelable.Creator<RealTimeInfo> CREATOR = new Creator<RealTimeInfo>() {

		@Override
		public RealTimeInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new RealTimeInfo[size];
		}

		@Override
		public RealTimeInfo createFromParcel(Parcel source) {
			RealTimeInfo info = new RealTimeInfo();
			info.id = source.readLong();
			info.cityName = source.readString();
			info.setTemp(source.readString());
			info.setWind(source.readString());
			info.code = source.readInt();
			info.setText(source.readString());
			info.setHumidity(source.readString());
			info.setMax(source.readString());
			info.setMin(source.readString());
			info.updateTime = source.readString();
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
		dest.writeLong(id);
		dest.writeString(cityName);
		dest.writeString(getTemp());
		dest.writeString(getWind());
		dest.writeInt(code);
		dest.writeString(getText());
		dest.writeString(getHumidity());
		dest.writeString(getMax());
		dest.writeString(getMin());
		dest.writeString(updateTime);

	}

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
