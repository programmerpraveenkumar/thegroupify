package com.thegroupify.adapters;

/**
 * Created by pravaeen kumar new on 30-07-15.
 */
public class temp {
    String id,name,user_exist;
    public temp(String id,String name,String user_exist){
        this.id = id;
        this.name = name;
        this.user_exist = user_exist;
    }
    public String str_get(String type){
        if(type == "id"){
            return this.id;
        }else if(type == "name"){
            return this.name;
        }else if(type == "user_exist"){
                return this.user_exist;
        }
        return null;
    }

}

