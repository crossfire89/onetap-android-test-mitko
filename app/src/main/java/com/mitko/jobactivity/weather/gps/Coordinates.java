package com.mitko.jobactivity.weather.gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.mitko.jobactivity.GlobalValues;
import com.mitko.jobactivity.weather.dataCallback.GpsDataCallback;
import com.mitko.jobactivity.weather.dataCallback.WeatherDataCallback;
import com.mitko.jobactivity.weather.webApiCalls.ApiFiveDays;

/**
 * Created by Mitko on 31.7.2016 г..
 */
public class Coordinates{

    ProgressDialog dialog;

    Context context;
    public LocationManager lm;
    public GpsDataCallback gpsDataCallback;

    public Coordinates(Context context) {
        this.context = context;
    }


    /**
     * start gps listener
     */

    public void startGpsCoordinates() {

        dialog = new ProgressDialog(context);
        dialog.setMessage("Searching for satellites...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        //check status of gps provider
        if (lm.isProviderEnabled( LocationManager.NETWORK_PROVIDER ) ) {
            lm.requestLocationUpdates(lm.NETWORK_PROVIDER, 50000, 10, Loclist);
        } else if(lm.isProviderEnabled( LocationManager.GPS_PROVIDER) ) {
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 50000, 10, Loclist);
        } else {
            dialog.dismiss();
            Toast.makeText(context, "Enable Location", Toast.LENGTH_LONG).show();
        }
    }



    /**
     * get gps coordinates
     */

    LocationListener Loclist = new LocationListener() {

        @Override
        public void onLocationChanged(Location lc) {
            // TODO Auto-generated method stub

            dialog.dismiss();

            GlobalValues.lat = lc.getLatitude();
            GlobalValues.lon = lc.getLongitude();

            gpsDataCallback.gpsCallback();
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
    };

}
