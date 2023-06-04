package com.android.LitangPrince.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.android.LitangPrince.R;

import java.io.ByteArrayOutputStream;

public class UserDatabaseHelper extends SQLiteOpenHelper {



    public UserDatabaseHelper(@Nullable Context context, int version) {
        super(context, "                                                                                                 ", null, version);
//        super(context, "mydata.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT, nickname TEXT, headImage INTEGER )";
        db.execSQL(createTable);

        // Insert some sample data
        ContentValues values = new ContentValues();
        values.put("username", "1200113220");
        values.put("password", "123");
        values.put("nickname", "hpls");
        values.putNull("headImage");
        db.insert("users", null, values);

        // Insert more sample data...
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade database version, if necessary
    }
}
