package com.android.LitangPrince.dao;


import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.android.LitangPrince.R;

import java.io.ByteArrayOutputStream;

/*
这段代码是一个 SQLite 数据库的帮助类，用于创建和管理该应用程序中使用的本地数据库。
在 Android 应用中，每个应用都可以拥有自己的私有数据库，以便存储和检索数据。
在 `DatabaseHelper` 的构造函数中，指定了数据库名称和版本号，其中数据库名称为第一个参数 `" "`，版本号为第四个参数 `version`。
在 `onCreate()` 方法中，定义了一个名为 `orders` 的表，包含了订单的各个属性字段，例如订单名称、图片、金额、下单时间、用户名等。
通过 `execSQL()` 方法执行 SQL 语句来创建这个表。
如果需要对数据库进行升级，可以在 `onUpgrade()` 方法中编写相应的 SQL 语句，
并将新的版本号作为第三个参数传递进来。但在这段代码中，`onUpgrade()` 方法是空实现，即没有任何操作。
当需要访问或修改数据库时，可以通过调用 `getWritableDatabase()` 或 `getReadableDatabase()` 方法
获取到 SQLiteDatabase 对象，从而执行相应的操作。
* */
public class DatabaseHelper extends SQLiteOpenHelper {
//    private Context context;
    public DatabaseHelper(@Nullable Context context, int version) {
        super(context, "                                                                                                 ", null, version);
//        super(context, "mydata.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库
        String createOrders = "create table orders (" +
                "id integer primary key autoincrement," +
                "name text," +
                "image integer," +
                "money real," +
                "time text," +
                "username text)";

        db.execSQL(createOrders);
//        db.execSQL(createOrders);
        // 创建用户表
//            String createUsers = "create table users (" +
//                    "username text primary key," +
//                    "password text," +
//                    "nickname text," +
//                    "picture blob)";
//            db.execSQL(createUsers);
//
//            // Insert a new user with the specified values
//            Bitmap defaultPicture = BitmapFactory.decodeResource(context.getResources(), R.drawable.hpls);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            defaultPicture.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] pictureBytes = stream.toByteArray();
//
//            String insertUser = "insert into users (username, password, nickname, picture) values (?, ?, ?, ?)";
//            SQLiteStatement statement = db.compileStatement(insertUser);
//            statement.bindString(1, "1200113220");
//            statement.bindString(2, "123");
//            statement.bindString(3, "hpls");
//            statement.bindBlob(4, pictureBytes);
//            statement.executeInsert();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
