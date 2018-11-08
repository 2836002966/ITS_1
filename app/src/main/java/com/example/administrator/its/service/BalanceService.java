package com.example.administrator.its.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.example.administrator.its.Activity.IndexActivity;
import com.example.administrator.its.R;
import com.example.administrator.its.Util.CacheUntil;
import com.example.administrator.its.Util.NetUntil;
import com.example.administrator.its.entity.CarBalance;
import com.example.administrator.its.entity.EnvironmentalBean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/11/6.
 */

public class BalanceService extends Service{
    private String ip;
    private String port;
    private NetUntil netUntil;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ip= CacheUntil.getString(this,"url","192.168.1.112");
        port=CacheUntil.getString(this,"port","8080");
        netUntil=new NetUntil();

         handler.sendEmptyMessage(0);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                netUntil.getData("{\"CarId\":1}","http://192.168.1.112:8080/transportservice/type/jason/action/GetCarAccountBalance.do",handler);
                sendEmptyMessageDelayed(0,5000);
                break;
                case NetUntil.NET_GETDATA:
                    Intent intent=new Intent();
                    intent.setAction("sendCar");
                    intent.putExtra("CarInfo",msg.obj.toString());
                    LocalBroadcastManager broadcastManager=LocalBroadcastManager.getInstance(BalanceService.this);
                    broadcastManager.sendBroadcast(intent);
                break;
            }


        }
    };
}
