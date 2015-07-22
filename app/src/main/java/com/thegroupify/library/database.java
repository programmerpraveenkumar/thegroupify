package com.thegroupify.library;

/**
 * Created by pravaeen kumar new on 21-07-15.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.io.OutputStream;

public class database extends SQLiteOpenHelper {
    private static String db_name = "1.db";
    Context c;
    private   static String  db_path,db_with_name;
    public database(Context context) {
        super(context,db_name, null,1);
        c = context;
        if(android.os.Build.VERSION.SDK_INT >= 17){
            db_path = c.getApplicationInfo().dataDir+"/databases/";
        }
        else{
            db_path = "data/data/"+c.getPackageName()+"/databases/";
        }
        db_with_name = db_path+db_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public Boolean setUp(){
        Boolean status = true,new_database = false;
        if(new_database){
            this._CreateDatabase();
        }
        if(!this._checkDatabase()){
            this.getReadableDatabase();
            if(!this._CreateDatabase()){
                status = false;
            }
        }
        return status;
    }

    private Boolean _checkDatabase(){
        Boolean status = false;

        try{
            File checkDb = new File(db_with_name);
            if(checkDb .exists()){
                status = true;
            }else{
                this.library().developerError("creating database " + checkDb.getAbsolutePath());
                checkDb.getParentFile().mkdirs();
            }
        }
        catch(Exception e){
            this.library().developerError("while checking  database " + e.getMessage());
        }
        return status;
    }
    public static SQLiteDatabase test(){
        SQLiteDatabase Read = SQLiteDatabase.openDatabase(db_with_name,null,SQLiteDatabase.OPEN_READWRITE);
        return Read;
    }

    private Boolean _CreateDatabase(){
        Boolean status = false;
        try{
            InputStream assetDatabase = this.c.getAssets().open(db_name);
            OutputStream output = new FileOutputStream(db_with_name);
            byte[] buf  = new byte[1024];
            int length;
            while((length = assetDatabase.read(buf)) > 0){
                output.write(buf,0,length);
            }
            output.flush();
            output.close();
            assetDatabase.close();
            status = true;
        }
        catch(Exception e){
            this.library().showError("while creating database  " + e.getMessage());
        }
        return status;
    }
    public library library(){
        return new library();
    }
}
