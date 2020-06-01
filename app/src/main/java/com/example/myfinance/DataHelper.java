package com.example.myfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Finance.db";
    public static final String TABLE_NAME = "Transactions";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Date";
    public static final String COL_3 = "Type";
    public static final String COL_4 = "Amount";
    public static final String COL_5 = "Notes";


    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Type TEXT, Amount TEXT, Notes TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Date, String Type,String Amount, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, Date);
        contentValues.put(COL_3, Type);
        contentValues.put(COL_4, Amount);
        contentValues.put(COL_5, Notes);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

}
