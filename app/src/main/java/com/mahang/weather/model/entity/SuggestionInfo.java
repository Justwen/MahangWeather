package com.mahang.weather.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SuggestionInfo extends BaseInfo implements Parcelable {
	
	private String title;
	
	private String suggestion;
	
	private String index;


	protected SuggestionInfo(Parcel in) {
		title = in.readString();
		suggestion = in.readString();
		index = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(suggestion);
		dest.writeString(index);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<SuggestionInfo> CREATOR = new Creator<SuggestionInfo>() {
		@Override
		public SuggestionInfo createFromParcel(Parcel in) {
			return new SuggestionInfo(in);
		}

		@Override
		public SuggestionInfo[] newArray(int size) {
			return new SuggestionInfo[size];
		}
	};

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}


}
