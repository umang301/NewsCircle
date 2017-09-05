package com.protoplus.newscircle.newscircle.RootFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.protoplus.newscircle.CustomLists.MainTabPagerAdapter;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by Aakash on 12/1/2015.
 */
public class FavoriteNewsFragment extends Fragment {
    ViewPager pager;
    MainTabPagerAdapter adapter;
    private static boolean first_ad = true;
    SlidingTabLayout tabs;
    SharedPreferences mPrefs;
    Context context;
    View v;
    ProgressBar loader;
    LoadData loadData = new LoadData();
    ArrayList<String> fev_site_ar ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        loadData.cancel(true);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    public void onStart() {
        super.onStart();
        System.out.println("__________NewsTopics counter is : " + Helper.adFevNewsCounter + "_________________");
        if(first_ad){
            Appodeal.onResume(getActivity(), Appodeal.INTERSTITIAL);
            if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
                Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
            }
            first_ad = false;
        }
        else if(Helper.adFevNewsCounter %3==0){
            Appodeal.onResume(getActivity(), Appodeal.INTERSTITIAL);
            if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
                System.out.println(" ..........:: Interstitial Ad is Loaded");
                Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
            }
        }
        Helper.adFevNewsCounter++;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fevorite_news, null);
        context = getContext();
        loader =(ProgressBar) v.findViewById(R.id.frvoritecenterLoader);
        loadData.execute("");
        return v;
    }
    private class LoadData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, context.MODE_PRIVATE);
            fev_site_ar = Helper.getFavSiteAr(mPrefs);
            Helper.favorite_site_ar = fev_site_ar;

            if (fev_site_ar.size() > 0) {
                adapter = new MainTabPagerAdapter(getChildFragmentManager());
                //Activity activity = getActivity();
                System.out.println(" view : "+v);
                pager = (ViewPager) v.findViewById(R.id.fav_pager1);
                pager.setAdapter(adapter);
                pager.setOffscreenPageLimit(9);
                tabs = (SlidingTabLayout) v.findViewById(R.id.fav_tabs1);
                if(fev_site_ar.size()!=3){
                    tabs.setDistributeEvenly(true);
                }
                //tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
                tabs.setViewPager(pager);
                pager.setCurrentItem(Helper.favPagerItemIndex);
            }else{
                TextView no_fev_text = (TextView) v.findViewById(R.id.no_fev_text);
                no_fev_text.setVisibility(View.VISIBLE);
            }

            Helper.selected_item = "FavoriteNews";
            loader.setVisibility(View.GONE);


        }
    }

}
