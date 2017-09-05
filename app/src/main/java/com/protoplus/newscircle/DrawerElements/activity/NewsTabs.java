package com.protoplus.newscircle.DrawerElements.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.protoplus.newscircle.Bean.FragmentContent;
import com.protoplus.newscircle.CustomLists.TabsPagerAdapter;
import com.protoplus.newscircle.MainActivity;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.SlidingTabLayout;

public class NewsTabs extends AppCompatActivity {
    //Toolbar toolbar;
    ViewPager pager;
    LinearLayout layout;
    TabsPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[];
    int Numboftabs;
    String fragment_name;
    int fav_back_index;
    String classname;
    Toolbar toolbar;

    private void preBackProcess(){
        layout.removeAllViews();
        //Helper.pagerItemIndex=Helper.backIndex;
        if(fragment_name!=null && !Helper.selected_item.equals("FavoriteNews")){
            if(fragment_name.equals("HeadLine")){
                Helper.pagerItemIndex =0;
            } else if(fragment_name.equals("World")){
                Helper.pagerItemIndex =1;
            } else if(fragment_name.equals("LocalAndStates")){
                Helper.pagerItemIndex =2;
            } else if(fragment_name.equals("Nation")){
                Helper.pagerItemIndex =3;
            } else if(fragment_name.equals("Entertainment")){
                Helper.pagerItemIndex =4;
            } else if(fragment_name.equals("Business")){
                Helper.pagerItemIndex =5;
            } else if(fragment_name.equals("Sports")){
                Helper.pagerItemIndex =6;
            } else if(fragment_name.equals("Science")){
                Helper.pagerItemIndex =7;
            } else if(fragment_name.equals("LifeStyle")){
                Helper.pagerItemIndex =8;
            } else if(fragment_name.equals("Education")){
                Helper.pagerItemIndex =9;
            } else {Helper.pagerItemIndex = 0;}
        }
        else{
            Helper.favPagerItemIndex = fav_back_index;
        }
        Intent it = new Intent(NewsTabs.this,MainActivity.class);
        it.putExtra("fromBack",true);
        //onStop();
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO uncomment this if needed
        //preBackProcess();
     }

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);
        super.onRestart();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newstabs);

        Intent it = getIntent();
        FragmentContent content =(FragmentContent) it.getSerializableExtra("content");
        classname=it.getExtras().getString("class");
        fragment_name = it.getExtras().getString("fragment");
        fav_back_index = it.getExtras().getInt("fav_back_index");
        //getLayoutInflater().inflate(R.layout.activity_newstabs, frameLayout);
        //Drawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.drawerbar));
        try{
            String URL[] = content.getURLS();
            int cid = content.getCid();
            String category = content.getCategory();
            Numboftabs = content.getNumbofitems();
            System.out.println("the number of item is :::: "+Numboftabs);
            Titles = content.getTitles();
            layout= (LinearLayout) findViewById(R.id.newstabs);

            toolbar = (Toolbar) findViewById(R.id.news_toolbar);
            toolbar.setTitle("News");
            //toolbar.setLogo(R.drawable.leftarrow);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            adapter =  new TabsPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs,classname,cid,category,URL);

            pager = (ViewPager) findViewById(R.id.news_pager);

            //this line will define the number of fragment to be run behind visible fragment
            pager.setOffscreenPageLimit(1);

            pager.setAdapter(adapter);
            tabs = (SlidingTabLayout) findViewById(R.id.news_tabs);
            if(Numboftabs!=3){
                tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
            }
            tabs.setViewPager(pager);
        }catch(Exception e){
            System.out.println("at NewsTabs Line no 108___________");
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        System.out.println("___________ NewsTabs ______ OnDestroy __________________Helper.isAppOpen : "+Helper.isAppOpen);
        Helper.isAppOpen = false;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newstabs, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
        else*/
        if(id == android.R.id.home){
            onBackPressed();
            //preBackProcess();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}