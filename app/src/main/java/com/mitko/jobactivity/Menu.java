package com.mitko.jobactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mitko.jobactivity.tableView.ControlPager;
import com.mitko.jobactivity.weather.WeatherActivity;

public class Menu extends AppCompatActivity {

    Button btnTableView;
    Button btnWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        GlobalValues.menuClass = Menu.this;
        GlobalValues.context = this;
        GlobalValues.activity = (Activity) this;

        //initialize all values
        initValues();
    }

    /**
     * initialize all values
     * run all main thread process
     */

    public void initValues() {

        btnTableView = (Button) findViewById(R.id.btn_table_view);
        btnWeather = (Button) findViewById(R.id.btn_weather);

        btnTableView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu.this, ControlPager.class);
                startActivity(myIntent);
            }
        });


        btnWeather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //check for internet connection
                NetworkInfo info = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

                if(info == null) {
                    Toast.makeText(Menu.this, "Check your connection", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent myIntent = new Intent(Menu.this, WeatherActivity.class);
                    startActivity(myIntent);
                }


            }
        });
    }
}
