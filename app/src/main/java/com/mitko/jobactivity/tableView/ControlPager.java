package com.mitko.jobactivity.tableView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.mitko.jobactivity.R;
import com.mitko.jobactivity.tableView.sliding_layout.SlidingTabLayout;
import com.mitko.jobactivity.tableView.viewPageAdapter.ViewPagerAdapterHeader;
import com.mitko.jobactivity.tableView.viewPageAdapter.ViewPagerAdapterTabs;
import com.nineoldandroids.view.ViewHelper;

public class ControlPager extends AppCompatActivity implements ObservableScrollViewCallbacks {

    //header
    ViewPager viewPagerHeader;
    ViewPagerAdapterHeader viewPagerAdapterHeader;
    LinearLayout dotsLayout;
    TextView[] dots;
    int[] layouts;

    //tabs
    public static ViewPager viewPagerTabs;
    ViewPagerAdapterTabs viewPagerAdapterTabs;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Tab1", "Tab2", "Tab3"};
    int Numboftabs = 3;


    LinearLayout toolbarView;
    View mHeaderView;
    int mBaseTranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_control);

        mHeaderView = findViewById(R.id.header);
        toolbarView = (LinearLayout) findViewById(R.id.tool_bar_container);
        viewPagerAdapterTabs =  new ViewPagerAdapterTabs(getSupportFragmentManager(),Titles,Numboftabs);
        viewPagerTabs = (ViewPager) findViewById(R.id.view_pager_tabs);

        viewPagerHeader = (ViewPager) findViewById(R.id.view_pager_header);
        dotsLayout = (LinearLayout) findViewById(R.id.layout_dots);

        //add header
        header();

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
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
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


    /**
     * add header
     */

    public void header(){

        // layouts of all welcome sliders
        layouts = new int[]{
                R.layout.header1,
                R.layout.header2,
        };

        // adding bottom dots
        addBottomDots(0);

        viewPagerAdapterHeader = new ViewPagerAdapterHeader(layouts);
        viewPagerHeader.setAdapter(viewPagerAdapterHeader);
        viewPagerHeader.addOnPageChangeListener(viewPagerPageChangeListener);

    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[0]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[0]);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };



    /**
     * scroll view
     */

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            int toolbarHeight = toolbarView.getHeight();
            float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);
            if (firstScroll) {
                if (-toolbarHeight < currentHeaderTranslationY) {
                    mBaseTranslationY = scrollY;
                }
            }
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            com.nineoldandroids.view.ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);
    }

    @Override
    public void onDownMotionEvent() {}

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}