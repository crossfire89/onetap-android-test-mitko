package com.mitko.jobactivity.weather.dataCallback;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mitko on 14.4.2016 г..
 */

public interface WeatherDataCallback {
    void weatherCallback(ArrayList<HashMap<String, String>> weatherInfo);
}
