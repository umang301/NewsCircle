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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
public class IndianExpressMainContent extends AppCompatActivity {
    TextView pubDate,  contentTitle;
    AppCompatTextView description;
    Activity activity = this;
    TextView more_at_text;
    RelativeLayout link;
    AppCompatButton share;
    LinearLayout divider;
    Typeface tf;
    private boolean check_connectivity;
    //Button refreshContent;

    // TODO copy
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
    boolean insertItem = false;
    ImageView mainImage;
    String descriptionText = "";
    Bitmap bitmap;
    public ContextData contextData;
    SportChildSceneBean sportBean;
    ProgressBar imageLoader;
    //SportChildSceneBean bean,beanSport;
    Boolean notificationFlag;

    ArrayList<SportChildSceneBean> main_ar;
    ArrayList<SportChildSceneBean> refresh_ar = new ArrayList<SportChildSceneBean>();

    @Override
    protected void onRestart() {
        Helper.setSubActivityUI(this);

        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        if (notificationFlag) {
            Intent it = new Intent(IndianExpressMainContent.this, MainActivity.class);
            startActivity(it);
        } else {
            super.onBackPressed();
            Helper.setIsBack(true);
        }
    }
    /*=====================================================================*/


/*======================================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        // TODO set action bar hear
        toolbar = Helper.main_content_toolbar(this,"The "+Helper.IndianExpress+" News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Helper.setAppodealcallback(this);

        Intent it = getIntent();
        Helper.initAppodealBanner(this);
        //refreshContent = (Button) findViewById(R.id.contentRefresh);
        notificationFlag = it.getExtras().getBoolean("NotificationFlag");
        imageLoader = (ProgressBar) findViewById(R.id.imageLoader);
        Boolean search_flag = it.getExtras().getBoolean("search_result_flag");

        contextData = (ContextData) getApplicationContext();
        if (notificationFlag == null) {
            notificationFlag = false;
        }
        if (notificationFlag) {
            sportBean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
            System.out.println(".....>.....> the Bean Title is : " + sportBean.getTitle() + "......>....> ");
            System.out.println(">>> ____ >>> THe size of ArrayList is : "+Helper.getHeadlineBeanar().size());
        } else if(search_flag){
            getIntent().putExtra("search_result_flag",false);
            search_flag = false;
            sportBean = (SportChildSceneBean) it.getSerializableExtra("ItemObject");
        } else {

            main_ar = contextData.getIE_bean_array();
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
                Intent it = new Intent(IndianExpressMainContent.this, ShowContentPage.class);
                it.putExtra("url", sportBean.postId);
                //MainContent.this.stopService(getIntent());
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
                try {
                    loadImage.execute(Utils.resizeImage(urls));
                } catch (Exception e) {
                    new LoadDescription().execute("");
                }
            } else {
                if (sportBean.imageLink == null) {
                    new LoadDescription().execute("");
                } else {
                    //loadImage = new LoadImage();
                    loadImage.execute(sportBean.imageLink);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_content, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // TODO copy this
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
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageLoader.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect(sportBean.postId).get();
            } catch (Exception e) {
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
                System.out.println("::::::::::::::::THE DOCUMENT IS : " + doc);
                Element desc = doc.select("div.full-details").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                for (Element string : divChildren) {
                    sample = sample + string;
                }
                if(sample.length()<5){
                    //refreshContent.setVisibility(View.VISIBLE);
                }
                description.setText(android.text.Html.fromHtml(sample));

            } catch (Exception e) {
                String text = sportBean.description;
                String text_ar[] = text.split("<");
                text = "";
                for (String a : text_ar) {
                    if (!a.contains("/>")) {
                        text = text + a;
                    }
                }
                description.setText(text);
            }
            mainImage.setVisibility(View.VISIBLE);
            mainImage.setImageDrawable(getResources().getDrawable(R.drawable.background));

            pubDate.setText(sportBean.pubDate.substring(0, sportBean.pubDate.length() - 5));

            pubDate.setTextColor(Color.GRAY);


            contentTitle.setText(sportBean.title);
            if(check_connectivity) {

                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(IndianExpressMainContent.this);
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
            try {
                System.out.println("THE POST ID IS : " + sportBean.postId);
                try {
                    doc = Jsoup.connect(sportBean.postId).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                return bitmap;
            } catch (Exception e) {
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

            if (sportBean.pubDate.length() > 10) {
                pubdate = sportBean.pubDate.substring(0, sportBean.pubDate.length());
            } else {
                pubdate = sportBean.pubDate.substring(0, sportBean.pubDate.length());
            }
            pubDate.setText(pubdate);
            try {
                Element desc = doc.select("div.full-details").first();
                Elements divChildren = desc.getElementsByTag("p");
                String sample = "";
                for (Element string : divChildren) {
                    sample = sample + string;
                }
                if(sample.length()<5){
                    //refreshContent.setVisibility(View.VISIBLE);
                }
                description.setText(android.text.Html.fromHtml(sample));

            } catch (Exception e) {
                String text = sportBean.description;
                String text_ar[] = text.split("<");
                text = "";
                for (String a : text_ar) {
                    if (!a.contains("/>")) {
                        text = text + a;
                    }
                }
                description.setText(text);
            }

            pubDate.setTextColor(Color.GRAY);

            contentTitle.setText(sportBean.title);

            contentTitle.setTextColor(Color.BLACK);
            if(check_connectivity) {

                link.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] urls = sportBean.mainImageLink.split("#");
                        Dialog dialog = new Dialog(IndianExpressMainContent.this);
                        Helper.showDialog(dialog);

                        Helper.shareApp(activity, Utils.resizeImage(urls), Helper.getmassage(sportBean.getTitle(), sportBean.getPostId()),dialog);
                    }
                });
                divider.setVisibility(View.VISIBLE);

                more_at_text.setText("The " + Helper.IndianExpress);
            }
            mainImage.setImageBitmap(result);
            imageLoader.setVisibility(View.GONE);
        }
    }
}
