package com.protoplus.newscircle.newscircle.MainContent;

//import android.app.ProgressDialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.LayoutDirection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.MainActivity;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.Utils;
import com.protoplus.newscircle.newscircle.ShowContentPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aakash on 9/30/2015.
 */
public class HeadLineMainContent extends AppCompatActivity {


    TextView pubDate,contentTitle;
    AppCompatTextView description , description1;
    Typeface tthin,tbold;
    Activity activity = this;
    TextView more_at_text;
    AppCompatButton share;
    RelativeLayout link;
    Dialog dialog;
    LinearLayout divider;
    Context context;
    private boolean check_connectivity;
    Typeface tf;
    // TODO copy
    Boolean loadDescription = false;
    Toolbar toolbar;
    private void clearView(){
        contentTitle.setText("");
        description.setText("");
        description1.setText("");
        pubDate.setText("");
        mainImage.setImageDrawable(null);
        //refreshContent.setVisibility(View.INVISIBLE);
        link.setVisibility(View.INVISIBLE);
        share.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);
    }

    private Boolean NotificationFlag;
    LoadImage loadImage = new LoadImage();
    boolean insertItem =false;
    ImageView mainImage;
    Bitmap bitmap;
    public ContextData contextData;
    SportChildSceneBean sportBean;


    // TODO comment it
    //ProgressDialog pDialog;
    ProgressBar imageLoader;
    SportChildSceneBean bean,beanSport;
    Document doc;
    ArrayList<SportChildSceneBean> main_ar;
    ArrayList<SportChildSceneBean> refresh_ar = new ArrayList<SportChildSceneBean>();
    @Override
    public void onBackPressed() {
        if(NotificationFlag){
            Intent it = new Intent(HeadLineMainContent.this,MainActivity.class);
            startActivity(it);
        }
        else{
            super.onBackPressed();
            Helper.setIsBack(true);
        }

    }
    /*=====================================================================*/

    @Override
    protected void onStart() {
        super.onStart();
        Helper.setOnitemTapInterstitial(this);
    }

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);

        super.onRestart();
    }
