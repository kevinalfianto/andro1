package com.example.googlemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class LocationHelper {
	private MainActivity activity;
	
	// gps
	private Location location;
	private LocationManager locationManager;
	private String provider;
	
	public double latitude; // get latitude
	public double longitude; // get longitude
	
	public LocationHelper(MainActivity mainAct) {
		activity = mainAct;
	}
	
	public void changePosition(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	public void updateLocation() {
		Criteria criteria = new Criteria();
		locationManager = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);
		provider = locationManager.getBestProvider(criteria, false);
		location = locationManager.getLastKnownLocation(provider);

		boolean enabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!enabled) {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			activity.startActivity(intent);
		} else {
			if (location != null) {
				locationManager.requestLocationUpdates(provider, 500, 0.5f,
						myLocationListener);
			} else {
				changePosition(0, 0);
			}
		}
	}

	private LocationListener myLocationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(activity, "Enabled new provider " + provider,
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(activity, "Disabled provider " + provider,
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLocationChanged(Location location) {
			Toast.makeText(activity, (int) location.getLatitude() + "," + (int) location.getLongitude(), Toast.LENGTH_SHORT).show();
			changePosition(location.getLatitude(), location.getLongitude());
			activity.setPosition("Lat: " + latitude + "\nLong: "
					+ longitude);
		}
	};
}
