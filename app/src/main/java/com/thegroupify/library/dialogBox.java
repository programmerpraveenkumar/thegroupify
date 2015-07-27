package com.thegroupify.library;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thegroupify.R;

import org.w3c.dom.Text;

/**
 * Created by pravaeen kumar new on 22-07-15.
 */
public class dialogBox implements View.OnClickListener {
    static Context c;
    static Dialog d;
    public  dialogBox(Context c){
        this.c=c;
    }

    public void closeCheckDialog(){
        this.library().developerError("closing dialog");
        try {
            if (d != null && d.isShowing()) {
                d.dismiss();
            }
        }catch(Exception e){
            this.library().developerError(" error while closing dialog "+e.getMessage());
        }
    }

    public void errorDialog (String message){
        this.closeCheckDialog();
        d = new Dialog(c);
        d.setTitle("Error");
        d.setContentView(R.layout.dialog);
        Button ok_close_dialog = (Button) d.findViewById(R.id.ok_close_dialog);
        TextView errorMessage = (TextView)d.findViewById(R.id.error_message);
        errorMessage.setText(message);
        ok_close_dialog.setOnClickListener(this);
        d.show();
    }

    public Dialog currentDialogObj(){
        d = new Dialog(c);
        return d;
    }

    private  library library(){
        return new library(c);
    }

    public void  closeDialog(){
        this.closeCheckDialog();
    }

    public Dialog getObj(){
        return d;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_close_dialog:
                this.closeDialog();
            break;
        }
    }
}
