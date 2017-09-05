package com.protoplus.newscircle;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.google.android.gms.ads.AdView;
import com.protoplus.newscircle.DrawerElements.activity.FragmentDrawer;
import com.protoplus.newscircle.DrawerElements.adapter.NavigationDrawerAdapter;
import com.protoplus.newscircle.Util.AlarmReceiver;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.newscircle.RootFragments.FavoriteNewsFragment;
import com.protoplus.newscircle.newscircle.RootFragments.NewsTopics;
import com.protoplus.newscircle.newscircle.AboutusFragment;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    int pos;
    Activity activity = this;
    Boolean killApp = false;
    FragmentManager fragmentManager;
    //UiModeManager uiModeManager;
    ContextData contextData;
    private FragmentDrawer drawerFragment;
    public int itemIndex = 0;
    public FrameLayout frameLayout;

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Helper.initAppodealBanner(this);
    }

    @Override
    public void onBackPressed() {
        if(killApp){
            Helper.isAppOpen = false;
            android.os.Process.killProcess(android.os.Process.myPid());
        }else{
            Toast.makeText(getApplicationContext(),"press back again to close application",Toast.LENGTH_LONG).show();
            killApp = true;
        }
        System.out.println("____________________Back Button is Pressed");

        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);
        super.onCreate(savedInstanceState);
        // if(!DestroyHeadLine){

        setContentView(R.layout.activity_main);
        Intent it = getIntent();
        Boolean fromBack;
        try{
            fromBack = it.getExtras().getBoolean("fromBack");
        }
        catch(Exception e){
            fromBack =false;
        }
        /*uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        uiModeManager.disableCarMode(UiModeManager.MODE_NIGHT_YES);*/
        /*----------------------------------------*/

        Helper.initAppodealBanner(this);


            /*AdView mAdView = (AdView) findViewById(R.id.adLayout);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/
        /*------------------------------------------*/
           /* mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-6658314028492546/6351038919");


            requestNewInterstitial();*/

        /*----------------------------------------------*/
        try {
            frameLayout = (FrameLayout) findViewById(R.id.container_body);
            //BitmapDrawable background = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.drawerbar));
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            contextData = (ContextData) getApplicationContext();
            setSupportActionBar(mToolbar);
        /*=====================================================*/

            getSupportActionBar().setHomeButtonEnabled(false);


            /**
             * on swiping the viewpager make respective tab selected
             * */

        /*========================================================*/

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //getSupportActionBar().set
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff0066cc));
            drawerFragment = (FragmentDrawer)
                    getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
            drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
            drawerFragment.setDrawerListener(this);
            if(fromBack){
                if(Helper.selected_item.equals("FavoriteNews") ){
                    displayView(1);
                }else{
                    displayView(itemIndex);
                }
                fromBack = false;
            }
            else{
                displayView(itemIndex);
            }

        } catch (Exception e) {
            Log.d("kampuru", "back ni error solved ");

            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    PendingIntent broadcastPendingIntent;

    private void getBroadcastPandingIntent() {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        broadcastPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 234324243, intent, 0);
    }
    public void stopAlert() {
        System.out.println("_______________________THE STOP ALERT METHOD IS CALLED");
        getBroadcastPandingIntent();
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(broadcastPendingIntent);
    }

    public void startAlert() {
        System.out.println("_______________________THE START ALERT METHOD IS CALLED");
        int i = 5;
        int interval = 1000 * 60;
        getBroadcastPandingIntent();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), broadcastPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, broadcastPendingIntent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position, NavigationDrawerAdapter adapter) {
        displayView(position);
    }

    protected void displayView(int position) {
        pos = position;
        //pager.setCurrentItem(pos);

        // TODO set Inter Stitial ad
        /*if(Appodeal.isLoaded(Appodeal.INTERSTITIAL ) || Appodeal.isPrecache(Appodeal.INTERSTITIAL )){
            Appodeal.show(this, Appodeal.INTERSTITIAL );
        }
*/
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                Log.d("Fragment0", "The Fragment object is ( case 0 )::  "+fragment);
                fragment = new NewsTopics();

                Helper.selected_item = "NewsTopics";
                title = getResources().getString(R.string.app_name);
                break;
            case 1:
                Log.d("Fragment1", "The Fragment object is ( case 1 )::  "+fragment);
                fragment = new FavoriteNewsFragment();

                Helper.selected_item = "FavoriteNews";
                title = "FavoriteNews";
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                Helper.rateApp(this);
                break;
            case 5:
                Helper.shareApp(this,null,null);
                break;
            case 6:
                fragment = new AboutusFragment();
                Helper.selected_item = "AboutUs";
                title = "News Circle";
                break;

            default:
                break;
        }

        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            System.out.println("....................Back stack Entry Count ::: "+fragmentManager.getBackStackEntryCount());
            /*for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                fragmentManager.popBackStack();
            }*/
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Log.d("Fragment set", "The Fragment object Name ::  "+fragment.getClass().getName()
                    + "\n The Reference is : "+fragment);

            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
        /*if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    pager.setCurrentItem(pos);
                }
            });
        }*/

    }


}