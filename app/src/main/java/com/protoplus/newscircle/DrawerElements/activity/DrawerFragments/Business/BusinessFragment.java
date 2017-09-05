package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Business;

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
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.EconomicTimesNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.FirstpostNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IBNLiveNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.LivemintNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.MoneycontrolNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.MoneylifeNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.OutlookIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.mydigtalfcNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.RediffNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TheStatesmanNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aakash on 10/1/2015.
 */
public class BusinessFragment extends ParentFragment {
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    private static final BusinessFragment instance = new BusinessFragment();
    public BusinessFragment() {
        Helper.backIndex = 5;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("Business");
        }
    }
    public static final BusinessFragment getBusinessFragment(){
        return instance;
    }
    @Override
    public String getHeadlineText() {
        return Helper.Category_list[5];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder) view.getTag();
            String category = holder.category.getText().toString();
            Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment", "Business");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index", Helper.category_list.indexOf("Business"));
            }

            switch (category) {
                case Helper.Moneylife:
                    System.out.println("this is Money life");
                    content = new FragmentContent();
                    content.setURLS(Helper.MoneylifeBusinessURL.split(","));
                    content.setTitles(Helper.MoneyLifeBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", MoneylifeNewsFragment.class.getName());
                    break;
                case Helper.Livemint:
                    content = new FragmentContent();
                    content.setURLS(Helper.LivemintBusinessURL.split(","));
                    content.setTitles(Helper.LiveMintBusinessTitle.split(","));
                    content.setNumbofitems(8);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 8; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", LivemintNewsFragment.class.getName());
                    break;
                case Helper.mydigtalfc:
                    content = new FragmentContent();
                    content.setURLS(Helper.mydigtalfcBusinessURL.split(","));
                    content.setTitles(Helper.MyDigitalFCBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", mydigtalfcNewsFragment.class.getName());
                    break;
                case Helper.AsiaAge:
                    content = new FragmentContent();
                    content.setURLS(Helper.AsiaAgeBusinessURL.split(","));
                    content.setTitles(Helper.AsiaAgeBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", AsiaAgeNewsFragment.class.getName());
                    break;
                case Helper.Firstpost:
                    content = new FragmentContent();
                    content.setCategory("business");
                    content.setURLS(Helper.FirstpostBusinessURL.split(","));
                    content.setTitles(Helper.FirstPostBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", FirstpostNewsFragment.class.getName());
                    break;
                case Helper.RediffNews:
                    content = new FragmentContent();
                    content.setURLS(Helper.RediffNewsBusinessURL.split(","));
                    content.setTitles(Helper.RediffBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", RediffNewsFragment.class.getName());
                    break;
                case Helper.Moneycontrol:
                    content = new FragmentContent();
                    content.setURLS(Helper.MoneycontrolBusinessURL.split(","));
                    content.setTitles(Helper.MoneyControlBusinessTitle.split(","));
                    content.setNumbofitems(2);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 2; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", MoneycontrolNewsFragment.class.getName());
                    break;
                case Helper.OutlookIndia:
                    content = new FragmentContent();
                    content.setURLS(Helper.OutlookIndiaBusinessURL.split(","));
                    content.setTitles(Helper.OutlookIndiaBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
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
                    content.setCategory("business");
                    content.setURLS(Helper.IBNLiveWorldURL.split(","));
                    content.setTitles(Helper.IBNLiveWorldTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();
                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", IBNLiveNewsFragment.class.getName());
                    break;
                case Helper.TimesOfIndia:
                    System.out.println("this is Times Of India");
                    content = new FragmentContent();
                    content.setURLS(Helper.TimesOfIndiaBusinessURL.split(","));
                    content.setTitles(Helper.TOIBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", TimesOfIndiaNewsFragment.class.getName());
                    break;
                case Helper.TheStatesman:
                    content = new FragmentContent();
                    content.setURLS(Helper.TheStatesmanBusinessURL.split(","));
                    content.setTitles(Helper.TheStatesManBusinessTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 1; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", TheStatesmanNewsFragment.class.getName());
                    break;
                case Helper.EconomicTimes:
                    content = new FragmentContent();
                    content.setURLS(Helper.EconomicTimesBusinessURL.split(","));
                    content.setTitles(Helper.EconomicsTimesBusinessTitle.split(","));
                    content.setNumbofitems(2);
                    main_ar = new ArrayList<>();

                    for (int i = 0; i < 2; i++) {
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", EconomicTimesNewsFragment.class.getName());
                    break;
                default:
                    break;
            }
            try {
                startActivity(it);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int[] getIconAr() {
        if (Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")) {
            ArrayList<String> fav_site_ar = Helper.favorite_site_ar;
            String category, site;
            int fav_index[];
            int i = 0;

            fav_index = new int[fav_site_ar.size()];

            // TODO neeeds Helper.(Category name)FragmentItem
            ArrayList worldFragmentItems = new ArrayList(Arrays.asList(Helper.BusinessItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if (category.equals(Helper.Category_list[5])) {
                    fav_index[i++] = worldFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for (i = 0; i < fav_index.length; i++) {
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.BusinessIcons[fav_index[i]];
            }
            return fav_icon_ar;
        } else {
            return Helper.BusinessIcons;
        }
    }

    @Override
    public String getCategory() {
        if (Helper.selected_item!=null && Helper.selected_item.equals("FavoriteNews")) {
            String s = "";
            // TODO need fav_site_name ar from Parent class
            for (String str : fav_site_name) {
                s = s + str + ",";
            }
            return s;
        } else {
            return Helper.BusinessItems;
        }
    }

}
