package com.thegroupify.library;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pravaeen kumar new on 22-07-15.
 */
public class serverConnection extends AsyncTask<String ,String ,String >  {
    getServerResult getServerResult;
    String result = "",connectingURL;
    List<NameValuePair> UrlParameters;
    Boolean errorStatus = false;
    int type;
    HashMap<String ,String> tempUrlPair;
    String serverUrl = "http://10.0.2.2/thegroupify/";
    public serverConnection(int type,getServerResult _callbackInterface,String connectingURL,HashMap<String,String> tempUrlPair){
        this.type = type;
        this.tempUrlPair = tempUrlPair;
        UrlParameters = getUrlPair();
        this.getServerResult =_callbackInterface;
        this.connectingURL= serverUrl+connectingURL;
        this.library().serverError(this.errorStatus, "Ready to call..");
        this.execute("");
    }

    @Override
    protected void onPreExecute() {
        this.library().serverError(this.errorStatus, "started onpreExceute");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... te) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post=new HttpPost(this.connectingURL);
        try{
            this.library().serverError(this.errorStatus,"server connection started for " + this.connectingURL);
            post.setEntity(new UrlEncodedFormEntity(this.UrlParameters));
            HttpResponse ree = client.execute(post);
            this.library().serverError(this.errorStatus, "backend server connection get the result..");
            this.result = EntityUtils.toString(ree.getEntity());
            this.library().serverError(this.errorStatus, "backend server connection result is.." + this.result);
        }
        catch(UnsupportedEncodingException e){
            this.library().serverError(this.errorStatus, "encoding  exception " + e.getMessage());
        }
        catch(ClientProtocolException e){
            this.library().serverError(this.errorStatus, "ClientProtocolException exception " + e.getMessage());
        }
        catch(IOException e){
            this.library().serverError(this.errorStatus, "IO exception " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.library().serverError(this.errorStatus, "onposte execute");
        this.getServerResult.serverResult(this.type,this.result);
    }

    private List<NameValuePair> getUrlPair(){
        List<NameValuePair> val = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> obj:this.tempUrlPair.entrySet())
            val.add(new BasicNameValuePair(obj.getKey(),obj.getValue()));
        return val;
    }
    private library library(){
        return new library();
    }
}
