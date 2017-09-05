package com.protoplus.newscircle.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.CustomLists.Sports;
import com.protoplus.newscircle.MainActivity;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.SplashActivity;
import com.protoplus.newscircle.newscircle.ShowContentPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;


public class Helper {
    static Activity subActivity,adActivity;
    public static SportChildSceneBean header;
    public static int MainContent_tap_count = 1;
    public static Helper getHelperInstance() {
        return helperInstance;
    }
    public static final String appKey = "422f5a4b59812178f44e3bd1c3644c904a1132f382155f09";

    public static final String ApplicationURL = "https://goo.gl/QXuUe1";
   // public static Boolean adLoaded = false;
    public static int adNewsTopicsCounter = 0;
    public static int adFevNewsCounter = 0;
    private static final Helper helper = new Helper();
    public static String selected_item = "";

    public static ArrayList<String> favorite_site_ar;

    public static ArrayList<String> category_list;

    public static String Share_title = "Share Title";

    public static String Share_Text = "News Circle Share Text";

    public static ArrayList<SportChildSceneBean> getHeadlineBeanar() {
        return HeadlineBeanar;
    }

    public static void setHeadlineBeanar(ArrayList<SportChildSceneBean> headlineBeanar) {
        HeadlineBeanar = headlineBeanar;
    }

    private static ArrayList<SportChildSceneBean> HeadlineBeanar;


    public static ArrayList<SportChildSceneBean> getSearch_result() {
        return search_result;
    }

    public static void setSearch_result(ArrayList<SportChildSceneBean> search_result) {
        Helper.search_result = search_result;
    }

    private static ArrayList<SportChildSceneBean> search_result;
    private static final Helper helperInstance = new Helper();

    public static boolean getisBack() {
        return isBack;
    }
    public static final String TimesOfIndiaIcon = "http://timesofindia.indiatimes.com/photo/7787613.cms";
    public static final String LatoBlack = "font/Lato2OFL/Lato-Regular.ttf";
    public static final String LatoBold = "font/Lato2OFL/Lato-Bold.ttf";
    public static final String LatoBlackThin = "font/Lato2OFL/Lato-Light.ttf";
    public static final String Arial = "font/Arial/arial.ttf";
    public static final String Calibri = "font/Calibri/calibri.ttf";
    public static final String OswaldBold = "font/Oswald/Oswald-Bold.ttf";


    public static Boolean isAppOpen = false;
    public static final String HeadlineArstr = "HeadLine_ar";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String isAppStart = "isStart";
    public static final String favSiteList = "favSitesList";
    public static final String NotificationCounter = "notificationCounter";
    public static final String Notification_on = "Notification_on";

    public static final String IndianExpress = "Indian Express";
    public static final String OneIndia = "OneIndia";
    public static final String IndiaTV = "India TV";
    public static final String IBNLive = "IBNLive";
    public static final String DeccanHerald = "Deccanherald";
    public static final String TimesOfIndia = "Times Of India";
    public static final String HindustanTimes = "Hindustan Times";
    public static final String TheStatesman = "The Statesman";
    public static final String IndiaToday = "India Today";
    public static final String OutlookIndia = "Outlook India";
    public static final String AsiaAge = "Asia Age";
    public static final String CNET = "CNET";
    public static final String CNN_IBN = "CNN-IBN";
    public static final String DeccanChronicle = "Deccan Chronicle";
    public static final String DNAIndia = "DNA India";
    public static final String EconomicTimes = "Economic Times";
    public static final String Firstpost = "Firstpost";
    public static final String FreePressJournal = "Free Press Journal";
    public static final String HinduBusinessLine = "Hindu Business Line";
    public static final String InfoWorld = "InfoWorld";
    public static final String Livemint = "Livemint";
    public static final String Moneycontrol = "Moneycontrol";
    public static final String Moneylife = "Moneylife";
    public static final String mydigtalfc = "mydigtalfc";
    public static final String RediffNews = "Rediff News";
    public static final String TechCrunch = "TechCrunch";
    public static final String TechRepublic = "TechRepublic";
    public static final String WiredNews = "Wired News";

    private static Map<String, Integer> DeccanHeraldID = new HashMap<>();

    public static final String HeadlineURL = "http://indianexpress.com/section/india/feed/";
    public static SportChildSceneBean search_item = null;
    public static int getDeccanHeraldID(String site) {
        System.out.println("__________________THE SITE IS : " + site);
        DeccanHeraldID.put(Category_list[1], 158);
        DeccanHeraldID.put(Category_list[3], 70);
        DeccanHeraldID.put(Category_list[4], 161);
        DeccanHeraldID.put(Category_list[6], 76);
        DeccanHeraldID.put(Category_list[7], 133);
        DeccanHeraldID.put(Category_list[8], 128);
        return DeccanHeraldID.get(site);
    }

    static SharedPreferences.Editor prefsEditor;
    public static boolean fromReceiver = false;

    public static void setIsBack(boolean isBack) {
        Helper.isBack = isBack;
    }

