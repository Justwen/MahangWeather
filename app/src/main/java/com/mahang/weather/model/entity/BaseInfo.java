package com.mahang.weather.model.entity;

import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;
import com.mahang.utils.StringUtils;

public abstract class BaseInfo implements Parcelable {
	
	public static final String COL_ID = "_id";
	
	@PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column(COL_ID)
    protected long id;
	
	protected String cityName;
	
	protected String updateTime;
	
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return StringUtils.toString(this);
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
