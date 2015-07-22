package com.thegroupify.library;

import android.content.Context;

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

    }
    public void showError(String message){
        this.dialog().errorDialog(message);
    }
    public dialogBox dialog(){
        return new dialogBox(this.c);
    }
}
