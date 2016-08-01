package com.mitko.jobactivity.tableView.viewPageAdapter;

/**
 * Created by Administrator on 18.4.2015 г..
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.mitko.jobactivity.tableView.tabs.Tab1;
import com.mitko.jobactivity.tableView.tabs.Tab2;
import com.mitko.jobactivity.tableView.tabs.Tab3;


/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapterTabs extends CacheFragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapterTabs is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapterTabs is created
    private int mScrollY;


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
                return new Tab1();
            case 1:
                return new Tab2();
            case 2:
                return new Tab3();
        }

        return null;
    }


    public void setScrollY(int scrollY) {
        mScrollY = scrollY;
    }


    @Override
    protected Fragment createItem(int position) {
        Fragment f = new Tab1();
        if (0 < mScrollY) {
            Bundle args = new Bundle();
            args.putInt(Tab1.ARG_INITIAL_POSITION, 1);
            f.setArguments(args);
        }
        return f;
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