package com.thegroupify.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thegroupify.R;
import com.thegroupify.library.library;
import com.thegroupify.library.getServerResult;

/**
 * Created by pravaeen kumar new on 19-07-15.
 */
public class feeds extends Fragment implements getServerResult {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feeds,container,false);
        this.library().developerError("user id"+this.library().getUserId());
        TextView loader = (TextView)v.findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);
        return v;
    }
    public library library(){
        return new library();
    }

    @Override
    public void serverResult(int type, String output) {

    }
}
