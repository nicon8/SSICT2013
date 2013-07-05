package com.ssict.location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.Menu;

public class LocationActivity extends Activity {

	Fragment fg = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		if (savedInstanceState == null) {
	        fg = new LocationFragment();
	        getFragmentManager().beginTransaction().add(android.R.id.content, fg)
	            .commit();
	    }	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

	public void setLocation(Location l) {
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> addresses = null;
		try {
			addresses = geocoder.getFromLocation(l.getLatitude(), l.getLongitude(), 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Address a: addresses) {
			Log.d("COUNTRY",a.getCountryName());
			Log.d("LOCALITY",a.getSubLocality());
		}	
	}
	
}
