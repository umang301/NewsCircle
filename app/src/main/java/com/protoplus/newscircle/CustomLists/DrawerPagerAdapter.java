package com.protoplus.newscircle.CustomLists;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.protoplus.newscircle.DrawerElements.activity.HeadLine.HeadLines;
import com.protoplus.newscircle.newscircle.AboutusFragment;

/**
 * Created by Aakash on 11/30/2015.
 */
public class DrawerPagerAdapter extends FragmentStatePagerAdapter {

    public DrawerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 1:
                System.out.println("News Topics is selected");
                fragment = new HeadLines();
                break;
            case 2:
                System.out.println("Fevorite News is selected");
                fragment = new AboutusFragment();
                break;
            case 3:
                System.out.println("Notification is selected");
                fragment = new AboutusFragment();
                break;
            case 4:
                System.out.println("Rate us is selected");
                fragment = new AboutusFragment();
                break;
            case 5:
                System.out.println("Share us is selected");
                fragment = new AboutusFragment();
                break;
            case 6:
                System.out.println("About us is selected");
                fragment = new AboutusFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
