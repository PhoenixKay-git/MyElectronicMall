package com.bwie.test.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context) {
        super(context, "Shangpin.db", null, 1);
    }

    //第一次创建数据库的时候调用,,,,在这里面可以把表结构创建出来
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (name varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
