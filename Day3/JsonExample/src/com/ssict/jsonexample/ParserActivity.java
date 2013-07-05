package com.ssict.jsonexample;

import it.ssict.jsonparser.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ssict.jsonexample.utils.JSONParser;
import com.ssict.jsonexample.weatherdata.LocalData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class ParserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);
		pippo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parser, menu);
		return true;
	}

	
	
	
	
	public void pippo() {
		// Creating JSON Parser instance
		
		 
		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromResource(R.raw.res);
		
		
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {

			@Override
			protected Boolean doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				JSONParser jParser = new JSONParser(ParserActivity.this);
				String url = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=-25.948166,32.618408&key=p7ptew8qk9wfhcsspv8zvuq9&format=json&includeLocation=yes&num_of_days=10";
				JSONObject json = jParser.getJSONFromUrl(url);
				
				Gson gson = new Gson();
		         JSONObject dataObject = null;
		         try {
		 			dataObject = (JSONObject) json.get("data");
		 		} catch (JSONException e) {
		 			e.printStackTrace();
		 		}		        
		        LocalData data = gson.fromJson(dataObject.toString(), LocalData.class);
				Log.d("END",data+"");					
				return true;
			}
			
		};
		task.execute("");
		Log.d("das","END");
		/*try {
		    // Getting Array of Contacts
		    //contacts = json.getJSONArray(TAG_CONTACTS);
		     
		    // looping through All Contacts
		    for(int i = 0; i < contacts.length(); i++){
		        JSONObject c = contacts.getJSONObject(i);
		         
		        // Storing each json item in variable
		        String id = c.getString(TAG_ID);
		        String name = c.getString(TAG_NAME);
		        String email = c.getString(TAG_EMAIL);
		        String address = c.getString(TAG_ADDRESS);
		        String gender = c.getString(TAG_GENDER);
		         
		        // Phone number is agin JSON Object
		        JSONObject phone = c.getJSONObject(TAG_PHONE);
		        String mobile = phone.getString(TAG_PHONE_MOBILE);
		        String home = phone.getString(TAG_PHONE_HOME);
		        String office = phone.getString(TAG_PHONE_OFFICE);
		         
		    
		} catch (JSONException e) {
		    e.printStackTrace();
		}*/
		
		
		
	}
	
}
