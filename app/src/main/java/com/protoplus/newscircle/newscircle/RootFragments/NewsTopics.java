package com.protoplus.newscircle.newscircle.RootFragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.appodeal.ads.Appodeal;
import com.protoplus.newscircle.CustomLists.MainTabPagerAdapter;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.SlidingTabLayout;

/**
 * Created by Aakash on 11/30/2015.
 */
public class NewsTopics extends Fragment {
    ViewPager pager;
    MainTabPagerAdapter adapter;
    SlidingTabLayout tabs;
    ProgressBar loader;
    private static boolean first_ad = true;
    View v;
    private LoadData loadData = new LoadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("__________NewsTopics counter is : " + Helper.adNewsTopicsCounter + "_________________");
        if(first_ad){
            Appodeal.onResume(getActivity(), Appodeal.INTERSTITIAL);
            if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
                Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
            }
            first_ad = false;
        }
        else if(Helper.adNewsTopicsCounter %3==0){
            Appodeal.onResume(getActivity(), Appodeal.INTERSTITIAL);
            if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
                System.out.println(" ..........:: Interstitial Ad is Loaded");
                Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
            }
        }
        Helper.adNewsTopicsCounter++;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        loadData.cancel(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news_topics, null);
        loader = (ProgressBar) v.findViewById(R.id.newstopicscenterLoader);
        loadData.execute("");
        /*loader.setVisibility(View.VISIBLE);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter = new MainTabPagerAdapter(getChildFragmentManager());
        pager = (ViewPager) v.findViewById(R.id.pager1);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(9);
        tabs = (SlidingTabLayout) v.findViewById(R.id.tabs1);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabs.setViewPager(pager);
        pager.setCurrentItem(Helper.pagerItemIndex);
        Helper.selected_item = "NewsTopics";
        loader.setVisibility(View.GONE);*/
        return v;
    }

    private class LoadData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO solution of Interrupted Exception is at http://www.yegor256.com/2015/10/20/interrupted-exception.html
            Thread t = new Thread();
            try {
                t.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //t.interrupt();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            adapter = new MainTabPagerAdapter(getChildFragmentManager());
            pager = (ViewPager) v.findViewById(R.id.pager1);
            pager.setAdapter(adapter);
            pager.setOffscreenPageLimit(9);
            tabs = (SlidingTabLayout) v.findViewById(R.id.tabs1);
           // tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
            tabs.setViewPager(pager);
            pager.setCurrentItem(Helper.pagerItemIndex);
            Helper.selected_item = "NewsTopics";
            loader.setVisibility(View.GONE);
        }
    }
}
