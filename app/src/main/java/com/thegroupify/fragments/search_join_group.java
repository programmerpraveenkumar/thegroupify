package com.thegroupify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thegroupify.R;
import com.thegroupify.adapters.search_join_group_adapter;
import com.thegroupify.adapters.temp;
import com.thegroupify.library.getServerResult;
import com.thegroupify.library.library;
import com.thegroupify.library.serverConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pravaeen kumar new on 27-07-15.
 */
public class search_join_group extends Fragment implements getServerResult,View.OnClickListener {
    TextView loader = null;
    ListView group_search_list =null;
    HashMap<String,String> urlParameter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_join_group,container,false);
        urlParameter  = new HashMap<String, String>();
        loader = (TextView)v.findViewById(R.id.loader);
        group_search_list = (ListView)v.findViewById(R.id.group_list);
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
            case R.id.group_search_ok:
                EditText group_input = (EditText)getActivity().findViewById(R.id.group_input);
                if(group_input.getText().length() >4){
                    group_search_list.setAdapter(null);
                    loader.setVisibility(View.VISIBLE);
                    urlParameter.put("search_content", group_input.getText().toString().trim());
                    urlParameter.put("user_id", this.library().getUserId());
                    //TOdo loader
                    loader.setVisibility(View.VISIBLE);
                    new serverConnection(R.string.s_result_group_search,this,"group/search",urlParameter);
                    urlParameter.clear();
                }else{
                    this.library().toastMessage("minimum characters 4");
                }
            break;
            case R.id.group_join:
                this.library().developerError("group join click lister");
            break;
        }
    }

    @Override
    public void serverResult(int type, String output) {
        if(output != null){
            switch (type){
                case R.string.s_result_group_search:
                    ArrayList<temp> data = new ArrayList<temp>();
                    group_search_list.setAdapter(null);
                    try {
                        JSONArray array = new JSONArray(output);
                        for(int i = 0;i<array.length();i++){
                            JSONObject obj = array.getJSONObject(i);
                            data.add(new temp(obj.getString("id"),obj.getString("name"),obj.getString("exist")));
                        }
                    }catch (Exception e){
                        this.library().developerError("error while parsing "+e.getMessage());
                    }
                    loader.setVisibility(View.GONE);
                    search_join_group_adapter adapter = new search_join_group_adapter(data,getActivity());
                    group_search_list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }else{
            this.library().toastMessage("Nothing is received from server");
        }

    }
}
