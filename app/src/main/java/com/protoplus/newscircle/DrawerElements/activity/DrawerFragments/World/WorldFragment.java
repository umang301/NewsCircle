package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.World;

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
 * Created by Aakash on 29/07/15.
 */
public class WorldFragment extends ParentFragment {
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;

    private static final WorldFragment instance = new WorldFragment();
    public static final WorldFragment getWorldFragment(){
        return instance;
    }

    public WorldFragment() {
        Helper.backIndex = 1;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("World");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
        CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
        String category = holder.category.getText().toString();
        Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment","World");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("World"));
            }

            switch(category){
            case Helper.IndianExpress:
                System.out.println("this is Indian Express ");
                content = new FragmentContent();
                content.setURLS(Helper.IndianExpressWorldURL.split(","));
                content.setTitles(Helper.IEWorldTitle.split(","));
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
                content.setURLS(Helper.IndiaTodayWorldURL.split(","));
                content.setTitles(Helper.IndiaTodayWorldTitle.split(","));
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
                content.setURLS(Helper.OneIndiaWorldURL.split(","));
                content.setTitles(Helper.OneIndiaWorldTitle.split(","));
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
                content.setURLS(Helper.IndiaTVWorldURL.split(","));
                content.setTitles(Helper.IndiaTVWorldTitle.split(","));
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
            /*this is remaining*/
            /*case "outlookindia.com":
                System.out.println("this is outlookindia");
                break;*/
            case Helper.OutlookIndia:
                System.out.println("this is outlook India");
                content = new FragmentContent();
                content.setCategory("world");
                content.setURLS(Helper.OutLookIndiaWorldURL.split(","));
                content.setTitles(Helper.OutLookIndiaWorldTitle.split(","));
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
                content.setCategory("world");
                content.setURLS(Helper.IBNLiveWorldURL.split(","));
                content.setTitles(Helper.IBNLiveWorldTitle.split(","));
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
                content.setURLS(Helper.DeccanheraldWorldURL.split(","));
                content.setCid(158);
                content.setTitles(Helper.DeccanHeraldWorldTitle.split(","));
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
                content.setURLS(Helper.TimesOfIndiaWorldURL.split(","));
                content.setTitles(Helper.TOIWorldTitle.split(","));
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
                content.setURLS(Helper.HindustanTimesWorldURL.split(","));
                content.setTitles(Helper.HTWorldTitle.split(","));
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
                content.setURLS(Helper.TheStatesmanWorldURL.split(","));
                content.setTitles(Helper.TheStatesManWorldTitle.split(","));
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
            case Helper.AsiaAge:
                content = new FragmentContent();
                content.setURLS(Helper.AsiaAgeWorldURL.split(","));
                content.setTitles(Helper.AsiaAgeWorldTitle.split(","));
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
            ArrayList worldFragmentItems = new ArrayList(Arrays.asList(Helper.worldFragmentItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if(category.equals(Helper.Category_list[1])){
                    fav_index[i++] = worldFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(i =0 ; i<fav_index.length;i++){
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.worldFragmentIcons[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else{
            return Helper.worldFragmentIcons;
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
        else{
            return Helper.worldFragmentItems;
        }
    }

    @Override
    public String getHeadlineText() {
        return Helper.Category_list[1];
    }
}
