package com.fragdemo.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fragdemo.app.BlankFragment;
import com.fragdemo.app.BlankFragment2;
import com.fragdemo.app.BlankFragment3;
import com.fragdemo.app.BlankFragment4;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BlankFragment();
        } else if (position == 1) {
            return new BlankFragment2();
        } else if (position == 2) {
            return new BlankFragment3();
        } else if (position == 3) {
            return new BlankFragment4();
        } else {
            return new BlankFragment3();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Fragment1";
            case 1:
                return "Fragment2";
            case 2:
                return "Fragment3";
            case 3:
                return "Fragment4";
            default:
                return null;
        }
    }

}