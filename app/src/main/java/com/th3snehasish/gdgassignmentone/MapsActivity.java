package com.th3snehasish.gdgassignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

//    String mName = intent.getStringExtra("EXTRA_NAME");
    private GoogleMap mMap;
    private String mLat;
    private String mLon;
    private String mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mLat = extras.getString("EXTRA_LAT");
        mLon = extras.getString("EXTRA_LON");
        mName= extras.getString("EXTRA_NAME");
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        LatLng myLoc = new LatLng(Integer.parseInt(mLat),Integer.parseInt(mLon));
        mMap.addMarker(new MarkerOptions().position(myLoc).title(mName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));

    }
}
