package com.mitko.jobactivity.tableView.viewPageAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mitko.jobactivity.GlobalValues;
import com.mitko.jobactivity.R;
import com.mitko.jobactivity.tableView.bitmapOptions.RoundedBm;

/**
 * Created by Mitko on 31.7.2016 г..
 */

/**
 * View pager adapter header
 */
public class ViewPagerAdapterHeader extends PagerAdapter {
    private LayoutInflater layoutInflater;
    int[] layouts;


    public ViewPagerAdapterHeader(int[] layouts) {
        this.layouts = layouts;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) GlobalValues.menuClass.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        if(position == 0) {
            ImageView androidLogo = (ImageView) view.findViewById(R.id.android_logo);
            RoundedBm roundedBm = new RoundedBm();
            Bitmap icon = BitmapFactory.decodeResource(GlobalValues.menuClass.getResources(), R.drawable.android);
            androidLogo.setImageBitmap(roundedBm.getCroppedBitmap(icon, 1000));

        } else if(position == 1 ) {
            ImageView iosLogo = (ImageView) view.findViewById(R.id.ios_logo);
            RoundedBm roundedBm = new RoundedBm();
            Bitmap icon = BitmapFactory.decodeResource(GlobalValues.menuClass.getResources(), R.drawable.ios);
            iosLogo.setImageBitmap(roundedBm.getCroppedBitmap(icon, 1000));
        }


        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}