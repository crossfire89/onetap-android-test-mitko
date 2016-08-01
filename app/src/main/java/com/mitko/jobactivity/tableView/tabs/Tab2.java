package com.mitko.jobactivity.tableView.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.mitko.jobactivity.GlobalValues;
import com.mitko.jobactivity.R;
import com.mitko.jobactivity.tableView.customListView.ListAdapterTableView;

import java.util.ArrayList;


/**
 * Created by Mitko on 11.4.2016 г..
 */
public class Tab2 extends Fragment {

    ListAdapterTableView listAdapterTableView;
    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2, container, false);

        Activity parentActivity = getActivity();

        ArrayList<String> listViewData = new ArrayList<String>();
        for(int i = 0; i < 40; i++) {
            listViewData.add("row");
        }

        listAdapterTableView = new ListAdapterTableView(GlobalValues.menuClass, R.layout.list_layout_table, listViewData);

        final ObservableListView listView = (ObservableListView) v.findViewById(R.id.list_view_tab2);

        // Setting the adapter to the listView
        listView.setAdapter(listAdapterTableView);


        //listView button
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
            }
        });


        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            // Scroll to the specified position after layout
            Bundle args = getArguments();
            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
                ScrollUtils.addOnGlobalLayoutListener(listView, new Runnable() {
                    @Override
                    public void run() {
                        // scrollTo() doesn't work, should use setSelection()
                        listView.setSelection(initialPosition);
                    }
                });
            }

            // TouchInterceptionViewGroup should be a parent view other than ViewPager.
            listView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));

            listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }


        return v;
    }
}
