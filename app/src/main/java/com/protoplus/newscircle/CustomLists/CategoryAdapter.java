package com.protoplus.newscircle.CustomLists;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.protoplus.newscircle.Bean.CategoryBean;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakash on 9/23/2015.
 */
public class CategoryAdapter extends ArrayAdapter implements View.OnClickListener{
    Context context;
    int resource;
    CategoryBean bean;
    SharedPreferences mPrefs;

    ArrayList<CategoryBean> object;
    public CategoryAdapter(Context context, int resource, ArrayList<CategoryBean> objects) {
        super(context, resource, objects);
        this.context = context;
        mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, context.MODE_PRIVATE);
        this.resource = resource;
        /*if(Helper.selected_item.equals("FavoriteNews")){
            System.out.println("#_#_ THE FAVORITE NEWS SITE IS SELECTED");
            ArrayList<CategoryBean> fav_object = new ArrayList<>();
            for (CategoryBean cbean: objects) {
                if(cbean.getIsFav()){
                    System.out.println("#_#_ THE FEVORITE SITE IS : "+cbean.getHeadLine());
                    fav_object.add(cbean);
                }
            }
            this.object = fav_object;
            System.out.println("#_#_ THE SIZE OF FAVORITE ARRAY IS : "+fav_object.size());
        }
        else{*/
            this.object = objects;
        //}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        CategoryHolder holder = new CategoryHolder();
                
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bean = object.get(position);
        System.out.println(position+"_______is fav : "+bean.getIsFav());
        if(bean.getIsFav()==null){
            bean.setIsFav(false);
        }

        if(row == null){

            row = inflater.inflate(R.layout.category_child_scene,null);
            holder.category = (TextView)row.findViewById(R.id.category);
            holder.category.setTextColor(Color.BLACK);
            holder.icon = (ImageView) row.findViewById(R.id.category_icon);
            holder.fav = (Button) row.findViewById(R.id.fav_unfav_btn);
            row.setTag(holder);
            holder.fav.setOnClickListener(this);
        }
        else{
            holder =(CategoryHolder) row.getTag();
        }

            holder.category.setText(bean.getCategory());
            holder.icon.setImageResource(bean.getCategoryIcon());
            if(bean.getIsFav()){
                holder.fav.setBackgroundResource(R.drawable.fevorite);
            }else{
                holder.fav.setBackgroundResource(R.drawable.unfevorite);
            }

            //holder.fav.setText(String.valueOf(position));
            holder.fav.setTag(bean);
        return row;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        CategoryBean bean =(CategoryBean) b.getTag();
        System.out.println("___the tag is : "+b.getTag());
        Boolean is_fav = bean.getIsFav();
        System.out.println("____this tag is fevorite : "+is_fav);
        ArrayList<String> fev_ar ;
        try{
            fev_ar = Helper.getFavSiteAr(mPrefs);
        }catch(Exception e){
            fev_ar = new ArrayList();
            e.printStackTrace();
        }
        System.out.println("_____The size of arraylist is : "+fev_ar.size());
        if(fev_ar!=null){
            if (is_fav){
                b.setBackgroundResource(R.drawable.unfevorite);
                fev_ar.remove(bean.getHeadLine()+"#"+bean.getCategory());
                bean.setIsFav(false);
                Helper.setFavSiteAr(mPrefs,fev_ar);
            }
            else if(!is_fav){
                b.setBackgroundResource(R.drawable.fevorite);
                fev_ar.add(bean.getHeadLine()+"#"+bean.getCategory());
                bean.setIsFav(true);
                Helper.setFavSiteAr(mPrefs,fev_ar);
            }
        }
    }

    public class CategoryHolder{
        ImageView icon;
        public TextView category;
        Button fav;
    }

}
