package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Local_And_States;

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
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndiaTodayNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TheStatesmanNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndianExpressNewsFragment;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.newscircle.MainContent.TimesOfIndiaMainContent;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Akash on 29/07/15.
 */
public class LocalAndStatesFragment extends ParentFragment {
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    ArrayList<String> fav_site_ar = Helper.favorite_site_ar;
    int fav_index[]; int i = 0;
    String category,site;
    private static final LocalAndStatesFragment instance = new LocalAndStatesFragment();

    public static final LocalAndStatesFragment getLocalAndStatesFragment(){
        return instance;
    }

    public LocalAndStatesFragment() {
        Helper.backIndex = 2;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("Local And States");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
        CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
        String category = holder.category.getText().toString();
        Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
        it.putExtra("fragment","LocalAndStates");
        // TODO set this varialble
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("Local And States"));
            }

            switch(category){
            case Helper.IndianExpress:
                content = new FragmentContent();
                content.setURLS(Helper.IndianExpressLocalAndStatesURL.split(","));
                content.setTitles(Helper.IELocalAndStatesTitle.split(","));
                content.setNumbofitems(12);
                main_ar=new ArrayList<>();

                for(int i = 0;i<12;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content" , content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndianExpressNewsFragment.class.getName());
                break;
            case Helper.IndiaToday:
                content = new FragmentContent();
                content.setURLS(Helper.IndiaTodayLocalAndStatesURL.split(","));
                content.setTitles(Helper.IndiaTodayLocalAndStatesTitle.split(","));
                content.setNumbofitems(1);
                main_ar = new ArrayList<>();
                for(int i = 0; i < 1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndiaTodayNewsFragment.class.getName());
                break;
            case Helper.DeccanHerald:
                content = new FragmentContent();
                content.setURLS(Helper.DeccanheraldLocalAndStatesURL.split(","));
                content.setTitles(Helper.DeccanheraldLocalAndStatesTitle.split(","));
                content.setNumbofitems(1);
                main_ar = new ArrayList<>();
                for(int i = 0; i < 1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", DeccanheraldNewsFragment.class.getName());
                break;
            case Helper.TheStatesman:
                content = new FragmentContent();
                content.setURLS(Helper.TheStatesmanLocalAndStatesURL.split(","));
                content.setTitles(Helper.TheStatesmanLocalAndStatesTitle.split(","));
                content.setNumbofitems(5);
                main_ar = new ArrayList<>();
                for(int i = 0; i < 5;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", TheStatesmanNewsFragment.class.getName());
                break;
            case Helper.AsiaAge:
                content = new FragmentContent();
                content.setURLS(Helper.AsiaAgeLocalAndStatesURL.split(","));
                content.setTitles(Helper.AsiaAgeLocalAndStatesTitle.split(","));
                content.setNumbofitems(3);
                main_ar = new ArrayList<>();
                for(int i = 0; i < 3;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", AsiaAgeNewsFragment.class.getName());
                break;
            case Helper.TimesOfIndia:
                content = new FragmentContent();
                content.setURLS(Helper.TimesOfIndiaLocalAndStatesURL.split(","));
                content.setTitles(Helper.TimesOfIndiaLocalAndStatesTitle.split(","));
                content.setNumbofitems(32);
                main_ar = new ArrayList<>();
                for(int i = 0; i < 32;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", TimesOfIndiaNewsFragment.class.getName());
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
    public String getHeadlineText() {
        return Helper.Category_list[2];
    }

    @Override
    public int[] getIconAr() {
        if(Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")){
            fav_site_ar = Helper.favorite_site_ar;
            System.out.println("The Fevorite site list size is : "+Helper.favorite_site_ar.size());

            // TODO Hear i am getting the indexes which are fevorited
            fav_index = new int[fav_site_ar.size()];
            ArrayList worldFragmentItems = new ArrayList(Arrays.asList(Helper.LocalAndStateItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];
                System.out.println("#_#_ The site is : "+site);
                if(category.equals(Helper.Category_list[2])){
                    System.out.println(" Index __ "+worldFragmentItems.indexOf(str.split("#")[1]));
                    fav_index[i++] = worldFragmentItems.indexOf(str.split("#")[1]);
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(int i =0 ; i<fav_index.length;i++){
                fav_icon_ar[i] = Helper.LocalAndStatesIcons[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else{
            return Helper.LocalAndStatesIcons;
        }
    }

    @Override
    public String getCategory() {
        if(Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")){
            String s = "";
            for(String str : fav_site_name){
                s = s + str + ",";
            }
            return s;
        }
        else {
            return Helper.LocalAndStateItems;
        }
    }
}
