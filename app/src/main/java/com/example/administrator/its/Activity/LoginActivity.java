package com.example.administrator.its.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.its.R;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText username;
    private EditText password;
    private CheckBox rtp,al;
    private ImageView imageView;
    private Button login,register,button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initConfig();
        initView();
        init();
        show();
    }
    private void initConfig(){
        sp = getSharedPreferences("config",MODE_PRIVATE);
        editor = sp.edit();
        editor.putBoolean("isFirstcomming",false);
        editor.commit();
    }
    private void initView(){
        username =  findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        rtp = findViewById(R.id.cb_jzmm);
        al =  findViewById(R.id.cb_zddl);
        imageView = findViewById(R.id.imageview_001);
        login =  findViewById(R.id.bt_login);
        register = findViewById(R.id.bt_register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                checkInfo();
                break;
            case R.id.bt_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageview_001:
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                final View contentView = View.inflate(this, R.layout.logindialog, null);
                builder.setView(contentView);
                final AlertDialog dialog = builder.create();

                dialog.show();
                break;
        }
        boolean checkBoxLogin = rtp.isChecked();
        if (checkBoxLogin)   {

            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putBoolean("jzmm", true);
            editor.commit();
        }   else   {

            editor.putString("username", null);
            editor.putString("password", null);
            editor.putBoolean("jzmm", false);
            editor.commit();
        }
        boolean CheckBoxRegister = al.isChecked();
        if (CheckBoxRegister)   {
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putBoolean("zddl", true);
            editor.commit();
        }else   {

            editor.putString("username", username.getText().toString());
            editor.putString("password", username.getText().toString());
            editor.putBoolean("zddl", false);
            editor.commit();
        }
    }
    private  void checkInfo(){
        String name = username.getText().toString();
        String pwd = password.getText().toString();
        String[] usernameliststr={"admin","user1","user2","user3","user4","user5"};
        String[] pwdliststr={"admin","123456"};
        List<String> usernamelist = Arrays.asList(usernameliststr);
        List<String> pwdlist = Arrays.asList(pwdliststr);
        if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"请将用户信息填写完整", Toast.LENGTH_SHORT).show();
            return;
        }
        if(usernamelist.contains(name) == false || pwdlist.contains(pwd) ==false){
            Toast.makeText(this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
            return;
        }

        boolean CheckBoxRegister = al.isChecked();
        Intent intent = new Intent(this,IndexActivity.class);
        startActivity(intent);
        finish();
    }
    public void init() {
        if (sp.getBoolean("jzmm", false)) {
            username.setText(sp.getString("username", null));
            password.setText(sp.getString("password", null));
            rtp.setChecked(true);
        }
        if (sp.getBoolean("zddl", false)) {
            username.setText(sp.getString("username", null));
            password.setText(sp.getString("password", null));
            al.setChecked(true);
        }
        login.setOnClickListener(this);
    }
    private void show(){
        boolean CheckBoxRegister = al.isChecked();
        if(CheckBoxRegister){
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000);
        }
    }
}
