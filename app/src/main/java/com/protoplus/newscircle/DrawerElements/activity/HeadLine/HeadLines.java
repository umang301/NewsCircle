package com.protoplus.newscircle.DrawerElements.activity.HeadLine;

/**
 * Created by Aakash on 29/07/15.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.clockbyte.admobadapter.AdmobAdapterWrapper;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.CustomLists.Sports;
import com.protoplus.newscircle.MyAlarmService;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.AlarmReceiver;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.IGetNotification;
import com.protoplus.newscircle.Util.Utils;
import com.protoplus.newscircle.newscircle.MainContent.HeadLineMainContent;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class HeadLines extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IGetNotification {
    Boolean loadingMore = false;
    private static final HeadLines headLineInstance = new HeadLines();

    public static HeadLines getHeadLineInstance() {
        return headLineInstance;
    }

    boolean call_xml = true, end = false, insert = true;
    String descriptionText = "";
    String HeadLineTitle;
    SwipeRefreshLayout swipeLayout;
    ArrayList<SportChildSceneBean> ar, refresh_ar = new ArrayList<SportChildSceneBean>();

    SportChildSceneBean bean;
    ListView listView;
    Timer updateAdsTimer;
    AdmobAdapterWrapper adapterWrapper;
    SharedPreferences mPrefs;
    private String category;
    //int cat_index;
    View headerView;
    View rootView;
    Boolean isAppStart;
    public static Sports sports;
    public ImageView headlineImage;
    public TextView headlineText;
    Bitmap bitmap;

    PendingIntent broadcastPendingIntent;

    private void getBroadcastPandingIntent() {
        Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);
        broadcastPendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 234324243, intent, 0);
    }

    @Override
    public void getNotificationData(Context context, String urlString, int cat_index, String category) {
        System.out.println("(:) (::) (:::) (::) (:)HEAR I AM CHECKING WHETHER ANY UPDATION IS THERE OR NOT___________________");
        this.category = category;
        new myTask(context).execute();
    }

    public void stopAlert() {
        System.out.println("_______________________THE STOP ALERT METHOD IS CALLED");
        getBroadcastPandingIntent();
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(broadcastPendingIntent);
    }

    public void startAlert() {
        System.out.println("_______________________THE START ALERT METHOD IS CALLED");
        int i = 5;
        int interval = 1000 * 60;
        getBroadcastPandingIntent();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (refresh_ar != null) {
            refresh_ar.clear();

        }
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), broadcastPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, broadcastPendingIntent);
    }

    /*=====================================================================*/
    public void onRefresh() {
        System.out.println("_________________ Headline onRefresh method is called _________________");
        new myTask(null).execute();
    }

    private class myTask extends AsyncTask<String, String, String> {
        Context context;

        public myTask(Context context) {
            try {
                System.out.println(">>>>>> THe size of refresh arraylist is " + refresh_ar.size() + " <<<<<<<");
                refresh_ar.clear();
            } catch (NullPointerException nullp) {
                nullp.printStackTrace();
            }
            if (context != null) {
                mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, context.MODE_PRIVATE);
            }
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            if (ar != null && ar.size() > 0) {
                new refreshData();
            } else {
                ar = new ArrayList<>();
                new refreshData();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            if (!Helper.isAppOpen) {
                System.out.println("~~~~~~~~~~~Hear i am sending Alarm Alert........the size of Refresh ar is " + refresh_ar.size());
                if (refresh_ar.size() > 0) {
                    SportChildSceneBean sport_Bean = refresh_ar.get(0);
                    Helper.WriteHeadLineData(mPrefs, 0, 0, sport_Bean.imageLink + "&&&&" + sport_Bean.title);
                    Intent service1 = new Intent(context, MyAlarmService.class);
                    System.out.println("intent is : " + service1 + "\n\n____>THE OBJECT IS : " + sport_Bean + " >.....and title is : " + sport_Bean.getTitle());
                    service1.putExtra("NotificationData", sport_Bean);
                    service1.putExtra("web_index", 0);
                    service1.putExtra("cat_index", 0);
                    context.startService(service1);
                }
            } else {
                try {
                    if (refresh_ar.size() > 0) {
                        //TODO check Helper.header is null or not
                        if (Helper.header != null) {
                            ar.add(0, Helper.header);
                            ar.get(0).setMainContentClass(HeadLineMainContent.class.getName());
                            ar.get(0).setCategory(category);
                        } else {
                            System.out.println("__________________Helper.header is null __________________");
                        }

                        Helper.isImageLoaded = false;
                        ar.addAll(0, refresh_ar);
                        refresh_ar.clear();
                        Helper.header = ar.get(0);

                        System.out.println("Line no : 163 : In HeadLine SharedPreference is : " + mPrefs);
                        Helper.WriteHeadLineData(mPrefs, 0, 0, Helper.header.imageLink + "&&&&" + Helper.header.title);
                        ar.remove(0);
                    }
                    headlineImage = (ImageView) headerView.findViewById(R.id.HeadlineImage);
                    headlineText = (TextView) headerView.findViewById(R.id.headlineText);
                    headlineText.setText(Helper.header.getTitle());
                    ((LinearLayout) headerView.findViewById(R.id.headlineTextLayout)).setAlpha(0.5f);
                    if (Helper.header != null && !Helper.isImageLoaded) {
                        if (Helper.header.mainImageLink != null) {
                            String[] urls = Helper.header.mainImageLink.split("#");
                            new LoadImage().execute(Utils.resizeImage(urls));
                        } else {
                            if (Helper.header.imageLink == null) {
                                headlineImage.setImageResource(R.drawable.blank_headline);
                            } else {
                                new LoadImage().execute(Helper.header.imageLink);
                            }
                        }
                        Helper.isImageLoaded = true;
                    }
                    if (!Helper.getisBack()) {
                        swipeLayout.setRefreshing(false);
                    }

                    sports.notifyDataSetChanged();
                } catch (NullPointerException nullpointer) {
                    System.out.println("_______HeadLine view is accessed again_______");
                    nullpointer.printStackTrace();
                }

            }
        }
    }

    private class refreshData {
        public refreshData() {
            // TODO Hear I have to handle no connection exception
            final String urlString = Helper.HeadlineURL;
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(urlString, new DefaultHandler() {

                    boolean title = false, pubDate = false, guid = false, description = false;
                    String titleText = "", postIdText = "";
                    int i = 0;
                    String image_url = null;

                    public void startElement(String uri, String localName,
                                             String qName, Attributes attributes) throws SAXException {
                        if (qName.equals("item")) {
                            image_url = "";
                            insert = true;
                            bean = new SportChildSceneBean();
                        } else if (qName.equals("title")) {
                            titleText = "";
                            title = true;
                        } else if (qName.equals("pubDate")) pubDate = true;

                        else if (qName.equals("guid")) {
                            postIdText = "";
                            guid = true;
                        } else if (qName.equals("content:encoded")) {
                            description = false;
                            descriptionText = "";
                        } else if (qName.equals("media:thumbnail")) {
                            if (bean != null) {
                                bean.setImageLink(attributes.getValue("url"));
                            }
                        } else if (qName.equalsIgnoreCase("media:content")) {
                            i++;
                            if (bean != null) {
                                image_url = image_url + "#" + attributes.getValue("url");
                                bean.setMainImageLink(image_url);
                            }
                        }
                    }

                    public void endElement(String uri, String localName,
                                           String qName) throws SAXException {
                        if (qName.equals("item") && bean != null) {
                            System.out.println("In End Element ________________");
                            if (insert) {
                                // TODO hear I have to set different condition for notification and open app

                                if(Helper.isAppOpen && ar!=null && ar.size()>0){
                                    HeadLineTitle = Helper.header.getTitle();
                                }
                                else{
                                    HeadLineTitle = Helper.ReadHeadLineData(mPrefs, 0, 0).split("&&&&")[1];
                                }

                                System.out.println("__" + bean.getTitle() + "\n__" + HeadLineTitle + "___" + HeadLineTitle.contains(bean.getTitle()));
                                if (HeadLineTitle.contains(bean.getTitle()) || bean.getTitle().contains(HeadLineTitle)) {
                                    throw new SAXException();
                                } else {
                                    if (!Helper.isAppOpen) {
                                        bean.setMainContentClass(HeadLineMainContent.class.getName());
                                        bean.setCategory(category);
                                        refresh_ar.add(bean);
                                        throw new SAXException();
                                    }
                                    bean.setMainContentClass(HeadLineMainContent.class.getName());
                                    bean.setCategory(category);
                                    refresh_ar.add(bean);
                                }
                                bean = null;
                            }

                        } else if (qName.equals("title")) {

                            title = false;
                        } else if (qName.equals("pubDate")) {
                            pubDate = false;
                        } else if (qName.equals("guid")) guid = false;

                        else if (qName.equals("description") && bean != null) description = true;

                    }

                    public void characters(char ch[], int start, int length)
                            throws SAXException {
                        if (title && bean != null) {
                            titleText = titleText + new String(ch, start, length);
                            bean.setTitle(titleText);
                        } else if (pubDate && bean != null) {
                            String text = new String(ch, start, length);
                            if (text.length() > 10) {
                                text = text.substring(0, text.length() - 6);
                            }
                            bean.setPubDate(text);
                        } else if (guid && bean != null) {
                            postIdText = postIdText + new String(ch, start, length);
                            bean.setPostId(postIdText);
                        } else if (description && bean != null) {
                            String text1 = new String(ch, start, length);
                            descriptionText = descriptionText + android.text.Html.fromHtml(new String(ch, start, length)).toString();
                            bean.setDescription(descriptionText);
                        }

                    }

                    @Override
                    public void endDocument() throws SAXException {
                        super.endDocument();
                        end = true;
                    }

                });
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                System.out.println("___________I have Thrown SAXException Hear________");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

/*==================================================================*/

    public HeadLines() {
        Helper.backIndex = 0;
        //ar = ContextData.getHeadline_bean_array();
       /* ar = Helper.getHeadlineBeanar();
        if(ar!=null){
            System.out.println("#############   Size of ar is : "+ar.size()+"  #################");

        }else{
            System.out.println("#############   Size of ar is : 0  #################");

        }*/
    }

    int subLimit = 6;

    private class ListIEHandler extends DefaultHandler {
        public boolean title = false, pubDate = false, guid = false, description = false;
        public String descriptionText = "";
        public SportChildSceneBean bean;
        public String titleText = "", postIdText = "";
        public int counter = 0, i = 0;
        public int limit = 0;

        public String image_url = null;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (limit < subLimit) {
                if (qName.equals("item")) {
                    limit++;
                    return;
                }
            }
            if (qName.equals("item")) {
                image_url = "";
                bean = new SportChildSceneBean();
            } else if (qName.equals("title")) {
                titleText = "";
                title = true;
            } else if (qName.equals("pubDate")) pubDate = true;

            else if (qName.equals("guid")) {
                postIdText = "";
                guid = true;
            } else if (qName.equals("content:encoded")) {
                description = false;
                descriptionText = "";
            } else if (qName.equals("media:thumbnail")) {
                if (bean != null) {
                    bean.setImageLink(attributes.getValue("url"));
                }
            } else if (qName.equalsIgnoreCase("media:content")) {
                i++;
                if (bean != null) {
                    image_url = image_url + "#" + attributes.getValue("url");
                    bean.setMainImageLink(image_url);
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            if (title && bean != null) {
                titleText = titleText + new String(ch, start, length);
                bean.setTitle(titleText);
            } else if (pubDate && bean != null) {
                String text = new String(ch, start, length);
                if (text.length() > 10) {
                    text = text.substring(0, text.length() - 6);
                }
                bean.setPubDate(text);
            } else if (guid && bean != null) {
                postIdText = postIdText + new String(ch, start, length);
                bean.setPostId(postIdText);
            } else if (description) {
                if (bean != null) {
                    String text = new String(ch, start, length);
                    String txt = "";
                    String textsplit[] = text.split("caption");
                    for (String s : textsplit) {
                        if (s.contains("]")) {
                            txt = s.substring(s.indexOf(']'), s.length());
                        }
                    }
                    if (text.contains("[/caption]")) {
                        text = text.substring(text.indexOf("[/caption]") + 10, text.length());
                    }
                    if (text.contains("<") && text.contains(">")) {
                        text.split(text.substring(text.indexOf("<"), text.indexOf(">")));
                    }
                    descriptionText = descriptionText + text;
                    //android.text.Html.fromHtml(text).toString();
                    bean.setDescription(descriptionText);
                }

            }

        }

        public void endElement(String uri, String localName,
                               String qName) throws SAXException {

            if (qName.equals("item") && bean != null) {
                if (insert) {
                    if (counter >= 6) {
                        throw new SAXException();
                    } else {
                        counter++;
                    }
                    //Hear i am setting index of bean that will be used when We are publishing notification
                    bean.setMainContentClass(HeadLineMainContent.class.getName());
                    bean.setCategory(category);
                    ar.add(bean);
                    bean = null;
                }

            } else if (qName.equals("title")) {

                title = false;
            } else if (qName.equals("pubDate")) {
                pubDate = false;
            } else if (qName.equals("guid")) guid = false;

                        /*else if(qName.equals("description")) description = false;*/
            else if (qName.equals("description") && bean != null) description = true;

        }
    }

    private class getList {
        public getList() {
            final String urlString = Helper.HeadlineURL;
            //"http://indianexpress.com/section/india/feed/";
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(urlString, new ListIEHandler());
            } catch (SAXException e) {
                subLimit += 6;
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("---------------(0) )0( (0) )0( Header is : " + Helper.getisBack());
        Helper.isAppOpen = true;
        // stopAlert();
        if (Helper.header != null && Helper.getisBack()) {
            new myTask(null).execute("");
            Helper.setIsBack(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("___________ Headline On Resume __________");
        if (Helper.getisBack()) {
            new myTask(null).execute();
        }
        Helper.isAppOpen = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ar = Helper.getHeadlineBeanar();
        Helper.fromReceiver = false;
    }

    private View footerView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        final SearchManager searchManager = (SearchManager) getActivity().getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Query : " + query);
                for (SportChildSceneBean bean : ar) {
                    bean.setMainContentClass(HeadLineMainContent.class.getName());
                }
                Helper.setSearch_result(ar);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("newText : " + newText);
                final ArrayList<String> suggestions = new ArrayList<String>();
                for (SportChildSceneBean bean :
                        ar) {
                    if (bean.getTitle().contains(newText) || bean.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                        System.out.println("Title: " + bean.getTitle());
                        suggestions.add(bean.getTitle());
                    }
                }
                final SearchView.SearchAutoComplete searchSrcTextView = (SearchView.SearchAutoComplete) getActivity().findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchSrcTextView.setThreshold(1);
                searchSrcTextView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions));
                searchSrcTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("_____________searchtext");
                        searchSrcTextView.setText(suggestions.get(position));
                        return;
                    }
                });
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println(" On Focus Change");
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO hear I am killing application if someone press back from mainActivity page
        /*try {
            System.out.println(".....//> The HeadLine data from HeadLIne onCreateView is : " + Helper.header.getTitle());
        } catch (Exception e) {

            e.printStackTrace();
            Helper.isAppOpen = false;
            android.os.Process.killProcess(android.os.Process.myPid());
        }*/

        Helper.WriteHeadLineData(mPrefs, 0, 0, Helper.header.imageLink + "&&&&" + Helper.header.title);
        Helper.fromReceiver = false;
        if (sports != null) {
            sports.notifyDataSetChanged();
        }
        mPrefs = getActivity().getSharedPreferences(Helper.MyPREFERENCES, getActivity().MODE_PRIVATE);
        isAppStart = Helper.getIsAppStart(mPrefs);
        if (!isAppStart) {
            startAlert();
            System.out.println("..........In if condition\nTHE App start boolean variable is : " + isAppStart);
            Helper.setIsAppStart(mPrefs, true);
        }
        rootView = inflater.inflate(R.layout.fragment_headline, container, false);

        try {
            SportChildSceneBean bean = Helper.header;
            swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
            swipeLayout.setColorSchemeResources(R.color.colorPrimary);
            swipeLayout.setOnRefreshListener(this);
            sports = new Sports(getActivity().getApplicationContext(), R.layout.fragment_headline,ar);
            adapterWrapper = new AdmobAdapterWrapper(getActivity().getApplicationContext());

            adapterWrapper.setAdapter(sports); //wrapping your adapter with a AdmobAdapterWrapper.
            //here you can use the following string to set your custom layouts for a different types of native ads
            //adapterWrapper.setInstallAdsLayoutId(R.layout.your_installad_layout);
            //adapterWrapper.setcontentAdsLayoutId(R.layout.your_installad_layout);

            //Sets the max count of ad blocks per dataset, by default it equals to 3 (according to the Admob's policies and rules)
            adapterWrapper.setLimitOfAds(3);

            //Sets the number of your data items between ad blocks, by default it equals to 10.
            //You should set it according to the Admob's policies and rules which says not to
            //display more than one ad block at the visible part of the screen,
            // so you should choose this parameter carefully and according to your item's height and screen resolution of a target devices
            adapterWrapper.setNoOfDataBetweenAds(10);

            //It's a test admob ID. Please replace it with a real one only when you will be ready to deploy your product to the Release!
            //Otherwise your Admob account could be banned
            //String admobUnitId = getResources().getString(R.string.banner_admob_unit_id);
            //adapterWrapper.setAdmobReleaseUnitId(admobUnitId);

            listView = (ListView) rootView.findViewById(R.id.listView1);


            headerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headline_part, null, false);
            System.out.println("___________________THE HEADLINE PART is : " + headerView);
            headlineImage = (ImageView) headerView.findViewById(R.id.HeadlineImage);

            try {
                headlineText = (TextView) headerView.findViewById(R.id.headlineText);
                headlineText.setText(bean.getTitle());
                ((LinearLayout) headerView.findViewById(R.id.headlineTextLayout)).setAlpha(0.5f);
                if (bean != null) {
                    if (bean.mainImageLink != null) {
                        String[] urls = bean.mainImageLink.split("#");
                        new LoadImage().execute(Utils.resizeImage(urls));
                    } else {
                        if (bean.imageLink == null) {
                            headlineImage.setImageResource(R.drawable.blank_headline);
                        } else {
                            new LoadImage().execute(bean.imageLink);
                        }
                    }
                }
                listView.addHeaderView(headerView);

                footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);

                listView.addFooterView(footerView);
                listView.setAdapter(adapterWrapper);

                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    private LoadData getLoadData() {
                        return new LoadData();
                    }

                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        int threshold = 1;
                        try {
                            int count = listView.getCount();
                            if (scrollState == SCROLL_STATE_IDLE) {

                                if (listView.getLastVisiblePosition() >= count - threshold) {
                                    if (!end) getLoadData().execute("");
                                }
                            }
                        } catch (NullPointerException nullpointer) {
                            Log.d("Headline400", "getcount method send nullpointerexception");
                            nullpointer.printStackTrace();
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem,
                                         int visibleItemCount, int totalItemCount) {
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {

                        Intent it = new Intent(getActivity().getApplicationContext(), HeadLineMainContent.class);
                        //ContextData.setHeadline_bean_array(ar);

                        Helper.setHeadlineBeanar(ar);
                        it.putExtra("index", arg2);
                        it.putExtra("NotificationFlag", false);
                        startActivity(it);
                    }
                });
            } catch (NullPointerException e) {
                e.printStackTrace();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            if (rootView == null) {
                android.os.Process.killProcess(android.os.Process.myPid());
            } else {
                System.out.println("THE ROOT VIEW OBJECT IS : " + rootView);
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        initUpdateAdsTimer();

        return rootView;
    }
    private void initUpdateAdsTimer() {
        updateAdsTimer = new Timer();
        updateAdsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterWrapper.requestUpdateAd();
                    }
                });
            }
        }, 60*1000, 60*1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Hear I Have to stop current alarm notification
        //stopAlert();
        System.out.println("Hear I Have to stop current alarm notification");
    }

    @Override
    public void onStop() {
        try {
            System.out.println("HeadLine Fragment is Being Stop _____________________________");
            System.out.println("Hear I am Setting HeadLine Destroyed Variable true");
            Helper.isAppOpen = false;
            //startAlert();
            System.out.println("Hear I have to start Alarm Notification");
            super.onStop();
        } catch (Exception e) {
            e.printStackTrace();
            //android.os.Process.killProcess(android.os.Process.myPid());
        }


    }

    @Override
    public void onDestroyView() {
        try {
            super.onDestroyView();
            ((ViewGroup) rootView.getParent()).removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(updateAdsTimer!=null)
            updateAdsTimer.cancel();
        adapterWrapper.destroyAds();
    }

    @Override
    public void onDetach() {
        try {
            super.onDetach();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class LoadData extends AsyncTask<String, String, ArrayList<SportChildSceneBean>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            footerView.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<SportChildSceneBean> doInBackground(String... params) {
            loadingMore = true;
            new getList();
            loadingMore = false;
            return ar;
        }

        @Override
        protected void onPostExecute(ArrayList<SportChildSceneBean> result) {
            super.onPostExecute(result);
            //PageCount++;
            footerView.setVisibility(View.INVISIBLE);
            sports.notifyDataSetChanged();
        }
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            headlineImage.setImageResource(R.drawable.blank_headline);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            try {
                headlineImage.setImageBitmap(result);
                if (!Helper.isImageLoaded) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.abc_slide_in_bottom);
                    headlineImage.startAnimation(animation);
                    Helper.isImageLoaded = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
