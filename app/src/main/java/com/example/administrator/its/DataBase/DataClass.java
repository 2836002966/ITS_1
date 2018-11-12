package com.example.administrator.its.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/11/5.
 */

public class DataClass extends SQLiteOpenHelper{
    private static final String  NAME="its.db";
    private static int version=1;
    private String data = "create table data("+
            "id INTEGER not null primary key autoincrement,"+
            "temperature int,"+
            "humidity int,"+
            "lightIntensity int,"+
            "co2 int,"+
            "pm int,"+
            "status int,"+
            "time varchar(20))";
    DataClass(Context context){
        super(context, NAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
