package com.ssict.jsonsimple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.ssict.sonsimple.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	class WeatherForecast {
		public String locality;
		public Double rainProbability;
		public Double windStrenght;
		public String windDirection;
		public Boolean safe;
		public int[] day;
		public Info info;
	}

	class Info {
		public String provider;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		withGsonLibrary();
		withoutGsonLibrary();
	}

	private void withGsonLibrary()
	{
		//object to json string
		Info info = new Info();
		info.provider = "INAM";
		WeatherForecast wf = new WeatherForecast();
		wf.info = info;
		wf.locality = "Maputo";
		wf.rainProbability = 25.5;
		wf.windStrenght = 18.2;
		wf.windDirection = "NS";
		wf.day = new int[]{17,07,2013};
		wf.safe = true;
		Gson gson = new Gson();
		String res = gson.toJson(wf);
		Log.d("WITH Gson",res);
		
		//from json string to object
		@SuppressWarnings("unused")
		WeatherForecast wf2 = gson.fromJson(res, WeatherForecast.class);
		
	}
	
	private void withoutGsonLibrary()
	{
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject objInfo = new JSONObject();
		try {
			obj.put("locality", "Maputo");
			obj.put("rainProbability", 25.5);
			obj.put("windStrenght", 18.2);
			obj.put("windDirection", "NS"); 
			ja.put(17);
			ja.put(07);
			ja.put(2013);
			obj.put("day", ja);
			objInfo.put("provider", "INAM");
			obj.put("info", objInfo);
			obj.put("safe", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = obj.toString();
		Log.d("NO Gson",res);
		
		
		JSONTokener tokener = new JSONTokener(res);
		JSONObject o=null;
		while (tokener.more())
		{
			try {
				o = (JSONObject)tokener.nextValue();
				o.getString("locality");
				o.getDouble("rainProbability");
				o.getDouble("windSpeed");
				o.getString("windDirection");
				o.getBoolean("safe");
				o.getJSONArray("day");
				o.getJSONObject("info");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