    public static boolean isImageLoaded = false;
    private static boolean isBack = false;
    public static int backIndex = 0;
    private static Semaphore semaphore = new Semaphore(1);
    public static Boolean checkInternetConnection(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
    public static void setOnitemTapInterstitial(Activity activity){
        if(Helper.MainContent_tap_count%3==0){
            if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
                System.out.println("Appodeal ad is loaded");
            } else {
                System.out.println("Appodeal ad is _not_ loaded");
            }
            Appodeal.onResume(activity,Appodeal.INTERSTITIAL);
            Appodeal.show(activity, Appodeal.INTERSTITIAL);
            System.out.println("________________Appodeal ad will be shown hear_______________");
        }
        System.out.println("________________Appodeal counter is : " + Helper.MainContent_tap_count);
        Helper.MainContent_tap_count++;
    }
    public static void refreshData(final Activity activity,final TextView description1){
        AdLoader.Builder builder = new AdLoader.Builder(activity.getApplicationContext(), "ca-app-pub-3940256099942544/2247696110");
        System.out.println("THe Bulder is :::::::::::::::::::: \n" + builder);
        //if (requestAppInstallAds) {
        builder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
            @Override
            public void onAppInstallAdLoaded(NativeAppInstallAd ad) {
                System.out.println("________________THE AD IS LOADED__________");
                /*NativeAppInstallAdView adView = (NativeAppInstallAdView) getLayoutInflater()
                        .inflate(R.layout.adinstalllistview_item, null);*/
                NativeAppInstallAdView adView = (NativeAppInstallAdView) activity.findViewById(R.id.adinstalllistview_item_view);
                adView.setVisibility(View.VISIBLE);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.BELOW,R.id.adinstalllistview_item_view);
                description1.setLayoutParams(layoutParams);
                activity.findViewById(R.id.adcontentlistview_item_view).setVisibility(View.GONE);
                populateInstallAdView(ad, adView);

            }
        }).forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd ad) {
                System.out.println("________________THE AD IS LOADED__________");
                /*NativeContentAdView adView = (NativeContentAdView) getLayoutInflater()
                        .inflate(R.layout.adcontentlistview_item, null);*/
                NativeContentAdView adView = (NativeContentAdView) activity.findViewById(R.id.adcontentlistview_item_view);
                adView.setVisibility(View.VISIBLE);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.BELOW,R.id.adcontentlistview_item_view);
                description1.setLayoutParams(layoutParams);
                activity.findViewById(R.id.adinstalllistview_item_view).setVisibility(View.GONE);
                populateContentAdView(ad, adView);

            }
        });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                System.out.println("__________________________THE AD LOADING FAILED");
            }
        }).build();
        System.out.println("_______________AD LOADER IS _____________"+adLoader);
        adLoader.loadAd(new AdRequest.Builder().build());
    }
    private static void populateContentAdView(NativeContentAd ad,NativeContentAdView adView){
        adView.setHeadlineView(adView.findViewById(R.id.tvHeader));
        adView.setBodyView(adView.findViewById(R.id.tvDescription));
        adView.setAdvertiserView(adView.findViewById(R.id.tvAdvertiser));
        adView.setLogoView(adView.findViewById(R.id.ivLogo));
        adView.setCallToActionView(adView.findViewById(R.id.btnAction));
        adView.setImageView(adView.findViewById(R.id.ivImage));

        ((TextView)adView.getHeadlineView()).setText(ad.getHeadline());
        ((TextView)adView.getBodyView()).setText(ad.getBody());
        ((TextView)adView.getAdvertiserView()).setText(ad.getAdvertiser());

        if(ad.getLogo()!=null)
            ((ImageView) adView.getLogoView()).setImageDrawable(ad.getLogo().getDrawable());

        ((Button) adView.getCallToActionView()).setText(ad.getCallToAction());

        if(ad.getImages()!=null && ad.getImages().size()>0)
            ((ImageView) adView.getImageView()).setImageDrawable(ad.getImages().get(0).getDrawable());
        NativeAd.Image logoImage = ad.getLogo();
        if(logoImage != null){
            ((ImageView) adView.getLogoView()).setImageDrawable(logoImage.getDrawable());
        }
        adView.setNativeAd(ad);
    }
    private static void populateInstallAdView(NativeAppInstallAd ad,NativeAppInstallAdView adView){
        adView.setHeadlineView(adView.findViewById(R.id.tvHeader));
        adView.setBodyView(adView.findViewById(R.id.tvDescription));
        //adView.setStarRatingView(adView.findViewById(R.id.install_ad_ratingbar));
        adView.setIconView(adView.findViewById(R.id.ivLogo));
        adView.setCallToActionView(adView.findViewById(R.id.btnAction));
        adView.setStoreView(adView.findViewById(R.id.tvStore));
        adView.setPriceView(adView.findViewById(R.id.tvPrice));
        adView.setImageView(adView.findViewById(R.id.ivImage));

        ((TextView)adView.getHeadlineView()).setText(ad.getHeadline());
        ((TextView)adView.getBodyView()).setText(ad.getBody());
        ((Button)adView.getCallToActionView()).setText(ad.getCallToAction());
        //((RatingBar)adView.getStarRatingView()).setRating(ad.getStarRating().floatValue());
        if(ad.getIcon()!=null)
            ((ImageView)adView.getIconView()).setImageDrawable(ad.getIcon().getDrawable());
        ((TextView)adView.getStoreView()).setText(ad.getStore());
        ((TextView)adView.getPriceView()).setText(ad.getPrice());
        if(ad.getImages()!=null && ad.getImages().size()>0)
            ((ImageView)adView.getImageView()).setImageDrawable(ad.getImages().get(0).getDrawable());

        adView.setNativeAd(ad);
    }
    public static void setAppodealcallback(Activity activity){
        adActivity = activity;
        Appodeal.setBannerViewId(R.id.contentadLayout);
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded() {
                try {
                    View v =adActivity.findViewById(R.id.contentadLayout);
                    BannerView.MarginLayoutParams param = (BannerView.MarginLayoutParams) v.getLayoutParams();
                    param.setMargins(0,0,0,0);
                    v.setLayoutParams(param);
                    Appodeal.show(adActivity, Appodeal.BANNER_VIEW);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onBannerFailedToLoad() {
                System.out.println("___________ad failed to load");
                try {
                    View v =adActivity.findViewById(R.id.contentadLayout);
                    BannerView.MarginLayoutParams param = (BannerView.MarginLayoutParams) v.getLayoutParams();
                    param.setMargins(0,0,0,-v.getHeight());
                    v.setLayoutParams(param);

                    //Appodeal.hide(adActivity, Appodeal.BANNER_VIEW);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onBannerShown() {
                System.out.println("_________IN MainContent banner shown is called____________");
            }

            @Override
            public void onBannerClicked() {

            }
        });
    }
    public static void initAppodeal(Activity activity){
        adActivity = activity;
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(activity, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER);
        //Appodeal.setAutoCache(Appodeal.INTERSTITIAL | Appodeal.BANNER, true);
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded() {
                try {
                    /*adActivity.findViewById(R.id.adLayout).setVisibility(View.INVISIBLE);*/
                    Appodeal.show(adActivity, Appodeal.BANNER_VIEW);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBannerFailedToLoad() {
                System.out.println("___________ad failed to load");
                try {
                    /*adActivity.findViewById(R.id.adLayout).setVisibility(View.GONE);*/
                    Appodeal.hide(adActivity, Appodeal.BANNER_VIEW);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onBannerShown() {

            }

            @Override
            public void onBannerClicked() {

            }
        });
    }

    public static void initAppodealBanner(Activity activity){
        if(activity.getClass().getName().equals(MainActivity.class.getName()) || activity.getClass().getName().equals(SplashActivity.class.getName()) || activity.getClass().getName().equals(ShowContentPage.class.getName())) {
            initAppodeal(activity);
            if(activity.getClass().getName().equals(MainActivity.class.getName()) ){
                try{
                    Appodeal.setBannerViewId(R.id.adLayout);
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else if(activity.getClass().getName().equals(ShowContentPage.class.getName())){
                try{
                    Appodeal.setBannerViewId(R.id.webadLayout);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
        Appodeal.show(activity, Appodeal.BANNER_VIEW);
    }

    public static void rateApp(Activity activity){
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    public static Dialog getDialog(Context context){
        Dialog dialog = new Dialog(context);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loader);

        return dialog;
    }
    private LoadImage getLoadImage(Activity activity,Dialog dialog){
        return new LoadImage(activity,dialog);
    }
    /*private Load_Image getLoad_image(Activity activity){return new Load_Image(activity);}

    private Load_Image getLoad_image(Activity activity,Dialog dialog){return new Load_Image(activity,dialog);}

    private class Load_Image extends AsyncTask<String,String,Bitmap>{
        Activity activity;
        Dialog dialog;
        String massage;
        public Load_Image(Activity activity){
            this.activity = activity;
        }
        public Load_Image(Activity activity , Dialog dialog){
            this.activity = activity;
            this.dialog = dialog;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                Bitmap image = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                this.massage = params[1];
                return image;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            try{
                File f = new File(Environment.getExternalStorageDirectory().toString()+"/"+"NewsCircleAppData");
                if(!f.exists()){
                    System.out.println("***************************");
                    System.out.println("I am creating new File hear");
                    System.out.println("***************************");
                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);

                dialog.dismiss();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, massage);
                shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));  //optional//use this when you want to send an image
                shareIntent.setType("image/jpeg");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                activity.startActivity(Intent.createChooser(shareIntent, "send"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/
    private class LoadImage extends AsyncTask<String,String,Bitmap>{
        Activity activity;
        Dialog dialog;
        String massage;
        public LoadImage(Activity activity,Dialog dialog){
            this.activity = activity;
            this.dialog = dialog;
        }
        @Override
        protected Bitmap doInBackground(String[] params) {
            try{
                Bitmap image = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                this.massage = params[1];
                return image;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap o) {
            super.onPostExecute(o);
            try{
                File f = new File(Environment.getExternalStorageDirectory().toString()+"/"+"NewsCircleAppData");
                if(!f.exists()){
                    System.out.println("***************************");
                    System.out.println("I am creating new File hear");
                    System.out.println("***************************");
                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                o.compress(Bitmap.CompressFormat.JPEG, 100, fout);



                PackageManager pm = activity.getPackageManager();

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");

                Intent emailIntent = new Intent();
                emailIntent.setAction(Intent.ACTION_SEND);
                // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
                emailIntent.putExtra(Intent.EXTRA_TEXT, "1");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "2");
                emailIntent.setType("message/*");
                Intent openInChooser = Intent.createChooser(emailIntent, "Share image via...");

                List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                for ( int i = 0 ; i < resInfo.size() ; i++ ) {
                    ResolveInfo ri = resInfo.get(i);
                    String packageName = ri.activityInfo.packageName;
                    /*if(packageName.contains("android.email")) {
                        emailIntent.setPackage(packageName);
                    }
                    else */if(packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")){
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                        if(packageName.contains("facebook")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, massage);
                           /* intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));  //optional//use this when you want to send an image
                            intent.setType("image/jpeg");
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);*/
                        }
                        else{
                            intent.putExtra(Intent.EXTRA_TEXT, massage);
                            String path = f.getAbsolutePath();
                            System.out.println("_____THE image path is : " + o + "\n" + path);
                            Uri screenshotUri = Uri.fromFile(f);
                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                            intent.setType("image/*");
                        }
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }
                    else{
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                        intent.putExtra(Intent.EXTRA_TEXT, massage);
                        String path = f.getAbsolutePath();
                        System.out.println("_____THE image path is : " + o + "\n" + path);
                        Uri screenshotUri = Uri.fromFile(f);
                        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        intent.setType("image/*");
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));

                    }

                }

                LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

                openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                dialog.dismiss();
                activity.startActivity(openInChooser);

            }catch(Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static Toolbar main_content_toolbar(Activity activity , String title){
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.main_content_toolbar);
        toolbar.setTitle(title);
        return toolbar;
    }
    public static String getmassage(String Title , String postId){
        return Title+"\nmore at... " +
                "\n"+postId+
                "\n Via NewsCircle" +
                "\nDownload Now " +
                "\n"+Helper.ApplicationURL;
    }
    public static void shareApp(Activity activity , String url , String massage, Dialog dialog){
        if(url==null){
            url = "https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-xpf1/v/t1.0-9/12310630_939978756083041_5343950249935239338_n.jpg?oh=975acdafac5ec9986eaf652b5512555f&oe=56D8A3D1&__gda__=1457547451_e1f121068e434c4ffbf43a1a2c9a7335";
        }
        if(massage == null){
            massage = "Stay updated with breaking News Via NewsCircle\n" +
                    "Download Now \n"+Helper.ApplicationURL;
        }
        //optional //internal storage
        helper.getLoadImage(activity, dialog).execute(url,massage);
    }
    public static void shareApp(Activity activity , String url , String massage) {
        if(url==null){
            url = "https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-xpf1/v/t1.0-9/12310630_939978756083041_5343950249935239338_n.jpg?oh=975acdafac5ec9986eaf652b5512555f&oe=56D8A3D1&__gda__=1457547451_e1f121068e434c4ffbf43a1a2c9a7335";
        }
        if(massage == null){
            massage = "Stay updated with breaking News Via NewsCircle\n" +
                    "Download Now \n"+Helper.ApplicationURL;
        }
          //optional //internal storage
        //helper.getLoad_image(activity).execute(url,massage);
        //helper.getLoadImage(activity).execute(url, massage);
    }
    public static void showDialog(Dialog dialog){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }
    public static void clearSharedPreference(SharedPreferences mPrefs) {
        prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public static void setIsAppStart(SharedPreferences mPrefs, boolean appStart) {
        prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(isAppStart, appStart);
        prefsEditor.commit();
    }

    public static int getNotificationCounter(SharedPreferences mPrefs) {
        return mPrefs.getInt(NotificationCounter, 0);
    }

    public static void setNotificationStatus(SharedPreferences mPrefs, Boolean status) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(Notification_on, status);
        prefsEditor.commit();
    }

    public static Boolean getNotificationStatus(SharedPreferences mPrefs) {
        return mPrefs.getBoolean(Notification_on, true);
    }

    public static void setNotificationCounter(SharedPreferences mPrefs, int counter) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putInt(NotificationCounter, counter);
        prefsEditor.commit();
    }

    public static Boolean getIsAppStart(SharedPreferences mPrefs) {
        Boolean appStart = mPrefs.getBoolean(isAppStart, false);
        return appStart;
    }

    public static void setFavSiteAr(SharedPreferences mPrefs, ArrayList ar) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putStringSet(favSiteList, new HashSet<String>(ar));
        prefsEditor.commit();
    }

    public static ArrayList<String> getFavSiteAr(SharedPreferences mPrefs) {
        ArrayList ar;
        try{
            ar  = new ArrayList(mPrefs.getStringSet(favSiteList, null));
        }catch(Exception e){
            ar = new ArrayList();
        }
        return ar;
    }

    public static void WriteHeadLineData(SharedPreferences mPrefs, int web_index, int cat_index, String data) {
        try {
            Gson gson = new Gson();
            SharedPreferences.Editor prefsEditor = mPrefs.edit();

            Type type = new TypeToken<String[][]>() {
            }.getType();
            String json = mPrefs.getString(HeadlineArstr, "");
            String headlineAr[][];

            if (json.equals("")) {
                System.out.println("_________The array is null json = line no 90");
                headlineAr = new String[29][10];
            } else {
                System.out.println("_______Line no 94____the array is not null json = " + json);
                headlineAr = gson.fromJson(json, String[][].class);
            }

            headlineAr[web_index][cat_index] = data;
            json = gson.toJson(headlineAr);
            prefsEditor.putString(HeadlineArstr, json);
            prefsEditor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String ReadHeadLineData(SharedPreferences mPrefs, int web_index, int cat_index) {

        Gson gson = new Gson();
        Type type = new TypeToken<String[][]>() {
        }.getType();
        String json = mPrefs.getString(HeadlineArstr, "");
        String headAr[][] = gson.fromJson(json, String[][].class);
        return headAr[web_index][cat_index];
    }

    public static String getCurrentURL() {
        String s = null;
        try {
            semaphore.acquire();
            s = CurrentURL;
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }


    public static void setCurrentURL(String currentURL) {
        try {
            semaphore.acquire();
            CurrentURL = currentURL;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static String CurrentURL;

    public static int getCid() {
        return cid;
    }

    public static void setCid(int cid) {
        Helper.cid = cid;
    }

    private static int cid;

    public static String getCategory() {
        return Category;
    }

    public static void setCategory(String category) {
        Category = category;
    }

    public static String classPath = "com.protoplus.newscircle.DrawerElements.activity.DrawerFragments.";

    private static String Category;
    public static String Category_list[] = {"Headline", "World", "Local And States", "National", "Entertainment", "Business", "Sports", "Science And Technology", "LifeStyle And Health", "Education"};
    public static final ArrayList<String> Category_ar_list = new ArrayList<>(Arrays.asList(Category_list));

    public static int entertainmentIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small, R.drawable.indiatv, R.drawable.ibnlive, R.drawable.dh, R.drawable.the_statesman};
    // TODO removing Hindustan Times
    public static String entertainmentItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia + "," + IndiaTV + "," + IBNLive + "," + DeccanHerald + "," + TheStatesman;
    //public static String entertainmentItems=IndianExpress+", "+OneIndia+", India TV, IBNLive, deccanherald.com, Times Of India, Hindustan Time, thestatesman.com";
    public static int worldFragmentIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small, R.drawable.indiatv, R.drawable.ibnlive, R.drawable.outlook_india, R.drawable.dh, R.drawable.the_statesman, R.drawable.asian_age};
    public static int nationalFragmentIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small, R.drawable.indiatv, R.drawable.ibnlive, R.drawable.outlook_india, R.drawable.dh,  R.drawable.the_statesman, R.drawable.asian_age, R.drawable.dna};

    //public static String worldFragmentItems = IndianExpress + "," + IndiaToday + "," + OneIndia + "," + IndiaTV + "," + OutlookIndia + "," + IBNLive + "," + DeccanHerald + "," + TimesOfIndia + "," + TheStatesman + "," + AsiaAge;
    public static String worldFragmentItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia + "," + IndiaTV + "," + IBNLive + "," + OutlookIndia + "," + DeccanHerald + "," + TheStatesman + "," + AsiaAge ;

    //public static String worldFragmentItems=IndianExpress+",Indiatoday.in,"+OneIndia+",India TV,outlookindia.com,IBN Live,deccanherald.com,Times Of India,Hindustan Time,thestatesman.com";
    public static String nationalFragmentItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia + "," + IndiaTV + "," + IBNLive + "," + OutlookIndia + "," + DeccanHerald + "," +  TheStatesman + "," + AsiaAge + "," + DNAIndia;

    public static int LocalAndStatesIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.the_statesman, R.drawable.asian_age};
    public static String LocalAndStateItems = IndianExpress + "," + TimesOfIndia + "," + TheStatesman + "," + AsiaAge;

    public static int BusinessIcons[] = {R.drawable.toi, R.drawable.et, R.drawable.ibnlive, R.drawable.moneycontrol, R.drawable.the_statesman, R.drawable.livemint, R.drawable.asian_age, R.drawable.firstpost, R.drawable.moneylife, R.drawable.rediff, R.drawable.my_digital_fc, R.drawable.outlook_india};
    public static String BusinessItems = TimesOfIndia + "," + EconomicTimes + "," + IBNLive + "," + Moneycontrol + "," + TheStatesman + "," + Livemint + "," + AsiaAge + "," + Firstpost + "," + Moneylife + "," + RediffNews + "," + mydigtalfc + "," + OutlookIndia;
//public static String BusinessItems="moneylife.in,Live mint,My Digital FC,Asia Age,Firstpost,Rediff,Money Control,outlookindia.com,IBN Live,Times Of India,thestatesman.com,Economics times";

    public static int SportsIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small, R.drawable.indiatv, R.drawable.ibnlive, R.drawable.outlook_india, R.drawable.dh, R.drawable.the_statesman, R.drawable.firstpost
            , R.drawable.india_today, R.drawable.deccanchronicle};
    public static String SportsItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia + "," + IndiaTV + "," + IBNLive + "," + OutlookIndia + "," + DeccanHerald + "," + TheStatesman + "," + Firstpost + "," + IndiaToday + "," + DeccanChronicle;
//public static String SportsItems =  IndianExpress+",Indiatoday.in,"+OneIndia+",India TV,outlookindia.com,IBN Live,deccanherald.com,Times Of India,Hindustan Time,thestatesman.com,FirstPost";

    public static int ScienceIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.ibnlive, R.drawable.cnet, R.drawable.the_statesman

            , R.drawable.techcrunch
            , R.drawable.infoworld
            , R.drawable.techreplublic
            , R.drawable.wired
            , R.drawable.businessline
            , R.drawable.dh};
    public static String ScienceItems = IndianExpress + "," + TimesOfIndia + "," + IBNLive + "," + CNET + "," + TheStatesman + "," + TechCrunch + "," + InfoWorld + "," + TechRepublic + "," + WiredNews + "," + HinduBusinessLine + "," + DeccanHerald;
