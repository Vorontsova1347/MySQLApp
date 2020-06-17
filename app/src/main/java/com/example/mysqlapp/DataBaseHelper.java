package com.example.mysqlapp;

import android.content.Context;
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
}
