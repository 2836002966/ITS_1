package com.example.administrator.its.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.its.Adapter.ListViewAdapter;
import com.example.administrator.its.Fragment.AccountFragment;
import com.example.administrator.its.Fragment.BusQueryFragment;
import com.example.administrator.its.Fragment.DataAnalysisFrament;
import com.example.administrator.its.Fragment.EnvironmentalFragment;
import com.example.administrator.its.Fragment.LifeAssistantFrament;
import com.example.administrator.its.Fragment.PersonalFrament;
import com.example.administrator.its.Fragment.TrafficLightFragment;
import com.example.administrator.its.Fragment.TrafficQueryFrament;
import com.example.administrator.its.Fragment.TrafficViolationFrament;
import com.example.administrator.its.R;
import com.example.administrator.its.Util.CacheUntil;
import com.example.administrator.its.configuration.ListViewConfiguration;
import com.example.administrator.its.entity.CarBalance;
import com.example.administrator.its.entity.ListViewData;
import com.example.administrator.its.service.BalanceService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private int num;
    private TextView textView2;
    private List<ListViewData> fruitList = new ArrayList<>();
    private  Fragment  currentFragment=new Fragment();
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;
    private  FragmentTransaction transaction;
    TextView textView;
    Button button;
    AccountFragment accountFragment = new AccountFragment();
    BusQueryFragment busQueryFragment = new BusQueryFragment();
    DataAnalysisFrament dataAnalysisFrament = new DataAnalysisFrament();
    LifeAssistantFrament lifeAssistantFrament = new LifeAssistantFrament();
    PersonalFrament personalFrament = new PersonalFrament();
    TrafficLightFragment trafficLightFragment = new TrafficLightFragment();
    TrafficQueryFrament trafficQueryFrament = new TrafficQueryFrament();
    TrafficViolationFrament trafficViolationFrament = new TrafficViolationFrament();
    EnvironmentalFragment environmentalFragment = new EnvironmentalFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dj();
        Intent intent=new Intent(this,BalanceService.class);
        startService(intent);

        intentFilter=new IntentFilter();
        intentFilter.addAction("sendCar");
        broadcastManager=LocalBroadcastManager.getInstance(this);

        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data= intent.getStringExtra("CarInfo");
                showNotifyOnlyText(data);
            }
        };

        broadcastManager.registerReceiver(broadcastReceiver,intentFilter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        this.startService(new Intent(this,BalanceService.class));
        getDate();

    }

//    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String info= intent.getStringExtra("CarInfo");
//            Log.e("info",info);
//        }
//    };

    private void dj() {
        mDrawerLayout = findViewById(R.id.dl_left);
        button = findViewById(R.id.bt_dj_3);
        textView = findViewById(R.id.text001);
        ListViewConfiguration cc = new ListViewConfiguration();
        cc.initListData(fruitList);
        ListViewAdapter adapter1 = new ListViewAdapter(IndexActivity.this, R.layout.list_item, fruitList);
        ListView listView1 = findViewById(R.id.lv_left_menu);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListViewData fruit = fruitList.get(i);
                Toast.makeText(IndexActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
                transaction = getSupportFragmentManager()
                        .beginTransaction();
                switch (fruit.getName()) {
                    case "车辆账户管理":
                        textView.setText("车辆账户管理");
                        transaction.replace(R.id.frame,accountFragment).commit();
                        break;

                    case "红绿灯管理":

                        transaction.replace(R.id.frame,trafficLightFragment).commit();
                        textView.setText("红绿灯管理");
                        break;
                    case "车辆违章":
                        transaction.replace(R.id.frame,trafficViolationFrament).commit();
                        textView.setText("车辆违章");
                        break;
                    case "路况查询":
                        transaction.replace(R.id.frame,trafficQueryFrament).commit();
                        textView.setText("路况查询");
                        break;
                    case "生活助手":

                        transaction.replace(R.id.frame,lifeAssistantFrament).commit();
                        textView.setText("生活助手");
                        break;
                    case "数据分析":
                        transaction.replace(R.id.frame,dataAnalysisFrament).commit();
                        textView.setText("数据分析");
                        break;
                    case "个人中心":
                        transaction.replace(R.id.frame,personalFrament).commit();
                        textView.setText("个人中心");
                        break;
                    case "环境指标":

                        transaction.replace(R.id.frame,environmentalFragment).commit();
                        textView.setText("环境指标");
                        break;
                    case "公交查询":
                        transaction.replace(R.id.frame,busQueryFragment).commit();
                        textView.setText("公交查询");
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num+=1;
                if (num%2==0){
                    mDrawerLayout.closeDrawers();
                }else {
                    mDrawerLayout.openDrawer(GravityCompat.START);

                }
            }
        });
    }






    private void showNotifyOnlyText(String data) {

            Intent intent1 = new Intent(this,BalanceActivity.class);
            PendingIntent pend = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            CarBalance car=new CarBalance();
            try {
                String serverinfo=new JSONObject(data).getString("serverinfo");
                JSONObject jsonObject =new JSONObject(serverinfo);

                car.setBalance(jsonObject.getInt("Balance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("小车余额" )
                .setContentText(car.getBalance()+"")
                .setContentIntent(pend);


            NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1,builder.build());

    }
    private void getDate(){
        textView2 = findViewById(R.id.ip_context);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM/dd HH:mm-ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        textView2.setText(CacheUntil.getString(this,"url","192.168.1.1")+" "+simpleDateFormat.format(date));
    }

    private static boolean isExit = false;

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 3000);
        } else {


            this.finish();
        }
    }
}
