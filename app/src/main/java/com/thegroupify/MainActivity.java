package com.thegroupify;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thegroupify.fragments.feeds;
import com.thegroupify.fragments.login;
import com.thegroupify.fragments.search_join_group;
import com.thegroupify.library.getServerResult;
import com.thegroupify.library.library;
import com.thegroupify.library.serverConnection;

import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends FragmentActivity implements View.OnClickListener,getServerResult {

    private String currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (this.library().setUP()) {
                if (this._userExist()) {
                    if (this._joinedGroupCount() > 0)
                        this.loadFra(new feeds());
                    else
                        this.loadFra(new search_join_group());
                } else {
                    this.loadFra(new login());
                }
            } else {
                this.library().showError("setup Error");
            }
        }catch (Exception e){
            this.library().developerError("error "+e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private  void loadFra(Fragment f){
        this.currentFragment = f.getClass().getSimpleName();
        FragmentTransaction fragmentmanager=getSupportFragmentManager().beginTransaction();
        fragmentmanager.replace(R.id.loadmain, f);
        //fragmentmanager.addToBackStack("initial");
        fragmentmanager.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_close_dialog:
                this.library().dialog().closeCheckDialog();
            break;
            case R.id.create_account:
                TextView create_email = (TextView) findViewById(R.id.create_email);
                String mail = create_email.getText().toString().trim();
                if(mail.length() > 4){
                    HashMap<String,String> create = new HashMap<String, String>();
                    create.put("email",mail);
                    this._setText(R.id.loading_status, "Creating account!!Please wait...");
                    new serverConnection(R.string.create_account,this,"user/create",create);
                }else{
                    this.library().toastMessage("Please Enter Valid Email");
                }

            break;
        }
    }
    public library library(){
        return new library(this);
    }

    @Override
    public void serverResult(int type, String output) {
        try{
            JSONObject result = this.library().json_object(output);
            if(result.equals(null)){
                throw new Exception("nothing is received from server");
            }
            switch (type){
                case R.string.create_account:
                    if(this.library().db().exeQuery("insert into user(id,mail,join_on,device_id)values('"+result.getString("id")+"','"+result.getString("mail")+"','"+result.getString("join_on")+"','"+result.getString("device_id")+"')")){
                        this._setText(R.id.loading_status,"Successfull!!Account created!!");
                        //this.library().toastMessage("Account created!!!");
                        Cursor c = this.library().db().oneFetchQuery("select id from user");
                        this.library().writeUserId(c.getString(c.getColumnIndex("id")));
                        this._setText(R.id.loading_status, "Successfull !!Please Wait !!!");
                        this.loadFra(new search_join_group());
                    }
                break;
                default:
                    this.library().developerError(this.getString(type) + " does not have any result type");
            }
        }catch (Exception e){
            this.library().toastMessage(e.getMessage());
            this.library().developerError("server Result is "+output+" error "+e.getMessage());
        }
    }
    private Boolean _userExist(){
        Boolean status = false;
        try{
            if(!this.library().getUserId().equals("empty")){
                Cursor c = this.library().db().oneFetchQuery("select id from `user` where id=\'"+this.library().getUserId()+"\'");
                this.library().developerError("user count is "+c.getCount()+" and  user id is "+this.library().getUserId());
                status  = (c.getCount() > 0)?true:false;
            }
        }catch (Exception e){
            this.library().developerError("may be the user id is not available "+this.library().getUserId());
            status = false;
        }
        return  status;
    }
    private int _joinedGroupCount(){
        int count = 0;
        try{
            Cursor c = this.library().db().oneFetchQuery("select id from `group`");
            count = c.getCount();
        }catch (Exception e){

        }
        return count;
    }
    private void _setText(int id,String val){
        TextView tmp = (TextView)findViewById(id);
        tmp.setVisibility(View.VISIBLE);
        tmp.setText(val);
    }
}
