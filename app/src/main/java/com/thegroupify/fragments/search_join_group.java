package com.thegroupify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thegroupify.R;
import com.thegroupify.library.getServerResult;
import com.thegroupify.library.library;
import com.thegroupify.library.serverConnection;

import java.util.HashMap;

/**
 * Created by pravaeen kumar new on 27-07-15.
 */
public class search_join_group extends Fragment implements getServerResult,View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_join_group,container,false);
        //this.library().developerError("user id"+this.library().getUserId());
        Button group_ok = (Button)v.findViewById(R.id.group_search_ok);
        group_ok.setOnClickListener(this);
        /*TextView loader = (TextView)v.findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);*/
        return v;
    }

    public library library(){
        return new library(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.group_input:
                EditText group_input = (EditText)getActivity().findViewById(R.id.group_input);
                if(group_input.getText().length() >4){
                    HashMap<String,String> create = new HashMap<String, String>();
                    create.put("email", group_input.getText().toString());
                    //TOdo loader
                    new serverConnection(R.string.s_result_group_search,this,"group/groupsearch",create);
                }else{
                    this.library().toastMessage("minimum characters 4");
                }

            break;
        }


    }

    @Override
    public void serverResult(int type, String output) {

    }
}
