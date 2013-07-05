package com.ssict.jsonexample.weatherdata;


import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ssict.jsonexample.data.Request;


public class LocalData {
	@SerializedName("current_condition")
	public List<Condition> currentCondition;
	@SerializedName("nearest_area")
	public List<Area> area;
	@SerializedName("request")
	public List<Request> request;
	@SerializedName("weather")
	public List<Weather> weather;	
}
