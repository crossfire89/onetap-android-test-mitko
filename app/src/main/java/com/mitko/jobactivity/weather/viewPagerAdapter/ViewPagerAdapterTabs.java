package com.mitko.jobactivity.weather.viewPagerAdapter;

/**
 * Created by Administrator on 18.4.2015 г..
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.mitko.jobactivity.tableView.tabs.Tab1;
import com.mitko.jobactivity.tableView.tabs.Tab2;
import com.mitko.jobactivity.tableView.tabs.Tab3;
import com.mitko.jobactivity.weather.tabs.WeatherTab1;
import com.mitko.jobactivity.weather.tabs.WeatherTab2;


/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapterTabs extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapterTabs is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapterTabs is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterTabs(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new WeatherTab1();
            case 1:
                return new WeatherTab2();
        }
        return null;
    }


    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }

}