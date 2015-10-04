package com.example.ajinkya.magafoods;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.location.LocationManager;
import android.content.Context;
import android.util.Log;
import android.location.Location;

public class MainActivity extends Activity implements LocationListener{

    private double lat;
    private double lon;

    public final static String latt = "latitude";
    public final static String lonn = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lat = 0.0;
        lon = 0.0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLocationChanged(Location location)
    {
        lat = location.getLatitude();
        lon = location.getLongitude();
    }

    public void onProviderDisabled(String provider)
    {
        Log.d("Latitude", "disable");
    }

    public void onProviderEnabled(String provider)
    {
        Log.d("Latitude", "enable");
    }

    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        Log.d("Latitude", "status");
    }

    public void onClickBtn(View v)
    {
        Intent execute = new Intent(this, MagaActivity.class);
        execute.putExtra(latt, lat);
        execute.putExtra(lonn, lon);
        startActivity(execute);
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }
}
