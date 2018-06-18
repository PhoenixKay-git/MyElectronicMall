package com.bwie.test.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SqlDao {
    private MyHelper myHelper;
    private SQLiteDatabase database;

    public SqlDao(Context context){
        //获取到一个帮助类对象
        myHelper = new MyHelper(context);
        database = myHelper.getWritableDatabase();
    }

    //增加
    public ContentValues add(ContentValues value){
        database.insert("user", null, value);
        return value;
    }

    public void add1(String s){
        database.execSQL("insert into user values(?)",new Object[]{s});
    }

    //查找所有
    public List<String> select() {
        List<String> list=new ArrayList<>();
        Cursor user = database.rawQuery("select * from user", new String[]{});
        while (user.moveToNext()) {
            String name = user.getString(user.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

    //清空
    public  void  delete(){
        database.execSQL("delete from user");
    }
}
