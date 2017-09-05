package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.protoplus.newscircle.Bean.CategoryBean;
import com.protoplus.newscircle.Bean.FragmentContent;
import com.protoplus.newscircle.CustomLists.CategoryAdapter;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;

/**
 * Created by Aakash on 9/25/2015.
 */
public abstract class ParentFragment extends Fragment implements AdapterView.OnItemClickListener  {
    ListView newspaper_list;
    ArrayList<String> fev_ar;
    public ArrayList<String> fav_site_name = new ArrayList<>();
    SharedPreferences mPrefs;
    protected FragmentContent content;
    CategoryAdapter adapter;
    View v;
    ArrayList<CategoryBean> ar = new ArrayList<CategoryBean>();
    int icon_ar[]=getIconAr();
    String category[];
    public TextView headlineText ;
    public abstract int[] getIconAr();
    public abstract String getCategory();
    public abstract String getHeadlineText();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPrefs = getActivity().getSharedPreferences(Helper.MyPREFERENCES, getActivity().MODE_PRIVATE);
        try{
            fev_ar = Helper.getFavSiteAr(mPrefs);
        }catch(Exception e){
            fev_ar = new ArrayList<>();
        }

        String string= getCategory();

        category = string.split(",");
        System.out.println("the category is : : : "+string+" : : : Headline : : : "+getHeadlineText());
        View rootView = inflater.inflate(R.layout.fragment_world, container, false);
        v=inflater.inflate(R.layout.headline_text,null);
        for (int i = 0; i<category.length;i++){
            CategoryBean cbean=new CategoryBean();
            cbean.setCategory(category[i]);
            cbean.setCategoryIcon(icon_ar[i]);
            cbean.setHeadLine(getHeadlineText());
            for (String fev_category:
                 fev_ar) {
                if(fev_category.equals(cbean.getHeadLine()+"#"+category[i])){
                    cbean.setIsFav(true);
                }

            }
            ar.add(i, cbean);
        }
        newspaper_list = (ListView) rootView.findViewById(R.id.newspaper_list);

        headlineText = (TextView) v.findViewById(R.id.headline);
        headlineText.setText(getHeadlineText());

        adapter = new CategoryAdapter(getActivity().getApplicationContext(),R.layout.fragment_world,ar);
        // Inflate the layout for this fragment

        //newspaper_list.addHeaderView(v);
        newspaper_list.setAdapter(adapter);
        newspaper_list.setOnItemClickListener(this);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
