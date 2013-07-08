package com.ssict.jsonexample;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import it.ssict.jsonparser.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ssict.jsonexample.utils.JSONParser;
import com.ssict.jsonexample.weatherdata.Condition;
import com.ssict.jsonexample.weatherdata.LocalData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class ParserActivity extends Activity {

	LocalData data;
	ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);
		image = (ImageView) findViewById(R.id.imageView1);
		getJson();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parser, menu);
		return true;
	}

	
	
	
	
	public void getJson() {
		// Creating JSON Parser instance
		
		 
		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromResource(R.raw.res);
		
		
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {

			@Override
			protected Boolean doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				JSONParser jParser = new JSONParser(ParserActivity.this);
				String url = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=-25.948166,32.618408&key=p7ptew8qk9wfhcsspv8zvuq9&format=json&includeLocation=yes&num_of_days=1";
				JSONObject json = jParser.getJSONFromUrl(url);
				
				Gson gson = new Gson();
		         JSONObject dataObject = null;
		         try {
		 			dataObject = (JSONObject) json.get("data");
		 		} catch (JSONException e) {
		 			e.printStackTrace();
		 		}		        
		        data = gson.fromJson(dataObject.toString(), LocalData.class);				
				
				for (Condition c : data.currentCondition)
				{
					try {
					    URL thumb_u = new URL(c.weatherIconUrl.get(0).value);
					    Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
					    image.setImageDrawable(thumb_d);
					}
					catch (Exception e) {
					    e.printStackTrace();
					}
					
				}
				return true;
			}
			
		};
		try {
			task.execute("").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("das","END");
		
	}
	
}
