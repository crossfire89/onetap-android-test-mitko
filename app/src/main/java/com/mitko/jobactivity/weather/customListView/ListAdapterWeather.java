package com.mitko.jobactivity.weather.customListView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mitko.jobactivity.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mitko on 11.6.2016 г..
 */
public class ListAdapterWeather extends BaseAdapter {


    Context context;
    int layoutResourceId;
    ArrayList<HashMap<String, String>> aList;


    public ListAdapterWeather(Context context, int layoutResourceId, ArrayList<HashMap<String, String>> aList) {
        super();
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.aList = aList;
    }


    @Override
    public int getCount() {
        return aList.size();
    }

    @Override
    public Object getItem(int position) {
        return aList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView tvMain = (TextView) row.findViewById(R.id.tv_main);
        TextView tvDesc = (TextView) row.findViewById(R.id.tv_desc);


        String main = aList.get(position).get("main");
        String desc = aList.get(position).get("description");
        String pressure = aList.get(position).get("pressure");

        tvMain.setText(main);
        tvDesc.setText(desc);

        return row;
    }
}
