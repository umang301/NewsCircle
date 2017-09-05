package com.protoplus.newscircle.newscircle;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.CustomLists.Sports;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.ImageLoader;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView search_list;
    ArrayList<SportChildSceneBean> search_result_ar = Helper.getSearch_result();
    ArrayList<SportChildSceneBean> search_filter_ar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.setSubActivityUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // TODO set Toolbar
        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("Search Result");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search_list = (ListView) findViewById(R.id.search_result_list);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void filterArray(String query) {

        for (SportChildSceneBean bean :
                search_result_ar) {
            if (bean.getTitle().contains(query) || bean.getTitle().toLowerCase().contains(query)) {
                search_filter_ar.add(bean);
            }
        }

    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            filterArray(query);
            if (search_filter_ar.size() == 0) {
                ((RelativeLayout) findViewById(R.id.no_search_txt)).setVisibility(View.VISIBLE);
            }

            Sports adapter = new Sports(getApplicationContext(), R.layout.activity_search_result, search_filter_ar);
            search_list.setAdapter(adapter);
            search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Class c = Class.forName(search_filter_ar.get(position).getMainContentClass());
                        Activity a = (Activity) c.newInstance();
                        Intent intent = new Intent(SearchResultActivity.this, a.getClass());
                        intent.putExtra("ItemObject", search_filter_ar.get(position));
                        intent.putExtra("search_result_flag", true);
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            //use the query to search
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            //preBackProcess();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
