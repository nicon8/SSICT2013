package com.ssict.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener{

	private LocationActivity mActivity;
	
	
	
	public MyLocationListener(LocationActivity a) {
		super();
		this.mActivity = a;
	}

	@Override
	public void onLocationChanged(Location l) {
		mActivity.setLocation(l);	
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
