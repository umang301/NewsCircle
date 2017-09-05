package com.protoplus.newscircle.newscircle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.protoplus.newscircle.R;

import org.w3c.dom.Text;

/**
 * Created by Aakash on 11/30/2015.
 */
public class AboutusFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ProgressBar loader;
    View v;
    TextView aboutus;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_aboutus,null);
        loader =(ProgressBar) v.findViewById(R.id.aboutuscenterLoader);
        aboutus = (TextView) v.findViewById(R.id.aboutusText);
        aboutus.setVisibility(View.GONE);
        new LoadData().execute("");
        return v;
    }
    private class LoadData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            aboutus.setVisibility(View.VISIBLE);
            loader.setVisibility(View.GONE);
        }
    }
}
