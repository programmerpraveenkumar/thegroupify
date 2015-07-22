package com.thegroupify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thegroupify.fragments.login;
import com.thegroupify.library.library;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadFra(new login());
            this.library().showError("test");
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
        }
    }
    public library library(){
        return new library(this);
    }
}
