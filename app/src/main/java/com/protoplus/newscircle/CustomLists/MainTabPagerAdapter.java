package com.protoplus.newscircle.CustomLists;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Business.BusinessFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Education.EducationFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Entertainment.EntertainmentFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.LifeStyle_and_Health.LifeStyleNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Local_And_States.LocalAndStatesFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.National.NationalFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.NullFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Science_And_Technology.ScienceFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Sports.SportsFragment;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.World.WorldFragment;
import com.protoplus.newscircle.DrawerElements.activity.HeadLine.HeadLines;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Aakash on 10/20/2015.
 */
public class MainTabPagerAdapter extends FragmentStatePagerAdapter {
    String selectedItem = Helper.selected_item;
    Set<String> s = new <String>HashSet();
    Fragment fragment = null;
    String cat_list[];
    int i = 0;
    public MainTabPagerAdapter(FragmentManager fm) {
        super(fm);
        System.out.println("# MainTAbPagerAdapter : constructor");
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("_______________delay called");
            }
        }, 2000);*/
        if(selectedItem!=null && selectedItem.equals("FavoriteNews")){
            for (String str: Helper.favorite_site_ar){
                s.add(str.split("#")[0]);
            }
            cat_list = new String[s.size()];
            Iterator<String> itr = s.iterator();
            while(itr.hasNext()){
                cat_list[i++] = itr.next();
            }
            Helper.category_list = new ArrayList<>(Arrays.asList(cat_list));
        }
    }

    @Override
    public Fragment getItem(final int position) {



        if(selectedItem.equals("FavoriteNews")){
            String className = (String)  getPageTitle(position);
            if(className.contains("LifeStyle") || className.contains("Science")){
                className = className.split(" ")[0];
            }
            className = className.replace(" ","");
            String folderName = ((String) getPageTitle(position)).replace(" ","_");
            try {
                System.out.println(Helper.classPath+folderName+"."+className+"Fragment");
                //System.out.println(LocalAndStatesFragment.class.getName());
                fragment = (Fragment)Class.forName(Helper.classPath+folderName+"."+className+"Fragment").newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{

            switch (position){
                case 0:
                    System.out.println(" HeadLIne Freagment is calling");
                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("_______________delay called");

                        }
                    }, 2000);*/
                    fragment = new HeadLines();
                    break;
                case 1:
                    System.out.println("World fragment is called");
                    fragment = new WorldFragment();
                    break;
                case 2:
                    System.out.println("Local and States Fragment is called");
                    fragment = new LocalAndStatesFragment();
                    break;
                case 3:
                    System.out.println("National Fragment is called");
                    fragment = new NationalFragment();
                    break;
                case 4:
                    System.out.println("Entertainment Fragment is called");
                    fragment = new EntertainmentFragment();
                    break;
                case 5:
                    System.out.println("Business Fragment is called");
                    fragment = new BusinessFragment();
                    break;
                case 6:
                    System.out.println("Sports Fragment is called");
                    fragment = new SportsFragment();
                    break;
                case 7:
                    System.out.println("Science Fragment is called");
                    fragment = new ScienceFragment();
                    break;
                case 8:
                    System.out.println("LifeStyle Fragment is called");
                    fragment = new LifeStyleNewsFragment();
                    break;
                case 9:
                    System.out.println("Education Fragment is called");
                    fragment = new EducationFragment();
                    break;

                default:
                    break;
            }
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        System.out.println("# MainTAbPagerAdapter : get Page Title");
        if (selectedItem!=null && selectedItem.equals("FavoriteNews")){
            return cat_list[position];
        }else{
            return Helper.Category_list[position];
        }
    }

    @Override
    public int getCount() {
        System.out.println("# MainTAbPagerAdapter : get Count");
        if(selectedItem!=null && selectedItem.equals("FavoriteNews")){
            return s.size();
        }else{
            return Helper.Category_list.length;
        }

    }
}
