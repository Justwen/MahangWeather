package com.mahang.weather.model.entity;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.Mapping.Relation;
import com.litesuits.orm.db.annotation.Table;

@Table("WeatherInfo")
public class WeatherInfo extends BaseInfo implements Parcelable {

	@Mapping(Relation.OneToOne)
	private CityInfo mCityInfo;

	@Mapping(Relation.OneToOne)
	private AirInfo mAirInfo;

	@Mapping(Relation.OneToOne)
	private RealTimeInfo mRealTimeInfo;

	@Mapping(Relation.OneToMany)
	private ArrayList<DailyInfo> mDailyInfo;
	
	@Mapping(Relation.OneToMany)
	private ArrayList<HourlyInfo> mHourlyInfo;

	private ArrayList<SuggestionInfo> mSuggestionInfos;

	public static final Parcelable.Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {

		@Override
		public WeatherInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WeatherInfo[size];
		}

		@SuppressWarnings("unchecked")
		@Override
		public WeatherInfo createFromParcel(Parcel source) {
			WeatherInfo weather = new WeatherInfo();
			weather.id = source.readLong();
			weather.mCityInfo = source.readParcelable(getClass().getClassLoader());
			weather.mAirInfo = source.readParcelable(getClass().getClassLoader());
			weather.mRealTimeInfo = source.readParcelable(getClass().getClassLoader());
			weather.mDailyInfo = source.readArrayList(getClass().getClassLoader());
		//	weather.mHourlyInfo = source.readArrayList(getClass().getClassLoader());
			weather.updateTime = source.readString();
			return weather;
		}
	};

	public CityInfo getCityInfo() {
		return mCityInfo;
	}

	public void setCityInfo(CityInfo cityInfo){
		mCityInfo = cityInfo;
	}
	
	public AirInfo getAirInfo() {
		return mAirInfo;
	}

	public void setAirInfo(AirInfo airInfo) {
		this.mAirInfo = airInfo;
	}

	public RealTimeInfo getRealTimeInfo() {
		return mRealTimeInfo;
	}

	public void setRealTimeInfo(RealTimeInfo realTimeInfo) {
		mRealTimeInfo = realTimeInfo;
	}

	public ArrayList<DailyInfo> getDailyInfo() {
		return mDailyInfo;
	}

	public void setDailyInfo(ArrayList<DailyInfo> dailyInfo) {
		this.mDailyInfo = dailyInfo;
	}
	
	public ArrayList<HourlyInfo> getHourlyInfo() {
		return mHourlyInfo;
	}

	public void setHourlyInfo(ArrayList<HourlyInfo> hourlyInfo) {
		this.mHourlyInfo = hourlyInfo;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeParcelable(mCityInfo, flags);
		dest.writeParcelable(mAirInfo, flags);
		dest.writeParcelable(mRealTimeInfo, flags);
		dest.writeList(mDailyInfo);
	//	dest.writeList(mHourlyInfo);
		dest.writeString(updateTime);

	}

	@Override
	public boolean equals(Object object) {
		return getCityName().equals(((WeatherInfo) object).getCityName());
	}

	public ArrayList<SuggestionInfo> getSuggestionInfos() {
		return mSuggestionInfos;
	}

	public void setSuggestionInfos(ArrayList<SuggestionInfo> suggestionInfos) {
		this.mSuggestionInfos = suggestionInfos;
	}
	


}
