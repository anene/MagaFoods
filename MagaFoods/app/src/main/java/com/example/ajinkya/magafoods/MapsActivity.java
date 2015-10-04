package com.example.ajinkya.magafoods;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.*;
import com.squareup.picasso.Picasso;
import android.location.*;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import java.text.DecimalFormat;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.StrictMode;
import android.widget.TextView;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private final double meterToMile  = 0.000621371192;

    private DecimalFormat df;

    private double lat;
    private double lon;

    private Location myLocation;

    private boolean clickedOnce = false;
    private ArrayList<Business> mapsMaga;
    private HashMap<String, Integer> bubba;
    private ArrayList<ImageView> collection;

    private TextView textView;

    private final int numImages = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        df = new DecimalFormat("#.##");
        collection = new ArrayList<ImageView>();
        bubba = new HashMap<String, Integer>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i = getIntent();
        mapsMaga = i.getParcelableArrayListExtra(MagaActivity.data);
        lat = i.getDoubleExtra(MainActivity.latt, 0.0);
        lon = i.getDoubleExtra(MainActivity.lonn, 0.0);

        for (int k = 0; k < numImages; k++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(k);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setScaleType(ScaleType.FIT_XY);
            collection.add(imageView);
        }

        myLocation = new Location("User");
        myLocation.setLatitude(lat);
        myLocation.setLongitude(lon);

        textView = (TextView)findViewById(R.id.topText);

        setUpMapIfNeeded();
        setMarkerClickListener(mMap);

        mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );
    }

    /**
     * meters to Miles
     * @param meters double
     * @return double
     */
    private double metersToMiles(double meters)
    {
        return meters*meterToMile;
    }

    /**
     * adds a listener to each marker
     * @param map GoogleMap
     */
    private void setMarkerClickListener(GoogleMap map) {
        map.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                if (!clickedOnce)
                {
                    clickedOnce = true;
                }
                else
                {
                    for (int i = 0; i < numImages; i++) {
                        layout.removeView(collection.get(i));
                    }
                }
                String title = marker.getTitle();
                int id = bubba.get(title);
                for (int i = 0; i < numImages; i++) {
                    ImageView imageView = collection.get(i);
                    Picasso.with(getApplicationContext()).load(mapsMaga.get(id).getPhotoUrl()).resize(200,200).into(imageView);
                    layout.addView(imageView);
                }

                Location posB = new Location("restaurant");
                posB.setLatitude(mapsMaga.get(id).getLatitude());
                posB.setLongitude(mapsMaga.get(id).getLongitude());
                double meters = myLocation.distanceTo(posB);
                textView.setText(df.format(metersToMiles(meters)) + " miles");
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }

            LatLng latLng = new LatLng(lat, lon);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        for (int i = 0; i < mapsMaga.size(); i++)
        {
            mMap.addMarker(new MarkerOptions().position(new LatLng(mapsMaga.get(i).getLatitude(), mapsMaga.get(i).getLongitude())).title(mapsMaga.get(i).toString()));
            bubba.put(mapsMaga.get(i).toString(), i);
        }
        if (mMap != null)
        {
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
    }

    /**
     * goes back to magaActivity
     * @param v View
     */
    public void goBack(View v)
    {
        mMap.clear();
        Intent execute = new Intent(this, MagaActivity.class);
        execute.putExtra(MainActivity.latt, lat);
        execute.putExtra(MainActivity.lonn, lon);
        startActivity(execute);
    }
}
