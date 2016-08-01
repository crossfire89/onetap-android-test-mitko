package com.mitko.jobactivity.weather.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mitko.jobactivity.GlobalValues;
import com.mitko.jobactivity.R;
import com.mitko.jobactivity.tableView.customListView.ListAdapterTableView;
import com.mitko.jobactivity.weather.WeatherActivity;
import com.mitko.jobactivity.weather.customListView.ListAdapterWeather;
import com.mitko.jobactivity.weather.dataCallback.GpsDataCallback;
import com.mitko.jobactivity.weather.dataCallback.WeatherDataCallback;
import com.mitko.jobactivity.weather.gps.Coordinates;
import com.mitko.jobactivity.weather.webApiCalls.ApiFiveDays;
import com.mitko.jobactivity.weather.webApiCalls.ApiSixteenDays;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Mitko on 11.4.2016 г..
 */
public class WeatherTab2 extends Fragment implements WeatherDataCallback, GpsDataCallback {

    ListAdapterWeather listAdapterWeather;
    Activity parentActivity;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.weather_tab2, container, false);
        parentActivity = getActivity();

        //start listening for coordinate
        Coordinates coordinates  = new Coordinates(parentActivity);
        coordinates.gpsDataCallback = this;
        coordinates.startGpsCoordinates();

        return v;
    }

    @Override
    public void weatherCallback(final ArrayList<HashMap<String, String>> weatherInfo) {

        listAdapterWeather = new ListAdapterWeather(GlobalValues.menuClass, R.layout.list_layout_weather, weatherInfo);

        final ListView listView = (ListView) v.findViewById(R.id.list_view_weather2);

        // Setting the adapter to the listView
        listView.setAdapter(listAdapterWeather);


        //listView button
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub

                WeatherActivity.slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

                String pressure = weatherInfo.get(position).get("pressure");
                WeatherActivity.tvPressure.setText(pressure);

                String locationName = weatherInfo.get(position).get("name");
                WeatherActivity.tvLocationName.setText(locationName);
            }
        });
    }


    @Override
    public void gpsCallback() {
        ApiSixteenDays apiSixteenDays = new ApiSixteenDays(parentActivity);
        apiSixteenDays.weatherDataCallback = this;
        apiSixteenDays.execute();
    }
}
