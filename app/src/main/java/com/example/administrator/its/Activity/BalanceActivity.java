package com.example.administrator.its.Activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.its.R;
import com.example.administrator.its.Util.CacheUntil;
import com.example.administrator.its.Util.NetUntil;
import com.example.administrator.its.entity.CarBalance;
import com.example.administrator.its.service.BalanceService;

import org.json.JSONException;
import org.json.JSONObject;

public class BalanceActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;
    Button chaxun,chongzhi,back;
    EditText et_chongzhi;
    private String ip;
    private String port;
    private NetUntil netUntil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Intent intent=new Intent(this,BalanceService.class);
        startService(intent);

        intentFilter=new IntentFilter();
        intentFilter.addAction("sendCar");
        broadcastManager= LocalBroadcastManager.getInstance(this);

        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data= intent.getStringExtra("CarInfo");
                showNotifyOnlyText(data);
            }
        };

        broadcastManager.registerReceiver(broadcastReceiver,intentFilter);
        chaxun = findViewById(R.id.chaxun);
        back = findViewById(R.id.back_001);
        chongzhi = findViewById(R.id.chongzhi);
        et_chongzhi = findViewById(R.id.et_chongzhi);


    }
    private void showNotifyOnlyText(String data) {


//                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//                    intent.setComponent(new ComponentName(getContext(), MainActivity.class));
//
//                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

            final CarBalance car=new CarBalance();
            try {
                String serverinfo=new JSONObject(data).getString("serverinfo");
                JSONObject jsonObject =new JSONObject(serverinfo);

                car.setBalance(jsonObject.getInt("Balance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            chaxun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(BalanceActivity.this,car.getBalance()+"", Toast.LENGTH_SHORT).show();
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            chongzhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i;
                    i=Integer.parseInt(et_chongzhi.getText().toString());
                    if(i>500){
                        AlertDialog.Builder builder = new AlertDialog.Builder(BalanceActivity.this);
                        View contentView = LayoutInflater.from(BalanceActivity.this).inflate(R.layout.passwordlog, null);
                        builder.setView(contentView);
                       final EditText passwrod = contentView.findViewById(R.id.password001);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String str = passwrod.getText().toString().trim();
                                if (str.equals("123456")){
                                    ip= CacheUntil.getString(BalanceActivity.this,"url","192.168.1.112");
                                    port=CacheUntil.getString(BalanceActivity.this,"port","8080");
                                    netUntil=new NetUntil();
                                    handler.sendEmptyMessage(0);
                                }

                            }
                        });
                        //    设置一个NegativeButton
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Toast.makeText(BalanceActivity.this, "充值取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                        final AlertDialog dialog = builder.create();

                        dialog.show();
                    }else if(i<500 || i>0) {
                        ip= CacheUntil.getString(BalanceActivity.this,"url","192.168.1.112");
                        port=CacheUntil.getString(BalanceActivity.this,"port","8080");
                        netUntil=new NetUntil();
                        handler.sendEmptyMessage(0);
                    }

                    /*ip= CacheUntil.getString(BalanceActivity.this,"url","192.168.1.112");
                    port=CacheUntil.getString(BalanceActivity.this,"port","8080");
                    netUntil=new NetUntil();
                    handler.sendEmptyMessage(0);*/
                }
            });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    netUntil.getData("{\"CarId\":1,\"Money\" : "+et_chongzhi.getText()+"}","http://192.168.1.112:8080/transportservice/type/jason/action/SetCarAccountRecharge.do",handler);
                    break;
                case NetUntil.NET_GETDATA:
                    Toast.makeText(BalanceActivity.this,"充值成功", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    };

}
