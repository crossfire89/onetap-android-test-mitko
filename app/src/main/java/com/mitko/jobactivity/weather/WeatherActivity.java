package com.mitko.jobactivity.weather;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mitko.jobactivity.R;
import com.mitko.jobactivity.weather.sliding_layout.SlidingTabLayout;
import com.mitko.jobactivity.weather.viewPagerAdapter.ViewPagerAdapterTabs;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Mitko on 31.7.2016 г..
 */
public class WeatherActivity extends AppCompatActivity {

    public static ViewPager viewPagerTabs;
    ViewPagerAdapterTabs viewPagerAdapterTabs;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"5 days", "16 days"};
    int Numboftabs = 2;

    public static SlidingUpPanelLayout slidingUpPanelLayout;
    public static RelativeLayout allDropDown;
    public static TextView tvPressure;
    public static TextView tvLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        viewPagerAdapterTabs =  new ViewPagerAdapterTabs(getSupportFragmentManager(),Titles,Numboftabs);
        viewPagerTabs = (ViewPager) findViewById(R.id.view_pager);
        allDropDown = (RelativeLayout) findViewById(R.id.all_dropDown);
        tvPressure = (TextView) findViewById(R.id.tv_pressure);
        tvLocationName = (TextView) findViewById(R.id.tv_location_name);

        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setShadowHeight(0);
        slidingUpPanelLayout.setEnableDragViewTouchEvents(false);
        slidingUpPanelLayout.setTouchEnabled(true);
        slidingUpPanelLayout.setOverlayed(true);
        allDropDown.setVisibility(View.GONE);

        //add tabs
        tabs();
    }


    /**
     * add tabs
     */

    public void tabs() {

        // Assigning ViewPager View and setting the adapter
        viewPagerTabs.setAdapter(viewPagerAdapterTabs);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.weather_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(viewPagerTabs);
        tabs.setDistributeEvenly(true);
    }

}
