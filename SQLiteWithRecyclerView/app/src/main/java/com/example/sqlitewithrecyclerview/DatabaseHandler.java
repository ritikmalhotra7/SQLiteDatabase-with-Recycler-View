package com.example.sqlitewithrecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, PSFVariables.databaseName, null, PSFVariables.database_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + PSFVariables.tableName + "(" +
                PSFVariables.keyId + " INTEGER PRIMARY KEY," +
                PSFVariables.keyPar1 + "TEXT," +
                PSFVariables.keyPar2 + "TEXT)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PSFVariables.tableName);
    }
    //crud - create remove update display
    public void addModel(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PSFVariables.keyPar1, model.getPar1());
        cv.put(PSFVariables.keyPar2, model.getPar2());
        long res = db.insert(PSFVariables.tableName, null, cv);
        if (res == -1){
            Log.d("msg", "FAILED INSERTION");

        }else {
            Log.d("msg", "COMPLETE INSERTION");
        }
    }
    public void removeModel(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(PSFVariables.tableName, PSFVariables.keyId + "=?", new String[] {String.valueOf(model.getId())});
        if (res == -1){
            Log.d("msg", "FAILED DELETION");

        }else {
            Log.d("msg", "COMPLETE DELETION");
        }
    }
    public void updateModel(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PSFVariables.keyPar1, model.getPar1());
        cv.put(PSFVariables.keyPar2, model.getPar2());
        long res = db.update(PSFVariables.tableName,cv, PSFVariables.keyId + "=?", new String[] {String.valueOf(model.getId())});
        if (res == -1){
            Log.d("msg", "FAILED UPDATION");

        }else {
            Log.d("msg", "COMPLETE UPDATION");
        }
    }
    public List<Model> getAllModel(){//or display all model
        SQLiteDatabase db = this.getReadableDatabase();
        List<Model> ls= new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + PSFVariables.tableName,null);
        if(c.moveToFirst()){
            do{
                Model model = new Model();
                model.setId(Integer.parseInt(c.getString(0)));
                model.setPar1(c.getString(1));
                model.setPar2(c.getString(2));
                ls.add(model);
            }while(c.moveToNext());
        }
        return ls;
    }
    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + PSFVariables.tableName,null);
        return c.getCount();

    }
}
