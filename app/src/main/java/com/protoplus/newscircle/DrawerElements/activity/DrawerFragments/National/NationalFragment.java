package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.National;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.protoplus.newscircle.Bean.FragmentContent;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.CustomLists.CategoryAdapter;
import com.protoplus.newscircle.CustomLists.TabsPagerAdapter;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.ParentFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.AsiaAgeNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DNAIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DeccanheraldNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.HindustanTimesNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IBNLiveNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndiaTVNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndiaTodayNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.OneIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.OutlookIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TheStatesmanNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndianExpressNewsFragment;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aakash on 9/25/2015.
 */
public class NationalFragment extends ParentFragment {
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    private static final NationalFragment instance = new NationalFragment();
    public static final NationalFragment getNationalFragment(){
        return instance;
    }

    public NationalFragment() {
        Helper.backIndex = 3;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("National");
        }
        // Required empty public constructor
    }

    @Override
    public String getHeadlineText() {
        return Helper.Category_list[3];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
        CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
        String category = holder.category.getText().toString();
        System.out.println("______________the selected icon is__________ " + category);
        Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment","Nation");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("National"));
            }

            switch(category){
            case Helper.IndianExpress:
                System.out.println("this is Indian Express ");
                content = new FragmentContent();
                content.setURLS(Helper.IndianExpressNationalURL.split(","));
                content.setTitles(Helper.IENationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();
                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndianExpressNewsFragment.class.getName());
                break;
            case Helper.IndiaToday:
                System.out.println("this is India Today");
                content = new FragmentContent();
                content.setURLS(Helper.IndiaTodayNationalURL.split(","));
                content.setTitles(Helper.IndiaTodayNationalTitile.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndiaTodayNewsFragment.class.getName());
                break;
            case Helper.OneIndia:
                System.out.println("this is one india");
                content = new FragmentContent();
                content.setURLS(Helper.OneIndiaNationalURL.split(","));
                content.setTitles(Helper.OneIndiaNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();
                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", OneIndiaNewsFragment.class.getName());
                break;
            case Helper.IndiaTV:
                System.out.println("this is india tv");
                content = new FragmentContent();
                content.setURLS(Helper.IndiaTVNationalURL.split(","));
                content.setTitles(Helper.IndiaTVNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndiaTVNewsFragment.class.getName());
                break;
            case Helper.OutlookIndia:
                System.out.println("this is OutLookIndia");
                content = new FragmentContent();
                content.setURLS(Helper.OutlookIndiaNationalURL.split(","));
                content.setTitles(Helper.OutlookIndiaNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", OutlookIndiaNewsFragment.class.getName());
                break;
            case Helper.IBNLive:
                System.out.println("this is ibnlive");
                content = new FragmentContent();
                content.setCategory("india");
                content.setURLS(Helper.IBNLiveNationalURL.split(","));
                content.setTitles(Helper.IBNLiveNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();
                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IBNLiveNewsFragment.class.getName());
                break;
            case Helper.DeccanHerald:
                System.out.println("this is deccanherald");
                content = new FragmentContent();
                content.setURLS(Helper.DeccanheraldNationalURL.split(","));
                content.setTitles(Helper.DeccanHeraldNationalTitle.split(","));
                content.setCid(70);
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", DeccanheraldNewsFragment.class.getName());
                break;
            case Helper.TimesOfIndia:
                System.out.println("this is times of india");
                content = new FragmentContent();
                content.setURLS(Helper.TimesOfIndiaNationalURL.split(","));
                content.setTitles(Helper.TOINationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", TimesOfIndiaNewsFragment.class.getName());
                break;
            case Helper.HindustanTimes:
                System.out.println("this is hindustan times");
                content = new FragmentContent();
                content.setURLS(Helper.HindustanTimesNationalURL.split(","));
                content.setTitles(Helper.HTNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", HindustanTimesNewsFragment.class.getName());
                break;
            case Helper.TheStatesman:
                System.out.println("this is the statesman");
                content = new FragmentContent();
                content.setURLS(Helper.TheStatesmanNationalURL.split(","));
                content.setTitles(Helper.TheStatesNationalManTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", TheStatesmanNewsFragment.class.getName());
                break;
            case Helper.DNAIndia:
                System.out.println("this is the statesman");
                content = new FragmentContent();
                content.setURLS(Helper.DNAIndiaNationalURL.split(","));
                content.setTitles(Helper.DNAIndiaNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", DNAIndiaNewsFragment.class.getName());
                break;
            case Helper.AsiaAge:
                System.out.println("this is the statesman");
                content = new FragmentContent();
                content.setURLS(Helper.AsiaAgeNationalURL.split(","));
                content.setTitles(Helper.AsiaAgeNationalTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();

                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", AsiaAgeNewsFragment.class.getName());
                break;
            default:
                break;
        }
        startActivity(it);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int[] getIconAr() {
        if(Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")){
            ArrayList<String> fav_site_ar = Helper.favorite_site_ar;
            String category,site;
            int fav_index[]; int i = 0;

            fav_index = new int[fav_site_ar.size()];

            // TODO neeeds Helper.(Category name)FragmentItem
            ArrayList nationFragmentItems = new ArrayList(Arrays.asList(Helper.nationalFragmentItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if(category.equals(Helper.Category_list[3])){
                    fav_index[i++] = nationFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(i =0 ; i<fav_index.length;i++){
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.nationalFragmentIcons[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else {
            return Helper.nationalFragmentIcons;
        }
    }

    @Override
    public String getCategory() {
        if(Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")){
            String s = "";
            // TODO need fav_site_name ar from Parent class
            for(String str : fav_site_name){
                s = s + str + ",";
            }
            return s;
        }
        else {
            return Helper.nationalFragmentItems;
        }
    }
}
