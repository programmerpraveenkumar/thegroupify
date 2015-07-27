package com.thegroupify.library;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by pravaeen kumar new on 21-07-15.
 */
public class library {
    Context c;
    public library(){

    }
    public library(Context c){
        this.c = c;
    }
    public void developerError(String message){
        Log.i("praveen",message);
    }
    public void showError(String message){
        this.dialog().errorDialog(message);
    }
    public dialogBox dialog(){
        return new dialogBox(this.c);
    }
    public void serverError(Boolean status,String message){
        if(status){
            this.developerError(message);
        }
    }
    public void toastMessage(String message){
        Toast.makeText(this.c,message,Toast.LENGTH_LONG).show();
    }
    public JSONObject json_object(String object){
        JSONObject obj = null;
        try {
             obj = new JSONObject(object);
        } catch (Exception e) {
        }
        return obj;
    }
    public Boolean setUP(){
        Boolean status = true;
        try {
            if(!this.db().setUp()){
                status = false;
            }
        }catch (Exception e){
            status = false;
        }
        return status;
    }
    public database db(){
        database db = new database(this.c);
        return db;
    }
}
