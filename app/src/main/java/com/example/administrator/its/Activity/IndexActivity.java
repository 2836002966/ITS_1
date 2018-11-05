package com.example.administrator.its.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.its.Adapter.HeadView;
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
import com.example.administrator.its.configuration.ListViewConfiguration;
import com.example.administrator.its.entity.ListViewData;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private int num;
    private List<ListViewData> fruitList = new ArrayList<>();
    private  Fragment  currentFragment=new Fragment();
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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        dj();
    }

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
                switch (fruit.getName()) {
                    case "车辆账户管理":
                        switchFragment(accountFragment);
                        textView.setText("车辆账户管理");
                        break;
                    case "公交查询":
                       switchFragment(busQueryFragment);
                        textView.setText("公交查询");
                        break;
                    case "红绿灯管理":
                        switchFragment(trafficLightFragment);
                        textView.setText("红绿灯管理");
                        break;
                    case "车辆违章":
                        switchFragment(trafficViolationFrament);
                        textView.setText("车辆违章");
                        break;
                    case "路况查询":
                        switchFragment(trafficQueryFrament);
                        textView.setText("路况查询");
                        break;
                    case "生活助手":

                        switchFragment(lifeAssistantFrament);
                        textView.setText("生活助手");
                        break;
                    case "数据分析":

                        switchFragment(dataAnalysisFrament);
                        textView.setText("数据分析");
                        break;
                    case "个人中心":

                        switchFragment(personalFrament);
                        textView.setText("个人中心");
                        break;
                    case "环境指标":
                        switchFragment(environmentalFragment);
                        textView.setText("环境指标");
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



    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.frame, targetFragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }
}
