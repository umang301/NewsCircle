package com.protoplus.newscircle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.newscircle.HandleErrorPage;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SplashActivity extends AppCompatActivity {
    private Boolean NotificationFlag = false;
    private SportChildSceneBean mainContent_bean ;
    private final LinearInterpolator linearInterpolator = new LinearInterpolator();
    private LoadData loadData = new LoadData();
    public static Intent intent;
    SharedPreferences mPrefs;
    ContextData contextData;
    SAXParser parser;
    //TextView splash_title;

    ArrayList<SportChildSceneBean> sceneBeanAr=new ArrayList<SportChildSceneBean>();
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         Helper.setActivityUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        System.out.println("++++++++++++++    On create Execute is called    +++++++++++++++++");
        Helper.initAppodeal(this);
        /*splash_title = (TextView) findViewById(R.id.splash_title);
        Typeface tf = Typeface.createFromAsset(getAssets(), Helper.OswaldBold);
        splash_title.setTypeface(tf);
        splash_title.setTextColor(Color.parseColor("#0066CC"));*/
        intent = getIntent();
        Helper.isAppOpen = true;
        try{
            NotificationFlag = intent.getExtras().getBoolean("NotificationFlag");
            mainContent_bean = (SportChildSceneBean)intent.getSerializableExtra("ItemObject");
        }catch(Exception e){
            e.printStackTrace();
            NotificationFlag = false;
        }

        contextData = (ContextData) getApplicationContext();
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO uncomment
        loadData.execute("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class LoadData extends AsyncTask<String, String, String> {
        Intent it;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println(" __________ THE Preexecute is Called__________________________");
        }

        private class SplashIEHandler extends DefaultHandler {
            public boolean title=false,pubDate=false,guid=false , description=false;
            public String descriptionText = "";
            public SportChildSceneBean bean;
            public String titleText="",postIdText="";
            public int counter = 0,i=0;
            public int limit = 0;
            public String image_url = null;
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
                if (qName.equals("item")){
                    image_url = "";
                    bean = new SportChildSceneBean();
                }
                else if (qName.equals("title") ){
                    titleText="";
                    title = true;
                }

                else if (qName.equals("pubDate")) pubDate = true;

                else if(qName.equals("guid")){
                    postIdText = "";
                    guid = true;
                }
                else if(qName.equals("content:encoded")){
                    description =false;
                    descriptionText = "";
                }
                else if(qName.equals("media:thumbnail")) {
                    if(bean!=null){
                        bean.setImageLink(attributes.getValue("url"));
                    }
                }
                else if(qName.equalsIgnoreCase("media:content")){
                    i++;
                    if(bean != null){
                        image_url=image_url+"#"+attributes.getValue("url");
                        bean.setMainImageLink(image_url);
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);

                if (title  && bean!=null){
                    titleText = titleText + new String(ch, start, length);
                    bean.setTitle(titleText);
                }
                else if (pubDate  && bean!=null) {
                    String text=new String(ch,start,length);
                    if(text.length()>10){
                        text = text.substring(0,text.length()-6);
                    }
                    bean.setPubDate(text);
                }
                else if(guid && bean != null){
                    postIdText=postIdText+new String(ch, start, length);
                    bean.setPostId(postIdText);
                }
                else if(description ){
                    if(bean!=null){
                        String text=new String(ch,start,length);
                        String txt="";
                        String textsplit[]=text.split("caption");
                        for (String s:textsplit) {
                            if(s.contains("]")){
                                txt=s.substring(s.indexOf(']'),s.length());
                            }
                        }
                        if(text.contains("[/caption]")){
                            text=text.substring(text.indexOf("[/caption]")+10,text.length());
                        }
                        if(text.contains("<") && text.contains(">")){
                            text.split(text.substring(text.indexOf("<"),text.indexOf(">")));
                        }
                        descriptionText = descriptionText + text;
                        bean.setDescription(descriptionText);
                    }
                }
            }
            public void endElement(String uri, String localName,
                                   String qName) throws SAXException
            {

                if (qName.equals("item") && bean!=null)
                {

                    counter++;
                    if(counter>6){
                        throw new SAXException();
                    }
                    sceneBeanAr.add(bean);
                    bean=null;
                }
                else if(qName.equals("title")){

                    title = false;
                }
                else if(qName.equals("pubDate")){
                    pubDate = false;
                }

                else if(qName.equals("guid")) guid = false;
                else if(qName.equals("description") && bean!=null) description = true;
                        /*else if(qName.equals("description")) description = false;*/

            }
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println(" __________ THE doinbackground is Called__________________________");

            final String urlString=Helper.HeadlineURL;
            mPrefs = getSharedPreferences(Helper.MyPREFERENCES,MODE_PRIVATE);
            try {
                parser.parse(urlString,new SplashIEHandler());
            } catch (SAXException e) {
                System.out.println("______________ SAX Exception occured_________________");
                if(NotificationFlag){
                    try{
                        it = new Intent(SplashActivity.this, Class.forName(mainContent_bean.getMainContentClass()));
                        it.putExtra("NotificationFlag", NotificationFlag);
                        it.putExtra("ItemObject", mainContent_bean);
                    }
                    catch (ClassCastException classcastex){
                        classcastex.printStackTrace();
                    }
                    catch(ClassNotFoundException classnotfound){
                        classnotfound.printStackTrace();
                    }
                }
                else{
                    Helper.isAppOpen = true;
                    System.out.println("++++++++++++++    On mainactivity is called    +++++++++++++++++");
                    it =new Intent(SplashActivity.this, MainActivity.class);
                }
                Helper.header=sceneBeanAr.get(0);
                Helper.WriteHeadLineData(mPrefs, 0, 0, Helper.header.imageLink + "&&&&" + Helper.header.title);
                sceneBeanAr.remove(0);
                Helper.setHeadlineBeanar(sceneBeanAr);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                it = new Intent(SplashActivity.this, HandleErrorPage.class);
            }

            return null;
        }
        /*private final static int INTERVAL = 1000 * 2 ; //2 minutes
        Handler mHandler = new Handler();
*/



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // TODO http://stackoverflow.com/questions/6207362/how-to-run-an-async-task-for-every-x-mins-in-android
            System.out.println("++++++++++++++    On post Execute is called    +++++++++++++++++");
            SplashActivity.this.finish();
            startActivity(it);

/*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("calling activity................................");
                    SplashActivity.this.finish();
                    startActivity(it);
                }
            }, 3000);*/


        }

    }

}
