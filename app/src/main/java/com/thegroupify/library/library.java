package com.thegroupify.library;

import android.content.Context;
import android.content.SharedPreferences;
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

    private SharedPreferences getShare(Context c){
        SharedPreferences s = c.getSharedPreferences("groupify", Context.MODE_PRIVATE);
        return s;
    }

    private void writeIds(Context c,String id,String idName){
        try {
            SharedPreferences s = this.getShare(c);
            s.edit().putString(idName, id).commit();
        }catch (Exception e){
            this.developerError(e.getMessage());
        }
    }

    public String getUserId(){
        SharedPreferences s=this.getShare(c);
        return s.getString("user_id", "empty");
    }

    public void writeUserId(String shopId){
        this.writeIds(c,shopId,"user_id");
    }

    public database db(){
        database db = new database(this.c);
        return db;
    }
}