//public static String ScienceItems = IndianExpress+",IBN Live,deccanherald.com,Times Of India,thestatesman.com,FreePress Journal,HinduBusiness Line,CNET,TechCrunch,InfoWorld,TechRepublic,Wired";

    public static int LifeStyleIcons[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small, R.drawable.dna, R.drawable.ibnlive, R.drawable.deccanchronicle
            , R.drawable.the_statesman

            , R.drawable.asian_age
            , R.drawable.dh};

    public static String LifeStyleItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia + "," + DNAIndia + "," + IBNLive + "," + DeccanChronicle + "," + TheStatesman + "," + AsiaAge + "," + DeccanHerald;
//public static String LifeStyleItems = IndianExpress+","+OneIndia+",IBNLive,DeccanHerald,Times Of India,The Statesman,Asian Age,CNN IBN,DNA India,Deccan Chronicle";

    public static String EducationItems = IndianExpress + "," + TimesOfIndia + "," + OneIndia;
//public static String EducationItems = IndianExpress+","+OneIndia+",Times Of India";

    public static int Education[] = {R.drawable.ie, R.drawable.toi, R.drawable.oneindia_small};
    private static final ArrayList<SportChildSceneBean> itemList = new ArrayList<SportChildSceneBean>();

    public static ArrayList<SportChildSceneBean> getItemList() {
        return itemList;
    }

    private static Sports.SportsHolder bean;

    public static Sports.SportsHolder getBean() {
        return bean;
    }

    public static void setBean(Sports.SportsHolder bean) {
        Helper.bean = bean;
    }

    public static int pagerItemIndex = 0;
    public static int favPagerItemIndex = 0;
    public static int drawerItemIcon[] = {R.drawable.headline
            , R.drawable.fevorite_icon
            , R.drawable.set_notification
            , R.drawable.headline
            , R.drawable.rate_us
            , R.drawable.share_us
            , R.drawable.about_us
            };
    public static boolean done = false;
    public static String IENationalTitle = "National,";
    public static String IndianExpressNationalURL = "http://indianexpress.com/section/india/feed/,";

    public static String HTNationalTitle = "National,";
    public static String HindustanTimesNationalURL = "http://feeds.hindustantimes.com/HT-India,";

    public static String TheStatesNationalManTitle = "National,";
    public static String TheStatesmanNationalURL = "http://www.thestatesman.com/feed.aspx?cat_id=6,";

    public static String OneIndiaNationalTitle = "National,";
    public static String OneIndiaNationalURL = "http://www.oneindia.com/rss/news-india-fb.xml,";

    public static String TOINationalTitle = "National,";
    public static String TimesOfIndiaNationalURL = "http://timesofindia.feedsportal.com/c/33039/f/533916/index.rss,";

    public static String IndiaTVNationalTitle = "National,";
    public static String IndiaTVNationalURL = "http://www.indiatvnews.com/rssfeed/india.xml,";

    public static String OutlookIndiaNationalTitle = "National,";
    public static String OutlookIndiaNationalURL = "http://feeds.feedburner.com/OutlookNational,";

    public static String IBNLiveNationalTitle = "National";
    public static String IBNLiveNationalURL = "http://m.ibnlive.com/india/";

    public static String IndiaTodayNationalTitile = "National,";
    public static String IndiaTodayNationalURL = "http://indiatoday.feedsportal.com/c/33614/f/589702/index.rss?http://indiatoday.intoday.in/rss/article.jsp?sid=36,";

    public static String DeccanHeraldNationalTitle = "National,";
    public static String DeccanheraldNationalURL = "http://www.deccanherald.com/more_category_news.php?page=0&cid=158,";

    public static String AsiaAgeNationalTitle = "National,";
    public static String AsiaAgeNationalURL = "http://www.asianage.com/rss/38,";

    public static String DNAIndiaNationalTitle = "National,";
    public static String DNAIndiaNationalURL = "http://www.dnaindia.com/syndication/rss,catID-2.xml,";

    public static String OutLookIndiaWorldTitle = "World,";
    public static String OutLookIndiaWorldURL = "http://feeds.feedburner.com/OutlookInternational?format=xml,";

    public static String IEWorldTitle = "World,";
    public static String IndianExpressWorldURL = "http://indianexpress.com/section/world/feed/,";

    public static String HTWorldTitle = "World,";
    public static String HindustanTimesWorldURL = "http://feeds.hindustantimes.com/HT-World,";

    public static String OneIndiaWorldTitle = "World,";
    public static String OneIndiaWorldURL = "http://www.oneindia.com/rss/news-international-fb.xml,";

    public static String TOIWorldTitle = "World,";
    public static String TimesOfIndiaWorldURL = "http://timesofindia.feedsportal.com/c/33039/f/533917/index.rss,";

    public static String IndiaTVWorldTitle = "World,";
    public static String IndiaTVWorldURL = "http://www.indiatvnews.com/rssfeed/world_news.xml,";

    public static String IndiaTodayWorldTitle = "World,";
    public static String IndiaTodayWorldURL = "http://indiatoday.feedsportal.com/c/33614/f/589705/index.rss?http://indiatoday.intoday.in/rss/article.jsp?sid=61,";

    public static String DeccanHeraldWorldTitle = "World,";
    public static String DeccanheraldWorldURL = "http://www.deccanherald.com/contents/158/international.html,";

    public static String TheStatesManWorldTitle = "World,";
    public static String TheStatesmanWorldURL = "http://www.thestatesman.com/feed.aspx?cat_id=2,";

    public static String IBNLiveWorldTitle = "World,";
    public static String IBNLiveWorldURL = "http://www.ibnlive.com/rss/world.xml,";

    public static String AsiaAgeWorldTitle = "World,";
    public static String AsiaAgeWorldURL = "http://www.asianage.com/rss/37,";

    public static String IELocalAndStatesTitle = "Delhi,Lucknow,Pune,Chandigarh,Mumbai,Kolkata,Ludhiana,Gujarat,Maharashtra,Uttar Pradesh " +
            ",West Bengal ,Punjab and Haryana";
    public static String IndianExpressLocalAndStatesURL = "http://www.indianexpress.com/section/cities/delhi/feed/," +
            "http://www.indianexpress.com/section/cities/lucknow/feed/," +
            "http://www.indianexpress.com/section/cities/pune/feed/," +
            "http://indianexpress.com/section/cities/chandigarh/feed/," +
            "http://www.indianexpress.com/section/cities/mumbai/feed/," +
            "http://www.indianexpress.com/section/cities/kolkata/feed/," +
            "http://indianexpress.com/section/cities/ludhiana/feed/," +
            "http://indianexpress.com/section/india/education/feed/," +
            "http://indianexpress.com/section/india/maharashtra/feed/," +
            "http://indianexpress.com/section/india/uttar-pradesh/feed/," +
            "http://indianexpress.com/section/india/west-bengal/feed/," +
            "http://indianexpress.com/section/india/punjab-and-haryana/feed/";

    public static String TimesOfIndiaLocalAndStatesTitle = "Mumbai,Delhi,Bangalore,Hyderabad,Chennai," +
            "Ahemdabad,Allahabad,Bhubaneswar,Coimbatore,Gurgaon," +
            "Guwahati,Hubli,Kanpur,Kolkata,Ludhiana," +
            "Mangalore,Mysore,Noida,Pune,Goa," +
            "Chandigarh,Lucknow,Patna,Jaipur,Nagpur," +
            "Rajkot,Ranchi,Surat,Vadodara,Varanasi," +
            "Thane,Thiruvananthapuram";
    public static String TimesOfIndiaLocalAndStatesURL = "http://timesofindia.feedsportal.com/c/33039/f/533975/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533976/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533977/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533978/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533979/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533980/index.rss," +
            "http://timesofindia.indiatimes.com/rssfeeds/3947060.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/4118235.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/7503091.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/6547154.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/4118215.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942695.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3947067.cms," +
            "http://timesofindia.feedsportal.com/c/33039/f/533981/index.rss," +
            "http://timesofindia.indiatimes.com/rssfeeds/3947051.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942690.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942693.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/8021716.cms," +
            "http://timesofindia.feedsportal.com/c/33039/f/533982/index.rss," +
            "http://timesofindia.indiatimes.com/rssfeeds/3012535.cms," +
            "http://timesofindia.feedsportal.com/c/33039/f/533984/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533985/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533986/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533987/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533988/index.rss," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942663.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/4118245.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942660.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3942666.cms," +
            "http://timesofindia.indiatimes.com/rssfeeds/3947071.cms," +
            "http://timesofindia.feedsportal.com/c/33039/f/533989/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533990/index.rss";

    public static String IndiaTodayLocalAndStatesTitle = "Local News,";
    public static String IndiaTodayLocalAndStatesURL = "http://indiatoday.feedsportal.com/c/33614/f/589703/index.rss?http://indiatoday.intoday.in/rss/article.jsp?sid=21,";

    public static String DeccanheraldLocalAndStatesTitle = "Local News,";
    public static String DeccanheraldLocalAndStatesURL = "http://www.deccanherald.com/rss/state.rss,";

    public static String TheStatesmanLocalAndStatesTitle = "Bengal,Oddisa,Delhi,Northeast,Bihar";
    public static String TheStatesmanLocalAndStatesURL = "http://www.thestatesman.com/feed.aspx?cat_id=10," +
            "http://www.thestatesman.com/feed.aspx?cat_id=429," +
            "http://www.thestatesman.com/feed.aspx?cat_id=430," +
            "http://www.thestatesman.com/feed.aspx?cat_id=467," +
            "http://www.thestatesman.com/feed.aspx?cat_id=474";

    public static String AsiaAgeLocalAndStatesTitle = "Delhi,Kolkatta,Mumbai";
    public static String AsiaAgeLocalAndStatesURL = "http://www.asianage.com/rss/40," +
            "http://www.asianage.com/rss/41," +
            "http://www.asianage.com/rss/42";

    /*=================================================Entertainment Data========================================*/
    public static String IEEntertainmentTitle = "Entertainment,Movie Reviews ,Reviews: MUSIC ";
    public static String IndianExpressEntertainmentURL = "http://indianexpress.com/section/entertainment/feed/," +
            "http://indianexpress.com/section/entertainment/movie-review/feed/," +
            "http://indianexpress.com/print/reviews-music/feed/";
    public static String HTEntertainmentTitle = "Entertainment,Bollywood,Hollywood,Reviews,Music,Tabloid";
    public static String HindustanTimesEntertainmentURL = "http://feeds.hindustantimes.com/HT-Entertainment," +
            "http://feeds.hindustantimes.com/HT-Bollywood," +
            "http://feeds.hindustantimes.com/HT-Hollywood," +
            "http://feeds.hindustantimes.com/HT-Reviews-News," +
            "http://feeds.hindustantimes.com/HT-Music," +
            "http://feeds.hindustantimes.com/HT-Tabloid";
    public static String OneIndiaEntertainmentTitle = "Music,Bollywood,Telgu Film,Tevelision,Tamil,Hollywood,Malayalam,Kannada";
    public static String OneIndiaEntertainmentURL = "http://www.filmibeat.com/rss/entertainment-music-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-bollywood-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-telugu-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-television-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-tamil-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-hollywood-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-malayalam-fb.xml," +
            "http://www.filmibeat.com/rss/filmibeat-kannada-fb.xml";
    public static String TheStatesManEntertainmentTitle = "thestatesman,";
    public static String TheStatesmanEntertainmentURL = "http://www.thestatesman.com/feed.aspx?cat_id=468,";
    public static String TOIEntertainmentTitle = "Times Of India,";
    public static String TimesOfIndiaEntertainmentURL = "http://timesofindia.feedsportal.com/c/33039/f/533928/index.rss,";

    public static String IndiaTVEntertainmentTitle = "Entertainment,Hollywood,Bollywood,Movie Review";
    public static String IndiaTVEntertainmentURL = "http://www.indiatvnews.com/rssfeed/masala_entertainment_news.xml," +
            "http://www.indiatvnews.com/rssfeed/masala_hollywood_news.xml," +
            "http://www.indiatvnews.com/rssfeed/masala_bollywood_news.xml," +
            "http://www.indiatvnews.com/rssfeed/movie-reviews.xml";

    public static String IBNLiveEntertainmentTitle = "Entertainment";
    public static String IBNLiveEntertainmentURL = "http://m.ibnlive.com/movies/";

    public static String DeccanheraldEntertainmentTitle = "Entertainment News";
    /*+
    ",Movie Review";*/
    public static String DeccanheraldEntertainmentURL = "http://www.deccanherald.com/rss/entertainment.rss,";
            /*	+
        "http://www.deccanherald.com/rss/movie-reviews.rss";
*/
/*================================================Business Data==================================================*/

    public static String TOIBusinessTitle = "Times Of India,";
    public static String TimesOfIndiaBusinessURL = "http://timesofindia.feedsportal.com/c/33039/f/533919/index.rss,";

    public static String TheStatesManBusinessTitle = "thestatesman,";
    public static String TheStatesmanBusinessURL = "http://www.thestatesman.com/feed.aspx?cat_id=3,";

    public static String MoneyLifeBusinessTitle = "Money Life,";
    public static String MoneylifeBusinessURL = "http://www.moneylife.in/site/rss.php,";

    public static String LiveMintBusinessTitle = "Top Stories,Companies,Consumer,Opinion,Money,Industry,Economy Politics,Lounge";
    public static String LivemintBusinessURL = "http://www.livemint.com/rss/homepage," +
            "http://www.livemint.com/rss/companies," +
            "http://www.livemint.com/rss/consumer," +
            "http://www.livemint.com/rss/opinion," +
            "http://www.livemint.com/rss/money," +
            "http://www.livemint.com/rss/industry," +
            "http://www.livemint.com/rss/economy_politics," +
            "http://www.livemint.com/rss/lounge";

    public static String MyDigitalFCBusinessTitle = "My Digital FC,";
    public static String mydigtalfcBusinessURL = "http://www.mydigitalfc.com/rss.xml,";

    public static String AsiaAgeBusinessTitle = "AsiaAge,";
    public static String AsiaAgeBusinessURL = "http://dc.asianage.com/rss/43,";

    public static String FirstPostBusinessTitle = "FirstPost,";
    public static String FirstpostBusinessURL = "http://www.firstpost.com/business/feed,";

    public static String OutlookIndiaBusinessTitle = "Business,";
    public static String OutlookIndiaBusinessURL = "http://feeds.feedburner.com/outlookindia/dvyO?format=xml,";

    public static String RediffBusinessTitle = "Rediff,";
    public static String RediffNewsBusinessURL = "http://www.rediff.com/rss/moneyrss.xml,";

    public static String MoneyControlBusinessTitle = "business,economy";
    public static String MoneycontrolBusinessURL = "http://www.moneycontrol.com/rss/business.xml," +
            "http://www.moneycontrol.com/rss/economy.xml";

    public static String EconomicsTimesBusinessTitle = "business,economy";
    public static String EconomicTimesBusinessURL = "http://economictimes.indiatimes.com/rssfeeds/1373380680.cms," +
            "http://economictimes.indiatimes.com/rssfeeds/13352306.cms";

    /*public static String AsiaAgeBusinessTitle = "Business,";
    public static String AsiaAgeBusinessURL = "http://www.asianage.com/rss/43,";*/
	/*================================================Sports Data==================================================*/
    public static String IndianExpressSportsTitle = "Latest News,Cricket,Football,Tennis,Motor Sports,Golf,Hockey,Other sports";
    public static String IndianExpressSportsURL = "http://indianexpress.com/section/sports/feed/," +
            "http://indianexpress.com/section/sports/cricket/feed/," +
            "http://www.indianexpress.com/sports/football/feed/," +
            "http://indianexpress.com/section/sports/tennis/feed/," +
            "http://indianexpress.com/section/sports/motor-sport/feed/," +
            "http://indianexpress.com/section/sports/golf/feed/," +
            "http://indianexpress.com/section/sports/hockey/feed/," +
            "http://indianexpress.com/section/sports/sport-others/feed/";

    public static String IndiaTodaySportsTitle = "Indiatoday.in,";
    public static String IndiaTodaySportsURL = "http://indiatoday.feedsportal.com/c/33614/f/589706/index.rss?http://indiatoday.intoday.in/rss/article.jsp?sid=84,";

    public static String OneIndiaSportsTitle = "Sports News,Cricket,Cricket-IPL";
    public static String OneIndiaSportsURL = "http://www.oneindia.com/rss/news-sports-fb.xml," +
            "http://www.thatscricket.com/rss/cricket-fb.xml," +
            "http://www.thatscricket.com/rss/cricket-ipl-fb.xml";

    public static String IndiaTvSportsTitle = "Cricket,Tennis,Hockey,Football,Other Sports";
    public static String IndiaTvSportsURL = "http://www.indiatvnews.com/rssfeed/sports_cricket_news.xml," +
            "http://www.indiatvnews.com/rssfeed/sports_tennis_news.xml," +
            "http://www.indiatvnews.com/rssfeed/sports_hockey_news.xml," +
            "http://www.indiatvnews.com/rssfeed/sports_soccer_news.xml," +
            "http://www.indiatvnews.com/rssfeed/sports_other_news.xml";

    public static String OutLookIndiaSportsTitle = "outlookindia.com,";
    public static String OutlookIndiaSportsURL = "http://feeds.feedburner.com/outlookindia/ZoJW?format=xml,";

    public static String IBNLiveSportsTitle = "Sprots,Hockey,Formula One";
    public static String IBNLiveSportsURL = "http://www.ibnlive.com/rss/sports.xml," +
            "http://www.ibnlive.com/rss/hockey.xml," +
            "http://www.ibnlive.com/rss/formula-one.xml";

    public static String DeccanHeraldSportsTitle = "deccanherald.com,";
    public static String DeccanheraldSportsURL = "http://www.deccanherald.com/rss/sports.rss,";

    public static String TimesOfIndiaSportsTitle = "Sports,Cricket";
    public static String TimesOfIndiaSportsURL = "http://timesofindia.feedsportal.com/c/33039/f/533921/index.rss," +
            "http://timesofindia.feedsportal.com/c/33039/f/533920/index.rss";

    public static String HindustanTimesSportsTitle = "Sports,Cricket,Football,Tennis,Other Sports";
    public static String HindustanTimesSportsURL = "http://feeds.hindustantimes.com/HT-Sport," +
            "http://feeds.hindustantimes.com/HT-cricket," +
            "http://feeds.hindustantimes.com/HT-Football," +
            "http://feeds.hindustantimes.com/HT-Tennis," +
            "http://feeds.hindustantimes.com/HT-Othersports";

    public static String TheStatesManSportsTitle = "thestatesman.com,";
    public static String TheStatesmanSportsURL = "http://www.thestatesman.com/feed.aspx?cat_id=5,";

    public static String FirstPostSportsTitle = "Firstpost,";
    public static String FirstpostSportsURL = "http://www.firstpost.com/sports/feed,";

    public static String DeccanChronicleSportsTitle = "Deccan Chronicle,";
    public static String DeccanChronicleSportsURL = "http://www.deccanchronicle.com/sports/cricket,";
    /*================================================Science Data==================================================*/
    public static String IndianExpressScienceTitle = IndianExpress + ",";
    public static String IndianExpressScienceURL = "http://indianexpress.com/section/technology/feed/,";

    public static String IBNLiveScienceTitle = "Science,Technology";
    public static String IBNLiveScienceURL = "http://www.ibnlive.com/rss/tech.xml," +
            "http://www.ibnlive.com/xml/rss/sci-tech.xml";

    public static String DeccanHeraldScienceTitle = "deccanherald.com,";
    public static String DeccanheraldScienceURL = "http://www.deccanherald.com/rss/science-technology.rss,";

    public static String TimesOfIndiaScienceTitle = "Science,Technology";
    public static String TimesOfIndiaScienceURL = "http://timesofindia.feedsportal.com/c/33039/f/533922/index.rss," +
            "http://timesofindia.indiatimes.com/rssfeeds/5880659.cms";

    public static String TheStatesManScienceTitle = "The Statesman,";
    public static String TheStatesmanScienceURL = "http://www.thestatesman.com/feed.aspx?cat_id=452,";

    public static String FreePressJournalScienceTitle = "Freepress Journal,";
    public static String FreePressJournalScienceURL = "http://www.freepressjournal.in/category/technology/feed/,";

    public static String HinduBusinessScienceTitle = "Hindu Businessline,";
    public static String HinduBusinessLineScienceURL = "http://www.thehindubusinessline.com/info-tech/?service=rss,";

    public static String CNETScienceTitle = "CNET,CNET Review,CNET IPhone Update,CNET Android Update";
    public static String CNETScienceURL = "http://www.cnet.com/rss/news/," +
            "http://www.cnet.com/rss/reviews/," +
            "http://www.cnet.com/rss/iphone-update/," +
            "http://www.cnet.com/rss/android-update/";

    public static String TechCrunchScienceTitle = "Tech Crunch,Startup,Mobile,Gadget,Gaming";
    public static String TechCrunchScienceURL = "http://feeds.feedburner.com/TechCrunch/," +
            "http://feeds.feedburner.com/techcrunch/startups?format=xml," +
            "http://feeds.feedburner.com/Mobilecrunch," +
            "http://feeds.feedburner.com/crunchgear," +
            "http://feeds.feedburner.com/TechCrunch/Gaming?format=xml";

    public static String InfoWorldScienceTitle = "Info World,";
    public static String InfoWorldScienceURL = "http://www.infoworld.com/index.rss,";

    public static String TechRepublicScienceTitle = "Tech Republic,";
    public static String TechRepublicScienceURL = "http://techrepublic.com.feedsportal.com/c/35463/f/670841/index.rss,";

    public static String WiredScienceTitle = "Wired,";
    public static String WiredNewsScienceURL = "http://www.wired.com/category/gear/feed/";

    public static String DeccanChronicleScienceTitle = "Deccan Chronicle,";
    public static String DeccanChronicleScienceURL = "http://www.deccanchronicle.com/rss/sports/rss.xml,";
    /*================================================  Lifestyle  ==============================================*/
    public static String IndianExpressLifestyleTitle = "LifeStyle,Fashion,Health";
    public static String IndianExpressLifeStyleURL = "http://indianexpress.com/section/lifestyle/life-style/feed/," +
            "http://indianexpress.com/section/lifestyle/fashion/feed/," +
            "http://www.indianexpress.com/section/lifestyle/health/feed/";
    public static String OneIndiaLifeStyleTitle = "Beauty,Cookery,Insync,Health,Home and Garden,Pregnancy Parenting,Relationship,Yoga Spirituality";
    public static String OneIndiaLifeStyleURL =
            "http://www.boldsky.com/rss/boldsky-beauty-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-cookery-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-insync-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-health-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-home-garden-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-pregnancy-parenting-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-relationship-fb.xml," +
                    "http://www.boldsky.com/rss/boldsky-yoga-spirituality-fb.xml";

    public static String IBNLiveLifeStyleTitle = "IBNLive,";
    public static String IBNLiveLifeStyleURL = "http://www.ibnlive.com/rss/lifestyle.xml,";

    public static String DeccanHeraldLifeStyleTitle = "Home Interiors,Living";
    public static String DeccanheraldLifeStyleURL =
            "http://www.deccanherald.com/rss/homes-interiors.rss," +
                    "http://www.deccanherald.com/rss/living.rss";
    /*second link is not active*/
    public static String TimesOfIndiaLifeStyleTitle = "Lifestyle,Health";
    public static String TimesOfIndiaLifeStyleURL = "http://timesofindia.feedsportal.com/c/33039/f/533929/index.rss," +
            "http://www.timesofindia.indiatimes.com/rssfeeds/3908999.cms";

    public static String TheStatesManLifeStyleTitle = "The Statesman,";
    public static String TheStatesmanLifeStyleURL = "http://www.thestatesman.com/feed.aspx?cat_id=453,";

    public static String AsianAgeLifeStyleTitle = "LifeStyle,";
    public static String AsiaAgeLifeStyleURL = "http://dc.asianage.com/rss/53,";

    public static String CNN_IBNLifeStyleTitle = "CNN IBN,";
    public static String CNN_IBNLifeStyleURL = "http://www.ibnlive.com/xml/rss/lifestyle.xml,";

    public static String DNAIndiaLifeStyleTitle = "DNA India,";
    public static String DNAIndiaLifeStyleURL = "http://www.dnaindia.com/feeds/health.xml,";

    public static String DeccanChronicleLifeStyleTitle = "Deccan Chronicle,";
    public static String DeccanChronicleLifeStyleURL = "http://www.deccanchronicle.com/lifestyle/health-and-wellbeing,";

    public static String IndianExpressEducationTitle = IndianExpress + ",";
    public static String IndianExpressEducationURL = "http://indianexpress.com/section/india/education/feed/,";

    public static String OneIndiaEducationTitle = "Admission,Education,Exam Time Table";
    public static String OneIndiaEducationURL = "http://www.careerindia.com/rss/education-admissions-fb.xml," +
            "http://www.careerindia.com/rss/education-news-fb.xml," +
            "http://www.careerindia.com/rss/education-exam-time-table-fb.xml";

    public static String TimesOfIndiaEducationTitle = "Times Of India";
    public static String TimesOfIndiaEducationURL = "http://timesofindia.feedsportal.com/c/33039/f/533924/index.rss,";

