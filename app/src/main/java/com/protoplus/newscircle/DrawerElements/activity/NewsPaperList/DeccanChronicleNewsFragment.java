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
import com.protoplus.newscircle.newscircle.MainContent.DeccanChronicleMainContent;

import org.ccil.cowan.tagsoup.jaxp.SAXParserImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aakash on 10/10/2015.
 */
public class DeccanChronicleNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IGetNotification {
    int counter=0;
    int Minimum_Value=0;
    int page_no = 0;
    int position;
    int span_Counter = 0;
    private String category;
    private final int web_index = 4;
    private String urlString;
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
    SportChildSceneBean bean,lastBean;
    int view_content = 0,bean_counter =0;
    ListView listView;
    View rootView;
    public static Sports sports;

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
        System.out.println("(:) (::) (:::) (::) (:)THE URL STRING IS : "+urlString);
        Minimum_Value = 0;
        this.cat_index = cat_index;
        this.urlString = urlString;
        this.category = category;
        new myTask(context).execute();
    }
    private class myTask extends AsyncTask<String,String,String> {

        Context context;

        public myTask(Context context) {
            if (context != null) {
                Minimum_Value = 0;
                counter = 0;
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
                if (refresh_ar.size() > 1) {
                    SportChildSceneBean sportBean = refresh_ar.get(1);
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
                    }catch(Exception e){
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
                SAXParserImpl.newInstance(null).parse(new URL(urlString+"?page=0").openConnection().getInputStream(), new DefaultHandler(){
                    int span_Counter = 0;

                    boolean pubDate = false,Description = false ,title = false,view_content_flag = true;
                    String titletxt = "";
                    public void startElement(String uri, String localName,
                                             String name, Attributes a)
                    {

                        if(name.equals("div")){
                            String s = a.getValue("class");

                            if(s!=null){
                                if(s.equals("grid-16 alpha panel-content-left")){
                                    System.out.println("view content flag true ");
                                    view_content_flag = true;
                                }
                                else if(s.contains("views-row views-row-") && view_content_flag ){
                                    if(s.equals("views-row views-row-8 views-row-even views-row-last")){
                                        System.out.println("got last tag");
                                        lastBean = new SportChildSceneBean();
                                        end = true;
                                    }
                                    bean_counter++;
                                    bean = new SportChildSceneBean();
                                }

                            }
                        }
        	/*______________++++++++++++++__________________*/
                        else if(name.equals("a") && bean!=null){
                            bean.setPostId(a.getValue("href"));
                            if(end){
                                System.out.println("link is ::::: "+a.getValue("href"));
                                lastBean.setPostId(a.getValue("href"));
                            }
                            System.out.println("title is true ::::::::::::::::::");
                            title = true;
                        }

                        else if(name.equals("img")  && bean!=null){
                            bean.setImageLink(a.getValue("src"));
                            if(end){
                                System.out.println("image Link is ::::: "+a.getValue("src"));
                                lastBean.setImageLink(a.getValue("src"));
                            }
                        }

                        else if(name.equals("span") && bean!=null){
                            if(span_Counter%2==0){
                                span_Counter++;
                                pubDate = true;
                            }
                            else {
                                span_Counter++;
                                Description = true;
                            }
                        }


                    }
                    public void characters(char[] arg0, int arg1, int arg2)  {
                        if(pubDate && bean !=null){
                            bean.setPubDate(new String(arg0, arg1, arg2));
                            if(end){
                                System.out.println("publication date ::: "+new String(arg0,arg1,arg2));
                                lastBean.setPubDate(new String(arg0, arg1, arg2));
                            }
                            pubDate = false;
                        }
                        else if(Description && bean !=null){
                            bean.setDescription(new String(arg0,arg1,arg2));
                            if(end){
                                System.out.println("description is ::: "+new String(arg0,arg1,arg2));
                                lastBean.setDescription(new String(arg0,arg1,arg2));
                            }
                            Description = false;
                        }
                        else if(title && bean !=null){
                            /*String titleTxt = new String(arg0,arg1,arg2);*/
                            titletxt = titletxt + new String(arg0,arg1,arg2);
                            bean.setTitle(titletxt);
                            if(end){
                                System.out.println("title ::: "+titletxt);
                                lastBean.setTitle(titletxt);
                            }
                            System.out.println("the title text is set :::::: " + titletxt);
                            title = false;
                        }

                    }

                    public void endElement(String uri, String localName, String qName) throws SAXException {
                        if(Helper.isAppOpen){
                            if(bean.getTitle().equals(ar.get(0).getTitle())){
                                ContextData.getMain_ar().set(position, ar);
                                throw new SAXException();
                            }
                        }
                        if(qName.equals("span")){

                            if(span_Counter%2==0){
                                pubDate = false;
                            }
                            else {
                                if(!Helper.isAppOpen && bean.getTitle()!=null){
                                    try {
                                        HeadLineTitle = Helper.ReadHeadLineData(mPrefs, web_index,cat_index).split("&&&&")[1];
                                        System.out.println("....> DeccanChronical  __" + bean.getTitle() + "\n__" + HeadLineTitle + "___" + HeadLineTitle.contains(bean.getTitle()));
                                        if (bean.getTitle().contains(HeadLineTitle) || HeadLineTitle.contains(bean.getTitle())) {
                                            Helper.WriteHeadLineData(mPrefs, web_index,cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                            throw new SAXException();
                                        } else if (bean != null) {
                                            Description = false;
                                            bean.setMainContentClass(DeccanChronicleMainContent.class.getName());
                                            bean.setCategory(category);

                                            refresh_ar.add(bean);
                                        }
                                        bean = null;
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                        System.out.println("......... In catch Block       ______         ..................");
                                        System.out.println("Counter is .......... =" + startIndex);
                                        /*startIndex++;
                                        if (startIndex > 2) {
                                            System.out.println("..........Throwing SAXException ");
                                            throw new SAXException();
                                        } else*/ if (bean != null) {
                                            Helper.WriteHeadLineData(mPrefs, web_index, cat_index, bean.getImageLink() + "&&&&" + bean.getTitle());
                                            System.out.println("...........//   The Bean Image Link is  .. " + Helper.ReadHeadLineData(mPrefs, web_index, cat_index).split("&&&&")[0] + "..: The Bean title is : " + Helper.ReadHeadLineData(mPrefs, web_index, cat_index).split("&&&&")[1]);
                                            Description = false;
                                            bean.setMainContentClass(DeccanChronicleMainContent.class.getName());
                                            bean.setCategory(category);
                                            System.out.println("=====================  The Bean Details ============================" +
                                                "\n+++ Title :  " + bean.title + "" +
                                                "\n+++ Post ID : " + bean.postId + "" +
                                                "\n+++ Description : " + bean.description + "" +
                                                "\n+++ Image Link : " + bean.imageLink + "\n" + bean.mainImageLink);
                                            refresh_ar.add(bean);
                                            throw new SAXException();

                                        }

                                    }

                                /*___________________________________________________________*/
                                }else{
                                    Description = false;
                                    if(end){
                                        end=false;bean=null;view_content_flag=false;
                                        lastBean.setMainContentClass(DeccanChronicleMainContent.class.getName());

                                        refresh_ar.add(lastBean);
                                    }
                                    if(bean!=null){
                                        bean.setMainContentClass(DeccanChronicleMainContent.class.getName());

                                        refresh_ar.add(bean);
                                    }
                                }




                            }
                        } else if(qName.equals("a")){
                            title = false;
                        }




                    }
                    @Override
                    public void endDocument() throws SAXException {
                        super.endDocument();
                        end=true;
                    }

                });
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

/*==================================================================*/

    public DeccanChronicleNewsFragment(){
        if(Helper.isAppOpen){
            urlString=Helper.getCurrentURL();
            System.out.println("...................>>>>>>>>>>>>> The Current URL is  : " + urlString);

            position= ContextData.getPosition();
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
    private class NewTabHandler extends DefaultHandler{


        boolean pubDate = false,Description = false ,title = false,view_content_flag = false,end = false;
        String titletxt = "";
        public void startElement(String uri, String localName,
                                 String name, Attributes a)
        {
            if(name.equals("div")){
                String s = a.getValue("class");

                if(s!=null){
                    if(s.equals("grid-16 alpha panel-content-left")){
                        System.out.println("view content flag true ");
                        view_content_flag = true;
                    }
                    else if(s.contains("views-row views-row-") && view_content_flag ){
                        if(s.equals("views-row views-row-8 views-row-even views-row-last")){
                            System.out.println("got last tag");
                            lastBean = new SportChildSceneBean();
                            end = true;
                        }
                        bean_counter++;
                        bean = new SportChildSceneBean();
                    }

                }
            }
        	/*______________++++++++++++++__________________*/
            else if(name.equals("a") && bean!=null){
                bean.setPostId(a.getValue("href"));
                if(end){
                    System.out.println("link is ::::: "+a.getValue("href"));
                    lastBean.setPostId(a.getValue("href"));
                }
                System.out.println("title is true ::::::::::::::::::");
                titletxt = "";
                title = true;
            }

            else if(name.equals("img")  && bean!=null){
                bean.setImageLink(a.getValue("src"));
                if(end){
                    System.out.println("image Link is ::::: "+a.getValue("src"));
                    lastBean.setImageLink(a.getValue("src"));
                }
            }

            else if(name.equals("span") && bean!=null){
                if(span_Counter%2==0){
                    span_Counter++;
                    pubDate = true;
                }
                else {
                    span_Counter++;
                    Description = true;
                }
            }

        }
        public void characters(char[] arg0, int arg1, int arg2)  {
            if(pubDate && bean !=null){
                bean.setPubDate(new String(arg0, arg1, arg2));
                if(end){
                    System.out.println("publication date ::: "+new String(arg0,arg1,arg2));
                    lastBean.setPubDate(new String(arg0, arg1, arg2));
                }
                pubDate = false;
            }
            else if(Description && bean !=null){
                bean.setDescription(new String(arg0,arg1,arg2));
                if(end){
                    System.out.println("description is ::: "+new String(arg0,arg1,arg2));
                    lastBean.setDescription(new String(arg0,arg1,arg2));
                }
                Description = false;
            }
            else if(title && bean !=null){
                titletxt = titletxt + new String(arg0,arg1,arg2);
                bean.setTitle(titletxt);
                if(end){
                    System.out.println("title ::: "+titletxt);
                    lastBean.setTitle(titletxt);
                }
                System.out.println("the title text is set :::::: "+titletxt);
                title = false;
            }
        }

        public void endElement(String uri, String localName, String qName)  {


            if(qName.equals("span")){

                if(span_Counter%2==0){
                    pubDate = false;
                }
                else {
                    Description = false;
                    if(end){
                        end=false;bean=null;view_content_flag=false;
                        lastBean.setMainContentClass(DeccanChronicleMainContent.class.getName());

                        ar.add(lastBean);
                    }
                    if(bean!=null){
                        bean.setMainContentClass(DeccanChronicleMainContent.class.getName());

                        ar.add(bean);
                    }
                }
            } else if(qName.equals("a")){
                title = false;
            }


        }



    }
    private class NewTabData{
        public NewTabData(){
            try {


                SAXParserImpl.newInstance(null).parse(new URL(urlString+"?page=0").openConnection().getInputStream(), new NewTabHandler());
            } catch (SAXException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class getList{
        public getList(){
            try {
                page_no++;span_Counter = 0 ;view_content = 0;bean_counter =0;
                System.out.println("***********\n\n"+urlString+"?page="+page_no);
                SAXParserImpl.newInstance(null).parse(new URL(urlString+"?page="+page_no).openConnection().getInputStream(), new NewTabHandler());
            } catch (SAXException e) {
                counter = 0;
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
                Intent it = new Intent(getActivity().getApplicationContext(), DeccanChronicleMainContent.class);
                ContextData.setDeccanChronicle_bean_array(ar);
                it.putExtra("index", arg2);
                if(ar.size()>0){
                    startActivity(it);
                }

            }
        });
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
            try{
                sports.notifyDataSetChanged();
            }catch(IllegalStateException e){
                e.printStackTrace();
            }
        }
    }

}
