package com.example.administrator.its.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.its.entity.EnvironmentalBean;

/**
 * Created by Administrator on 2018/11/8.
 */

public class DataOperator {
    private DataClass dbHelper;
    private SQLiteDatabase db;
    private static DataOperator instance;
    private ContentValues values;
    private Long result;
    private Cursor cursor;

    public DataOperator(Context context){
        dbHelper= new DataClass(context);
        db=dbHelper.getWritableDatabase();
    }

    public static synchronized DataOperator getInstance(Context context){
        if (instance==null){
            instance=new DataOperator(context);
        }
        return instance;
    }
    String[] row = new String[]{"temperature","humidity","lightIntensity","co2","pm","status"};
    public long insertData(String time, int... datas){
        values = new ContentValues();
        values.put("time",time);
        for (int i = 0; i < datas.length; i++) {
            values.put(row[i],datas[i]);
        }
        result = db.insert("data",null,values);
        values.clear();
        cursor = db.rawQuery("select * from data",null);
        if(cursor.getCount() > 5){
            db.execSQL("delete from data where id = (select id from data order by id limit 0,1)");
        }
        cursor.close();
        return result;
    }

}
