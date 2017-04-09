package com.rambostudio.zojoz.realmdatabase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rambostudio.zojoz.realmdatabase.fragment.HomeFragment;
import com.rambostudio.zojoz.realmdatabase.fragment.PersonFragment;
import com.rambostudio.zojoz.realmdatabase.fragment.PersonManagementFragment;

/**
 * Created by rambo on 8/4/2560.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return PersonFragment.newInstance();
            case 2:
                return PersonManagementFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "หน้าแรก";
            case 1:
                return "Home";
            case 2:
                return "Person";
            default:
                return null;
        }
    }

}
