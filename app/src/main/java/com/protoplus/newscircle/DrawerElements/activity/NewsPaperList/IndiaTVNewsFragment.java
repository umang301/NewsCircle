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
import com.protoplus.newscircle.newscircle.MainContent.IndiaTvMainContent;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Aakash on 10/1/2015.
 */
public class IndiaTVNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IGetNotification {

    int counter=0;
    int Minimum_Value=0;
    int position;
    private final int web_index = 15;
    private String urlString;
    private String category;
    int cat_index;
    SharedPreferences mPrefs;
    String HeadLineTitle;
    int startIndex = 0;

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

    //    ProgressDialog pDialog;
/*=====================================================================*/
    public void onRefresh() {
        refreshTask = new myTask(null);
        refreshTask.execute();
    }
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
    public void getNotificationData(Context context, String urlString,int cat_index,String category) {
        System.out.println("(:) (::) (:::) (::) (:)In Times Of India I AM CHECKING WHETHER ANY UPDATION IS THERE OR NOT___________________");
        Minimum_Value = 0;
        this.cat_index = cat_index;
        this.category = category;
        this.urlString = urlString;
        new myTask(context).execute();
    }
    private class myTask extends AsyncTask<String,String,String> {
        Context context;

        public myTask(Context context) {
            if (context != null) {
                Minimum_Value = 0;
                counter = 0;
                System.out.println("...................>>>>>>>>>>>>> The Current URL is  : " + urlString);
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
            }else{
                if(refresh_ar.size()>0) {

                    ar.addAll(0, refresh_ar);
                    refresh_ar.clear();
                }

                if(!Helper.getisBack()){
                    try{
                        swipeLayout.setRefreshing(false);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                try{
                    sports.notifyDataSetChanged();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    private class refreshData{
        public refreshData(){
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(urlString, new DefaultHandler(){

                    boolean title=false,pubDate=false,link=false , description=false,subDescription=false,image_link=false;
                    String titleText="",postIdText="",image_linkText="";
                    int i=0;
                    int limit=0;
                    String image_url = null;
                    public void startElement(String uri, String localName,
                                             String qName, Attributes attributes) throws SAXException
                    {

                        if (qName.equals("item")){
                            if(limit<Minimum_Value){
                                limit++;
                                return;
                            }
                            image_url = "";
                            //insert=true;
                            bean = new SportChildSceneBean();
                        }
                        else if (qName.equals("title") ){
                            titleText="";
                            title = true;
                        }

                        else if (qName.equals("pubDate") && bean !=null) pubDate = true;

                        else if(qName.equals("link")){
                            postIdText = "";
                            link = true;
                        }

                        else if(qName.equals("description")) {
                            description = true;
                            subDescription=true;
                            descriptionText = "";image_linkText="";
                        }


                    }
                    public void endElement(String uri, String localName,
                                           String qName) throws SAXException
                    {

                        if (qName.equals("item") && bean!=null)
                        {
                            if(Helper.isAppOpen){
                                if(bean.getTitle().equals(ar.get(0).getTitle())){
                                    ContextData.getMain_ar().set(position, ar);
                                    throw new SAXException();
                                }
                                else  if(bean!=null){
                                    bean.setMainContentClass(IndiaTvMainContent.class.getName());

                                    refresh_ar.add(bean);
                                }
                                bean=null;
                            }else{
                                try {
                                    HeadLineTitle = Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[1];
                                    System.out.println("....> IndiaTV __" + bean.getTitle() + "\n__" + HeadLineTitle + "___" + HeadLineTitle.contains(bean.getTitle()));
                                    if (bean.getTitle().contains(HeadLineTitle) || HeadLineTitle.contains(bean.getTitle())) {
                                        Helper.WriteHeadLineData(mPrefs, web_index,cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                        throw new SAXException();
                                    } else if (bean != null) {
                                        bean.setMainContentClass(IndiaTvMainContent.class.getName());
                                        bean.setCategory(category);
                                        refresh_ar.add(bean);
                                    }
                                    bean = null;
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                    System.out.println("......... In catch Block       ______         ..................");
                                    System.out.println("Counter is .......... =" + startIndex);
                                    /*startIndex++;
                                    if (startIndex > 1) {
                                        System.out.println("..........Throwing SAXException ");
                                        throw new SAXException();
                                    } else*/ if (bean != null) {
                                        bean.setMainContentClass(IndiaTvMainContent.class.getName());
                                        bean.setCategory(category);
                                        Helper.WriteHeadLineData(mPrefs, web_index,cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                        System.out.println("...........//   The Bean Image Link is  .. " + Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[0] + "..: The Bean title is : " + Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[1]);
                                        refresh_ar.add(bean);
                                        throw new SAXException();

                                    }

                                }
                            }

                        }
                        else if(qName.equals("title")&& bean!=null){

                            title = false;
                        }
                        else if(qName.equals("pubDate")&& bean!=null){
                            pubDate = false;
                        }

                        else if(qName.equals("link")&& bean!=null) link = false;

                        else if(qName.equals("description") && bean!=null) description = false;


                    }

                    public void characters(char ch[], int start, int length)
                            throws SAXException
                    {
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
                        else if(link && bean != null){
                            postIdText=postIdText+new String(ch, start, length);
                            bean.setPostId(postIdText);
                        }
                        else if(description && bean != null){
                            String text1 = new String(ch, start, length);
                            descriptionText = descriptionText + text1;
                            image_linkText = image_linkText + text1;
                            if(descriptionText.contains("src=") && descriptionText.length()>=descriptionText.indexOf("src=")+53+5+8){
                               bean.setDescription(descriptionText.substring(descriptionText.indexOf("src=")+53+5+8, descriptionText.length()));
                            }
                            if(descriptionText.contains("src=") && descriptionText.contains(".jpg") && descriptionText.length()>=descriptionText.indexOf("src=")+5 && descriptionText.length()>=descriptionText.indexOf(".jpg")+4){
                                bean.setImageLink(descriptionText.substring(descriptionText.indexOf("src=")+5, descriptionText.indexOf(".jpg")+4));
                                bean.setMainImageLink(bean.getImageLink());
                            }
                        }
                    }
                    @Override
                    public void endDocument() throws SAXException {
                        super.endDocument();
                        end=true;
                    }

                });
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }   catch(Exception e){
                e.printStackTrace();
            }
        }
    }

/*==================================================================*/

    public IndiaTVNewsFragment(){
        if(Helper.isAppOpen){
            urlString=Helper.getCurrentURL();
            position=ContextData.getPosition();
            try{
                ar=ContextData.getMain_ar().get(position);

            }catch(NullPointerException nullpointer){
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
    private class NewTabData{
        public NewTabData(){
            try {

                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(urlString,new DefaultHandler(){

                    boolean title=false,pubDate=false,link=false , description=false,subDescription=false,image_link = false;
                    String titleText="",postIdText="",imageLinkText="";
                    int i=0;
                    int limit=0;
                    public void startElement(String uri, String localName,
                                             String qName, Attributes attributes) throws SAXException
                    {
                        if (limit<Minimum_Value){
                            if(qName.equals("item")){
                                limit++;
                                return;
                            }
                        }else{

                            if(qName.equals("item")){
                                bean = new SportChildSceneBean();
                            }
                            else if (qName.equals("title") ){
                                titleText="";
                                title = true;
                            }

                            else if (qName.equals("pubDate") && bean !=null) pubDate = true;

                            else if(qName.equals("link")){
                                postIdText = "";
                                link = true;
                            }

                            else if(qName.equals("description")) {
                                description = true;
                                subDescription = true;
                                descriptionText = "";imageLinkText="";
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

                                Minimum_Value+=6;
                                ContextData.getMain_ar().set(position, ar);
                                throw new SAXException();
                            }
                            bean.setMainContentClass(IndiaTvMainContent.class.getName());

                            ar.add(bean);

                            bean=null;
                        }

                        else if(qName.equals("title")&& bean!=null){

                            title = false;
                        }
                        else if(qName.equals("pubDate")&& bean!=null){
                            pubDate = false;
                        }

                        else if(qName.equals("link")&& bean!=null) link = false;

                        else if(qName.equals("description") && bean!=null) description = false;


                    }

                    public void characters(char ch[], int start, int length)
                            throws SAXException
                    {
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
                        else if(link && bean != null){
                            postIdText=postIdText+new String(ch, start, length);
                            bean.setPostId(postIdText);
                        }
                        else if(description && bean != null){
                            String text1 = new String(ch, start, length);
                            descriptionText = descriptionText + text1;
                            imageLinkText = imageLinkText + text1;
                            if(descriptionText.contains("src=") && descriptionText.length()>=descriptionText.indexOf("src=")+53+5+8){
                                bean.setDescription(descriptionText.substring(descriptionText.indexOf("src=")+53+5+8, descriptionText.length()));
                            }
                            if(descriptionText.contains("src=") && descriptionText.contains(".jpg") && descriptionText.length()>=descriptionText.indexOf("src=")+5 && descriptionText.length()>=descriptionText.indexOf(".jpg")+4){
                                bean.setImageLink(descriptionText.substring(descriptionText.indexOf("src=")+5, descriptionText.indexOf(".jpg")+4));
                                bean.setMainImageLink(bean.getImageLink());
                            }
                        }

                    }
                    @Override
                    public void endDocument() throws SAXException {
                        super.endDocument();
                        end=true;
                    }

                });
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class ListIEHandler extends DefaultHandler {
        public boolean title=false,pubDate=false,link=false , description=false,subDescription=false,image_link=false;
        public String descriptionText = "",image_link_text="";
        public SportChildSceneBean bean;
        public String titleText="",postIdText="";
        public int counter = 0,i=0;
        public int limit = 0;
        public String image_url = null;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if(limit< Minimum_Value){
                if(qName.equals("item"))
                {
                    limit++;
                    return;
                }
            }else if(!end){
                if(qName.equals("item")){
                    bean = new SportChildSceneBean();
                }
                else if (qName.equals("title") ){
                    titleText="";
                    title = true;
                }

                else if (qName.equals("pubDate") && bean !=null) pubDate = true;

                else if(qName.equals("link")){
                    postIdText = "";
                    link = true;
                }

                else if(qName.equals("description")) {
                    description = true;
                    subDescription = true;
                    descriptionText = "";image_link_text="";
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
            else if(link && bean != null){
                postIdText=postIdText+new String(ch, start, length);
                bean.setPostId(postIdText);
            }
            else if(description && bean != null){
                String text1 = new String(ch, start, length);
                descriptionText = descriptionText + text1;
                image_link_text = image_link_text +text1;
                if(descriptionText.contains("src=") && descriptionText.length()>=descriptionText.indexOf("src=")+53+5+8){
                    bean.setDescription(descriptionText.substring(descriptionText.indexOf("src=")+53+5+8, descriptionText.length()));
                }
                if(descriptionText.contains("src=") && descriptionText.contains(".jpg") && descriptionText.length()>=descriptionText.indexOf("src=")+5 && descriptionText.length()>=descriptionText.indexOf(".jpg")+4){
                    bean.setImageLink(descriptionText.substring(descriptionText.indexOf("src=")+5, descriptionText.indexOf(".jpg")+4));
                    bean.setMainImageLink(bean.getImageLink());
                }
            }

        }

        public void endElement(String uri, String localName,
                               String qName) throws SAXException
        {

            if (qName.equals("item") && bean!=null)
            {
                //if(insert){
                if(counter>=6){
                    int ar_index=0;
                    ContextData.getMain_ar().set(position, ar);
                    throw new SAXException();
                }else{
                    counter++;
                }
                bean.setMainContentClass(IndiaTvMainContent.class.getName());

                ar.add(bean);
                bean=null;
                //}

            }
            else if(qName.equals("title")&& bean!=null){

                title = false;
            }
            else if(qName.equals("pubDate")&& bean!=null){
                pubDate = false;
            }

            else if(qName.equals("link")&& bean!=null) link = false;

            else if(qName.equals("description") && bean!=null) description = false;


        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            end=true;
        }
    }
    private class getList{
        public getList(){
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(urlString, new ListIEHandler());
            } catch (SAXException e) {
                Minimum_Value+=6;
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
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
                Intent it = new Intent(getActivity().getApplicationContext(), IndiaTvMainContent.class);
                ContextData.setIndiaTV_bean_array(ar);
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
