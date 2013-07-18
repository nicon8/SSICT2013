package com.ssict.jsonwin;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getJson();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
				JSONParser jParser = new JSONParser(MainActivity.this);
				String url = "http://10.0.115.138:8080/test-app/farmer?locality=Maputo";
				JSONObject json = jParser.getJSONFromUrl(url);
				
				Gson gson = new Gson();
		        JSONObject dataObject = null;
		         try {
		 			dataObject = (JSONObject) json.get("suggestion");
		 			Log.d("","");
		 		} catch (JSONException e) {
		 			e.printStackTrace();
		 		}		        
		        /*data = gson.fromJson(dataObject.toString(), LocalData.class);				
				
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
					
				}*/
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
