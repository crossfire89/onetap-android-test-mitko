package com.mitko.jobactivity.tableView.customListView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mitko.jobactivity.R;

import java.util.ArrayList;

/**
 * Created by Mitko on 11.6.2016 г..
 */
public class ListAdapterTableView extends BaseAdapter {


    Context context;
    int layoutResourceId;
    ArrayList<String> aList;


    public ListAdapterTableView(Context context, int layoutResourceId, ArrayList<String> aList) {
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
        return row;
    }
}
