package com.example.administrator.its.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.its.Adapter.MyViewPager;
import com.example.administrator.its.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ViewPager viewPager;
    private View view1, view2, view3;
    private List<View> views;
    private SharedPreferences sp;
    Button btn;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Log.e("eeeeeee","请求失败");
        sp = getSharedPreferences("config",MODE_PRIVATE);
        if(!sp.getBoolean("isFirstcomming",true)){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
                finish();
            }
        },3000);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton(this);
            //这里用使用0，1，2，3为id，对应ViewPager的pos值
            rb.setId(i);
            rg.addView(rb);
            //默认第一个选中
            if (i == 0) {
                rb.setChecked(true);
            }
        }

        final MyViewPager myViewPager = (MyViewPager) findViewById(R.id.vp_my);
        //按钮点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int pos = checkedId;
                myViewPager.setCurrentItem(pos);
            }
        });
        //ViewPager切换事件
        myViewPager.setOnPagerChangeListener(new MyViewPager.OnPagerChangeListener() {
            @Override
            public void onPagerChange(int pos) {
                rg.check(pos);
            }
        });
        View testView = View.inflate(MainActivity.this, R.layout.layout001, null);
        myViewPager.addView(testView, 2);
        /*myViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });*/
        Button button = myViewPager.findViewById(R.id.btn_001);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
