package com.protoplus.newscircle.DrawerElements.activity.NewsPaperList;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.CustomLists.Sports;
import com.protoplus.newscircle.MyAlarmService;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.CircularProgressBar;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.IGetNotification;
import com.protoplus.newscircle.newscircle.MainContent.DeccanHeraldMainContent;

import org.ccil.cowan.tagsoup.jaxp.SAXParserImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aakash on 10/2/2015.
 */
public class DeccanheraldNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IGetNotification {
    int counter=0;
    int Minimum_Value=0;

    int page_no = 0;
    int position;
    private String category;
    private final int web_index = 5;
    int cat_index;
    SharedPreferences mPrefs;
    String HeadLineTitle;
    int startIndex = 0;

    int cid;
    boolean end=false;
    NewTab newtab=new NewTab();
    LoadData loadData;
    myTask refreshTask;
    String descriptionText="";
    SwipeRefreshLayout swipeLayout;
    ArrayList<SportChildSceneBean> ar , refresh_ar=new ArrayList<SportChildSceneBean>();
    SportChildSceneBean bean;
    ListView listView;
    View rootView;
    public static Sports sports;
    private int div_index = 1;
    // TODO copy this methods
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        final SearchManager searchManager = (SearchManager) getActivity().getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.news_tabs_action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Query : " + query);
                Helper.setSearch_result(ar);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("newText : "+newText);
                final ArrayList<String> suggestions = new ArrayList<String>();
                for (SportChildSceneBean bean:
                        ar) {
                    if(bean.getTitle().contains(newText) || bean.getTitle().toLowerCase().contains(newText.toLowerCase())){
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
                // TODO Auto-generated method stub
                System.out.println(" On Focus Change");
            }
        });

    }

    @Override
    public void getNotificationData(Context context, String urlString,int cat_index, String category) {
        System.out.println("(:) (::) (:::) (::) (:)In Deccan Herald I AM CHECKING WHETHER ANY UPDATION IS THERE OR NOT___________________");
        Minimum_Value = 0;
        this.cat_index = cat_index;
        this.cid = Integer.parseInt(urlString);
        this.category=category;
        new myTask(context).execute();
    }

    /*=====================================================================*/
    public void onRefresh() {
        refreshTask = new myTask(null);
        refreshTask.execute();
    }

    private class myTask extends AsyncTask<String,String,String> {
        Context context;
        public myTask(Context context){
            if (context != null) {
                Minimum_Value = 0;
                counter = 0;
                System.out.println("...................>>>>>>>>>>>>> The Current URL is  : " + "http://www.deccanherald.com/more_category_news.php?page=0&cid="+cid);
                mPrefs = context.getSharedPreferences(Helper.MyPREFERENCES, context.MODE_PRIVATE);
                this.context = context;
            }
        }
        @Override
        protected String doInBackground(String... params) {
            new refreshData();
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            if(!Helper.isAppOpen){
                if (refresh_ar.size() > 0) {
                    SportChildSceneBean sportBean = refresh_ar.get(0);

                    Helper.WriteHeadLineData(mPrefs, web_index, cat_index, sportBean.imageLink + "&&&&" + sportBean.title);
                    Intent service1 = new Intent(context, MyAlarmService.class);
                    System.out.println("intent is : " + service1 + "\n\n____>THE OBJECT IS : " + sportBean + " >.....and title is : " + sportBean.getTitle());
                    service1.putExtra("NotificationData", sportBean);
                    service1.putExtra("web_index", web_index);
                    service1.putExtra("cat_index", cat_index);
                    context.startService(service1);
                }
            }
            else{
                if(refresh_ar.size()>0) {
                    ar.addAll(0, refresh_ar);
                    refresh_ar.clear();
                }

                if(!Helper.getisBack()){
                    swipeLayout.setRefreshing(false);
                }

                try{
                    sports.notifyDataSetChanged();
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }
            }

        }
    }

    private class refreshData{
        public refreshData(){
            try {
                SAXParserImpl.newInstance(null).parse(new URL("http://www.deccanherald.com/more_category_news.php?page=0&cid="+cid).openConnection().getInputStream(), new NewTabHandler() {

                    boolean title = false, pubDate = false, link = false, description = false, image_link = false;
                    String titleText = "", postIdText = "", image_linkText = "";
                    int i = 0;
                    int limit = 0;
                    String image_url = null;

                    public void startElement(String uri, String localName,
                                             String name, Attributes a) {

                        if (name.equalsIgnoreCase("div")) {
                            String s = a.getValue("class");
                            if (s != null && s.equals("categoryNewsText")) {
                                System.out.println("THIS IS DIV BEAN TAG");
                                bean = new SportChildSceneBean();
                                imageLink = true;
                            }
                        } else if (name.equalsIgnoreCase("i") && bean != null) {
                            System.out.println("in pubdate tag");
                            pubDate = true;
                        } else if (name.equalsIgnoreCase("img") && imageLink) {
                            String s = a.getValue("src");
                            if (s != null && bean != null) {
                                System.out.println("imageLink : " + s);
                                bean.setImageLink(s);
                            }
                        } else if (name.equalsIgnoreCase("a") && bean != null) {
                            System.out.println("in post id tag");
                            title = true;
                            System.out.println("THE POST ID IS : : " + a.getValue("href"));
                            bean.setPostId("http://www.deccanherald.com" + a.getValue("href"));
                        } else if (name.equalsIgnoreCase("p") && bean != null) {
                            description = true;
                            System.out.println("In description tag");
                            descriptionText = "";
                        }
                    }

                    public void characters(char ch[], int start, int length)
                            throws SAXException {
                        if (title) {
                            bean.setTitle(new String(ch, start, length));
                        } else if (description) {
                            descriptionText = descriptionText + new String(ch, start, length);
                            bean.setDescription(descriptionText);
                        } else if (pubDate) {
                            bean.setPubDate(new String(ch, start, length));
                        }
                    }

                    public void endElement(String uri, String localName,
                                           String qName) throws SAXException {

                        if (qName.equals("div") && bean != null) {
                            //if(insert){
                            div_index++;
                            if(div_index%2!=0){
                                if(Helper.isAppOpen){
                                    if (bean.getTitle().equals(ar.get(0).getTitle())) {
                                        ContextData.getMain_ar().set(position, ar);
                                        throw new SAXException();
                                    } else if (bean != null) {
                                        bean.setMainContentClass(DeccanHeraldMainContent.class.getName());

                                        refresh_ar.add(bean);
                                    }
                                    bean = null;
                                }else{
                                    try {
                                        HeadLineTitle = Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[1];
                                        System.out.println("....> Times of India __" + bean.getTitle() + "\n__" + HeadLineTitle + "___" + HeadLineTitle.contains(bean.getTitle()));
                                        if (bean.getTitle().contains(HeadLineTitle) || HeadLineTitle.contains(bean.getTitle())) {
                                            Helper.WriteHeadLineData(mPrefs, web_index,cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                            throw new SAXException();
                                        } else if (bean != null) {
                                            bean.setMainContentClass(DeccanHeraldMainContent.class.getName());
                                            bean.setCategory(category);
                                            refresh_ar.add(bean);
                                        }
                                        bean = null;
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                        System.out.println("......... In catch Block       ______         ..................");
                                        System.out.println("Counter is .......... =" + startIndex);
                                        startIndex++;
                                        if (startIndex > 1) {
                                            System.out.println("..........Throwing SAXException ");
                                            throw new SAXException();
                                        } else if (bean != null) {
                                            bean.setMainContentClass(DeccanHeraldMainContent.class.getName());
                                            bean.setCategory(category);
                                            Helper.WriteHeadLineData(mPrefs, web_index, cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                            //System.out.println("...........//   The Bean Image Link is  .. " + Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[0] + "..: The Bean title is : " + Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[1]);

                                            refresh_ar.add(bean);
                                            bean = null;
                                        }

                                    }
                                }
                            }
                            //}
                        } else if(qName.equals("img") && bean!=null){
                            System.out.println(" End Tag : Image ");
                            imageLink = false;
                        }
                        else if(qName.equals("a") && bean!=null){
                            System.out.println("End Tag : Title");
                            title = false;
                        } else if(qName.equals("p") && bean != null){
                            System.out.println("End Tag : description");
                            description = false;

                        } else if(qName.equals("i") && bean!=null){
                            System.out.println("End Tag : publication Date");
                            pubDate = false;
                        }


                    }

                    @Override
                    public void endDocument() throws SAXException {
                        super.endDocument();
                        end = true;
                    }

                });
            }  catch (SAXException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

/*==================================================================*/

    public DeccanheraldNewsFragment() {

        System.out.println("...................._________" +
                "\nThe Helper.fromReciever Variable is _____________............." +
                "\n:  " + Helper.fromReceiver + "\n.....>>>>>>>>>>>>>>>>>>>>>>>");

        if(Helper.isAppOpen){
            cid = Helper.getCid();
            position = ContextData.getPosition();
            try {
                ar = ContextData.getMain_ar().get(position);

            } catch (NullPointerException nullpointer) {
                ar = new ArrayList<>();
                nullpointer.printStackTrace();
            }

            if(sports!=null){sports.notifyDataSetChanged();}
            if(ar.size()>0){
                Minimum_Value=ar.size();
            }else{
                Minimum_Value=0;
            }
            newtab.execute("");
        }

    }
    private class NewTab extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            new NewTabData();
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(centerLoader!=null){
                centerLoader.setVisibility(View.GONE);
            }
            if(swipeLayout!=null){
                swipeLayout.setVisibility(View.VISIBLE);
            }

            if(footerView!=null){
                footerView.setVisibility(View.GONE);
            }
        }
    }
    private class NewTabHandler extends DefaultHandler{
        public boolean title=false,pubDate=false,link=false , description=false, imageLink = false;

        public String DescriptionText = "";
        //String text;

        public void startElement(String uri, String localName,
                                 String name, Attributes a)
        {
            if (name.equalsIgnoreCase("div")){
                String s= a.getValue("class");

                if(s!=null && s.equals("categoryNewsText")){
                    System.out.println("THIS IS DIV BEAN TAG");

                    bean = new SportChildSceneBean();
                    imageLink = true;
                }
            }
            else if(name.equalsIgnoreCase("i") && bean!=null){
                System.out.println("in pubdate tag");
                pubDate = true;
            }
            else if(name.equalsIgnoreCase("img") && imageLink ){
                String s = a.getValue("src");
                if(s!=null && bean!=null){
                    System.out.println("imageLink : " + s);
                    bean.setImageLink(s);
                }
            }
            else if(name.equalsIgnoreCase("a") && bean!=null){
                System.out.println("in post id tag");
                title = true;
                System.out.println("THE POST ID IS : : "+a.getValue("href"));
                bean.setPostId("http://www.deccanherald.com"+a.getValue("href"));
            }
            else if(name.equalsIgnoreCase("p") && bean!=null){
                description = true ;
                System.out.println("In description tag");
                descriptionText = "";
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            if(title){
                bean.setTitle(new String(ch,start,length));
            }
            else if(description){
                descriptionText = descriptionText + new String(ch,start,length);
                bean.setDescription(descriptionText);
            }
            else if(pubDate){
                bean.setPubDate(new String(ch,start,length));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(qName.equalsIgnoreCase("div") && bean!=null ){
                div_index++;
                if(div_index%2!=0){
                    System.out.println("Div tag is ended : uri : "+uri+" , localName : "+localName);
                    if(counter>=10){
                        ContextData.getMain_ar().set(position, ar);
                        throw new SAXException();
                    }else{
                        counter++;
                    }
                    bean.setMainContentClass(DeccanHeraldMainContent.class.getName());

                    ar.add(bean);


                    bean=null;
                }

            }else if(qName.equals("img") && bean!=null){
                System.out.println(" End Tag : Image ");
                imageLink = false;
            }
            else if(qName.equals("a") && bean!=null){
                System.out.println("End Tag : Title");
                title = false;
            } else if(qName.equals("p") && bean != null){
                System.out.println("End Tag : description");
                description = false;

            } else if(qName.equals("i") && bean!=null){
                System.out.println("End Tag : publication Date");
                pubDate = false;
            }
        }

    }
    private class NewTabData{
        public NewTabData(){
            try{
                System.out.println("Page No is :" + page_no);
                SAXParserImpl.newInstance(null).parse(new URL("http://www.deccanherald.com/more_category_news.php?page=0&cid="+cid).openConnection().getInputStream(),
                        new NewTabHandler());
            }catch(SAXException e){
                e.printStackTrace();
                page_no +=1;
            } catch (MalformedURLException e1){
                e1.printStackTrace();
            } catch(IOException io){
                io.printStackTrace();
            }

        }
    }

    private class getList{
        public getList(){
            try {
                System.out.println(" THE PAGE No Is : "+page_no);
                counter = 0;page_no +=1;div_index=1;
                SAXParserImpl.newInstance(null).parse(new URL("http://www.deccanherald.com/more_category_news.php?page=" + page_no + "&cid="+cid).openConnection().getInputStream(), new NewTabHandler());
            } catch (SAXException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        ((ViewGroup)rootView).removeAllViews();
        newtab.cancel(true);
        if(loadData!=null){
            loadData.cancel(true);
        }
        if(refreshTask!=null){
            refreshTask.cancel(true);
            if(swipeLayout!=null){
                swipeLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Helper.isAppOpen = true;
        if(Helper.header!=null && Helper.getisBack()){
            new myTask(null).execute("");
            Helper.setIsBack(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Helper.isAppOpen = true;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Helper.isAppOpen = false;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        setRetainInstance(true);
    }
    private View footerView;
    private CircularProgressBar centerLoader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_headline, container, false);
        centerLoader = (CircularProgressBar) rootView.findViewById(R.id.centerLoader);
        centerLoader.setVisibility(View.VISIBLE);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setVisibility(View.GONE);
        swipeLayout.setColorSchemeResources(R.color.gplus_color_1);
        swipeLayout.setOnRefreshListener(this);
        sports=new Sports(getActivity().getApplicationContext(), R.layout.fragment_headline, ar);
        listView=(ListView) rootView.findViewById(R.id.listView1);
        listView.setAdapter(sports);

        footerView = ((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
        listView.addFooterView(footerView);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private LoadData getLoadData() {
                loadData = new LoadData();
                return loadData;
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = listView.getCount();
                if (scrollState == SCROLL_STATE_IDLE) {

                    if (listView.getLastVisiblePosition() >= count - threshold) {
                        if (!end) getLoadData().execute("");
                    }
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
                Intent it = new Intent(getActivity().getApplicationContext(), DeccanHeraldMainContent.class);
                ContextData.setDeccanHerald_bean_array(ar);
                it.putExtra("index", arg2);
                if(ar.size()>0){
                    startActivity(it);
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    private class LoadData extends AsyncTask<String, String,ArrayList<SportChildSceneBean>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            footerView.setVisibility(View.VISIBLE);
        }
        @Override
        protected ArrayList<SportChildSceneBean> doInBackground(String... params) {
            new getList();
            return ar;
        }
        @Override
        protected void onPostExecute(ArrayList<SportChildSceneBean> result) {
            super.onPostExecute(result);
            footerView.setVisibility(View.GONE);
            //PageCount++;
            try{
                sports.notifyDataSetChanged();
            }catch(IllegalStateException e){
                e.printStackTrace();
            }
        }
    }

}
