package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.LifeStyle_and_Health;

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
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.CNN_IBNNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DNAIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DeccanChronicleNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.DeccanheraldNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IBNLiveNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndianExpressNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.OneIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TheStatesmanNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aakash on 10/9/2015.
 */
public class LifeStyleNewsFragment extends ParentFragment{
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    private static final LifeStyleNewsFragment instance = new LifeStyleNewsFragment();
    public static final LifeStyleNewsFragment getLifeStyleNewsFragment(){
        return instance;
    }

    public LifeStyleNewsFragment() {
        Helper.backIndex = 8;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("Lifestyle And Health");
        }
    }
    @Override
    public String getHeadlineText() {
        return Helper.Category_list[8];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
        CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
        String category = holder.category.getText().toString();
        System.out.println("______________the selected icon is__________ "+category);
        Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment","LifeStyle");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("Lifestyle"));
            }

            switch(category){
            case Helper.IndianExpress:
                System.out.println("this is Indian Express ");
                content = new FragmentContent();
                content.setURLS(Helper.IndianExpressLifeStyleURL.split(","));
                content.setTitles(Helper.IndianExpressLifestyleTitle.split(","));
                content.setNumbofitems(3);
                main_ar=new ArrayList<>();
                for(int i = 0;i<3;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", IndianExpressNewsFragment.class.getName());
                break;
            case Helper.OneIndia:
                System.out.println("this is Indian Express ");
                content = new FragmentContent();
                content.setURLS(Helper.OneIndiaLifeStyleURL.split(","));
                content.setTitles(Helper.OneIndiaLifeStyleTitle.split(","));
                content.setNumbofitems(8);
                main_ar=new ArrayList<>();
                for(int i = 0;i<8;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", OneIndiaNewsFragment.class.getName());
                break;
            case Helper.IBNLive:
                System.out.println("this is ibnlive");
                content = new FragmentContent();
                content.setCategory("lifestyle");
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
                content.setURLS(Helper.DeccanheraldLifeStyleURL.split(","));
                content.setTitles(Helper.DeccanHeraldLifeStyleTitle.split(","));
                content.setNumbofitems(2);
                content.setCid(145);
                main_ar=new ArrayList<>();

                for(int i = 0;i<2;i++){
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
                content.setURLS(Helper.TimesOfIndiaLifeStyleURL.split(","));
                content.setTitles(Helper.TimesOfIndiaLifeStyleTitle.split(","));
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
                content.setURLS(Helper.TheStatesmanLifeStyleURL.split(","));
                content.setTitles(Helper.TheStatesManLifeStyleTitle.split(","));
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
                System.out.println("this is the Asian Age");
                content = new FragmentContent();
                content.setURLS(Helper.AsiaAgeLifeStyleURL.split(","));
                content.setTitles(Helper.AsianAgeLifeStyleTitle.split(","));
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
            case Helper.CNN_IBN:
                System.out.println("this is the CNN IBN");
                content = new FragmentContent();
                content.setURLS(Helper.CNN_IBNLifeStyleURL.split(","));
                content.setTitles(Helper.CNN_IBNLifeStyleTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();
                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", CNN_IBNNewsFragment.class.getName());
                break;
            case Helper.DNAIndia:
                System.out.println("this is the DNA India");
                content = new FragmentContent();
                content.setURLS(Helper.DNAIndiaLifeStyleURL.split(","));
                content.setTitles(Helper.DNAIndiaLifeStyleTitle.split(","));
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
            case Helper.DeccanChronicle:
                System.out.println("this is the Deccan Chronicle ");
                content = new FragmentContent();
                content.setURLS(Helper.DeccanChronicleLifeStyleURL.split(","));
                content.setTitles(Helper.DeccanChronicleLifeStyleTitle.split(","));
                content.setNumbofitems(1);
                main_ar=new ArrayList<>();
                for(int i = 0;i<1;i++){
                    main_ar.add(new ArrayList<SportChildSceneBean>());
                }
                ContextData.setMain_ar(main_ar);
                it.putExtra("content", content);
                it.putExtra("adapter", TabsPagerAdapter.class.getName());
                it.putExtra("class", DeccanChronicleNewsFragment.class.getName());
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
            ArrayList lifestyleFragmentItems = new ArrayList(Arrays.asList(Helper.LifeStyleItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if(category.equals(Helper.Category_list[8])){
                    fav_index[i++] = lifestyleFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(i =0 ; i<fav_index.length;i++){
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.LifeStyleIcons[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else {
            return Helper.LifeStyleIcons;
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
            return Helper.LifeStyleItems;
        }
    }

}
