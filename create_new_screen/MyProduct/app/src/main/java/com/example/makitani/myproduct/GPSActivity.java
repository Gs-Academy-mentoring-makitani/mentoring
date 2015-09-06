package com.example.makitani.myproduct;
//package com.moonlight_aska.android.gps03;

/**
 * Created by makitani on 2015/08/27.
 */

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GPSActivity extends Activity implements LocationListener {
    private LocationManager manager = null;
    private TextView latitude;
    private TextView longitude;
    private TextView altitude;
    /** Called when the activity is first created. */
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        // GPSサービス取得
        manager = (LocationManager)getSystemService(LOCATION_SERVICE);
        latitude = (TextView)findViewById(R.id.latitude_id);
        longitude = (TextView)findViewById(R.id.longitude_id);
        //altitude = (TextView)findViewById(R.id.altitude_id);
        }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        if(manager != null) {
            manager.removeUpdates(this);
            }
        super.onPause();
        }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        if(manager != null) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        super.onResume();
        }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        String str = "緯度：" + location.getLatitude();
        latitude.setText(str);
        str = "経度：" + location.getLongitude();
        longitude.setText(str);
        str = "高度：" + location.getAltitude();
        altitude.setText(str);
        }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        }
}