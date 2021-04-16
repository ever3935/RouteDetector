package com.example.routedetector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String dbName = "UserLoginInformation";
    public static final int dbVersion = 1;
    public static final String createTableQuery = "create table UserInfo(name text, email text, password text)";

    public Database(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }

    long insert(String name, String email, String password) {
        SQLiteDatabase sqlDb = getWritableDatabase();
        ContentValues cVal = new ContentValues();
        cVal.put("name", name);
        cVal.put("email", email);
        cVal.put("password", password);
        long id = sqlDb.insert("UserInfo", null, cVal);
        sqlDb.close();
        return id;
    }

    Cursor getData() {
        SQLiteDatabase sqlDb = getReadableDatabase();
        Cursor cursor = sqlDb.rawQuery("select * from UserInfo", null);
        return cursor;
    }

}