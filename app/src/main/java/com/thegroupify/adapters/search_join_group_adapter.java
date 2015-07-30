package com.thegroupify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thegroupify.R;
import com.thegroupify.library.library;

import java.util.ArrayList;

/**
 * Created by pravaeen kumar new on 30-07-15.
 */
public class search_join_group_adapter extends BaseAdapter {
    ArrayList<temp> data;
    Context context;

    public search_join_group_adapter(ArrayList<temp> data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_join_group_list, parent,false);
        }
        temp t = (temp)this.data.get(position);
        TextView name = (TextView)convertView.findViewById(R.id.group_name);
        name.setText(t.str_get("name"));
        Button join = (Button)convertView.findViewById(R.id.group_join);
        this.library().developerError("user exist "+t.str_get("user_exist"));
        if(t.str_get("user_exist") != null){
            join.setTag(""+t.str_get("id"));
            join.setOnClickListener((View.OnClickListener) this);
        }
        return convertView;
    }
    public library library(){
        return new library();
    }
}
