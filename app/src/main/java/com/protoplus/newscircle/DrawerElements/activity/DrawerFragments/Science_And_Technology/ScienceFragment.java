package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Science_And_Technology;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.protoplus.newscircle.Bean.FragmentContent;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.CustomLists.CategoryAdapter;
import com.protoplus.newscircle.CustomLists.TabsPagerAdapter;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.ParentFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.CNETNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DeccanheraldNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.HinduBusinessLineNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IBNLiveNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndianExpressNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.InfoWorldNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TechCrunchNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TechRepublicNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TheStatesmanNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.WiredNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aakash on 10/9/2015.
 */
public class ScienceFragment extends ParentFragment{
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    private static final ScienceFragment instance = new ScienceFragment();
    public static final ScienceFragment getScienceFragment(){
        return instance;
    }

    public ScienceFragment() {
        Helper.backIndex = 7;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("Science And Technology");
        }
    }
    @Override
    public String getHeadlineText() {
        return Helper.Category_list[7];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try{
            CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
            String category = holder.category.getText().toString();
            System.out.println("______________the selected icon is__________ "+category);
            Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment","Science");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("Science"));
            }

            switch(category){
                case Helper.IndianExpress:
                    System.out.println("this is Indian Express ");
                    content = new FragmentContent();
                    content.setURLS(Helper.IndianExpressScienceURL.split(","));
                    content.setTitles(Helper.IndianExpressScienceTitle.split(","));
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
                case Helper.IBNLive:
                    System.out.println("this is ibnlive");
                    content = new FragmentContent();
                    content.setCategory("tech");
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
                    content.setURLS(Helper.DeccanheraldScienceURL.split(","));
                    content.setTitles(Helper.DeccanHeraldScienceTitle.split(","));
                    content.setCid(133);
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
                    content.setURLS(Helper.TimesOfIndiaScienceURL.split(","));
                    content.setTitles(Helper.TimesOfIndiaScienceTitle.split(","));
                    content.setNumbofitems(2);
                    main_ar=new ArrayList<>();

                    for(int i = 0;i<2;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", TimesOfIndiaNewsFragment.class.getName());
                    break;

                case Helper.TheStatesman:
                    System.out.println("this is the statesman");
                    content = new FragmentContent();
                    content.setURLS(Helper.TheStatesmanScienceURL.split(","));
                    content.setTitles(Helper.TheStatesManScienceTitle.split(","));
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
                case Helper.FreePressJournal:
                    break;
                case Helper.HinduBusinessLine:
                    content = new FragmentContent();
                    content.setURLS(Helper.HinduBusinessLineScienceURL.split(","));
                    content.setTitles(Helper.HinduBusinessScienceTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<1;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", HinduBusinessLineNewsFragment.class.getName());
                    break;
                case Helper.CNET:
                    content = new FragmentContent();
                    content.setURLS(Helper.CNETScienceURL.split(","));
                    content.setTitles(Helper.CNETScienceTitle.split(","));
                    content.setNumbofitems(4);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<4;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", CNETNewsFragment.class.getName());

                    break;
                case Helper.TechCrunch:
                    content = new FragmentContent();
                    content.setURLS(Helper.TechCrunchScienceURL.split(","));
                    content.setTitles(Helper.TechCrunchScienceTitle.split(","));
                    content.setNumbofitems(5);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<5;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", TechCrunchNewsFragment.class.getName());
                    break;
                case Helper.InfoWorld:
                    content = new FragmentContent();
                    content.setURLS(Helper.InfoWorldScienceURL.split(","));
                    content.setTitles(Helper.InfoWorldScienceTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<1;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", InfoWorldNewsFragment.class.getName());

                    break;
                case Helper.TechRepublic:
                    content = new FragmentContent();
                    content.setURLS(Helper.TechRepublicScienceURL.split(","));
                    content.setTitles(Helper.TechRepublicScienceTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<1;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", TechRepublicNewsFragment.class.getName());
                    break;
                case Helper.WiredNews:
                    content = new FragmentContent();
                    content.setURLS(Helper.WiredNewsScienceURL.split(","));
                    content.setTitles(Helper.WiredScienceTitle.split(","));
                    content.setNumbofitems(1);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<1;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", WiredNewsFragment.class.getName());
                    break;
                default:
                    break;
            }
            try{
                startActivity(it);
            }catch(Exception e){
                System.out.println("The Item is Touched");
            }
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
            ArrayList scienceFragmentItems = new ArrayList(Arrays.asList(Helper.ScienceItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if(category.equals(Helper.Category_list[7])){
                    fav_index[i++] = scienceFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(i =0 ; i<fav_index.length;i++){
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.ScienceIcons[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else {
            return Helper.ScienceIcons;
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
            return Helper.ScienceItems;
        }
    }
}
