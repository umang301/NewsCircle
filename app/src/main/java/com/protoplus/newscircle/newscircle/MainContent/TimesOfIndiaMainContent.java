package com.protoplus.newscircle.newscircle.MainContent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.protoplus.newscircle.Util.Utils;
import com.protoplus.newscircle.newscircle.ShowContentPage;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aakash on 10/1/2015.
 */
public class TimesOfIndiaMainContent extends AppCompatActivity {

    TextView pubDate,  contentTitle;
    AppCompatTextView description;
    TextView more_at_text;
    RelativeLayout link;
    AppCompatButton share;
    LinearLayout divider;
    Typeface tf;
    private boolean check_connectivity;
    Boolean loadDescription = false;
    Toolbar toolbar;
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
    Activity activity = this;

    LoadImage loadImage = new LoadImage();
    boolean insertItem = false;
    ImageView mainImage;
    String descriptionText = "";
    Bitmap bitmap;
    public ContextData contextData;
    SportChildSceneBean sportBean;
    ProgressBar imageLoader;

    Boolean notificationFlag;

    ArrayList<SportChildSceneBean> main_ar;

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);

        super.onRestart();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Helper.setOnitemTapInterstitial(this);
    }

    @Override
    public void onBackPressed() {
        if (notificationFlag) {
            Intent it = new Intent(TimesOfIndiaMainContent.this, MainActivity.class);
            startActivity(it);
        } else {
            super.onBackPressed();
            Helper.setIsBack(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        toolbar = Helper.main_content_toolbar(this,Helper.TimesOfIndia+" News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        Helper.initAppodealBanner(this);
        Helper.setAppodealcallback(this);
        Bundle b = it.getExtras();
        notificationFlag = b.getBoolean("NotificationFlag");
        contextData = (ContextData) getApplicationContext();
        imageLoader = (ProgressBar) findViewById(R.id.imageLoader);

        // TODO copy
        Boolean search_flag = it.getExtras().getBoolean("search_result_flag");

        if (notificationFlag == null) {
            notificationFlag = false;
        }
        if (notificationFlag) {
            sportBean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
            //(".....>.....> the Bean Title is : "+sportBean.getTitle()+"......>....> ");

        } else if(search_flag){
            getIntent().putExtra("search_result_flag",false);
            search_flag = false;
            sportBean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
        } else{
            main_ar = contextData.getTimesOfIndia_bean_array();
            int index = it.getExtras().getInt("index");
            sportBean = main_ar.get(index);
        }

        pubDate = (TextView) findViewById(R.id.pubDate);
        description = (AppCompatTextView) findViewById(R.id.mainDescription);
        contentTitle = (TextView) findViewById(R.id.contentTitle);

        tf = Typeface.createFromAsset(getAssets(), Helper.LatoBlack);
        contentTitle.setTypeface(tf);
        description.setTypeface(tf);
        pubDate.setTypeface(tf);

        more_at_text = (TextView) findViewById(R.id.more_at_text);
        link = (RelativeLayout) findViewById(R.id.link);
        share= (AppCompatButton)findViewById(R.id.share);
        share.setCompoundDrawables(Helper.share_button_drawable(this), null, null, null);

        divider = (LinearLayout) findViewById(R.id.divider);

        link.setVisibility(View.INVISIBLE);
        share.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);

        link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(TimesOfIndiaMainContent.this, ShowContentPage.class);
                it.putExtra("url", sportBean.postId);
                startActivity(it);
            }
        });
        mainImage = (ImageView) findViewById(R.id.mainImage);
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
        if (sportBean != null) {
            if (sportBean.mainImageLink != null) {
                String[] urls = sportBean.mainImageLink.split("#");
                System.out.println("The List of URL is : \n");
                // TODO check how many URLS are there
                for(String url:urls){
                    System.out.println(":::: "+url+"\n");
                }
                loadImage.execute(Utils.resizeImage(urls));
            } else {
                if (sportBean.imageLink == null) {
                    mainImage.setVisibility(View.INVISIBLE);
                    new LoadDescription().execute("");
                } else {
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

    private class LoadDescription extends AsyncTask<String, String, String> {
        Document doc;
        //boolean flag = true;
        String sample = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("___ Load Description is called ___");
            imageLoader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect(sportBean.postId).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // TODO set refresh button for Load Description
            if(s==null){
                loadDescription = true;
            }
            try {


                Element e = doc.select("meta[property=og:image]").first();
                System.out.println("_______:: The image element is : "+e);
                String ImageLink = e.attr("content");
                // TODO check whether got image from next page or not
                System.out.println("::::: The next page image is : " + ImageLink);

                //if (ImageLink.contains(".jpg")) {
                    new LoadImage().execute(ImageLink.toString());
                /*} else {
                    System.out.println("::::::::: ___ Exception ___ ::::::::::::");
                    throw new Exception();
                }*/

            } catch (Exception e) {
                e.printStackTrace();
                imageLoader.setVisibility(View.GONE);
                mainImage.setVisibility(View.VISIBLE);
                mainImage.setImageDrawable(getResources().getDrawable(R.drawable.background));

                /*mainImage.setVisibility(View.INVISIBLE);*/
            }
            try {
                //System.out.println(":::::::::::DOC:::::::::::::\n" + doc + "\n::::::::::::::::");
                Element desc = doc.select("div.story-txt").first();
                if(desc==null){
                    desc = doc.select("div.article-txt.clearfix.article-section").first();
                    sample = sample + desc;
                    System.out.println("_____________The description is ___\n" + sample);
                    description.setText(android.text.Html.fromHtml("" + sample));

                }
                else{
                    Element dec = desc.getElementsByTag("div").first();
                    sample = sample + dec;
                    System.out.println("_____________The description is ___\n" + sample);
                    description.setText(android.text.Html.fromHtml("" + sample.substring(0, sample.indexOf("#byt_container") - 7)));

                }

                // TODO check which description is to be set
                System.out.println("____ parsed description is printed ___ ");
            } catch (Exception e) {
                String des_ar[] = sportBean.description.split(">");
                //(sportBean.description);
                for (String ds : des_ar) {
                    // ("String is : "+ds);
                    if (!ds.contains("<") || ds.contains("<br clear=")) {
                        sportBean.description = ds;
                    }
                }
                description.setText(android.text.Html.fromHtml(sportBean.description).toString());
                System.out.println(" _____ descrpition printed from previous page ____ ");
                e.printStackTrace();
            }

            pubDate.setText(sportBean.pubDate);

            pubDate.setTextColor(Color.GRAY);


            contentTitle.setText(sportBean.title);
            if(check_connectivity) {

                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(TimesOfIndiaMainContent.this);
                        Helper.showDialog(dialog);
                        Helper.shareApp(activity, null, Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);
                more_at_text.setText(Helper.TimesOfIndia);
            }
            imageLoader.setVisibility(View.GONE);

        }
    }

    private class LoadImage extends AsyncTask<String, String, String> {
        Document doc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println(" ___ Load Image is called ____ ");
            imageLoader.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                try {
                    doc = Jsoup.connect(sportBean.postId).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("_________-> the image link is  : "+params[0]);
                if(params[0].equals(Helper.TimesOfIndiaIcon)){
                    params[0]=params[0].replace("http:","https:");
                }
                /*bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                System.out.println("_________-> the bitmap is  : " + bitmap);*/
                return params[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String pubdate, sample = "";
            // TODO set refresh Button for Load Image
            if(result==null || doc==null){
                loadDescription = false;
            }
            if (sportBean.pubDate.length() > 10) {
                pubdate = sportBean.pubDate.substring(0, sportBean.pubDate.length());
            } else {
                pubdate = sportBean.pubDate.substring(0, sportBean.pubDate.length());
            }
            pubDate.setText(pubdate);


            pubDate.setTextColor(Color.GRAY);
            try {
                System.out.println(":::::::::::DOC:::::::::::::\n"+doc+"\n::::::::::::::::");
                Element desc = doc.select("div.story-txt").first();
                if(desc==null){
                    desc = doc.select("div.article-txt.clearfix.article-section").first();
                    sample = sample + desc;
                    description.setText(android.text.Html.fromHtml("" + sample));
                }
                else {
                    Element dec = desc.getElementsByTag("div").first();
                    sample = sample + dec;
                    description.setText(android.text.Html.fromHtml("" + sample.substring(0, sample.indexOf("#byt_container") - 7)));
                }

                System.out.println(" ______ get description by parsing");
            } catch (Exception e) {
                String des_ar[] = sportBean.description.split(">");
                //(sportBean.description);
                for (String ds : des_ar) {
                    if (!ds.contains("<") || ds.contains("<br clear=")) {
                        sportBean.description = ds;
                    }
                }
                description.setText(android.text.Html.fromHtml(sportBean.description).toString());
                System.out.println("_____ get description without parsing ");
                e.printStackTrace();
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
                        Dialog dialog = new Dialog(TimesOfIndiaMainContent.this);
                        Helper.showDialog(dialog);
                        Helper.shareApp(activity, sportBean.mainImageLink, Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);
                more_at_text.setText(Helper.TimesOfIndia);
            }
            mainImage.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(result)
                    .error(R.drawable.background)
                    .into(mainImage);
            //mainImage.setImageBitmap(result);
            imageLoader.setVisibility(View.GONE);
        }
    }
}
