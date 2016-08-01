package com.mitko.jobactivity.weather.webApiCalls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mitko.jobactivity.GlobalValues;
import com.mitko.jobactivity.weather.dataCallback.WeatherDataCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Mitko on 31.7.2016 г..
 */
public class ApiSixteenDays extends AsyncTask<JSONObject, Void, JSONObject> {

    private static final int MAX_RETRIES = 5;
    int mReentryCount = 0;

    JSONObject jObj = null;
    String strJson = "";
    String weatherUrl;

    double lat;
    double lon;
    String main;
    String description;
    String pressure;
    String name;
    ArrayList<HashMap<String, String>> weatherData = new ArrayList<HashMap<String, String>>();


    Context context;
    public WeatherDataCallback weatherDataCallback;

    public ApiSixteenDays(Context context) {
        this.context = context;
        lat = GlobalValues.lat;
        lon = GlobalValues.lon;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected JSONObject doInBackground(JSONObject... params) {

        try {
            weatherUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + lat + "&lon=" + lon + "&cnt=16" + "&APPID=889ea6ae9738bd030d9aabb9f82520b1";
            doHttpUrlConnectionAction(weatherUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObj;
    }



    //After completing background task Dismiss the progress dialog
    protected void onPostExecute(JSONObject jObj) {
        getAllData(jObj);
        weatherDataCallback.weatherCallback(weatherData);
    }



    /**
     * Returns the output from the given URL.
     *
     * I tried to hide some of the ugliness of the exception-handling
     * in this method, and just return a high level Exception from here.
     *
     * @param desiredUrl
     * @return JsonObject
     * @throws Exception
     */
    private void doHttpUrlConnectionAction(String desiredUrl) throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("POST");

            connection.setConnectTimeout(1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            mReentryCount = 0;

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            strJson = stringBuilder.toString();

            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(strJson);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }

        } catch (EOFException ex) {

            //repeat requests
            //EOFException can happen for Android 4.2 and below
            if (mReentryCount < MAX_RETRIES) {

                mReentryCount++;
                doHttpUrlConnectionAction(weatherUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }


    public void getAllData(JSONObject data) {

        if (data != null) {
            Iterator<String> it = data.keys();

            while (it.hasNext()) {
                String key = it.next();
                try {
                    if (data.get(key) instanceof JSONArray) {
                        JSONArray arry = data.getJSONArray(key);
                        int size = arry.length();

                        for (int i = 0; i < size; i++) {
                            getAllData(arry.getJSONObject(i));
                        }
                    } else if (data.get(key) instanceof JSONObject) {
                        getAllData(data.getJSONObject(key));

                    } else {

                        if(key.equals("main")){
                            main = data.optString(key);
                        }
                        if(key.equals("description")){
                            description = data.optString(key);
                        }
                        if(key.equals("pressure")){
                            pressure = data.optString(key);
                        }
                        if(key.equals("name")){
                            name = data.optString(key);
                        }

                        if(main != null && description != null && pressure != null) {
                            HashMap<String, String> weatherHashData = new HashMap<String, String>();
                            weatherHashData.put("main", main);
                            weatherHashData.put("description", description);
                            weatherHashData.put("pressure", pressure);
                            weatherHashData.put("name", name);
                            weatherData.add(weatherHashData);

                            main = null;
                            description = null;
                            pressure = null;
                        }

                    }
                } catch (Throwable e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
