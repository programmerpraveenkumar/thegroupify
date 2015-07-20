package com.thegroupify.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thegroupify.R;

/**
 * Created by pravaeen kumar new on 19-07-15.
 */
public class login extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login,container,false);
        return v;
    }
}