/*======================================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        Intent it=getIntent();
        Helper.initAppodealBanner(this);
        this.context = getApplicationContext();
        contextData =(ContextData) getApplicationContext();
        Helper.setAppodealcallback(this);

        // TODO set action bar hear
        toolbar = Helper.main_content_toolbar(this,"The "+Helper.IndianExpress+" News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO copy it
        imageLoader  = (ProgressBar) findViewById(R.id.imageLoader);
        main_ar = Helper.getHeadlineBeanar();
        Bundle b = it.getExtras();
        NotificationFlag = b.getBoolean("NotificationFlag");
        Boolean search_flag = b.getBoolean("search_result_flag");
        if(search_flag){
            getIntent().putExtra("search_result_flag",false);
            search_flag = false;
            bean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
        }
        else if(!NotificationFlag) {
            int index = it.getExtras().getInt("index") - 1;
            if (index == -1) {
                //index=0;
                bean = Helper.header;
            } else {
                bean = main_ar.get(index);
            }
        }
        else{
            /*finish();
            startActivity(getIntent());*/
            bean = (SportChildSceneBean)it.getSerializableExtra("ItemObject");
        }

        sportBean=bean;
        try{
            System.out.println("The Post id is : "+sportBean.postId);
        }catch(NullPointerException nullpoint){
            System.out.println("Headline main content ma bean nathi________________");
            nullpoint.printStackTrace();
            /*HeadLines.getHeadLineInstance().startAlert();*/
            Helper.isAppOpen = false;
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        pubDate=(TextView) findViewById(R.id.pubDate);
        description=(AppCompatTextView) findViewById(R.id.mainDescription);
        description1 = (AppCompatTextView) findViewById(R.id.mainDescription1);
        contentTitle=(TextView) findViewById(R.id.contentTitle);

        tthin = Typeface.createFromAsset(getAssets(),Helper.LatoBlackThin);
        tbold = Typeface.createFromAsset(getAssets(),Helper.LatoBold);
        tf = Typeface.createFromAsset(getAssets(),Helper.LatoBlack);
        contentTitle.setTypeface(tbold);
        description.setTypeface(tf);
        description1.setTypeface(tf);
        pubDate.setTypeface(tf);

        more_at_text = (TextView) findViewById(R.id.more_at_text);
        link=(RelativeLayout) findViewById(R.id.link);

        // TODO change Button type from Appcompat Button
        share= (AppCompatButton)findViewById(R.id.share);
        share.setCompoundDrawables(Helper.share_button_drawable(this), null, null, null);

        divider = (LinearLayout) findViewById(R.id.divider);

        link.setVisibility(View.INVISIBLE);
        share.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);

        link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(HeadLineMainContent.this, ShowContentPage.class);
                it.putExtra("url", bean.postId);
                //MainContent.this.stopService(getIntent());
                startActivity(it);
            }
        });
        mainImage=(ImageView) findViewById(R.id.mainImage);

        // TODO hear i am setting no connection setting
        ScrollView content_scrollView = (ScrollView)findViewById(R.id.content_scrollView);
        RelativeLayout error_content = (RelativeLayout) findViewById(R.id.content_layout);
        AppCompatButton error_button = (AppCompatButton) findViewById(R.id.content_Retry);
        check_connectivity = Helper.checkInternetConnection(this);
        if(!check_connectivity){
            content_scrollView.setVisibility(View.GONE);
            error_content.setVisibility(View.VISIBLE);
            error_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(getIntent());
                }
            });
        }

        if (bean!=null) {
            if(bean.mainImageLink!=null){
                String []urls=bean.mainImageLink.split("#");

                try{
                    loadImage.execute(Utils.resizeImage(urls));
                }catch(Exception e){
                    e.printStackTrace();
                    new LoadDescription().execute("");
                }
            }
            else{
                if(bean.imageLink==null){
                    new LoadDescription().execute("");
                }
                else{
                    //loadImage = new LoadImage();
                    loadImage.execute(bean.imageLink);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        else if ( id == R.id.action_refresh){
            clearView();
            if(loadDescription){
                new LoadDescription().execute("");
            } else{
                new LoadImage().execute(sportBean.imageLink);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoadDescription extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // TODO comment it
            /*pDialog = new ProgressDialog(HeadLineMainContent.this);
            pDialog.setMessage("Loading Data...");
            pDialog.show();*/
            imageLoader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                doc = Jsoup.connect(bean.postId).get();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null){
                loadDescription = true;
            }
            try{
                System.out.println(":::::::::THE DOCUMENT IS :"+doc);
                Element desc= doc.select("div.full-details").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                for(Element string:divChildren){
                    sample = sample + string;
                }

                description.setText(Html.fromHtml(sample), TextView.BufferType.SPANNABLE);
            }catch(Exception e){
                String text=sportBean.description;
                String text_ar[]=text.split("<");
                text="";
                for (String a:text_ar) {
                    if(!a.contains("/>")){
                        text=text+a;
                    }
                }
                description.setText(text);
                description.setTextColor(Color.BLACK);
            }
            mainImage.setVisibility(View.VISIBLE);
            mainImage.setImageDrawable(getResources().getDrawable(R.drawable.background));
            pubDate.setText(sportBean.pubDate.substring(0, sportBean.pubDate.length() - 5));
            //pubDate.setTextSize(16);
            pubDate.setTextColor(Color.GRAY);

            //description.setTextSize(20);
            contentTitle.setText(sportBean.title);
            //contentTitle.setTextSize(26);

            // TODO check connection
            if(check_connectivity){
                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //imageLoader.setVisibility(View.VISIBLE);
                        dialog = new Dialog(HeadLineMainContent.this);
                        Helper.showDialog(dialog);
                        Helper.shareApp(activity, null, Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);
                more_at_text.setText("The " + Helper.IndianExpress);
            }
            imageLoader.setVisibility(View.GONE);
        }
    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageLoader.setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                System.out.println("THE POST ID IS : "+ bean.postId);
                try{
                    doc = Jsoup.connect(sportBean.postId).get();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                bitmap= BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                return bitmap;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            String pubdate;
            if(result==null || doc==null){
                loadDescription = false;
            }
            if(sportBean.pubDate.length()>10){
                pubdate=sportBean.pubDate.substring(0, sportBean.pubDate.length() );
            }
            else{
                pubdate=sportBean.pubDate.substring(0, sportBean.pubDate.length());
            }
            pubDate.setText(pubdate);

            //pubDate.setTextSize(16);
            pubDate.setTextColor(Color.GRAY);
            try{
                Element desc= doc.select("div.full-details").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                String sample2 = "";
                int i = 0;
                for(Element string:divChildren){
                    if (i>=2){
                        sample2 = sample2 + string;
                    } else  {
                        sample = sample + string;
                    }
                    i++;
                }
                description.setText(Html.fromHtml(sample), TextView.BufferType.SPANNABLE);
                // TODO hear i am setting ad by bellow method
                Helper.refreshData(activity,description1);
                description1.setText(Html.fromHtml(sample2), TextView.BufferType.SPANNABLE);
                // TODO i set description text hear
            }catch(Exception e){
                String text=sportBean.description;
                String text_ar[]=text.split("<");
                text="";
                for (String a:text_ar) {
                    if(!a.contains("/>")){
                        text=text+a;
                    }
                }
                description.setText(text);
                description.setTextColor(Color.BLACK);

            }
            //description.setText(sportBean.description);
            //description.setTextSize(20);
            contentTitle.setText(sportBean.title);
            //contentTitle.setTextSize(24);
            contentTitle.setTextColor(Color.BLACK);
            // TODO internet connectoin
            if(check_connectivity){
                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //imageLoader.setVisibility(View.VISIBLE);
                        // TODO copy dialog
                        dialog = new Dialog(HeadLineMainContent.this);
                        Helper.showDialog(dialog);
                        String[] urls = sportBean.mainImageLink.split("#");
                        // TODO change Attribute
                        Helper.shareApp(activity, Utils.resizeImage(urls), Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);

                more_at_text.setText("The " + Helper.IndianExpress);
            }

            mainImage.setImageBitmap(result);
            // TODO copy it
            imageLoader.setVisibility(View.GONE);

            //this.cancel(true);
        }

    }

}
