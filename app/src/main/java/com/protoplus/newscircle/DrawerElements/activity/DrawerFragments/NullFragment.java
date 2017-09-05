package com.protoplus.newscircle.DrawerElements.activity.DrawerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.protoplus.newscircle.R;

/**
 * Created by Aakash on 12/7/2015.
 */
public class NullFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nulldata,null);
        return v;
    }
}
