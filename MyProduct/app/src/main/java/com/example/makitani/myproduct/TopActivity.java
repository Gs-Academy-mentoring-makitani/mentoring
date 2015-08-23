package com.example.makitani.myproduct;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class TopActivity extends FragmentActivity {

    private Button search;
    private View.OnClickListener search_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            search_Click();
        }
    };

    private void setViewObject () {
        search = (Button)findViewById(R.id.search);

    }
    private void setListner () {
        search.setOnClickListener(search_ClickListener);
    }

    private void  search_Click () {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
            }

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        setUpMapIfNeeded();
        setViewObject();
        setListner();
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
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
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
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    //現在地の周辺店舗情報を取得し表示
    private void setUpMap() {
        LatLng kurashima = new LatLng(35.665931,139.755918);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(kurashima, 18);
        mMap.moveCamera(cu);
        /*Marker test =*/
                mMap.addMarker(new MarkerOptions().position(kurashima).title("くら島").snippet("Recommend number is 10"));
        //recommend number を店舗ごとに取得し格納する
        //recommend number が１以上の店舗のみ表示
        //test.hideInfoWindow();
    }

}