// TODO content taken from http://stackoverflow.com/questions/21164836/immersive-mode-navigation-becomes-sticky-after-volume-press-or-minimise-restore
    /*public static void setActivityUI(Activity activity) {
        subActivity = activity;
        subActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View decorView = setSystemUiVisilityMode(subActivity);
                decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        setSystemUiVisilityMode(subActivity); // Needed to avoid exiting immersive_sticky when keyboard is displayed
                    }
                });
            }
        });

    }

    public static View setSystemUiVisilityMode(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int options;
        options =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility(options);
        return decorView;
    }*/
    public  static void setSubActivityUI(Activity activity){
        subActivity = activity;
        subActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
//                     View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    /*subActivity.getWindow().getDecorView().setSystemUiVisibility(

                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    );*/

// clear FLAG_TRANSLUCENT_STATUS flag:
                    subActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                    subActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                    subActivity.getWindow().setStatusBarColor(subActivity.getResources().getColor(R.color.colorPrimaryDark));
                } catch (Error e) {
                    return;
                }
            }
        });
    }
    public static Drawable share_button_drawable(Activity activity){
        Drawable drawable = activity.getResources().getDrawable(R.drawable.share);
        int size = Math.round(activity.getResources().getDimension(R.dimen.dp16));
        drawable.setBounds(new Rect(0, 0, size,
                size));
        return drawable;
    }
    public static void setActivityUI(Activity activity) {
        subActivity = activity;
        subActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    /*| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY*/
                    subActivity.getWindow().getDecorView().setSystemUiVisibility(
                             View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    );
                } catch (Error e) {
                    return;
                }
            }
        });
    }
       /* activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY   );*/

}
