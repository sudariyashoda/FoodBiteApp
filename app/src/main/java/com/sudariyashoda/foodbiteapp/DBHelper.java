package com.sudariyashoda.foodbiteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        // Create a table named "users" with two columns: inputName and inputPassword
        MyDB.execSQL("create Table users(inputName TEXT primary key,inputPassword TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        // Drop the existing table named "users" if it exists
        MyDB.execSQL("drop Table if exists users");

    }

    public boolean insertdata(String inputName, String inputPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // Insert the inputName and inputPassword values into the users table
        contentValues.put("inputName", inputName);
        contentValues.put("inputPassword", inputPassword);
        long result = MyDB.insert("users", null, contentValues);
        // Check if the insertion was successful
        if (result == -1) return false;
        else return true;
    }

    public boolean checkinputName(String inputName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // Retrieve a cursor containing rows where inputName matches the provided value
        Cursor cursor = MyDB.rawQuery("Select * from users where inputName=?", new String[]{inputName});
        // Check if the cursor contains any rows
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean checkinputNameinputPassword(String inputName, String inputPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // Retrieve a cursor containing rows where both inputName and inputPassword match the provided values
        Cursor cursor = MyDB.rawQuery("Select * from users where inputName=? and inputPassword=?",
                new String[]{inputName, inputPassword});
        // Check if the cursor contains any rows
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}
