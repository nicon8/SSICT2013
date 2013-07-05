package com.ssict.location;

import android.app.Fragment;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LocationFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_location, container, false);
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		LocationManager locationManager = (LocationManager) getActivity().getApplicationContext()
	            .getSystemService(Context.LOCATION_SERVICE);
		LocationListener list = new MyLocationListener((LocationActivity)getActivity()); 
		
		locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, list, getActivity().getMainLooper());
		
		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, list);
	}

}
