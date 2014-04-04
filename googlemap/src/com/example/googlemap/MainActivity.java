package com.example.googlemap;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi") public class MainActivity extends Activity {
  private static Context context;
  static private LatLng MYPOSITION = new LatLng(-6.890543, 107.610016);
  private final double itbLat = -6.892834;
    private final double itbLon = 107.610420; 
  static final LatLng GERBANGITB = new LatLng(-6.892774, 107.610419);
  
   private Marker myposition;
  
  private LocationHelper locationHelper;
	
	public void updateGPS(View view) {
		locationHelper.updateLocation();
	}
  
  private GoogleMap map;

  @SuppressLint("NewApi") @Override
  protected void onCreate(Bundle savedInstanceState) {
	context=getApplicationContext();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
	locationHelper = new LocationHelper(this);
	locationHelper.updateLocation();
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    Marker gerbangitb = map.addMarker(new MarkerOptions().position(GERBANGITB)
        .title("GerbangITB"));
    
    //MYPOSITION = new LatLng(locationHelper.latitude, locationHelper.longitude);
    
    myposition = map.addMarker(new MarkerOptions()
        .position(MYPOSITION)
        .title("myposition")
        .snippet("cool")
        .icon(BitmapDescriptorFactory
            .fromResource(R.drawable.ic_launcher)));

    //Toast.makeText(context, (int) MYPOSITION.latitude + "," + (int) MYPOSITION.longitude, Toast.LENGTH_SHORT).show();
    //final TextView distance = (TextView) findViewById(R.id.distance);             
    //distance.setText("Latitude	: " + MYPOSITION.latitude);
    //distance.setText("Distance : (calc)");
	//distance.setX(0);
    //distance.setY(0);
    //final TextView bearing = (TextView) findViewById(R.id.bearing);             
    //bearing.setText("Longitude	: " + MYPOSITION.longitude);
    //bearing.setText( "Bearing  : (calc)");
	//bearing.setX(0);
   // bearing.setY(30);
    
    
    TextView textBearing = (TextView) findViewById(R.id.bearing);
	textBearing.setText("Bearing : " + CalcHelper.bearing(
			locationHelper.latitude, locationHelper.longitude, 
			itbLat, itbLon));
	//textBearing.setText("Bearing : " + CalcHelper.bearing(
	//		MYPOSITION.latitude, MYPOSITION.longitude, 
	//		itbLat, itbLon));
	textBearing.setX(0);
	textBearing.setY(30);
	
	TextView textDist = (TextView) findViewById(R.id.distance);
	textDist.setText("Dist : " + CalcHelper.distVincenty(
			locationHelper.latitude, locationHelper.longitude, 
			itbLat, itbLon));
	//textDist.setText("Dist : " + CalcHelper.distVincenty(
	//		MYPOSITION.latitude, MYPOSITION.longitude, 
    //		itbLat, itbLon));
	textDist.setX(0);
	textDist.setY(0);
	MYPOSITION = new LatLng(locationHelper.latitude, locationHelper.longitude);
	myposition.setPosition(MYPOSITION);
	map.moveCamera(CameraUpdateFactory.newLatLngZoom(MYPOSITION, 10));
	
	
    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(GERBANGITB, 15));

    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
	
	
	public void setPosition(String string) {
		TextView textBearing = (TextView) findViewById(R.id.bearing);
		textBearing.setText("Bearing : " + CalcHelper.bearing(
				locationHelper.latitude, locationHelper.longitude, 
				itbLat, itbLon));
		TextView textDist = (TextView) findViewById(R.id.distance);
		textDist.setText("Dist : " + CalcHelper.distVincenty(
				locationHelper.latitude, locationHelper.longitude, 
				itbLat, itbLon));
		MYPOSITION = new LatLng(locationHelper.latitude, locationHelper.longitude);
		myposition.setPosition(MYPOSITION);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(MYPOSITION, 10));
	}
} 