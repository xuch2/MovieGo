package com.example.moviego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmanager {

    static final String DB_LIKE ="likes.db";
    static final String TABLE_LIKE ="likes";
    static final int DB_VERSION = 1;

    Context mcontext = null;

    private SQLiteDatabase mDatabase = null;

    static private DBmanager mDbManager = null;


    private DBmanager (Context context){
        mcontext = context;
        mDatabase = context.openOrCreateDatabase(DB_LIKE, Context.MODE_PRIVATE, null); //데이터베이스 생성!
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS Students (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT," +
                "original_title TEXT," +
                "overview TEXT," +
                "release_date TEXT," +
                "poster_path TEXT );");
    }

    static public DBmanager getInstance(Context context){
        if(mDbManager == null){
            mDbManager = new DBmanager(context);
        }
        return mDbManager;
    }

    public long insert(ContentValues contentValues){
        /*mDatabase.execSQL("");*/
        return mDatabase.insert(TABLE_LIKE, null, contentValues);

    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        mDatabase.rawQuery("", null);
        //mDatabase.rawQuery("sql 문", null);
        return mDatabase.query(TABLE_LIKE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues contentValues, String where ,String[] stringArgs){
        return mDatabase.update(TABLE_LIKE, contentValues, where, stringArgs);
    }

    public int delete(String where, String[] whereAgrs){
        return mDatabase.delete(TABLE_LIKE, where, whereAgrs);
    }



}
