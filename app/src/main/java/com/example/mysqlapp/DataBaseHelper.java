package com.example.mysqlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Flower.db";
    public static final String TABLE_NAME = "flower_table";

    public static final String ID = "ID";
    public static final String IMAGE = "IMAGE";
    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String WINT_CARE = "WINT_CARE";
    public static final String SUMM_CARE = "SUMM_CARE";
    public static final String REPRODUCTION = "REPRODUCTION";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, IMAGE TEXT, NAME TEXT, TYPE TEXT, WINT_CARE TEXT, SUMM_CARE TEXT, REPRODUCTION TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

     public boolean insertData(String name, String type, String wcare, String scare, String repr){
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues content = new ContentValues();
         content.put(NAME, name);
         content.put(TYPE, type);
         content.put(WINT_CARE, wcare);
         content.put(SUMM_CARE, scare);
         content.put(REPRODUCTION, repr);
         long res = db.insert(TABLE_NAME, null, content);
         if(res == -1)
             return false;
         else
             return true;
     }

     public Cursor getAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res = db.rawQuery("select * from " + TABLE_NAME, null );
         return res;
    }

    public Cursor getNewData(String name, String type, String wcare, String scare, String repr){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null );
        return res;
    }

    public boolean updateData(String id, String name, String type, String wcare, String scare, String repr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(ID, id);
        content.put(NAME, name);
        content.put(TYPE, type);
        content.put(WINT_CARE, wcare);
        content.put(SUMM_CARE, scare);
        content.put(REPRODUCTION, repr);
        db.update(TABLE_NAME, content, "ID = ?", new String[]{ id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{ id });
    }
}
