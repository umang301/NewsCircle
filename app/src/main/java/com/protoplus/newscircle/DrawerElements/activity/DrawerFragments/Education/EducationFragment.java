package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.Education;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.protoplus.newscircle.Bean.FragmentContent;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.CustomLists.CategoryAdapter;
import com.protoplus.newscircle.CustomLists.TabsPagerAdapter;
import com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.ParentFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.IndianExpressNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.OneIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsPaperList.TimesOfIndiaNewsFragment;
import com.protoplus.newscircle.DrawerElements.activity.NewsTabs;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aakash on 10/16/2015.
 */
public class EducationFragment extends ParentFragment{
    ArrayList<ArrayList<SportChildSceneBean>> main_ar;
    private static final EducationFragment instance = new EducationFragment();
    public static final EducationFragment getEducationFragment(){
        return instance;
    }

    public EducationFragment() {
        Helper.backIndex = 9;
        if(Helper.selected_item.equals("FavoriteNews")){
            Helper.favPagerItemIndex = Helper.category_list.indexOf("Education");
        }
    }
    @Override
    public String getHeadlineText() {
        return Helper.Category_list[9];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
            CategoryAdapter.CategoryHolder holder = (CategoryAdapter.CategoryHolder)view.getTag();
            String category = holder.category.getText().toString();
            System.out.println("______________the selected icon is__________ "+category);
            Intent it = new Intent(getActivity().getApplicationContext(), NewsTabs.class);
            it.putExtra("fragment","Education");
            if(Helper.selected_item.equals("FavoriteNews")){
                it.putExtra("fav_back_index",Helper.category_list.indexOf("Education"));
            }

            switch(category){
                case Helper.IndianExpress:
                    System.out.println("this is Indian Express ");
                    content = new FragmentContent();
                    content.setURLS(Helper.IndianExpressEducationURL.split(","));
                    content.setTitles(Helper.IndianExpressEducationTitle.split(","));
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
                case Helper.OneIndia:
                    System.out.println("this is Indian Express ");
                    content = new FragmentContent();
                    content.setURLS(Helper.OneIndiaEducationURL.split(","));
                    content.setTitles(Helper.OneIndiaEducationTitle.split(","));
                    content.setNumbofitems(3);
                    main_ar=new ArrayList<>();
                    for(int i = 0;i<3;i++){
                        main_ar.add(new ArrayList<SportChildSceneBean>());
                    }
                    ContextData.setMain_ar(main_ar);
                    it.putExtra("content", content);
                    it.putExtra("adapter", TabsPagerAdapter.class.getName());
                    it.putExtra("class", OneIndiaNewsFragment.class.getName());
                    break;

                case Helper.TimesOfIndia:
                    System.out.println("this is times of india");
                    content = new FragmentContent();
                    content.setURLS(Helper.TimesOfIndiaEducationURL.split(","));
                    content.setTitles(Helper.TimesOfIndiaEducationTitle.split(","));
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
            ArrayList worldFragmentItems = new ArrayList(Arrays.asList(Helper.EducationItems.split(",")));
            for (String str : fav_site_ar) {

                category = str.split("#")[0];
                site = str.split("#")[1];

                // TODO needs the number of catrgory from category list
                if(category.equals(Helper.Category_list[9])){
                    fav_index[i++] = worldFragmentItems.indexOf(str.split("#")[1]);
                    // TODO needs fav_site_name variable from ParentFragment
                    fav_site_name.add(site);
                }

            }

            int fav_icon_ar[] = new int[fav_index.length];
            for(i =0 ; i<fav_index.length;i++){
                // TODO needs (CategoryName)fragmentIcons array
                fav_icon_ar[i] = Helper.Education[fav_index[i]];
            }
            return fav_icon_ar;
        }
        else {
            return Helper.Education;
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
            return Helper.EducationItems;
        }
    }

}
