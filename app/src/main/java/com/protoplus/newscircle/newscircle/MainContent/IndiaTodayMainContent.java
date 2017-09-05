package com.protoplus.newscircle.newscircle.MainContent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.MainActivity;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.newscircle.ShowContentPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aakash on 10/2/2015.
 */
public class IndiaTodayMainContent extends AppCompatActivity{
    TextView pubDate,contentTitle;
    AppCompatTextView description;
    TextView more_at_text;
    RelativeLayout link;
    AppCompatButton share;
    LinearLayout divider;
    Activity activity = this;
    private boolean check_connectivity;
    Typeface tf;
    Boolean loadDescription = false;
    Toolbar toolbar;
    @Override
    protected void onStart() {
        super.onStart();
        Helper.setOnitemTapInterstitial(this);
    }

    private void clearView(){
        contentTitle.setText("");
        description.setText("");
        pubDate.setText("");
        mainImage.setImageDrawable(null);
        //refreshContent.setVisibility(View.INVISIBLE);
        link.setVisibility(View.INVISIBLE);
        share.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);
    }

    Document doc;
    LoadImage loadImage = new LoadImage();
    boolean insertItem =false;
    ImageView mainImage;
    String descriptionText="";
    Bitmap bitmap;
    public ContextData contextData;
    SportChildSceneBean sportBean;
    ProgressBar imageLoader;

    Boolean notificationFlag = false;
    ArrayList<SportChildSceneBean> main_ar;
    @Override
    public void onBackPressed() {
        if(notificationFlag){
            Intent it = new Intent(IndiaTodayMainContent.this,MainActivity.class);
            startActivity(it);
        }
        else{
            super.onBackPressed();
            Helper.setIsBack(true);
        }
    }
    /*=====================================================================*/
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
        // TODO set action bar hear
        toolbar = Helper.main_content_toolbar(this,Helper.IndiaToday+" News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it=getIntent();
        Helper.initAppodealBanner(this);
        Helper.setAppodealcallback(this);

        contextData =(ContextData) getApplicationContext();
        imageLoader  = (ProgressBar) findViewById(R.id.imageLoader);
        Boolean search_flag = it.getExtras().getBoolean("search_result_flag");

        notificationFlag = it.getExtras().getBoolean("NotificationFlag");
        if(notificationFlag==null){ notificationFlag = false;}
        if(notificationFlag){
            sportBean =(SportChildSceneBean) it.getSerializableExtra("ItemObject");
            System.out.println(".....>.....> the Bean Title is : "+sportBean.getTitle()+"......>....> ");
        }
        else if(search_flag){
            getIntent().putExtra("search_result_flag",false);
            search_flag = false;
            sportBean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
        } else {
            main_ar = contextData.getIndiaToday_bean_array();
            int index = it.getExtras().getInt("index");
            sportBean = main_ar.get(index);
        }
        System.out.println(":::::::::THE POST ID IS : "+sportBean.postId);
        pubDate=(TextView) findViewById(R.id.pubDate);
        description=(AppCompatTextView) findViewById(R.id.mainDescription);
        contentTitle=(TextView) findViewById(R.id.contentTitle);

        tf = Typeface.createFromAsset(getAssets(),Helper.LatoBlack);
        contentTitle.setTypeface(tf);
        description.setTypeface(tf);
        pubDate.setTypeface(tf);

        more_at_text = (TextView) findViewById(R.id.more_at_text);
        link=(RelativeLayout) findViewById(R.id.link);
        share= (AppCompatButton)findViewById(R.id.share);
        share.setCompoundDrawables(Helper.share_button_drawable(this), null, null, null);

        divider = (LinearLayout) findViewById(R.id.divider);

        link.setVisibility(View.INVISIBLE);
        share.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);
        link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(IndiaTodayMainContent.this, ShowContentPage.class);
                it.putExtra("url", sportBean.postId);
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
        if (sportBean!=null) {
            if(sportBean.mainImageLink!=null){
                System.out.println("i am in condition 1 := bean.mainImageLink!=null");
                loadImage.execute(sportBean.mainImageLink);
            }
            else{
                if(sportBean.imageLink==null){
                    new LoadDescription().execute("");
                }
                else{
                    System.out.println("i am in condition 3 := bean.imageLink!=null");
                    loadImage.execute(sportBean.imageLink);
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

    private class LoadDescription extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageLoader.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            try{
                doc = Jsoup.connect(sportBean.postId).get();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // TODO set refresh button for Load Description
            if(s==null){
                System.out.println("_____________THe load description variable is true ________________");
                loadDescription = true;
            }
            try{
                System.out.println(":::::::::::::::::THE DOCUMENT IS : "+doc);
                Element desc= doc.select("div.right-story-container").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                for(Element string:divChildren){
                    sample = sample + string;
                }
                description.setText(android.text.Html.fromHtml(sample));
            }catch(Exception e){
                e.printStackTrace();
                String sport_des[]=sportBean.description.split("<");
                for(String string : sport_des){
                    if(!string.contains(">")){
                        sportBean.setDescription(string);
                    }
                }
                description.setText(sportBean.description);

            }
            mainImage.setVisibility(View.VISIBLE);
            mainImage.setImageDrawable(getResources().getDrawable(R.drawable.background));
            try{
                pubDate.setText(sportBean.pubDate.substring(0, sportBean.pubDate.length() - 5));
            }catch(Exception e){
                e.printStackTrace();
                pubDate.setText(sportBean.pubDate);
            }

            pubDate.setTextColor(Color.GRAY);

            
            contentTitle.setText(sportBean.title);
            if(check_connectivity) {

                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(IndiaTodayMainContent.this);
                        Helper.showDialog(dialog);
                        Helper.shareApp(activity, null, Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);
                more_at_text.setText(Helper.IndiaToday);
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
            // TODO set refresh Button for Load Image
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

            
            pubDate.setTextColor(Color.GRAY);
           /* ...*/
            try{
                System.out.println(":::::::::::::::::THE DOCUMENT IS : "+doc);
                Element desc= doc.select("div.right-story-container").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                for(Element string:divChildren){
                    sample = sample + string;
                }
                description.setText(android.text.Html.fromHtml(sample));
            }catch(Exception e) {
                e.printStackTrace();
                String sport_des[]=sportBean.description.split("<");
                for(String string : sport_des){
                    System.out.println("description:"+string);
                    if(string.contains(">") && string.substring(0,3).contains("/a>") && string.length()>10){
                        sportBean.setDescription(string.substring(3,string.length()));
                    }
                }
                description.setText(sportBean.description);
            }

            
            contentTitle.setText(sportBean.title);
            
            contentTitle.setTextColor(Color.BLACK);
            if(check_connectivity) {

                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sportBean.mainImageLink == null) {
                            sportBean.mainImageLink = sportBean.imageLink;
                        }
                        Dialog dialog = new Dialog(IndiaTodayMainContent.this);
                        Helper.showDialog(dialog);
                        Helper.shareApp(activity, sportBean.mainImageLink, Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);

                more_at_text.setText(Helper.IndiaToday);
            }
            mainImage.setImageBitmap(result);
            imageLoader.setVisibility(View.GONE);


            //this.cancel(true);
        }

    }

}
